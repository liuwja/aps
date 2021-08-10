/*
 * @(#) WebUtil.java 2014-1-21 下午07:12:20
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import com.peg.model.CommonVo;
import com.peg.qms.condition.BaseConditionVO;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("deprecation")
public class WebUtil
{
	/**
	 * 多选条件转换
	 * @param vo
	 */
	public static void convertMultiSelectCondition(CommonVo vo)
	{
		if(StringUtils.isNotBlank(vo.getRegionListTxt())){
			vo.setRegions(vo.getRegionListTxt().replaceAll("'", "").split(";"));
		}
		if(StringUtils.isNotBlank(vo.getPartTypeListTxt())){
			vo.setPartTypes(vo.getPartTypeListTxt().replaceAll("'", "").split(","));
		}
		if(StringUtils.isNotBlank(vo.getPlineListTxt())){
			vo.setPlines(vo.getPlineListTxt().replaceAll("'", "").split(";"));
		}
		if(StringUtils.isNotBlank(vo.getFaultReasonCode())){
			vo.setFaultReasons(vo.getFaultReasonCode().split(","));
		}
		if(StringUtils.isNotBlank(vo.getMeshFaultReasonCode())){
			vo.setMeshFaultReasons(vo.getMeshFaultReasonCode().split(","));
		}
		if(StringUtils.isNotBlank(vo.getShiftGroupTxt())){
			vo.setShiftGroups(vo.getShiftGroupTxt().replaceAll("'", "").split(";"));
		}
		
		if(StringUtils.isNotBlank(vo.getFaultTypeCode())){
			vo.setFaultTypes(vo.getFaultTypeCode().split(","));
		}
		if(StringUtils.isNotBlank(vo.getProductFamilyTxt())){
			vo.setProductFamilys(vo.getProductFamilyTxt().replaceAll("'", "").split(";"));
		}
		
	}
	
	/**
	 * 分页查询（MYSQL）
	 * @param vo
	 * @param dbFilter
	 */
	@SuppressWarnings("unused")
	public static void passStartEndIdxToFilter(BaseConditionVO vo)
	{
		int startNumber = (vo.getPageNum() - 1) * vo.getNumPerPage() + 1;
		int offset =  vo.getOffset();
		
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
	 * 获取开始结束时间的区间集，不重复
	 * @param startTime
	 * @param endTime
	 */
	public static Set<String> getBetweenMonth(String startTime,String endTime){
		try {
			Date startDate = DateUtils.parseDate(startTime, new String[] { "yyyy-MM" });
			Date endDate = DateUtils.parseDate(endTime, new String[] { "yyyy-MM" });
			Set<String> set = new TreeSet<String>();
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			while(endDate.after(cal.getTime())){
				set.add(DateFormatUtils.format(cal, "yyyy-MM"));
				cal.add(Calendar.MONTH, 1);
			}
			set.add(endTime);
			return set;
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 获取开始结束时间的区间集，List
	 * @param startTime
	 * @param endTime
	 */
	public static List<String> getBetweenMonthList(String startTime,String endTime){
		try {
			Date startDate = DateUtils.parseDate(startTime, new String[] { "yyyy-MM" });
			Date endDate = DateUtils.parseDate(endTime, new String[] { "yyyy-MM" });
			List<String> set = new ArrayList<String>();
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			while(endDate.after(cal.getTime())){
				set.add(DateFormatUtils.format(cal, "yyyy-MM"));
				cal.add(Calendar.MONTH, 1);
			}
			set.add(endTime);
			return set;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取时间段的季度，不重复
	 * @param months
	 * @return
	 */
	public static Set<String> getBetweenQuarter(Set<String> months){
		try {
			Set<String> set = new TreeSet<String>();
			for(String mon : months){
				Date date = DateUtils.parseDate(mon, new String[] {"yyyy-MM"});
				String year = DateFormatUtils.format(date, "yyyy");
				float month = date.getMonth()+1;
				String quarter = "";
				switch ((int)Math.ceil(month/3)) {
				case 1:
					quarter =  year+"年1季度";
					break;
				case 2:
					quarter =  year+"年2季度";
					break;
				case 3:
					quarter =  year+"年3季度";
					break;
				case 4:
					quarter =  year+"年4季度";
					break;
				}
				set.add(quarter);
			}
			return set;
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 获取时间段的年份，不重复
	 * @param months
	 * @return
	 */
	public static Set<String> getBetweenYear(Set<String> months){
		try {
			Set<String> set = new TreeSet<String>();
			for(String mon : months){
				Date date = DateUtils.parseDate(mon, new String[] {"yyyy-MM"});
				String year = DateFormatUtils.format(date, "yyyy");
				set.add(year);
			}
			return set;
		} catch (Exception e) {
			return null;
		}
	}
	/**计算获取指定时间内离季度开始计算的月份
	 * @return
	 */
	public static int getDiffquarter(String monthDate){
		Date date;
		try {
			date = DateUtils.parseDate(monthDate, new String[] {"yyyy-MM"});
			int month = date.getMonth();
			if(month%3 == 0){
				return 0;
			}else{
				if((month+1)%3==0){
					return 1;
				}else if((month+2)%3==0){
					return 2;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 计算获取指定时间内离年初开始计算的月份
	 * @param monthDate
	 * @return
	 */
	public static int getDiffYear(String monthDate){
		Date date;
		try {
			date = DateUtils.parseDate(monthDate, new String[] {"yyyy-MM"});
			int month = date.getMonth();
			return 12-month;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 获取第几季度
	 * @param months
	 * @return
	 */
	public static int getQuarter(String dateTime){
		try {
				Date date = DateUtils.parseDate(dateTime, new String[] {"yyyy-MM"});
				float month = date.getMonth()+1;
				int quarter = 0;
				switch ((int)Math.ceil(month/3)) {
				case 1:
					quarter = 1;
					break;
				case 2:
					quarter = 2;
					break;
				case 3:
					quarter = 3;
					break;
				case 4:
					quarter = 4;
					break;
				}
			return quarter;
		} catch (Exception e) {
			return 0;
		}
	}
	/**
	 * 季度间隔数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getSpanQuarter(String startTime,String endTime){
		try {
			int qum1 = getQuarter(startTime);
			int qum2 = getQuarter(endTime);
			
			Date date1 = DateUtils.parseDate(startTime, new String[] {"yyyy-MM"});
			Date date2 = DateUtils.parseDate(endTime, new String[] {"yyyy-MM"});
			return (date2.getYear()- date1.getYear())*4+(qum2-qum1);
		} catch (Exception e) {
			return 0;
		}
	}
	/**
	 * 年份间隔
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getSpanYear(String startTime,String endTime){
		try {
			Date date1 = DateUtils.parseDate(startTime, new String[] {"yyyy-MM"});
			Date date2 = DateUtils.parseDate(endTime, new String[] {"yyyy-MM"});
			return date2.getYear()- date1.getYear();
		} catch (Exception e) {
			return 0;
		}
	}
	/**
	 * 是否同属一年份
	 * @param dateTime 年月
	 * @param month 累加月份1
	 * @param difMonth 累加月份2
	 * @return
	 */
	public static Boolean isSameYear(String dateTime,int month,int difMonth){
		try {
			Date Date = DateUtils.parseDate(dateTime, new String[] {"yyyy-MM"});
			Calendar ca1 = Calendar.getInstance();
			ca1.setTime(Date);
			ca1.add(Calendar.MONTH, month);
			
			Calendar ca2 = Calendar.getInstance();
			ca2.setTime(Date);
			ca2.add(Calendar.MONTH, difMonth);
			boolean result = ca2.get(Calendar.YEAR) == ca1.get(Calendar.YEAR);
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 是否同属一个季度
	 * @param dateTime
	 * @param month
	 * @param difMonth
	 * @return
	 */
	public static Boolean isSameQuarter(String dateTime,int month,int difMonth){
		try {
			Date Date = DateUtils.parseDate(dateTime, new String[] {"yyyy-MM"});
			Calendar ca1 = Calendar.getInstance();
			ca1.setTime(Date);
			ca1.add(Calendar.MONTH, month);
			
			Calendar ca2 = Calendar.getInstance();
			ca2.setTime(Date);
			ca2.add(Calendar.MONTH, difMonth);
			int qum1 = getQuarter(DateFormatUtils.format(ca1.getTime(), "yyyy-MM"));
			int qum2 = getQuarter(DateFormatUtils.format(ca2.getTime(), "yyyy-MM"));
			
			boolean result = qum2 == qum1;
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public static Date parseMonthToDate(String yearMonth) throws Exception{
		Date monthDate = DateUtils.parseDate(yearMonth, new String[] { "yyyy-MM" });
		return monthDate;
	}
	
	
	public static String addDays(String date, int num) throws Exception{
		Date month_date = DateUtils.parseDate(date, new String[] { "yyyy-MM-dd" });
		Calendar cal = Calendar.getInstance();
		cal.setTime(month_date);
		cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, num);
		return DateFormatUtils.format(cal,"yyyy-MM-dd");
	}
	/**
	 * 如果yearMonth1在yearMonth2之前则返回true，否则返回false
	 * @param yearMonth1
	 * @param yearMonth2
	 * @return
	 * @throws Exception
	 */
	public static boolean compareYearMonthDate(String yearMonth1, String yearMonth2) throws Exception{
		Date date1 = DateUtils.parseDate(yearMonth1, new String[] { "yyyy-MM" });
		Date date2 = DateUtils.parseDate(yearMonth2, new String[] { "yyyy-MM" });
		
		return date1.before(date2);
	}
	
	/**
	 * 获取月份列表
	 * @param currentYearMonth
	 * @param months
	 * @return
	 * @throws Exception
	 */
	public static List<String> getDateList(String currentYearMonth, int months) throws Exception
	{
		List<String> monthList = new ArrayList<String>();
		for(int i=0; i < months; i++)
		{
			monthList.add(WebUtil.rebackMonths(currentYearMonth, -i));
		}
		return monthList;
	}
	
	/**
	 * 往前推baseMonths个月为基准月份，在往前推months个月,获取月份列表
	 * @param currentYearMonth
	 * @param months
	 * @param baseMonths
	 * @return
	 * @throws Exception
	 */
	public static List<String> getDateList(String currentYearMonth, int months, int baseMonths) throws Exception
	{
		String baseMonth = WebUtil.rebackMonths(currentYearMonth, -baseMonths);
		return getDateList(baseMonth, months);
	}
	
	
	
	/**
	 * 将字符串按“;”分割，并返回以“,”分割的字符串
	 * 1;2;3;4;5 ==> '1','2','3','4','5'
	 * @param str
	 * @return
	 */
	public static String parseToSelectedStr(String str) {
		if(StringUtils.isBlank(str)) {
			return "";
		}
		String[] arr = str.split(";");
		StringBuilder sb = new StringBuilder();
		for(String s :arr) {
			sb.append(",'").append(s).append("'");
		}
		if(arr.length > 0) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}
	
	/**
	 * 将字符串按“,”分割，并返回一个数组
	 * '1','2','3','4','5' ==> [1,2,3,4,5]
	 * @param str
	 * @return
	 */
	public static String[] parseToSelectedStrArr(String str) {
		String[] strArr = str.replaceAll("\'", "").split(",");
		return strArr;
	}
	
	/**
	 * 可访问质量系统
	 * @param accessPrivilege
	 * @return
	 */
	public static boolean canAccessQms(int accessPrivilege)
	{
		return (accessPrivilege & ConstantInterface.QUALITY_SYSTEM)> 0 ;
	}
	
	/**
	 * 可访问绩效系统
	 * @param accessPrivilege
	 * @return
	 */
	public static boolean canAccessAchievement(int accessPrivilege)
	{
		return (accessPrivilege & ConstantInterface.ACHIEVEMENT_SYSTEM)> 0 ;
	}
	
	public static boolean canAccess(int accessPrivilege, int systemType)
	{
		return (accessPrivilege & systemType)> 0 ;
	}
	
	/**
	 * 编码转换
	 * @param value
	 * @return
	 */
	public static String ISOToUTF8(String value){
		try {
			if(value!=null && value.length()>0 && value.equals(new String(value.getBytes("iso8859-1"),"iso8859-1"))){
				value = new String(value.getBytes("iso8859-1"),"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 获取月份列表
	 * @param currentYearMonth
	 * @param months
	 * @return
	 * @throws Exception
	 */
	public static List<String> getBackDateList(String currentYearMonth, int months) throws Exception
	{
		List<String> monthList = new ArrayList<String>();
		for(int i=months; i >= 0; i--)
		{
			monthList.add(WebUtil.rebackMonths(currentYearMonth, -i));
		}
		return monthList;
	}
	
	/**
	 * 比较两个日期的大小，如果date1比date2大返回true，否则返回false，相等时也返回false。两个日期的格式必须为“yyyy-MM”
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDate(String date1, String date2) {
		Integer startYear = Integer.parseInt(date1.substring(0, 4));
		Integer endYear = Integer.parseInt(date2.substring(0, 4));
		Integer startMonth = Integer.parseInt(date1.substring(5, 7));
		Integer endMonth = Integer.parseInt(date2.substring(5, 7));
		if (startYear > endYear) {
			return true;
		} else if (startYear == endYear && startMonth > endMonth) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 得出两个时间相差的月份数，日期格式必须为“yyyy-MM”
	 * @param startTime 开始时间
	 * @param endTime 结果时间
	 * @return 相差月份数
	 */
	public static Integer getMonthQuantity(String startTime, String endTime) {
		Integer startYear = Integer.parseInt(startTime.substring(0, 4));
		Integer endYear = Integer.parseInt(endTime.substring(0, 4));
		Integer startMonth = 0;
		Integer endMonth = 0;
		if (startTime.length() == 7) {
			startMonth = Integer.parseInt(startTime.substring(5, 7));
		} else {
			startMonth = Integer.parseInt(startTime.substring(5, 6));
		}
		if (endTime.length() == 7) {
			endMonth = Integer.parseInt(endTime.substring(5, 7));
		} else {
			endMonth = Integer.parseInt(endTime.substring(5, 6));
		}
		Integer l = ((endYear - startYear - 1) * 12) + ((12 - startMonth) + endMonth);
		return l;
	}
	
	/**
	 * 查询连续的日期数据时（格式为“yyyy-MM”），补充缺失的日期
	 * @param list 日期数据
	 * @param dateName 日期属性名称（首字母大写）
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	public static List<CommonVo> supplementMonth(List<CommonVo> list, String dateName, String startTime, String endTime) {
		try {
//			Class clazz = CommonVo.class;
//			Method getMonth = clazz.getDeclaredMethod("get" + dateName);
//			Method setMonth = clazz.getDeclaredMethod("set" + dateName, String.class);
//			Method setCount = clazz.getDeclaredMethod("set" + countName, Long.class);
			int l = WebUtil.getMonthQuantity(startTime, endTime);
			if (list.size() <= l) {
				for (int i = 0; i <= l; i++) {
					int tempType = -1;
					String month = WebUtil.rebackMonths(startTime, i);
					if (dateName.equals("DownLineMonth")) { //时间序列三角阵
						for (int j = 0; j < list.size(); j++) {
							CommonVo tempVo = list.get(j);
							if (tempVo.getDownLineMonth().equals(month)) {
								tempType = j;
								break;
							}
						}
						if (tempType == -1) {
							CommonVo emptyVo = new CommonVo();
							emptyVo.setDownLineMonth(month);
							emptyVo.setDownlineCount(0L);
							list.add(i, emptyVo);
						}
					} else if (dateName.equals("InstallMonth")) { //安装三角阵
						for (int j = 0; j < list.size(); j++) {
							CommonVo tempVo = list.get(j);
							if (tempVo.getInstallMonth().equals(month)) {
								tempType = j;
								break;
							}
						}
						if (tempType == -1) {
							CommonVo emptyVo = new CommonVo();
							emptyVo.setInstallMonth(month);
							emptyVo.setInstallCount(0L);
							list.add(i, emptyVo);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}