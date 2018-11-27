package com.chatroom_websocket.model;

import java.util.Date;

public class InMessage {
    //发送者
    private String from;
    //接收者
    private String to;
    //消息内容
    private String content;
    //发送时间
    private Date time;

    public InMessage() {
    }

    public InMessage(String content) {
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
