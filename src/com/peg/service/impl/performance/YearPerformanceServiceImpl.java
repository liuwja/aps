package com.peg.service.impl.performance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.performance.YearPerformanceMapper;
import com.peg.model.performance.YearPerformance;
import com.peg.service.AbstractService;
import com.peg.service.performance.YearPerformanceService;

/**
 * 年度绩效指标实现类
 * @author xuanm
 *
 */
@Service("yearPerformanceService")
public class YearPerformanceServiceImpl extends AbstractService<YearPerformance, Long>
		implements YearPerformanceService {

	/**
	 * 年度绩效指标Mapper接口对象
	 */
	@Autowired
	private YearPerformanceMapper yearPerformanceMapper;
	
	@Override
	public void setBaseMapper() {
		super.setBaseMapper(yearPerformanceMapper);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(YearPerformance record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(YearPerformance record) {
		return yearPerformanceMapper.insertSelective(record);
	}

	@Override
	public YearPerformance selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(YearPerformance record) {
		return yearPerformanceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(YearPerformance record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public YearPerformance selectByPiid(Long piid) {
		return yearPerformanceMapper.selectByPiid(piid);
	}

	@Override
	public void deleteByPiid(Long piid) {
		yearPerformanceMapper.deleteByPiid(piid);
	}
}
