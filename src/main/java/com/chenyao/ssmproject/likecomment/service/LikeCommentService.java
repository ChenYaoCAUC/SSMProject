package com.chenyao.ssmproject.likecomment.service;

import com.chenyao.ssmproject.likecomment.model.LikeComment;

public interface LikeCommentService {
	public Integer select(LikeComment likeComment);
	public Integer selectcount(LikeComment likeComment);
	public int insert(String username,String id);
	public int delete(String username);
}
