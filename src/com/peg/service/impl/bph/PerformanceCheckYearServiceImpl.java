package com.peg.service.impl.bph;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.PerformanceCheckYearMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.PerformanceCheckYear;
import com.peg.service.bph.PerformanceCheckYearServiceI;

@Service("performanceCheckYearService")
public class PerformanceCheckYearServiceImpl implements PerformanceCheckYearServiceI{
	
	@Autowired
	private PerformanceCheckYearMapper performanceCheckYearMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return performanceCheckYearMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PerformanceCheckYear record) {
		
		return performanceCheckYearMapper.insert(record);
	}

	@Override
	public int insertSelective(PerformanceCheckYear record) {
		
		return performanceCheckYearMapper.insertSelective(record);
	}

	@Override
	public PerformanceCheckYear selectByPrimaryKey(Long id) {
		
		return performanceCheckYearMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PerformanceCheckYear record) {
		
		return performanceCheckYearMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PerformanceCheckYear record) {
		
		return performanceCheckYearMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<PerformanceCheckYear> getAllByPage(BaseSearch bs) {
		
		return performanceCheckYearMapper.getAllByPage(bs);
	}

}
