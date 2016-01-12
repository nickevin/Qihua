package com.qihua.front.gift;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qihua.common.Constants;
import com.qihua.front.product.CategoryService;
import com.qihua.front.product.ProductService;
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
@RequestMapping("/gift")
public class GiftController {

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private ProductService productService;

  @RequestMapping
  public String index(HttpServletRequest request) throws Exception {
    try {
      // request.setAttribute("categoryList", categoryService.findCategories());
      request.setAttribute("recommendList", productService.findRecommendImg());
      request.setAttribute("flashSale", productService.findFlashSaleProduct());
      request.setAttribute("hottestList", productService.findHottest());
      request.setAttribute("tabAList", productService.findByScore(0, 199));
      request.setAttribute("tabBList", productService.findByScore(199, 599));
      request.setAttribute("tabCList", productService.findByScore(599, 999));
      request.setAttribute("a", productService.findImgBlock(Constants.PRODUCT_IMG_BLOCK_POSISION_A));
      request.setAttribute("b", productService.findImgBlock(Constants.PRODUCT_IMG_BLOCK_POSISION_B));
      request.setAttribute("c", productService.findImgBlock(Constants.PRODUCT_IMG_BLOCK_POSISION_C));
      request.setAttribute("d", productService.findImgBlock(Constants.PRODUCT_IMG_BLOCK_POSISION_D));
      request.setAttribute("e", productService.findImgBlock(Constants.PRODUCT_IMG_BLOCK_POSISION_E));
      request.setAttribute("f", productService.findImgBlock(Constants.PRODUCT_IMG_BLOCK_POSISION_F));
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/front/gift";
  }
}
