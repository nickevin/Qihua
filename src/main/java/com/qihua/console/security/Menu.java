package com.qihua.console.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class Menu implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long menuId;
  private Long parentId;
  private String menuName;
  private String menuUrl;
  private List<Menu> items = new ArrayList<Menu>();

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Menu item : items) {
      sb.append(item);
    }

    return "Menu [menuId=" + menuId + ", parentId=" + parentId + ", menuName=" + menuName + ", menuUrl=" + menuUrl
        + ", items=" + sb + "]";
  }

}
