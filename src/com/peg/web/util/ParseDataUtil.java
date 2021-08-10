package com.peg.web.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.Model;

import com.peg.model.CommonVo;
import com.peg.model.TimeMatrixResultVo;
/**
 * 按年、季度统计，解析结果集
 * @author Administrator
 */
public class ParseDataUtil {
	
	/**
	 * 将每月维修数/安装数换算成季度或者年
	 * 
	 * @param list
	 * @param model
	 * @param vo
	 * @param split 当换算成季度时为3，年时为12
	 * @return
	 */
	public static List<TimeMatrixResultVo> splitData(List<TimeMatrixResultVo> list, Model model, CommonVo vo, int split) {
		Long maxRepairCount = 0L; //最大维修数
		Long maxRepPercent = 0L; //最大维修率
		for (int i = 0; i < list.size(); i++) {
			TimeMatrixResultVo matrixResult = list.get(i);
			List<Long> reCount = matrixResult.getReCount();// 维修数
			List<Long> newReCount = new ArrayList<Long>();// 新维修数
			List<Long> newRePercent = new ArrayList<Long>();// 新维修率
			List<Long> newReTotalCount = new ArrayList<Long>();// 新累计维修数
			List<Long> newReTotalPercent = new ArrayList<Long>();// 新累计维修率
			Long repairCount = 0L;
			Long recTotal = 0L;
			if (reCount != null && reCount.size() > 0) {
				if (reCount.size() <= split) {
					for (Long l : reCount) {
						repairCount += l;
					}
					newReCount.add(repairCount); //添加维修数
					newReTotalCount.add(repairCount); //添加累计维修数
					if (matrixResult.getBaseCount() != null && matrixResult.getBaseCount() > 0) {
						newRePercent.add(Math.round(repairCount * 1000000.0 / matrixResult.getBaseCount())); //添加维修率
						newReTotalPercent.add(Math.round(repairCount * 1000000.0 / matrixResult.getBaseCount())); //添加累计维修率
						if (repairCount * 1000000.0 / matrixResult.getBaseCount() > maxRepPercent) { //设置最大维修率，颜色分级使用
							maxRepPercent = Math.round(repairCount * 1000000.0 / matrixResult.getBaseCount());
						}
					}
					if (repairCount > maxRepairCount) { //设置最大维修数，颜色分级使用
						maxRepairCount = repairCount;
					}
				} else if (reCount.size() % split == 0){
					for (int j = split; j <= reCount.size(); j += split) {
						List<Long> newList = reCount.subList(j - split, j);
						for (Long l : newList) {
							repairCount += l;
							recTotal += l;
						}
						if (newReTotalCount.size() != 0) {
							recTotal += newReTotalCount.get(newReTotalCount.size() - 1);
						}
						newReCount.add(repairCount); //添加维修数
						newReTotalCount.add(recTotal);// 添加累计维修数
						if (matrixResult.getBaseCount() != null && matrixResult.getBaseCount() > 0) {
							newRePercent.add(Math.round(repairCount * 1000000.0 / matrixResult.getBaseCount())); //添加维修率
							newReTotalPercent.add(Math.round(recTotal * 1000000.0 / matrixResult.getBaseCount())); //添加累计维修率
							if (repairCount * 1000000.0 / matrixResult.getBaseCount() > maxRepPercent) { //设置最大维修率，颜色分级使用
								maxRepPercent = Math.round(repairCount * 1000000.0 / matrixResult.getBaseCount());
							}
						}
						if (repairCount > maxRepairCount) { //设置最大维修数，颜色分级使用
							maxRepairCount = repairCount;
						}
						repairCount = 0L;
						recTotal = 0L;
					}
				} else {
					int temp = reCount.size() % split;
					for (int j = split; j <= reCount.size() - temp; j += split) {
						List<Long> newList = reCount.subList(j - split, j);
						for (Long l : newList) {
							repairCount += l;
							recTotal += l;
						}
						if (newReTotalCount.size() != 0) {
							recTotal += newReTotalCount.get(newReTotalCount.size() - 1);
						}
						newReCount.add(repairCount); //添加维修数
						newReTotalCount.add(recTotal);// 添加累计维修数
						if (matrixResult.getBaseCount() != null && matrixResult.getBaseCount() > 0) {
							newRePercent.add(Math.round(repairCount * 1000000.0 / matrixResult.getBaseCount())); //添加维修率
							newReTotalPercent.add(Math.round(recTotal * 1000000.0 / matrixResult.getBaseCount())); //添加累计维修率
							if (repairCount * 1000000.0 / matrixResult.getBaseCount() > maxRepPercent) { //设置最大维修率，颜色分级使用
								maxRepPercent = Math.round(repairCount * 1000000.0 / matrixResult.getBaseCount());
							}
						}
						if (repairCount > maxRepairCount) { //设置最大维修数，颜色分级使用
							maxRepairCount = repairCount;
						}
						repairCount = 0L;
						recTotal = 0L;
					}
					List<Long> newList = reCount.subList(reCount.size() - temp, reCount.size());
					for (Long l : newList) {
						repairCount += l;
						recTotal += l;
					}
					if (newReTotalCount.size() != 0) {
						recTotal += newReTotalCount.get(newReTotalCount.size() - 1);
					}
					newReCount.add(repairCount); ////添加维修数
					newReTotalCount.add(recTotal);// 添加累计维修数
					if (matrixResult.getBaseCount() != null && matrixResult.getBaseCount() > 0) {
						newRePercent.add(Math.round(repairCount * 1000000.0 / matrixResult.getBaseCount())); //添加维修率
						newReTotalPercent.add(Math.round(recTotal * 1000000.0 / matrixResult.getBaseCount())); //添加累计维修率
						if (repairCount * 1000000.0 / matrixResult.getBaseCount() > maxRepPercent) { //设置最大维修率，颜色分级使用
							maxRepPercent = Math.round(repairCount * 1000000.0 / matrixResult.getBaseCount());
						}
					}
					if (repairCount > maxRepairCount) { //设置最大维修数，颜色分级使用
						maxRepairCount = repairCount;
					}
				}
			}
			if (i != 0) { //倒三角阵时，设置间隔
				matrixResult.setPreDiff(list.get(0).getReCount().size() - newReCount.size());
			}
			matrixResult.setReCount(newReCount);
			matrixResult.setReTotalCount(newReTotalCount);
			matrixResult.setRepairPercent(newRePercent);
			matrixResult.setRepairTotalPercent(newReTotalPercent);
//			System.out.println("第" + i + "次循环:maxRepairCount = " + maxRepairCount + "-----maxRepPercent = " + maxRepPercent);
		}
		if (vo.getRepairCount() != null) { //设置最大维修数，进行颜色分级
			model.addAttribute("rangeList", StatisUtils.getArrageIntList(maxRepairCount.intValue(), vo.getRepairCount().intValue()));
		} else {
			model.addAttribute("rangeList", StatisUtils.getArrageIntList(maxRepairCount.intValue(), null));
		}
		if (StringUtils.isNotEmpty(vo.getRepairPercent())) { //设置最大维修率，进行颜色分级
			model.addAttribute("rangePercentList", StatisUtils.getArrageList(maxRepPercent, Double.parseDouble(vo.getRepairPercent())));
		} else {
			model.addAttribute("rangePercentList", StatisUtils.getArrageList(maxRepPercent, null));
		}
		return list;
	}
	
