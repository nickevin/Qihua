package com.qihua.test;

import org.apache.commons.lang3.RandomStringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

public class RedisTransactionTest {

  private static String host = "192.168.199.183";

  private static JedisPool pool = new JedisPool(new JedisPoolConfig(), host, 6739, 3000, "admin123456");

  private long rowCount = 1000000; // 100万

  public static void main(final String[] args) {
    long start = System.currentTimeMillis();
    new RedisTransactionTest().noTransactionNoPipeline();
    System.out.println("noTransactionNoPipeline use " + (System.currentTimeMillis() - start) / 1000);

    pool = new JedisPool(new JedisPoolConfig(), host);
    start = System.currentTimeMillis();
    new RedisTransactionTest().pipelineWithoutTransaction();
    System.out.println("pipelineWithoutTransaction use " + (System.currentTimeMillis() - start) / 1000);

    pool = new JedisPool(new JedisPoolConfig(), host);
    start = System.currentTimeMillis();
    new RedisTransactionTest().pipelineWithTransaction();
    System.out.println("pipelineWithTransaction use " + (System.currentTimeMillis() - start) / 1000);

    pool = new JedisPool(new JedisPoolConfig(), host);
    start = System.currentTimeMillis();
    new RedisTransactionTest().transactionNoPipeline();
    System.out.println("transactionNoPipeline use " + (System.currentTimeMillis() - start) / 1000);

  }

  public void pipelineWithoutTransaction() {
    Jedis jedis = pool.getResource();
    try {
      Pipeline p = jedis.pipelined();
      for (int i = 0; i < rowCount; i++) {
        String key = RandomStringUtils.randomAlphabetic(8);
        p.set(key, RandomStringUtils.randomNumeric(5));
        p.expire(key, 5 * 60);
      }
      p.sync();
    } catch (Exception e) {
      pool.returnResource(jedis);
    }
  }

  public void pipelineWithTransaction() {
    Jedis jedis = pool.getResource();
    try {
      Pipeline p = jedis.pipelined();
      p.multi();
      for (int i = 0; i < rowCount; i++) {
        String key = RandomStringUtils.randomAlphabetic(8);
        p.set(key, RandomStringUtils.randomNumeric(5));
        p.expire(key, 5 * 60);
      }
      p.exec();
      p.sync();
    } catch (Exception e) {
      pool.returnResource(jedis);
    }
  }

  public void noTransactionNoPipeline() {
    Jedis jedis = pool.getResource();
    try {
      for (int i = 0; i < rowCount; i++) {
        String key = RandomStringUtils.randomAlphabetic(8);
        jedis.set(key, RandomStringUtils.randomNumeric(5));
        jedis.expire(key, 5 * 60);
      }
    } catch (Exception e) {
      pool.returnResource(jedis);
    }
  }

  public void transactionNoPipeline() {
    Jedis jedis = pool.getResource();
    try {
      Transaction tx = jedis.multi();
      for (int i = 0; i < rowCount; i++) {
        String key = RandomStringUtils.randomAlphabetic(8);
        tx.set(key, RandomStringUtils.randomNumeric(5));
        tx.expire(key, 5 * 60);
      }
      tx.exec();
    } catch (Exception e) {
      pool.returnResource(jedis);
    }
  }
}
