package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.ExceptionEnterMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.ExceptionEnter;
import com.peg.service.ExceptionEnterServiceI;

@Service("exceptionService")
public class ExceptionServiceImpl implements ExceptionEnterServiceI{

	@Autowired
	private ExceptionEnterMapper exceptionEnterMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return exceptionEnterMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ExceptionEnter record) {
		
		return exceptionEnterMapper.insert(record);
	}

	@Override
	public int insertSelective(ExceptionEnter record) {
		
		return exceptionEnterMapper.insertSelective(record);
	}

	@Override
	public ExceptionEnter selectByPrimaryKey(Long id) {
		
		return exceptionEnterMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ExceptionEnter record) {
		
		return exceptionEnterMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ExceptionEnter record) {
		
		return exceptionEnterMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ExceptionEnter> getAllByPage(BaseSearch bs) {
	
		return exceptionEnterMapper.getAllByPage(bs);
	}

	@Override
	public List<ExceptionEnter> getExceptionByMonth(BaseSearch bs) {
		
		return exceptionEnterMapper.getExceptionByMonth(bs);
	}

}
