package com.elin4it.ssm.pojo;

import java.util.Date;

public class ConferenceStatusKey {
    private Integer conferenceId;

    private Date updateTime;

    public Integer getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}