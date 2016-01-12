package com.qihua.console.product;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.qihua.common.CustomBeanPropertyRowMapper;
import com.qihua.common.GenericDAO;

@Repository
public class ProductDAO extends GenericDAO<Product, String> {

  public List<Product> select(ProductQueryParameter queryParam) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT pdct.product_id,                        ");
    sql.append("        pdct.product_code,                      ");
    sql.append("        pdct.product_name,                      ");
    sql.append("        pdct.score,                             ");
    sql.append("        pdct.rate,                              ");
    sql.append("        pdct.is_recommend,                      ");
    sql.append("        pdct.is_flash_sale,                     ");
    sql.append("        pdct.on_sale_date,                      ");
    sql.append("        pdct.countdown,                         ");
    sql.append("        pc.category_id,                         ");
    sql.append("        pc.category_name,                       ");
    sql.append("        pc.subcategory_id,                      ");
    sql.append("        pc.subcategory_name,                    ");
    sql.append("        pdct.insert_time                        ");
    sql.append("   FROM                                         ");
    sql.append("       t_product pdct,                          ");
    sql.append("       t_product_category pc                    ");
    sql.append("   WHERE                                        ");
    sql.append("       pdct.category_id = pc.subcategory_id     ");

    String productName = queryParam.getProductName();
    if (StringUtils.isNotEmpty(productName)) {
      sql.append("   AND pdct.product_name LIKE ? ");
      params.add(productName + "%");
      types.add(Types.VARCHAR);
    }

    String lowScore = queryParam.getLowScore();
    if (StringUtils.isNotEmpty(lowScore)) {
      sql.append("   AND pdct.score >= ? ");
      params.add(lowScore);
      types.add(Types.INTEGER);
    }

    String highScore = queryParam.getHighScore();
    if (StringUtils.isNotEmpty(highScore)) {
      sql.append("   AND pdct.score <= ? ");
      params.add(highScore);
      types.add(Types.INTEGER);
    }

    String categoryId = queryParam.getCategoryId();
    if (StringUtils.isNotEmpty(categoryId)) {
      sql.append("   AND pc.category_id = ? ");
      params.add(categoryId);
      types.add(Types.VARCHAR);
    }

