package com.peg.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.model.RepairTotalrateInput;

public interface RepairTotalrateInputServiceI extends BaseService<RepairTotalrateInput, Long>{

	List<RepairTotalrateInput> getTotalAll(@Param("queryMonth")String queryMonth);
	
	/**
	 * 根据维修月份、机型类型查询录入累计维修数据
	 * @param repairMonth 维修月份
	 * @param productType 机型类别
	 * @return
	 */
	public RepairTotalrateInput getByTypeAndMonth(@Param("repairMonth") String repairMonth,@Param("productType") String productType);

	
}
