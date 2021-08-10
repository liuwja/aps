package com.peg.service.performance;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.performance.PerformanceIndex;
import com.peg.service.BaseService;

/**
 * 绩效指标增删查该操作接口
 * @author xuanm
 *
 */
public interface PerformanceIndexCrudService extends BaseService<PerformanceIndex, Long> {
	
	/**
	 * 获取所有绩效指标记录
	 * @param bs 绩效指标查询条件
	 * @return 绩效指标列表
	 */
	List<PerformanceIndex> getAllByPage(BaseSearch bs);
	
	/**
	 * 新增绩效指标记录
	 */
	@Override
	int insert(PerformanceIndex record);
	
	/**
	 * 新增绩效指标记录（可以插入指定的字段）
	 */
	@Override
	int insertSelective(PerformanceIndex record);
	
	/**
	 * 修改绩效指标记录
	 */
	@Override
	int updateByPrimaryKeySelective(PerformanceIndex record);
	
	/**
	 * 删除绩效指标记录（传入记录主键ID作为参数）
	 */
	@Override
	int deleteByPrimaryKey(Long id);
	
	/**
	 * 获取年度绩效指标
	 * @param id 指标编号
	 * @return 年度绩效指标
	 */
	PerformanceIndex getYearPerformanceIndex(Long id);
	
	/**
	 * 获取月度绩效指标
	 * @return
	 */
	List<PerformanceIndex> getMonthPerformance(BaseSearch bs);
	
	/**
	 * 获取所有月度绩效指标
	 * @return
	 */
	List<PerformanceIndex> getMonthPerformanceAll(BaseSearch bs);
	/**
	 * 获取一条年度绩效指标，对应多条月度绩效指标
	 * @param bs
	 * @return
	 */
	PerformanceIndex getOneMonthPerformanceById(Long id);
	
	/**
	 * 获取年度绩效明细
	 * @param bs
	 * @return
	 */
	List<PerformanceIndex> getTotalPerformanceByPage(BaseSearch bs);
	
	/**
	 * 通过某些条件查询唯一的一个指标对象记录
	 * @param bs 查询条件
	 * @return
	 */
	List<PerformanceIndex> getOnePerformanceByObject(BaseSearch bs);
}
