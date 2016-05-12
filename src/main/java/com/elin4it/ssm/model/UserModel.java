package com.elin4it.ssm.model;

import com.elin4it.ssm.pojo.User;

import java.util.Date;

/**
 * Created by jpan on 2016/5/12.
 */
public class UserModel extends User {
    String validateCode;
    Date registerTime;

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
