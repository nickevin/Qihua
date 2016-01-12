package com.qihua.exception;

/**
 * 空对象。
 * 
 * @author aopfilter@163.com
 * @since 2013-10-11
 * @version 1.0
 * @see
 */
public class NullObjectException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NullObjectException(String message) {
		super(message);
	}
	
	public NullObjectException() {}
}
