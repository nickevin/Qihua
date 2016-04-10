package com.qihua.common.repository;

import static java.util.Arrays.asList;
import static org.springframework.util.ObjectUtils.toObjectArray;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.support.ReflectionEntityInformation;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.qihua.common.BaseEntity;
import com.qihua.common.repository.annotation.ExcludeColumn;
import com.qihua.common.repository.annotation.Table;
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
public abstract class JdbcRepository<T, ID extends Serializable> {

  private static final String TABLE_NAME_PREFIX = "t_";

  @Autowired
  protected JdbcTemplate jdbcTemplate;

  private EntityInformation<T, ID> entityInfo;

  private TableDescription table;

  @Autowired
  private SqlGenerator sqlGenerator;

  public JdbcRepository() {
    entityInfo = createEntityInformation();
    table = createTableDescription();
  }

  @SuppressWarnings("unchecked")
  private EntityInformation<T, ID> createEntityInformation() {
    Class<T> clazz = (Class<T>) GenericTypeResolver.resolveTypeArguments(getClass(), JdbcRepository.class)[0];

    return new ReflectionEntityInformation(clazz);
  }

  private TableDescription createTableDescription() {
    return new TableDescription(getTableName(), getPkColumns());
  }

  private String getTableName() {
    Class<T> entityType = entityInfo.getJavaType();
    String entityName = entityType.getSimpleName().toLowerCase();

    Table tableAnnotation = entityType.getAnnotation(Table.class);
    if (tableAnnotation == null) {
      return TABLE_NAME_PREFIX + underscoreName(entityName);
    } else {
      String tableName = tableAnnotation.name();
      if (StringUtils.isEmpty(tableName)) {
        return TABLE_NAME_PREFIX + underscoreName(entityName);
      } else {
        return tableName;
      }
    }
  }

  private String getPkColumns() {
    Class<T> entityType = entityInfo.getJavaType();

    Field[] fields = entityType.getDeclaredFields();
    for (Field field : fields) {
      Id idAnnotation = field.getAnnotation(Id.class);
      if (idAnnotation != null) {
        String id = idAnnotation.name();
        if (StringUtils.isEmpty(id)) {
          return underscoreName(field.getName());
        } else {
          return id;
        }
      }
    }

    return "";
  }

  private ID id(final T entity) {
    return entityInfo.getId(entity);
  }

  private List<ID> ids(final Iterable<? extends T> entities) {
    List<ID> ids = new ArrayList<>();

    for (T entity : entities) {
      ids.add(id(entity));
    }
    return ids;
  }

  public Long sequence() {
    Long sequence = jdbcTemplate.queryForObject(sqlGenerator.sequence(table), Long.class);
    if (sequence == null) {
      sequence = new Long(0);
    }

    return sequence + 1;
  }

  public Long sequence(final String sequenceName) {
    Long sequence = jdbcTemplate.queryForObject(sqlGenerator.sequence(sequenceName), Long.class);
    if (sequence == null) {
      sequence = new Long(0);
    }

    return sequence + 1;
  }

  public long count() {
    return jdbcTemplate.queryForObject(sqlGenerator.count(table), Long.class);
  }


  public long count(final String sql, final List<Integer> types, final List<Object> params) {
    return 0;
  }

  public void delete(final ID id) {
    jdbcTemplate.update(sqlGenerator.deleteById(table), wrapToArray(id));
  }

  public void delete(final T entity) {
    delete(id(entity));
  }

  public void delete(final Iterable<? extends T> entities) {
    List<ID> ids = ids(entities);

    if (!ids.isEmpty()) {
      jdbcTemplate.update(sqlGenerator.deleteByIds(table, ids.size()), flatten(ids));
    }
  }

  public void deleteAll() {
    jdbcTemplate.update(sqlGenerator.deleteAll(table));
  }

  public boolean exists(final ID id) {
    return !jdbcTemplate.queryForList(sqlGenerator.existsById(table), wrapToArray(id), Integer.class).isEmpty();
  }

  public T selectOne(final ID id) {
    List<T> entityOrEmpty = jdbcTemplate.query(sqlGenerator.selectById(table), wrapToArray(id),
        new GenericBeanPropertyRowMapper<T>(entityInfo.getJavaType()));

    return entityOrEmpty.isEmpty() ? null : entityOrEmpty.get(0);
  }

