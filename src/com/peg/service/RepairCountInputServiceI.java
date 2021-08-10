package com.peg.service;

import java.util.List;

import com.peg.model.RepairRateInput;

public interface RepairCountInputServiceI extends BaseService<RepairRateInput, Long>{
	/**
	 * 根据维修月份、机型类型查询录入数据
	 * @param repairMonth 维修月份
	 * @param productType 机型类别
	 * @return
	 */
	public RepairRateInput getByTypeAndMonth(String repairMonth,String productType);
	/**
	 * 根据条件 计算 并更新单月维修率和累计维修率
	 * @param productType  机型类别
	 * @param startMonth 维修月份（起始）
	 * @param endMonth 维修月份（截止）
	 */
	public void updateCalculate(String productType,String startMonth,String endMonth) throws Exception;
	/**
	 * 录入的警示图展示
	 * @return
	 */
	public List<RepairRateInput> queryAlarm(String startMonth,String endMonth) throws Exception;
	/**
	 * 录入的时间序列图
	 * @param productType
	 * @param startMonth
	 * @param endMonth
	 */
	public List<RepairRateInput> queryTimeChart(String productType,String queryMonth) throws Exception;
	
}
