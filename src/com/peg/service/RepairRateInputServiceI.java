package com.peg.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.model.RepairRateInput;

public interface RepairRateInputServiceI extends BaseService<RepairRateInput, Long>{

	
	List<RepairRateInput> getAllInput(@Param("startMonth") String startMonth,
			@Param("endMonth")String endMonth);
}
