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

//������
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl service;
	
	//�Ѻ�������ֵд��http�����ģ�һ�������ġ�
	@ResponseBody  
	//ӳ��
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
				data.put("reason", "��ϲ��:"+user.getUsername()+"��¼�ɹ���");
			}else {
				data.put("status", Status.LOGIN_ERROR);
				data.put("reason", "�û������������");
			}	
		} catch(Exception e) {
			data.put("status", Status.DB_ERROR);
			data.put("reason", "���ݿ�����ˣ�");
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
			data.put("reason", "�û�������С��6λ��");
		}else if(user.getPassword().length()<6){
			data.put("status", Status.SUCCESS);
			data.put("reason", "���벻��С����λ��");	
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
				data.put("reason","��ϲ�㣬ע��ɹ���");
			} catch (Exception e) {
				data.put("status", Status.DB_ERROR);
				data.put("reason","���ݿ�����ˣ�");
				e.printStackTrace();
			}
		}
		return data;
	}
	
}
