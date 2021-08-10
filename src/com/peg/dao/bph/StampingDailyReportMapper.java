package com.peg.dao.bph;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.EntityClass;
import com.peg.model.bph.StampingDailyReport;

public interface StampingDailyReportMapper {
  
	List<StampingDailyReport> getStampingDailyAllByPage(BaseSearch bs);

	List<StampingDailyReport> getStampingDaliy(StampingDailyReport stampingDailyReport);
	
	List<EntityClass> getStampingDaliybad(@Param("entity")StampingDailyReport stampingDailyReport);
	
	List<EntityClass> getEntityClassarrange(@Param("entity")StampingDailyReport stampingDailyReport);
	
	List<EntityClass> getEntityClassarrangetwo(@Param("entity")StampingDailyReport stampingDailyReport);
	
}