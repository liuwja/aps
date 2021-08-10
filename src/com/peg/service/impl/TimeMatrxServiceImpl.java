package com.peg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.TimeMatrxMapper;
import com.peg.model.TimeMatrx;
import com.peg.service.TimeMatrxServiceI;


@Service("timeMatrxService")
public class TimeMatrxServiceImpl implements TimeMatrxServiceI{

	@Autowired
	private TimeMatrxMapper timeMatrxMapper;
	@Override
	public int insert(TimeMatrx record) {
		// TODO Auto-generated method stub
		timeMatrxMapper.insert(record);
		return 0;
	}
	@Override
	public int deleteByPrimaryKey(TimeMatrx record) {
		// TODO Auto-generated method stub
		return timeMatrxMapper.deleteByPrimaryKey(record);
	}
	@Override
	public Map<String, TimeMatrx> getAllByMachineType(TimeMatrx record) {
		List<TimeMatrx> list = timeMatrxMapper.getAllByMachineType(record);
		Map<String, TimeMatrx> tmpMap = new HashMap<String, TimeMatrx>();
		for (int i = 0; i < list.size(); i++) {
			TimeMatrx t = list.get(i);
			tmpMap.put(t.getMachineType() + "-" + t.getStatisType() + "-" + t.getProductionMonth(), t);
		}
		return tmpMap;
	}
	
	public List<TimeMatrx> getAllByMachineType(String machineType, String statisType) {
		TimeMatrx t = new TimeMatrx();
		t.setMachineType(machineType);
		t.setStatisType(statisType);
		List<TimeMatrx> list = timeMatrxMapper.getAllByMachineType(t);
		return list;
	}
	
}
