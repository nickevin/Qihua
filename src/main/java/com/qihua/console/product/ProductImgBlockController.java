package com.qihua.console.product;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qihua.common.Constants;
import com.qihua.common.FileMeta;
import com.qihua.common.JSONResponseBody;
import com.qihua.util.ExceptionUtils;
import com.qihua.util.IdUtils;

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
@RequestMapping("/console/product/img-block")
public class ProductImgBlockController {


  @Autowired
  private ProductService productService;

  @RequestMapping
  public String list(HttpServletRequest request, ProductQueryParameter queryParam) {
    try {
      request.setAttribute("queryParam", queryParam);
      request.setAttribute("productList", productService.find(queryParam));
      request.setAttribute("a", productService.findImgBlockByPosition(Constants.PRODUCT_IMG_BLOCK_POSISION_A));
      request.setAttribute("b", productService.findImgBlockByPosition(Constants.PRODUCT_IMG_BLOCK_POSISION_B));
      request.setAttribute("c", productService.findImgBlockByPosition(Constants.PRODUCT_IMG_BLOCK_POSISION_C));
      request.setAttribute("d", productService.findImgBlockByPosition(Constants.PRODUCT_IMG_BLOCK_POSISION_D));
      request.setAttribute("e", productService.findImgBlockByPosition(Constants.PRODUCT_IMG_BLOCK_POSISION_E));
      request.setAttribute("f", productService.findImgBlockByPosition(Constants.PRODUCT_IMG_BLOCK_POSISION_F));
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/console/product/img-block/display";
  }

  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody upload(MultipartHttpServletRequest request) {
    try {
      String position = request.getParameter("position");

      MultipartFile attachment = request.getFile("file");
      if (attachment != null && attachment.getSize() > 0) {
        FileMeta meta = new FileMeta();
        meta.setId(request.getParameter("productId"));
        meta.setName(IdUtils.uuid2());
        meta.setSize(attachment.getSize());
        meta.setExtension(StringUtils.getFilenameExtension(attachment.getOriginalFilename()));
        meta.setBytes(attachment.getBytes());
        meta.setUrl(Constants.DEPLOY_URL + Constants.PRODUCT_IMG_UPLOAD_DIR);

        return new JSONResponseBody(productService.uploadImgBlock(meta, position));
      }
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody();
  }

  @RequestMapping(value = "/remove", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody removeImg(String imgName) {
    try {
      productService.removeImgBlock(imgName);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody();
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody update(String imgName, String productId) {
    try {
      productService.updateProductId(imgName, productId);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody();
  }
}
