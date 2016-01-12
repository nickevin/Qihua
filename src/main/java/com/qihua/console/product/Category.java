package com.qihua.console.product;

import com.qihua.common.BaseEntity;
import com.qihua.common.annotation.NotNull;
import com.qihua.common.annotation.PrimaryKey;
import com.qihua.common.annotation.Table;

import lombok.Data;

/**
 * Class description goes here.
 *
 * @author aopfilter@163.com
 * @since Jan 21, 2015 2:06:15 PM
 * @version 1.0
 *
 */
@Data
@Table(name = "t_product_category")
public class Category extends BaseEntity {

  @PrimaryKey
  private Long categoryId;
  @NotNull
  private String categoryName = "";
  @NotNull
  private Long subcategoryId;
  @NotNull
  private String subcategoryName = "";

}
