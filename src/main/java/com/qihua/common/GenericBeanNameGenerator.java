package com.qihua.common;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since 2014年4月3日
 * @version 1.0
 * @see
 */
public class GenericBeanNameGenerator implements BeanNameGenerator {

  @Override
  public String generateBeanName(final BeanDefinition definition, final BeanDefinitionRegistry registry) {
    return definition.getBeanClassName();
  }

}
