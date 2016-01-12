package com.qihua.front.order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.qihua.common.Constants;
import com.qihua.common.JSONResponseBody;
import com.qihua.exception.IllegalValueException;
import com.qihua.front.cart.CartService;
import com.qihua.front.member.Member;

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
@RequestMapping("/order")
public class OrderController {


  @Autowired
  private OrderService orderService;

  @Autowired
  private CartService cartService;

  @RequestMapping(value = "/generate", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody generate(HttpServletRequest request) {
    try {
      Member session = (Member) WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER);

      orderService.check(session);
    } catch (IllegalValueException e) {
      return new JSONResponseBody(false, e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();

      return new JSONResponseBody(false, "订单生成失败，请联系网站管理员。");
    }

    return new JSONResponseBody();
  }

  @RequestMapping(value = {"/display"})
  public String display(HttpServletRequest request) {
    try {
      Member session = (Member) WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER);

      request.setAttribute("lastOrder", orderService.findLastOrder(session));
      request.setAttribute("cartList", cartService.findByMember(session));
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "/front/order";
  }

  @RequestMapping(value = "/confirm", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody confirm(HttpServletRequest request, Order order) {
    try {
      Member session = (Member) WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER);

      orderService.check(session);

      orderService.confirm(session, order);

      WebUtils.setSessionAttribute(request, Constants.SESSION_MEMBER, session);
      WebUtils.setSessionAttribute(request, "__changed__", "");
    } catch (IllegalValueException e) {
      return new JSONResponseBody(false, e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();

      return new JSONResponseBody(false, "订单确认失败，请联系网站管理员。");
    }

    return new JSONResponseBody();
  }

}