    return jdbcTemplate.query(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new CustomBeanPropertyRowMapper<Product>(Product.class));
  }

  public void updateRate(String productId, int rate) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder("UPDATE t_product SET rate = ? WHERE product_id = ?");

    params.add(rate);
    types.add(Types.INTEGER);

    params.add(productId);
    types.add(Types.VARCHAR);

    jdbcTemplate.update(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])));
  }

  public String insertImage(ProductImg img) {
    StringBuilder sql = new StringBuilder();
    sql.append(" INSERT INTO t_product_img      ");
    sql.append("   (name,                       ");
    sql.append("    size,                       ");
    sql.append("    extension,                  ");
    sql.append("    product_id,                 ");
    sql.append("    insert_time,                ");
    sql.append("    update_time)                ");
    sql.append(" VALUES                         ");
    sql.append("   (?,                          ");
    sql.append("    ?,                          ");
    sql.append("    ?,                          ");
    sql.append("    ?,                          ");
    sql.append("    ?,                          ");
    sql.append("    ?)                          ");

    jdbcTemplate.update(sql.toString(),
        new Object[] {img.getName(), img.getSize(), img.getExtension(), img.getProductId(), img.getInsertTime(),
            img.getUpdateTime()},
        new int[] {Types.VARCHAR, Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP});

    return img.getName();
  }

  public void updateImage(String productId, String imgName) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder("UPDATE t_product_img SET product_id = ? WHERE name = ? ");

    params.add(productId);
    types.add(Types.VARCHAR);

    params.add(imgName);
    types.add(Types.VARCHAR);

    jdbcTemplate.update(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])));
  }

  public List<ProductImg> selectImg(String productId) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.product_id,       ");
    sql.append("        tb.name,             ");
    sql.append("        tb.extension,        ");
    sql.append("        tb.size              ");
    sql.append("   FROM t_product_img tb     ");
    sql.append("  WHERE tb.product_id = ?    ");

    params.add(productId);
    types.add(Types.VARCHAR);

    return jdbcTemplate.query(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new CustomBeanPropertyRowMapper<ProductImg>(ProductImg.class));
  }

  public void deleteImg(String imgName) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder("DELETE FROM t_product_img WHERE name = ? ");

    params.add(imgName);
    types.add(Types.VARCHAR);

    jdbcTemplate.update(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])));
  }

  public List<ProductImg> selectRecommendImg(String productId) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.product_id,                  ");
    sql.append("        tb.name,                        ");
    sql.append("        tb.extension,                   ");
    sql.append("        tb.size                         ");
    sql.append("   FROM t_product_recommend_img tb      ");
    sql.append("  WHERE tb.product_id = ?               ");

    params.add(productId);
    types.add(Types.VARCHAR);

    return jdbcTemplate.query(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new CustomBeanPropertyRowMapper<ProductImg>(ProductImg.class));
  }

  public List<ProductImg> selectFlashSaleImg(String productId) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.product_id,              ");
    sql.append("        tb.name,                    ");
    sql.append("        tb.extension,               ");
    sql.append("        tb.size                     ");
    sql.append("   FROM t_product_flash_sale_img tb ");
    sql.append("  WHERE tb.product_id = ?           ");

    params.add(productId);
    types.add(Types.VARCHAR);

    return jdbcTemplate.query(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new CustomBeanPropertyRowMapper<ProductImg>(ProductImg.class));
  }

  public String insertRecommendImg(ProductImg img) {
    StringBuilder sql = new StringBuilder();
    sql.append(" INSERT INTO t_product_recommend_img      ");
    sql.append("   (name,                                 ");
    sql.append("    size,                                 ");
    sql.append("    extension,                            ");
    sql.append("    product_id,                           ");
    sql.append("    insert_time,                          ");
    sql.append("    update_time)                          ");
    sql.append(" VALUES                                   ");
    sql.append("   (?,                                    ");
    sql.append("    ?,                                    ");
    sql.append("    ?,                                    ");
    sql.append("    ?,                                    ");
    sql.append("    ?,                                    ");
    sql.append("    ?)                                    ");

    jdbcTemplate.update(sql.toString(),
        new Object[] {img.getName(), img.getSize(), img.getExtension(), img.getProductId(), img.getInsertTime(),
            img.getUpdateTime()},
        new int[] {Types.VARCHAR, Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP});

    return img.getName();
  }

  public String insertFlashSaleImage(ProductImg img) {
    StringBuilder sql = new StringBuilder();
    sql.append(" INSERT INTO t_product_flash_sale_img     ");
    sql.append("   (name,                                 ");
    sql.append("    size,                                 ");
    sql.append("    extension,                            ");
    sql.append("    product_id,                           ");
    sql.append("    insert_time,                          ");
    sql.append("    update_time)                          ");
    sql.append(" VALUES                                   ");
    sql.append("   (?,                                    ");
    sql.append("    ?,                                    ");
    sql.append("    ?,                                    ");
    sql.append("    ?,                                    ");
    sql.append("    ?,                                    ");
    sql.append("    ?)                                    ");

    jdbcTemplate.update(sql.toString(),
        new Object[] {img.getName(), img.getSize(), img.getExtension(), img.getProductId(), img.getInsertTime(),
            img.getUpdateTime()},
        new int[] {Types.VARCHAR, Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP});

    return img.getName();
  }

  public void deleteRecommendImg(String imgName) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder("DELETE FROM t_product_recommend_img WHERE name = ? ");

    params.add(imgName);
    types.add(Types.VARCHAR);

    jdbcTemplate.update(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])));
  }

  public void deleteFlashSaleImg(String imgName) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder("DELETE FROM t_product_flash_sale_img WHERE name = ? ");

    params.add(imgName);
    types.add(Types.VARCHAR);

    jdbcTemplate.update(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])));
  }

  public String insertImageBlock(ProductImg img) {
    StringBuilder sql = new StringBuilder();
    sql.append(" INSERT INTO t_product_block_img         ");
    sql.append("   (name,                                ");
    sql.append("    size,                                ");
    sql.append("    extension,                           ");
    sql.append("    product_id,                          ");
    sql.append("    position,                            ");
    sql.append("    insert_time,                         ");
    sql.append("    update_time)                         ");
    sql.append(" VALUES                                  ");
    sql.append("   (?,                                   ");
    sql.append("    ?,                                   ");
    sql.append("    ?,                                   ");
    sql.append("    ?,                                   ");
    sql.append("    ?,                                   ");
    sql.append("    ?,                                   ");
    sql.append("    ?)                                   ");

    jdbcTemplate.update(sql.toString(),
        new Object[] {img.getName(), img.getSize(), img.getExtension(), img.getProductId(), img.getPostion(),
            img.getInsertTime(), img.getUpdateTime()},
        new int[] {Types.VARCHAR, Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,
            Types.TIMESTAMP});

    return img.getName();
  }

  public ProductImg selectImgBlockByPosition(String position) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.product_id,              ");
    sql.append("        tb.name,                    ");
    sql.append("        tb.extension,               ");
    sql.append("        tb.size                     ");
    sql.append("   FROM t_product_block_img tb      ");
    sql.append("  WHERE tb.position = ?             ");
    sql.append("  LIMIT 1                           ");

    params.add(position);
    types.add(Types.VARCHAR);

    return jdbcTemplate.queryForObject(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new CustomBeanPropertyRowMapper<ProductImg>(ProductImg.class));
  }

  public void deleteImgBlock(String imgName) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder("DELETE FROM t_product_block_img WHERE name = ? ");

    params.add(imgName);
    types.add(Types.VARCHAR);

    jdbcTemplate.update(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])));
  }

  public void updateProductId(String imgName, String productId) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder("UPDATE t_product_block_img SET product_id = ? WHERE name = ? ");

    params.add(productId);
    types.add(Types.VARCHAR);

    params.add(imgName);
    types.add(Types.VARCHAR);

    jdbcTemplate.update(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])));
  }

  public ProductImg selectImgByImgName(String imgName) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.product_id,       ");
    sql.append("        tb.name,             ");
    sql.append("        tb.extension,        ");
    sql.append("        tb.size              ");
    sql.append("   FROM t_product_img tb     ");
    sql.append("  WHERE tb.name = ?          ");

    params.add(imgName);
    types.add(Types.VARCHAR);

    return jdbcTemplate.queryForObject(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new CustomBeanPropertyRowMapper<ProductImg>(ProductImg.class));
  }

  public ProductImg selectRecommendImgByImgName(String imgName) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.product_id,                  ");
    sql.append("        tb.name,                        ");
    sql.append("        tb.extension,                   ");
    sql.append("        tb.size                         ");
    sql.append("   FROM t_product_recommend_img tb      ");
    sql.append("  WHERE tb.name = ?                     ");

    params.add(imgName);
    types.add(Types.VARCHAR);

    return jdbcTemplate.queryForObject(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new CustomBeanPropertyRowMapper<ProductImg>(ProductImg.class));
  }

  public ProductImg selectFlashSaleImgByImgName(String imgName) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.product_id,                      ");
    sql.append("        tb.name,                            ");
    sql.append("        tb.extension,                       ");
    sql.append("        tb.size                             ");
    sql.append("   FROM t_product_flash_sale_img tb         ");
    sql.append("  WHERE tb.name = ?                         ");

    params.add(imgName);
    types.add(Types.VARCHAR);

    return jdbcTemplate.queryForObject(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new CustomBeanPropertyRowMapper<ProductImg>(ProductImg.class));
  }

  public ProductImg selectImgBlockByImgName(String imgName) {
    List<Object> params = new ArrayList<Object>();
    List<Integer> types = new ArrayList<Integer>();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT tb.product_id,                  ");
    sql.append("        tb.name,                        ");
    sql.append("        tb.extension,                   ");
    sql.append("        tb.size                         ");
    sql.append("   FROM t_product_block_img tb          ");
    sql.append("  WHERE tb.name = ?                     ");

    params.add(imgName);
    types.add(Types.VARCHAR);

    return jdbcTemplate.queryForObject(sql.toString(), params.toArray(),
        ArrayUtils.toPrimitive(types.toArray(new Integer[types.size()])),
        new CustomBeanPropertyRowMapper<ProductImg>(ProductImg.class));
  }
}
