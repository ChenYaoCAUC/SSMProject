package com.chenyao.ssmproject.likecomment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenyao.ssmproject.likecomment.model.LikeComment;
import com.chenyao.ssmproject.likecomment.service.LikeCommentServiceImpl;
import com.chenyao.ssmproject.util.Status;

@RequestMapping("/likecomment")
@Controller
public class LikeCommentController {
	@Autowired
	private LikeCommentServiceImpl service;
	@ResponseBody
	@RequestMapping("/select")
	public Map<String, Object> select(LikeComment likeComment,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			Integer count = service.select(likeComment);
			data.put("status", Status.SUCCESS);
			data.put("reason", "OK!");
			data.put("count", count);
			data.put("comment_id", likeComment.getComment_id());
		} catch (Exception e) {
			// TODO: handle exception
			data.put("status", Status.DB_ERROR);
			data.put("reason", "数据库错误！");
			e.printStackTrace();
		}
		return data;	
	}
	@ResponseBody
	@RequestMapping("/like")
	public Map<String, Object> like(LikeComment likeComment,
			HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		likeComment.setUsername(getAuthenticatedUsername().toString());
		int sum = selectcount(likeComment);
		try {
			if(sum==0)
			{
				service.insert(likeComment.getUsername(),likeComment.getComment_id());
				data.put("status", Status.SUCCESS);
				data.put("reason", "点赞!");
			}else if(sum>0) {
				service.delete(likeComment.getUsername());
				data.put("status", Status.SUCCESS);
				data.put("reason", "取消点赞!");
			}else {
				data.put("status", Status.DB_ERROR);
				data.put("reason", "数据库错误！");			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			data.put("status", Status.DB_ERROR);
			data.put("reason", "数据库错误！");
			e.printStackTrace();
		}
		return data;
	}
	public int selectcount(LikeComment likeComment) {
		try {
			int count = service.selectcount(likeComment);
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
