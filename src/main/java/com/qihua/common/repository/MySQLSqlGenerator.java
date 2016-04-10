package com.qihua.common.repository;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.springframework.util.StringUtils.collectionToDelimitedString;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since Apr 4, 2016
 * @version 1.0
 * @see
 */
@Component(value = "sqlGenerator")
public class MySQLSqlGenerator implements SqlGenerator {

  static final String AND = " AND ", OR = " OR ", COMMA = ", ", PARAM = " = ?";

  @Override
  public boolean isCompatible(final DatabaseMetaData metadata) throws SQLException {
    return true;
  }

  @Override
  public String count(final TableDescription table) {
    return format("SELECT count(1) FROM %s", table.getTableName());
  }

  @Override
  public String deleteAll(final TableDescription table) {
    return format("DELETE FROM %s", table.getTableName());
  }

  @Override
  public String deleteById(final TableDescription table) {
    return deleteByIds(table, 1);
  }

  @Override
  public String deleteByIds(final TableDescription table, final int idsCount) {
    return deleteAll(table) + " WHERE " + idsPredicate(table, idsCount);
  }

  @Override
  public String existsById(final TableDescription table) {
    return format("SELECT 1 FROM %s WHERE %s", table.getTableName(), idPredicate(table));
  }

  @Override
  public String insert(final TableDescription table, final Map<String, Object> columns) {
    return format("INSERT INTO %s (%s) VALUES (%s)", table.getTableName(),
        collectionToDelimitedString(columns.keySet(), COMMA), repeat("?", COMMA, columns.size()));
  }

  @Override
  public String selectAll(final TableDescription table) {
    return format("SELECT %s FROM %s", "*", table.getTableName());
  }

  @Override
  public String selectAll(final TableDescription table, final Pageable page) {
    Sort sort = page.getSort() != null ? page.getSort() : sortById(table);

    return format(
        "SELECT t2__.* FROM ( " + "SELECT row_number() OVER (ORDER BY %s) AS rn__, t1__.* FROM ( %s ) t1__ "
            + ") t2__ WHERE t2__.rn__ BETWEEN %s AND %s",
        orderByExpression(sort), selectAll(table), page.getOffset() + 1, page.getOffset() + page.getPageSize());
  }

  @Override
  public String selectAll(final TableDescription table, final Sort sort) {
    return selectAll(table) + (sort != null ? orderByClause(sort) : "");
  }

  @Override
  public String selectById(final TableDescription table) {
    return selectByIds(table, 1);
  }

  @Override
  public String selectByIds(final TableDescription table, final int idsCount) {
    return idsCount > 0 ? selectAll(table) + " WHERE " + idsPredicate(table, idsCount) : selectAll(table);
  }

  @Override
  public String update(final TableDescription table, final Map<String, Object> columns) {
    return format("UPDATE %s SET %s WHERE %s", table.getTableName(), formatParameters(columns.keySet(), COMMA),
        idPredicate(table));
  }

  @Override
  public String sequence(final TableDescription table) {
    return format("SELECT MAX(%s) FROM %s", idPredicate(table), table.getTableName());
  }

  @Override
  public String sequence(final String sequenceName) {
    return format("SELECT %s.nextval FROM dual", sequenceName);
  }

  protected String orderByClause(final Sort sort) {
    return " ORDER BY " + orderByExpression(sort);
  }

  protected String orderByExpression(final Sort sort) {
    StringBuilder sb = new StringBuilder();

    for (Iterator<Order> it = sort.iterator(); it.hasNext();) {
      Order order = it.next();
      sb.append(order.getProperty()).append(' ').append(order.getDirection());

      if (it.hasNext()) {
        sb.append(COMMA);
      }
    }
    return sb.toString();
  }

  protected Sort sortById(final TableDescription table) {
    return new Sort(Direction.ASC, table.getPkColumns());
  }


  private String idPredicate(final TableDescription table) {
    return formatParameters(table.getPkColumns(), AND);
  }

  private String idsPredicate(final TableDescription table, final int idsCount) {
    Assert.isTrue(idsCount > 0, "idsCount must be greater than zero");

    List<String> idColumnNames = table.getPkColumns();

    if (idsCount == 1) {
      return idPredicate(table);
    } else if (idColumnNames.size() > 1) {
      return repeat("(" + formatParameters(idColumnNames, AND) + ")", OR, idsCount);
    } else {
      return idColumnNames.get(0) + " IN (" + repeat("?", COMMA, idsCount) + ")";
    }
  }

  private String formatParameters(final Collection<String> columns, final String delimiter) {
    return collectionToDelimitedString(columns, delimiter, "", PARAM);
  }

}
