/*
 * @(#) TwoGen.java 2015-4-3 下午03:51:29
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.util;

/**
 * 两个参数的对象
 * <p>
 * 
 * @author Lin, 2015-4-3 下午03:51:29
 */
public class TwoGen<T, V>
{

	private T ob1;

	private V ob2;

	public TwoGen(T o1, V o2)
	{
		ob1 = o1;
		ob2 = o2;
	}

	public TwoGen()
	{
	}
	
	public T getOb1()
	{
		return ob1;
	}

	public V getOb2()
	{
		return ob2;
	}
	
	public void setOb1(T ob1)
	{
		this.ob1 = ob1;
	}

	public void setOb2(V ob2)
	{
		this.ob2 = ob2;
	}

	public static void main(String[] args)
	{
//		TwoGen<List<String>,List<String>> a = new TwoGen<List<String>, List<String>>(o1, o2);
		TwoGen<String,String> a = new TwoGen<String,String>();
		a.setOb1("aa");
		a.setOb2("bb");
		a.getOb1();
		a.getOb2();
	}
}
