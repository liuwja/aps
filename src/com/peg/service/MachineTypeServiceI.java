package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.MachineType;

public interface MachineTypeServiceI {

int insert(MachineType machineType);
	List<MachineType>  getAll();

	List<MachineType>  getAllByPage(BaseSearch bs);
	
	public List<MachineType> findAll();
	
	
	int updateByPrimaryKeySelective(MachineType machineType);

	MachineType selectByPrimaryKey(Long id);

	int deleteByPrimaryKey(Long id);

	MachineType selectByPrimaryKeyForPartManager(Long id);
}
