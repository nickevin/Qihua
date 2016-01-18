package com.qihua.test.front.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qihua.front.member.Member;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since Jan 15, 2016
 * @version 1.0
 * @see
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext-test.xml"})
public class TestMemberCacheService extends AbstractJUnit4SpringContextTests {

  @Autowired
  private MemberCacheService service;

  @Test
  public void test0() throws Exception {
    service.deleteAll();
  }

  @Test
  public void test2() throws Exception {
    Member member = new Member();

    member.setMemberName("root");
    member.setPassword("123");
    member.setEmail("root@163.com");
    member.setMobile("133");

    service.save(member);

    member.setMemberName("root2");
    member.setPassword("123");
    member.setEmail("root2@163.com");
    member.setMobile("155");

    service.update(member);
  }

  @Test
  public void test4() throws Exception {
    Member m3 = new Member();
    m3.setMemberId("0001");
    m3.setEmail("root2@163.com");
    System.out.println(service.findByEmail(m3));

    Member m2 = new Member();
    m2.setMemberId("0001");
    m2.setEmail("root@163.com");
    System.out.println(service.findByEmail(m2));

    Member m1 = new Member();
    m1.setMemberId("0001");
    System.out.println(service.findByMemberId(m1));

    m1.setMemberId("0002");
    System.out.println(service.findByMemberId(m1));

    Member m4 = new Member();
    m4.setMemberId("0001");
    m4.setMemberName("root");
    System.out.println(service.findByMemberName(m4));

    Member m5 = new Member();
    m5.setMemberId("0001");
    m5.setMemberName("root2");
    System.out.println(service.findByMemberName(m5));
  }

}
