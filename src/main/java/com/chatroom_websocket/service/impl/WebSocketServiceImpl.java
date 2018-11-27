package com.chatroom_websocket.service.impl;

import com.chatroom_websocket.controller.UserController;
import com.chatroom_websocket.model.InMessage;
import com.chatroom_websocket.model.OutMessage;
import com.chatroom_websocket.pojo.User;
import com.chatroom_websocket.service.WebSocketService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WebSocketServiceImpl implements WebSocketService {
    @Resource
    private SimpMessagingTemplate template;

    @Override
    public void sendAssignMessage(String dest, InMessage message, Integer count, Long millis) {
        if (null == count) count = 1;

        for (int i = 0; i < count; i++) {
            try {
                Thread.sleep(millis);//毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            template.convertAndSend(dest, new OutMessage(message.getContent()));
        }
    }

    @Override
    public void sendServerInfo() {
        int processors = Runtime.getRuntime().availableProcessors();//处理器(核数)
        long freeMemory = Runtime.getRuntime().freeMemory();//可用JVM内存数
        long maxMemory = Runtime.getRuntime().maxMemory();//总JVM内存数
        String format = String.format("当前为 %d 核处理器 ; 虚拟机最大内存 : %d ; 虚拟机空闲内存 : %d", processors, maxMemory, freeMemory);

        template.convertAndSend("/topic/server_info", new OutMessage(format));
    }

    @Override
    public void sendChatMessage(OutMessage message) {
        template.convertAndSend("/chat/single/" + message.getTo(), message);
    }

    @Override
    public void sendUnread(List<User> users, String to) {
        try {
            Thread.sleep(4000);//毫秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        template.convertAndSend("/chat/unread/" + to, users);
    }


}
