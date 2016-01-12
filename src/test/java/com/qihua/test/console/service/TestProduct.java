package com.qihua.test.console.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qihua.console.product.Product;
import com.qihua.console.product.ProductService;
import com.qihua.util.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext-test.xml", "/spring/applicationContext-test-assembly.xml"})
public class TestProduct extends AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  private ProductService service;

  @Test
  public void testSelect() throws Exception {
    assertEquals("", service.find().size(), 28);
  }

  @Test
  public void testInsert() throws Exception {
    int size = service.find().size();

    Product item = new Product();
    item.setProductName("有线游戏键盘机械手感电脑背光键盘迷你键盘笔记本台式键盘");
    item.setProductCode(DateUtils.currentDate() + "01");
    item.setPrice(50.2);
    item.setCategoryId(new Long(2));
    item.setDescription("有线游戏键盘产品描述");
    item.setScore(20);
    service.save(item);

    Product item2 = new Product();
    item2.setProductName("糖果色彩格子棉拖鞋");
    item2.setProductCode(DateUtils.currentDate() + "02");
    item2.setPrice(20);
    item2.setCategoryId(new Long(3));
    item2.setDescription("糖果色彩格子棉拖鞋产品描述");
    item2.setScore(20);
    service.save(item2);

    assertEquals("", size + 2, 30);
  }

  @Test
  public void testUpdate() throws Exception {
    Product item = service.find("2bec035fc64440a095cffb7d1693296c");
    item.setProductName("纯银恩戒");
    item.setDescription("纯银恩戒产品描述");
    service.save(item);

    Product item2 = service.find("2bec035fc64440a095cffb7d1693296c");
    assertEquals("", item.getProductName() + item.getDescription(), item2.getProductName() + item2.getDescription());
  }

}
