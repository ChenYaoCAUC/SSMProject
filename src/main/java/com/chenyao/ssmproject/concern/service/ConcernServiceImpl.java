package com.chenyao.ssmproject.concern.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenyao.ssmproject.concern.dao.ConcernMapper;
import com.chenyao.ssmproject.concern.model.ConcernModel;
import com.chenyao.ssmproject.user.model.User;

@Service
public class ConcernServiceImpl implements ConcernService {
	@Autowired
	private ConcernMapper dao;
	@Override
	public List<ConcernModel> select() {
		// TODO Auto-generated method stub
		return dao.select();
	}

	@Override
	public int insert(ConcernModel concern) {
		// TODO Auto-generated method stub
		return dao.insert(concern);
	}

	@Override
	public int delete(String follwer) {
		// TODO Auto-generated method stub
		return dao.delete(follwer);
	}

	@Override
	public int selectcount(ConcernModel concern) {
		// TODO Auto-generated method stub
		return dao.selectcount(concern);
	}

	@Override
	public List<String> selectwasfuns(ConcernModel concern) {
		// TODO Auto-generated method stub
		return dao.selectwasfuns(concern);
	}

	@Override
	public Integer selectnum(ConcernModel concern) {
		// TODO Auto-generated method stub
		return dao.selectnum(concern);
	}

}
