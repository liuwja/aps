/*
 * @(#) MathUtil.java 2015-7-27 下午01:06:44
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.util;

import java.math.BigDecimal;

/**
 * TODO
 * <p>
 * 
 * @author Lin, 2015-7-27 下午01:06:44
 */
public class MathUtil
{
	/**
	 * 进行除法运算
	 * d1 / d2
	 * @param d1
	 * @param d2
	 * @param len 保留位数
	 * @return
	 */
	public static double divide(double d1, double d2, int len)
	{
		if(d2 == 0)
		{
			return 0;
		}
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 进行除法运算
	 * d1 / d2
	 * @param d1
	 * @param d2
	 * @return 商, 默认保留3位小数
	 */
	public static double divide(double d1, double d2)
	{
		if(d2 == 0)
		{
			return 0;
		}
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.divide(b2, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static double round(double d, int len)
	{
		// 进行四舍五入 操作
		BigDecimal b1 = new BigDecimal(d);
		BigDecimal b2 = new BigDecimal(1);
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP是BigDecimal的一个常量， 表示进行四舍五入的操作
		return b1.divide(
			b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		double b1 = 1;
		double b2 = 3;
		System.out.println(divide(b1,b2,5));
	}

}
