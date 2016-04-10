package com.qihua.front.cart;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Repository;

import com.qihua.common.repository.GenericBeanPropertyRowMapper;
import com.qihua.common.repository.JdbcRepository;

@Repository
public class CartRepository extends JdbcRepository<Cart, String> {

  public List<Cart> selectByMemberId(String memberId) {
    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.cart_id,         ");
    sql.append("        tb.member_id,       ");
    sql.append("        tb.product_id,      ");
    sql.append("        tb.product_name,    ");
    sql.append("        tb.product_img_name,");
    sql.append("        tb.score,           ");
    sql.append("        tb.quantity         ");
    sql.append("   FROM t_cart tb           ");
    sql.append("  WHERE tb.member_id = ?    ");

    return jdbcTemplate.query(sql.toString(), new Object[] {memberId}, new int[] {Types.VARCHAR},
        new GenericBeanPropertyRowMapper<Cart>(Cart.class));
  }

  public Cart select(String memberId, String productId) {
    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.cart_id,         ");
    sql.append("        tb.score,           ");
    sql.append("        tb.quantity         ");
    sql.append("   FROM t_cart tb           ");
    sql.append("  WHERE tb.member_id = ?    ");
    sql.append("    AND tb.product_id = ?   ");

    return jdbcTemplate.queryForObject(sql.toString(), new Object[] {memberId, productId},
        new int[] {Types.VARCHAR, Types.VARCHAR}, new GenericBeanPropertyRowMapper<Cart>(Cart.class));
  }

  public long calcScore(String memberId) {
    StringBuilder sql = new StringBuilder();
    sql.append("   SELECT sum(score * quantity) ");
    sql.append("     FROM t_cart                ");
    sql.append("    WHERE member_id = ?         ");
    sql.append(" GROUP BY member_id             ");

    Object[] params = new Object[] {memberId};
    int[] types = new int[] {Types.VARCHAR};

    return jdbcTemplate.queryForObject(sql.toString(), params, types, Long.class);
  }

  public void deleteByMemberId(String memberId) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder("DELETE FROM t_cart WHERE member_id = ? ");

    params.add(memberId);
    types.add(Types.VARCHAR);

    jdbcTemplate.update(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])));
  }

  public void updateQuantity(Cart cart) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder("UPDATE t_cart SET quantity = ? WHERE cart_id = ? ");

    params.add(cart.getQuantity());
    types.add(Types.BIGINT);

    params.add(cart.getCartId());
    types.add(Types.VARCHAR);

    jdbcTemplate.update(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])));
  }

}
