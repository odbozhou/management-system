package com.management.model;

import org.thymeleaf.util.DateUtils;

import java.util.Date;

public class User {
    private Integer userId;

    private String loginName;

    private String userName;

    private String passwd;

    private String role;

    private Integer status;

    private Integer deleteStatus;

    private Date createTime;

    private Date updateTime;

    public User() {

    }

    public User(Integer userId, String loginName, String userName, String passwd, String role, Integer status, Integer deleteStatus, Date createTime, Date updateTime) {
        this.userId = userId;
        this.loginName = loginName;
        this.userName = userName;
        this.passwd = passwd;
        this.role = role;
        this.status = status;
        this.deleteStatus = deleteStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User(String loginName, String passwd, String role) {
        this.loginName = loginName;
        this.userName = loginName;
        this.passwd = passwd;
        this.role = role;
        this.createTime = DateUtils.createNow().getTime();
        this.updateTime = DateUtils.createNow().getTime();
        this.status = 1;
        this.deleteStatus = 1;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}