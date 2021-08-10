package com.peg.service.jxmb;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.PerMonth;
import com.peg.service.BaseService;

public interface MonthServicel extends BaseService<PerMonth, Long> {
	List<PerMonth>getMonthAllByPage(BaseSearch bs);
	
	List<PerMonth>selectByGroupAndMonth(@Param("groupKey")Long groupKey);
	List<PerMonth>findeByGroupAndMonth(@Param("groupKey")Long groupKey,@Param("queryMonth")String queryMonth);
	List<PerMonth> findeByGroupAndMonthAndIndex(@Param("groupKey")Long groupKey,@Param("queryMonth")String queryMonth,
			@Param("indexKey")Long indexKey);
}
