package com.peg.web.util;

import java.util.Comparator;

import com.peg.model.CommonVo;

/**
 * 根据维修率进行排序
 * 
 * @author Administrator
 * 
 */
public class StatisTypeComparator implements Comparator<CommonVo> {
	@Override
	public int compare(CommonVo vo1, CommonVo vo2) {
		Double b1 = Double.parseDouble(String.format("%.2f",
				(vo1.getRepairCount() * 100.0 / vo1.getShipCount())));
		Double b2 = Double.parseDouble(String.format("%.2f",
				(vo2.getRepairCount() * 100.0 / vo2.getShipCount())));
		return b2.compareTo(b1);
	}

}
