package com.qihua.aspect;

import java.sql.Timestamp;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.qihua.common.BaseEntity;

/**
 * Class description goes here.
 * 
 * @author zhen.ni@ebaotech.com
 * @since 2013-10-11
 * @version 1.0
 * @see
 */
@Component
@Aspect
public class SystemAspect {

  // @Pointcut("execution (* com.qihua..*.*Repository.select*(..))")
  // public void select() {}

  @Pointcut("execution (* com.qihua..*.*Repository.insert*(..))")
  public void insert() {}

  @Pointcut("execution (* com.qihua..*.*Repository.update*(..))")
  public void update() {}

  // @Before("select()")
  // public void select(final JoinPoint joinPoint) {
  // }

  @Before("insert()")
  public void insert(final JoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();
    if (args.length == 1) {
      Object domain = args[0];
      if (domain instanceof BaseEntity) {
        ((BaseEntity) domain).setInsertTime(new Timestamp(System.currentTimeMillis()));
        ((BaseEntity) domain).setUpdateTime(new Timestamp(System.currentTimeMillis()));
      }
    }
  }

  @Before("update()")
  public void update(final JoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();
    if (args.length == 1) {
      Object domain = args[0];
      if (domain instanceof BaseEntity) {
        ((BaseEntity) domain).setUpdateTime(new Timestamp(System.currentTimeMillis()));
      }
    }
  }

}
