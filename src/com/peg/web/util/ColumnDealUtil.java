package com.peg.web.util;

import java.math.BigDecimal;

/**
 * 字段处理常用工具类
 * @author xuanm
 *
 */
public class ColumnDealUtil {

	/**
	 * 字符串转换成数值检查
	 * @param checkString 待转化字符串
	 * @return 转换结果
	 */
	public static boolean checkStringToNumber(String checkString){
		try {
			new BigDecimal(checkString);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 检查字段值是否为空或者为null(去掉前后空格)
	 * @param targetStr 待检查字符串
	 * @return 如果字符串为空或者null,返回true,否则返回false
	 */
	public static boolean checkColumnIsEmptyOrNull(String targetStr){
		if(targetStr.trim().equals("") || targetStr == null){
			return true;
		}
		return false;
	}
}
