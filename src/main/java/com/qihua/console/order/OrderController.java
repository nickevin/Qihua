package com.qihua.console.order;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
  public String search(final HttpServletRequest request, final OrderQueryParameter queryParam) {
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
  public String display(final HttpServletRequest request, @RequestParam(required = false) final String orderId) {
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
  public @ResponseBody ResponseEntity<HashMap<String, Object>> save(final Order order) {
    final Order newOrder;

    try {
      newOrder = orderService.save(order);
    } catch (final Exception e) {
      e.printStackTrace();

      return new ResponseEntity<HashMap<String, Object>>(new HashMap<String, Object>() {
        {
          put("result", false);
          put("data", e.getMessage());
        }
      }, HttpStatus.OK);
    }

    return new ResponseEntity<HashMap<String, Object>>(new HashMap<String, Object>() {
      {
        put("result", true);
        put("data", newOrder.getOrderId());
      }
    }, HttpStatus.OK);
  }

}
