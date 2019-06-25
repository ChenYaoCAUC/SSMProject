package com.chenyao.ssmproject.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.chenyao.ssmproject.user.model.User;
//´ú±ímapper
@Repository
public interface UserMapper {
	@Select("select avatar from user_info where username = #{username};")
	public List<User> getImage(@Param("username") String username);

	/*
	 * @Select("select introduction from user_info where username = #{username};")
	 */
	@Select("select introduction,sex from user_info where username= #{username};")
	public List<User> getinfomation(@Param("username") String username);
	@Insert("insert into users(username,password,enabled) values(#{username},#{password},1);")
	public int signup(User user);
	@Insert("INSERT INTO authorities(username, authority) VALUES (#{username}, 'authenticated');")
	public int signupauthority(@Param("username") String username);
	@Insert("insert into user_info(username,sex,introduction) values(#{username},0,'');")
	public int signup_info(@Param("username") String username);
	@Update("update user_info set sex=#{sex},introduction=#{introduction} where username=#{username};")
	public int updatepeople(@Param("username") String username,@Param("sex") int sex,@Param("introduction") String introduction);
	@Update("update user_info set avatar=#{avatar} where username=#{username};")
	public int updateimage(@Param("username") String username,@Param("avatar") String avatar);
	
}

