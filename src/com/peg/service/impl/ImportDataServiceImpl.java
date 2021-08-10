package com.peg.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.ImportDataMapper;
import com.peg.model.CommonVo;
import com.peg.model.DownLineTotal;
import com.peg.model.MarketRepairTotal;
import com.peg.model.ShipTotal;
import com.peg.service.ImportDataServiceI;
import com.peg.web.util.WebUtil;

@Service("importDataService")
public class ImportDataServiceImpl implements ImportDataServiceI{
	@Autowired
	private ImportDataMapper importDataMapper;
	
	@Override
	public List<Integer> importRepairdataFromCrm(CommonVo vo) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(importDataMapper.deleteCRMDataByTimes(vo));
		list.add(importDataMapper.getDataFromCRM(vo));
		list.add(importDataMapper.deleteSameOrderNoForCRM(vo));
		list.add(importDataMapper.updateServiceCenterForCRM(vo));
//		list.add(importDataMapper.updateInstallDateByCode(vo));   <!-- 该方法已经弃用，因为CEM已经给了安装日期，直接取CEM给的安装日期，不再去匹配安装工单的安装日期   liuwjg 2019年7月1日15:57:27-->
		list.add(importDataMapper.updateDownlineDateByCode(vo));
		list.add(importDataMapper.updateVocCategoryToRe(vo));
		list.add(importDataMapper.updateGas(vo));
		return list;
	}
	
	@Override
	public int importDataFromCRM(CommonVo vo) {
		importDataMapper.deleteCRMDataByTimes(vo);
		int row = importDataMapper.getDataFromCRM(vo);
//		row = importDataMapper.getDataFromCrmBySerialNumber(vo);
		return row;
	}
	
	@Override
	public int updateByPartNameForCRM(CommonVo vo) {
		return importDataMapper.updateByPartNameForCRM(vo);
	}
	
	@Override
	public int updateDownlineDateByCode(CommonVo vo) {
		return importDataMapper.updateDownlineDateByCode(vo);
	}
	
	@Override
	public int updateInstallDateByCode(CommonVo vo) {
		return importDataMapper.updateInstallDateByCode(vo);
	}
	
	@Override
	public int updateServiceCenterForCRM(CommonVo vo) {
		return importDataMapper.updateServiceCenterForCRM(vo);
	}
	
	@Override
	public int deleteSameOrderNoForCRM(CommonVo vo) {
		return importDataMapper.deleteSameOrderNoForCRM(vo);
	}
	
	@Override
	public List<Integer> importDataFromMES(CommonVo vo) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(importDataMapper.deleteDownlineShipDataByTimes(vo));
		list.add(importDataMapper.getDataFromMES(vo));
		list.add(importDataMapper.deleteSameDownlineShip(vo));
		return list;
	}
	
	@Override
	public int updateShipDateForMES(CommonVo vo) {
		return importDataMapper.updateShipDateForMES(vo);
	}
	
	@Override
	public int updateMarketRepairTotalByMonth(MarketRepairTotal record) {
		importDataMapper.deleteMarketRepairTotalByMonth(record);
		return importDataMapper.updateMarketRepairTotalByMonth(record);
	}
	
	@Override
	public int updateDownlineTotalByMonth(DownLineTotal record) {
		importDataMapper.deleteDownlineTotalByMonth(record);
		return importDataMapper.updateDownlineTotalByMonth(record);
	}
	
	@Override
	public int updateShipTotalByMonth(ShipTotal record) {
		importDataMapper.deleteShipTotalByMonth(record);
		return importDataMapper.updateShipTotalByMonth(record);
	}
	
	@Override
	public int sumInstallMonthData(Map<String, String> map)
	{
		importDataMapper.deleteInstallTotalByMonth(map);
		importDataMapper.updateInstallTotalByMonth(map);
		importDataMapper.updateInstallDownlineTime(map);
		return importDataMapper.deleteInstallTotalByIdentity(map);
	}

	@Override
	public int getDataFromCrmBySerialNumber(CommonVo vo) {
		return importDataMapper.getDataFromCrmBySerialNumber(vo);
	}
	
	@Override
	public int deleteInstallTotalByIdentity(Map<String, String> map) {
		return importDataMapper.deleteInstallTotalByIdentity(map);
	}
	
	@Override
	public int sumInstallRepairData(MarketRepairTotal record) {
		importDataMapper.deleteInstallRepair(record);
		return importDataMapper.updateInstallRepair(record);
	}
}
