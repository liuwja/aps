package com.peg.service;

import java.util.List;
import java.util.Map;

import com.peg.model.TimeMatrx;

public interface TimeMatrxServiceI {

	int insert(TimeMatrx timeMatrx);
	
	int deleteByPrimaryKey(TimeMatrx record);

	Map<String, TimeMatrx> getAllByMachineType(TimeMatrx record);
	
	List<TimeMatrx> getAllByMachineType(String machineType, String statisType);
}
