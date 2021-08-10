package com.peg.service.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.CountPerformanceMonth;

public interface CountPerformanceMonthServiceI {

	    int deleteByPrimaryKey(Long id);

	    int insert(CountPerformanceMonth record);

	    int insertSelective(CountPerformanceMonth record);

	    CountPerformanceMonth selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(CountPerformanceMonth record);

	    int updateByPrimaryKey(CountPerformanceMonth record);
	    
	    List<CountPerformanceMonth> getAllByPage(BaseSearch bs);
	    
	    List<CountPerformanceMonth> getAllByMonth(BaseSearch bs);
        
	    //查询是否存在相同的月份
		List<CountPerformanceMonth> selectCommonMonth(CountPerformanceMonth CountPerformanceMonth);
		
		//计算
		int CaculateCountPerformaceMonth(CountPerformanceMonth ref);
}
