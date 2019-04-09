package com.chenyao.ssmproject.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chenyao.ssmproject.user.model.User;
//´ú±ímapper
@Repository
public interface UserMapper {
	@Select("select * from user where username=#{username} and password=#{password};")
	public List<User> signin(User user);
	@Insert("insert into user values(#{username},#{password});")
	public int signup(User user);
}

