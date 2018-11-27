package com.chatroom_websocket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan("com.chatroom_websocket")       //加载@Service @Control注解类
@MapperScan(value = "com.chatroom_websocket.mapper")  //mybatis 需要扫描mapper接口
@EnableTransactionManagement    //启用事务管理
@EnableScheduling
@EnableWebMvc                   //启用mvc
public class ChatroomWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatroomWebsocketApplication.class, args);
    }
}
