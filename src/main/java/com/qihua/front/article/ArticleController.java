package com.qihua.front.article;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qihua.common.Constants;

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
@RequestMapping("/")
public class ArticleController {


  @Autowired
  private ArticleService articleService;

  @RequestMapping(value = "club")
  public String club(HttpServletRequest request) throws Exception {
    Article content = articleService.findByArticleType(Constants.ARTICLE_TYPE_CLUB);

    request.setAttribute("content", content);

    return "/front/club";
  }

  @RequestMapping(value = "partner")
  public String partner(HttpServletRequest request) throws Exception {
    Article content = articleService.findByArticleType(Constants.ARTICLE_TYPE_PARTNER);

    request.setAttribute("content", content);

    return "/front/partner";
  }

  @RequestMapping(value = "merchant")
  public String merchant(HttpServletRequest request) throws Exception {
    Article content = articleService.findByArticleType(Constants.ARTICLE_TYPE_MERCHANT);

    request.setAttribute("content", content);

    return "/front/merchant";
  }

}
