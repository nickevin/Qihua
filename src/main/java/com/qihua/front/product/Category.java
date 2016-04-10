package com.qihua.front.product;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.qihua.common.BaseEntity;

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

  @Id
  private Long categoryId;
  private String categoryName = "";
  private Long subcategoryId;
  private String subcategoryName = "";
  private List<Category> subcategories;

}
