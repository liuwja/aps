package com.peg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.model.RepairRateInput;

public interface RepairRateInputMapper extends BaseMapper<RepairRateInput, Long>{

	/**
	 * 查询所有
	 * @param bs
	 * @return
	 */
	List<RepairRateInput> getAllInput(@Param("startMonth") String startMonth,
			@Param("endMonth")String endMonth);
	
	/**
	 * 根据维修月份查询所有
	 * @param bs
	 * @return
	 */
	List<RepairRateInput> findByTypeAndMonth(@Param("productType") String productType,@Param("queryMonth") String queryMonth,
			@Param("endMonth")String endMonth);
	
	/**
	 * 根据维修月份、机型类型查询录入数据
	 * @param repairMonth 维修月份
	 * @param productType 机型类别
	 * @return
	 */
	public RepairRateInput getByTypeAndMonth(@Param("repairMonth") String repairMonth,@Param("productType") String productType);
	/**
	 * 根据机型类别获取区间发货数
	 * @param productType 机型类别
	 * @param startMonth 维修月份  起始
	 * @param endMonth  维修月份  截止
	 * @return
	 */
	public Long getShifCountByType(@Param("productType") String productType,@Param("startMonth") String startMonth,@Param("endMonth") String endMonth);
	/**
	 * 根据机型类别获取区间维修数
	 * @param productType  机型类别
	 * @param startMonth 维修月份  起始
	 * @param endMonth  维修月份  截止
	 * @return
	 */
	public Long getRateCountByType(@Param("productType") String productType,@Param("startMonth") String startMonth,@Param("endMonth") String endMonth);
	/**
	 * 根据机型类别，维修月份区间查询
	 * @param productType
	 * @param startMonth
	 * @param endMonth
	 * @return
	 */
	List<RepairRateInput> getAllByType(@Param("productType") String productType,@Param("startMonth") String startMonth,@Param("endMonth")String endMonth);
}
