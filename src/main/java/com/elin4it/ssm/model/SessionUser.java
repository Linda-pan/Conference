package com.elin4it.ssm.model;

/**
 * Created by jpan on 2016/4/28.
 */
public class SessionUser {
    private int userId;
    private String username;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    private int roleId;

    public SessionUser(int userId,String username,int roleId){
        this.userId=userId;
        this.username=username;
        this.roleId =roleId;
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
