package com.qihua.front.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qihua.front.member.Member;
import com.qihua.front.product.Product;
import com.qihua.util.IdUtils;

@Service
public class CartService {

  @Autowired
  private CartRepository cartRepository;

  public Cart add(final Member member, final Product product) throws Exception {
    Cart cart = null;

    try {
      cart = exists(member, product);
    } catch (EmptyResultDataAccessException e) {
      cart = new Cart();
      cart.setCartId(IdUtils.uuid2());
      cart.setMemberId(member.getMemberId());
      cart.setProductId(product.getProductId());
      cart.setProductName(product.getProductName());
      cart.setProductImgName(product.getImage().getName() + "." + product.getImage().getExtension());
      cart.setScore(product.getScore());
      cart.setQuantity(1);

      return cartRepository.insert(cart);
    }

    cart.setQuantity(cart.getQuantity() + 1);
    cart.setScore(cart.getScore() + product.getScore());

    return cartRepository.update(cart);
  }

  private Cart exists(final Member member, final Product product) throws Exception {
    return cartRepository.select(member.getMemberId(), product.getProductId());
  }

  public List<Cart> findByMember(final Member member) throws Exception {
    return findByMemberId(member.getMemberId());
  }

  public List<Cart> findByMemberId(final String memberId) throws Exception {
    return cartRepository.selectByMemberId(memberId);
  }

  public void remove(final String cartId) {
    cartRepository.delete(cartId);
  }

  public long calcScore(final Member member) {
    try {
      return cartRepository.calcScore(member.getMemberId());
    } catch (EmptyResultDataAccessException e) {
      return 0;
    }
  }

  public void clear(final String memberId) {
    cartRepository.deleteByMemberId(memberId);
  }

  public void updateQuantity(final Cart cart) {
    cartRepository.updateQuantity(cart);
  }

}
