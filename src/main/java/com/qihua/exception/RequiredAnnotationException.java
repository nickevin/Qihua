package com.qihua.exception;

/**
 * 必填注解为空。
 * 
 * @author aopfilter@163.com
 * @since 2013-10-11
 * @version 1.0
 * @see
 */
public class RequiredAnnotationException extends Exception {

  private static final long serialVersionUID = 1L;

  public RequiredAnnotationException() {
  }

  public RequiredAnnotationException(String message) {
    super(message);
  }

}
