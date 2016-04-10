package com.qihua.front.cart;

import org.springframework.data.annotation.Id;

import com.qihua.common.BaseEntity;
import com.qihua.common.Constants;

import lombok.Data;

/**
 * Class description goes here.
 *
 * @author aopfilter@163.com
 * @since Jan 21, 2015 2:06:15 PM
 * @version 1.0
 * @see
 */
@Data
public class Cart extends BaseEntity {

  @Id
  private String cartId;
  private String memberId;
  private String productId;
  private String productName;
  private String productImgName;
  private long score;
  private int quantity;

  public String getProductImgUrl() {
    return Constants.CONTEXT_URL + Constants.PRODUCT_IMG_URL + getProductImgName();
  }


}
