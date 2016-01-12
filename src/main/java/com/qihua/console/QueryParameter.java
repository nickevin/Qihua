package com.qihua.console;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import com.qihua.common.Constants;
import com.qihua.console.user.User;

import lombok.extern.slf4j.Slf4j;

/**
 * Class description goes here.
 *
 * @author zhen.ni@ebaotech.com
 * @since 2013-10-14
 * @version 1.0
 * @see
 */
@Slf4j
public class QueryParameter {

  private User sessionUser;
  private String status = "1";
  private int pageNo = 1;
  private int pageSize = Constants.PAGE_SIZE;

  public QueryParameter() {
    try {
      HttpServletRequest request =
          ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
      sessionUser = (User) WebUtils.getSessionAttribute(request, Constants.SESSION_USER);
    } catch (Exception e) {
      log.debug("未登录，无法获取session。");
    }
  }

  public User getSessionUser() {
    return sessionUser;
  }

  public void setSessionUser(User sessionUser) {
    this.sessionUser = sessionUser;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  @Override
  public String toString() {
    return "QueryParameter [status=" + status + ", pageNo=" + pageNo + ", pageSize=" + pageSize + "]";
  }

}
