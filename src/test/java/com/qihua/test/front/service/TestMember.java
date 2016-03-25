package com.qihua.test.front.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qihua.front.member.Member;
import com.qihua.front.member.MemberService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/test.xml", "/spring/test-assembly.xml"})
public class TestMember extends AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  private MemberService service;

  @Autowired
  private MemberCacheService cacheService;

  @Test
  public void test0() throws Exception {
    cacheService.deleteAll();
  }

  @Test
  public void test1() throws Exception {
    assertEquals("", service.findByEmail("nickevin@gmail.com").getMobile(), "13818112388");
    assertEquals("", service.findByEmail("nickevin@gmail.com").getMemberId(), "0c3889a322604d4b856330df52da43c9");

    assertEquals("", service.findByEmail("aopfilter@163.com").getMemberId(), "82ac42b5e1fb4766bad2f9d65597794d");
    assertEquals("", service.findByEmail("aopfilter@163.com").getMemberName(), "aopfilter");
  }

  @Test
  public void test2() throws Exception {
    assertEquals("", service.findByMobile("13818112388").getEmail(), "nickevin@gmail.com");
    assertEquals("", service.findByMobile("13818112388").getMemberName(), "nickevin");

    assertEquals("", service.findByMobile("13818112391").getMemberName(), "aopfilter");
    assertEquals("", service.findByMobile("13818112391").getMobile(), "13818112391");
  }

  @Test
  public void test3() throws Exception {
    assertEquals("", service.find("0c3889a322604d4b856330df52da43c9").getMemberName(), "nickevin");
    assertEquals("", service.find("0c3889a322604d4b856330df52da43c9").getEmail(), "nickevin@gmail.com");
  }

  @Test
  public void test4() throws Exception {
    assertEquals("", service.existsName("nickevin").getEmail(), "nickevin@gmail.com");
    assertEquals("", service.existsName("nickevin").getMobile(), "13818112388");
  }

  @Test
  public void test5() throws Exception {
    Member item = new Member();
    item.setMemberId("0c3889a322604d4b856330df52da43c9");
    item.setPassword("a66abb5684c45962d887564f08346e8d");

    assertEquals("", service.updatePassword(item).getMobile(), "13818112388");
    assertEquals("", service.find("0c3889a322604d4b856330df52da43c9").getPassword(),
        "a66abb5684c45962d887564f08346e8d");
  }

  @Test
  public void test6() throws Exception {
    Member item = new Member();
    item.setMemberId("0c3889a322604d4b856330df52da43c9");
    item.setScore(1000);

    assertEquals("", service.updateScore(item).getEmail(), "nickevin@gmail.com");
    assertEquals("", service.find("0c3889a322604d4b856330df52da43c9").getScore(), 1000);
  }

  // @Test
  public void testInsert() throws Exception {
    int size = service.find().size();

    Member item = new Member();
    item.setMemberName("zhen.ni");
    item.setPassword("123");
    item.setEmail("abc@163.com");
    item.setMobile("13761620233");
    service.save(item);

    Member item2 = new Member();
    item2.setMemberName("gang.wu");
    item2.setPassword("123");
    item2.setEmail("abc@qq.com");
    item2.setMobile("13761620234");
    service.save(item2);

    assertEquals("", size + 2, 5);
  }

  // @Test
  public void testUpdate() throws Exception {
    Member item = service.find("82ac42b5e1fb4766bad2f9d65597794d");
    item.setMemberName("sam.zhang");
    service.save(item);

    Member item2 = service.find("82ac42b5e1fb4766bad2f9d65597794d");
    assertEquals("", item.getMemberName(), item2.getMemberName());
  }

}
