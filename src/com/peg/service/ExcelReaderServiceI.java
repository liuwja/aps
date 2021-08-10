package com.peg.service;

import java.util.List;

public interface ExcelReaderServiceI {

	public String[] checkMonthlyImportExcel(List<String[]> dataList);

	public String uploadMonthly(String data);

}
