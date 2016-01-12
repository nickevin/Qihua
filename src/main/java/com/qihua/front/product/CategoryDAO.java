package com.qihua.front.product;

import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.qihua.common.CustomBeanPropertyRowMapper;
import com.qihua.common.GenericDAO;

@Repository
public class CategoryDAO extends GenericDAO<Category, Long> {

    public List<Category> selectCategory() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT DISTINCT             ");
        sql.append("        category_id,         ");
        sql.append("        category_name        ");
        sql.append("   FROM t_product_category   ");

        return jdbcTemplate.query(sql.toString(), new CustomBeanPropertyRowMapper<Category>(Category.class));
    }

    public Category selectCategory(Long categoryId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT DISTINCT                ");
        sql.append("        category_id,            ");
        sql.append("        category_name           ");
        sql.append("   FROM t_product_category      ");
        sql.append("  WHERE category_id = ?         ");

        Object[] params = new Object[] { categoryId };
        int[] types = new int[] { Types.BIGINT };

        return jdbcTemplate.queryForObject(sql.toString(), params, types, new CustomBeanPropertyRowMapper<Category>(
                Category.class));
    }

    public List<Category> selectSubcategoryByCatgeoryId(Long categoryId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT subcategory_id,         ");
        sql.append("        subcategory_name        ");
        sql.append("   FROM t_product_category      ");
        sql.append("  WHERE category_id = ?         ");

        Object[] params = new Object[] { categoryId };
        int[] types = new int[] { Types.BIGINT };

        return jdbcTemplate.query(sql.toString(), params, types, new CustomBeanPropertyRowMapper<Category>(
                Category.class));
    }

    public Category selectSubcategory(Long subcatgeoryId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT category_id,            ");
        sql.append("        category_name,          ");
        sql.append("        subcategory_id,         ");
        sql.append("        subcategory_name        ");
        sql.append("   FROM t_product_category      ");
        sql.append("  WHERE subcategory_id = ?      ");

        Object[] params = new Object[] { subcatgeoryId };
        int[] types = new int[] { Types.BIGINT };

        return jdbcTemplate.queryForObject(sql.toString(), params, types, new CustomBeanPropertyRowMapper<Category>(
                Category.class));
    }

}
