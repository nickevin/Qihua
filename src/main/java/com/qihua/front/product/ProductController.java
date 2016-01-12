package com.qihua.front.product;

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
@RequestMapping("/product")
public class ProductController {


  @Autowired
  private CategoryService categoryService;

  @Autowired
  private ProductService productService;

  @RequestMapping(value = "/display")
  public String display(HttpServletRequest request, String productId) {
    try {
      Product product = productService.find(productId);

      request.setAttribute("product", product);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/front/product/detail";
  }

  @RequestMapping(value = "/category")
  public String category(HttpServletRequest request, ProductQueryParameter queryParam) {
    try {
      String categoryId = queryParam.getCategoryId();
      if (categoryId.length() == 6) { // 子类
        Long _categoryId = Long.parseLong(categoryId.substring(0, 3));
        request.setAttribute("category", categoryService.find(_categoryId));
        request.setAttribute("subcategoryList", categoryService.findSubcategories(_categoryId));
      } else {
        request.setAttribute("category", categoryService.find(new Long(categoryId)));
        request.setAttribute("subcategoryList", categoryService.findSubcategories(new Long(categoryId)));
      }

      request.setAttribute("queryParam", queryParam);
      request.setAttribute("pageModel", productService.search(queryParam));
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/front/product/category";
  }
}
