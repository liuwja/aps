/*
 * @(#) ExcelReaderJXL.java 2014-10-15 上午11:05:44
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.qms.controller;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.springframework.util.StringUtils;

import com.peg.model.bph.MonthlyAssessmentExcel;
import com.peg.model.bph.QprnSettingExcel;

/**
 * TODO
 * <p>
 * 
 * @author Lin, 2014-10-15 上午11:05:44
 */
public class ExcelReaderJXL
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		File file = new File("D:\\45\\test.xls");
		List<String[]> dataList;
		try
		{
			dataList = readExcel(file);
			for(String[] arr : dataList)
			{
				System.out.println(arr[0]);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 读取Excel2003
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<String[]> readExcel(File file) throws Exception
	{
		if(file == null || !file.exists())
		{
			throw new Exception("文件不存在");
		}
		String ext = StringUtils.getFilenameExtension(file.getName());
		if("xlsx".equals(ext.toLowerCase()))
		{
			throw new Exception("不支持xlsx格式的Excel");
		}
		List<String[]> dataList = new ArrayList<String[]>();
		try
		{
			Workbook book = Workbook.getWorkbook(file);
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			// 得到单元格
			for (int j = 1; j < sheet.getRows(); j++)
			{
				String[] singleRow = new String[sheet.getColumns()];
				for (int i = 0; i < sheet.getColumns(); i++)
				{
					Cell cell = sheet.getCell(i, j);
					singleRow[i] = cell.getContents() ;
				}
				dataList.add(singleRow);
			}
			book.close();
		}
		catch (Exception e)
		{
			throw new Exception("文件解析失败1");
		}
		return dataList;
	}
	
	/**
	 * 读取Excel2003
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<String[]> readExcel(InputStream is) throws Exception
	{
		List<String[]> dataList = new ArrayList<String[]>();
		try
		{
			Workbook book = Workbook.getWorkbook(is);
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			// 得到单元格
			for (int j = 1; j < sheet.getRows(); j++)
			{
				String[] singleRow = new String[sheet.getColumns()];
				for (int i = 0; i < sheet.getColumns(); i++)
				{
					Cell cell = sheet.getCell(i, j);
					singleRow[i] = cell.getContents() ;
				}
				dataList.add(singleRow);
			}
			book.close();
		}
		catch (Exception e)
		{
			throw new Exception("文件解析失败");
		}
		return dataList;
	}

	/**
	 * 读取Excel2003
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<QprnSettingExcel> readQprnExcel(InputStream is) throws Exception
	{
		List<QprnSettingExcel> qprnSettinglist = new ArrayList<QprnSettingExcel>();
//		System.out.println(is.toString());	
		try
		{
			Workbook book = Workbook.getWorkbook(is);
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			
			// 得到单元格		
			for (int j = 1; j < sheet.getRows(); j++)
			{		
				
				QprnSettingExcel excel = new QprnSettingExcel();
				String wei = sheet.getCell(2, j).getContents();
				String sco = sheet.getCell(3, j).getContents();
				BigDecimal weight = null ;
				BigDecimal score = null;
				if(wei =="" ||"".equals(wei) ){
					 weight = null;
				}else if(sco =="" ||"".equals(sco)){
					 score = null;
				}else{
					 score = new BigDecimal(sheet.getCell(3, j).getContents());
					 weight = new BigDecimal(sheet.getCell(2, j).getContents());
					 excel.setQprn(sheet.getCell(0, j).getContents());
				     excel.setScoreType(Short.parseShort(( sheet.getCell(1, j).getContents())));
					 excel.setWeight( weight);
					 excel.setScore(score);
					 qprnSettinglist.add(excel);
				}
				

				
			}			
			book.close();
			return qprnSettinglist;
		}
		catch (Exception e)
		{
			throw new Exception("文件解析失败2");
		}
		
		
	}
	
	/**
	 * 读取Excel2003
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<MonthlyAssessmentExcel> readMonthExcel(InputStream is) throws Exception
	{
		String message = "";
		List<MonthlyAssessmentExcel> qprnSettinglist = new ArrayList<MonthlyAssessmentExcel>();
//		System.out.println(is.toString());	
		int num = 0;
		try
		{   
			Workbook book = Workbook.getWorkbook(is);
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			
			// 得到单元格		
			for (int j = 1; j < sheet.getRows(); j++)
			{		
				num++;
				MonthlyAssessmentExcel excel = new MonthlyAssessmentExcel();
		

					 excel.setMonth(sheet.getCell(0, j).getContents());
				     excel.setFactory( sheet.getCell(1, j).getContents());
					 excel.setArea( sheet.getCell(2, j).getContents());
					 excel.setCategory(sheet.getCell(3, j).getContents());
					 excel.setGroupName(sheet.getCell(4, j).getContents());
					 excel.setItemCode(sheet.getCell(5, j).getContents());
					 excel.setItemName(sheet.getCell(6, j).getContents());
					 if(sheet.getCell(7, j).getContents()!=null &&!"".equals(sheet.getCell(7, j).getContents()) ){
						 excel.setItemScale(Double.parseDouble(sheet.getCell(7, j).getContents()));
					 }else{
						 excel.setItemScale(0.0);
					 }
					 
					 excel.setIndexCode(sheet.getCell(8, j).getContents());
					 excel.setIndexName(sheet.getCell(9, j).getContents());
					
					 if(sheet.getCell(10, j).getContents()!= null && !"".equals(sheet.getCell(10, j).getContents())){
						 excel.setIndexScale(Double.parseDouble(sheet.getCell(10, j).getContents()));
					 }else{
						 excel.setIndexScale(0.0);
					 }
					 excel.setWeithKey(sheet.getCell(11, j).getContents());
					 if(sheet.getCell(12, j).getContents()!=null && !"".equals(sheet.getCell(12, j).getContents())){
						 excel.setBaseValue(Double.parseDouble(sheet.getCell(12, j).getContents()));
					 }else{
						 excel.setBaseValue(0.0);
					 }
					 if(sheet.getCell(13, j).getContents()!= null &&!"".equals(sheet.getCell(13, j).getContents())){
						 excel.setTargetValue(Double.parseDouble(sheet.getCell(13, j).getContents()));
					 }else{
						 excel.setTargetValue(0.0);
					 }
					
					 qprnSettinglist.add(excel);
				}
				

				
						
			book.close();
			return qprnSettinglist;
		}
		catch (Exception e)
		{
			message = e.getMessage();
			throw new Exception("第"+num+"行出错" +message);
		}
		
		
	}

}
