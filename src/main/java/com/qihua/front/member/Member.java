package com.qihua.front.member;

import org.springframework.data.annotation.Id;

import com.qihua.common.BaseEntity;
import com.qihua.common.repository.annotation.NotNull;

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
@EqualsAndHashCode(callSuper = false, exclude = {"score", "address", "email", "mobile", "memberName", "password"})
public class Member extends BaseEntity {

  private static final long serialVersionUID = -2680452026485974989L;

  @Id
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
