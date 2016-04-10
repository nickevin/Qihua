package com.qihua.test.console.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.qihua.console.product.Product;
import com.qihua.console.product.ProductService;
import com.qihua.test.console.BaseContextTests;
import com.qihua.util.DateUtils;
import com.qihua.util.IdUtils;

public class TestProduct extends BaseContextTests {

  @Autowired
  private ProductService service;

  // @Test
  public void testSelect() throws Exception {
    assertEquals("", service.find().size(), 28);
  }

  // @Test
  @Rollback(false)
  public void testInsert() throws Exception {
    Product item = new Product();
    item.setProductId(IdUtils.uuid2());
    item.setProductName("aa");
    item.setProductCode(DateUtils.currentDate() + "01");
    item.setPrice(50.2);
    item.setCategoryId(new Long(2));
    item.setDescription("bb");
    item.setScore(20);
    service.create(item);

    Product item2 = new Product();
    item2.setProductId(IdUtils.uuid2());
    item2.setProductName("cc");
    item2.setProductCode(DateUtils.currentDate() + "02");
    item2.setPrice(20);
    item2.setCategoryId(new Long(3));
    item2.setDescription("dd");
    item2.setScore(20);
    service.create(item2);

    // assertEquals("", size + 2, 30);
  }

  @Test
  @Rollback(false)
  public void testUpdate() throws Exception {
    Product item = service.find("2bec035fc64440a095cffb7d1693296c");
    item.setProductName("***纯银恩戒****");
    item.setDescription("纯银恩戒产品描述");
    item.setPrice(22);
    service.update(item);

    // Product item2 = service.find("2bec035fc64440a095cffb7d1693296c");
    // assertEquals("", item.getProductName() + item.getDescription(), item2.getProductName() + item2.getDescription());
  }

}
