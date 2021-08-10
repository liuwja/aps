package com.peg.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class StatisUtils {

	/**
	 * 获取连续字符数组
	 * @param index 开始数
	 * @param count 数组长度
	 * @return
	 */
	public static String[] getArryStr(int index,int count)
	{
		String[] arryString = new String[count];
		for (int i = 0; i < arryString.length; i++) {
			arryString[i] = index+"";
			index++;
		}
		return arryString;
	}
	/**
	 * 获取两时间段月份集合
	 * @param beginMonth 开始年月
	 * @param endMonth  截止年月  当为null时，默认当前月
	 * @return
	 */
	public static List<String> getBetweenMonth(String beginMonth,String endMonth){
		List<String> dateList = new ArrayList<String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");  
		Date botMonth = null;
		Date topMonth = null;
		try {
			botMonth = format.parse(beginMonth);
			if(endMonth==null){
				topMonth = format.parse(format.format(new Date()));
			}else{
				topMonth = format.parse(endMonth);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c_begin = new GregorianCalendar();
		Calendar c_end =   new GregorianCalendar();
		c_begin.setTime(botMonth);
		c_end.setTime(topMonth);
		while (c_begin.before(c_end) || c_begin.equals(c_end)) {
			String monStr = "";
			int month = c_begin.get(Calendar.MONTH) + 1;
			if(month<=9){
				monStr="0"+month;
			}else{
				monStr = month+"";
			}
			int year=c_begin.get(Calendar.YEAR);
			dateList.add(year+"-"+monStr);
			c_begin.add(Calendar.MONTH, 1);
		}
		return dateList;
		
	}
	
	/**
	 *求平均范围
	 * 
	 * @param maxCount
	 * @return
	 */
	public static List<Double> getArrageList(double maxCount, Double repairPercent) {
		Double tempCount = maxCount;
		if (repairPercent != null && repairPercent >= 10) {
			maxCount = repairPercent;
		}
		List<Double> rangeList = new ArrayList<Double>();
		if (maxCount == 0) {
			maxCount = 1l;
		}
		double averageNum = Double.parseDouble(String.format("%.2f",
				(maxCount / 10.0)));
		rangeList.add(0d);
		for (int i = 0; i < 10; i++) {
			if (i < 9) {
				double kl = averageNum + rangeList.get(rangeList.size() - 1);
				rangeList.add(Double.parseDouble(String.format("%.2f", kl)));
			} else {
				rangeList.add(tempCount * 1d);
			}
		}
		return rangeList;
	}
	
	/**
	 * 获取平均范围(整数)
	 * @param maxCount 最大数值
	 * @return
	 */
	public static List<Integer> getArrageIntList(Integer maxCount, Integer repairCount) {
		int tempCount = maxCount;
		if (repairCount != null && repairCount >= 10) {
			maxCount = repairCount;
		}
		List<Integer> rangeList = new ArrayList<Integer>();
		if (maxCount == 0 || maxCount == null || maxCount < 10) {
			maxCount = 10;
		}
		int averageNum = maxCount / 10;
		rangeList.add(0);
		for (int i = 0; i < 10; i++) {
			if (i < 9) {
				int kl = averageNum + rangeList.get(rangeList.size() - 1);
				rangeList.add(kl);
			} else {
				rangeList.add(tempCount);
			}
		}
		return rangeList;
	}
	
	
	
}
