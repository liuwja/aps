package com.peg.web.util;

import java.util.HashMap;
import java.util.Map;

public class UrlIntercepterMap {
     private static Map<String,String> urlMap = null;
     
     public static Map<String,String> getUrlMap()
     {
    	 if(urlMap == null)
    	 {
    		 urlMap = new HashMap<String, String>();
    	 }
    	 urlMap.put("/quality/testInstance/supplerDefectRate.do", "进料质量");
    	 urlMap.put("/quality/testInstance/supplerDefect.do", "进料统计分析");
    	 urlMap.put("/quality/testInstance/feedDetail.do", "进料质量明细");
    	 urlMap.put("/base/online/onlineHomeChar.do", "物料在线统计分析");
    	 urlMap.put("/quality/marketPart/marketDefectIndex.do", "市场不良排列图");
    	 urlMap.put("/quality/marketPart/marketDefectTrendIndex.do", "市场不良趋势图");
    	 urlMap.put("/groupPerformanceChart/singlePerformanceChar.do", "班组绩效排名");
    	 urlMap.put("/groupPerformanceChart/oneGroupPerfanceYear.do", "班组绩效统计");
    	 urlMap.put("/groupPerformanceChart/performanceDetailList.do", "班组绩效明细查询");
    	 urlMap.put("/groupPerformanceChart/achievementscontrast.do", "绩效对比");
    	 urlMap.put("/partTypeChart/analysisChart.do", "型号排列图");
    	 urlMap.put("/regionChart/analysisChart.do", "区域排列图");
    	 urlMap.put("/plineChart/analysisChart.do", "产线排列图");
    	 urlMap.put("/faultTypeChart/singleChart.do", "故障大类排列图");
    	 urlMap.put("/faultReasonChart/analysisChart.do", "故障小类排列图");
    	 urlMap.put("/timeChart/singleChart.do", "百台维修率序列图");
    	 urlMap.put("/partTypeChart/singleChart.do", "型号排列图");
    	 urlMap.put("/regionChart/singleChart.do", "区域排列图");
    	 urlMap.put("/plineChart/singleChart.do", "产线排列图");
    	 urlMap.put("/faultTypeChart/singleChart.do", "故障大类排列图");
    	 urlMap.put("/faultReasonChart/singleChart.do", "故障小类排列图-累加");
    	 urlMap.put("/faultReason2Chart/singleChart.do", "故障小类排列图-单月");
    	 urlMap.put("/partTypeChart/common.do", "年度百台排列分析");
    	 urlMap.put("/partTypeChart/betweenCommon.do", "生产及维修日期排列分析");
    	 urlMap.put("/scatterChart/scatterChart.do", "百台四象限分析");
    	 urlMap.put("/scatterChart/installScatterChart.do", "安装四象限分析");
    	 urlMap.put("/timeMatrixTable/sigleMonthReCount.do", "时间序列分析(正三角)");
    	 urlMap.put("/timeMatrixTable/sigleDownMonthReCount.do", "时间序列分析(倒三角)");
    	 urlMap.put("/timeMatrixInstall/trgMatrixInsSigleCount.do", "安装时间序列分析(正三角)");
    	 urlMap.put("/timeMatrixInstall/trgMatrixDownInsSigleCount.do", "安装时间序列分析(倒三角)");
    	 urlMap.put("/timeMatrixTable/underWarrantyChart.do", "保内时序图");
    	 urlMap.put("/timeMatrixTable/timeTotalPChart.do", "生产月P控图分析");
    	 urlMap.put("/mapChart/singleMapChart.do", "区域维修分析");
    	 urlMap.put("/timeMatrixTable/sigleMonthReTotalCount.do", "时间序列分析(累计)");
    	 urlMap.put("/timeMatrixTable/trgMatrixReTotalCount.do", "时间序列分析(P控图)");
    	 urlMap.put("/timeMatrixTable/trgMatrixRePercent.do", "时间序列矩阵(三角阵-累计维修率)");
    	 urlMap.put("/base/repairRecord/initIntermediate.do", "CRM市场维修记录-中间表");
    	 return urlMap;
     }
}
