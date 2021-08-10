package com.peg.dao;

import java.util.Map;

import com.peg.model.CommonVo;
import com.peg.model.DownLineTotal;
import com.peg.model.MarketRepairTotal;
import com.peg.model.ShipTotal;

public interface ImportDataMapper {
	
	/**
	 * 根据时间，删除CRM导入数据（T_MARKET_REPAIR_RECORD）
	 * @param vo
	 * @return
	 */
	int deleteCRMDataByTimes(CommonVo vo);
	
	/**
	 * 通过匹配物料名称将CRM的数据导入本地（T_MARKET_REPAIR_RECORD）
	 * @param vo
	 * @return
	 */
	int getDataFromCRM(CommonVo vo);
	
	/**
	 * 通过匹配主机条码将CRM换数据导入本地（仅会导入无法匹配物料名称的数据），对匹配物料名称方法的补充（T_MARKET_REPAIR_RECORD）
	 * @param vo
	 * @return
	 */
	int getDataFromCrmBySerialNumber(CommonVo vo);

	/**
	 * 对于CRM导入的缺失机型类别，产品型号进行刷新（根据物料名称）（T_MARKET_REPAIR_RECORD）
	 * @param vo
	 * @return
	 */
	int updateByPartNameForCRM(CommonVo vo);
	
	/**
	 * 对于CRM导入的数据，匹配服务中心表得出合并服务中心名称（T_MARKET_REPAIR_RECORD）
	 * @param vo
	 * @return
	 */
	int updateServiceCenterForCRM(CommonVo vo);
	
	/**
	 * 删除工单号相同的CRM维修数据（T_MARKET_REPAIR_RECORD）
	 * @param vo
	 * @return
	 */
	int deleteSameOrderNoForCRM(CommonVo vo);
	
	/**
	 * 对于CRM导入的数据，通过解析主机条码得出下线日期
	 * 7天头的条码，且条码中不包含线别代码’’L“时，从条码最右至左截取，截取10位后再从左至右截取8位，代表年份(2位)、月份(2位)、日期(2位)
	 * 7天头的条码，且条码中包含线别代码’’L“时，从条码中”L”位开始从左至右截取11位后再从右至左截取8位，代表年份(2位)、月份(2位)、日期(2位)（T_MARKET_REPAIR_RECORD）
	 * @param vo
	 * @return
	 */
	int updateDownlineDateByCode(CommonVo vo);
	
	/**
	 * 对于CRM导入的数据，通过客户ID及产品ID与安装数据匹配得出安装日期（T_MARKET_REPAIR_RECORD）
	 * @param vo
	 * @return
	 */
	int updateInstallDateByCode(CommonVo vo);
	
	/**
	 * 根据时间，删除MES导入数据（T_DOWNLINE_SHIP_RECORD）
	 * @param vo
	 * @return
	 */
	int deleteDownlineShipDataByTimes(CommonVo vo);
	
	/**
	 * 从MES导入数据（T_DOWNLINE_SHIP_RECORD）
	 * @param vo
	 * @return
	 */
	int getDataFromMES(CommonVo vo);
	
	/**
	 * 删除条码重复的数据（T_DOWNLINE_SHIP_RECORD）
	 * @param vo
	 * @return
	 */
	int deleteSameDownlineShip(CommonVo vo);
	
	/**
	 * 更新MES基础表发货时间为空的记录的发货时间（T_DOWNLINE_SHIP_RECORD）
	 */
	int updateShipDateForMES(CommonVo vo);
	
	/**
	 * 根据时间，删除维修汇总数据（T_MARKET_REPAIR_TOTAL_RECORD）
	 * @param record
	 * @return
	 */
	int deleteMarketRepairTotalByMonth(MarketRepairTotal record);
	
	/**
	 * 维修汇总（T_MARKET_REPAIR_TOTAL_RECORD）
	 * @param record
	 * @return
	 */
	int updateMarketRepairTotalByMonth(MarketRepairTotal record);
	
	/**
	 * 根据时间，删除维修汇总表
	 * @param record
	 * @return
	 */
	int deleteInstallRepair(MarketRepairTotal record);
	
	/**
	 * 维修汇总安装三角阵使用（T_INSTALL_REPAIR）
	 * @param record
	 * @return
	 */
	int updateInstallRepair(MarketRepairTotal record);
	
	/**
	 * 根据时间，删除下线汇总数据（T_DOWNLINE_TOTAL_RECORD）
	 * @param record
	 * @return
	 */
	int deleteDownlineTotalByMonth(DownLineTotal record);
	
	/**
	 * 下线汇总（T_DOWNLINE_TOTAL_RECORD）
	 * @param record
	 * @return
	 */
	int updateDownlineTotalByMonth(DownLineTotal record);
	
	/**
	 * 根据时间，删除发货汇总数据（T_SHIP_TOTAL_RECORD）
	 * @param record
	 * @return
	 */
	int deleteShipTotalByMonth(ShipTotal record);
	
	/**
	 * 发货汇总（T_SHIP_TOTAL_RECORD）
	 * @param record
	 * @return
	 */
	int updateShipTotalByMonth(ShipTotal record);
	
	/**
	 * 根据时间，删除安装汇总数据（T_INSTALL）
	 * @param map
	 * @return
	 */
	int deleteInstallTotalByMonth(Map<String, String> map);
	
	/**
	 * 安装汇总（T_INSTALL）
	 * @param map
	 * @return
	 */
	int updateInstallTotalByMonth(Map<String, String> map);
	
	/**
	 * 更新安装汇总表的下线时间
	 * @param map
	 * @return
	 */
	int updateInstallDownlineTime(Map<String, String> map);
	
	/**
	 * 删除安装表中的重复数据（T_INSTALL）
	 * @param map
	 * @return
	 */
	int deleteInstallTotalByIdentity(Map<String, String> map);

	Integer updateVocCategoryToRe(CommonVo vo);

	Integer updateGas(CommonVo vo);
	
//	int updateByPartCodeForCRM(CommonVo vo);
	//模糊匹配维修汇总
//	int updateMarketRepairMatchByMonth(MarketRepairTotal record);
	//精确匹配维修汇总
//	int updateMarketRepairCorrectByMonth(MarketRepairTotal record);
//	int deleteMarketRepairMatchByMonth(MarketRepairTotal record);
//	int deleteMarketRepairCorrectByMonth(MarketRepairTotal record);
}