	/**
	 * 按年份解析结果集
	 * @return
	 */
	public static List<TimeMatrixResultVo> parseToYear(List<TimeMatrixResultVo> list, Model model) {
		int maxCount = 0;
		double maxPercent = 0L;
		for (int i = 0; i < list.size(); i++) {
			TimeMatrixResultVo matrixResult = list.get(i);
			List<Long> reCount = matrixResult.getReCount();// 维修数
			List<Long> reTotalCount = matrixResult.getReTotalCount();// 累计维修数

			List<Long> newReCount = new ArrayList<Long>();// 新维修数
			List<Long> newRePercent = new ArrayList<Long>();// 新维修率
			List<Long> newReTotalCount = new ArrayList<Long>();// 新累计维修数
			List<Long> newReTotalPercent = new ArrayList<Long>();// 新累计维修率
			Long reSum = 0L;
			Long ToSum = 0L;
			if (i == 0) {
				matrixResult.setPreDiff(0);
			}
			int span = WebUtil.getSpanYear(list.get(0).getBaseMonth(),matrixResult.getBaseMonth());
			matrixResult.setPreDiff(span);
			// 判断初始年份统计
			int diffMonth = WebUtil.getDiffYear(matrixResult.getBaseMonth());
			if (reCount.size() <= diffMonth) {
				diffMonth = reCount.size();
			}
			// 初始第一年-------------------------------
			if (diffMonth != 0) {
				for (int r = 0; r < diffMonth; r++) {
					Long re = reCount.get(r);
					Long toRe = reTotalCount.get(r);
					if (re == null) {
						re = 0L;
					}
					if (toRe == null) {
						toRe = 0L;
					}
					reSum = reSum + re;
					ToSum = ToSum + toRe;
				}
				newReCount.add(reSum);
				newReTotalCount.add(ToSum);
				if (maxCount < reSum) {
					maxCount = reSum.intValue();
				}
				long statisPer = Math.round(reSum * 1000000.0 / matrixResult.getBaseCount());
				long statisToPer = Math.round(ToSum * 1000000.0 / matrixResult.getBaseCount());
				double k = (double) statisPer;
				if (maxPercent < k) {
					maxPercent = k;
				}
				newRePercent.add(statisPer);// 维修率
				newReTotalPercent.add(statisToPer);// 累计维修率
				reSum = 0L;
				ToSum = 0L;
			}
			// 从第二年开始每隔十二月统计------------
			int len = reCount.size();
			for (int j = diffMonth; j < len; j++) {
				Long re = reCount.get(j);
				Long toRe = reTotalCount.get(j);
				if (re == null) {
					re = 0L;
				}
				if (toRe == null) {
					toRe = 0L;
				}
				reSum = reSum + re;
				ToSum = ToSum + toRe;
				int nextMon = j+1;
				if (j + 1>=len) {
					nextMon = len;
				} 
				if (!WebUtil.isSameYear(matrixResult.getBaseMonth(), j, nextMon) || j==len-1) {
					newReCount.add(reSum);
					newReTotalCount.add(ToSum);
					if (maxCount < reSum) {
						maxCount = reSum.intValue();
					}
					long statisPer = Math.round(reSum * 1000000.0 / matrixResult.getBaseCount());
					long statisToPer = Math.round(ToSum * 1000000.0 / matrixResult.getBaseCount());
					double k = (double) statisPer;
					if (maxPercent < k) {
						maxPercent = k;
					}
					newRePercent.add(statisPer);// 维修率
					newReTotalPercent.add(statisToPer);// 累计维修率
					reSum = 0L;
					ToSum = 0L;
				}
			}
			matrixResult.setReCount(newReCount);
			matrixResult.setRepairPercent(newRePercent);
			matrixResult.setReTotalCount(newReTotalCount);
			matrixResult.setRepairTotalPercent(newReTotalPercent);
		}
		model.addAttribute("rangeList", StatisUtils.getArrageIntList(maxCount, null));
		model.addAttribute("rangePercentList",StatisUtils.getArrageList(maxPercent, null));
		return list;
	}

