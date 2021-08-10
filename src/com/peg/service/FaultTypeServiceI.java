package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.FaultType;

public interface FaultTypeServiceI {
	
int insert(FaultType faultType);
	
	List<FaultType>  findAllByPage(FaultType faultType,PageParameter page);
	
	public List<FaultType> findAll(FaultType faultType);
	
	
	int updateByPrimaryKeySelective(FaultType faultType);

	FaultType selectByPrimaryKey(Long id);

	int deleteByPrimaryKey(Long id);

	FaultType getfaultbykey(String u);
}
