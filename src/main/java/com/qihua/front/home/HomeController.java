package com.qihua.front.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qihua.util.ExceptionUtils;

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
@RequestMapping("/")
public class HomeController {


  @Autowired
  private HomeService homeService;

  @RequestMapping
  public String index(HttpServletRequest request) throws Exception {
    try {
      request.setAttribute("centerImgList", homeService.findCenterImgList());
      request.setAttribute("bottomImgList", homeService.findBottomImgList());
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));
    }

    return "/front/index";
  }

}
