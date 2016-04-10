package com.qihua.console.article;

import org.springframework.data.annotation.Id;

import com.qihua.common.BaseEntity;
import com.qihua.common.repository.annotation.NotNull;

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

  @Id
  private Long articleId;
  @NotNull
  private int articleType;
  private String content;

}
