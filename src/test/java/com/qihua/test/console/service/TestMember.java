package com.qihua.test.console.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qihua.console.member.Member;
import com.qihua.console.member.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext-test.xml", "/spring/applicationContext-test-assembly.xml"})
public class TestMember extends AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  private MemberService service;

  @Test
  public void testSelect() throws Exception {
    assertEquals("", service.find().size(), 3);
  }

  @Test
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

  @Test
  public void testUpdate() throws Exception {
    Member item = service.find("82ac42b5e1fb4766bad2f9d65597794d");
    item.setMemberName("sam.zhang");
    service.save(item);

    Member item2 = service.find("82ac42b5e1fb4766bad2f9d65597794d");
    assertEquals("", item.getMemberName(), item2.getMemberName());
  }

}
