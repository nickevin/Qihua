package com.qihua.exception;

/**
 * 存在多个对象。
 * 
 * @author aopfilter@163.com
 * @since 2013-10-11
 * @version 1.0
 * @see
 */
public class MultipleObjectException extends Exception {

	private static final long serialVersionUID = 1L;

	public MultipleObjectException() {
	}

	public MultipleObjectException(String message) {
		super(message);
	}

}
