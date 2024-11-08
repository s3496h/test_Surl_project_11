package com.koreait.Surl_project_11.domain.article.article.repository;

import com.koreait.Surl_project_11.domain.article.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
 }
