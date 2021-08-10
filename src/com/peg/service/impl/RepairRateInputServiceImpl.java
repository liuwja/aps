package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.RepairRateInputMapper;
import com.peg.model.RepairRateInput;
import com.peg.service.AbstractService;
import com.peg.service.RepairRateInputServiceI;

@Service("repairRateInputService")
public class RepairRateInputServiceImpl extends AbstractService<RepairRateInput, Long> implements RepairRateInputServiceI{

	@Autowired
	private RepairRateInputMapper repairRateInputMapper;
	
	@Override
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(repairRateInputMapper);
	}

	@Override
	public List<RepairRateInput> getAllInput(String startMonth, String endMonth) {
		return repairRateInputMapper.getAllInput(startMonth, endMonth);
	}


}
