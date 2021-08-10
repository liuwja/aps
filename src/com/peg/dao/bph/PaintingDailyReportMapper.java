package com.peg.dao.bph;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.EntityClass;
import com.peg.model.bph.PaintingDailyReport;

public interface PaintingDailyReportMapper {
	
	List<PaintingDailyReport> getPaintingDailyAllByPage(BaseSearch bs);

	List<PaintingDailyReport> getPaintingDaily(
			PaintingDailyReport paintingDailyReport);
	
	List<EntityClass> getPaintingDailybad(@Param("entity")PaintingDailyReport paintingDailyReport);
	
	List<EntityClass> paintingDailyarrange(@Param("entity")PaintingDailyReport paintingDailyReport);
	
	List<EntityClass> paintingDailyarrangetwo(@Param("entity")PaintingDailyReport paintingDailyReport);
}