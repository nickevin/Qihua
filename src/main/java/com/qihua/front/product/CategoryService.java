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

  public Category find(final Long categoryId) throws NullObjectException {
    try {
      return categoryDAO.selectCategory(categoryId);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  public List<Category> findCategories() {
    List<Category> categoryList = categoryDAO.selectCategory();
    for (Category item : categoryList) {
      item.setSubcategories(findSubcategories(item.getCategoryId()));
    }

    return categoryList;
  }

  public Category findSubcategory(final Long subcatgeoryId) {
    return categoryDAO.selectSubcategory(subcatgeoryId);
  }

  public List<Category> findSubcategories() {
    return categoryDAO.select();
  }

  public List<Category> findSubcategories(final Long catgeoryId) {
    return categoryDAO.selectSubcategoryByCatgeoryId(catgeoryId);
  }

}
