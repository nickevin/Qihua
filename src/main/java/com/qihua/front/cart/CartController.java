package com.qihua.front.cart;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.qihua.common.Constants;
import com.qihua.common.JSONResponseBody;
import com.qihua.front.member.Member;
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
@RequestMapping("/cart")
public class CartController {


  @Autowired
  private CartService cartService;

  @Autowired
  private ProductService productService;

  @RequestMapping
  public String list(HttpServletRequest request) {
    try {
      Member session = (Member) WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER);

      request.setAttribute("cartList", cartService.findByMember(session));
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "/front/cart";
  }

  @RequestMapping(value = "/add")
  public String add(ModelMap model, HttpServletRequest request, String productId) {
    try {
      Member session = (Member) WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER);

      cartService.add(session, productService.find(productId));

      model.put("result", true);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      model.put("result", false);
      model.put("data", "加入购物车失败，请联系网站管理员。");
    }

    if (request.getRequestURI().endsWith(".json")) {
      return "/cart";
    }

    return "redirect:/cart";
  }

  @RequestMapping(value = "/update-quantity", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody updateQuantity(HttpServletRequest request, Cart cart) {
    try {
      Member session = (Member) WebUtils.getSessionAttribute(request, Constants.SESSION_MEMBER);
      cart.setMemberId(session.getMemberId());

      cartService.updateQuantity(cart);
    } catch (Exception e) {
      e.printStackTrace();

      return new JSONResponseBody(false, "商品数量更新失败，请联系网站管理员。");
    }

    return new JSONResponseBody();
  }

  @RequestMapping(value = "/remove", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody remove(HttpServletRequest request, String cartId) {
    try {
      cartService.remove(cartId);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody();
  }

}
