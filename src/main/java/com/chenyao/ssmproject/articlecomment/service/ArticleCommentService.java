package com.chenyao.ssmproject.articlecomment.service;

import java.util.List;

import com.chenyao.ssmproject.articlecomment.model.ArticleComment;

public interface ArticleCommentService {
	public List<ArticleComment> refresh(String id);
	public int insertArticleComment(ArticleComment articleComment);
	public int deleteArticleComment(String id);
}
