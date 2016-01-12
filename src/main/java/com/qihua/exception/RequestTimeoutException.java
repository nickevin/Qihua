package com.qihua.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 服务器等待请求超时。
 *	
 * @author  aopfilter@163.com
 * @since   2013-10-11
 * @version 1.0
 * @see     http://docs.spring.io/spring/docs/3.0.x/api/org/springframework/http/HttpStatus.html
 */
@ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
public class RequestTimeoutException extends Exception {

	private static final long serialVersionUID = 1L;

}
