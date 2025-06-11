package org.dromara.common.websocket.handler;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.websocket.dto.WebSocketMessageDto;
import org.dromara.common.websocket.holder.WebSocketSessionHolder;
import org.dromara.common.websocket.utils.WebSocketUtils;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.List;

import static org.dromara.common.websocket.constant.WebSocketConstants.LOGIN_USER_KEY;

/**
 * WebSocketHandler 实现类
 *
 * @author zendwang
 */
@Slf4j
public class PlusWebSocketHandler extends AbstractWebSocketHandler {

    /**
     * 连接成功后
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        LoginUser loginUser = (LoginUser) session.getAttributes().get(LOGIN_USER_KEY);
        if (ObjectUtil.isNull(loginUser)) {
            session.close(CloseStatus.BAD_DATA);
            log.info("[connect] invalid token received. sessionId: {}", session.getId());
            return;
        }
        WebSocketSessionHolder.addSession(loginUser.getUserId(), session);
        log.info("[connect] sessionId: {},userId:{},userType:{}", session.getId(), loginUser.getUserId(), loginUser.getUserType());
    }

    /**
     * 处理接收到的文本消息
     *
     * @param session WebSocket会话
     * @param message 接收到的文本消息
     * @throws Exception 处理消息过程中可能抛出的异常
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 从WebSocket会话中获取登录用户信息
        LoginUser loginUser = (LoginUser) session.getAttributes().get(LOGIN_USER_KEY);

        // 创建WebSocket消息DTO对象
        WebSocketMessageDto webSocketMessageDto = new WebSocketMessageDto();
        webSocketMessageDto.setSessionKeys(List.of(loginUser.getUserId()));
        webSocketMessageDto.setMessage(message.getPayload());
        WebSocketUtils.publishMessage(webSocketMessageDto);
    }

    /**
     * 处理接收到的二进制消息
     *
     * @param session WebSocket会话
     * @param message 接收到的二进制消息
     * @throws Exception 处理消息过程中可能抛出的异常
     */
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        super.handleBinaryMessage(session, message);
    }

    /**
     * 处理接收到的Pong消息（心跳监测）
     *
     * @param session WebSocket会话
     * @param message 接收到的Pong消息
     * @throws Exception 处理消息过程中可能抛出的异常
     */
    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        WebSocketUtils.sendPongMessage(session);
    }

    /**
     * 处理WebSocket传输错误
     *
     * @param session   WebSocket会话
     * @param exception 发生的异常
     * @throws Exception 处理过程中可能抛出的异常
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("[transport error] sessionId: {} , exception:{}", session.getId(), exception.getMessage());
    }

    /**
     * 在WebSocket连接关闭后执行清理操作
     *
     * @param session WebSocket会话
     * @param status  关闭状态信息
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        LoginUser loginUser = (LoginUser) session.getAttributes().get(LOGIN_USER_KEY);
        if (ObjectUtil.isNull(loginUser)) {
            log.info("[disconnect] invalid token received. sessionId: {}", session.getId());
            return;
        }
        WebSocketSessionHolder.removeSession(loginUser.getUserId());
        log.info("[disconnect] sessionId: {},userId:{},userType:{}", session.getId(), loginUser.getUserId(), loginUser.getUserType());
    }

    /**
     * 指示处理程序是否支持接收部分消息
     *
     * @return 如果支持接收部分消息，则返回true；否则返回false
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}

//package org.dromara.common.websocket.handler;
//
//import cn.hutool.core.util.ObjectUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.dromara.common.core.domain.model.LoginUser;
//import org.dromara.common.websocket.dto.WebSocketMessageDto;
//import org.dromara.common.websocket.holder.WebSocketSessionHolder;
//import org.dromara.common.websocket.utils.WebSocketUtils;
//import org.springframework.web.socket.*;
//import org.springframework.web.socket.handler.AbstractWebSocketHandler;
//
//import java.io.IOException;
//import java.util.List;
//
//import static org.dromara.common.websocket.constant.WebSocketConstants.LOGIN_USER_KEY;
//
///**
// * WebSocketHandler 实现类
// *
// * @author zendwang
// */
//@Slf4j
//public class PlusWebSocketHandler extends AbstractWebSocketHandler {
//
//    /**
//     * 连接成功后
//     */
////    @Override
////    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
////        LoginUser loginUser = (LoginUser) session.getAttributes().get(LOGIN_USER_KEY);
////        if (ObjectUtil.isNull(loginUser)) {
////            session.close(CloseStatus.BAD_DATA);
////            log.info("[connect] invalid token received. sessionId: {}", session.getId());
////            return;
////        }
////        WebSocketSessionHolder.addSession(loginUser.getUserId(), session);
////        log.info("[connect] sessionId: {},userId:{},userType:{}", session.getId(), loginUser.getUserId(), loginUser.getUserType());
////    }
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
//        LoginUser loginUser = (LoginUser) session.getAttributes().get(LOGIN_USER_KEY);
//        if (ObjectUtil.isNull(loginUser)) {
//            session.close(CloseStatus.BAD_DATA);
//            log.info("[connect] invalid token received. sessionId: {}", session.getId());
//            return;
//        }
//        WebSocketSessionHolder.addSession(loginUser.getUserId(), session);
//        log.info("[connect] sessionId: {},userId:{},userType:{}", session.getId(), loginUser.getUserId(), loginUser.getUserType());
//        log.info("session attributes: {}", session.getAttributes());
//
//        // ✅ 主动发送欢迎消息，避免连接空闲被关闭
//        session.sendMessage(new TextMessage("🟢 已连接 WebSocket，欢迎 userId=" + loginUser.getUserId()));
//        session.sendMessage(new TextMessage("{\"code\":200,\"data\":{\"content\":\"🟢 已连接 WebSocket\",\"senderName\":\"系统\"}}"));
//
//    }
//
//
//    /**
//     * 处理接收到的文本消息
//     *
//     * @param session WebSocket会话
//     * @param message 接收到的文本消息
//     * @throws Exception 处理消息过程中可能抛出的异常
//     */
////    @Override
////    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
////        LoginUser loginUser = (LoginUser) session.getAttributes().get(LOGIN_USER_KEY);
////        String payload = message.getPayload();
////
////        if ("ping".equalsIgnoreCase(payload)) {
////            session.sendMessage(new TextMessage("pong")); // 可选回应
////            return;
////        }
////
////        // 正常消息处理
////        WebSocketMessageDto dto = new WebSocketMessageDto();
////        dto.setSessionKeys(List.of(loginUser.getUserId()));
////        dto.setMessage(payload);
////        WebSocketUtils.publishMessage(dto);
////    }
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();
//        LoginUser loginUser = (LoginUser) session.getAttributes().get(LOGIN_USER_KEY);
//
//        if (loginUser == null) {
//            session.close(CloseStatus.BAD_DATA);
//            log.warn("[message] 登录信息缺失，关闭连接 sessionId: {}", session.getId());
//            return;
//        }
//
//        // ✅ 心跳消息处理
//        if ("ping".equalsIgnoreCase(payload) || payload.contains("\"type\":\"ping\"")) {
//            log.debug("[ping] 收到心跳，userId={}", loginUser.getUserId());
//            return;
//        }
//
//        // ✅ 忽略 register 类型（前端首次主动注册）
//        if (payload.contains("\"type\":\"register\"")) {
//            log.debug("[register] 忽略前端注册重复消息，userId={}", loginUser.getUserId());
//            return;
//        }
//
//        // ✅ 正常业务消息转发
//        WebSocketMessageDto dto = new WebSocketMessageDto();
//        dto.setSessionKeys(List.of(loginUser.getUserId()));
//        dto.setMessage(payload);
//        WebSocketUtils.publishMessage(dto);
//
//        log.info("[message] 收到消息，userId={}，内容={}", loginUser.getUserId(), payload);
//    }
//
//
//
//    /**
//     * 处理接收到的二进制消息
//     *
//     * @param session WebSocket会话
//     * @param message 接收到的二进制消息
//     * @throws Exception 处理消息过程中可能抛出的异常
//     */
//    @Override
//    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
//        super.handleBinaryMessage(session, message);
//    }
//
//    /**
//     * 处理接收到的Pong消息（心跳监测）
//     *
//     * @param session WebSocket会话
//     * @param message 接收到的Pong消息
//     * @throws Exception 处理消息过程中可能抛出的异常
//     */
//    @Override
//    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
//        WebSocketUtils.sendPongMessage(session);
//    }
//
//    /**
//     * 处理WebSocket传输错误
//     *
//     * @param session   WebSocket会话
//     * @param exception 发生的异常
//     * @throws Exception 处理过程中可能抛出的异常
//     */
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//        log.error("[transport error] sessionId: {} , exception:{}", session.getId(), exception.getMessage());
//    }
//
//    /**
//     * 在WebSocket连接关闭后执行清理操作
//     *
//     * @param session WebSocket会话
//     * @param status  关闭状态信息
//     */
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
//        LoginUser loginUser = (LoginUser) session.getAttributes().get(LOGIN_USER_KEY);
//        if (ObjectUtil.isNull(loginUser)) {
//            log.info("[disconnect] invalid token received. sessionId: {}", session.getId());
//            return;
//        }
//        WebSocketSessionHolder.removeSession(loginUser.getUserId());
//        log.info("[disconnect] sessionId: {},userId:{},userType:{}", session.getId(), loginUser.getUserId(), loginUser.getUserType());
//    }
//
//    /**
//     * 指示处理程序是否支持接收部分消息
//     *
//     * @return 如果支持接收部分消息，则返回true；否则返回false
//     */
//    @Override
//    public boolean supportsPartialMessages() {
//        return false;
//    }
//
//}
