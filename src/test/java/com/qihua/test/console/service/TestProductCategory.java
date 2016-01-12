package com.qihua.test.console.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qihua.console.product.Category;
import com.qihua.console.product.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext-test.xml", "/spring/applicationContext-test-assembly.xml"})
public class TestProductCategory extends AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  private CategoryService service;

  @Test
  public void testSelect() throws Exception {
    assertEquals("", service.find().size(), 18);
  }

  @Test
  public void testInsert() throws Exception {
    int size = service.find().size();

    Category item = new Category();
    item.setCategoryId(600l);
    item.setCategoryName("电子产品");
    item.setSubcategoryId(600100l);
    item.setSubcategoryName("手机");
    service.save(item);

    assertEquals("", size + 1, 19);
  }

}
