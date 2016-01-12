package com.qihua.console.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qihua.common.PageModel;
import com.qihua.exception.NullObjectException;
import com.qihua.front.card.CardService;
import com.qihua.front.cart.CartService;
import com.qihua.front.member.MemberService;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private CartService cartService;

    @Autowired
    private CardService cardService;

    @Autowired
    private MemberService memberService;

    public PageModel<Order> search(OrderQueryParameter queryParam) {
        return orderDAO.selectByPagination(queryParam);
    }

    public Order find(String orderId) throws Exception {
        try {
            return orderDAO.select(orderId);
        } catch (EmptyResultDataAccessException e) {
            throw new NullObjectException();
        }
    }

    public Order save(Order order) throws Exception {
        if (order.getOrderId() == null) {
            return orderDAO.insert(order);
        }

        return orderDAO.update(order);
    }

    public void remove(String orderId) {
        orderDAO.delete(orderId);
    }

}
