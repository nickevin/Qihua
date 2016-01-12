package com.qihua.front.card;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.qihua.common.Constants;
import com.qihua.common.JSONResponseBody;
import com.qihua.exception.NullObjectException;
import com.qihua.exception.ObjectExistsException;
import com.qihua.front.member.Member;
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
@RequestMapping("/card")
public class CardController {


  @Autowired
  private CardService cardService;

  @RequestMapping
  public String list(HttpServletRequest request) {
    try {
      Member session = (Member) WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER);

      request.setAttribute("cardList", cardService.findByMember(session));
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/front/member/card";
  }

  @RequestMapping(value = "/recharge", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody recharge(HttpServletRequest request, Card card) {
    try {
      Member session = (Member) WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER);

      card = cardService.recharge(session, card);

      WebUtils.setSessionAttribute(request, Constants.SESSION_MEMBER, session);
      WebUtils.setSessionAttribute(request, "__changed__", "");
    } catch (ObjectExistsException e) {
      return new JSONResponseBody(false, "此卡已充过值，请确认。");
    } catch (NullObjectException e) {
      return new JSONResponseBody(false, "此卡号和密码不匹配，请确认。");
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, "系统异常，请联系管理员。");
    }

    return new JSONResponseBody(true, card.getScore());
  }

}
