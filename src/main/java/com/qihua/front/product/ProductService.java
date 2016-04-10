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

import com.qihua.common.repository.PageModel;
import com.qihua.exception.NullObjectException;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  // @Autowired
  // private RedisTemplate<String, Object> redisTemplate;

  @Autowired
  private CategoryService categoryService;

  private final static String CACHE_PREFIX_NAME = "\"Product.";

  public List<Product> find() {
    return productRepository.selectAll();
  }

  @Caching(cacheable = {@Cacheable(value = "Product", key = CACHE_PREFIX_NAME + "queryParam:\" + #queryParam")})
  public PageModel<Product> search(final ProductQueryParameter queryParam) {
    // ValueOperations<String, Object> ops = redisTemplate.opsForValue();
    // Product pdct = (Product) ops.get("Product.findFlashSaleProduct");
    // System.out.println(pdct);

    PageModel<Product> pageModel = productRepository.selectByPagination(queryParam);
    List<Product> list = pageModel.getContent();
    for (Product item : list) {
      item.setImages(productRepository.selectProductImg(item.getProductId()));
    }

    return pageModel;
  }

  @Caching(cacheable = {@Cacheable(value = "Product", key = CACHE_PREFIX_NAME + "productId:\" + #productId")})
  public Product find(final String productId) throws NullObjectException {
    try {
      Product product = productRepository.selectOne(productId);
      product.setCategory(categoryService.findSubcategory(product.getCategoryId()));
      product.setImages(productRepository.selectProductImg(productId));

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
      return productRepository.insert(product);
    }

    return productRepository.update(product);
  }

  @Caching(cacheable = {@Cacheable(value = "Product", key = CACHE_PREFIX_NAME + "findHottest\"")})
  public List<Product> findHottest() {
    List<Product> list = productRepository.selectHottest();
    for (Product item : list) {
      item.setImages(productRepository.selectProductImg(item.getProductId()));
    }

    return list;
  }

  @Caching(cacheable = {
      @Cacheable(value = "Product", key = CACHE_PREFIX_NAME + "findByScore:\" + #lowScore + \" to \"+ #highScore")})
  public List<Product> findByScore(final int lowScore, final int highScore) {
    List<Product> list = productRepository.selectByScore(lowScore, highScore);
    for (Product item : list) {
      item.setImages(productRepository.selectProductImg(item.getProductId()));
    }

    return list;
  }

  @Caching(cacheable = {@Cacheable(value = "Product", key = CACHE_PREFIX_NAME + "findRecommendImg\"")})
  public List<ProductImg> findRecommendImg() {
    return productRepository.selectRecommendImg();
  }

  @Caching(cacheable = {@Cacheable(value = "Product", key = CACHE_PREFIX_NAME + "findFlashSaleProduct\"")})
  public Product findFlashSaleProduct() {
    Product product;

    try {
      product = productRepository.selectFlashSaleProduct();
      product.setFlashSaleImages(productRepository.selectFlashSaleImg(product.getProductId()));
    } catch (DataAccessException e) {
      return new Product();
    }

    return product;
  }

  @Caching(cacheable = {@Cacheable(value = "Product", key = CACHE_PREFIX_NAME + "findImgBlock:\" + #position")})
  public ProductImg findImgBlock(final String position) {
    try {
      return productRepository.selectImgBlock(position);
    } catch (DataAccessException e) {
      return new ProductImg();
    }
  }
}
