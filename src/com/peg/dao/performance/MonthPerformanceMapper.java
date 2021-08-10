package com.peg.dao.performance;

import java.util.List;

import com.peg.dao.BaseMapper;
import com.peg.model.performance.MonthPerformance;

/**
 * 月度绩效Mapper
 * @author xuanm
 *
 */
public interface MonthPerformanceMapper extends BaseMapper<MonthPerformance, Long> {
	
	/**
	 * 获取所有月度绩效列表
	 * @return
	 */
	List<MonthPerformance> getAllByPage();
	
	/**
	 * 插入月度绩效指标（可以插入指定字段）
	 */
	@Override
	int insertSelective(MonthPerformance record);
	
	/**
	 * 根据主键编号修改月度绩效指标
	 */
	@Override
	int updateByPrimaryKeySelective(MonthPerformance record);
	
	/**
	 * 根据主键编号删除月度绩效指标
	 */
	@Override
	int deleteByPrimaryKey(Long id);
	
	/**
	 * 根据主键编号查询月度指标信息
	 */
	@Override
	MonthPerformance selectByPrimaryKey(Long id);
	
}
