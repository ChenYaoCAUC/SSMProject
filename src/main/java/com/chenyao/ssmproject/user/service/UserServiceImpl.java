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
	public int signup(User user) {
		return dao.signup(user);
	}
	@Override
	public int signupauthority(String username) {
		// TODO Auto-generated method stub
		return dao.signupauthority(username);
	}
	@Override
	public int signup_info(String username) {
		// TODO Auto-generated method stub
		return dao.signup_info(username);
	}
	@Override
	public int updatepeople(String username, int sex, String introduction) {
		// TODO Auto-generated method stub
		return dao.updatepeople(username, sex, introduction);
	}
	@Override
	public int updateimage(String username, String avatar) {
		// TODO Auto-generated method stub
		return dao.updateimage(username, avatar);
	}
	@Override
	public List<User> getImage(String username) {
		// TODO Auto-generated method stub
		return dao.getImage(username);
	}
	@Override
	public List<User> getinfomation(String username) {
		// TODO Auto-generated method stub
		return dao.getinfomation(username);
	}

}
