package com.qihua.console.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qihua.exception.NullObjectException;

@Service
public class ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    public List<Article> find() {
        return articleDAO.select();
    }

    public Article find(Long id) throws NullObjectException {
        try {
            return articleDAO.select(id);
        } catch (Exception e) {
            throw new NullObjectException();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Article save(Article item) throws Exception {
        if (item.getArticleId() == null) {
            return articleDAO.insert(item);
        }

        return articleDAO.update(item);
    }

}
