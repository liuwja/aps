package com.peg.service;

import java.util.List;
import java.util.Map;

import com.peg.model.CommonVo;
import com.peg.model.DownLineTotal;
import com.peg.model.MarketRepairTotal;
import com.peg.model.ShipTotal;

/**
 * 接口
 * @author song
 */
public interface ImportDataServiceI {
	
	/**
	 * 将CRM数据导入本地维修记录表
	 * @param vo
	 * @return
	 */
	List<Integer> importRepairdataFromCrm(CommonVo vo);
	
	/**
	 * 通过匹配物料名称将CRM的数据导入本地
	 * @param vo
	 * @return
	 */
	int importDataFromCRM(CommonVo vo);
	
	/**
	 * 通过匹配主机条码将CRM换数据导入本地（仅会导入无法匹配物料名称的数据），对匹配物料名称方法的补充
	 * @param vo
	 * @return
	 */
	int getDataFromCrmBySerialNumber(CommonVo vo);
	
	/**
	 * 对于CRM导入的缺失机型类别，产品型号进行刷新（根据物料名称）
	 * @param vo
	 * @return
	 */
	int updateByPartNameForCRM(CommonVo vo);
	
	/**
	 * 对于CRM导入的数据，通过解析主机条码得出下线日期
	 * 7天头的条码，且条码中不包含线别代码’’L“时，从条码最右至左截取，截取10位后再从左至右截取8位，代表年份(2位)、月份(2位)、日期(2位)
	 * 7天头的条码，且条码中包含线别代码’’L“时，从条码中”L”位开始从左至右截取11位后再从右至左截取8位，代表年份(2位)、月份(2位)、日期(2位)
	 * @param vo
	 * @return
	 */
	int updateDownlineDateByCode(CommonVo vo);

	/**
	 * 对于CRM导入的数据，通过客户ID及产品ID与安装数据匹配得出安装日期
	 * @param vo
	 * @return
	 */
	int updateInstallDateByCode(CommonVo vo);
	
	/**
	 * 对于CRM导入的数据，匹配服务中心表得出合并服务中心名称
	 * @param vo
	 * @return
	 */
	int updateServiceCenterForCRM(CommonVo vo);

	/**
	 * 删除工单号相同的CRM维修数据
	 * @param vo
	 * @return
	 */
	int deleteSameOrderNoForCRM(CommonVo vo);
	
	/**
	 * 从MES导入数据
	 */
	List<Integer> importDataFromMES(CommonVo vo);

	/**
	 * 更新MES基础表发货时间为空的记录的发货时间
	 * @param vo
	 * @return
	 */
	int updateShipDateForMES(CommonVo vo);
	
	/**
	 * 维修汇总
	 */
	int updateMarketRepairTotalByMonth(MarketRepairTotal record);
	
	/**
	 * 下线汇总
	 */
	int updateDownlineTotalByMonth(DownLineTotal record);
	/**
	 * 发货汇总
	 */
	int updateShipTotalByMonth(ShipTotal record);
	/**
	 * 安装汇总
	 */
	int sumInstallMonthData(Map<String,String> map);
	
	/**
	 * 删除重复的安装记录
	 * */
	int deleteInstallTotalByIdentity(Map<String, String> map);
	
	/**
	 * 安装维修汇总
	 */
	int sumInstallRepairData(MarketRepairTotal record);
//	int updateByPartCodeForCRM(CommonVo vo);
}
