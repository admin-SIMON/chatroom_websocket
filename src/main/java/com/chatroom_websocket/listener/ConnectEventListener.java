package com.chatroom_websocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.util.Map;

/**
 * 连接事件监听器 SessionConnectEvent
 * 实现接口ApplicationListener<T></>
 * T 表示以下WebSocket监听器类型:
 * SessionConnectEvent 	建立连接事件
 * SessionDisconnectEvent 	断开连接事件
 * SessionSubscribeEvent 	订阅事件
 * SessionUnsubscribeEvent	取消订阅事件
 *
 * @Component spring会把该类纳入管理
 */
@Component
public class ConnectEventListener implements ApplicationListener<SessionConnectEvent> {
    /**
     * 连接事件触发逻辑方法
     *
     * @StompHeaderAccessor 简单消息传递协议中处理消息头基类
     * 通过该类获取消息类型(例如:发布订阅,[建立/断开]连接,会话ID等)
     */
    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        //得到消息头访问器
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        SimpMessageType messageType = headerAccessor.getCommand().getMessageType();//得到消息类型
        System.out.println("[ConnectEventListener监听器事件 类型]" + messageType);

        Map<String, Object> attributes = headerAccessor.getSessionAttributes();//得到拦截器存储的数据
        System.out.println("[ConnectEventListener监听器事件 sessionId]" + attributes.get("sessionId"));

    }
}
