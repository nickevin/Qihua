package com.qihua.front.member;

import java.sql.Timestamp;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.qihua.common.CustomBeanPropertyRowMapper;
import com.qihua.common.GenericDAO;

@Repository
public class MemberDAO extends GenericDAO<Member, String> {

  public Member selectCredential(Member member) {
    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.member_id,                   ");
    sql.append("        tb.member_name,                 ");
    sql.append("        tb.password,                    ");
    sql.append("        tb.mobile,                      ");
    sql.append("        tb.email,                       ");
    sql.append("        tb.address,                     ");
    sql.append("        tb.score                        ");
    sql.append("   FROM t_member tb                     ");
    sql.append("  WHERE (tb.mobile = ? OR tb.email = ?) ");
    sql.append("   AND LOWER(tb.PASSWORD) = LOWER(?)    ");

    return jdbcTemplate.queryForObject(sql.toString(),
        new Object[] {member.getMobile(), member.getMobile(), member.getPassword()},
        new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR}, new CustomBeanPropertyRowMapper<Member>(Member.class));
  }

  public Member selectByMobile(String mobile) {
    String sql = "SELECT tb.mobile FROM t_member tb WHERE tb.mobile = ? ";

    Object[] params = new Object[] {mobile};
    int[] types = new int[] {Types.VARCHAR};

    return jdbcTemplate.queryForObject(sql, params, types, new CustomBeanPropertyRowMapper<Member>(Member.class));
  }

  public Member selectByEmail(String email) {
    String sql = "SELECT tb.member_id, tb.email, tb.member_name FROM t_member tb WHERE tb.email = ? ";

    Object[] params = new Object[] {email};
    int[] types = new int[] {Types.VARCHAR};

    return jdbcTemplate.queryForObject(sql, params, types, new CustomBeanPropertyRowMapper<Member>(Member.class));
  }

  public Member selectByMemberName(String memberName) {
    String sql = "SELECT tb.member_id, tb.member_name FROM t_member tb WHERE tb.member_name = ? ";

    Object[] params = new Object[] {memberName};
    int[] types = new int[] {Types.VARCHAR};

    return jdbcTemplate.queryForObject(sql, params, types, new CustomBeanPropertyRowMapper<Member>(Member.class));
  }

  public void updatePassword(Member member) {
    StringBuilder sql = new StringBuilder();
    sql.append("UPDATE t_member SET password = ?, update_time = ? WHERE member_id = ?");

    Object[] params =
        new Object[] {member.getPassword(), new Timestamp(System.currentTimeMillis()), member.getMemberId()};
    int[] types = new int[] {Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR};

    jdbcTemplate.update(sql.toString(), params, types);
  }

  public void updateScore(Member member) {
    StringBuilder sql = new StringBuilder();
    sql.append("UPDATE t_member SET score = ?, update_time = ? WHERE member_id = ?");

    Object[] params = new Object[] {member.getScore(), new Timestamp(System.currentTimeMillis()), member.getMemberId()};
    int[] types = new int[] {Types.INTEGER, Types.TIMESTAMP, Types.VARCHAR};

    jdbcTemplate.update(sql.toString(), params, types);
  }

}
