package com.chatroom_websocket.mapper;

import com.chatroom_websocket.pojo.Msgex;
import com.chatroom_websocket.pojo.User;

import java.util.List;

public interface MsgexMapper {
    List<Msgex> getMsgexList(Msgex msgex);

    Integer saveMsgex(Msgex msgex);

    List<User> getUnread(Msgex msgex);

    Integer setMsg(Msgex msgex);

}
