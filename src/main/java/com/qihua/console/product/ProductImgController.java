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
@RequestMapping("/console/product/img")
public class ProductImgController {


  @Autowired
  private ProductService productService;

  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody upload(MultipartHttpServletRequest request) {
    try {
      MultipartFile attachment = request.getFile("file");
      if (attachment != null && attachment.getSize() > 0) {
        FileMeta meta = new FileMeta();
        meta.setId(request.getParameter("productId"));
        meta.setName(IdUtils.uuid2());
        meta.setSize(attachment.getSize());
        meta.setExtension(StringUtils.getFilenameExtension(attachment.getOriginalFilename()));
        meta.setBytes(attachment.getBytes());
        meta.setUrl(Constants.DEPLOY_URL + Constants.PRODUCT_IMG_UPLOAD_DIR);

        // File folder = new File(Constants.DEPLOY_URL + Constants.PRODUCT_UPLOAD_DIR );
        // folder.mkdir();

        return new JSONResponseBody(productService.uploadImg(meta));
      }
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody();
  }

  @RequestMapping(value = "/recommend/upload", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody uploadRecommend(MultipartHttpServletRequest request) {
    try {
      MultipartFile attachment = request.getFile("file");
      if (attachment != null && attachment.getSize() > 0) {
        FileMeta meta = new FileMeta();
        meta.setId(request.getParameter("productId"));
        meta.setName(IdUtils.uuid2());
        meta.setSize(attachment.getSize());
        meta.setExtension(StringUtils.getFilenameExtension(attachment.getOriginalFilename()));
        meta.setBytes(attachment.getBytes());
        meta.setUrl(Constants.DEPLOY_URL + Constants.PRODUCT_IMG_UPLOAD_DIR);

        // File folder = new File(Constants.DEPLOY_URL + Constants.PRODUCT_UPLOAD_DIR );
        // folder.mkdir();

        return new JSONResponseBody(productService.uploadRecommendImg(meta));
      }
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody();
  }

  @RequestMapping(value = "/flash-sale/upload", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody uploadFlashSale(MultipartHttpServletRequest request) {
    try {
      MultipartFile attachment = request.getFile("file");
      if (attachment != null && attachment.getSize() > 0) {
        FileMeta meta = new FileMeta();
        meta.setId(request.getParameter("productId"));
        meta.setName(IdUtils.uuid2());
        meta.setSize(attachment.getSize());
        meta.setExtension(StringUtils.getFilenameExtension(attachment.getOriginalFilename()));
        meta.setBytes(attachment.getBytes());
        meta.setUrl(Constants.DEPLOY_URL + Constants.PRODUCT_IMG_UPLOAD_DIR);

        // File folder = new File(Constants.DEPLOY_URL + Constants.PRODUCT_UPLOAD_DIR );
        // folder.mkdir();

        return new JSONResponseBody(productService.uploadFlashSaleImg(meta));
      }
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody();
  }

  @RequestMapping(value = "/remove", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody removeImg(HttpServletRequest request, String imgName) {
    try {
      productService.removeImg(imgName);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody();
  }

  @RequestMapping(value = "/recommend/remove", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody removeRecommend(HttpServletRequest request, String imgName) {
    try {
      productService.removeRecommendImg(imgName);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody();
  }

  @RequestMapping(value = "/flash-sale/remove", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody removeFlashSale(HttpServletRequest request, String imgName) {
    try {
      productService.removeFlashSaleImg(imgName);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody();
  }
}
