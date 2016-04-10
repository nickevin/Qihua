package com.qihua.console.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qihua.exception.NullObjectException;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> find() {
    return categoryRepository.selectAll();
  }

  public Category find(final Long categoryId) throws NullObjectException {
    try {
      return categoryRepository.selectOne(categoryId);
    } catch (EmptyResultDataAccessException e) {
      throw new NullObjectException();
    }
  }

  public List<Category> findCategories() {
    return categoryRepository.selectCategory();
  }

  public List<Category> findSubcategories() {
    return categoryRepository.selectAll();
  }

  @Transactional(rollbackFor = Exception.class)
  public Category save(final Category item) throws Exception {
    if (item.getCategoryId() == null) {
      return categoryRepository.insert(item);
    } else {
      return categoryRepository.update(item);
    }
  }

}
