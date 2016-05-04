package com.elin4it.ssm.pojo;

import java.util.Date;

public class ConferenceStatus {
    private Integer id;

    private Integer conferenceId;

    private Date updateTime;

    private Integer adminId;

    private Byte conferenceStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Byte getConferenceStatus() {
        return conferenceStatus;
    }

    public void setConferenceStatus(Byte conferenceStatus) {
        this.conferenceStatus = conferenceStatus;
    }
}