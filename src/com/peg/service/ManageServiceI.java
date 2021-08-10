package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.Manage;

public interface ManageServiceI {
	int insert(Manage manage);
	
	List<Manage>  getAllByPage(BaseSearch bs);
	
	public List<Manage> findAll();
	
	
	int updateByPrimaryKeySelective(Manage manage);

	Manage selectByPrimaryKey(Long id);

	int deleteByPrimaryKey(Long id);

	Manage selectByPrimaryKeyForPartManager(Long id);

	
	
}
