package com.chenyao.ssmproject.concern.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.chenyao.ssmproject.concern.model.ConcernModel;
import com.chenyao.ssmproject.user.model.User;
@Repository
public interface ConcernMapper {
	@Select("select wasfuns from concern where follower=#{follower};")
	public List<String> selectwasfuns(ConcernModel concern); 
	@Select("Select * from concern;")
	public List<ConcernModel> select();
	@Insert("insert into concern values(#{follower},#{wasfuns});")
	public int insert(ConcernModel concern);
	@Delete("delete from concern where follower=#{follower}")
	public int delete(@Param("follower") String follwer);
	public Integer selectcount(ConcernModel concern);
	public Integer selectnum(ConcernModel concern);
	
	
}
