package com.qihua.front.article;

import com.qihua.common.BaseEntity;
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
public class Article extends BaseEntity {

  @PrimaryKey
  private Long articleId;
  @NotNull
  private int articleType;
  private String content;

}
