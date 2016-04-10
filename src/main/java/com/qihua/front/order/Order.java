package com.qihua.front.order;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.qihua.common.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Class description goes here.
 *
 * @author aopfilter@163.com
 * @since Jan 21, 2015 2:06:15 PM
 * @version 1.0
 * @see
 */
@Data
@EqualsAndHashCode(exclude = {"memberId", "recipient", "mobile", "email", "zip", "address", "remarks", "items"})
@ToString(callSuper = true, exclude = {"items"})
public class Order extends BaseEntity {

  @Id
  private String orderId;
  private String orderNo;
  private String memberId;
  private String recipient = "";
  private String mobile = "";
  private String email = "";
  private String zip = "";
  private String address = "";
  private String remarks = "";
  private List<OrderItem> items;

}
