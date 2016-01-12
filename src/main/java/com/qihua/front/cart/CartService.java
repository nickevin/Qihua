package com.qihua.front.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qihua.front.member.Member;
import com.qihua.front.product.Product;

@Service
public class CartService {

    @Autowired
    private CartDAO cartDAO;

    public Cart add(Member member, Product product) throws Exception {
        Cart cart = null;

        try {
            cart = exists(member, product);
        } catch (EmptyResultDataAccessException e) {
            cart = new Cart();
            cart.setMemberId(member.getMemberId());
            cart.setProductId(product.getProductId());
            cart.setProductName(product.getProductName());
            cart.setProductImgName(product.getImage().getName() + "." + product.getImage().getExtension());
            cart.setScore(product.getScore());
            cart.setQuantity(1);

            return cartDAO.insert(cart);
        }

        cart.setQuantity(cart.getQuantity() + 1);
        cart.setScore(cart.getScore() + product.getScore());

        return cartDAO.update(cart);
    }

    private Cart exists(Member member, Product product) throws Exception {
        return cartDAO.select(member.getMemberId(), product.getProductId());
    }

    public List<Cart> findByMember(Member member) throws Exception {
        return findByMemberId(member.getMemberId());
    }

    public List<Cart> findByMemberId(String memberId) throws Exception {
        return cartDAO.selectByMemberId(memberId);
    }

    public void remove(String cartId) {
        cartDAO.delete(cartId);
    }

    public long calcScore(Member member) {
        try {
            return cartDAO.calcScore(member.getMemberId());
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public void clear(String memberId) {
        cartDAO.deleteByMemberId(memberId);
    }

    public void updateQuantity(Cart cart) {
        cartDAO.updateQuantity(cart);
    }

}
