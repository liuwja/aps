package com.peg.dao.jxmb;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.PerMonth;

public interface PerMonthMapper  extends BaseMapper<PerMonth, Long>{
	  /**
     * 按月份查询月度基准
     * @param queryMonth
     * @return
     */
	List<PerMonth>getMonthAllByMonth(@Param("queryMont")String queryMonth);
	/**
	 * 根据key,月份查询是否存在月度考核指标
	 * @param groupKey
	 * @param queryMonth
	 * @return
	 */
	List<PerMonth>findeByGroupAndMonth(@Param("groupKey")Long groupKey,@Param("queryMonth")String queryMonth);
	
	int deleteMonthByGcKey(Long gcKey);
	
	List<PerMonth> findeByGroupAndMonthAndIndex(@Param("groupKey")Long groupKey,@Param("queryMonth")String queryMonth,
			@Param("indexKey")Long indexKey);
	
  List<PerMonth>selectByGroupAndMonth(Long groupKey);
  
  List<PerMonth>getMonthAllByPage(BaseSearch bs);
}