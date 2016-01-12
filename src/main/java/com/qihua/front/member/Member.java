package com.qihua.front.member;

import com.qihua.common.BaseEntity;
import com.qihua.common.annotation.GeneratedValue;
import com.qihua.common.annotation.NotNull;
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
@EqualsAndHashCode
public class Member extends BaseEntity {

  @PrimaryKey
  @GeneratedValue(generator = "uuid")
  private String memberId;
  private String memberName = "";
  @NotNull
  private String password;
  @NotNull
  private String mobile = "";
  private String email = "";
  private String address = "";
  private long score;

}
