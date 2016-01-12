package com.qihua.console.article;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qihua.common.Constants;
import com.qihua.common.JSONResponseBody;
import com.qihua.util.ExceptionUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Class description goes here.
 *
 * @author zhen.ni@ebaotech.com
 * @since 2013-10-18
 * @version 1.0
 * @see
 */
@Slf4j
@Controller
@RequestMapping("/console/article")
public class ArticleController {


  @Autowired
  private ArticleService articleService;

  @RequestMapping
  public String list(HttpServletRequest request, @RequestParam(required = false) String p) {
    try {
      Article article = null;

      String title = "";

      if (p == null || p.contains("club")) {
        article = articleService.find(new Long(Constants.ARTICLE_TYPE_CLUB));
        title = Constants.ARTICLE_TYPE_CLUB_NAME;
      } else if (p.contains("partner")) {
        article = articleService.find(new Long(Constants.ARTICLE_TYPE_PARTNER));
        title = Constants.ARTICLE_TYPE_PARTNER_NAME;
      } else {
        article = articleService.find(new Long(Constants.ARTICLE_TYPE_MERCHANT));
        title = Constants.ARTICLE_TYPE_MERCHANT_NAME;
      }

      request.setAttribute("article", article);
      request.setAttribute("title", title);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/console/article/display";
  }

  @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody save(Article article) {
    try {
      article = articleService.save(article);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody(true, article.getArticleId());
  }

}
