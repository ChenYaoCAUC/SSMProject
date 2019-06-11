package com.chenyao.ssmproject.user.controller;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
				int users = service.signup(user);
				int authority = service.signupauthority(user.getUsername());
				int user_info = service.signup_info(user.getUsername());
				if(users==1&&authority==1&&user_info==1) {
					data.put("status", Status.SUCCESS);
					data.put("reason","恭喜你，注册成功！");
				}else {
					data.put("status",Status.LOGINUP_ERROR);
					data.put("reason","注册失败！");
				}
				
			} catch (Exception e) {
				data.put("status", Status.DB_ERROR);
				data.put("reason","数据库出错了！");
				e.printStackTrace();
			}
		}
		return data;
	}
	@ResponseBody  
	@RequestMapping("/updatepeople")
	public Map<String, Object> updatesex(User user,HttpServletRequest request,HttpServletResponse reponse) {
		Map<String, Object> data = new HashMap<>();
		String username = getAuthenticatedUsername().toString();
		try {
			service.updatepeople(username, user.getSex(), user.getIntroction());
			data.put("status", Status.SUCCESS);
			data.put("reason", "成功修改性别~");
		} catch (Exception e) {
			// TODO: handle exception
			data.put("status", Status.DB_ERROR);
			data.put("reson", "数据库错误！");
		}
		return data;
	}
	@ResponseBody  
	@RequestMapping("/upload")
	public Map<String, Object> onUpload(HttpServletRequest request,HttpServletResponse reponse) {
		Map<String, Object> data = new HashMap<>();
		//file:/C:/apache-tomcat-9.0.17/wtpwebapps/SSMProject/WEB-INF/classes/
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		path = path.replace("file:/", "");
		path = path.replace("WEB-INF/classes/", "uploads/");
		try {
			File file = resolveUploadFile((MultipartHttpServletRequest) request, path);
			String avatar = "SSMProject/uploads/"+UUID.randomUUID().toString()+file.getName();
			service.updateimage(getAuthenticatedUsername().toString(),avatar);
			data.put("status",Status.SUCCESS);
			data.put("reason", "OK");
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data.put("status",Status.FILEUPLOAD_ERROR);
			data.put("reason", "文件上传失败");
		}
		return data;
	}
	private File resolveUploadFile(MultipartHttpServletRequest request,String path) throws IllegalStateException, IOException{
		//MultipartHttpServletRequest 继承了HttpServletRequest
		// mimeType:"multipart/form-data"
		MultipartFile multipartFile = request.getFile("file");
		new File(path).mkdirs();
		File targetFile = new File(path+multipartFile.getOriginalFilename());
		multipartFile.transferTo(targetFile);
		return targetFile;		
	}
	public static String getAuthenticatedUsername() { 
		String username = null; 
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		if (principal instanceof UserDetails) { 
			username = ((UserDetails) principal).getUsername(); 
		} else { 
			username = principal.toString(); 
		} 
		return username; 
	}
	
}
