package org.dromara.common.websocket.interceptor;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.core.utils.ServletUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.satoken.utils.LoginHelper;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

import static org.dromara.common.websocket.constant.WebSocketConstants.LOGIN_USER_KEY;

/**
 * WebSocket握手请求的拦截器
 *
 * @author zendwang
 */
@Slf4j
public class PlusWebSocketInterceptor implements HandshakeInterceptor {

    /**
     * WebSocket握手之前执行的前置处理方法
     *
     * @param request    WebSocket握手请求
     * @param response   WebSocket握手响应
     * @param wsHandler  WebSocket处理程序
     * @param attributes 与WebSocket会话关联的属性
     * @return 如果允许握手继续进行，则返回true；否则返回false
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        try {
            // 检查是否登录 是否有token
            LoginUser loginUser = LoginHelper.getLoginUser();

            // 解决 ws 不走 mvc 拦截器问题(cloud 版本不受影响)
            // 检查 header 与 param 里的 clientid 与 token 里的是否一致
            String headerCid = ServletUtils.getRequest().getHeader(LoginHelper.CLIENT_KEY);
            String paramCid = ServletUtils.getParameter(LoginHelper.CLIENT_KEY);
            String clientId = StpUtil.getExtra(LoginHelper.CLIENT_KEY).toString();
            if (!StringUtils.equalsAny(clientId, headerCid, paramCid)) {
                // token 无效
                throw NotLoginException.newInstance(StpUtil.getLoginType(),
                    "-100", "客户端ID与Token不匹配",
                    StpUtil.getTokenValue());
            }

            attributes.put(LOGIN_USER_KEY, loginUser);
            return true;
        } catch (NotLoginException e) {
            log.error("WebSocket 认证失败'{}',无法访问系统资源", e.getMessage());
            return false;
        }
    }

    /**
     * WebSocket握手成功后执行的后置处理方法
     *
     * @param request   WebSocket握手请求
     * @param response  WebSocket握手响应
     * @param wsHandler WebSocket处理程序
     * @param exception 握手过程中可能出现的异常
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        // 在这个方法中可以执行一些握手成功后的后续处理逻辑，比如记录日志或者其他操作
    }

}


//package org.dromara.common.websocket.interceptor;
//
//import cn.dev33.satoken.exception.NotLoginException;
//import cn.dev33.satoken.stp.StpUtil;
//import cn.hutool.core.util.ObjectUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.dromara.common.core.domain.model.LoginUser;
//import org.dromara.common.core.utils.StringUtils;
//import org.dromara.common.satoken.utils.LoginHelper;
//import org.dromara.common.websocket.holder.WebSocketSessionHolder;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//import java.io.IOException;
//import java.net.URLDecoder;
//import java.nio.charset.StandardCharsets;
//import java.util.Arrays;
//import java.util.Map;
//
//import static org.dromara.common.websocket.constant.WebSocketConstants.LOGIN_USER_KEY;
//
///**
// * WebSocket 握手拦截器（含 token 校验 + clientid 校验）
// */
//@Slf4j
//public class PlusWebSocketInterceptor implements HandshakeInterceptor {
//
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
//                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {
//        try {
//            // ✅ 提取参数
//            String query = request.getURI().getQuery();
//            if (StringUtils.isBlank(query)) {
//                log.warn("WebSocket 握手失败：query 参数为空");
//                return false;
//            }
//
//            // ✅ 解析 Authorization
//            String tokenParam = Arrays.stream(query.split("&"))
//                .filter(p -> p.startsWith("Authorization="))
//                .findFirst()
//                .orElse(null);
//
//            if (StringUtils.isBlank(tokenParam)) {
//                log.warn("WebSocket 握手失败：缺少 Authorization 参数");
//                return false;
//            }
//
//            String rawToken = tokenParam.substring("Authorization=".length());
//            String decodedToken = URLDecoder.decode(rawToken, StandardCharsets.UTF_8);
//            if (!decodedToken.startsWith("Bearer ")) {
//                log.warn("WebSocket 握手失败：Token 格式错误，应以 Bearer 开头");
//                return false;
//            }
//            String token = decodedToken.substring("Bearer ".length());
//
//            // ✅ 获取 loginId 并校验 token 是否有效
//            Object loginId = StpUtil.getLoginIdByToken(token);
//            if (loginId == null) {
//                throw NotLoginException.newInstance(StpUtil.getLoginType(), "-100", "Token 无效或已过期", token);
//            }
//
//            // ✅ 获取 LoginUser（必须在登录时写入 tokenSession）
//            LoginUser loginUser = LoginHelper.getLoginUser(token);
//            if (loginUser == null) {
//                log.warn("WebSocket 拦截失败：无法获取 LoginUser（请确认登录成功后已写入）");
//                return false;
//            }
//
//            // ✅ 校验 clientid 参数
//            String clientIdParam = Arrays.stream(query.split("&"))
//                .filter(p -> p.startsWith("clientid="))
//                .map(p -> p.substring("clientid=".length()))
//                .findFirst()
//                .orElse(null);
//
//            Object clientIdStored = StpUtil.getExtra(token, LoginHelper.CLIENT_KEY);
//            if (!StringUtils.equals(String.valueOf(clientIdStored), clientIdParam)) {
//                throw NotLoginException.newInstance(StpUtil.getLoginType(), "-101", "Token 中 clientId 与传参不一致", token);
//            }
//
//            // ✅ 注入用户信息用于后续逻辑
//            attributes.put(LOGIN_USER_KEY, loginUser);
//            log.info("✅ WebSocket 鉴权通过：userId={}, clientId={}", loginUser.getUserId(), clientIdParam);
//            return true;
//
//        } catch (NotLoginException e) {
//            log.error("❌ WebSocket 鉴权失败：{}", e.getMessage());
//            return false;
//        } catch (Exception e) {
//            log.error("❌ WebSocket 握手异常：{}", e.getMessage(), e);
//            return false;
//        }
//    }
//
//    @Override
//    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
//                               WebSocketHandler wsHandler, Exception exception) {
//        // 握手后逻辑可选
//        System.out.println(1);
//
//    }
//
//
//}
