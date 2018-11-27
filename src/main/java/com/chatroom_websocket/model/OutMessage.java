package com.chatroom_websocket.model;

import java.util.Date;

public class OutMessage {

    private String from;

    private String content;

    private Date time = new Date();

    private String fromName;

    private String to;

    public OutMessage(String from, String content, Date time, String fromName, String to) {
        this.from = from;
        this.content = content;
        this.time = time;
        this.fromName = fromName;
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public OutMessage(String from, String content, Date time, String fromName) {
        this.from = from;
        this.content = content;
        this.time = time;
        this.fromName = fromName;
    }

    public OutMessage() {
    }

    public OutMessage(String content) {
        this.content = content;
    }

    public OutMessage(String from, String content) {
        this.from = from;
        this.content = content;
    }

    public OutMessage(String from, String content, Date time) {
        this.from = from;
        this.content = content;
        this.time = time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
}
