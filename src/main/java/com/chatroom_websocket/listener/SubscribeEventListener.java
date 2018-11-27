package com.chatroom_websocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

/**
 * 订阅事件监听器 SessionSubscribeEvent
 *
 * @Component spring会把该类纳入管理
 */
@Component
public class SubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {
    /**
     * 订阅事件触发逻辑方法
     */
    @Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        //得到消息头访问器
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        SimpMessageType messageType = headerAccessor.getCommand().getMessageType();//得到消息类型
        System.out.println("[SubscribeEventListener监听器事件 类型]" + messageType);
    }
}
