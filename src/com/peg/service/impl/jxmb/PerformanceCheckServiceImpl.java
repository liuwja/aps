package com.peg.service.impl.jxmb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.jxmb.PerformanceCheckMapper;
import com.peg.model.jxmb.PerformanceCheck;
import com.peg.service.AbstractService;
import com.peg.service.jxmb.PerformanceCheckServicel;

@Service("performanceCheckService")
public class PerformanceCheckServiceImpl extends AbstractService<PerformanceCheck, Long> implements PerformanceCheckServicel{
	
	@Autowired
	private PerformanceCheckMapper performanceCheckMapper;

	@Override
	public List<PerformanceCheck> getAllBypage(BaseSearch bs) {
		return performanceCheckMapper.getAllByPage(bs);
	}
	@Override
	public List<PerformanceCheck> getAll(BaseSearch bs) {
		return performanceCheckMapper.getAll(bs);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return performanceCheckMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(PerformanceCheck record) {
		
		return performanceCheckMapper.insertSelective(record);
	}

	@Override
	public PerformanceCheck selectByPrimaryKey(Long id) {
		
		return performanceCheckMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PerformanceCheck record) {
	
		return performanceCheckMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PerformanceCheck record) {
		return performanceCheckMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<PerformanceCheck> getByid(Long formula) {
		return performanceCheckMapper.getByid(formula);
	}

	@Override
	public void setBaseMapper() {
		super.setBaseMapper(performanceCheckMapper);
		
	}

}
