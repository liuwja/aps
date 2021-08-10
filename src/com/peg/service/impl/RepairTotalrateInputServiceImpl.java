package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.RepairTotalrateInputMapper;
import com.peg.model.RepairTotalrateInput;
import com.peg.service.AbstractService;
import com.peg.service.RepairTotalrateInputServiceI;

@Service("repairTotalRateService")
public class RepairTotalrateInputServiceImpl extends AbstractService<RepairTotalrateInput, Long> implements RepairTotalrateInputServiceI{

	@Autowired
	private RepairTotalrateInputMapper repairTotalrareInputMapper;
	
	@Override
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(repairTotalrareInputMapper);
	}

	@Override
	public List<RepairTotalrateInput> getTotalAll(String queryMonth) {
		return repairTotalrareInputMapper.getTotalAll(queryMonth);
	}

	@Override
	public RepairTotalrateInput getByTypeAndMonth(String repairMonth,
			String productType) {
		// TODO Auto-generated method stub
		return repairTotalrareInputMapper.getByTypeAndMonth(repairMonth, productType);
	}

}
