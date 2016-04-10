package com.qihua.console.order;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.qihua.common.BaseEntity;

/**
 * Class description goes here.
 *
 * @author aopfilter@163.com
 * @since Jan 21, 2015 2:06:15 PM
 * @version 1.0
 * @see
 */
public class Order extends BaseEntity {

  @Id
  private String orderId;
  private String orderNo;
  private String memberId;
  private String recipient;
  private String mobile;
  private String email;
  private String zip;
  private String address;
  private String remarks;
  private List<OrderItem> items;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(final String orderId) {
    this.orderId = orderId;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(final String orderNo) {
    this.orderNo = orderNo;
  }

  public String getMemberId() {
    return memberId;
  }

  public void setMemberId(final String memberId) {
    this.memberId = memberId;
  }

  public String getRecipient() {
    return recipient;
  }

  public void setRecipient(final String recipient) {
    this.recipient = recipient;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(final String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(final String zip) {
    this.zip = zip;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(final String remarks) {
    this.remarks = remarks;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public void setItems(final List<OrderItem> items) {
    this.items = items;
  }

}
