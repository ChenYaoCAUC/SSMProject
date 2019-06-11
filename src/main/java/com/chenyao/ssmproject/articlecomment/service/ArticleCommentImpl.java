package com.chenyao.ssmproject.articlecomment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.stereotype.Service;

import com.chenyao.ssmproject.articlecomment.dao.ArticleCommentMapper;
import com.chenyao.ssmproject.articlecomment.model.ArticleComment;
@Service
public class ArticleCommentImpl implements ArticleCommentService {
	@Autowired
	public ArticleCommentMapper dao;
	@Override
	public List<ArticleComment> refresh(String id) {
		// TODO Auto-generated method stub
		return dao.refresh(id);
	}
	@Override
	public int insertArticleComment(ArticleComment articleComment) {
		// TODO Auto-generated method stub
		return dao.insertArticleComment(articleComment);
	}
	@Override
	public int deleteArticleComment(String id) {
		// TODO Auto-generated method stub
		return dao.deleteArticleComment(id);
	}

}
