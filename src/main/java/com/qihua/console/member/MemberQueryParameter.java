package com.qihua.console.member;

import com.qihua.console.QueryParameter;

/**
 * Class description goes here.
 * 
 * @author zhen.ni@ebaotech.com
 * @since 2013-10-17
 * @version 1.0
 * @see
 */
public class MemberQueryParameter extends QueryParameter {

    private String memberId;
    private String memberName = "";
    private String password = "";
    private String mobile = "";
    private String email = "";

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "MemberQueryParameter [memberId=" + memberId + ", memberName=" + memberName + ", password=" + password + ", mobile=" + mobile + ", email=" + email + "]";
    }

}
