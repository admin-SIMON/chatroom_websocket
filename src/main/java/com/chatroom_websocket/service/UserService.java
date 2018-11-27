package com.chatroom_websocket.service;

import com.chatroom_websocket.pojo.User;

import java.util.List;

public interface UserService {
    User getUserByName(User user);

    List<User> getUserList(User user);

}
