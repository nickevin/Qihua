package com.qihua.front;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * Class description goes here.
 *
 * @author aopfilter@163.com
 * @since 2014-09-20
 * @version 1.0
 * @see
 */
@Slf4j
@Controller
@RequestMapping("/guide")
public class GuideController {

  @RequestMapping(value = "/about")
  public String about(HttpServletRequest request) throws Exception {
    return "/front/guide/about";
  }

  @RequestMapping(value = "/brand")
  public String brand(HttpServletRequest request) throws Exception {
    return "/front/guide/brand";
  }

  @RequestMapping(value = "/intro")
  public String intro(HttpServletRequest request) throws Exception {
    return "/front/guide/intro";
  }

  @RequestMapping(value = "/faq")
  public String cs(HttpServletRequest request) throws Exception {
    return "/front/guide/faq";
  }

  @RequestMapping(value = "/reset")
  public String reset(HttpServletRequest request) throws Exception {
    return "/front/guide/reset";
  }

  @RequestMapping(value = "/process")
  public String process(HttpServletRequest request) throws Exception {
    return "/front/guide/process";
  }

  @RequestMapping(value = "/agreement")
  public String agreement(HttpServletRequest request) throws Exception {
    return "/front/guide/agreement";
  }

  @RequestMapping(value = "/distribution")
  public String distribution(HttpServletRequest request) throws Exception {
    return "/front/guide/distribution";
  }

  @RequestMapping(value = "/logistics")
  public String logistics(HttpServletRequest request) throws Exception {
    return "/front/guide/logistics";
  }

  @RequestMapping(value = "/advertisement")
  public String advertisement(HttpServletRequest request) throws Exception {
    return "/front/guide/advertisement";
  }

  @RequestMapping(value = "/links")
  public String links(HttpServletRequest request) throws Exception {
    return "/front/guide/links";
  }

}
