package com.qihua.console.user;

import org.springframework.data.annotation.Id;

import com.qihua.common.BaseEntity;
import com.qihua.common.repository.annotation.ExcludeColumn;
import com.qihua.common.repository.annotation.NotNull;
import com.qihua.console.security.Role;

import lombok.Data;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since Jan 21, 2015 2:06:15 PM
 * @version 1.0
 * 
 */
@Data
public class User extends BaseEntity {

  @Id
  private String userId;
  @NotNull
  private String username = "";
  @NotNull
  private String password;
  @NotNull
  private String realName = "";
  @NotNull
  private String mobile = "";
  @NotNull
  private String email = "";
  private String description = "";
  @ExcludeColumn
  private Role role;

  @Override
  public String toString() {
    return "User [userId=" + userId + ", username=" + username + ", realName=" + realName + ", mobile=" + mobile
        + ", email=" + email + "]";
  }

}
