package com.qihua.common.repository;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;

import java.util.List;

import org.springframework.util.Assert;


/**
 * TableDescription.
 * 
 * @author aopfilter@163.com
 * @since Apr 4, 2016
 * @version 1.0
 * @see
 */
public class TableDescription {

  private String tableName;
  private List<String> idColumns = singletonList("");

  public TableDescription() {}

  public TableDescription(final String tableName, final List<String> pkColumns) {
    setTableName(tableName);
    setPkColumns(pkColumns);
  }

  public TableDescription(final String tableName, final String idColumn) {
    this(tableName, singletonList(idColumn));
  }

  public String getTableName() {
    Assert.state(tableName != null, "tableName must not be null");
    return tableName;
  }

  public void setTableName(final String tableName) {
    Assert.hasText(tableName, "tableName must not be blank");
    this.tableName = tableName;
  }

  public List<String> getPkColumns() {
    return idColumns;
  }

  public void setPkColumns(final List<String> pkColumns) {
    for (String column : pkColumns) {
      Assert.hasLength(column, "At least one primary key column must be provided");
    }

    idColumns = unmodifiableList(pkColumns);
  }

  public void setPkColumns(final String... idColumns) {
    setPkColumns(asList(idColumns));
  }

}
