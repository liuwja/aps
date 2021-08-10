package com.peg.dao.bph;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.model.bph.MonthAssessment;


public interface MonthAssessmentMapper extends BaseMapper<MonthAssessment, Long>{
	
    /**
     * 按月份查询月度基准
     * @param queryMonth
     * @return
     */
	List<MonthAssessment> getMonthAllByMonth(@Param("queryMont")String queryMonth);
	
	/**
	 * 根据班组key,月份查询是否存在月度考核指标
	 * @param groupKey
	 * @param queryMonth
	 * @return
	 */
	List<MonthAssessment> findeByGroupAndMonth(@Param("groupKey")Long groupKey,@Param("queryMonth")String queryMonth);

	/**
	 * 通过班组类别名称删除月份指标
	 * @param gcKey
	 * @return
	 */
	int deleteMonthByGcKey(Long gcKey);
	
    /**
     * 根据班组key,月份,指标key查询是否存在月度考核指标
     * @param groupKey
     * @param queryMonth
     * @param indexKey
     * @return
     */
	List<MonthAssessment> findeByGroupAndMonthAndIndex(@Param("groupKey")Long groupKey,@Param("queryMonth")String queryMonth,
			@Param("indexKey")Long indexKey);
}
