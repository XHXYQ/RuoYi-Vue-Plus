package org.dromara.demo.controller;

import org.dromara.common.core.domain.R;
import org.dromara.common.websocket.dto.WebSocketMessageDto;
import org.dromara.common.websocket.utils.WebSocketUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * WebSocket 演示案例
 *
 * @author zendwang
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/demo/websocket")
@Slf4j
public class WeSocketController {

    /**
     * 发布消息
     *
     * @param dto 发送内容
     */
    @GetMapping("/send")
    public R<Void> send(WebSocketMessageDto dto) throws InterruptedException {
        WebSocketUtils.publishMessage(dto);
        return R.ok("操作成功");
    }
}

//package org.dromara.demo.controller;
//
//import cn.dev33.satoken.stp.StpUtil;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.dromara.common.core.domain.R;
//import org.dromara.common.websocket.dto.WebSocketMessageDto;
//import org.dromara.common.websocket.utils.WebSocketUtils;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/demo/websocket")
//@Slf4j
//public class WeSocketController {
//
//    /**
//     * 发布消息 - POST JSON方式
//     */
//    @PostMapping("/send")
//    public R<Void> send(@RequestBody WebSocketMessageDto dto) {
//        // 校验是否已登录（如你使用了拦截器的话，这一步可能不是必须）
//        StpUtil.checkLogin(); // 如有开启全局拦截则可不写
//
//        Long userId = StpUtil.getLoginIdAsLong();
//        log.info("当前发送者: {}", userId);
//        log.info("当前 token 的 loginType 是: {}", StpUtil.getLoginType());
//
//
//        WebSocketUtils.publishMessage(dto);
//        return R.ok("消息发送成功");
//    }
//}

