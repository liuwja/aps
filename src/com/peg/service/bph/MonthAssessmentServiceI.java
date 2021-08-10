package com.peg.service.bph;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.model.bph.MonthAssessment;
import com.peg.service.BaseService;

public interface MonthAssessmentServiceI extends BaseService<MonthAssessment, Long>{

	List<MonthAssessment> findeByGroupAndMonth(@Param("groupKey")Long groupKey,@Param("queryMonth")String queryMonth);
	
	List<MonthAssessment> findeByGroupAndMonthAndIndex(@Param("groupKey")Long groupKey,@Param("queryMonth")String queryMonth,
			@Param("indexKey")Long indexKey);
}
