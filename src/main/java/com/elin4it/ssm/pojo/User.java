package com.elin4it.ssm.pojo;

public class User {
    private Integer userId;

    private Integer adminId;

    private String trueName;

    private Boolean isShowName;

    private String telephone;

    private String email;

    private Boolean isEmailConfirmed;

    private String username;

    private Byte roleId;

    private String account;

    private String password;

    private String paymentVoucher;

    private Boolean isPaymentConfirmed;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public Boolean getIsShowName() {
        return isShowName;
    }

    public void setIsShowName(Boolean isShowName) {
        this.isShowName = isShowName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Boolean getIsEmailConfirmed() {
        return isEmailConfirmed;
    }

    public void setIsEmailConfirmed(Boolean isEmailConfirmed) {
        this.isEmailConfirmed = isEmailConfirmed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Byte getRoleId() {
        return roleId;
    }

    public void setRoleId(Byte roleId) {
        this.roleId = roleId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPaymentVoucher() {
        return paymentVoucher;
    }

    public void setPaymentVoucher(String paymentVoucher) {
        this.paymentVoucher = paymentVoucher == null ? null : paymentVoucher.trim();
    }

    public Boolean getIsPaymentConfirmed() {
        return isPaymentConfirmed;
    }

    public void setIsPaymentConfirmed(Boolean isPaymentConfirmed) {
        this.isPaymentConfirmed = isPaymentConfirmed;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", adminId=" + adminId +
                ", trueName='" + trueName + '\'' +
                ", isShowName=" + isShowName +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", isEmailConfirmed=" + isEmailConfirmed +
                ", username='" + username + '\'' +
                ", roleId=" + roleId +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", paymentVoucher='" + paymentVoucher + '\'' +
                ", isPaymentConfirmed=" + isPaymentConfirmed +
                '}';
    }
}