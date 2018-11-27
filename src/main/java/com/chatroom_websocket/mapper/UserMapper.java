package com.chatroom_websocket.mapper;

import com.chatroom_websocket.pojo.User;

import java.util.List;

public interface UserMapper {
    User getUserByName(User user);

    List<User> getUserList(User user);
}
