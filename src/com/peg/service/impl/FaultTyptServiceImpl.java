package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.FaultTypeMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.FaultType;
import com.peg.service.FaultTypeServiceI;
@Service("faultTypeService")
public class FaultTyptServiceImpl implements FaultTypeServiceI{
	
	@Autowired
	private FaultTypeMapper faultTypeMapper;

	@Override
	public int insert(FaultType record) {
		// TODO Auto-generated method stub
		faultTypeMapper.insert(record);
		return 0;
	}

	@Override
	public List<FaultType> findAllByPage(FaultType faultType,PageParameter page) {
		// TODO Auto-generated method stub
		return faultTypeMapper.findAllByPage(faultType, page);
	}

	@Override
	public List<FaultType> findAll(FaultType faultType) {
		// TODO Auto-generated method stub
		return faultTypeMapper.findAll(faultType);
	}

	@Override
	public int updateByPrimaryKeySelective(FaultType record) {
		// TODO Auto-generated method stub
		return faultTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public FaultType selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return faultTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return faultTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public FaultType getfaultbykey(String u) {
		// TODO Auto-generated method stub
		return faultTypeMapper.getfaultbykey(u);
	}

}
