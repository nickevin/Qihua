package com.qihua.front.order;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qihua.exception.IllegalValueException;
import com.qihua.front.card.CardService;
import com.qihua.front.cart.Cart;
import com.qihua.front.cart.CartService;
import com.qihua.front.member.Member;
import com.qihua.front.member.MemberService;
import com.qihua.util.IdUtils;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private CartService cartService;

  @Autowired
  private CardService cardService;

  @Autowired
  private MemberService memberService;

  public List<Order> findByMember(final Member member) {
    List<Order> orderList = orderRepository.selectByMemberId(member.getMemberId());
    for (Order order : orderList) {
      order.setItems(findItems(order));
    }

    return orderList;
  }

  private List<OrderItem> findItems(final Order order) {
    return orderRepository.selectOrderItem(order.getOrderId());
  }

  public void remove(final String orderId) {
    orderRepository.delete(orderId);
  }

  @Transactional(rollbackFor = Exception.class)
  public void confirm(final Member member, final Order order) throws Exception {
    order.setOrderId(IdUtils.uuid2());
    order.setMemberId(member.getMemberId());
    order.setOrderNo(generateOrderNo());
    orderRepository.insert(order);

    long amount = insertOrderItems(order);

    member.setScore(member.getScore() - amount);
    memberService.updateScore(member);

    cartService.clear(order.getMemberId());
  }

  public static synchronized String generateOrderNo() throws Exception {
    return new DateTime().toString("yyyyMMddHHmmssSSS");
  }

  private long insertOrderItems(final Order order) throws Exception {
    long amount = 0;

    List<Cart> cartList = cartService.findByMemberId(order.getMemberId());
    for (Cart cart : cartList) {
      OrderItem item = new OrderItem();
      item.setOrderId(order.getOrderId());
      item.setProductId(cart.getProductId());
      item.setProductName(cart.getProductName());
      item.setProductImgName(cart.getProductImgName());
      item.setScore(cart.getScore());
      item.setQuantity(cart.getQuantity());

      orderRepository.insertOrderItem(item);

      amount += cart.getQuantity() * cart.getScore();
    }

    return amount;
  }

  public void check(final Member member) throws Exception {
    long memberScore = member.getScore();
    long cartScore = cartService.calcScore(member);

    if (memberScore < cartScore) {
      throw new IllegalValueException("订单礼券不足，您当前礼券余额：" + memberScore);
    }
  }

  public Order findLastOrder(final Member member) {
    try {
      return orderRepository.selectLastOrder(member.getMemberId());
    } catch (EmptyResultDataAccessException e) {
      return new Order();
    }
  }

}
