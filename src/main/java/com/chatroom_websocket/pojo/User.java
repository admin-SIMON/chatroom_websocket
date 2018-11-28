package com.chatroom_websocket.pojo;


public class User {

    private Long uid;
    private String uUsername;
    private String uPassword;
    private String uImage_Url;

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

    public String getuImage_Url() {
        return uImage_Url;
    }

    public void setuImage_Url(String uImage_Url) {
        this.uImage_Url = uImage_Url;
    }

    public Integer getUnread() {
        return unread;
    }

    public void setUnread(Integer unread) {
        this.unread = unread;
    }
}
