package com.chatroom_websocket.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Msgex {

    private Long mId;
    private Long mFrom;
    private Long mTo;
    private String mContent;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date mTime;
    private Long mStatus;

    private String uUsername;

    public String getuUsername() {
        return uUsername;
    }

    public void setuUsername(String uUsername) {
        this.uUsername = uUsername;
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public Long getmFrom() {
        return mFrom;
    }

    public void setmFrom(Long mFrom) {
        this.mFrom = mFrom;
    }

    public Long getmTo() {
        return mTo;
    }

    public void setmTo(Long mTo) {
        this.mTo = mTo;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    public Long getmStatus() {
        return mStatus;
    }

    public void setmStatus(Long mStatus) {
        this.mStatus = mStatus;
    }
}
