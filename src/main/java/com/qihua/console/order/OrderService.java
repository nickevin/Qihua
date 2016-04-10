package com.qihua.console.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qihua.common.repository.PageModel;
import com.qihua.exception.NullObjectException;
import com.qihua.front.card.CardService;
import com.qihua.front.cart.CartService;
import com.qihua.front.member.MemberService;

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

  public PageModel<Order> search(final OrderQueryParameter queryParam) {
    return orderRepository.selectByPagination(queryParam);
  }

  public Order find(final String orderId) throws Exception {
    try {
      return orderRepository.selectOne(orderId);
    } catch (EmptyResultDataAccessException e) {
      throw new NullObjectException();
    }
  }

  public Order save(final Order order) throws Exception {
    if (order.getOrderId() == null) {
      return orderRepository.insert(order);
    }

    return orderRepository.update(order);
  }

  public void remove(final String orderId) {
    orderRepository.delete(orderId);
  }

}
