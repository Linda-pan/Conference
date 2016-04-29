package com.elin4it.ssm.pojo;

public class Comment extends CommentKey {
    private Byte q1;

    private Byte q2;

    private Byte q3;

    private Byte q4;

    private Byte q5;

    private String shortComment;

    private Byte score;

    public Byte getQ1() {
        return q1;
    }

    public void setQ1(Byte q1) {
        this.q1 = q1;
    }

    public Byte getQ2() {
        return q2;
    }

    public void setQ2(Byte q2) {
        this.q2 = q2;
    }

    public Byte getQ3() {
        return q3;
    }

    public void setQ3(Byte q3) {
        this.q3 = q3;
    }

    public Byte getQ4() {
        return q4;
    }

    public void setQ4(Byte q4) {
        this.q4 = q4;
    }

    public Byte getQ5() {
        return q5;
    }

    public void setQ5(Byte q5) {
        this.q5 = q5;
    }

    public String getShortComment() {
        return shortComment;
    }

    public void setShortComment(String shortComment) {
        this.shortComment = shortComment == null ? null : shortComment.trim();
    }

    public Byte getScore() {
        return score;
    }

    public void setScore(Byte score) {
        this.score = score;
    }
}