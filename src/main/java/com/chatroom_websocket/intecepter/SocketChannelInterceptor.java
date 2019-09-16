package com.chatroom_websocket.intecepter;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 频道拦截器(类似管道)
 * 可以获取消息的原数据
 */
public class SocketChannelInterceptor extends ChannelInterceptorAdapter {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private void connect() {
        System.out.println("[频道拦截器 建立连接]");
    }

    private void disconnect(String sessionId) {
        System.out.println("[频道拦截器 断开连接]");
        //TODO 用户下线操作
        HashOperations<String, Object, Object> ops = stringRedisTemplate.opsForHash();
        if (null != ops) {
            String id = ops.get(sessionId, "id").toString();
            List<String> range = stringRedisTemplate.opsForList().range(id, 0, -1);
            boolean del = false;
            for (String list : range) {
                if (!del) {
                    if (list.equals(sessionId)) {
                        del = true;
                    }
                } else {
                    if (list.equals(sessionId)) {
                        del = false;
                        break;
                    }
                }
            }
            stringRedisTemplate.opsForList().remove(id, 1, sessionId);
            if (del) {
                stringRedisTemplate.delete(sessionId);
            }
        }
//        stringRedisTemplate.delete(id);
    }

    private void subscribe() {
        System.out.println("[频道拦截器 添加订阅]");
    }

    private void unsubscribe() {
        System.out.println("[频道拦截器 取消订阅]");
    }

    /**
     * 发送消息调用后立即调用
     */
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        System.out.println("[频道拦截器 SocketChannelInterceptor->postSend]");
        //消息头访问器
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

        if (null == headerAccessor.getCommand()) {
            return;//过滤非stomp消息类型,例如心跳检测
        }

        Map<String, Object> attributes = headerAccessor.getSessionAttributes();

        String sessionId = attributes.get("sessionId").toString();

        System.out.println("[频道拦截器 SocketChannelInterceptor->postSend] sessionId : " + sessionId);

        switch (headerAccessor.getCommand()) {
            case CONNECT:
                connect();
                break;
            case DISCONNECT:
                disconnect(sessionId);
                break;
            case SUBSCRIBE:
                subscribe();
                break;
            case UNSUBSCRIBE:
                unsubscribe();
                break;
            default:
                break;
        }

    }

    /**
     * 消息发送到频道前进行调用
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        System.out.println("[SocketChannelInterceptor->preSend]");
        return super.preSend(message, channel);
    }

    /**
     * 完成发送后进行调用,不管是否有异常发生(一般用于资源清理)
     */
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        System.out.println("[SocketChannelInterceptor->afterSendCompletion]");
        super.afterSendCompletion(message, channel, sent, ex);
    }

}
