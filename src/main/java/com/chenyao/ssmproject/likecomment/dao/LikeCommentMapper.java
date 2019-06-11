package com.chenyao.ssmproject.likecomment.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.chenyao.ssmproject.likecomment.model.LikeComment;

@Repository
public interface LikeCommentMapper {
	@Insert("insert into like_comment values(#{username},#{comment_id});")
	public int insert(@Param("username") String username,@Param("comment_id") String id);
	@Delete("delete from like_comment where username=#{username};")
	public int delete(@Param("username") String username);
	public Integer select(LikeComment likeComment);
	public Integer selectcount(LikeComment likeComment);
}
