package com.elin4it.ssm.pojo;

public class ConferenceStatus extends ConferenceStatusKey {
    private Integer adminId;

    private Byte conferenceStatus;

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