  public List<T> selectAll() {
    return jdbcTemplate.query(sqlGenerator.selectAll(table),
        new GenericBeanPropertyRowMapper<T>(entityInfo.getJavaType()));
  }

  public List<T> selectAll(final Iterable<ID> ids) {
    List<ID> idsList = toList(ids);

    if (idsList.isEmpty()) {
      return Collections.emptyList();
    }

    return jdbcTemplate.query(sqlGenerator.selectByIds(table, idsList.size()),
        new GenericBeanPropertyRowMapper<T>(entityInfo.getJavaType()), flatten(idsList));
  }

  public <S extends T> S insert(final S entity) {
    Map<String, Object> columns = preInsert(columns(entity), entity);

    return insert(entity, columns);
  }

  private <S extends T> S insert(final S entity, final Map<String, Object> columns) {
    String sql = sqlGenerator.insert(table, columns);
    Object[] queryParams = columns.values().toArray();

    jdbcTemplate.update(sql, queryParams);

    return postInsert(entity, null);
  }

  protected Map<String, Object> preInsert(final Map<String, Object> columns, final T entity) {
    return columns;
  }

  protected <S extends T> S postInsert(final S entity, final Number generatedId) {
    return entity;
  }

  public <S extends T> S update(final S entity) {
    Map<String, Object> columns = preUpdate(entity, columns(entity));

    List<Object> idValues = removeIdColumns(columns); // modifies the columns list!
    String sql = sqlGenerator.update(table, columns);

    if (idValues.contains(null)) {
      throw new IllegalArgumentException("Entity's ID contains null values");
    }

    for (int i = 0; i < table.getPkColumns().size(); i++) {
      columns.put(table.getPkColumns().get(i), idValues.get(i));
    }

    Object[] queryParams = columns.values().toArray();

    int rowsAffected = jdbcTemplate.update(sql, queryParams);
    if (rowsAffected < 1) {
      throw new NoRecordUpdatedException(table.getTableName(), idValues.toArray());
    }

    if (rowsAffected > 1) {
      throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(sql, 1, rowsAffected);
    }

    return postUpdate(entity);
  }

  protected Map<String, Object> preUpdate(final T entity, final Map<String, Object> columns) {
    return columns;
  }

  protected <S extends T> S postUpdate(final S entity) {
    return entity;
  }

  private List<Object> removeIdColumns(final Map<String, Object> columns) {
    List<Object> idColumnsValues = new ArrayList<>(columns.size());

    for (String idColumn : table.getPkColumns()) {
      idColumnsValues.add(columns.remove(idColumn));
    }

    return idColumnsValues;
  }

  private Map<String, Object> columns(final T entity) {
    Map<String, Object> columns = new LinkedCaseInsensitiveMap<>();

    Field[] fields = ReflectionUtils.getDeclaredFields(entityInfo.getJavaType());
    for (Field field : fields) {
      String fieldName = field.getName();
      Object fieldVal = ReflectionUtils.invokeGetter(entity, fieldName);

      if (isAssignableFrom(field)) { // 不处理对象类型。
        continue;
      }

      if (field.isAnnotationPresent(ExcludeColumn.class)) {
        continue;
      }

      columns.put(underscoreName(fieldName), fieldVal);
    }

    return columns;
  }

  private static <T> List<T> toList(final Iterable<T> iterable) {
    if (iterable instanceof List) {
      return (List<T>) iterable;
    }

    List<T> result = new ArrayList<>();
    for (T item : iterable) {
      result.add(item);
    }

    return result;
  }

  private boolean isAssignableFrom(final Field field) {
    return Collection.class.isAssignableFrom(field.getType()) || BaseEntity.class.isAssignableFrom(field.getType());
  }

  private static <ID> Object[] flatten(final List<ID> ids) {
    List<Object> result = new ArrayList<>();
    for (ID id : ids) {
      result.addAll(asList(wrapToArray(id)));
    }
    return result.toArray();
  }

  private static Object[] wrapToArray(final Object obj) {
    if (obj == null) {
      return new Object[0];
    }
    if (obj instanceof Object[]) {
      return (Object[]) obj;
    }
    if (obj.getClass().isArray()) {
      return toObjectArray(obj);
    }
    return new Object[] {obj};
  }

  /**
   * @see: org.springframework.jdbc.core.BeanPropertyRowMapper
   *
   */
  private String underscoreName(final String name) {
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

}
