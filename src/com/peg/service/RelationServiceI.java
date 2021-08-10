package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.Relation;

public interface RelationServiceI {

	int insert(Relation relation);
	
	List<Relation>  getAllByPage(BaseSearch bs);
	
	public List<Relation> findAll();
	
	
	int updateByPrimaryKeySelective(Relation relation);

	Relation selectByPrimaryKey(Long id);

	int deleteByPrimaryKey(Long id);
}
