package com.qihua.front.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qihua.exception.NullObjectException;

@Service
public class CategoryService {

  @Autowired
  private CategoryDAO categoryDAO;

  // @Cacheable(value = "category_findOne")
  public Category find(Long categoryId) throws NullObjectException {
    try {
      return categoryDAO.selectCategory(categoryId);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  // @Cacheable(value = "category_findCategories")
  public List<Category> findCategories() {
    List<Category> categoryList = categoryDAO.selectCategory();
    for (Category item : categoryList) {
      item.setSubcategories(findSubcategories(item.getCategoryId()));
    }

    return categoryList;
  }

  // @Cacheable(value = "category_findSubcategoryBySubcatgeoryId")
  public Category findSubcategory(Long subcatgeoryId) {
    return categoryDAO.selectSubcategory(subcatgeoryId);
  }

  // @Cacheable(value = "category_findSubcategories")
  public List<Category> findSubcategories() {
    return categoryDAO.select();
  }

  // @Cacheable(value = "category_findSubcategoriesByCatgeoryId")
  public List<Category> findSubcategories(Long catgeoryId) {
    return categoryDAO.selectSubcategoryByCatgeoryId(catgeoryId);
  }

}
