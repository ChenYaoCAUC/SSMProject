package com.chenyao.ssmproject.likearticle.controller;

import java.util.HashMap;
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

import com.chenyao.ssmproject.likearticle.model.LikeArticle;
import com.chenyao.ssmproject.likearticle.service.LikeArticleServiceImpl;
import com.chenyao.ssmproject.util.Status;

@Controller
@RequestMapping("/likearticle")
public class LikeArticleController {
	private static Logger log = LogManager.getLogger();
	@Autowired
	private LikeArticleServiceImpl service;
	@ResponseBody
	@RequestMapping("like")
	public Map<String, Object> like(LikeArticle likeArticle,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();	
		String name = getAuthenticatedUsername().toString();
		likeArticle.setUsername(name);
		int count = selectcount(likeArticle);
		System.out.println("现在的用户为："+name);
		try {
			if(count == 0) {
				service.insert(name,likeArticle.getArticle_id());
				data.put("status",Status.SUCCESS);
				data.put("reason", "点赞！");
			}else if(count == -1){
				data.put("status",Status.DB_ERROR);
				data.put("reason", "数据库错误");
			}else {
				service.delete(likeArticle.getArticle_id(),name);
				data.put("status",Status.SUCCESS);
				data.put("reason", "取消点赞！");
				}
			}catch (Exception e){
			// TODO: handle exception
			data.put("status",Status.DB_ERROR);
			data.put("reason", "数据库错误");
			e.printStackTrace();
		}
		return data;
	}
	@ResponseBody
	@RequestMapping("select")
	public Map<String, Object> select(LikeArticle likeArticle,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> data = new HashMap<>();
		try {
			int count = service.selectLikeArticle(likeArticle);
			data.put("status", Status.SUCCESS);
			data.put("count", count);
			data.put("article_id", likeArticle.getArticle_id());
		} catch (Exception e) {
			// TODO: handle exception
			data.put("status", Status.DB_ERROR);
			data.put("reason", "数据库错误");
			e.printStackTrace();
		}
		return data;	
	}
	public int selectcount(LikeArticle likeArticle) {
		try {
			int count = service.selectCount(likeArticle);
			log.debug("点赞数量为："+count);
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
