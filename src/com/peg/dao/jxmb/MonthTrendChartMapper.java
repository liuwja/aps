package com.peg.dao.jxmb;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.MonthTrendChart;

public interface MonthTrendChartMapper {
	
	List<MonthTrendChart> getAll(BaseSearch bs);
	
	
	List<MonthTrendChart> getAllByYear(BaseSearch bs);
}
