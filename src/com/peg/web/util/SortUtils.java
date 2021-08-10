package com.peg.web.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.peg.model.RepairRateDashboard;
import com.peg.model.part.MarketPart;

public class SortUtils implements Comparator<Object> {
	
	public static String sortType;
	
	public SortUtils(MarketPart marketPart) {
		sortType = marketPart.getSortType();
	}
	
	@Override
	public int compare(Object o1, Object o2) {
		MarketPart part1 = (MarketPart)o1;
		MarketPart part2 = (MarketPart)o2;
		if (sortType.equals("不良率降序")) {
			int flag = part1.getRepairRate().compareTo(part2.getRepairRate());
			return flag;
		}
		int flag = part1.getCommonName().compareTo(part2.getCommonName());  
		return flag;
	}
	
	/**
	 * 按机型排序维修率List
	 * @param list
	 * @return
	 */
	public static List<RepairRateDashboard> sortRepairRate(List<RepairRateDashboard> list) {
		List<RepairRateDashboard> resultList = new ArrayList<RepairRateDashboard>();
		RepairRateDashboard repairRate = null;
		int i = 0;
		while(resultList.size() < 11) {
			String productType = SortUtils.getProductType(i);
			if (list != null && list.size() > 0) {
				for (RepairRateDashboard vo : list) {
					if (productType.equals(vo.getProductType())) {
						resultList.add(vo);
						break;
					}
				}
				if (resultList.size() == i) {
					repairRate = new RepairRateDashboard();
					repairRate.setProductType(productType);
					resultList.add(repairRate);
				}
			} else {
				repairRate = new RepairRateDashboard();
				repairRate.setProductType(productType);
				resultList.add(repairRate);
			}
			i++;
		}
		return resultList;
	}
	
	private static String getProductType(int i) {
		switch(i) {
			case 0:
				return "油烟机";
			case 1:
				return "灶具";
			case 2:
				return "热水器";
			case 3:
				return "洗碗机";
			case 4:
				return "蒸箱";
			case 5:
				return "烤箱";
			case 6:
				return "微波炉";
			case 7:
				return "消毒柜";
			case 8:
				return "蒸微一体";
			case 9:
				return "蒸锅";
			case 10:
				return "保温箱";
		};
		return "";
	}
}
