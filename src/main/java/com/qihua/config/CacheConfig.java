package com.qihua.config;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

  @Value("#{configProperties['redis.host']}")
  private String hostName;

  @Value("#{configProperties['redis.port']}")
  private int port;

  @Value("#{configProperties['redis.timeout']}")
  private int timeout;

  @Value("#{configProperties['redis.pool.max-active']}")
  private int maxActive;

  @Value("#{configProperties['redis.pool.max-idle']}")
  private int maxIdle;

  @Value("#{configProperties['redis.pool.max-wait']}")
  private int maxWait;

  @Override
  @Bean
  public KeyGenerator keyGenerator() {
    return new KeyGenerator() {
      public Object generate(Object o, Method method, Object... objects) {
        StringBuilder sb = new StringBuilder();
        sb.append(o.getClass().getName());
        sb.append(method.getName());
        for (Object obj : objects) {
          sb.append(obj.toString());
        }
        return sb.toString();
      }
    };
  }

  @Bean
  public JedisConnectionFactory redisConnectionFactory() {
    JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
    redisConnectionFactory.setHostName(hostName);
    redisConnectionFactory.setPort(port);
    redisConnectionFactory.setTimeout(timeout);

    JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setBlockWhenExhausted(true);
    poolConfig.setJmxEnabled(false);
    poolConfig.setLifo(true);
    poolConfig.setMaxIdle(maxIdle);
    poolConfig.setMaxTotal(maxActive);
    poolConfig.setMaxWaitMillis(maxWait);
    poolConfig.setMinEvictableIdleTimeMillis(60 * 60 * 10);
    poolConfig.setMinIdle(0);
    poolConfig.setNumTestsPerEvictionRun(3);
    poolConfig.setSoftMinEvictableIdleTimeMillis(1800000);
    poolConfig.setTestOnBorrow(true);
    poolConfig.setTestWhileIdle(true);

    redisConnectionFactory.setPoolConfig(poolConfig);

    return redisConnectionFactory;
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
    redisTemplate.setConnectionFactory(cf);
    return redisTemplate;
  }

  @Bean
  public CacheManager cacheManager(RedisTemplate redisTemplate) {
    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
    cacheManager.setDefaultExpiration(3000);

    return cacheManager;
  }
}
