package com.qihua.front.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qihua.exception.NullObjectException;

@Service
public class ArticleService {

  @Autowired
  private ArticleRepository articleRepository;

  public List<Article> find() {
    return articleRepository.selectAll();
  }

  public Article find(final Long id) throws NullObjectException {
    try {
      return articleRepository.selectOne(id);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  @Transactional(rollbackFor = Exception.class)
  public Article save(final Article item) throws Exception {
    if (item.getArticleId() == null) {
      return articleRepository.insert(item);
    }

    return articleRepository.update(item);
  }

  public Article findByArticleType(final int articleType) {
    return articleRepository.selectByArticleType(articleType);
  }

}
