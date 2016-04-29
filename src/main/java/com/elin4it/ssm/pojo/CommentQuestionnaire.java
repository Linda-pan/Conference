package com.elin4it.ssm.pojo;

import java.util.Date;

public class CommentQuestionnaire {
    private Integer commentQId;

    private Integer themeId;

    private Date updateTime;

    private Integer adminId;

    private String q1;

    private String q2;

    private String q3;

    private String q4;

    private String q5;

    public Integer getCommentQId() {
        return commentQId;
    }

    public void setCommentQId(Integer commentQId) {
        this.commentQId = commentQId;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
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

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1 == null ? null : q1.trim();
    }

    public String getQ2() {
        return q2;
    }

    public void setQ2(String q2) {
        this.q2 = q2 == null ? null : q2.trim();
    }

    public String getQ3() {
        return q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3 == null ? null : q3.trim();
    }

    public String getQ4() {
        return q4;
    }

    public void setQ4(String q4) {
        this.q4 = q4 == null ? null : q4.trim();
    }

    public String getQ5() {
        return q5;
    }

    public void setQ5(String q5) {
        this.q5 = q5 == null ? null : q5.trim();
    }
}