package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.FaultReason;
import com.peg.model.MarketRepairTotal;

public interface MarketRepairTotalServiceI {
	
	List<MarketRepairTotal> findRepairTotalByPage(MarketRepairTotal total,FaultReason faultReason,PageParameter page); 
	List<MarketRepairTotal> findAllRepairTotal(MarketRepairTotal total,FaultReason faultReason); 
	/**
	 * 维修量总和
	 */
	int sumRepair(MarketRepairTotal total,FaultReason faultReason);

}
