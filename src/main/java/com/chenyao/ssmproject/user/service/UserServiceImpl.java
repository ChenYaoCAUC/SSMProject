package com.chenyao.ssmproject.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenyao.ssmproject.user.dao.UserMapper;
import com.chenyao.ssmproject.user.model.User;
//代表一个服务
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper dao;
	@Override
	public List<User> signin(User user) {
		return dao.signin(user);
	}
	public int signup(User user) {
		return dao.signup(user);
	}

}
