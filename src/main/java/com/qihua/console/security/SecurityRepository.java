package com.qihua.console.security;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.qihua.common.repository.GenericBeanPropertyRowMapper;
import com.qihua.common.repository.JdbcRepository;
import com.qihua.console.user.User;

/**
 * Class description goes here.
 * 
 * @author aopfilter@163.com
 * @since 2013-10-11
 * @version 1.0
 * @see
 */
@Repository
public class SecurityRepository extends JdbcRepository<Role, Long> {

  public List<Role> selectRole() {
    String sql = "SELECT * FROM t_role ORDER BY role_id";

    return jdbcTemplate.query(sql, new GenericBeanPropertyRowMapper<Role>(Role.class));
  }

  public Role selectRole(final Long id) {
    String sql = "SELECT * FROM t_role where role_id = ?";

    return jdbcTemplate.queryForObject(sql, new Object[] {id}, new int[] {Types.BIGINT},
        new GenericBeanPropertyRowMapper<Role>(Role.class));
  }

  public List<Menu> selectMenu() {
    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT m.menu_id, m.menu_name, m.menu_url, m.parent_id 	");
    sql.append("   FROM t_menu m 											");
    sql.append("  WHERE m.parent_id is not null 							");
    sql.append("  ORDER BY m.menu_id                                        ");

    return jdbcTemplate.query(sql.toString(), new GenericBeanPropertyRowMapper<Menu>(Menu.class));
  }

  public List<Menu> selectByRoleId(final Long id) {
    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT m.menu_id, m.menu_name, m.menu_url, m.parent_id          ");
    sql.append("   FROM t_role r, t_menu m, t_role_menu rm                       ");
    sql.append("  WHERE r.role_id = rm.role_id                                   ");
    sql.append("    AND m.menu_id = rm.menu_id                                   ");
    sql.append("    AND r.role_id = ?                                            ");
    sql.append("  ORDER BY m.menu_id                                             ");

    return jdbcTemplate.query(sql.toString(), new Object[] {id}, new int[] {Types.BIGINT},
        new GenericBeanPropertyRowMapper<Menu>(Menu.class));
  }

  public boolean insertUserRole(final User user) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder("INSERT INTO t_user_role ( ");
    StringBuilder placeholder = new StringBuilder();

    String userId = user.getUserId();
    sql.append(" user_id, ");
    placeholder.append(" ?, ");
    params.add(userId);
    types.add(Types.VARCHAR);

    Long roleId = user.getRole().getRoleId();
    sql.append(" role_id ");// 注意：没有分号
    placeholder.append(" ? "); // 注意：没有分号
    params.add(roleId);
    types.add(Types.BIGINT);

    sql.append(" ) VALUES ( ").append(placeholder).append(" ) ");

    return jdbcTemplate.update(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()]))) == 1;
  }

  public boolean updateUserRole(final User user) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder("UPDATE t_user_role SET ");

    Long role = user.getRole().getRoleId();
    sql.append(" role_id = ?, ");
    params.add(role);
    types.add(Types.BIGINT);

    sql.append(" user_id = ? ");// 注意：没有分号
    params.add(user.getUserId());
    types.add(Types.VARCHAR);

    sql.append(" WHERE user_id = ? ");
    params.add(user.getUserId());
    types.add(Types.VARCHAR);

    return jdbcTemplate.update(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()]))) == 1;
  }

  public Role insertRole(final Role role) {
    String sql = "INSERT INTO t_role (role_id, role_name) VALUES (?, ?)";

    Object[] params = new Object[] {role.getRoleId(), role.getRoleName()};
    int[] types = new int[] {Types.BIGINT, Types.VARCHAR};

    int result = jdbcTemplate.update(sql, params, types);
    if (result == 1) {
      return role;
    }

    return null;
  }

  public void insertRoleMenu(final Role role, final List<Menu> items) {
    String sql = "INSERT INTO t_role_menu (role_id, menu_id) VALUES (?, ?)";
    int[] types = new int[] {Types.BIGINT, Types.BIGINT};

    for (Menu item : items) {
      Object[] params = new Object[] {role.getRoleId(), item.getMenuId()};

      jdbcTemplate.update(sql, params, types);
    }
  }

  public Role updateRole(final Role role) {
    List<String> params = new ArrayList<String>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder("UPDATE t_role SET ");

    String roleName = role.getRoleName();
    if (!StringUtils.isEmpty(roleName)) {
      sql.append(" role_name = ?, ");
      params.add(roleName);
      types.add(Types.VARCHAR);
    }

    sql.append(" role_id = ? ");
    params.add(role.getRoleId() + "");
    types.add(Types.BIGINT);

    sql.append(" WHERE role_id = ? ");
    params.add(role.getRoleId() + "");
    types.add(Types.BIGINT);

    int result = jdbcTemplate.update(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])));
    if (result == 1) {
      return role;
    }

    return null;
  }

  public void updateRoleMenu(final Role role, final List<Menu> items) {
    deleteMenuByRoleId(role.getRoleId());
    insertRoleMenu(role, items);
  }

  private void deleteMenuByRoleId(final long id) {
    String sql = "DELETE FROM t_role_menu WHERE role_id = ?";
    Object[] params = new Object[] {id};
    int[] types = new int[] {Types.BIGINT};

    jdbcTemplate.update(sql, params, types);
  }

}
