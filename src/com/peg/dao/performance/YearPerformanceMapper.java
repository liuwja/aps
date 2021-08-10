package com.peg.dao.performance;

import com.peg.dao.BaseMapper;
import com.peg.model.performance.YearPerformance;

/**
 * 年度绩效指标Mapper接口
 * @author xuanm
 *
 */
public interface YearPerformanceMapper extends BaseMapper<YearPerformance, Long> {
	
	/**
	 * 根据主键查询记录
	 */
	@Override
	YearPerformance selectByPrimaryKey(Long id);
	
	/**
	 * 根据绩效指标查询记录
	 * @param piid 绩效指标外键
	 * @return
	 */
	YearPerformance selectByPiid(Long piid);
	
	/**
	 * 新增年度绩效指标（可以插入指定字段）
	 */
	@Override
	int insertSelective(YearPerformance record);
	
	/**
	 * 修改年度绩效指标（可以修改指定字段）
	 */
	@Override
	int updateByPrimaryKeySelective(YearPerformance record);
	
	/**
	 * 删除年度指标
	 * @param piid 关联指标外键
	 */
	void deleteByPiid(Long piid);
	
	
}
