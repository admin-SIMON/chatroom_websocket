package com.chatroom_websocket.config;

import com.chatroom_websocket.intecepter.HttpHandShackIntecepter;
import com.chatroom_websocket.intecepter.SocketChannelInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * 注册端点
     * 发布或订阅消息需要连接此端点
     * <p>
     * addInterceptors() 新增一个拦截器 HttpHandShackIntecepter()
     * <p>
     * setAllowedOrigins() 非必须,设置允许访问端点的域名: * 表示允许其他域进行连接
     * <p>
     * withSockJS() 表示开始 SockJS 支持
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/endpoint-websocket").addInterceptors(new HttpHandShackIntecepter()).setAllowedOrigins("*").withSockJS();
    }

    /**
     * 配置消息代理(中介)
     * enableSimpleBroker 服务端推送给客户端的路径前缀
     * setApplicationDestinationPrefixes 客户端发送数据给服务器端的路径前缀
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/chat");
        registry.setApplicationDestinationPrefixes("/app");
    }

    /**
     * 避免拦截器获取不到Redis操作对象(StringRedisTemplate)
     */
    @Bean
    public SocketChannelInterceptor getSocketChannelInterceptor() {
        return new SocketChannelInterceptor();
    }

    /**
     * 配置客户端进站通道拦截器
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(getSocketChannelInterceptor());
    }

    /**
     * 配置客户端出站通道拦截器
     */
    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.interceptors(getSocketChannelInterceptor());
    }
}
