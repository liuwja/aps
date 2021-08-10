package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.MarketRepairRecord;


public interface MarketRepairRecordServiceI {
	
	int insert(MarketRepairRecord marketRepairRecord);
	//所有市场维修记录
	List<MarketRepairRecord>  findAllRepair(PageParameter page,MarketRepairRecord repair);
	//不分页
	public List<MarketRepairRecord> findAll(MarketRepairRecord repair);
	
	//根据条件查找总数
	int getTotalNumber(MarketRepairRecord repair);
	int updateByPrimaryKeySelective(MarketRepairRecord marketRepairRecord);

	MarketRepairRecord selectByPrimaryKey(Long id);

	int deleteByPrimaryKey(Long id);
	
	List<MarketRepairRecord> findAllRepairIntermediate(PageParameter page, MarketRepairRecord repair, String isPage);
}
