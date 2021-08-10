package com.peg.web.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.Model;

import com.peg.model.TimeMatrixResultVo;
import com.peg.model.part.MarketPart;
import com.peg.service.part.MarketPartServiceI;

public class MarketPartUtil {
	
	public static String getChartTitle(MarketPart vo) {
		switch(vo.getTitle()) {
			case ConstantInterface.PART_DEFECT_HISTOGRAM_SUPPLIER:
				return vo.getStartTime() + "~" + vo.getEndTime() + " 供应商不良排列图";
			case ConstantInterface.PART_DEFECT_HISTOGRAM_PART:
				return vo.getStartTime() + "~" + vo.getEndTime() + " 零部件不良排列图";
			case ConstantInterface.PART_DEFECT_HISTOGRAM_REGION:
				return vo.getStartTime() + "~" + vo.getEndTime() + " 区域不良排列图";
			case ConstantInterface.PART_DEFECT_HISTOGRAM_FAULT_TYPE:
				return vo.getStartTime() + "~" + vo.getEndTime() + " 故障大类不良排列图";
			case ConstantInterface.PART_DEFECT_HISTOGRAM_FAULT_REASON:
				return vo.getStartTime() + "~" + vo.getEndTime() + " 故障小类不良排列图";
			case ConstantInterface.PART_DEFECT_HISTOGRAM_PART_LEVEL:
				return vo.getStartTime() + "~" + vo.getEndTime() + " 物料级别不良排列图";
			case ConstantInterface.PART_DEFECT_TREND:
				return vo.getStartTime() + "~" + vo.getEndTime() + " 市场不良趋势图";
			case ConstantInterface.PART_DEFECT_TREND_MONTH:
				return vo.getStartTime() + "~" + vo.getEndTime() + " 市场不良趋势图-单月";
			case ConstantInterface.PART_DEFECT_TREND_HUNDRED:
				return vo.getStartTime() + "~" + vo.getEndTime() + " 市场不良趋势图-百台维修";
		}
		return "";
	}
	
	/**
	 * 设置查询条件
	 * @param vo
	 * */
	public static MarketPart setData(MarketPartServiceI marketPartService, MarketPart vo) {
		if (StringUtils.isNotEmpty(vo.getSupplierNumber())) { //供应商
			vo.setSupplierNumbers(vo.getSupplierNumber().split(","));
			vo.setSupplierNumberList(Arrays.asList(vo.getSupplierNumbers()));
		}
		if (StringUtils.isNotEmpty(vo.getRegionListTxt())) {
			vo.setRegions(vo.getRegionListTxt().split(";"));
		}
		if (StringUtils.isNotEmpty(vo.getFaultTypeCode())) {
			vo.setFaultTypes(vo.getFaultTypeCode().split(","));
		}
		if (StringUtils.isNotEmpty(vo.getFaultReasonCode())) {
			vo.setFaultReasons(vo.getFaultReasonCode().split(","));
		}
		if (StringUtils.isNotEmpty(vo.getPartTypesListTxt())) {
			vo.setPartTypes(vo.getPartTypesListTxt().split(";"));
		}
		if (StringUtils.isNotEmpty(vo.getPartNumber())) {
			String partNumber = MarketPartUtil.getNewAndOldPart(vo.getPartNumber(), marketPartService);
			vo.setPartNumber(partNumber);
			vo.setPartNumbers(partNumber.split(","));
			vo.setPartNumberList(Arrays.asList(vo.getPartNumbers()));
			if (ConstantInterface.PART_NO_HAS_VERSION.equals(vo.getHasVersion())) { //不带版本
				List<String> partNumberList = Arrays.asList(vo.getPartNumber().split(","));
//				for (String s : partNumberList) {
//					s = s.substring(0, s.length() - 1);
//				}
				for (int i = 0; i < partNumberList.size(); i++) {
					partNumberList.set(i, partNumberList.get(i).substring(0, partNumberList.get(i).length() - 1));
				}
				vo.setPartNumbers(partNumberList.toArray(new String[partNumberList.size()]));
				vo.setPartNumberList(Arrays.asList(vo.getPartNumber().split(",")));
			}
		}
		if (StringUtils.isNotEmpty(vo.getMesPartNumber())) {
			String mesPartNumber = MarketPartUtil.getNewAndOldPart(vo.getMesPartNumber(), marketPartService);
			vo.setMesPartNumber(mesPartNumber);
			vo.setMesPartNumbers(mesPartNumber.split(","));
			vo.setMesPartNumberList(Arrays.asList(vo.getMesPartNumber()));
			if (ConstantInterface.PART_NO_HAS_VERSION.equals(vo.getHasVersion())) { //不带版本
				List<String> partNumberList = Arrays.asList(vo.getMesPartNumber().split(","));
				for (int i = 0; i < partNumberList.size(); i++) {
					partNumberList.set(i, partNumberList.get(i).substring(0, partNumberList.get(i).length() - 1));
				}
				vo.setMesPartNumbers(partNumberList.toArray(new String[partNumberList.size()]));
				vo.setMesPartNumberList(Arrays.asList(vo.getMesPartNumber().split(",")));
			}
		}
		return vo;
	}
	
