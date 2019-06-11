package com.chenyao.ssmproject.articlecomment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chenyao.ssmproject.articlecomment.model.ArticleComment;
@Repository
public interface ArticleCommentMapper {
	@Select("select * from article_comment where article_id=#{id};")
	public List<ArticleComment> refresh(@Param("id") String id);
	@Insert("insert into article_comment(comment_id,article_id,content,username) values(#{comment_id},#{article_id},#{content},#{username});")
	public int insertArticleComment(ArticleComment articleComment);
	@Delete("delete from article_comment where comment_id=#{id};")
	public int deleteArticleComment(@Param("id") String id);
}
