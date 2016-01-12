package com.qihua.front.product;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.qihua.common.Constants;
import com.qihua.common.CustomBeanPropertyRowMapper;
import com.qihua.common.GenericDAO;
import com.qihua.common.PageModel;

@Repository
public class ProductDAO extends GenericDAO<Product, String> {

  public PageModel<Product> selectByPagination(ProductQueryParameter queryParam) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.product_id,          ");
    sql.append("        tb.product_name,        ");
    sql.append("        tb.score                ");
    sql.append("   FROM t_product tb            ");
    sql.append("  WHERE 1 = 1                   ");

    String categoryId = queryParam.getCategoryId();
    if (StringUtils.isNotEmpty(categoryId)) {
      if (categoryId.length() == 6) {
        sql.append(" AND tb.category_id = ?");
        params.add(categoryId);
        types.add(Types.BIGINT);
      } else {
        sql.append(" AND tb.category_id REGEXP ?");
        params.add("^" + categoryId);
        types.add(Types.VARCHAR);
      }
    }

    String lowScore = queryParam.getLowScore();
    if (StringUtils.isNotEmpty(lowScore)) {
      sql.append("   AND tb.score >= ? ");
      params.add(lowScore);
      types.add(Types.INTEGER);
    }

    String highScore = queryParam.getHighScore();
    if (StringUtils.isNotEmpty(highScore)) {
      sql.append("   AND tb.score <= ? ");
      params.add(highScore);
      types.add(Types.INTEGER);
    }

    long count = count(sql.toString(), types, params);

    if (queryParam.getOrder().equals("")) {
      sql.append(" ORDER BY tb.insert_time DESC");
    }

    sql.append(" LIMIT ?, ? ");

    int pageNo = 0;
    if (queryParam.getPageNo() <= 0 || count == 0) {
      pageNo = 1;
    } else if (queryParam.getPageNo() * Constants.PAGE_SIZE > count) {
      pageNo = (int) Math.ceil(count / (double) Constants.PAGE_SIZE);
    } else {
      pageNo = queryParam.getPageNo();
    }

    types.add(Types.INTEGER);
    params.add((pageNo - 1) * Constants.PAGE_SIZE);

    types.add(Types.INTEGER);
    params.add(Constants.PAGE_SIZE);

    List<Product> result = jdbcTemplate.query(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new CustomBeanPropertyRowMapper<Product>(Product.class));

    return new PageModel<Product>(result, new PageRequest(pageNo, Constants.PAGE_SIZE), count);
  }

  public List<Product> selectHottest() {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT product_id,             ");
    sql.append("        product_name,           ");
    sql.append("        score                   ");
    sql.append("   FROM (                       ");
    sql.append("       SELECT                   ");
    sql.append("           product_id,          ");
    sql.append("           product_name,        ");
    sql.append("           score                ");
    sql.append("       FROM t_product           ");
    sql.append("   ORDER BY rate DESC ) tb      ");
    sql.append("  ORDER BY rand() LIMIT 6       ");

    return jdbcTemplate.query(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new BeanPropertyRowMapper<Product>(Product.class));
  }

  public List<Product> selectByScore(int lowScore, int highScore) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT product_id,             ");
    sql.append("        product_name,           ");
    sql.append("        score                   ");
    sql.append("   FROM (                       ");
    sql.append("       SELECT                   ");
    sql.append("           product_id,          ");
    sql.append("           product_name,        ");
    sql.append("           score                ");
    sql.append("       FROM t_product           ");
    sql.append("      WHERE score >= ?          ");
    sql.append("        AND score <= ?          ");
    sql.append("   ORDER BY rate DESC ) tb      ");
    sql.append("  ORDER BY rand() LIMIT 8       ");

    params.add(lowScore);
    types.add(Types.DOUBLE);

    params.add(highScore);
    types.add(Types.DOUBLE);

    return jdbcTemplate.query(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new BeanPropertyRowMapper<Product>(Product.class));
  }

  public List<ProductImg> selectProductImg(String productId) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.product_id,          ");
    sql.append("        tb.name,                ");
    sql.append("        tb.extension            ");
    sql.append("   FROM t_product_img tb        ");
    sql.append("  WHERE tb.product_id = ?       ");

    params.add(productId);
    types.add(Types.VARCHAR);

    return jdbcTemplate.query(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new BeanPropertyRowMapper<ProductImg>(ProductImg.class));
  }

  public List<ProductImg> selectRecommendImg() {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT img.product_id,                     ");
    sql.append("        img.name,                           ");
    sql.append("        img.extension                       ");
    sql.append("   FROM t_product pdct,                     ");
    sql.append("        t_product_recommend_img img         ");
    sql.append("  WHERE pdct.product_id = img.product_id    ");
    sql.append("    AND pdct.is_recommend = " + Constants.ENABLE);

    return jdbcTemplate.query(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new BeanPropertyRowMapper<ProductImg>(ProductImg.class));
  }

  public Product selectFlashSaleProduct() {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT pdct.product_id,                ");
    sql.append("        pdct.product_name,              ");
    sql.append("        pdct.on_sale_date,              ");
    sql.append("        pdct.countdown,                 ");
    sql.append("        score                           ");
    sql.append("   FROM t_product pdct,                 ");
    sql.append("        t_product_flash_sale_img tb     ");
    sql.append("  WHERE pdct.product_id = tb.product_id ");
    sql.append("    AND pdct.is_flash_sale = " + Constants.ENABLE);
    sql.append("    AND pdct.on_sale_date is not null   ");
    sql.append("    AND pdct.countdown is not null      ");
    sql.append("  LIMIT 1                               ");

    return jdbcTemplate.queryForObject(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new BeanPropertyRowMapper<Product>(Product.class));
  }

  public List<ProductImg> selectFlashSaleImg(String productId) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.product_id,                  ");
    sql.append("        tb.name,                        ");
    sql.append("        tb.extension                    ");
    sql.append("   FROM t_product_flash_sale_img tb     ");
    sql.append("  WHERE tb.product_id = ?               ");

    params.add(productId);
    types.add(Types.VARCHAR);

    return jdbcTemplate.query(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new BeanPropertyRowMapper<ProductImg>(ProductImg.class));
  }

  public ProductImg selectImgBlock(String position) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.product_id,              ");
    sql.append("        tb.name,                    ");
    sql.append("        tb.extension,               ");
    sql.append("        tb.size                     ");
    sql.append("   FROM t_product_block_img tb      ");
    sql.append("  WHERE tb.position = ?             ");

    params.add(position);
    types.add(Types.VARCHAR);

    return jdbcTemplate.queryForObject(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new CustomBeanPropertyRowMapper<ProductImg>(ProductImg.class));
  }

}