	/**
	 * 获取三角阵数据
	 * @param model
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static List<TimeMatrixResultVo> getMatrixData(MarketPartServiceI marketPartService, Model model, MarketPart vo) throws Exception {
		List<MarketPart> matrixDataList = marketPartService.getMatrixData(vo);
		List<MarketPart> marketPartList = new ArrayList<MarketPart>();
		if (vo.getIsConsumed().equals("1")) {
			marketPartList = marketPartService.getShipPartByDate(vo);
		} else {
			marketPartList = marketPartService.getQuantityByDate(vo);
		}
		Long maxCount = 0L;
		Long maxRepairPercent = 0L;
		Map<String, Long> marketPartMap = new HashMap<String, Long>();
		for (MarketPart marketPart : matrixDataList) {
			marketPartMap.put(marketPart.getCommonName() + "&" + marketPart.getRepairDate(), marketPart.getRepairCount());
		}
		
		List<TimeMatrixResultVo> list = new ArrayList<TimeMatrixResultVo>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Integer countMonth = (sdf.parse(vo.getEndTime()).getYear() * 12 + sdf.parse(vo.getEndTime()).getMonth()) - (sdf.parse(vo.getStartTime()).getYear() * 12 + sdf.parse(vo.getStartTime()).getMonth());
		if (list.size() < countMonth) {
			Calendar tempCalendar = Calendar.getInstance();
			tempCalendar.setTime(new SimpleDateFormat("yyyy-MM").parse(vo.getEndTime()));
			tempCalendar.add(Calendar.MONTH, 1);
			Date tempStartDate = new SimpleDateFormat("yyyy-MM").parse(vo.getStartTime());//定义起始日期
			Date tempSendDate = new SimpleDateFormat("yyyy-MM").parse(new SimpleDateFormat("yyyy-MM").format(tempCalendar.getTime()));//定义结束日期
			tempCalendar.setTime(tempStartDate);
			while(tempCalendar.getTime().before(tempSendDate)) {
				Boolean b = false;
				String month = sdf.format(tempCalendar.getTime());
				for (MarketPart marketPart : marketPartList) {
					if (month.equals(marketPart.getCommonName())) {
						b = true;
					}
				}
				if (!b) {
					MarketPart tempPart = new MarketPart();
					tempPart.setCommonName(month);
					tempPart.setQuantity(0L);
					marketPartList.add(tempPart);
				}
				tempCalendar.add(Calendar.MONTH, 1);
			}
		}
		SortUtils sortUtils = new SortUtils(vo);
		Collections.sort(marketPartList,  sortUtils);
		for (MarketPart marketPart : marketPartList) {
			List<Long> repairCountList = new ArrayList<Long>();
			List<Long> repairPercentList = new ArrayList<Long>();
			TimeMatrixResultVo tmpVo = new TimeMatrixResultVo();
			
			tmpVo.setBaseMonth(marketPart.getCommonName());
			tmpVo.setBaseCount(marketPart.getQuantity());
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new SimpleDateFormat("yyyy-MM").parse(vo.getEndTime()));
			calendar.add(Calendar.MONTH, 1);
			Date startDate = new SimpleDateFormat("yyyy-MM").parse(vo.getStartTime());//定义起始日期
			Date endDate = new SimpleDateFormat("yyyy-MM").parse(new SimpleDateFormat("yyyy-MM").format(calendar.getTime()));//定义结束日期
			calendar.setTime(startDate);

			while(calendar.getTime().before(endDate)) {
				String month = sdf.format(calendar.getTime());
				
				if (sdf.parse(marketPart.getCommonName()).getTime() > calendar.getTime().getTime()) {
					calendar.add(Calendar.MONTH, 1);
					continue;
				}
				Long mapValue = marketPartMap.get(marketPart.getCommonName() + "&" + month);
				if (mapValue == null) {
					mapValue = 0L;
				}
				if (mapValue > maxCount) {
					maxCount = mapValue;
				}
				repairCountList.add(mapValue);
				if (marketPart.getQuantity() != null && marketPart.getQuantity() > 0) {
					Long d = Math.round(mapValue * 1000000.0 / marketPart.getQuantity());
					if (d > maxRepairPercent) {
						maxRepairPercent = d;
					}
					repairPercentList.add(d);
				} else {
					repairPercentList.add(0L);
				}
				calendar.add(Calendar.MONTH, 1);
			}
			tmpVo.setReCount(repairCountList);
			tmpVo.setRepairPercent(repairPercentList);
			list.add(tmpVo);
		}
		if (vo.getMaxValue() != null) {
			model.addAttribute("rangeList", StatisUtils.getArrageIntList(maxCount.intValue(), vo.getMaxValue().intValue()));
			model.addAttribute("rangePercentList", StatisUtils.getArrageIntList(maxRepairPercent.intValue(), vo.getMaxValue().intValue()));
		} else {
			model.addAttribute("rangeList", StatisUtils.getArrageIntList(maxCount.intValue(), null));
			model.addAttribute("rangePercentList", StatisUtils.getArrageIntList(maxRepairPercent.intValue(), null));
		}
		model.addAttribute("vo", vo);
		return list;
	}
	
	/**
	 * 将三角阵数据按时间维度分割
	 * @param model
	 * @param vo
	 * @param list
	 * @return
	 */
	public static List<TimeMatrixResultVo> getDefectMatrixData(MarketPartServiceI marketPartService, Model model, MarketPart vo) throws Exception {
		List<TimeMatrixResultVo> list = getMatrixData(marketPartService, model, vo);
		List<TimeMatrixResultVo> resultList = new ArrayList<TimeMatrixResultVo>();
		if (vo.getMatrixType().equals("正三角阵")) {
			String[] column = null;
			Set<String> betweenMon = WebUtil.getBetweenMonth(list.get(0).getBaseMonth(), vo.getEndTime());
			column = StatisUtils.getArryStr(1,betweenMon.size());
			if (ConstantInterface.DATE_FORMAT_YEAR.equals(vo.getSelectDate())) {
				column = StatisUtils.getArryStr(1, WebUtil.getBetweenYear(betweenMon).size());
				resultList = ParseDataUtil.parseToYearByMarketPart(list, model);
			} else if (ConstantInterface.DATE_FORMAT_QUARTER.equals(vo.getSelectDate())) {
				column = StatisUtils.getArryStr(1,WebUtil.getBetweenQuarter(betweenMon).size());
				resultList = ParseDataUtil.parseToQuarterByMarketPart(list, model);
			} else{
				resultList = list;
			}
			model.addAttribute("column", column);
		} else {
			Set<String> column = WebUtil.getBetweenMonth(vo.getStartTime(), vo.getEndTime());
			if (ConstantInterface.DATE_FORMAT_YEAR.equals(vo.getSelectDate())) {
				resultList = ParseDataUtil.parseToYearByMarketPart(list, model);
				column = WebUtil.getBetweenYear(column);
			} else if (ConstantInterface.DATE_FORMAT_QUARTER.equals(vo.getSelectDate())) {
				resultList = ParseDataUtil.parseToQuarterByMarketPart(list, model);
				column = WebUtil.getBetweenQuarter(column);
			} else{
				for(int i=0;i<list.size();i++){
					list.get(i).setPreDiff(i);
				}
				resultList = list;
			}
			model.addAttribute("column", column);
		}
		return resultList;
	}
	
