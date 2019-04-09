package com.chenyao.ssmproject.user.service;

import java.util.List;

import com.chenyao.ssmproject.user.model.User;

public interface UserService {
	public List<User> signin(User user);
	public int signup(User user);
}
