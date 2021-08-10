package com.peg.service.impl.performance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.performance.MonthPerformanceMapper;
import com.peg.model.performance.MonthPerformance;
import com.peg.service.AbstractService;
import com.peg.service.performance.MonthPerformanceService;

/**
 * 月度绩效实现类
 * @author xuanm
 *
 */
@Service("monthPerformanceService")
public class MonthPerformanceServiceImpl extends AbstractService<MonthPerformance, Long>
		implements MonthPerformanceService {

	@Autowired
	private MonthPerformanceMapper monthPerformanceMapper;
	
	@Override
	public void setBaseMapper() {
		super.setBaseMapper(monthPerformanceMapper);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return monthPerformanceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MonthPerformance record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(MonthPerformance record) {
		return monthPerformanceMapper.insertSelective(record);
	}

	@Override
	public MonthPerformance selectByPrimaryKey(Long id) {
		return monthPerformanceMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MonthPerformance record) {
		return monthPerformanceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MonthPerformance record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<MonthPerformance> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<MonthPerformance> getAllByPage() {
		return monthPerformanceMapper.getAllByPage();
	}

}
