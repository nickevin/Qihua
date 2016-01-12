package com.qihua.console.card;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.qihua.common.Constants;
import com.qihua.common.CustomBeanPropertyRowMapper;
import com.qihua.common.GenericDAO;
import com.qihua.common.PageModel;
import com.qihua.util.IdUtils;

@Repository
public class CardDAO extends GenericDAO<Card, String> {

  public List<Card> select(CardQueryParameter queryParam) {
    return null;
  }

  public PageModel<Card> selectByPagination(CardQueryParameter queryParam) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.card_id,             ");
    sql.append("        tb.card_no,             ");
    sql.append("        tb.password,            ");
    sql.append("        tb.score,               ");
    sql.append("        tb.recharge_time,       ");
    sql.append("        tb.status,              ");
    sql.append("        tb.insert_time          ");
    sql.append("   FROM t_card tb               ");
    sql.append("  WHERE 1 = 1                   ");

    String cardNo = queryParam.getCardNo();
    if (StringUtils.isNotEmpty(cardNo)) {
      sql.append(" AND tb.card_no LIKE ?");
      params.add("%" + cardNo + "%");
      types.add(Types.VARCHAR);
    }

    String status = queryParam.getStatus();
    if (StringUtils.isNotEmpty(status)) {
      sql.append(" AND tb.status = ?");
      params.add(status);
      types.add(Types.INTEGER);
    }

    String password = queryParam.getPassword();
    if (StringUtils.isNotEmpty(password)) {
      sql.append(" AND tb.password LIKE ?");
      params.add("%" + password + "%");
      types.add(Types.VARCHAR);
    }

    String lowScore = queryParam.getLowScore();
    if (StringUtils.isNotEmpty(lowScore)) {
      sql.append("   AND tb.score >= ? ");
      params.add(lowScore);
      types.add(Types.INTEGER);
    }

    String highScore = queryParam.getHighScore();
    if (StringUtils.isNotEmpty(highScore)) {
      sql.append("   AND tb.score <= ? ");
      params.add(highScore);
      types.add(Types.INTEGER);
    }

    String startDate = queryParam.getStartDate();
    if (!StringUtils.isEmpty(startDate)) {
      sql.append(" AND DATE_FORMAT(recharge_time, '%Y-%m-%d') >= DATE_FORMAT(?, '%Y-%m-%d')");
      params.add(startDate);
      types.add(Types.VARCHAR);
    }

    String endDate = queryParam.getEndDate();
    if (!StringUtils.isEmpty(endDate)) {
      sql.append(" AND DATE_FORMAT(recharge_time, '%Y-%m-%d') < date_add(str_to_date(?, '%Y-%m-%d'), INTERVAL 1 DAY)");
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

    List<Card> result = jdbcTemplate.query(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new CustomBeanPropertyRowMapper<Card>(Card.class));

    return new PageModel<Card>(result, new PageRequest(pageNo, Constants.PAGE_SIZE), count);
  }

  public List<CardUpload> selectUpload() {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.upload_id,     ");
    sql.append("        tb.total,         ");
    sql.append("        tb.filename,      ");
    sql.append("        tb.insert_time    ");
    sql.append("   FROM t_card_upload tb  ");
    sql.append("  WHERE 1 = 1             ");

    return jdbcTemplate.query(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new BeanPropertyRowMapper<CardUpload>(CardUpload.class));
  }

  public Long insertUpload(CardUpload upload) {
    Long sequence = sequence("t_card_upload", "upload_id");

    StringBuilder sql = new StringBuilder();
    sql.append(" INSERT INTO t_card_upload      ");
    sql.append("   (upload_id,                  ");
    sql.append("    total,                      ");
    sql.append("    filename,                   ");
    sql.append("    status,                     ");
    sql.append("    insert_time,                ");
    sql.append("    update_time)                ");
    sql.append(" VALUES                         ");
    sql.append("   (?,                          ");
    sql.append("    ?,                          ");
    sql.append("    ?,                          ");
    sql.append("    ?,                          ");
    sql.append("    ?,                          ");
    sql.append("    ?)                          ");

    jdbcTemplate.update(sql.toString(),
        new Object[] {sequence, upload.getTotal(), upload.getFilename(), Constants.CARD_TYPE_ENABLE,
            upload.getInsertTime(), upload.getUpdateTime()},
        new int[] {Types.BIGINT, Types.BIGINT, Types.VARCHAR, Types.INTEGER, Types.TIMESTAMP, Types.TIMESTAMP});

    return sequence;
  }

  public void batchInsert(final List<Card> list) {
    StringBuilder sql = new StringBuilder();
    sql.append(" INSERT INTO t_card     ");
    sql.append("   (card_id,            ");
    sql.append("    upload_id,          ");
    sql.append("    card_no,            ");
    sql.append("    password,           ");
    sql.append("    score,              ");
    sql.append("    status,             ");
    sql.append("    insert_time,        ");
    sql.append("    update_time)        ");
    sql.append(" VALUES                 ");
    sql.append("   (?,                  ");
    sql.append("    ?,                  ");
    sql.append("    ?,                  ");
    sql.append("    ?,                  ");
    sql.append("    ?,                  ");
    sql.append("    ?,                  ");
    sql.append("    ?,                  ");
    sql.append("    ?)                  ");

    jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
      public void setValues(PreparedStatement ps, int idx) throws SQLException {
        int paramIdx = 1;

        Card item = list.get(idx);
        ps.setString(paramIdx++, IdUtils.uuid2());
        ps.setLong(paramIdx++, item.getUploadId());
        ps.setString(paramIdx++, item.getCardNo());
        ps.setString(paramIdx++, item.getPassword());
        ps.setDouble(paramIdx++, item.getScore());
        ps.setInt(paramIdx++, item.getStatus());
        ps.setTimestamp(paramIdx++, new Timestamp(System.currentTimeMillis()));
        ps.setTimestamp(paramIdx++, new Timestamp(System.currentTimeMillis()));
      }

      public int getBatchSize() {
        return list.size();
      }
    });
  }

  public void updateUpload(CardUpload upload) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder("UPDATE t_card_upload SET total = ?, update_time = ? WHERE upload_id = ?");

    params.add(upload.getTotal());
    types.add(Types.BIGINT);

    params.add(new Timestamp(System.currentTimeMillis()));
    types.add(Types.TIMESTAMP);

    params.add(upload.getUploadId());
    types.add(Types.BIGINT);

    jdbcTemplate.update(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])));
  }

  public CardUpload selectUpload(Long uploadId) {
    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.upload_id,              ");
    sql.append("        tb.total,                  ");
    sql.append("        tb.filename,               ");
    sql.append("        tb.insert_time             ");
    sql.append("   FROM t_card_upload tb           ");
    sql.append("  WHERE 1 = 1                      ");
    sql.append("    AND tb.upload_id = " + uploadId);

    return jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper<CardUpload>(CardUpload.class));
  }

  public Card selectByCardNo(String cardNo) {
    String sql = "SELECT * FROM t_card tb WHERE tb.card_no = ?  ";

    Object[] params = new Object[] {cardNo};
    int[] types = new int[] {Types.VARCHAR};

    return jdbcTemplate.queryForObject(sql, params, types, new BeanPropertyRowMapper<Card>(Card.class));
  }

}
