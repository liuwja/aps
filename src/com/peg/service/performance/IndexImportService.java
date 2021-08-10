package com.peg.service.performance;

import java.util.List;

/**
 * 绩效指标Exce导入接口
 * @author xuanm
 *
 */
public interface IndexImportService {
	
	/**
	 * 检查导入的数据是否符合规范
	 * @param dataList
	 * @return
	 */
	public String[] checkIndexImportData(List<String[]> dataList);
	
	/**
	 * 将上传数据写入数据库（已有记录，更新；无记录，插入）
	 * @param uploadData
	 * @return
	 */
	public String setUploadData(String uploadData);
}
