package com.qihua.console.member;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.qihua.common.GenericDAO;

@Repository
public class MemberDAO extends GenericDAO<Member, String> {

  public List<Member> select(MemberQueryParameter queryParam) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.member_id,          ");
    sql.append("        tb.member_name,        ");
    sql.append("        tb.password,           ");
    sql.append("        tb.mobile,             ");
    sql.append("        tb.email,              ");
    sql.append("        tb.insert_time         ");
    sql.append("   FROM t_member tb            ");
    sql.append("  WHERE 1 = 1                  ");

    String memberName = queryParam.getMemberName();
    if (StringUtils.isNotEmpty(memberName)) {
      sql.append(" AND tb.member_name = ?");
      params.add(memberName);
      types.add(Types.VARCHAR);
    }

    String mobile = queryParam.getMobile();
    if (StringUtils.isNotEmpty(mobile)) {
      sql.append(" AND tb.mobile = ?");
      params.add(mobile);
      types.add(Types.VARCHAR);
    }

    String email = queryParam.getEmail();
    if (StringUtils.isNotEmpty(email)) {
      sql.append(" AND tb.email = ?");
      params.add(email);
      types.add(Types.VARCHAR);
    }

    return jdbcTemplate.query(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new BeanPropertyRowMapper<Member>(Member.class));
  }

}
