package com.qihua.console.order;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.qihua.common.Constants;
import com.qihua.common.repository.GenericBeanPropertyRowMapper;
import com.qihua.common.repository.JdbcRepository;
import com.qihua.common.repository.PageModel;

@Repository
public class OrderRepository extends JdbcRepository<Order, String> {

  public PageModel<Order> selectByPagination(OrderQueryParameter queryParam) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.order_id,            ");
    sql.append("        tb.order_no,            ");
    sql.append("        tb.recipient,           ");
    sql.append("        tb.mobile,              ");
    sql.append("        tb.email,               ");
    sql.append("        tb.zip,                 ");
    sql.append("        tb.address,             ");
    sql.append("        tb.remarks,             ");
    sql.append("        tb.status,              ");
    sql.append("        tb.insert_time          ");
    sql.append("   FROM t_order tb              ");
    sql.append("  WHERE 1 = 1                   ");

    String orderNo = queryParam.getOrderNo();
    if (StringUtils.isNotEmpty(orderNo)) {
      sql.append(" AND tb.order_no LIKE ?");
      params.add(orderNo + "%");
      types.add(Types.VARCHAR);
    }

    String recipient = queryParam.getRecipient();
    if (StringUtils.isNotEmpty(recipient)) {
      sql.append(" AND tb.recipient LIKE ?");
      params.add(recipient + "%");
      types.add(Types.VARCHAR);
    }

    String mobile = queryParam.getMobile();
    if (StringUtils.isNotEmpty(mobile)) {
      sql.append(" AND tb.mobile LIKE ?");
      params.add(mobile + "%");
      types.add(Types.VARCHAR);
    }

    String email = queryParam.getEmail();
    if (StringUtils.isNotEmpty(email)) {
      sql.append(" AND tb.email LIKE ?");
      params.add(email + "%");
      types.add(Types.VARCHAR);
    }

    String address = queryParam.getAddress();
    if (StringUtils.isNotEmpty(address)) {
      sql.append(" AND tb.address LIKE ?");
      params.add("%" + address + "%");
      types.add(Types.VARCHAR);
    }

    String remarks = queryParam.getRemarks();
    if (StringUtils.isNotEmpty(remarks)) {
      sql.append(" AND tb.remarks LIKE ?");
      params.add("%" + remarks + "%");
      types.add(Types.VARCHAR);
    }

    String status = queryParam.getStatus();
    if (StringUtils.isNotEmpty(status)) {
      sql.append(" AND tb.status = ?");
      params.add(status);
      types.add(Types.INTEGER);
    }

    String startDate = queryParam.getStartDate();
    if (!StringUtils.isEmpty(startDate)) {
      sql.append(" AND DATE_FORMAT(insert_time, '%Y-%m-%d') >= DATE_FORMAT(?, '%Y-%m-%d')");
      params.add(startDate);
      types.add(Types.VARCHAR);
    }

    String endDate = queryParam.getEndDate();
    if (!StringUtils.isEmpty(endDate)) {
      sql.append(" AND DATE_FORMAT(insert_time, '%Y-%m-%d') < date_add(str_to_date(?, '%Y-%m-%d'), INTERVAL 1 DAY)");
      params.add(endDate);
      types.add(Types.VARCHAR);
    }

    long count = count(sql.toString(), types, params);

    sql.append(" LIMIT ?, ? ");

    int pageNo = 0;
    if (queryParam.getPageNo() <= 0 || count == 0) {
      pageNo = 1;
    } else if (queryParam.getPageNo() * Constants.PAGE_SIZE > count) {
      pageNo = (int) Math.ceil(count / (double) Constants.PAGE_SIZE);
    } else {
      pageNo = queryParam.getPageNo();
    }

    types.add(Types.INTEGER);
    params.add((pageNo - 1) * Constants.PAGE_SIZE);

    types.add(Types.INTEGER);
    params.add(Constants.PAGE_SIZE);

    List<Order> result = jdbcTemplate.query(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new GenericBeanPropertyRowMapper<Order>(Order.class));

    return new PageModel<Order>(result, new PageRequest(pageNo, Constants.PAGE_SIZE), count);
  }

}
