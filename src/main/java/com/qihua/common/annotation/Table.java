package com.qihua.common.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since Nov 19, 2014 6:55:02 PM
 * @version 1.0
 * @see
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Table {
	/**
	 * (Optional) The name of the table.
	 * <p/>
	 * Defaults to the entity name.
	 */
	String name() default "";

	/**
	 * (Optional) The primary key of the table.
	 * <p/>
	 */
	String id() default "";

}