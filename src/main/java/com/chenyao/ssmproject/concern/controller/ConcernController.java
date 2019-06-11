package com.chenyao.ssmproject.concern.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenyao.ssmproject.concern.model.ConcernModel;
import com.chenyao.ssmproject.concern.service.ConcernServiceImpl;
import com.chenyao.ssmproject.user.model.User;
import com.chenyao.ssmproject.util.Status;
import com.mysql.cj.log.Log;

@Controller
@RequestMapping("/Concern")
public class ConcernController {
	@Autowired
	private ConcernServiceImpl service;
	private static Logger log = LogManager.getLogger();
	@ResponseBody
	@RequestMapping("/select")
	public Map<String, Object> select(ConcernModel concern,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			List<ConcernModel> concerns = service.select();
			data.put("status", Status.SUCCESS);
			data.put("concern", concerns);
			data.put("reason", "OK");
		} catch (Exception e) {
			// TODO: handle exception
			data.put("status", Status.DB_ERROR);
			data.put("reason", "数据库错误");
			e.printStackTrace();
		}
		return data; 
	}
	@ResponseBody
	@RequestMapping("/selectwasfuns")
	public Map<String, Object> selectwasfuns(ConcernModel concern,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		concern.setFollower(getAuthenticatedUsername().toString());
		try {
			List<String> users = service.selectwasfuns(concern);
			data.put("status", Status.SUCCESS);
			data.put("user", users);	
		} catch (Exception e) {
			// TODO: handle exception
			data.put("status", Status.DB_ERROR);
			data.put("reason", "数据库错误！");
		}
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/concern")
	public Map<String, Object> consern(ConcernModel concern,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		String follower = getAuthenticatedUsername().toString();
		concern.setFollower(follower);
		int count = selectcount(concern);
		try {
			if(count==0) {
				if(concern.getWasfuns()!=follower)
				{
					service.insert(concern);
					data.put("status", Status.SUCCESS);
					data.put("reason", "关注成功！");
				}else {
					data.put("status", Status.PARAM_ERROR);
					data.put("reason", "不能关注自己！");
				}
			}else if(count>0) {
				service.delete(follower);
				data.put("status", Status.SUCCESS);
				data.put("reason", "取消关注！");
			}else {
				data.put("status", Status.DB_ERROR);
				data.put("reason", "数据库错误");
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			data.put("status", Status.DB_ERROR);
			data.put("reason", "数据库错误");
			e.printStackTrace();
		}
		return data; 
	}
	@ResponseBody
	@RequestMapping("/concerncount")
	public Map<String, Object> concerncount(ConcernModel concern,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		String follower = getAuthenticatedUsername().toString();
		concern.setFollower(follower);
		int count = selectnum(concern);
		data.put("status", Status.SUCCESS);
		data.put("count", count);
		return data;
	}

	private int selectnum(ConcernModel concern) {
		try {
			int count = service.selectnum(concern);
			return count;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}
	public int selectcount(ConcernModel concern){
		try {
			int count = service.selectcount(concern);
			return count;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
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
