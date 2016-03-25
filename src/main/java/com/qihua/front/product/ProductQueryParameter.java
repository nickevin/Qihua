package com.qihua.front.product;

import com.qihua.console.QueryParameter;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class description goes here.
 *
 * @author zhen.ni@ebaotech.com
 * @since 2013-10-17
 * @version 1.0
 * @see
 */
@Data
@EqualsAndHashCode()
public class ProductQueryParameter extends QueryParameter {

  private String productName = "";
  private String lowScore = "";
  private String highScore = "";
  private String categoryId = "";
  private String order = "";

  @Override
  public String toString() {
    return "ProductQueryParameter [productName=" + productName + ", lowScore=" + lowScore + ", highScore=" + highScore
        + ", categoryId=" + categoryId + ", order=" + order + "]";
  }

}
