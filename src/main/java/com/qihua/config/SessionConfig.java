package com.qihua.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since Jan 13, 2016
 * @version 1.0
 * @see
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 30)
public class SessionConfig {

}

