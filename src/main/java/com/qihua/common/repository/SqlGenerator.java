package com.qihua.common.repository;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since Apr 4, 2016
 * @version 1.0
 * @see
 */
public interface SqlGenerator {
  /**
   * This method is used by {@link SqlGeneratorFactory} to select a right SQL Generator.
   *
   * @param metadata The database metadata.
   * @return Whether is this generator compatible with the database described by the given {@code metadata}.
   */
  boolean isCompatible(DatabaseMetaData metadata) throws SQLException;

  String count(TableDescription table);

  String deleteAll(TableDescription table);

  String deleteById(TableDescription table);

  String deleteByIds(TableDescription table, int idsCount);

  String existsById(TableDescription table);

  String insert(TableDescription table, Map<String, Object> columns);

  String selectAll(TableDescription table);

  String selectAll(TableDescription table, Pageable page);

  String selectAll(TableDescription table, Sort sort);

  String selectById(TableDescription table);

  String selectByIds(TableDescription table, int idsCount);

  String update(TableDescription table, Map<String, Object> columns);

  String sequence(TableDescription table);

  String sequence(String sequenceName);
}
