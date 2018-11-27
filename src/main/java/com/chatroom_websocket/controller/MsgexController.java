package com.chatroom_websocket.controller;

import com.chatroom_websocket.model.InMessage;
import com.chatroom_websocket.model.OutMessage;
import com.chatroom_websocket.pojo.Msgex;
import com.chatroom_websocket.service.MsgexService;
import com.chatroom_websocket.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MsgexController {
    @Autowired
    private WebSocketService webSocketService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource
    private MsgexService msgexService;

    /**
     * 定时给客户端推送系统信息
     * <p>
     * Scheduled 标记的注解的方法不能有参数,不然会报错
     */
    @Scheduled(fixedRate = 1000)//1秒自动推送(fixedRate/毫秒单位)
    public void onServerInfo() {
        webSocketService.sendServerInfo();
    }

    /**
     * 聊天接口
     *
     * @param message        消息体
     * @param headerAccessor 消息头访问器
     */
    @MessageMapping("/chat")
    public void topicChat(InMessage message, SimpMessageHeaderAccessor headerAccessor) {
        Msgex msgex = new Msgex();
        msgex.setmFrom(Long.valueOf(message.getFrom()));
        msgex.setmTo(Long.valueOf(message.getTo()));
        msgex.setmContent(message.getContent());
        msgex.setmStatus(0L);

        Map<String, Object> attributes = headerAccessor.getSessionAttributes();
        String sessionId = attributes.get("sessionId").toString();
        if (0 == redisTemplate.opsForList().size(message.getTo())) {
            msgex.setmStatus(1L);
        }
        OutMessage outMessage = new OutMessage(message.getFrom(), message.getContent()
                , null == message.getTime() ? new Date() : message.getTime()
                , redisTemplate.opsForHash().get(sessionId, "username").toString(), message.getTo());
        msgexService.saveMsgex(msgex);
        webSocketService.sendChatMessage(outMessage);

    }

    @RequestMapping("/getMsgexList")
    @ResponseBody
    public Object getMsgexList(Msgex msgex) {
        List<Msgex> msgexList = msgexService.getMsgexList(msgex);
        return msgexList;
    }
}
