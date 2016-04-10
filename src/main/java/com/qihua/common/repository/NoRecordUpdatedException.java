package com.qihua.common.repository;

import static java.lang.String.format;
import static org.springframework.util.StringUtils.arrayToCommaDelimitedString;

import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since Apr 4, 2016
 * @version 1.0
 * @see
 */
public class NoRecordUpdatedException extends IncorrectUpdateSemanticsDataAccessException {

  private static final long serialVersionUID = 1L;

  private final String tableName;
  private final Object[] id;

  public NoRecordUpdatedException(final String tableName, final Object... id) {
    super(format("No record with id = {%s} exists in table %s", arrayToCommaDelimitedString(id), tableName));
    this.tableName = tableName;
    this.id = id.clone();
  }

  public NoRecordUpdatedException(final String tableName, final String msg) {
    super(msg);
    this.tableName = tableName;
    id = new Object[0];
  }

  public NoRecordUpdatedException(final String tableName, final String msg, final Throwable cause) {
    super(msg, cause);
    this.tableName = tableName;
    id = new Object[0];
  }

  @Override
  public boolean wasDataUpdated() {
    return false;
  }

  public String getTableName() {
    return tableName;
  }

  public Object[] getId() {
    return id.clone();
  }
}
