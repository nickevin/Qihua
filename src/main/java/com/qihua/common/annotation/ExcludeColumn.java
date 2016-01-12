package com.qihua.common.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since Nov 18, 2014 4:27:44 PM
 * @version 1.0
 * @see
 */

@Target({ METHOD, FIELD })
@Retention(RUNTIME)
public @interface ExcludeColumn {

}
