package com.qihua.test.console.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qihua.console.security.Role;
import com.qihua.console.user.User;
import com.qihua.console.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext-test.xml", "/spring/applicationContext-test-assembly.xml"})
public class TestUser extends AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  private UserService service;

  @Test
  public void testSelect() throws Exception {
    assertEquals("", service.find().size(), 3);
  }

  @Test
  public void testInsert() throws Exception {
    int size = service.find().size();

    User user = new User();
    user.setUsername("zhen.ni");
    user.setRealName("zhen.ni");
    user.setPassword("fd28e770000849b91d5e2b2dc7d363bc");
    user.setEmail("zhen.ni@163.com");
    user.setMobile("13761622012");
    user.setRole(new Role(1l));
    service.save(user);

    User user2 = new User();
    user2.setUsername("kuhn.wang");
    user2.setRealName("kuhn.wang");
    user2.setPassword("9d652de355bdc08425639f22f4e4c96e");
    user2.setEmail("kuhn.wang@163.com");
    user2.setMobile("13761622012");
    user2.setRole(new Role(1l));
    service.save(user2);

    assertEquals("", size + 2, 5);
  }

  @Test
  public void testUpdate() throws Exception {
    User item = service.find("aeffe47a3d7d4001abcdf184c281bd4a");
    item.setUsername("sam.zhang");
    item.setRole(new Role(new Long(0)));
    service.save(item);

    User item2 = service.find("aeffe47a3d7d4001abcdf184c281bd4a");
    assertEquals("", item.getUsername(), item2.getUsername());
  }
}
