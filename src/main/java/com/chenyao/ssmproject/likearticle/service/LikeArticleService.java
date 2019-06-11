package com.chenyao.ssmproject.likearticle.service;

import org.springframework.stereotype.Service;

import com.chenyao.ssmproject.likearticle.model.LikeArticle;
@Service
public interface LikeArticleService {
	public int insert(String id, String name);
	public int delete(String id, String name);
	public Integer selectLikeArticle(LikeArticle likeArticle );
	public Integer selectCount(LikeArticle likeArticle);
}
