package com.qihua.console.product;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.qihua.common.BaseEntity;
import com.qihua.common.annotation.ExcludeColumn;
import com.qihua.common.annotation.GeneratedValue;
import com.qihua.common.annotation.NotNull;
import com.qihua.common.annotation.PrimaryKey;

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
public class Product extends BaseEntity {

  @PrimaryKey
  @GeneratedValue(generator = "uuid")
  private String productId;
  @NotNull
  private String productCode = "";
  @NotNull
  private String productName = "";
  private double price;
  @NotNull
  private long score;
  private int rate = 3;
  private int isRecommend = 1; // 由于 Bootstrap Switch 开关关时不传值，所以默认为关闭。
  private int isFlashSale = 1;
  private Date onSaleDate = new Date(System.currentTimeMillis());
  private int countdown;
  @ExcludeColumn
  private String categoryName;
  @NotNull
  private Long categoryId;
  @ExcludeColumn
  private Long subcategoryId;
  @ExcludeColumn
  private String subcategoryName;
  private String description = "";
  private List<ProductImg> images = new ArrayList<ProductImg>();
  private List<ProductImg> recommendImages = new ArrayList<ProductImg>();
  private List<ProductImg> flashSaleImages = new ArrayList<ProductImg>();

}
