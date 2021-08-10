package com.peg.service.performance;

import com.peg.model.performance.YearPerformance;
import com.peg.service.BaseService;

/**
 * 年度绩效指标接口
 * @author xuanm
 *
 */
public interface YearPerformanceService extends BaseService<YearPerformance, Long> {
	
	/**
	 * 根据绩效指标查询记录
	 * @param piid 绩效指标外键
	 * @return 年度绩效指标值的记录
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
	 * 删除年度绩效指标
	 * @param piid 关联指标主键
	 */
	void deleteByPiid(Long piid);
}
