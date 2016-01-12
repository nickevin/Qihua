package com.qihua.front.product;

import com.qihua.common.BaseEntity;
import com.qihua.common.Constants;
import com.qihua.common.annotation.PrimaryKey;

import lombok.Data;

@Data
public class ProductImg extends BaseEntity {

  @PrimaryKey
  private String name;
  private long size;
  private String extension;
  private String productId;
  private String postion;

  public String getImgUrl() {
    return Constants.CONTEXT_URL + Constants.PRODUCT_IMG_URL + getName() + "." + getExtension();
  }

}
