package com.qihua.console.order;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import com.qihua.console.QueryParameter;

/**
 * Class description goes here.
 *
 * @author zhen.ni@ebaotech.com
 * @since 2013-10-17
 * @version 1.0
 * @see
 */
public class OrderQueryParameter extends QueryParameter {

    private String orderNo = "";
    private String recipient = "";
    private String mobile = "";
    private String email = "";
    private String address = "";
    private String remarks = "";
    private String startDate = new DateTime().withDayOfWeek(DateTimeConstants.MONDAY).toString("yyyy-MM-dd"); // 默认：本周一
    private String endDate = new DateTime().withDayOfWeek(DateTimeConstants.SUNDAY).toString("yyyy-MM-dd"); // 默认：本周日
    private String rangeDate = startDate + " - " + endDate;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRangeDate() {
        return rangeDate;
    }

    public void setRangeDate(String rangeDate) {
        this.rangeDate = rangeDate;
    }

    @Override
    public String toString() {
        return "OrderQueryParameter [orderNo=" + orderNo + ", mobile=" + mobile + ", email=" + email + ", address="
                + address + ", remarks=" + remarks + ", startDate=" + startDate + ", endDate=" + endDate
                + ", rangeDate=" + rangeDate + "]";
    }

}
