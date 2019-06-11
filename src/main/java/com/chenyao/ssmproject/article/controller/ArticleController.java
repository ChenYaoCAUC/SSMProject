package com.chenyao.ssmproject.article.controller;

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

import com.chenyao.ssmproject.article.model.Article;
import com.chenyao.ssmproject.article.service.ArticleServiceImpl;
import com.chenyao.ssmproject.util.Status;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleServiceImpl service;
	@ResponseBody
	@RequestMapping("/insert")
	public Map<String, Object> insert(Article article,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		try {
			article.setId(UUID.randomUUID().toString());
			article.setAuthor(getAuthenticatedUsername());
			service.insert(article);
			data.put("status", Status.SUCCESS);
			data.put("reason", "OK");
		} catch (Exception e) {
			data.put("status", Status.DB_ERROR);
			data.put("reason", "���ݿ����");
		}
		return data;	
	}
	@ResponseBody
	@RequestMapping("/refresh")
	public Map<String, Object> refresh(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			List<Article> articles = service.refresh();
			data.put("status", Status.SUCCESS);
			data.put("article", articles);
			data.put("reason", "OK");
		} catch (Exception e) {
			data.put("status", Status.DB_ERROR);
			data.put("reason", "���ݿ����");
		}
		return data;	
	}
	@ResponseBody
	@RequestMapping("/refreshByUsername")
	public Map<String, Object> refreshByUsername(Article article,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			List<Article> articles = service.refreshByUsername(getAuthenticatedUsername());
			data.put("status", Status.SUCCESS);
			data.put("article", articles);
			data.put("reason", "OK");
		} catch (Exception e) {
			data.put("status", Status.DB_ERROR);
			data.put("reason", "���ݿ����");
		}
		return data;	
	}
	@ResponseBody
	@RequestMapping("/refreshByTitle")
	public Map<String, Object> refreshByTitle(Article article,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			List<Article> articles = service.refreshByTitle(article.getId());
			data.put("status", Status.SUCCESS);
			data.put("article", articles);
			data.put("reason", "OK");
		} catch (Exception e) {
			data.put("status", Status.DB_ERROR);
			data.put("reason", "���ݿ����");
		}
		return data;	
	}
	@ResponseBody
	@RequestMapping("/refreshByUuid")
	public Map<String, Object> refreshByUuid(@RequestParam("id") String id,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			List<Article> articles = service.refreshByUuid(id);
			data.put("status", Status.SUCCESS);
			data.put("article", articles);
			data.put("reason", "OK");
		} catch (Exception e) {
			data.put("status", Status.DB_ERROR);
			data.put("reason", "���ݿ����");
		}
		return data;	
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(Article article,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			List<Article> articles = service.refreshByUuid(article.getId());
			if (articles.isEmpty()) {
				data.put("status", Status.DB_ERROR);
				data.put("reason", "���²�����");
			}
			if (!getAuthenticatedUsername().equals(articles.get(0).getAuthor())) {
				data.put("status", Status.ACCESS_DENIED_ERROR);
				data.put("reason", "ֻ�ܸ����Լ������£�");
			}
		} catch (Exception e) {
			data.put("status", Status.DB_ERROR);
			data.put("reason", "���ݿ����");
		}
		try {
			service.update(article);
			data.put("status", Status.SUCCESS);
			data.put("reason", "OK");
		} catch (Exception e) {
			data.put("status", Status.DB_ERROR);
			data.put("reason", "���ݿ����");
		}
		return data;	
	}
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Article article,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			List<Article> articles = service.refreshByUuid(article.getId());
			if (articles.isEmpty()) {
				data.put("status", Status.DB_ERROR);
				data.put("reason", "���²�����");
			}
			if (!getAuthenticatedUsername().equals(articles.get(0).getAuthor())) {
				data.put("status", Status.ACCESS_DENIED_ERROR);
				data.put("reason", "ֻ��ɾ���Լ������£�");
			}
		} catch (Exception e) {
			data.put("status", Status.DB_ERROR);
			data.put("reason", "���ݿ����");
		}
		try {
			service.delete(article.getId());
			data.put("status", Status.SUCCESS);
			data.put("reason", "OK");
		} catch (Exception e) {
			data.put("status", Status.DB_ERROR);
			data.put("reason", "���ݿ����");
		}
		return data;
	}
	
	/**
	 * ��Spring Security ����л�õ�¼�û���
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
