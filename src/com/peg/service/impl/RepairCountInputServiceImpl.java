package com.peg.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.RepairRateInputMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.MachineType;
import com.peg.model.RepairRate;
import com.peg.model.RepairRateInput;
import com.peg.service.AbstractService;
import com.peg.service.MachineTypeServiceI;
import com.peg.service.RepairCountInputServiceI;
import com.peg.service.RepairRateServiceI;
import com.peg.web.util.WebUtil;

@Service("repairCountInputService")
public class RepairCountInputServiceImpl extends AbstractService<RepairRateInput, Long> implements RepairCountInputServiceI{

	@Autowired
	private RepairRateInputMapper repairRateInputMapper;
	
	@Autowired
	private MachineTypeServiceI machineTypeService;
	
	@Autowired
	private RepairRateServiceI repairRateService;
	
	@Override
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(repairRateInputMapper);
	}

	@Override
	public RepairRateInput getByTypeAndMonth(String repairMonth,
			String productType) {
		// TODO Auto-generated method stub
		return repairRateInputMapper.getByTypeAndMonth(repairMonth, productType);
	}

	@Override
	public void updateCalculate(String productType, String startMonth,
			String endMonth) throws Exception{
		List<RepairRateInput> list = repairRateInputMapper.getAllByType(productType, startMonth, endMonth);
		for(RepairRateInput input : list){
			calculateRate(input);
			repairRateInputMapper.updateByPrimaryKey(input);
		}
	}
	
	@Override
	public List<RepairRateInput> queryAlarm(String startMonth,String queryMonth) throws Exception {
		//展示月份集
		List<String> monthList = WebUtil.getDateList(queryMonth, 12);
		Collections.reverse(monthList);
		//机型类别
		List<MachineType> types = machineTypeService.getAll();
		//区间内所有数据
		List<RepairRateInput> list = repairRateInputMapper.findByTypeAndMonth(null, startMonth, queryMonth);
		//类型-月份      obj
		Map<String,RepairRateInput> map = new HashMap<String,RepairRateInput>();
		for(RepairRateInput input : list){
			map.put(input.getTypeCategory()+"_"+input.getRepairMonth(), input);
		}
		//查询设定的基准维修率
		BaseSearch bs = new BaseSearch();
		bs.put("startMon",startMonth);
		bs.put("endMon",queryMonth);
		List<RepairRate>  rateList = repairRateService.findByDateRange(bs);
		Map<String,Double> rateMap = parseToMap(rateList);
		
		//构造结果数据
		List<RepairRateInput> resultList = new ArrayList<RepairRateInput>();
		for(MachineType type : types){
			RepairRateInput newInput = new RepairRateInput();
			String produType = type.getMachineType();
			newInput.setTypeCategory(produType);
			RepairRateInput laterInput = map.get(produType+"_"+queryMonth);
			if(laterInput==null){
				newInput.setRepairRate(new BigDecimal(0));
			}else{
				newInput.setRepairRate(laterInput.getRepairRate());
			}
			List<RepairRateInput> childList = new ArrayList<RepairRateInput>();
			for(String month : monthList){
				RepairRateInput input = map.get(produType+"_"+month);
				//是否超过基准维修率
				if(input!=null){
					Double baseRate = rateMap.get(produType+"_"+month);
					BigDecimal totalRate = input.getRepairTotalRate();
					if(baseRate!=null && totalRate!=null){
						int out = totalRate.doubleValue() > baseRate ? 1:0;
						input.setFlag(out);
					}
				}
				childList.add(input);
			}
			newInput.setRateResults(childList);
			resultList.add(newInput);
		}
		return resultList;
	}
	
	/**
	 * 计算维修率和累计维修率
	 * @param input
	 * @throws Exception 
	 */
	public void calculateRate(RepairRateInput rateInput) throws Exception{
		String endMonth = WebUtil.rebackMonths(rateInput.getRepairMonth(), -3);
		String startMonth = WebUtil.rebackMonths(endMonth, -11);
		//获取累计发货数
		Long totalShifCount = repairRateInputMapper.getShifCountByType(rateInput.getTypeCategory(), startMonth, endMonth);
		//获取累计维修数
		endMonth = rateInput.getRepairMonth();
		startMonth = WebUtil.rebackMonths(endMonth, -11);
		Long totalRateCount = repairRateInputMapper.getRateCountByType(rateInput.getTypeCategory(), startMonth, endMonth);
		//单月维修率
		BigDecimal sigleRateBic = null;
		BigDecimal totalRateBic = null;
		if(totalShifCount == null || totalShifCount == 0){
			sigleRateBic = new BigDecimal(0).setScale(2,  BigDecimal.ROUND_HALF_UP);
			totalRateBic = new BigDecimal(0).setScale(2,  BigDecimal.ROUND_HALF_UP);
			rateInput.setRepairRate(sigleRateBic);
			rateInput.setRepairTotalRate(totalRateBic);
		}else{
			//计算单月维修率
			double siglerate = rateInput.getRepairedCount()==null ? 0:rateInput.getRepairedCount().doubleValue()*100/(totalShifCount.doubleValue()/12);
			sigleRateBic = new BigDecimal(siglerate).setScale(2,  BigDecimal.ROUND_HALF_UP);
			rateInput.setRepairRate(sigleRateBic);
			//计算累计维修率
			double totalrate = totalRateCount == null ? 0 : totalRateCount.doubleValue()*100/totalShifCount.doubleValue();
			totalRateBic = new BigDecimal(totalrate).setScale(2,  BigDecimal.ROUND_HALF_UP);
			rateInput.setRepairTotalRate(totalRateBic);
		}
	}
	
	/**
	 * 设定的各机型月度累计百台维修率
	 * <机型类别_月份, 设定的维修率>
	 * @param rateList
	 * @return
	 */
	private Map<String,Double> parseToMap(List<RepairRate>rateList)
	{
		Map<String,Double> resultMap = new HashMap<String,Double>();
		if(null == rateList || rateList.isEmpty())
		{
			return resultMap;
		}
		//乘以100
		for(RepairRate rate : rateList)
		{
			resultMap.put(rate.getMachineType() + "_" + rate.getYearMon(), rate.getHundredRepairRate().doubleValue() * 100);
		}
		return resultMap;
	}

	@Override
	public List<RepairRateInput> queryTimeChart(String productType,
			String queryMonth) throws Exception {
		//展示月份集
		List<String> monthList = WebUtil.getDateList(queryMonth, 12);
		Collections.reverse(monthList);
		String backMonth = WebUtil.rebackMonths(queryMonth, -11);
		//区间内所有数据
		List<RepairRateInput> list = repairRateInputMapper.findByTypeAndMonth(productType, backMonth, queryMonth);
		//月份  -- obj
		Map<String,RepairRateInput> map = new HashMap<String,RepairRateInput>();
		for(RepairRateInput input : list){
			map.put(input.getRepairMonth(), input);
		}
		//查询设定的基准维修率
		BaseSearch bs = new BaseSearch();
		bs.put("startMon",backMonth);
		bs.put("endMon",queryMonth);
		List<RepairRate>  rateList = repairRateService.findByDateRange(bs);
		Map<String,Double> rateMap = parseToMap(rateList);
		List<RepairRateInput> resultList = new ArrayList<RepairRateInput>();
		for(String month : monthList){
			RepairRateInput input = map.get(month);
			if(input == null){
				continue;
			}
			String endMonth = WebUtil.rebackMonths(month, -3);
			String startMonth = WebUtil.rebackMonths(endMonth, -11);
			//获取累计发货数
			Long totalShifCount = repairRateInputMapper.getShifCountByType(productType, startMonth, endMonth);
			//获取累计维修数
			startMonth = WebUtil.rebackMonths(month, -11);
			Long totalRateCount = repairRateInputMapper.getRateCountByType(productType, startMonth, month);
			
			Double baseRate = rateMap.get(productType+"_"+month);
			input.setBaseRepairRate(baseRate==null?0:baseRate);
			
			input.setTotalShipCount(totalShifCount==null?0:totalShifCount);
			input.setTotalRepairedCount(totalRateCount==null?0:totalRateCount);
			
			resultList.add(input);
		}
		return resultList;
	}

	

}
