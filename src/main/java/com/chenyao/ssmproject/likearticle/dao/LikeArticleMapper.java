package com.chenyao.ssmproject.likearticle.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chenyao.ssmproject.likearticle.model.LikeArticle;

@Repository
public interface LikeArticleMapper {
	@Insert("insert into like_article values(#{username},#{article_id});")
	public int insert(@Param("article_id") String id,@Param("username") String name);
	@Delete("delete from like_article where article_id=#{article_id} and username=#{username};")
	public int delete(@Param("article_id") String id,@Param("username") String name);
	public Integer selectLikeArticle(LikeArticle likeArticle);
	public Integer selectCount(LikeArticle likeArticle);
}
