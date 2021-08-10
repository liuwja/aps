package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.ShiftGroupMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.ShiftGroup;
import com.peg.service.ShiftGroupServiceI;
@Service("ShiftGroupService")
public class ShiftGroupServiceImpl implements ShiftGroupServiceI{
	@Autowired
	private ShiftGroupMapper shiftGroupMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return shiftGroupMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<ShiftGroup> getAllByPage(BaseSearch bs) {
		
		return shiftGroupMapper.getAllByPage(bs);
	}

	@Override
	public int insert(ShiftGroup record) {
		shiftGroupMapper.insert(record);
		return 0;
	}

	@Override
	public ShiftGroup selectByPrimaryKey(Long id) {
		
		return shiftGroupMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(Long id) {
		
		return shiftGroupMapper.updateByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ShiftGroup record) {
		
		return shiftGroupMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<ShiftGroup> getArea(String factory) {
		
		return shiftGroupMapper.getArea(factory);
		
	}

	@Override
	public List<ShiftGroup> getShiftGroup(String factory) {
		
		return shiftGroupMapper.getShiftGroup(factory);
	}

	@Override
	public List<ShiftGroup> getName() {
		
		return shiftGroupMapper.getName();
	}

	@Override
	public List<ShiftGroup> getShiftGroupByFoArea(String factory, String area) {
		
		return shiftGroupMapper.getShiftGroupByFoArea(factory, area);
	}



}
