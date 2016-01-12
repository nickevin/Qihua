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
    private CategoryDAO categoryDAO;

    public List<Category> find() {
        return categoryDAO.select();
    }

    public Category find(Long categoryId) throws NullObjectException {
        try {
            return categoryDAO.select(categoryId);
        } catch (EmptyResultDataAccessException e) {
            throw new NullObjectException();
        }
    }

    public List<Category> findCategories() {
        return categoryDAO.selectCategory();
    }

    public List<Category> findSubcategories() {
        return categoryDAO.select();
    }

    @Transactional(rollbackFor = Exception.class)
    public Category save(Category item) throws Exception {
        if (item.getCategoryId() == null) {
            return categoryDAO.insert(item);
        } else {
            return categoryDAO.update(item);
        }
    }

}
