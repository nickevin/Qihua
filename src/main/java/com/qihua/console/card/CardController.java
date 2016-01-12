package com.qihua.console.card;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qihua.common.Constants;
import com.qihua.common.FileMeta;
import com.qihua.common.JSONResponseBody;
import com.qihua.exception.MultipleObjectException;
import com.qihua.util.DateUtils;
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
@RequestMapping("/console/card")
public class CardController {


  @Autowired
  private CardService cardService;

  // @RequestMapping
  // public String list(HttpServletRequest request, CardQueryParameter queryParam) {
  // try {
  // request.setAttribute("queryParam", queryParam);
  // request.setAttribute("cardList", cardService.find(queryParam));
  // } catch (Exception e) {
  // log.error(ExceptionUtils.getStackTraceAsString(e));
  //
  // e.printStackTrace();
  // }
  //
  // return "/console/card/list";
  // }

  @RequestMapping
  public String search(HttpServletRequest request, CardQueryParameter queryParam) {
    try {
      request.setAttribute("queryParam", queryParam);
      request.setAttribute("pageModel", cardService.search(queryParam));
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/console/card/list";
  }

  @RequestMapping(value = "/display")
  public String display(HttpServletRequest request, @RequestParam(required = false) String cardId) {
    Card card = new Card();

    try {
      if (cardId == null) {
        // ...
      } else {
        card = cardService.find(cardId);
      }

      request.setAttribute("card", card);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/console/card/display";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody save(Card card) {
    try {
      card = cardService.save(card);
    } catch (MultipleObjectException e) {
      return new JSONResponseBody(false, "此充值卡号已经登记过，请确认。");
    } catch (Exception e) {
      e.printStackTrace();

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody(true, card.getCardId());
  }

  @RequestMapping(value = "/remove", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody remove(HttpServletRequest request, String cardId) {
    try {
      cardService.remove(cardId);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody();
  }

  @RequestMapping(value = "/upload")
  public String upload(HttpServletRequest request) {
    request.setAttribute("uploadList", cardService.findUpload());

    return "/console/card/upload";
  }

  @RequestMapping(value = "/import", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody _import(MultipartHttpServletRequest request) {
    int count = 0;

    try {
      MultipartFile attachment = request.getFile("file");
      if (attachment != null && attachment.getSize() > 0) {
        String filename = DateUtils.currentDate() + "_" + IdUtils.uuid2() + "."
            + StringUtils.getFilenameExtension(attachment.getOriginalFilename());

        FileMeta meta = new FileMeta();
        meta.setName(filename);
        meta.setSize(attachment.getSize());
        meta.setType(attachment.getContentType());
        meta.setBytes(attachment.getBytes());
        meta.setUrl(Constants.DEPLOY_URL + Constants.CARD_FILE_UPLOAD_DIR + filename);

        // FileCopyUtils.copy(meta.getBytes(), new FileOutputStream(meta.getUrl()));

        // BCSUtils.putObject(filename, meta.getBytes());

        count = cardService.save(meta);
      }
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, "请确认充值卡数据是否有效。");
    }

    return new JSONResponseBody(count);
  }

  @RequestMapping(value = "/download")
  public void download(HttpServletRequest request, HttpServletResponse response,
      @RequestParam(required = true) Long uploadId) throws Exception {
    if (uploadId == null) {
      return;
    }

    CardUpload upload = cardService.findUpload(uploadId);

    try {
      // String url = Constants.DEPLOY_URL + Constants.CARD_UPLOAD_DIR + upload.getFilename();
      // FileUtils.download(request, response, url);

      // BCSUtils.download(request, response, BCSUtils.getObject(upload.getFilename()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