	/**
	 * 将数据按时间分割（年/月）
	 * @param vo
	 * @param marketPartList
	 * @param type
	 * @return
	 * */
	public static List<MarketPart> splitDataByDate(MarketPart vo, List<MarketPart> marketPartList, String type) throws Exception {
		List<MarketPart> partList = new ArrayList<MarketPart>();
		if (ConstantInterface.DATE_FORMAT_YEAR.equals(vo.getSelectDate())) {
			Integer year = Integer.parseInt(vo.getQueryMonth().substring(0, 4));
			Integer month = Integer.parseInt(vo.getQueryMonth().substring(5));
			Integer startYear = year - vo.getxCount() - 1;
			for(Integer i = startYear; i < year; i++) {
				Long count = 0L;
				for (MarketPart marketPart1 : marketPartList) {
					Integer year1 = Integer.parseInt(marketPart1.getCommonName().substring(0, 4));
					Integer month1 = Integer.parseInt(marketPart1.getCommonName().substring(5));
					if (type.equals("不良")) {
						if (year1.equals(i) && month1 > month) {
							count += marketPart1.getRepairCount() == null ? 0 : marketPart1.getRepairCount();
						} else if (year1.equals(i + 1) && month1 <= month) {
							count += marketPart1.getRepairCount() == null ? 0 : marketPart1.getRepairCount();
						}
					} else if (type.equals("发货")) {
						if (year1.equals(i) && month1 > month) {
							count += marketPart1.getQuantity() == null ? 0 : marketPart1.getQuantity();
						} else if (year1.equals(i + 1) && month1 <= month) {
							count += marketPart1.getQuantity() == null ? 0 : marketPart1.getQuantity();
						}
					}
				}
				MarketPart repairPart = new MarketPart();
				repairPart.setCommonName(i + 1 + "");
				if (type.equals("不良")) {
					repairPart.setRepairCount(count);
				} else if (type.equals("发货")) {
					repairPart.setQuantity(count);
				}
				partList.add(repairPart);
			}
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new SimpleDateFormat("yyyy-MM").parse(WebUtil.rebackMonths(vo.getQueryMonth(), - vo.getxCount())));
			Date endDate = new SimpleDateFormat("yyyy-MM").parse(WebUtil.rebackMonths(vo.getQueryMonth(), 1));//定义结束日期
			while(calendar.getTime().before(endDate)) {
				String month = sdf.format(calendar.getTime());
				Boolean b = false;
				for (MarketPart mp : marketPartList) {
					if (mp.getCommonName().equals(month)) {
						partList.add(mp);
						b = true;
					}
				}
				if (!b) {
					MarketPart mp = new MarketPart();
					mp.setCommonName(month);
					mp.setRepairCount(0L);
					mp.setShipCount(0L);
					mp.setQuantity(0L);
					partList.add(mp);
				}
				calendar.add(Calendar.MONTH, 1);
			}
		}
		return partList;
	}
	
	/**
	 * 将参数转化成List
	 * */
	public static List<String> assemblyVo(MarketPart vo) {
		String supplierNumberList = "";
		String regionList = "";
		String faultTypeCodeList = "";
		String faultReasonCodeList = "";
		String partNumberList = "";
		String partTypeList = "";
		String mesPartNumberList = "";
		List<String> returnList = new ArrayList<String>();
		if (StringUtils.isNotEmpty(vo.getSupplierNumber())) { //供应商
			supplierNumberList = TmStringUtils.getSupStringByComma(vo.getSupplierNumber());
		}
		if (StringUtils.isNotEmpty(vo.getRegionListTxt())) {
			regionList = TmStringUtils.getSupStringBySemicolon(vo.getRegionListTxt());
		}
		if (StringUtils.isNotEmpty(vo.getFaultTypeCode())) {
			faultTypeCodeList = TmStringUtils.getSupStringByComma(vo.getFaultTypeCode());
		}
		if (StringUtils.isNotEmpty(vo.getFaultReasonCode())) {
			faultReasonCodeList = TmStringUtils.getSupStringByComma(vo.getFaultReasonCode());
		}
		if (StringUtils.isNotEmpty(vo.getPartNumber())) {
			String[] tempPartNumberList = vo.getPartNumber().split(",");
			if (ConstantInterface.PART_NO_HAS_VERSION.equals(vo.getHasVersion())) { //不带版本
				List<String> partNumbers = new ArrayList<String>();
				for (String s : tempPartNumberList) {
					s = s.substring(0, s.length() - 1);
					partNumbers.add(s);
				}
				tempPartNumberList = partNumbers.toArray(new String[partNumbers.size()]);
			}
			partNumberList = TmStringUtils.getStringByArray(tempPartNumberList);
		}
		if (StringUtils.isNotEmpty(vo.getPartTypesListTxt())) {
			partTypeList = TmStringUtils.getSupStringBySemicolon(vo.getPartTypesListTxt());
		}
		if (StringUtils.isNotEmpty(vo.getMesPartNumber())) {
			String[] tempMesPartNumberList = vo.getMesPartNumber().split(",");
			if (ConstantInterface.PART_NO_HAS_VERSION.equals(vo.getHasVersion())) { //不带版本
				List<String> mesPartNumbers = new ArrayList<String>();
				for (String s : tempMesPartNumberList) {
					s = s.substring(0, s.length() - 1);
					mesPartNumbers.add(s);
				}
				tempMesPartNumberList = mesPartNumbers.toArray(new String[mesPartNumbers.size()]);
			}
			mesPartNumberList = TmStringUtils.getStringByArray(tempMesPartNumberList);
		}
		returnList.add(supplierNumberList);
		returnList.add(regionList);
		returnList.add(faultTypeCodeList);
		returnList.add(faultReasonCodeList);
		returnList.add(partNumberList);
		returnList.add(partTypeList);
		returnList.add(mesPartNumberList);
		return returnList;
	}
	
	/**
	 * 计算上下控制线
	 * @param vo
	 * @param marketPartList
	 * @return
	 */
	public static List<MarketPart> getCtrLine(MarketPartServiceI marketPartService, MarketPart vo, List<MarketPart> marketPartList) throws Exception {
		Double p = getP(marketPartService, vo);
		if (p == 0) {
			for (MarketPart marketPart : marketPartList) {
				marketPart.setP(0d);
				marketPart.setQ(0d);
				marketPart.setTopCtrLineValue(0d);
				marketPart.setLowerCtrLineValue(0d);
			}
		} else {
			for (MarketPart marketPart : marketPartList) {
				Double q = 3 * Math.sqrt(p * (1 - p));
				if (marketPart.getQuantity() != null && marketPart.getQuantity() > 0) {
					q = q / marketPart.getQuantity().doubleValue();
				}
				marketPart.setP(p);
				marketPart.setQ(q);
				marketPart.setTopCtrLineValue(p + q);
				marketPart.setLowerCtrLineValue(p - q < 0 ? 0 : p - q);
			}
		}
		return marketPartList;
	}
	
	/**
	 * 计算上下控制线的P值
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static Double getP(MarketPartServiceI marketPartService, MarketPart vo) throws Exception {
		List<MarketPart> defectAll = new ArrayList<MarketPart>();
		List<MarketPart> shipAll = new ArrayList<MarketPart>();
		Double p = 0d;
		vo.setStartTime(WebUtil.rebackMonths(vo.getQueryMonth(), -29));
		vo.setEndTime(vo.getQueryMonth());
		defectAll = marketPartService.getDefectByAll(vo);
		if (StringUtils.isNotEmpty(vo.getIsConsumed())) {
			if (vo.getIsConsumed().equals("1")) {
				shipAll = marketPartService.getShipCount(vo);
			} else {
				shipAll = marketPartService.getQuantity(vo);
			}
			if (defectAll != null && defectAll.size() > 0 && shipAll != null && shipAll.size() > 0 && defectAll.get(0).getRepairCount() > 0 && shipAll.get(0).getQuantity() > 0) {
				p = defectAll.get(0).getRepairCount().doubleValue()/shipAll.get(0).getQuantity().doubleValue();
			}
			vo.setStartTime(vo.getQueryMonth());
		}
		return p;
	}
	
	/**
	 * 转换乱码而用
	 * 
	 * @param value
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decode(String value) throws UnsupportedEncodingException {
		if (StringUtils.isNotBlank(value)) {
			if(value.equals(new String(value.getBytes("iso8859-1"), "iso8859-1")))
			{
				value = new String(value.getBytes("ISO-8859-1"),"utf-8");
			}			
		}
		return value;
	}
	
	/**
	 * 根据物料编号获取对应的新物料号及旧物料号加入查询条件中
	 * @param partNumbers
	 * @param marketPartService
	 * @return
	 */
	private static String getNewAndOldPart(String partNumbers, MarketPartServiceI marketPartService) {
		String[] partNumberArr = partNumbers.split(",");
		List<String> newPartList = new ArrayList<String>();
		List<String> oldPartList = new ArrayList<String>();
		StringBuilder newPart = new StringBuilder();
		StringBuilder oldPart = new StringBuilder();
		MarketPart vo = new MarketPart();
		for (String partNumber : partNumberArr) {
			if ("1".equals(partNumber.substring(0, 1))) {
				newPart.append("," + partNumber);
			} else {
				oldPart.append("," + partNumber);
			}
		}
		if (StringUtils.isNotEmpty(newPart.toString())) {
			String[] newPartArr = newPart.toString().split(",");
			newPartArr[0].replace(",", "");
			vo.setPartNumbers(newPartArr);
			oldPartList = marketPartService.getOldPartNumber(vo);
		}
		if (StringUtils.isNotEmpty(oldPart.toString())) {
			String[] oldPartArr = oldPart.toString().split(",");
			oldPartArr[0].replace(",", "");
			vo.setPartNumbers(oldPartArr);
			newPartList = marketPartService.getNewPartNumber(vo);
		}
		for (String str : oldPartList) {
			partNumbers += "," + str;
		}
		for (String str : newPartList) {
			partNumbers += "," + str;
		}
		return partNumbers;
	}
}