	public static List<TimeMatrixResultVo> parseToYearByMarketPart(List<TimeMatrixResultVo> list, Model model) {
		int maxCount = 0;
		double maxPercent = 0L;
		for (int i = 0; i < list.size(); i++) {
			TimeMatrixResultVo matrixResult = list.get(i);
			List<Long> reCount = matrixResult.getReCount();// 维修数

			List<Long> newReCount = new ArrayList<Long>();// 新维修数
			List<Long> newRePercent = new ArrayList<Long>();// 新维修率
			Long reSum = 0L;
			if (i == 0) {
				matrixResult.setPreDiff(0);
			}
			int span = WebUtil.getSpanYear(list.get(0).getBaseMonth(),matrixResult.getBaseMonth());
			matrixResult.setPreDiff(span);
			// 判断初始年份统计
			int diffMonth = WebUtil.getDiffYear(matrixResult.getBaseMonth());
			if (reCount.size() <= diffMonth) {
				diffMonth = reCount.size();
			}
			// 初始第一年-------------------------------
			if (diffMonth != 0) {
				for (int r = 0; r < diffMonth; r++) {
					Long re = reCount.get(r);
					if (re == null) {
						re = 0L;
					}
					reSum = reSum + re;
				}
				newReCount.add(reSum);
				if (maxCount < reSum) {
					maxCount = reSum.intValue();
				}
				long statisPer = Math.round(reSum * 1000000.0 / matrixResult.getBaseCount());
				double k = (double) statisPer;
				if (maxPercent < k) {
					maxPercent = k;
				}
				newRePercent.add(statisPer);// 维修率
				reSum = 0L;
			}
			// 从第二年开始每隔十二月统计------------
			int len = reCount.size();
			for (int j = diffMonth; j < len; j++) {
				Long re = reCount.get(j);
				if (re == null) {
					re = 0L;
				}
				reSum = reSum + re;
				int nextMon = j+1;
				if (j + 1>=len) {
					nextMon = len;
				} 
				if (!WebUtil.isSameYear(matrixResult.getBaseMonth(), j, nextMon) || j==len-1) {
					newReCount.add(reSum);
					if (maxCount < reSum) {
						maxCount = reSum.intValue();
					}
					long statisPer = Math.round(reSum * 1000000.0 / matrixResult.getBaseCount());
					double k = (double) statisPer;
					if (maxPercent < k) {
						maxPercent = k;
					}
					newRePercent.add(statisPer);// 维修率
					reSum = 0L;
				}
			}
			matrixResult.setReCount(newReCount);
			matrixResult.setRepairPercent(newRePercent);
		}
		model.addAttribute("rangeList", StatisUtils.getArrageIntList(maxCount, null));
		model.addAttribute("rangePercentList",StatisUtils.getArrageList(maxPercent, null));
		return list;
	}
	
