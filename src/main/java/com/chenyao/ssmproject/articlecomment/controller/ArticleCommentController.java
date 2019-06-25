package com.chenyao.ssmproject.articlecomment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.chenyao.ssmproject.articlecomment.model.ArticleComment;
import com.chenyao.ssmproject.articlecomment.service.ArticleCommentImpl;
import com.chenyao.ssmproject.util.Status;

@Controller
@RequestMapping("/articlecomment")
public class ArticleCommentController {
	@Autowired
	private ArticleCommentImpl service;
	@ResponseBody
	@RequestMapping("/select")
	public Map<String, Object> refresh(@RequestParam("id") String id,
			HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();	
		try {
			List<ArticleComment> articleComments = service.refresh(id);
			data.put("status", Status.SUCCESS);
			data.put("articlecomment", articleComments);
			data.put("reason", "OK");
		} catch (Exception e) {
			data.put("status", Status.DB_ERROR);
			data.put("reason", "数据库错误");
			e.printStackTrace();
		}
		return data;	
	}
	@ResponseBody
	@RequestMapping("/insert")
	public Map<String, Object> insertArticleComment(ArticleComment articleComment,
			HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			articleComment.setComment_id(UUID.randomUUID().toString());
			articleComment.setUsername(getAuthenticatedUsername().toString());
			service.insertArticleComment(articleComment);
			data.put("status", Status.SUCCESS);
			data.put("reason", "OK");
		} catch (Exception e) {
			data.put("status", Status.DB_ERROR);
			data.put("reason", "数据库错误");
			e.printStackTrace();
		}
		return data;
			
	}
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> deleteArticleCommet(ArticleComment articleComment,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			List<ArticleComment> articleComments = service.refresh(articleComment.getComment_id());
			if (articleComments.isEmpty()) {
				data.put("status", Status.DB_ERROR);
				data.put("reason", "评论不存在");
			}
			if (!getAuthenticatedUsername().equals(articleComments.get(0).getUsername())) {
				data.put("status", Status.ACCESS_DENIED_ERROR);
				data.put("reason", "只能删除自己的评论！");
			} else {
				service.deleteArticleComment(articleComment.getComment_id());
				data.put("status", Status.SUCCESS);
				data.put("reason", "OK");
			}
		} catch (Exception e) {
			data.put("status", Status.DB_ERROR);
			data.put("reason", "数据库错误");
		}
		return data;
	}
	
	/**
	 * 从Spring Security 组件中获得登录用户名
	 * @return
	 */
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
