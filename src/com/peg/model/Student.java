/*
 * @(#) Student.java 2015-9-28 下午04:39:46
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.model;

/**
 * TODO
 * <p>
 * @author Lin, 2015-9-28 下午04:39:46
 */
public class Student
{
	String age;
	String name;
	String sex;
	
	
	
	public Student()
	{
	}
	public Student(String name, String sex,String age)
	{
		this.age = age;
		this.name = name;
		this.sex = sex;
	}
	public String getAge()
	{
		return age;
	}
	public void setAge(String age)
	{
		this.age = age;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	
	
}
