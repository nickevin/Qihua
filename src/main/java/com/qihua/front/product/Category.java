package com.qihua.front.product;

import java.util.List;

import com.qihua.common.BaseEntity;
import com.qihua.common.annotation.PrimaryKey;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class description goes here.
 *
 * @author aopfilter@163.com
 * @since Jan 21, 2015 2:06:15 PM
 * @version 1.0
 *
 */
@Data
@EqualsAndHashCode(exclude = {"subcategories"})
public class Category extends BaseEntity {

  @PrimaryKey
  private Long categoryId;
  private String categoryName = "";
  private Long subcategoryId;
  private String subcategoryName = "";
  private List<Category> subcategories;



}
