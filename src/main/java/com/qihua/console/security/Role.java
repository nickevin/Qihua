package com.qihua.console.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

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
public class Role implements Serializable {

  @Id
  private Long roleId;
  private String roleName = "";
  private List<Menu> items = new ArrayList<Menu>();

  public Role() {}

  public Role(final Long roleId) {
    this.roleId = roleId;
  }

  public Role(final Long roleId, final String roleName) {
    this.roleId = roleId;
    this.roleName = roleName;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Menu item : items) {
      sb.append(item);
    }

    return "Role [roleId=" + roleId + ", roleName=" + roleName + ", items=" + sb + "]";
  }

}
