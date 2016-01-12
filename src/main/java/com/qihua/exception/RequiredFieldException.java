package com.qihua.exception;

/**
 * 必填项为空。
 * 
 * @author aopfilter@163.com
 * @since 2013-10-11
 * @version 1.0
 * @see
 */
public class RequiredFieldException extends Exception {

  private static final long serialVersionUID = 1L;

  public RequiredFieldException() {
  }

  public RequiredFieldException(String message) {
    super(message);
  }

}
