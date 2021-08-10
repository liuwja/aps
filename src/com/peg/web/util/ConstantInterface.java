/*
 * @(#) ConstantInterface.java 2014-9-1 下午07:03:18
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.util;

/**
 * <p>
 * @author Lin, 2014-9-1 下午07:03:18
 */
public interface ConstantInterface{
	
	 short PRIVILEGE_TYPE_MENU = 0;
	 
	 short PRIVILEGE_TYPE_OPERATION = 1;
	 
	 /** 日期格式-年*/
	 public static final String DATE_FORMAT_YEAR = "year";
	 
	 /** 日期格式-季度*/
	 public static final String DATE_FORMAT_QUARTER = "quarter";
	 
	 /** 日期格式-月*/
	 public static final String DATE_FORMAT_MONTH = "month";
	 
	 /** 日期格式-周*/
	 public static final String DATE_FORMAT_WEEK = "week";
	 
	 public static final String YES = "yes";
	 
	 public static final String NO = "no";
	 
	 /**
	  * 质量管理系统
	  */
	 final int QUALITY_SYSTEM = 2;
	 
	 /**
	  * 班组绩效管理系统
	  */	 
	 final int ACHIEVEMENT_SYSTEM = 4;
	 
	 final static short UIMENU_TYPE_ACCORDION = 1;
	 final static short UIMENU_TYPE_FOLDER = 2;
	 final static short UIMENU_TYPE_MENU = 3;
	 
	 /**
	  * 物料统计分析
	  * */
	 
	 /** 供应商市场不良排列图*/
	 public static final int PART_DEFECT_HISTOGRAM_SUPPLIER = 1;
	 
	 /** 零部件市场不良排列图*/
	 public static final int PART_DEFECT_HISTOGRAM_PART = 2;
	 
	 /** 区域市场不良排列图*/
	 public static final int PART_DEFECT_HISTOGRAM_REGION = 3;
	 
	 /** 故障大类市场不良排列图*/
	 public static final int PART_DEFECT_HISTOGRAM_FAULT_TYPE = 4;
	 
	 /** 故障小类市场不良排列图*/
	 public static final int PART_DEFECT_HISTOGRAM_FAULT_REASON = 5;
	 
	 /** 机型与物料级别市场不良排列图*/
	 public static final int PART_DEFECT_HISTOGRAM_PART_LEVEL = 6;
	 
	 /** 物料类型市场不良排列图*/
	 public static final int PART_DEFECT_HISTOGRAM_PART_TYPE = 15;
	 
	 /** 市场不良趋势图-物料*/
	 public static final int PART_DEFECT_TREND = 7;
	 
	 /** 市场不良单月维修图-物料*/
	 public static final int PART_DEFECT_TREND_MONTH = 8;
	 
	 /** 市场不良百台维修图-物料*/
	 public static final int PART_DEFECT_TREND_HUNDRED = 9;
	 
	 /** 市场不良三角阵-正三角*/
	 public static final int PART_DEFECT_MATRIX_POSITIVE = 10;
	 
	 /** 市场不良三角阵-倒三角*/
	 public static final int PART_DEFECT_MATRIX_NEGATIVE = 11;
	 
	 /** 市场不良-不良明细*/
	 public static final int PART_DEFECT_DETAIL_PART = 12;
	 
	 /** 市场不良-来料入库明细*/
	 public static final int PART_DEFECT_DETAIL_IN_COMING_PART = 13;
	 
	 /** 市场不良-扫码入库明细*/
	 public static final int PART_DEFECT_DETAIL_SERIAL_PART = 14;
	 
	 /** 产品-百台维修率单月趋势图*/
	 public static final int PRODUCTION_REPAIR_SINGLE_TREND = 21;
	 
	 /** 产品-百台维修率累计趋势图*/
	 public static final int PRODUCTION_REPAIR_TOTAL_TREND = 22;
	 
	 /** 产品-物料-百台维修率单月趋势图*/
	 public static final int PRODUCTION_PART_SINGLE_TREND = 23;
	 
	 /** 产品-物料-百台维修率累计趋势图*/
	 public static final int PRODUCTION_PART_TOTAL_TREND = 24;
	 
	 /** 产品-供应商-关键物料-百台维修率单月趋势图*/
	 public static final int PRODUCTION_SUPPLIER_PART_SINGLE_TREND = 25;
	 
	 /** 产品-供应商-关键物料-百台维修率累计趋势图*/
	 public static final int PRODUCTION_SUPPLIER_PART_TOTAL_TREND = 26;
	 
	 /** 市场不良session对象key*/
	 public static final String PART_DEFECT_SESSION_VO = "sessionVo";
	 
	 /** 市场不良趋势图session对象key*/
	 public static final String PART_DEFECT_SESSION_TREND_VO = "sessionTrendVo";
	 
	 /** 物料带版本*/
	 public static final String PART_HAS_VERSION = "1";
	 
	 /** 物料不带版本*/
	 public static final String PART_NO_HAS_VERSION = "2";
}
