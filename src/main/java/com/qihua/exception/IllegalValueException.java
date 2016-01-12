package com.qihua.exception;

/**
 * 非法值。
 * 
 * @author aopfilter@163.com
 * @since 2014年4月15日
 * @version 1.0
 * @see
 */
public class IllegalValueException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalValueException() {
	}

	public IllegalValueException(String message) {
		super(message);
	}

}
