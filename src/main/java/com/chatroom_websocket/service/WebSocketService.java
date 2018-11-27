package com.chatroom_websocket.service;

import com.chatroom_websocket.model.InMessage;
import com.chatroom_websocket.model.OutMessage;
import com.chatroom_websocket.pojo.User;

import java.util.List;

public interface WebSocketService {
    /**
     * 指定路径推送消息(公告推送)
     */
    void sendAssignMessage(String dest, InMessage message, Integer count, Long millis);

    /**
     * 点对点推送
     */
    void sendChatMessage(OutMessage message);

    /**
     * 系统信息推送
     */
    void sendServerInfo();

    /**
     * 当前在线用户信息推送
     */
    void sendUnread(List<User> users, String to);

}
