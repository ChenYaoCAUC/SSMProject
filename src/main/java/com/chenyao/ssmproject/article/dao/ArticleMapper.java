package com.chenyao.ssmproject.article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.chenyao.ssmproject.article.model.Article;

@Repository
public interface ArticleMapper {
	@Select("select * from article;")
	public List<Article> refresh();
	@Select("select * from article where author=#{user};")
	public List<Article> refreshByUsername(@Param("user") String username);
	@Select("select * from article where title like #{title};")
	public List<Article> refreshByTitle(@Param("title") String title);
	@Select("select * from article where id=#{id};")
	public List<Article> refreshByUuid(@Param("id") String id);
	@Insert("insert into article values(#{id},#{author},#{title},#{content}); ")
	public int insert(Article article);
	@Update("update article set title=#{title},content=#{content} where id=#{id};")
	public int update(Article article);
	@Delete("delete from article where id=#{id}")
	public int delete(@Param("id") String id);		
	
}
