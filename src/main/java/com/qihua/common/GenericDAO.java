package com.qihua.common;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.qihua.common.annotation.ExcludeColumn;
import com.qihua.common.annotation.GeneratedValue;
import com.qihua.common.annotation.NotNull;
import com.qihua.common.annotation.PrimaryKey;
import com.qihua.common.annotation.Table;
import com.qihua.common.annotation.UniqueColumn;
import com.qihua.exception.FieldTypeException;
import com.qihua.exception.IllegalValueException;
import com.qihua.exception.RequiredAnnotationException;
import com.qihua.exception.RequiredFieldException;
import com.qihua.util.IdUtils;
import com.qihua.util.ReflectionUtils;

/**
 * Class description goes here.
 *
 * @author aopfilter@163.com
 * @since 2013-10-11
 * @version 1.0
 * @see
 */
@Repository
public class GenericDAO<T, ID extends Serializable> {

  private static final String ID_GENERATOR_UUID = "uuid";
  private static final String ID_GENERATOR_AUTO = "auto";

  private static final String INT_DEFAULT_VALUE = "0";
  private static final String LONG_DEFAULT_VALUE = "0.0";

  private static final String TABLE_NAME_PREFIX = "t_";
  private static final String PRIMARY_KEY_POSTFIX = "_id";

  @Autowired
  protected JdbcTemplate jdbcTemplate;

  private Class<T> entityType;
  private String entityName;
  private String tableName;
  private String primaryKeyName;
  private Class primaryKeyClass;

  @SuppressWarnings("unchecked")
  public GenericDAO() {
    Type returnType = getClass().getGenericSuperclass();
    if (returnType instanceof ParameterizedType) {
      ParameterizedType pt = (ParameterizedType) returnType;
      entityType = (Class<T>) pt.getActualTypeArguments()[0];
      entityName = entityType.getSimpleName().toLowerCase();

      try {
        configTableInfo();
      } catch (RequiredAnnotationException e) {
        e.printStackTrace();
      }
    }
  }

  private void configTableInfo() throws RequiredAnnotationException {
    Table tableAnnotation = entityType.getAnnotation(Table.class);
    if (tableAnnotation == null) {
      tableName = TABLE_NAME_PREFIX + entityType.getSimpleName().toLowerCase();
      primaryKeyName = entityName + PRIMARY_KEY_POSTFIX;
    } else {
      String table = tableAnnotation.name();
      if (StringUtils.isEmpty(table)) {
        tableName = TABLE_NAME_PREFIX + entityType.getSimpleName().toLowerCase();
      } else {
        tableName = table;
      }
    }

    Field[] fields = entityType.getDeclaredFields();
    for (Field field : fields) {
      PrimaryKey primaryKeyAnnotation = field.getAnnotation(PrimaryKey.class);
      if (primaryKeyAnnotation != null) {
        primaryKeyClass = field.getType();

        String primaryKey = primaryKeyAnnotation.name();
        if (StringUtils.isEmpty(primaryKey)) {
          primaryKeyName = underscoreName(field.getName());
        } else {
          primaryKeyName = primaryKey;
        }

        break;
      } else {
        throw new RequiredAnnotationException(
            "entity: " + entityName + ", field: " + field.getName() + ", PrimaryKey annotation is required.");
      }
    }
  }

  public Long sequence() {
    return sequence(tableName, primaryKeyName);
  }

  public Long sequence(String tableName, String primaryKeyName) {
    String sql = "SELECT MAX(" + primaryKeyName + ") FROM " + tableName;

    Long sequence = jdbcTemplate.queryForObject(sql.toString(), Long.class);
    if (sequence == null) {
      sequence = new Long(0);
    }

    return sequence + 1;
  }

