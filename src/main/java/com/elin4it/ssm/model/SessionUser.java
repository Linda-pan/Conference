package com.elin4it.ssm.model;

/**
 * Created by jpan on 2016/4/28.
 */
public class SessionUser {
    private int userId;
    private String username;

    public SessionUser(int userId,String username){
        this.userId=userId;
        this.username=username;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

}
