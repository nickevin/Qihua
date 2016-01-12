package com.qihua.front.product;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.qihua.common.BaseEntity;
import com.qihua.common.annotation.GeneratedValue;
import com.qihua.common.annotation.PrimaryKey;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class description goes here.
 *
 * @author aopfilter@163.com
 * @since Jan 21, 2015 2:06:15 PM
 * @version 1.0
 * @see
 */
@Data
@EqualsAndHashCode(exclude = {"category", "images", "recommendImages", "flashSaleImages"})
public class Product extends BaseEntity {

  @PrimaryKey
  @GeneratedValue(generator = "uuid")
  private String productId;
  private String productCode = "";
  private String productName = "";
  private Long categoryId;
  private double price;
  private long score;
  private Date onSaleDate;
  private int countdown;
  private String description;
  private Category category;
  private List<ProductImg> images = new ArrayList<ProductImg>();
  private List<ProductImg> recommendImages = new ArrayList<ProductImg>();
  private List<ProductImg> flashSaleImages = new ArrayList<ProductImg>();

  public ProductImg getImage() {
    return images.size() == 0 ? new ProductImg() : images.get(0);
  }

  public ProductImg getFlashSaleImage() {
    return flashSaleImages.size() == 0 ? new ProductImg() : flashSaleImages.get(0);
  }


}
