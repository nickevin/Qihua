package com.qihua.console.order;

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
 * @author aopfilter@163.com
 * @since 2014-09-20
 * @version 1.0
 * @see
 */
@Slf4j
@Controller
@RequestMapping("/console/order")
public class OrderController {


  @Autowired
  private OrderService orderService;

  @RequestMapping
  public String search(HttpServletRequest request, OrderQueryParameter queryParam) {
    try {
      request.setAttribute("queryParam", queryParam);
      request.setAttribute("pageModel", orderService.search(queryParam));
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/console/order/list";
  }

  @RequestMapping(value = "/display")
  public String display(HttpServletRequest request, @RequestParam(required = false) String orderId) {
    Order order = new Order();

    try {
      if (orderId == null) {
        // ...
      } else {
        order = orderService.find(orderId);
      }

      request.setAttribute("order", order);
    } catch (Exception e) {
      log.error(ExceptionUtils.getStackTraceAsString(e));

      e.printStackTrace();
    }

    return "/console/order/display";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public @ResponseBody JSONResponseBody save(Order order) {
    try {
      order = orderService.save(order);
    } catch (Exception e) {
      e.printStackTrace();

      return new JSONResponseBody(false, e.getMessage());
    }

    return new JSONResponseBody(true, order.getOrderId());
  }

}
