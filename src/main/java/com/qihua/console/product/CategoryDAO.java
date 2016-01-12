package com.qihua.console.product;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.qihua.common.GenericDAO;

@Repository
public class CategoryDAO extends GenericDAO<Category, Long> {

    public List<Category> selectCategory() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT DISTINCT             ");
        sql.append("        category_id,         ");
        sql.append("        category_name        ");
        sql.append("   FROM t_product_category   ");

        Object[] params = new Object[] {};
        int[] types = new int[] {};

        return jdbcTemplate.query(sql.toString(), params, types, new BeanPropertyRowMapper<Category>(Category.class));
    }

}
