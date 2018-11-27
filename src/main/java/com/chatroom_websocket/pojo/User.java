package com.chatroom_websocket.pojo;


public class User {

    private Long uid;
    private String uUsername;
    private String uPassword;
    private String uImageUrl;

    private Integer unread;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getuUsername() {
        return uUsername;
    }

    public void setuUsername(String uUsername) {
        this.uUsername = uUsername;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuImageUrl() {
        return uImageUrl;
    }

    public void setuImageUrl(String uImageUrl) {
        this.uImageUrl = uImageUrl;
    }

    public Integer getUnread() {
        return unread;
    }

    public void setUnread(Integer unread) {
        this.unread = unread;
    }
}
