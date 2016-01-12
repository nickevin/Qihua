package com.qihua.console.user;

import com.qihua.console.QueryParameter;

/**
 * Class description goes here.
 * 
 * @author zhen.ni@ebaotech.com
 * @since 2013-10-17
 * @version 1.0
 * @see
 */
public class UserQueryParameter extends QueryParameter {

    private String userId;
    private String username = "";
    private String password = "";
    private String realName = "";
    private String mobile = "";
    private String email = "";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserQueryParameter [userId=" + userId + ", username=" + username + ", password=" + password + ", realName=" + realName + ", mobile=" + mobile + ", email=" + email + "]";
    }

}