  public int count(String sql, List<Integer> types, List<Object> params) {
    String _sql = "SELECT count(1) FROM (" + sql + ") tb";

    return jdbcTemplate.queryForObject(_sql, params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])), Integer.class);
  }

  public void delete(ID id) {
    StringBuilder sql = new StringBuilder();
    sql.append(" DELETE                               ");
    sql.append("   FROM " + tableName + "             ");

    if (primaryKeyClass == String.class) {
      sql.append("  WHERE " + primaryKeyName + " = '" + id + "'");
    } else {
      sql.append("  WHERE " + primaryKeyName + " = " + id);
    }

    jdbcTemplate.update(sql.toString());
  }

  public boolean exists(ID id) {
    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT COUNT(1)                      ");
    sql.append("   FROM " + tableName + "             ");
    sql.append("  WHERE " + primaryKeyName + " = " + id);

    return jdbcTemplate.queryForObject(sql.toString(), Integer.class) == 1;
  }

  public List<T> select() {
    String sql = "SELECT * FROM " + tableName;

    return jdbcTemplate.query(sql, new CustomBeanPropertyRowMapper<T>(entityType));
  }

  public T select(ID id) {
    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT *                             ");
    sql.append("   FROM " + tableName + "             ");

    if (primaryKeyClass == String.class) {
      sql.append("  WHERE " + primaryKeyName + " = '" + id + "'");
    } else {
      sql.append("  WHERE " + primaryKeyName + " = " + id);
    }

    return jdbcTemplate.queryForObject(sql.toString(), new CustomBeanPropertyRowMapper<T>(entityType));
  }

  public <S extends T> S insert(S obj) throws Exception {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
    StringBuilder placeholder = new StringBuilder();

    Field[] fields = ReflectionUtils.getDeclaredFields(entityType);
    for (Field field : fields) {
      String fieldName = field.getName();
      Object fieldVal = ReflectionUtils.invokeGetter(obj, fieldName);

      if (isAssignableFrom(field)) { // 不处理集合类型。
        continue;
      }

      if (field.isAnnotationPresent(ExcludeColumn.class)) {
        continue;
      }

      if (field.isAnnotationPresent(UniqueColumn.class) && isEmpty(field, fieldVal)) {
        continue;
      }

      if (field.isAnnotationPresent(NotNull.class) && isEmpty(field, fieldVal)) {
        throw new RequiredFieldException("entity: " + entityName + ", field: " + fieldName + " is null");
      }

      if (field.isAnnotationPresent(PrimaryKey.class) && !field.isAnnotationPresent(GeneratedValue.class)) {
        if (field.getType() != Long.class) {
          throw new FieldTypeException("entity: " + entityName + ", field: " + fieldName + ", primary key: "
              + primaryKeyName + ", generation strategy is default, please confirm field type is java.lang.Long");
        }

        if (isEmpty(field, fieldVal)) {
          fieldVal = sequence();
          ReflectionUtils.invokeSetter(obj, fieldName, fieldVal);
        }
      } else if (field.isAnnotationPresent(GeneratedValue.class)) {
        GeneratedValue generatedValueAnnotation = field.getAnnotation(GeneratedValue.class);
        String generator = generatedValueAnnotation.generator();
        if (StringUtils.isEmpty(generator)) {
          fieldVal = sequence();
          ReflectionUtils.invokeSetter(obj, fieldName, fieldVal);
        } else if (ID_GENERATOR_UUID.equals(generator.toLowerCase())) {
          if (field.getType() != String.class) {
            throw new FieldTypeException("entity: " + entityName + ", field: " + fieldName + ", primary key: "
                + primaryKeyName + ", generation strategy is uuid, please confirm field type is java.lang.String");
          }

          fieldVal = IdUtils.uuid2();
          ReflectionUtils.invokeSetter(obj, fieldName, fieldVal);
        } else if (ID_GENERATOR_AUTO.equals(generator.toLowerCase())) {
          if (field.getType() != Long.class) {
            throw new FieldTypeException("entity: " + entityName + ", field: " + fieldName + ", primary key: "
                + primaryKeyName + ", generation strategy is auto, please confirm field type is java.lang.Long");
          }

          fieldVal = sequence();
          ReflectionUtils.invokeSetter(obj, fieldName, fieldVal);
        } else {
          throw new IllegalValueException("entity: " + entityName + ", field: " + fieldName + ", primary key: "
              + primaryKeyName + ", generation strategy: [uuid, auto], you input: " + generator);
        }
      }

      sql.append(underscoreName(fieldName) + ", ");
      placeholder.append("?, ");

      params.add(fieldVal);
      types.add(getSqlType(field.getType()));
    }

    sql = new StringBuilder(StringUtils.removeEnd(sql.toString(), ", "));
    placeholder = new StringBuilder(StringUtils.removeEnd(placeholder.toString(), ", "));

    sql.append(") VALUES (").append(placeholder).append(")");

    jdbcTemplate.update(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])));

    return obj;
  }

  public <S extends T> S update(S obj) throws Exception {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    Object primaryKeyValue = null;

    StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");

    Field[] fields = ReflectionUtils.getDeclaredFields(entityType);
    for (Field field : fields) {
      String fieldName = field.getName();
      Object fieldVal = ReflectionUtils.invokeGetter(obj, fieldName);

      if (isAssignableFrom(field)) { // 不处理集合类型。
        continue;
      }

      if (field.isAnnotationPresent(ExcludeColumn.class)) {
        continue;
      }

      if (field.isAnnotationPresent(UniqueColumn.class) && isEmpty(field, fieldVal)) {
        continue;
      }

      // if (field.isAnnotationPresent(NotNull.class) && isEmpty(field,
      // fieldVal)) {
      // throw new RequiredFieldException("entity: " + entityName +
      // ", field: " + fieldName + " is null");
      // }

      if (field.isAnnotationPresent(PrimaryKey.class)) {
        if (isEmpty(field, fieldVal)) {
          throw new RequiredFieldException(
              "entity: " + entityName + ", field: " + fieldName + ", primary key: " + primaryKeyName + " is null");
        }

        primaryKeyValue = fieldVal;
      }

      if (isNotEmpty(field, fieldVal)) {
        sql.append(underscoreName(fieldName) + " = ?, ");
        params.add(fieldVal);
        types.add(getSqlType(field.getType()));
      }
    }

    sql = new StringBuilder(StringUtils.removeEnd(sql.toString(), ", "));

    sql.append(" WHERE " + primaryKeyName + " = ? ");
    params.add(primaryKeyValue);
    types.add(getSqlType(primaryKeyClass));

    jdbcTemplate.update(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])));

    return obj;
  }

  private boolean isEmpty(Field field, Object fieldVal) {
    if (field.getType() == String.class && StringUtils.isEmpty((String) fieldVal)) {
      return true;
    }

    if (field.getType().isPrimitive()
        && (INT_DEFAULT_VALUE.equals(fieldVal.toString()) || LONG_DEFAULT_VALUE.equals(fieldVal.toString()))) {
      return true;
    }

    if (fieldVal == null) {
      return true;
    }

    return false;
  }

  private boolean isNotEmpty(Field field, Object fieldVal) {
    return !isEmpty(field, fieldVal);
  }

  private Integer getSqlType(Class<?> type) {
    if (type == int.class || type == Integer.class) {
      return Types.INTEGER;
    } else if (type == long.class || type == Long.class) {
      return Types.BIGINT;
    } else if (type == double.class || type == Double.class || type == BigDecimal.class) {
      return Types.DOUBLE;
    } else if (type == Timestamp.class) {
      return Types.TIMESTAMP;
    } else if (type == Date.class) {
      return Types.DATE;
    }

    return Types.VARCHAR;
  }

  private boolean isAssignableFrom(Field field) {
    return Collection.class.isAssignableFrom(field.getType());
  }

  /**
   * @see: org.springframework.jdbc.core.BeanPropertyRowMapper
   *
   */
  private String underscoreName(String name) {
    StringBuffer result = new StringBuffer();
    if (name != null && name.length() > 0) {
      result.append(name.substring(0, 1).toLowerCase());
      for (int i = 1; i < name.length(); i++) {
        String s = name.substring(i, i + 1);
        if (s.equals(s.toUpperCase())) {
          result.append("_");
          result.append(s.toLowerCase());
        } else {
          result.append(s);
        }
      }
    }

    return result.toString();
  }

  /**
   *
   * Convert a column name with underscores to the corresponding property name using "camel case". A name like
   * "customer_number" would match a "customerNumber" property name.
   *
   * @param name the column name to be converted
   * @return the name using "camel case"
   * @see: org.springframework.jdbc.core.JdbcUtils
   *
   */
  @SuppressWarnings("unused")
  private static String convertUnderscoreNameToPropertyName(String name) {
    StringBuilder result = new StringBuilder();
    boolean nextIsUpper = false;

    if (name != null && name.length() > 0) {
      if (name.length() > 1 && name.substring(1, 2).equals("_")) {
        result.append(name.substring(0, 1).toUpperCase());
      } else {
        result.append(name.substring(0, 1).toLowerCase());
      }

      for (int i = 1; i < name.length(); i++) {
        String s = name.substring(i, i + 1);
        if (s.equals("_")) {
          nextIsUpper = true;
        } else {
          if (nextIsUpper) {
            result.append(s.toUpperCase());
            nextIsUpper = false;
          } else {
            result.append(s.toLowerCase());
          }
        }
      }
    }

    return result.toString();
  }
}
