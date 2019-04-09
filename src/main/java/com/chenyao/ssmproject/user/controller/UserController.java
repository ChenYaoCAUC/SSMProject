package com.chenyao.ssmproject.user.controller;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenyao.ssmproject.user.model.User;
import com.chenyao.ssmproject.user.service.UserServiceImpl;
import com.chenyao.ssmproject.util.Status;

//控制器
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl service;
	
	//把函数返回值写入http的正文，一般是正文。
	@ResponseBody  
	//映射
	@RequestMapping("/signin")
	public Map<String, Object> signin(User user,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			String password = user.getPassword();
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes());
			byte[] b= messageDigest.digest();
			String passwordHashed = Hex.toHexString(b);
			user.setPassword(passwordHashed);
			List<User> users = service.signin(user);
			if(users.size()==1) {
				data.put("status", Status.SUCCESS);
				data.put("reason", "恭喜你:"+user.getUsername()+"登录成功！");
			}else {
				data.put("status", Status.LOGIN_ERROR);
				data.put("reason", "用户名或密码错误");
			}	
		} catch(Exception e) {
			data.put("status", Status.DB_ERROR);
			data.put("reason", "数据库出错了！");
			e.printStackTrace();
		}		
		return data;
	}
	
	@ResponseBody  
	@RequestMapping("/signup")
	public Map<String, Object> signup(User user,HttpServletRequest request,HttpServletResponse reponse) {
		Map<String, Object> data = new HashMap<>();
		if(user.getUsername().length()<6) {
			data.put("status",Status.SUCCESS);
			data.put("reason", "用户名不得小于6位！");
		}else if(user.getPassword().length()<6){
			data.put("status", Status.SUCCESS);
			data.put("reason", "密码不得小于六位！");	
		}else{
			try {
				String password = user.getPassword();
				MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
				messageDigest.update(password.getBytes());
				byte[] b= messageDigest.digest();
				String passwordHashed = Hex.toHexString(b);
				user.setPassword(passwordHashed);
				int users = service.signup(user);
				data.put("status", Status.SUCCESS);
				data.put("reason","恭喜你，注册成功！");
			} catch (Exception e) {
				data.put("status", Status.DB_ERROR);
				data.put("reason","数据库出错了！");
				e.printStackTrace();
			}
		}
		return data;
	}
	
}
