package com.chenyao.ssmproject.likearticle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenyao.ssmproject.likearticle.dao.LikeArticleMapper;
import com.chenyao.ssmproject.likearticle.model.LikeArticle;
@Service
public class LikeArticleServiceImpl implements LikeArticleService {
	@Autowired
	private LikeArticleMapper dao;

	@Override
	public int insert(String id, String name) {
		// TODO Auto-generated method stub
		return dao.insert(id, name);
	}

	@Override
	public int delete(String id, String name) {
		// TODO Auto-generated method stub
		return dao.delete(id, name);
	}

	@Override
	public Integer selectCount(LikeArticle likeArticle) {
		// TODO Auto-generated method stub
		return dao.selectCount(likeArticle);
	}

	@Override
	public Integer selectLikeArticle(LikeArticle likeArticle) {
		// TODO Auto-generated method stub
		return dao.selectLikeArticle(likeArticle);
	}
}
