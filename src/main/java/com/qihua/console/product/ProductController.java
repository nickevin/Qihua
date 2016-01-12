package com.qihua.console.product;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/console/product")
public class ProductController {


  @Autowired
  private CategoryService categoryService;

  @Autowired
  private ProductService productService;

  @RequestMapping
  public String list(HttpServletRequest request, ProductQueryParameter queryParam) {
    try {
      request.setAttribute("queryParam", queryParam);
      request.setAttribute("categoryList", categoryService.findCategories());
      request.setAttribute("productList", productService.find(queryParam));
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/console/product/list";
  }

  @RequestMapping(value = "search-json")
  public @ResponseBody JSONResponseBody search(HttpServletRequest request, ProductQueryParameter queryParam) {
    List<Product> list = Collections.emptyList();

    try {
      list = productService.find(queryParam);

      System.out.println("ProductController.search()" + list);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return new JSONResponseBody(true, list);
  }

  @RequestMapping(value = "/display")
  public String display(HttpServletRequest request, @RequestParam(required = false) String productId) {
    Product item = new Product();

    try {
      if (productId == null) {
        // ...
      } else {
        item = productService.find(productId);
      }

      request.setAttribute("product", item);
      request.setAttribute("categoryList", categoryService.findCategories());
      request.setAttribute("subcategoryList", categoryService.findSubcategories());
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/console/product/display";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody save(Product item) {
    try {
      item = productService.save(item);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody(true, item.getProductId());
  }

  @RequestMapping(value = "/remove", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody remove(HttpServletRequest request, String productId) {
    try {
      productService.remove(productId);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody();
  }

  @RequestMapping(value = "/rating", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody rating(HttpServletRequest request, String productId, Integer rate) {
    try {
      productService.rating(productId, rate);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody();
  }

  @RequestMapping(value = "/switchStatus", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody switchStatus(HttpServletRequest request, Product product) {
    try {
      productService.switchStatus(product);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody();
  }

}
