package com.qihua.console.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qihua.common.BaseEntity;
import com.qihua.common.Constants;
import com.qihua.common.annotation.PrimaryKey;

import lombok.Data;

/**
 * 产品图片
 *
 * @since 2014-3-20
 * @author zhen.ni
 * @email: aopfilter@163.com
 */
@Data
@JsonIgnoreProperties({"productId"})
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
