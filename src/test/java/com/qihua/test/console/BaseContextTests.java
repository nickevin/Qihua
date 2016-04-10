package com.qihua.test.console;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * BaseContextTests.java.
 * 
 * @author aopfilter@163.com
 * @since Apr 7, 2016
 * @version 1.0
 * @see
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/test.xml"})
public class BaseContextTests extends AbstractTransactionalJUnit4SpringContextTests {

}
