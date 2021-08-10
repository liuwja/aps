package com.peg.service.part;

import java.util.List;


public interface IDataImportService {
	
	/**
	 * 导入bom数据
	 * @param sheetData
	 * @return
	 * @throws ApsException
	 */
	int insertData(List<String[]> sheetData) throws Exception;
	
	
}
