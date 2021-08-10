package com.peg.dao.performance;

import java.util.List;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.performance.PerformanceIndex;

/**
 * 绩效考核指标增删查改基础Mapper,负责与数据库交互
 * @author xuanm 2018年6月19日10:45:20
 *
 */
public interface PerformanceIndexCrudMapper extends BaseMapper<PerformanceIndex, Long> {
	
	/**
	 * 获取所有绩效指标记录
	 * @param bs 绩效指标查询条件
	 * @return 绩效指标列表
	 */
	List<PerformanceIndex> getAllByPage(BaseSearch bs);
	
	/**
	 * 新增绩效指标记录（插入所有字段）
	 */
	@Override
	int insert(PerformanceIndex record);
	
	/**
	 * 新增绩效指标记录（可以插入指定的字段）
	 */
	@Override
	int insertSelective(PerformanceIndex record);
	
	/**
	 * 根据编号获取绩效指标记录
	 */
	@Override
	PerformanceIndex selectByPrimaryKey(Long id);
	
	/**
	 * 修改绩效指标记录（修改全部字段）
	 */
	@Override
	int updateByPrimaryKey(PerformanceIndex record);
	
	/**
	 * 修改绩效指标记录（修改指定字段）
	 */
	@Override
	int updateByPrimaryKeySelective(PerformanceIndex record);
	
	/**
	 * 删除绩效指标记录
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
	List<PerformanceIndex> getMonthPerformanceByPage(BaseSearch bs);
	
	/**
	 * 获取一条年度绩效指标，对应多条月度绩效指标
	 * @param id绩效指标ID
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

	List<PerformanceIndex> getMonthPerformanceAll(BaseSearch bs);
}
