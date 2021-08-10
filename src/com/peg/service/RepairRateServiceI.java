package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.RepairRate;

public interface RepairRateServiceI {
int insert(RepairRate repairRate);
	
	List<RepairRate>  getAllByPage(BaseSearch bs);
	
	/**
	 * 查询设定的基准维修率
	 * @param bs
	 * @return
	 */
	List<RepairRate>  findByDateRange(BaseSearch bs);
	
	public List<RepairRate> findAll(String startTime,String endTime);
	
	
	int updateByPrimaryKeySelective(RepairRate repairRate);

	RepairRate selectByPrimaryKey(Long id);

	int deleteByPrimaryKey(Long id);
}
