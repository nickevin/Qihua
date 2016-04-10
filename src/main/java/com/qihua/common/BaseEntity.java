package com.qihua.common;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class description goes here.
 *
 * @author zhen.ni@ebaotech.com
 * @since 2013-10-18
 * @version 1.0
 *
 */
@Data
@EqualsAndHashCode(exclude = {"insertTime", "updateTime"})
public class BaseEntity implements Serializable {

  private int status = 1;
  private Timestamp insertTime;
  private Timestamp updateTime;

}
