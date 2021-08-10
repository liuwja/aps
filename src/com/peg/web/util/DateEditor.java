/*
 * @(#) DateEditor.java 2014-8-22 上午10:12:23
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.util;

 import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.util.StringUtils;

 public class DateEditor extends PropertyEditorSupport {

     private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
     private static final DateFormat TIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     private static final DateFormat MONTHFORMAT = new SimpleDateFormat("yyyy-MM");

     private DateFormat dateFormat;
     private boolean allowEmpty = true;

     public DateEditor() {
     }

     public DateEditor(DateFormat dateFormat) {
         this.dateFormat = dateFormat;
     }

     public DateEditor(DateFormat dateFormat, boolean allowEmpty) {
         this.dateFormat = dateFormat;
         this.allowEmpty = allowEmpty;
     }

     /**
      * Parse the Date from the given text, using the specified DateFormat.
      */
     @Override
     public void setAsText(String text) throws IllegalArgumentException {
         if (this.allowEmpty && !StringUtils.hasText(text)) {
             // Treat empty String as null value.
             setValue(null);
         } else {
             try {
                 if(this.dateFormat != null)
                     setValue(this.dateFormat.parse(text));
                 else {
                     if(text.contains(":"))
                         setValue(TIMEFORMAT.parse(text));
                     else
                    	 if(text.split("-").length == 2)
                    	 {
                    		 setValue(DATEFORMAT.parse(text + "-01"));
                    	 }
                    	 else
                    	 {
                    		 setValue(DATEFORMAT.parse(text));
                    	 }
                 }
             } catch (ParseException ex) {
                 throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
             }
         }
     }

     /**
      * Format the Date as String, using the specified DateFormat.
      */
     @Override
     public  String getAsText() {
         Date value = (Date) getValue();
         DateFormat dateFormat = this.dateFormat;
         if(dateFormat == null)
             dateFormat = TIMEFORMAT;
         return (value != null ? dateFormat.format(value) : "");
     }
     
     public static String getStartTime(){
    	    Calendar cal = Calendar.getInstance();       	
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, -1);
			cal.set(Calendar.DAY_OF_MONTH, 26);
		    return DATEFORMAT.format(cal.getTime());
     }
     
     public static String getEndTime(){
 	        Calendar cal = Calendar.getInstance();       	
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 25);
		    return DATEFORMAT.format(cal.getTime());
  }
     
     public static String getMonth(int t){
    	    Calendar cal = Calendar.getInstance();       	
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, t);
			return MONTHFORMAT.format(cal.getTime());
     }
     
     public static String getMonth(String month,int t) throws ParseException{
 	        Calendar cal = Calendar.getInstance();       	
			cal.setTime(MONTHFORMAT.parse(month));
			cal.add(Calendar.MONTH, t);
			return MONTHFORMAT.format(cal.getTime());
  }
     /**
      * 获取今天是今年的第几周
      * @param date
      * @return
     * @throws ParseException 
      */
     public static  int getWeek(String date) {
    	 int week  = 0;
    	 try {
    		Calendar cal = Calendar.getInstance();
    		cal.setFirstDayOfWeek(Calendar.SUNDAY);
			cal.setTime(DATEFORMAT.parse(date));
		    week = cal.get(Calendar.WEEK_OF_YEAR);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	 return week;
     }
   
     /**
 	 * Calendar c = Calendar.getInstance(); c.set(2012,8, 6, 14, 16,37); Date
 	 * date = c.getTime(); formatDate(date,"yyyy-MM-dd HH:mm:ss")
 	 * 返回"2012-09-06 14:16:37"
 	 * 
 	 * @param date
 	 *            需要格式化的日期
 	 * @param pattern
 	 *            year=yyyy month=MM day=dd hour=HH minute=mm second=ss
 	 * @return 根据指定格式将输入日期转换成字符串格式
 	 */
 	public static String formatDate(java.util.Date date, String pattern)
 	{
 		try
 		{
 			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
 			return sdf.format(date.getTime());
 		}
 		catch (Exception e)
 		{
 			return "";
 		}
 	}

 	/**
 	 * changeDateFormat("2012-12-01","yyyy-MM-dd","yyyy/MM/dd")返回"2012/12/01",
 	 * 注意若发生异常默认返回空字符串""
 	 * 
 	 * @param dateTime
 	 *            输入日期
 	 * @param inputFormat
 	 *            输入日期的格式
 	 * @param outputFormat
 	 *            输出日期的格式
 	 * @return 按输出日期格式转换后的日期
 	 */
 	public static String changeDateFormat(String dateTime, String inputFormat, String outputFormat)
 	{
 		try
 		{
 			SimpleDateFormat iFormat = new SimpleDateFormat(inputFormat);
 			java.util.Date iDate = iFormat.parse(dateTime);
 			SimpleDateFormat oFormat = new SimpleDateFormat(outputFormat);
 			String sRet = oFormat.format(iDate);
 			return sRet;
 		}
 		catch (Exception e)
 		{
 			return "";
 		}
 	}
 	/**
	 * parseDate("2012-12-01","yyyy-MM-dd"),若发生异常(传入的日期字符串与指定的时间格式不符),返回null
	 * 
	 * @param text
	 * @param pattern
	 * @return 根据指定格式将字符串转换后的日期
	 */
	public static java.util.Date parseDate(String text, String pattern)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(text);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * 获取供应商批次
	 * @param lotTime
	 */
	public static String getLotTime(String lotTime) {
		String[] group = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O" ,"P", "Q", "R", "S", "T", "U", "V"};
		String[] group1 = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"} ;
		String[] dates = lotTime.split("-");
		String date = "";
		for(int k = 0; k<dates.length; k++){
			String str = "";
			System.out.println(dates[k]);
			if(k==0){
				str = "0"+dates[k].substring(dates[k].length()-1,dates[k].length());
			}else{
				str = dates[k];
			}
			for(int i= 0; i<group1.length; i++){
				if(str.equals(group1[i])){
					date +=  group[i];
				}
			}
		}
		return date;
	}
	/**
	 * 天计算
	 * @param date
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public static String addDays(String date, int num) throws Exception{
		Date month_date = DateUtils.parseDate(date, new String[] { "yyyy-MM-dd" });
		Calendar cal = Calendar.getInstance();
		cal.setTime(month_date);
		cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, num);
		return DateFormatUtils.format(cal,"yyyy-MM-dd");
	}

	/**
	 * 月份计算
	 * @param month
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public static String rebackMonths(String month, int num) throws Exception{
		//-3，-11，-14
		Date monthDate = DateUtils.parseDate(month, new String[] { "yyyy-MM" });
		Calendar cal = Calendar.getInstance();
		cal.setTime(monthDate);
		cal.getTime();
		cal.add(Calendar.MONTH, num);
		return DateFormatUtils.format(cal,"yyyy-MM");
	}
	
	/**
	 * 获取月份列表
	 * @param currentYearMonth
	 * @param months
	 * @return
	 * @throws Exception
	 */
	public static List<String> getBackMonthList(String currentYearMonth, int months) throws Exception
	{
		List<String> monthList = new ArrayList<String>();
		for(int i=months; i >= 0; i--)
		{
			monthList.add(DateEditor.rebackMonths(currentYearMonth, -i));
		}
		return monthList;
	}
	/**
	 * 获取天列表
	 * @param currentYearMonth
	 * @param months
	 * @return
	 * @throws Exception
	 */
	public static List<String> getBackDayList(String date, int days) throws Exception
	{
		List<String> dayList = new ArrayList<String>();
		for(int i=days; i >= 0; i--)
		{
			dayList.add(DateEditor.addDays(date, -i));
		}
		return dayList;
	}
	
	/**
	 * 获取周列表
	 * @param currentYearMonth
	 * @param months
	 * @return
	 * @throws Exception
	 */
	public static List<String> getBackWeekList(String date, int week) throws Exception
	{
		List<String> weekList = new ArrayList<String>();
		for(int i=week; i >= 0; i--)
		{
			weekList.add(DateEditor.addDays(date, -7*i));
		}
		return weekList;
	}
	
	public static void main(String args[]) throws Exception{
//		System.out.println(getLotTime("2015-06-20"));
//		List<String> weekList = getBackWeekList("2016-09-05",5);
		List<String> weekList = getBackDayList("2016-09-05",5);
		System.out.println(weekList.toString());
	}
 }
