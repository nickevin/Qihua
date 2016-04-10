package com.qihua.front.product;

import org.springframework.data.annotation.Id;

import com.qihua.common.BaseEntity;
import com.qihua.common.Constants;

import lombok.Data;

@Data
public class ProductImg extends BaseEntity {

  @Id
  private String name;
  private long size;
  private String extension;
  private String productId;
  private String postion;

  public String getImgUrl() {
    return Constants.CONTEXT_URL + Constants.PRODUCT_IMG_URL + getName() + "." + getExtension();
  }

}
