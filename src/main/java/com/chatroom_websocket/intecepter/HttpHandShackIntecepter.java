package com.chatroom_websocket.intecepter;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * HTTP握手拦截器
 * 可以通过这个类获取request和response
 */
public class HttpHandShackIntecepter implements HandshakeInterceptor {
    /**
     * 握手前拦截器
     * 返回值为false 则拦截,true 则通过
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("[握手拦截器 beforeHandshake]");

        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverHRequest = (ServletServerHttpRequest) request;
            HttpSession session = serverHRequest.getServletRequest().getSession();
            String sessionId = session.getId();
            System.out.println("[握手拦截器 beforeHandshake] sessionId : " + sessionId);
            attributes.put("sessionId", sessionId);
        }

        return true;
    }

    /**
     * 握手后拦截器
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        System.out.println("[握手拦截器 afterHandshake]");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverHRequest = (ServletServerHttpRequest) request;
            HttpSession session = serverHRequest.getServletRequest().getSession();
            String sessionId = session.getId();
            System.out.println("[握手拦截器 afterHandshake] sessionId : " + sessionId);
        }
    }
}
