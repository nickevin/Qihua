package com.qihua.console.article;

import org.springframework.stereotype.Repository;

import com.qihua.common.repository.JdbcRepository;

@Repository
public class ArticleRepository extends JdbcRepository<Article, Long> {


}
