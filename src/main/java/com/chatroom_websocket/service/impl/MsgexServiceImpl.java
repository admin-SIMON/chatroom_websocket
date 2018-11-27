package com.chatroom_websocket.service.impl;

import com.chatroom_websocket.mapper.MsgexMapper;
import com.chatroom_websocket.pojo.Msgex;
import com.chatroom_websocket.pojo.User;
import com.chatroom_websocket.service.MsgexService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MsgexServiceImpl implements MsgexService {
    @Resource
    private MsgexMapper msgexMapper;

    @Override
    @Transactional
    public List<Msgex> getMsgexList(Msgex msgex) {
        List<Msgex> msgexList = msgexMapper.getMsgexList(msgex);
        msgex.setmStatus(0L);
        msgexMapper.setMsg(msgex);
        return msgexList;
    }

    @Override
    public List<User> getUnread(Msgex msgex) {
        return msgexMapper.getUnread(msgex);
    }

    @Override
    public Integer saveMsgex(Msgex msgex) {
        return msgexMapper.saveMsgex(msgex);
    }
}
