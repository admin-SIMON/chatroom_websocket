package com.chatroom_websocket.controller;

import com.chatroom_websocket.pojo.Msgex;
import com.chatroom_websocket.pojo.User;
import com.chatroom_websocket.service.MsgexService;
import com.chatroom_websocket.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private MsgexService msgexService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/index.html")
    public String index() {
        return "index";
    }

    @RequestMapping("/")
    public ModelAndView Login(User user, ModelAndView modelAndView, HttpSession session) {
        modelAndView.setViewName("chat");
        User userByName = userService.getUserByName(user);
        if (null != userByName && userByName.getuPassword().equals(user.getuPassword())) {
            List<User> userList = userService.getUserList(userByName);
            Msgex msgex = new Msgex();
            msgex.setmStatus(1L);

            msgex.setmTo(userByName.getUid());
            List<User> unreadList = msgexService.getUnread(msgex);

//            msgex.setmTo(userByName.getUid());
//            List<Msgex> msg = msgexService.getMsg(msgex);
            HashMap<Long, Object> map = new HashMap<>();
            for (User u : unreadList) {
                map.put(u.getUid(), u.getUnread());
            }
            //view
            modelAndView.addObject("user", userByName);
            modelAndView.addObject("userList", userList);
            modelAndView.addObject("unreadList", map);
//            modelAndView.addObject("msgList", msg);
            //Redis
            stringRedisTemplate.opsForHash().put(session.getId(), "username", userByName.getuUsername());
            stringRedisTemplate.opsForHash().put(session.getId(), "password", userByName.getuPassword());
            stringRedisTemplate.opsForHash().put(session.getId(), "id", userByName.getUid().toString());
//            stringRedisTemplate.opsForValue().set(session.getId(), userByName.getUid().toString(), 1, TimeUnit.DAYS);
//            stringRedisTemplate.opsForValue().set(userByName.getUid().toString(), session.getId());
            stringRedisTemplate.opsForList().leftPush(userByName.getUid().toString(), session.getId());

        } else {
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

}
