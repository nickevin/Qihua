package com.qihua.console.security;

import java.io.Serializable;

import lombok.Data;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since 2013-10-11
 * @version 1.0
 * @see
 */
@Data
public class RoleLevel implements Serializable {

  private static final long serialVersionUID = 1L;

  private long roleId;
  private String roleName = "";
  private long roleLevelId;
  private String roleLevelName = "";

  public RoleLevel() {}

  public RoleLevel(long roleLevelId, String roleLevelName) {
    this.roleLevelId = roleLevelId;
    this.roleLevelName = roleLevelName;
  }

}
