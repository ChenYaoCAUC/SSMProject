package com.chenyao.ssmproject.likecomment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenyao.ssmproject.likecomment.dao.LikeCommentMapper;
import com.chenyao.ssmproject.likecomment.model.LikeComment;

@Service
public class LikeCommentServiceImpl implements LikeCommentService {
	@Autowired
	private LikeCommentMapper dao; 
	@Override
	public int delete(String username) {
		// TODO Auto-generated method stub
		return dao.delete(username);
	}



	@Override
	public int insert(String username, String id) {
		// TODO Auto-generated method stub
		return dao.insert(username, id);
	}



	@Override
	public Integer select(LikeComment likeComment) {
		// TODO Auto-generated method stub
		return dao.select(likeComment);
	}



	@Override
	public Integer selectcount(LikeComment likeComment) {
		// TODO Auto-generated method stub
		return dao.selectcount(likeComment);
	}

}
