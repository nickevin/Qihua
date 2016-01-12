package com.qihua.test.console.service;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qihua.common.Constants;
import com.qihua.console.article.Article;
import com.qihua.console.article.ArticleService;

/**
 * @author Kevin
 * @see http://blog.csdn.net/u013998070/article/details/41621249?utm_source=tuicool&utm_medium=referral
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext-test.xml", "/spring/applicationContext-test-assembly.xml"})
public class TestArticle extends AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  private ArticleService service;

  @Test
  public void testSelect() throws Exception {
    int size = service.find().size();

    assertThat(size, equalTo(3));
    assertThat(size, greaterThan(1));
    assertThat(size, lessThan(5));
    assertThat(size, anyOf(greaterThan(1), lessThan(5)));
  }

  @Test
  public void testInsert() throws Exception {
    int size = service.find().size();

    Article item = new Article();
    item.setArticleType(Constants.ARTICLE_TYPE_CLUB);
    item.setContent(
        "<p><span style=\"font-size:16px\"><span style=\"font-family:trebuchet ms,helvetica,sans-serif\">I am a web developer and desginer based in Medell&iacute;n - Colombia, I like read books, good music and nature.I am a web developer and desginer based in Medell&iacute;n - Colombia, I like read books, good music and nature.I am a web developer and desginer based in Medell&iacute;n - Colombia, I like read books, good music and nature.</span></span></p>");
    service.save(item);

    Article item2 = new Article();
    item2.setArticleType(Constants.ARTICLE_TYPE_PARTNER);
    item2.setContent(
        "<p><span style=\"font-size:26px\"><span style=\"font-family:trebuchet ms,helvetica,sans-serif\">I am a web developer and desginer based in Medell&iacute;n - Colombia, I like read books, good music and nature.I am a web developer and desginer based in Medell&iacute;n - Colombia, I like read books, good music and nature.I am a web developer and desginer based in Medell&iacute;n - Colombia, I like read books, good music and nature.</span></span></p>");
    service.save(item2);

    assertEquals("", size + 2, 5);
  }

  @Test
  public void testUpdate() throws Exception {
    Article item = service.find(new Long(2));
    item.setContent(
        "<p><span style=\"font-size:26px\"><span style=\"font-family:trebuchet ms,helvetica,sans-serif\">I am a web <span style=\"font-size:18px\">developer </span>and <span style=\"color:#FF0000\">desginer </span>based in Medell&iacute;n - Colombia, I like read books, good music <span style=\"color:#00FF00\">and </span>nature.I am a web developer and desginer based in Medell&iacute;n - Colombia, I like read books, good music and <span style=\"color:#FFA07A\">nature</span>.</span></span></p>");
    service.save(item);

    Article item2 = service.find(new Long(2));
    assertEquals("", item.getContent(), item2.getContent());
  }

}
