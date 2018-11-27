package com.chatroom_websocket.service.impl;

import com.chatroom_websocket.mapper.UserMapper;
import com.chatroom_websocket.pojo.User;
import com.chatroom_websocket.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getUserList(User user) {
        return userMapper.getUserList(user);
    }

    @Override
    public User getUserByName(User user) {
        return userMapper.getUserByName(user);
    }
}
