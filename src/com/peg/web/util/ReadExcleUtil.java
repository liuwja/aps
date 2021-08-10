package com.peg.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel读取工具类
 * <p>
 * @author Lin, 2015-10-27 下午01:51:27
 */

public class ReadExcleUtil
{
	public static void main(String[] args) throws Exception
	{
		String path = "D:/abc.xls";
//		 String path = "D:/test.xlsx";
		Map<String, List<String[]>> map = readExcel(path);
//		List<String[]> list = map.get("Sheet1");
		List<String[]> list = map.entrySet().iterator().next().getValue();
		for (int i = 0; i < list.size(); i++)
		{
			String[] str = list.get(i);
			for (int j = 0; j < str.length; j++)
			{
				System.out.print(str[j] + "   |   ");
			}
			System.out.println("");
		}
	}

	/**
	 * 读取excel
	 * @param fs 文件流
	 * @param extension excel扩展名
	 * @return
	 * @throws Exception
	 */
	public static Map<String, List<String[]>> readExcel(InputStream fs, String extension) throws Exception
	{
		Workbook wb = null;
		Map<String, List<String[]>> map = new HashMap<String, List<String[]>>();
		try
		{
			if (("xls").equals(extension))
			{
				wb = new HSSFWorkbook(fs);
			}
			else
			{
				wb = new XSSFWorkbook(fs);
			}
			map = getExcelData(wb);
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return map;
	}
	
	/**
	 *获得excle的数据
	 * 
	 * @param pathname
	 * @return
	 * @throws Exception 
	 */
	public static Map<String, List<String[]>> readExcel(String pathname) throws Exception
	{
		InputStream fs = null;
		Workbook wb = null;
		Map<String, List<String[]>> map = new HashMap<String, List<String[]>>();
		try
		{
			// excle的类型
			String readType = pathname.substring(pathname.lastIndexOf("."));
			File file = new File(pathname);
			if (file.exists())
			{
				fs = new FileInputStream(file);
			}
			else
			{
				throw new Exception("文件不存在");
			}
			if (readType.equals(".xls"))
			{
				wb = new HSSFWorkbook(fs);
			}
			else
			{
				wb = new XSSFWorkbook(fs);
			}
			map = getExcelData(wb);
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return map;
	}

	/**
	 * 获得excle xls格式的数据
	 * 
	 * @param wb
	 * @return
	 */
	public static Map<String, List<String[]>> getExcelData(Workbook wb)
	{
		Map<String, List<String[]>> map = new LinkedHashMap<String, List<String[]>>();
		try
		{
			if (wb != null)
			{
				// sheet个数
				int numSheet = wb.getNumberOfSheets();
				for (int i = 0; i < numSheet; i++)
				{
					Sheet sheet = wb.getSheetAt(i);
					String sheetname = sheet.getSheetName();
					List<String[]> listData = getSheetData(sheet); // 读取sheet里的数据
					listData = setMergedRegion(
						sheet, listData);
					map.put(
						sheetname, listData);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 获得sheet 里的数据
	 * 
	 * @param sheet
	 * @return
	 */
	public static List<String[]> getSheetData(Sheet sheet)
	{
		List<String[]> listData = new ArrayList<String[]>();
		try
		{
			if (sheet != null)
			{
				for (int i = 1; i <= sheet.getLastRowNum(); i++)
				{
					Row row = sheet.getRow(i);
					String[] rowData = getRowData(row);
					listData.add(rowData);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return listData;
	}

	/**
	 * 获得row 的数据
	 * 
	 * @param row
	 * @return
	 */
	public static String[] getRowData(Row row)
	{
		String[] rowData = null;
		try
		{
			if (row != null)
			{
				int numcell = row.getLastCellNum();
				rowData = new String[numcell];
				for (int i = 0; i < numcell; i++)
				{
					Cell cell = row.getCell(i);
					rowData[i] = getCellData(cell);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return rowData;
	}

	/**
	 * 获得单元格的值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellData(Cell cell)
	{
		String value = null;
		try
		{
			if (cell != null)
			{
				switch (cell.getCellType())
				{
					case Cell.CELL_TYPE_NUMERIC: // 数值型
						if (DateUtil.isCellDateFormatted(cell))
						{
							// 如果是Date类型则 ，获取该Cell的Date值
							value = new SimpleDateFormat("yyyy-MM-dd").format(DateUtil
								.getJavaDate(cell.getNumericCellValue()));
						}
						else
						{
							// 纯数字，这里要判断是否为小数的情况，因为整数在写入时会被加上小数点
//							String t = cell.getNumericCellValue() + "";
							BigDecimal n = new BigDecimal(cell.getNumericCellValue());
							String t = n.toString();
							// 判断是否有小数点
							if (t.indexOf(".") < 0)
							{
								value = t;
							}
							else
							{
								// 数字格式化对象
								NumberFormat nf = NumberFormat.getInstance();
								// 小数点最大两位
								nf.setMaximumFractionDigits(2);
								// 执行格式化
								value = nf.format(n.doubleValue());
//								System.out.println(t);
//								System.out.println(value);
//								value = n.toString();
							}
						}
						break;
					case Cell.CELL_TYPE_STRING: // 字符串型
						value = cell.getRichStringCellValue().toString();
						break;
					case Cell.CELL_TYPE_FORMULA:// 公式型
						// 读公式计算值
						value = String.valueOf(cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_BOOLEAN:// 布尔
						value = " " + cell.getBooleanCellValue();
						break;
					/* 此行表示该单元格值为空 */
					case Cell.CELL_TYPE_BLANK: // 空值
						value = " ";
						break;
					case Cell.CELL_TYPE_ERROR: // 故障
						value = " ";
						break;
					default:
						value = cell.getRichStringCellValue().toString();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 处理单元格值相等的单元格
	 * 
	 * @param sheet
	 */
	@SuppressWarnings("unused")
	public static List<String[]> setMergedRegion(Sheet sheet, List<String[]> list)
	{
		int num = sheet.getNumMergedRegions();
		List<String[]> listDate = new ArrayList<String[]>();
		try
		{
			for (int i = 0; i < num; i++)
			{
				CellRangeAddress rangeAddress = sheet.getMergedRegion(i);
				int firstcell = rangeAddress.getFirstColumn();
				int firstrow = rangeAddress.getFirstRow();
				int lastcell = rangeAddress.getLastColumn();
				int lastrow = rangeAddress.getLastRow();
				// 处理合并行的值
				if (firstcell == lastcell)
				{
					for (int j = firstrow; j <= lastrow; j++)
					{
						list.get(j)[firstcell] = list.get(firstrow)[firstcell];
					}
				}
				// 处理合并列的值
				if (firstrow == lastrow)
				{
					for (int j = firstcell; j <= lastcell; j++)
					{
						list.get(firstrow)[j] = list.get(firstrow)[j];
					}
				}
				// 处理合并行列
				if (firstcell != lastcell && firstrow != lastrow)
				{
					for (int j = firstrow; j <= lastrow; j++)
					{
						for (int k = firstcell; k <= lastcell; k++)
						{
							list.get(j)[k] = list.get(firstrow)[firstcell];
						}
					}
				}
			}
			listDate = list;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
}