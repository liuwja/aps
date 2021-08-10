/*
 * @(#) IDateFormat.java 2014-2-13 下午1:35:06
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.util;

 /**
  * 时间格式定义 
  * @author Nemo, 2014-2-13 下午1:35:10
  */
public interface IDateFormat
{
	/**
	 * yyyy-MM-dd
	 */
	String TIME_STANDARD = "yyyy-MM-dd";
	
	/**
	 * yyyy-MM
	 */
	String TIME_NO_DAY = "yyyy-MM";
	
	/**
	 * M.d
	 */
	String TIME_NO_YEAR = "M.d";
	
	String TIME_NO_YEAR_CHINESE = "M月d日";
	
	/**
	 * MM-dd HH:mm:ss
	 */
	String TIME_STAMPING = "HH:mm:ss";
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	String TIME_LONG = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * HH:mm:ss
	 */
	String TIME_SHORT = "HH:mm:ss";
	
	/**
	 * HH:mm
	 */
	String TIME_HOUR_MIN="HH:mm";
	
	
	String MM_DD_TIME_HOUR_MIN="MM-dd HH:mm";
	
	/**
	 * yyyyMMdd
	 */
	String TIME_DAY = "yyyyMMdd";
	
	/**
	 * yyyyMMddHH
	 */
	String TIME_HOUR = "yyyyMMddHH";
	
	/**
	 * yyMMdd
	 */
	String TIME_SHORT_DAY = "yyMMdd";
	
	/**
	 * yyyyMMddHHmmssSSS
	 */
	String TIME_SEQUENCE = "yyyyMMddHHmmssSSS";
	
	/**
	 * HHmmss
	 */
	String HOUR = "HHmmss";
}
