package com.chenyao.ssmproject.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenyao.ssmproject.article.dao.ArticleMapper;
import com.chenyao.ssmproject.article.model.Article;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleMapper dao;
	@Override
	public List<Article> refresh() {
		// TODO Auto-generated method stub
		return dao.refresh();
	}

	@Override
	public List<Article> refreshByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.refreshByUsername(username);
	}

	@Override
	public List<Article> refreshByTitle(String title) {
		// TODO Auto-generated method stub
		return dao.refreshByTitle(title);
	}

	@Override
	public List<Article> refreshByUuid(String uuid) {
		// TODO Auto-generated method stub
		return dao.refreshByUuid(uuid);
	}

	@Override
	public int insert(Article article) {
		// TODO Auto-generated method stub
		return dao.insert(article);
	}

	@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		return dao.update(article);
	}

	@Override
	public int delete(String uuid) {
		// TODO Auto-generated method stub
		return dao.delete(uuid);
	}

}
