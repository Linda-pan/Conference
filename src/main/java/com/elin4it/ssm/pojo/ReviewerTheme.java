package com.elin4it.ssm.pojo;

public class ReviewerTheme {
    private Integer userid;

    private Integer theme1;

    private Integer theme2;

    private Integer theme3;

    private Integer theme4;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTheme1() {
        return theme1;
    }

    public void setTheme1(Integer theme1) {
        this.theme1 = theme1;
    }

    public Integer getTheme2() {
        return theme2;
    }

    public void setTheme2(Integer theme2) {
        this.theme2 = theme2;
    }

    public Integer getTheme3() {
        return theme3;
    }

    public void setTheme3(Integer theme3) {
        this.theme3 = theme3;
    }

    public Integer getTheme4() {
        return theme4;
    }

    public void setTheme4(Integer theme4) {
        this.theme4 = theme4;
    }

    @Override
    public String toString() {
        return "ReviewerTheme{" +
                "userid=" + userid +
                ", theme1=" + theme1 +
                ", theme2=" + theme2 +
                ", theme3=" + theme3 +
                ", theme4=" + theme4 +
                '}';
    }
}