	/**
	 * 按季度解析
	 */
	public static List<TimeMatrixResultVo> parseToQuarter(
			List<TimeMatrixResultVo> list, Model model) {
		int maxCount = 0;
		double maxPercent = 0L;
		for (int i = 0; i < list.size(); i++) {
			TimeMatrixResultVo matrixResult = list.get(i);
			List<Long> reCount = matrixResult.getReCount();// 维修数
			List<Long> reTotalCount = matrixResult.getReTotalCount();// 累计维修数

			List<Long> newReCount = new ArrayList<Long>();// 新维修数
			List<Long> newRePercent = new ArrayList<Long>();// 新维修率
			List<Long> newReTotalCount = new ArrayList<Long>();// 新累计维修数
			List<Long> newReTotalPercent = new ArrayList<Long>();// 新累计维修率
			Long reSum = 0L;
			Long ToSum = 0L;
			int diffMonth = 0;
			if (i == 0) {
				matrixResult.setPreDiff(0);
			}
			int span = WebUtil.getSpanQuarter(list.get(0).getBaseMonth(),matrixResult.getBaseMonth());
			matrixResult.setPreDiff(span);
			diffMonth = WebUtil.getDiffquarter(matrixResult.getBaseMonth());
			if (reCount.size() <= diffMonth) {
				diffMonth = reCount.size();
			}
			// 初始第一季度-------------------------------
			if (diffMonth != 0) {
				for (int r = 0; r < diffMonth; r++) {
					Long re = reCount.get(r);
					Long toRe = reTotalCount.get(r);
					if (re == null) {
						re = 0L;
					}
					if (toRe == null) {
						toRe = 0L;
					}
					reSum = reSum + re;
					ToSum = ToSum + toRe;
				}
				newReCount.add(reSum);
				newReTotalCount.add(ToSum);
				if (maxCount < reSum) {
					maxCount = reSum.intValue();
				}
				long statisPer = Math.round(reSum * 1000000.0 / matrixResult.getBaseCount());
				long statisToPer = Math.round(ToSum * 1000000.0 / matrixResult.getBaseCount());
				double k = (double) statisPer;
				if (maxPercent < k) {
					maxPercent = k;
				}
				newRePercent.add(statisPer);// 维修率
				newReTotalPercent.add(statisToPer);// 累计维修率
				reSum = 0L;
				ToSum = 0L;
			}
			// 从第二季度开始每隔三月统计-------------------------------
			int len = reCount.size();
			for (int j = diffMonth; j < len; j++) {
				Long re = reCount.get(j);
				Long toRe = reTotalCount.get(j);
				if (re == null) {
					re = 0L;
				}
				if (toRe == null) {
					toRe = 0L;
				}
				reSum = reSum + re;
				ToSum = ToSum + toRe;
				int nextMon = j+1;
				if (j + 1>=len) {
					nextMon = len;
				} 
				//不同季度进入统计
				if (!WebUtil.isSameQuarter(matrixResult.getBaseMonth(), j, nextMon) || j==len-1) {
					newReCount.add(reSum);
					newReTotalCount.add(ToSum);
					if (maxCount < reSum) {
						maxCount = reSum.intValue();
					}
					long statisPer = Math.round(reSum * 1000000.0 / matrixResult.getBaseCount());
					long statisToPer = Math.round(ToSum * 1000000.0 / matrixResult.getBaseCount());
					double k = (double) statisPer;
					if (maxPercent < k) {
						maxPercent = k;
					}
					newRePercent.add(statisPer);// 维修率
					newReTotalPercent.add(statisToPer);// 累计维修率
					reSum = 0L;
					ToSum = 0L;
				}
			}
			matrixResult.setReCount(newReCount);
			matrixResult.setRepairPercent(newRePercent);
			matrixResult.setReTotalCount(newReTotalCount);
			matrixResult.setRepairTotalPercent(newReTotalPercent);
		}
		model.addAttribute("rangeList",StatisUtils.getArrageIntList(maxCount, null));
		model.addAttribute("rangePercentList",StatisUtils.getArrageList(maxPercent, null));
		return list;
	}

