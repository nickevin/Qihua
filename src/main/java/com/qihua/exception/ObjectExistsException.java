package com.qihua.exception;

/**
 * 对象已存在。
 * 
 * @author aopfilter@163.com
 * @since 2013-10-11
 * @version 1.0
 * @see
 */
public class ObjectExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public ObjectExistsException() {
	}

	public ObjectExistsException(String message) {
		super(message);
	}

}
