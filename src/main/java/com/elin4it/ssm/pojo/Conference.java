package com.elin4it.ssm.pojo;

import java.util.Date;

public class Conference {
    private Integer conferenceId;

    private String conferenceName;

    private String conferenceIntro;

    private Date startTime;

    private Date endTime;

    public Integer getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName == null ? null : conferenceName.trim();
    }

    public String getConferenceIntro() {
        return conferenceIntro;
    }

    public void setConferenceIntro(String conferenceIntro) {
        this.conferenceIntro = conferenceIntro == null ? null : conferenceIntro.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}