	public static List<TimeMatrixResultVo> parseToQuarterByMarketPart(List<TimeMatrixResultVo> list, Model model) {
		int maxCount = 0;
		double maxPercent = 0L;
		for (int i = 0; i < list.size(); i++) {
			TimeMatrixResultVo matrixResult = list.get(i);
			List<Long> reCount = matrixResult.getReCount();// 维修数

			List<Long> newReCount = new ArrayList<Long>();// 新维修数
			List<Long> newRePercent = new ArrayList<Long>();// 新维修率
			Long reSum = 0L;
			int diffMonth = 0;
			if (i == 0) {
				matrixResult.setPreDiff(0);
			}
			int span = WebUtil.getSpanQuarter(list.get(0).getBaseMonth(),matrixResult.getBaseMonth());
			matrixResult.setPreDiff(span);
			diffMonth = WebUtil.getDiffquarter(matrixResult.getBaseMonth());
			if (reCount.size() <= diffMonth) {
				diffMonth = reCount.size();
			}
			// 初始第一季度-------------------------------
			if (diffMonth != 0) {
				for (int r = 0; r < diffMonth; r++) {
					Long re = reCount.get(r);
					if (re == null) {
						re = 0L;
					}
					reSum = reSum + re;
				}
				newReCount.add(reSum);
				if (maxCount < reSum) {
					maxCount = reSum.intValue();
				}
				long statisPer = Math.round(reSum * 1000000.0 / matrixResult.getBaseCount());
				double k = (double) statisPer;
				if (maxPercent < k) {
					maxPercent = k;
				}
				newRePercent.add(statisPer);// 维修率
				reSum = 0L;
			}
			// 从第二季度开始每隔三月统计-------------------------------
			int len = reCount.size();
			for (int j = diffMonth; j < len; j++) {
				Long re = reCount.get(j);
				if (re == null) {
					re = 0L;
				}
				reSum = reSum + re;
				int nextMon = j+1;
				if (j + 1>=len) {
					nextMon = len;
				} 
				//不同季度进入统计
				if (!WebUtil.isSameQuarter(matrixResult.getBaseMonth(), j, nextMon) || j==len-1) {
					newReCount.add(reSum);
					if (maxCount < reSum) {
						maxCount = reSum.intValue();
					}
					long statisPer = Math.round(reSum * 1000000.0 / matrixResult.getBaseCount());
					double k = (double) statisPer;
					if (maxPercent < k) {
						maxPercent = k;
					}
					newRePercent.add(statisPer);// 维修率
					reSum = 0L;
				}
			}
			matrixResult.setReCount(newReCount);
			matrixResult.setRepairPercent(newRePercent);
		}
		model.addAttribute("rangeList",StatisUtils.getArrageIntList(maxCount, null));
		model.addAttribute("rangePercentList",StatisUtils.getArrageList(maxPercent, null));
		return list;
	}
}
