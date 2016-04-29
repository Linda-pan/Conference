package com.elin4it.ssm.pojo;

public class Paper {
    private Integer paperId;

    private Integer userId;

    private String paperName;

    private String paperContent;

    private Byte paperStatus;

    private Integer averageScore;

    private Boolean isEmailPost;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName == null ? null : paperName.trim();
    }

    public String getPaperContent() {
        return paperContent;
    }

    public void setPaperContent(String paperContent) {
        this.paperContent = paperContent == null ? null : paperContent.trim();
    }

    public Byte getPaperStatus() {
        return paperStatus;
    }

    public void setPaperStatus(Byte paperStatus) {
        this.paperStatus = paperStatus;
    }

    public Integer getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Integer averageScore) {
        this.averageScore = averageScore;
    }

    public Boolean getIsEmailPost() {
        return isEmailPost;
    }

    public void setIsEmailPost(Boolean isEmailPost) {
        this.isEmailPost = isEmailPost;
    }
}