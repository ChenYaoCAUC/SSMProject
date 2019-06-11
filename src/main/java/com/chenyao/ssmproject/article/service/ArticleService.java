package com.chenyao.ssmproject.article.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chenyao.ssmproject.article.model.Article;
@Service
public interface ArticleService {
	public List<Article> refresh();
	public List<Article> refreshByUsername(String username);
	public List<Article> refreshByTitle(String title);
	public List<Article> refreshByUuid(String id);
	public int insert(Article article);
	public int update(Article article);
	public int delete(String id);	
}
