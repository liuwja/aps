package com.peg.service.jxmb;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.ChartsDataVo;
import com.peg.model.jxmb.MonthTrendChart;
import com.peg.model.jxmb.PerformanceCheck;

public interface ChartsDateService {
	
	List<MonthTrendChart> getAll(BaseSearch bs);
	
	List<MonthTrendChart> getAllByYear(BaseSearch bs);
	//绩效大类
	public List<String> getBigClass(List<PerformanceCheck> list);
	//绩效目标衡量指标内容
	public List<String> getIndexContent(List<PerformanceCheck> list,String depaName,String bigClass);
	//月度实际绩效趋势
	public ChartsDataVo setCharts(ChartsDataVo vo);
	//年度同比实际绩效统计
	public ChartsDataVo setChartsByAnYear(ChartsDataVo vo);
	//指标达成率统计
	public List<ChartsDataVo> setReachRateTable(ChartsDataVo vo);
	//年度实际绩效统计
	public ChartsDataVo setChartsByYear(ChartsDataVo vo);
	
}
