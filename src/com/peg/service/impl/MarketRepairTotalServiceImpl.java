package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.MarketRepairTotalMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.FaultReason;
import com.peg.model.MarketRepairTotal;
import com.peg.service.AbstractService;
import com.peg.service.MarketRepairTotalServiceI;

@Service("marketRepairTotalService")
public class MarketRepairTotalServiceImpl extends AbstractService<MarketRepairTotal, Long> 
implements  MarketRepairTotalServiceI{
	
	@Autowired
	private MarketRepairTotalMapper totalMaper;

	@Override
	public void setBaseMapper() {
		super.setBaseMapper(totalMaper);
		
	}

	@Override
	public List<MarketRepairTotal> findRepairTotalByPage(MarketRepairTotal total,FaultReason faultReason, PageParameter page) {
		// TODO Auto-generated method stub
		return totalMaper.findRepairTotalByPage(total, faultReason, page);
	}

	@Override
	public int sumRepair(MarketRepairTotal total, FaultReason faultReason) {
		// TODO Auto-generated method stub
		return totalMaper.sumRepair(total, faultReason);
	}

	@Override
	public List<MarketRepairTotal> findAllRepairTotal(MarketRepairTotal total,
			FaultReason faultReason) {
		// TODO Auto-generated method stub
		return totalMaper.findAllRepairTotal(total, faultReason);
	}

}
