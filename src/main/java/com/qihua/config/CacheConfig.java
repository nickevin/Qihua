package com.qihua.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class CacheConfig {

  @Value("#{configProperties['redis.host']}")
  private String hostName;

  @Value("#{configProperties['redis.password']}")
  private String password;

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

  // @Override
  // @Bean
  // public KeyGenerator keyGenerator() {
  // return new KeyGenerator() {
  // @Override
  // public Object generate(final Object object, final Method method, final Object... objects) {
  // StringBuilder sb = new StringBuilder(50);
  // sb.append(object.getClass().getName() + ".");
  // sb.append(method.getName() + ":");
  // for (Object obj : objects) {
  // sb.append(obj.toString());
  // }
  //
  // return sb.toString();
  // }
  // };
  // }

  @Bean
  public JedisConnectionFactory redisConnectionFactory() {
    JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
    redisConnectionFactory.setHostName(hostName);
    redisConnectionFactory.setPassword(password);
    redisConnectionFactory.setPort(port);
    redisConnectionFactory.setTimeout(timeout);

    JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMaxIdle(maxIdle);
    poolConfig.setMaxTotal(maxActive);
    poolConfig.setMaxWaitMillis(maxWait); // 获取连接时的最大等待毫秒数
    poolConfig.setTestOnBorrow(true); // 获取连接的时候检查有效性
    poolConfig.setTestWhileIdle(false);// 空闲时检查有效性
    poolConfig.setTimeBetweenEvictionRunsMillis(-1l);// 逐出扫描的时间间隔（毫秒）
    poolConfig.setNumTestsPerEvictionRun(3); // 每次连接断开检查时，断开的最大数目
    poolConfig.setMinEvictableIdleTimeMillis(1000L * 300L);// 断开连接的最小空闲时间（毫秒）
    poolConfig.setBlockWhenExhausted(true);// 连接耗尽时是否阻塞, false：报异常，ture：阻塞直到超时
    poolConfig.setJmxEnabled(false);
    poolConfig.setLifo(true);

    redisConnectionFactory.setPoolConfig(poolConfig);

    return redisConnectionFactory;
  }

  @Bean
  public RedisTemplate<String, Serializable> redisTemplate(final RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<String, Serializable>();
    redisTemplate.setConnectionFactory(connectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());

    return redisTemplate;
  }

  @Bean
  public CacheManager cacheManager(final RedisTemplate<String, Serializable> redisTemplate) {
    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
    cacheManager.setDefaultExpiration(60 * 10);

    return cacheManager;
  }
}
