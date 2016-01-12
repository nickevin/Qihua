package com.qihua.front.product;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

  @Autowired
  private CategoryService categoryService;;

  @Cacheable(value = "product_find")
  public List<Product> find() {
    return productDAO.select();
  }

  @Cacheable(value = "product_search")
  public PageModel<Product> search(ProductQueryParameter queryParam) {
    PageModel<Product> pageModel = productDAO.selectByPagination(queryParam);
    List<Product> list = pageModel.getContent();
    for (Product item : list) {
      item.setImages(productDAO.selectProductImg(item.getProductId()));
    }

    return pageModel;
  }

  @Cacheable(value = "product_findOne")
  public Product find(String productId) throws NullObjectException {
    try {
      Product product = productDAO.select(productId);
      product.setCategory(categoryService.findSubcategory(product.getCategoryId()));
      product.setImages(productDAO.selectProductImg(productId));

      return product;
    } catch (DataAccessException e) {
      throw new NullObjectException();
    }
  }

  @Caching(evict = {@CacheEvict(value = "product_find", allEntries = true),
      @CacheEvict(value = "product_search", allEntries = true),
      @CacheEvict(value = "product_findHottest", allEntries = true),
      @CacheEvict(value = "product_findRecommendImg", allEntries = true),
      @CacheEvict(value = "product_findFlashSaleProduct", allEntries = true)})
  @Transactional(rollbackFor = Exception.class)
  public Product save(Product item) throws Exception {
    if (StringUtils.isEmpty(item.getProductId())) {
      return productDAO.insert(item);
    }

    return productDAO.update(item);
  }

  @Cacheable(value = "product_findHottest")
  public List<Product> findHottest() {
    List<Product> list = productDAO.selectHottest();
    for (Product item : list) {
      item.setImages(productDAO.selectProductImg(item.getProductId()));
    }

    return list;
  }

  @Cacheable(value = "product_findByScore")
  public List<Product> findByScore(int lowScore, int highScore) {
    List<Product> list = productDAO.selectByScore(lowScore, highScore);
    for (Product item : list) {
      item.setImages(productDAO.selectProductImg(item.getProductId()));
    }

    return list;
  }

  @Cacheable(value = "product_findRecommendImg")
  public List<ProductImg> findRecommendImg() {
    return productDAO.selectRecommendImg();
  }

  @Cacheable(value = "product_findFlashSaleProduct")
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

  @Cacheable(value = "product_findImgBlock")
  public ProductImg findImgBlock(String position) {
    try {
      return productDAO.selectImgBlock(position);
    } catch (DataAccessException e) {
      return new ProductImg();
    }
  }
}
