package com.chenyao.ssmproject.concern.service;

import java.util.List;

import com.chenyao.ssmproject.concern.model.ConcernModel;
import com.chenyao.ssmproject.user.model.User;

public interface ConcernService {
	public List<ConcernModel> select();
	public int insert(ConcernModel concern);
	public int delete( String follwer,String wasfuns);
	public int selectcount(ConcernModel concern);
	public List<String> selectwasfuns(ConcernModel concern); 
	public Integer selectnum(ConcernModel concern);
}
