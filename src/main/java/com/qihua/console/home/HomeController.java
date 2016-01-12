package com.qihua.console.home;

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
 * @author aopfilter@163.com
 * @since 2014-09-20
 * @version 1.0
 * @see
 */
@Slf4j
@Controller
@RequestMapping("/console/home")
public class HomeController {


  @Autowired
  private HomeService homeService;

  @RequestMapping
  public String display(HttpServletRequest request) throws Exception {
    try {
      request.setAttribute("centerImgList", homeService.findImgList(Constants.HOME_IMG_POSISION_CENTER));
      request.setAttribute("bottomImgList", homeService.findImgList(Constants.HOME_IMG_POSISION_BOTTOM));
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));
    }

    return "/console/home/display";
  }

  @RequestMapping(value = "/img/upload", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody uploadCenter(MultipartHttpServletRequest request) {
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

        return new JSONResponseBody(homeService.uploadImg(meta, position));
      }
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody();
  }

  @RequestMapping(value = "/img/remove", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody removeRecommend(HttpServletRequest request, String imgName) {
    try {
      homeService.removeImg(imgName);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody();
  }

}
