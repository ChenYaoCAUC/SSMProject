package com.chenyao.ssmproject.user.service;

import java.util.List;


import com.chenyao.ssmproject.user.model.User;

public interface UserService {
	public int signup(User user);
	public int signupauthority(String username);
	public int signup_info(String username);
	public int updatepeople(String username, int sex,String introduction);
	public int updateimage(String username,String avatar);
	public List<User> getImage(String username);
	public List<User> getinfomation(String username);
	
}
