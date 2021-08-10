package com.peg.service;

import java.util.List;

public interface ExcelImportService {

	/**
	 * 检查导入的月度考核指标数据
	 */
	public String[] checkSettingMonthlyImportExcel(List<String[]> dataList);
	
	public String uploadSettingMonthly(String data);
}
