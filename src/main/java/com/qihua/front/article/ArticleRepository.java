package com.qihua.front.article;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.qihua.common.repository.JdbcRepository;

@Repository
public class ArticleRepository extends JdbcRepository<Article, Long> {

  public Article selectByArticleType(int articleType) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.article_id,           ");
    sql.append("        tb.content               ");
    sql.append("   FROM t_article tb             ");
    sql.append("  WHERE 1 = 1                    ");
    sql.append("    AND tb.article_type = ?      ");

    params.add(articleType);
    types.add(Types.INTEGER);

    return jdbcTemplate.queryForObject(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new BeanPropertyRowMapper<Article>(Article.class));
  }

}
