package com.qihua.test.console.service;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qihua.console.card.Card;
import com.qihua.console.card.CardService;
import com.qihua.util.IdUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext-test.xml", "/spring/applicationContext-test-assembly.xml"})
public class TestCard extends AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  private CardService service;

  @Test
  public void testSelect() throws Exception {
    assertEquals("", service.find().size(), 142);
  }

  @Test
  public void testInsert() throws Exception {
    int size = service.find().size();

    Card item = new Card();
    item.setCardNo(IdUtils.uuid2());
    item.setPassword("password");
    item.setScore(200);
    service.save(item);

    Card item2 = new Card();
    item2.setCardNo(IdUtils.uuid2());
    item2.setPassword("password");
    item2.setScore(150);
    service.save(item2);

    assertEquals("", size + 2, 144);
  }

  @Test
  public void testUpdate() throws Exception {
    Card item = service.find("00e80183d57d4c129719cd22dbc83eb4");
    String uuid = IdUtils.uuid();
    item.setCardNo(uuid);
    service.save(item);

    Card item2 = service.find("00e80183d57d4c129719cd22dbc83eb4");
    assertEquals("", item.getCardNo(), item2.getCardNo());
  }

  // @Test
  public void testGenerate() throws Exception {
    for (int idx = 0; idx < 8000; idx++) {
      System.out
          .println(IdUtils.uuid2() + "\t" + IdUtils.uuid().substring(0, 10) + "\t" + RandomUtils.nextInt(100, 5000));

    }
  }
}
