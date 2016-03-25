package com.qihua.front.product;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qihua.common.PageModel;
import com.qihua.exception.NullObjectException;

@Service
public class ProductService {

  @Autowired
  private ProductDAO productDAO;

  // @Autowired
  // private RedisTemplate<String, Object> redisTemplate;

  @Autowired
  private CategoryService categoryService;

  private final static String CACHE_PREFIX_NAME = "\"Product.";

  public List<Product> find() {
    return productDAO.select();
  }

  @Caching(cacheable = {@Cacheable(value = "Product", key = CACHE_PREFIX_NAME + "queryParam:\" + #queryParam")})
  public PageModel<Product> search(final ProductQueryParameter queryParam) {
    // ValueOperations<String, Object> ops = redisTemplate.opsForValue();
    // Product pdct = (Product) ops.get("Product.findFlashSaleProduct");
    // System.out.println(pdct);

    PageModel<Product> pageModel = productDAO.selectByPagination(queryParam);
    List<Product> list = pageModel.getContent();
    for (Product item : list) {
      item.setImages(productDAO.selectProductImg(item.getProductId()));
    }

    return pageModel;
  }

  @Caching(cacheable = {@Cacheable(value = "Product", key = CACHE_PREFIX_NAME + "productId:\" + #productId")})
  public Product find(final String productId) throws NullObjectException {
    try {
      Product product = productDAO.select(productId);
      product.setCategory(categoryService.findSubcategory(product.getCategoryId()));
      product.setImages(productDAO.selectProductImg(productId));

      return product;
    } catch (DataAccessException e) {
      throw new NullObjectException();
    }
  }

  @Caching(put = {@CachePut(value = "Product", key = CACHE_PREFIX_NAME + "productId:\" + #result.productId",
      condition = "#result != null")})
  @Transactional(rollbackFor = Exception.class)
  public Product save(final Product product) throws Exception {
    if (StringUtils.isEmpty(product.getProductId())) {
      return productDAO.insert(product);
    }

    return productDAO.update(product);
  }

  @Caching(cacheable = {@Cacheable(value = "Product", key = CACHE_PREFIX_NAME + "findHottest\"")})
  public List<Product> findHottest() {
    List<Product> list = productDAO.selectHottest();
    for (Product item : list) {
      item.setImages(productDAO.selectProductImg(item.getProductId()));
    }

    return list;
  }

  @Caching(cacheable = {
      @Cacheable(value = "Product", key = CACHE_PREFIX_NAME + "findByScore:\" + #lowScore + \" to \"+ #highScore")})
  public List<Product> findByScore(final int lowScore, final int highScore) {
    List<Product> list = productDAO.selectByScore(lowScore, highScore);
    for (Product item : list) {
      item.setImages(productDAO.selectProductImg(item.getProductId()));
    }

    return list;
  }

  @Caching(cacheable = {@Cacheable(value = "Product", key = CACHE_PREFIX_NAME + "findRecommendImg\"")})
  public List<ProductImg> findRecommendImg() {
    return productDAO.selectRecommendImg();
  }

  @Caching(cacheable = {@Cacheable(value = "Product", key = CACHE_PREFIX_NAME + "findFlashSaleProduct\"")})
  public Product findFlashSaleProduct() {
    Product product;

    try {
      product = productDAO.selectFlashSaleProduct();
      product.setFlashSaleImages(productDAO.selectFlashSaleImg(product.getProductId()));
    } catch (DataAccessException e) {
      return new Product();
    }

    return product;
  }

  @Caching(cacheable = {@Cacheable(value = "Product", key = CACHE_PREFIX_NAME + "findImgBlock:\" + #position")})
  public ProductImg findImgBlock(final String position) {
    try {
      return productDAO.selectImgBlock(position);
    } catch (DataAccessException e) {
      return new ProductImg();
    }
  }
}
