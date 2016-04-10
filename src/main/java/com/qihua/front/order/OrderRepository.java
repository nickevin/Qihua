package com.qihua.front.order;

import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.qihua.common.repository.GenericBeanPropertyRowMapper;
import com.qihua.common.repository.JdbcRepository;
import com.qihua.util.IdUtils;

@Repository
public class OrderRepository extends JdbcRepository<Order, String> {

  public List<Order> selectByMemberId(String memberId) {
    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.order_id,           ");
    sql.append("        tb.order_no,           ");
    sql.append("        tb.recipient,          ");
    sql.append("        tb.mobile,             ");
    sql.append("        tb.zip,                ");
    sql.append("        tb.address,            ");
    sql.append("        tb.remarks,            ");
    sql.append("        tb.status,             ");
    sql.append("        tb.insert_time         ");
    sql.append("   FROM t_order tb             ");
    sql.append("  WHERE tb.member_id = ?       ");
    sql.append("  ORDER BY tb.insert_time desc ");

    return jdbcTemplate.query(sql.toString(), new Object[] {memberId}, new int[] {Types.VARCHAR},
        new GenericBeanPropertyRowMapper<Order>(Order.class));
  }

  public Order select(String memberId, String productId) {
    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.cart_id,         ");
    sql.append("        tb.score,           ");
    sql.append("        tb.quantity         ");
    sql.append("   FROM t_cart tb           ");
    sql.append("  WHERE tb.member_id = ?    ");
    sql.append("    AND tb.product_id = ?   ");

    return jdbcTemplate.queryForObject(sql.toString(), new Object[] {memberId, productId},
        new int[] {Types.VARCHAR, Types.VARCHAR}, new GenericBeanPropertyRowMapper<Order>(Order.class));
  }

  public void insertOrderItem(OrderItem item) {
    StringBuilder sql = new StringBuilder();
    sql.append(" INSERT INTO t_order_item       ");
    sql.append("   (item_id,                    ");
    sql.append("    order_id,                   ");
    sql.append("    product_id,                 ");
    sql.append("    product_name,               ");
    sql.append("    product_img_name,           ");
    sql.append("    quantity,                   ");
    sql.append("    score)                      ");
    sql.append(" VALUES                         ");
    sql.append("   (?,                          ");
    sql.append("    ?,                          ");
    sql.append("    ?,                          ");
    sql.append("    ?,                          ");
    sql.append("    ?,                          ");
    sql.append("    ?,                          ");
    sql.append("    ?)                          ");

    jdbcTemplate.update(sql.toString(),
        new Object[] {IdUtils.uuid2(), item.getOrderId(), item.getProductId(), item.getProductName(),
            item.getProductImgName(), item.getQuantity(), item.getScore()},
        new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
            Types.INTEGER});
  }

  public List<OrderItem> selectOrderItem(String orderId) {
    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.item_id,             ");
    sql.append("        tb.product_id,          ");
    sql.append("        tb.product_name,        ");
    sql.append("        tb.quantity,            ");
    sql.append("        tb.score                ");
    sql.append("   FROM t_order_item tb         ");
    sql.append("  WHERE tb.order_id = ?         ");

    return jdbcTemplate.query(sql.toString(), new Object[] {orderId}, new int[] {Types.VARCHAR},
        new GenericBeanPropertyRowMapper<OrderItem>(OrderItem.class));
  }

  public Order selectLastOrder(String memberId) {
    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.*                            ");
    sql.append("   FROM t_order tb                      ");
    sql.append("  WHERE tb.order_id = (                 ");
    sql.append("           SELECT                       ");
    sql.append("               min(order_id) order_id   ");
    sql.append("           FROM                         ");
    sql.append("               t_order                  ");
    sql.append("           WHERE                        ");
    sql.append("               member_id = ?            ");
    sql.append("           GROUP BY                     ");
    sql.append("               member_id )              ");

    return jdbcTemplate.queryForObject(sql.toString(), new Object[] {memberId}, new int[] {Types.VARCHAR},
        new GenericBeanPropertyRowMapper<Order>(Order.class));
  }

}
