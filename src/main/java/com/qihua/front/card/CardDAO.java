package com.qihua.front.card;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.qihua.common.GenericDAO;
import com.qihua.front.member.Member;
import com.qihua.front.member.MemberCard;

@Repository
public class CardDAO extends GenericDAO<Card, String> {

  public List<MemberCard> select(Member member) {
    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT *                   ");
    sql.append("   FROM t_member_card mc    ");
    sql.append("  WHERE mc.member_id = ?    ");

    Object[] params = new Object[] {member.getMemberId()};
    int[] types = new int[] {Types.VARCHAR};

    return jdbcTemplate.query(sql.toString(), params, types, new BeanPropertyRowMapper<MemberCard>(MemberCard.class));
  }

  public Card selectByCardNo(String cardNo) {
    String sql = "SELECT * FROM t_card tb WHERE tb.card_no = ?  ";

    Object[] params = new Object[] {cardNo};
    int[] types = new int[] {Types.VARCHAR};

    return jdbcTemplate.queryForObject(sql, params, types, new BeanPropertyRowMapper<Card>(Card.class));
  }

  public Card select(String cardNo, String password) {
    String sql = "SELECT * FROM t_card tb WHERE tb.card_no = ? AND tb.password = ? ";

    Object[] params = new Object[] {cardNo, password};
    int[] types = new int[] {Types.VARCHAR, Types.VARCHAR};

    return jdbcTemplate.queryForObject(sql, params, types, new BeanPropertyRowMapper<Card>(Card.class));
  }

  public void insertMemberCard(MemberCard newItem) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" INSERT INTO t_member_card  ");
    sql.append("   (member_id,              ");
    sql.append("    member_name,            ");
    sql.append("    card_no,                ");
    sql.append("    card_password,          ");
    sql.append("    score,                  ");
    sql.append("    status,                 ");
    sql.append("    insert_time,            ");
    sql.append("    update_time)            ");
    sql.append(" VALUES                     ");
    sql.append("   (?,                      ");
    sql.append("    ?,                      ");
    sql.append("    ?,                      ");
    sql.append("    ?,                      ");
    sql.append("    ?,                      ");
    sql.append("    ?,                      ");
    sql.append("    ?,                      ");
    sql.append("    ?)                      ");

    params.add(newItem.getMemberId());
    types.add(Types.VARCHAR);

    params.add(newItem.getMemberName());
    types.add(Types.VARCHAR);

    params.add(newItem.getCardNo());
    types.add(Types.VARCHAR);

    params.add(newItem.getCardPassword());
    types.add(Types.VARCHAR);

    params.add(newItem.getScore());
    types.add(Types.BIGINT);

    params.add(newItem.getStatus());
    types.add(Types.INTEGER);

    params.add(newItem.getInsertTime());
    types.add(Types.TIMESTAMP);

    params.add(newItem.getUpdateTime());
    types.add(Types.TIMESTAMP);

    jdbcTemplate.update(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])));

  }

  public int calcScore(String memberId) {
    StringBuilder sql = new StringBuilder();
    sql.append("   SELECT sum(score)        ");
    sql.append("     FROM t_member_card     ");
    sql.append("    WHERE member_id = ?     ");
    sql.append(" GROUP BY member_id         ");

    Object[] params = new Object[] {memberId};
    int[] types = new int[] {Types.VARCHAR};

    return jdbcTemplate.queryForObject(sql.toString(), params, types, Integer.class);
  }
}
