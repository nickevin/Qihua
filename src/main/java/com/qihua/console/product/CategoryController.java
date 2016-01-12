package com.qihua.console.product;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequestMapping("/console/product/category")
public class CategoryController {


  @Autowired
  private CategoryService categoryService;

  @RequestMapping
  public String list(HttpServletRequest request) {
    try {
      request.setAttribute("categoryList", categoryService.find());
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/console/product/category/list";
  }

  @RequestMapping(value = "/display")
  public String display(HttpServletRequest request, @RequestParam(required = false) Long categoryId) {
    Category item = new Category();

    try {
      if (categoryId == null) {
        // ...
      } else {
        item = categoryService.find(categoryId);
      }

      request.setAttribute("category", item);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/console/product/category/display";
  }

  // @RequestMapping(value = "/save", method = RequestMethod.POST)
  // public @ResponseBody
  // JSONResponseBody save(@RequestBody Category category) {
  // try {
  // category = categoryService.save(category);
  // } catch (Exception e) {
  // log.error(ExceptionUtils.getStackTraceAsString(e));
  //
  // return new JSONResponseBody(false, e.getMessage());
  // }
  //
  // return new JSONResponseBody(true, category.getCategoryId());
  // }
}
