package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.MachineTypeMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.MachineType;
import com.peg.service.MachineTypeServiceI;
@Service("MachineTypeService")
public class MachineTypeServiceImpl implements MachineTypeServiceI{
	
	@Autowired
	private MachineTypeMapper machineTypeMapper;
	@Override
	public int insert(MachineType record) {
		machineTypeMapper.insert(record);
		return 0;
	}

	@Override
	public List<MachineType> getAllByPage(BaseSearch bs) {
		
		return machineTypeMapper.getAllByPage(bs);
	}

	@Override
	public List<MachineType> findAll() {
		// TODO Auto-generated method stub
		return machineTypeMapper.getAll();
	}

	@Override
	public int updateByPrimaryKeySelective(MachineType record) {
		// TODO Auto-generated method stub
		return machineTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public MachineType selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return machineTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return  machineTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public MachineType selectByPrimaryKeyForPartManager(Long id) {
		// TODO Auto-generated method stub
		return machineTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<MachineType> getAll() {
		// TODO Auto-generated method stub
		return machineTypeMapper.getAll();
	}
}
