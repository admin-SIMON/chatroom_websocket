package com.chatroom_websocket.service;

import com.chatroom_websocket.pojo.Msgex;
import com.chatroom_websocket.pojo.User;

import java.util.List;

public interface MsgexService {
    List<Msgex> getMsgexList(Msgex msgex);

    Integer saveMsgex(Msgex msgex);

    List<User> getUnread(Msgex msgex);

}
