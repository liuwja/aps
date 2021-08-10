package com.peg.service.impl.performance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.performance.PerformanceIndexCrudMapper;
import com.peg.model.performance.PerformanceIndex;
import com.peg.service.AbstractService;
import com.peg.service.performance.PerformanceIndexCrudService;

/**
 * 绩效指标增删查该实现类
 * @author xuanm
 *
 */
@Service("performanceIndexCrudService")
public class PerformanceIndexCrudServiceImpl extends AbstractService<PerformanceIndex, Long>
		implements PerformanceIndexCrudService {

	/**
	 * 绩效指标Mapper接口对象
	 */
	@Autowired
	private PerformanceIndexCrudMapper performanceIndexCrudMapper;
	
	@Override
	public void setBaseMapper() {
		super.setBaseMapper(performanceIndexCrudMapper);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return performanceIndexCrudMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PerformanceIndex record) {
		return performanceIndexCrudMapper.insert(record);
	}

	@Override
	public int insertSelective(PerformanceIndex record) {
		return performanceIndexCrudMapper.insertSelective(record);
	}

	@Override
	public PerformanceIndex selectByPrimaryKey(Long id) {
		return performanceIndexCrudMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PerformanceIndex record) {
		return performanceIndexCrudMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PerformanceIndex record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PerformanceIndex> getAllByPage(BaseSearch bs) {
		return performanceIndexCrudMapper.getAllByPage(bs);
	}

	@Override
	public PerformanceIndex getYearPerformanceIndex(Long id) {
		return performanceIndexCrudMapper.getYearPerformanceIndex(id);
	}

	@Override
	public List<PerformanceIndex> getMonthPerformance(BaseSearch bs) {
		return performanceIndexCrudMapper.getMonthPerformanceByPage(bs);
	}
	@Override
	public List<PerformanceIndex> getMonthPerformanceAll(BaseSearch bs) {
		return performanceIndexCrudMapper.getMonthPerformanceAll(bs);
	}

	@Override
	public PerformanceIndex getOneMonthPerformanceById(Long id) {
		return performanceIndexCrudMapper.getOneMonthPerformanceById(id);
	}

	@Override
	public List<PerformanceIndex> getTotalPerformanceByPage(BaseSearch bs) {
		return performanceIndexCrudMapper.getTotalPerformanceByPage(bs);
	}

	@Override
	public List<PerformanceIndex> getOnePerformanceByObject(BaseSearch bs) {
		return performanceIndexCrudMapper.getOnePerformanceByObject(bs);
	}

}
