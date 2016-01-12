package com.qihua.console.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.qihua.common.CustomBeanPropertyRowMapper;
import com.qihua.common.GenericDAO;
import com.qihua.console.security.Role;

@Repository
public class UserDAO extends GenericDAO<User, String> {

    public User selectByUsername(String username) {
        String sql = "SELECT * FROM t_user tb WHERE tb.username = ?  ";

        Object[] params = new Object[] { username };
        int[] types = new int[] { Types.VARCHAR };

        return jdbcTemplate.queryForObject(sql, params, types, new BeanPropertyRowMapper<User>(User.class));
    }

    public User selectCredential(User user) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT DISTINCT                                 ");
        sql.append("     usr.user_id,                                ");
        sql.append("     usr.username,                               ");
        sql.append("     usr.password,                               ");
        sql.append("     usr.real_name,                              ");
        sql.append("     usr.mobile,                                 ");
        sql.append("     usr.email,                                  ");
        sql.append("     ur.role_id,                                 ");
        sql.append("     r.role_name                                 ");
        sql.append(" FROM                                            ");
        sql.append("     t_user usr                                  ");
        sql.append("         RIGHT JOIN                              ");
        sql.append("     t_user_role ur ON usr.user_id = ur.user_id  ");
        sql.append("         JOIN                                    ");
        sql.append("     t_role r ON r.role_id = ur.role_id          ");
        sql.append(" WHERE usr.username = ?                          ");
        sql.append("   AND LOWER(usr.password) = LOWER(?)            ");

        return jdbcTemplate.queryForObject(sql.toString(), new Object[] { user.getUsername(), user.getPassword() }, new int[] { Types.VARCHAR, Types.VARCHAR }, new ResultMapper(User.class));
    }

    public List<User> selectByMobile(String mobile) {
        String sql = "SELECT * FROM t_user tb WHERE tb.mobile = ? ";

        Object[] params = new Object[] { mobile };
        int[] types = new int[] { Types.VARCHAR };

        return jdbcTemplate.query(sql, params, types, new BeanPropertyRowMapper<User>(User.class));
    }

    public List<User> selectByEmail(String email) {
        String sql = "SELECT * FROM t_user tb WHERE tb.email = ? ";

        Object[] params = new Object[] { email };
        int[] types = new int[] { Types.VARCHAR };

        return jdbcTemplate.query(sql, params, types, new BeanPropertyRowMapper<User>(User.class));
    }

    public List<User> select(UserQueryParameter queryParam) {
        List<Object> params = new ArrayList<Object>();
        List<Integer> types = new ArrayList<Integer>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT usr.user_id,            ");
        sql.append("        usr.username,           ");
        sql.append("        usr.password,           ");
        sql.append("        usr.real_name,          ");
        sql.append("        usr.mobile,             ");
        sql.append("        usr.email               ");
        sql.append("   FROM t_user usr              ");
        sql.append("  WHERE 1 = 1                   ");

        String username = queryParam.getUsername();
        if (StringUtils.isNotEmpty(username)) {
            sql.append(" AND usr.username = ?");
            params.add(username);
            types.add(Types.VARCHAR);
        }

        String password = queryParam.getPassword();
        if (StringUtils.isNotEmpty(password)) {
            sql.append(" AND LOWER(usr.password) = ?");
            params.add(password.toLowerCase());
            types.add(Types.VARCHAR);
        }

        String realName = queryParam.getRealName();
        if (StringUtils.isNotEmpty(realName)) {
            sql.append(" AND usr.real_name = ?");
            params.add(realName);
            types.add(Types.VARCHAR);
        }

        String mobile = queryParam.getMobile();
        if (StringUtils.isNotEmpty(mobile)) {
            sql.append(" AND usr.mobile = ?");
            params.add(mobile);
            types.add(Types.VARCHAR);
        }

        String email = queryParam.getEmail();
        if (StringUtils.isNotEmpty(email)) {
            sql.append(" AND usr.email = ?");
            params.add(email);
            types.add(Types.VARCHAR);
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])), new BeanPropertyRowMapper<User>(User.class));
    }

}

class ResultMapper extends CustomBeanPropertyRowMapper<User> implements RowMapper<User> {

    public ResultMapper(Class<User> clazz) {
        super(clazz);
    }

    @Override
    public User mapRow(ResultSet rs, int idx) throws SQLException {
        User item = new User();
        item.setUserId(rs.getString("user_id"));
        item.setUsername(rs.getString("username"));
        item.setPassword(rs.getString("password"));
        item.setRealName(rs.getString("real_name"));
        item.setMobile(rs.getString("mobile"));
        item.setEmail(rs.getString("email"));
        item.setRole(new Role(rs.getLong("role_id"), rs.getString("role_name")));

        return item;
    }
}