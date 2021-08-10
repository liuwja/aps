package com.peg.service.part.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.peg.dao.interceptor.PageParameter;
import com.peg.dao.part.MarketPartMapper;
import com.peg.model.part.MarketPart;
import com.peg.service.part.MarketPartServiceI;
import com.peg.web.util.ConstantInterface;
import com.peg.web.util.MarketPartUtil;
import com.peg.web.util.WebUtil;

@Service("MarketPartService")
public class MarketPartServiceImpl implements MarketPartServiceI {

	@Autowired
	private MarketPartMapper marketPartMapper;

	/**
	 * 获取不良数
	 * */
	@Override
	public List<MarketPart> getDefectPartList(MarketPart marketPart) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		if (ConstantInterface.DATE_FORMAT_YEAR.equals(marketPart.getSelectDate())) {
			marketPart.setStartTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -11)); //设置时间为T1
			marketPart.setEndTime(marketPart.getQueryMonth());
			marketPart.setTitleContext(MarketPartUtil.getChartTitle(marketPart));
		} else {
			marketPart.setStartTime(marketPart.getQueryMonth()); //设置时间为T3
			marketPart.setEndTime(marketPart.getQueryMonth());
			marketPart.setTitleContext(MarketPartUtil.getChartTitle(marketPart));
		}
		subject.getSession().setAttribute(ConstantInterface.PART_DEFECT_SESSION_VO, marketPart.clone());
		List<MarketPart> marketDefectPartList = new ArrayList<MarketPart>(); //不良数
		switch(marketPart.getTitle()) {
			case ConstantInterface.PART_DEFECT_HISTOGRAM_SUPPLIER:
				marketDefectPartList = marketPartMapper.getDefectBySupplier(marketPart);
				break;
			case ConstantInterface.PART_DEFECT_HISTOGRAM_PART:
				marketDefectPartList = marketPartMapper.getDefectByPart(marketPart);
				break;
			case ConstantInterface.PART_DEFECT_HISTOGRAM_REGION:
				marketDefectPartList = marketPartMapper.getDefectByRegion(marketPart);
				break;
			case ConstantInterface.PART_DEFECT_HISTOGRAM_FAULT_TYPE:
				marketDefectPartList = marketPartMapper.getDefectByFaultType(marketPart);
				break;
			case ConstantInterface.PART_DEFECT_HISTOGRAM_FAULT_REASON:
				marketDefectPartList = marketPartMapper.getDefectByFaultReason(marketPart);
				break;
			case ConstantInterface.PART_DEFECT_HISTOGRAM_PART_LEVEL:
				marketDefectPartList = marketPartMapper.getDefectByPartLevel(marketPart);
				break;
			case ConstantInterface.PART_DEFECT_HISTOGRAM_PART_TYPE:
				marketDefectPartList = marketPartMapper.getDefectByPartType(marketPart);
				break;
		}
		return marketDefectPartList;
	}
	
	@Override
	public List<MarketPart> getTotalityShipCountList(MarketPart marketPart) throws Exception {
		List<MarketPart> countPartList = new ArrayList<MarketPart>();
		if (StringUtils.isEmpty(marketPart.getIsConsumed())) {
			return countPartList;
		}
		if (ConstantInterface.DATE_FORMAT_YEAR.equals(marketPart.getSelectDate())) {
			marketPart.setStartTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -13)); //设置时间为T2
			marketPart.setEndTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -2));
		} else {
			marketPart.setStartTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -2));
			marketPart.setEndTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -2));
		}
		if ("1".equals(marketPart.getIsConsumed())) {
			switch(marketPart.getTitle()) {
				case ConstantInterface.PART_DEFECT_HISTOGRAM_SUPPLIER:
					countPartList = marketPartMapper.getShipCountBySupplier(marketPart);
					break;
				case ConstantInterface.PART_DEFECT_HISTOGRAM_PART:
					countPartList = marketPartMapper.getShipCountByPart(marketPart);
					break;
				case ConstantInterface.PART_DEFECT_HISTOGRAM_REGION:
					countPartList = marketPartMapper.getShipCountByRegion(marketPart);
					break;
				case ConstantInterface.PART_DEFECT_HISTOGRAM_PART_LEVEL:
					countPartList = marketPartMapper.getShipCountByPartLevel(marketPart);
					break;
				case ConstantInterface.PART_DEFECT_HISTOGRAM_PART_TYPE:
					countPartList = marketPartMapper.getShipCountByPartType(marketPart);
					break;
				case ConstantInterface.PART_DEFECT_HISTOGRAM_FAULT_TYPE:
				case ConstantInterface.PART_DEFECT_HISTOGRAM_FAULT_REASON:
					countPartList = marketPartMapper.getShipCount(marketPart);
					break;
			}
		} else if ("2".equals(marketPart.getIsConsumed())) {
			switch(marketPart.getTitle()) {
				case ConstantInterface.PART_DEFECT_HISTOGRAM_SUPPLIER:
					countPartList = marketPartMapper.getQuantityBySupplier(marketPart);
					break;
				case ConstantInterface.PART_DEFECT_HISTOGRAM_PART:
					countPartList = marketPartMapper.getQuantityByPart(marketPart);
					break;
				case ConstantInterface.PART_DEFECT_HISTOGRAM_PART_TYPE:
					countPartList = marketPartMapper.getQuantityByPartType(marketPart);
					break;
				default:
					countPartList = marketPartMapper.getQuantity(marketPart);
					break;
			}
		}
		return countPartList;
	}
	
	@Override
	public List<MarketPart> getQuantityByDate(MarketPart marketPart) {
		return marketPartMapper.getShip(marketPart);		//8084 物料-市场不良三角阵发货数完善
	}
	
	@Override
	public List<MarketPart> getQuantity(MarketPart marketPart) {
		return marketPartMapper.getQuantity(marketPart);
	}
	
	@Override
	public List<MarketPart> getDefectTrendShipData(MarketPart marketPart) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		List<MarketPart> marketCountPartList = new ArrayList<MarketPart>(); //发货数或入库数
		if (ConstantInterface.DATE_FORMAT_YEAR.equals(marketPart.getSelectDate())) {
			marketPart.setStartTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -((marketPart.getxCount() * 12) - 1)));
			marketPart.setEndTime(marketPart.getQueryMonth());
		} else {
			marketPart.setStartTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -(marketPart.getxCount())));
			marketPart.setEndTime(marketPart.getQueryMonth());
		}
		subject.getSession().setAttribute(ConstantInterface.PART_DEFECT_SESSION_VO, marketPart.clone());
		if (StringUtils.isNotEmpty(marketPart.getIsConsumed())) {
			if (marketPart.getIsConsumed().equals("1")) {
				marketCountPartList = getShipTrendByPart(marketPart);
			} else {
				marketCountPartList = marketPartMapper.getQuantityByDate(marketPart);
			}
		}
		marketPart.setTitleContext(MarketPartUtil.getChartTitle(marketPart));
		return marketCountPartList;
	}
	
	/**
	 * 获取总不良数
	 * */
	@Override
	public List<MarketPart> getDefectByAll(MarketPart marketPart) {
		return marketPartMapper.getDefect(marketPart);
	}
	
	@Override
	public MarketPart getDefectDetailPart(PageParameter page, Model model, MarketPart marketPart) throws Exception {
		marketPart.setCommonName(MarketPartUtil.decode(marketPart.getCommonName()));
		marketPart.setCommonNumber(MarketPartUtil.decode(marketPart.getCommonNumber()));
		Subject subject = SecurityUtils.getSubject();
		MarketPart vo = (MarketPart)subject.getSession().getAttribute(ConstantInterface.PART_DEFECT_SESSION_VO);
		switch(marketPart.getTitle()) {
			case ConstantInterface.PART_DEFECT_HISTOGRAM_SUPPLIER:
				vo.setSupplierListTxt(marketPart.getCommonName());
				vo.setSupplierNumber(marketPart.getCommonNumber());
				vo.setSupplierId(marketPartMapper.getSupplierId(vo).getSupplierId() + "," + vo.getSupplierNumber() + "," + vo.getSupplierListTxt() + ";");
				model.addAttribute("data", vo.getSupplierId());
				break;
			case ConstantInterface.PART_DEFECT_HISTOGRAM_PART:
				vo.setPartNumber(marketPart.getCommonNumber());
				MarketPart temp = marketPartMapper.getPartId(vo);
				vo.setPartNumber(temp.getPartNumber());
				vo.setPartName(temp.getPartName());
				vo.setPartDescription(temp.getPartName());
				vo.setPartId(temp.getPartId() + "," + temp.getPartNumber() + "," + temp.getPartName() + ";");
				model.addAttribute("data", vo.getPartId());
				break;
			case ConstantInterface.PART_DEFECT_HISTOGRAM_REGION:
				vo.setRegionListTxt(marketPart.getCommonName());
				break;
			case ConstantInterface.PART_DEFECT_HISTOGRAM_FAULT_TYPE:
				vo.setFaultTypeTxt(marketPart.getCommonName());
				vo.setFaultTypeCode(marketPart.getCommonNumber());
				vo.setFaultTypeID(marketPartMapper.getFaultTypeId(vo).getFaultTypeID());
				break;
			case ConstantInterface.PART_DEFECT_HISTOGRAM_FAULT_REASON:
				vo.setFaultReasonTxt(marketPart.getCommonName());
				vo.setFaultReasonCode(marketPart.getCommonNumber());
				vo.setFaultReasonID(marketPartMapper.getFaultReasonId(vo).getFaultReasonID());
				break;
			case ConstantInterface.PART_DEFECT_HISTOGRAM_PART_LEVEL:
				vo.setPartLevel(marketPart.getCommonName());
				break;
			case ConstantInterface.PART_DEFECT_TREND:
			case ConstantInterface.PART_DEFECT_TREND_MONTH:
			case ConstantInterface.PART_DEFECT_TREND_HUNDRED:
				if (ConstantInterface.DATE_FORMAT_MONTH.equals(vo.getSelectDate())) {
					vo.setStartTime(marketPart.getCommonName());
					vo.setEndTime(marketPart.getCommonName());
				} else {
					String date = marketPart.getCommonName() + "-" + vo.getQueryMonth().substring(5);
					vo.setStartTime(WebUtil.rebackMonths(date, -11)); //设置时间为T1
					vo.setEndTime(date);
				}
				break;
		}
		return vo;
	}
	
	/**
	 * 获取不良明细数据
	 * */
	@Override
	public List<MarketPart> getDefectDetailData(PageParameter page, MarketPart marketPart) {
		List<String> propList = MarketPartUtil.assemblyVo(marketPart);
		if (ConstantInterface.YES.equals(marketPart.getIsPage())) {
			List<MarketPart> defectDataPage = marketPartMapper.getDefectDataPage(page, marketPart, propList.get(0), propList.get(1), propList.get(2), propList.get(3), propList.get(4), propList.get(5), propList.get(6));
			return defectDataPage;
		} else {
			return marketPartMapper.getDefectData(marketPart, propList.get(0), propList.get(1), propList.get(2), propList.get(3), propList.get(4), propList.get(5), propList.get(6));
		}
	}
	
	/**
	 * 故障大类，原因发货数
	 * */
	@Override
	public List<MarketPart> getShipCount(MarketPart marketPart) {
		return marketPartMapper.getShipCount(marketPart);
	}
	
	/**
	 * 三角阵
	 * */
	@Override
	public List<MarketPart> getMatrixData(MarketPart marketPart) {
		return marketPartMapper.getMatrixData(marketPart);
	}
	
	/**
	 * 发货数按日期汇总
	 * */
	@Override
	public List<MarketPart> getShipPartByDate(MarketPart marketPart) {
		return marketPartMapper.getShip(marketPart);		//8084 物料-市场不良三角阵发货数完善
	}
	
	@Override
	public List<MarketPart> getDefectTrend(MarketPart marketPart) throws Exception {
		List<MarketPart> list = new ArrayList<MarketPart>();
		switch(marketPart.getTitle()) {
			case ConstantInterface.PART_DEFECT_TREND:
			case ConstantInterface.PART_DEFECT_TREND_MONTH:
				marketPart.setStartTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -marketPart.getxCount()));
				marketPart.setEndTime(marketPart.getQueryMonth());
				list = marketPartMapper.getDefectTrend(marketPart);
				break;
			case ConstantInterface.PART_DEFECT_TREND_HUNDRED:
				marketPart.setStartTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -marketPart.getxCount() - 11));
				marketPart.setEndTime(marketPart.getQueryMonth());
				List<MarketPart> tempList = marketPartMapper.getDefectTrend(marketPart);
				for (int i = 0; i <= (marketPart.getxCount() > tempList.size() ? marketPart.getxCount() : tempList.size()); i++) {
					Long sum = 0L;
					int j = 0;
					String month = WebUtil.rebackMonths(marketPart.getQueryMonth(), i - marketPart.getxCount());
					for (MarketPart tempVo : tempList) {
						sum += tempVo.getRepairCount();
						j++;
						if (month.equals(tempVo.getCommonName()) && j <= 12) {
							MarketPart vo = tempVo;
							vo.setRepairCount((long)Math.floor(sum/j));
							list.add(vo);
							break;
						}
					}
					if (j == 12) {
						tempList.remove(0);
					}
				}
				break;
		}
		return list;
	}
	
	@Override
	public List<MarketPart> getShipTrendByPart(MarketPart marketPart) throws Exception {
		List<MarketPart> list = new ArrayList<MarketPart>();
		switch(marketPart.getTitle()) {
			case ConstantInterface.PART_DEFECT_TREND:
				list = marketPartMapper.getShipTrend(marketPart);
				break;
			case ConstantInterface.PART_DEFECT_TREND_MONTH:
			case ConstantInterface.PART_DEFECT_TREND_HUNDRED:
				marketPart.setStartTime(WebUtil.rebackMonths(marketPart.getStartTime(), -13));
				marketPart.setEndTime(WebUtil.rebackMonths(marketPart.getEndTime(), -2));
				List<MarketPart> tempList = marketPartMapper.getShipTrend(marketPart);
				for (int i = 0; i <= (marketPart.getxCount() > tempList.size() ? marketPart.getxCount() : tempList.size()); i++) {
					Long sum = 0L;
					int j = 0;
					String month = WebUtil.rebackMonths(marketPart.getQueryMonth(), i - 2 - marketPart.getxCount());
					for (MarketPart tempVo : tempList) {
						sum += tempVo.getQuantity();
						j++;
						if (month.equals(tempVo.getCommonName())) {
							MarketPart vo = tempVo;
							vo.setQuantity((long)Math.floor(sum / j));
							list.add(vo);
							break;
						}
					}
					if (j == 12) {
						tempList.remove(0);
					}
				}
				for (MarketPart tempVo : list) {
					tempVo.setCommonName(WebUtil.rebackMonths(tempVo.getCommonName(), +2));
				}
				break;
		}
		return list;
	}
	
	/**
	 * 明细-来料入库
	 * */
	@Override
	public List<MarketPart> getIncomingPartData(PageParameter page, MarketPart marketPart) {
		StringBuilder supplierNumberList = new StringBuilder();
		StringBuilder partNumberList = new StringBuilder();
		StringBuilder partTypeList = new StringBuilder();
		String s = "";
		String p = "";
		String pt = "";
		if (marketPart.getSupplierNumbers() != null) {
			for(String supplierNumber : marketPart.getSupplierNumbers()) {
				supplierNumberList.append("'" + supplierNumber + "',");
			}
			s = supplierNumberList.toString();
			s = s.substring(0, s.length() - 1);
		}
		if (marketPart.getPartNumbers() != null) {
			for(String partNumber : marketPart.getPartNumbers()) {
				partNumberList.append("'" + partNumber + "',");
			}
			p = partNumberList.toString();
			p = p.substring(0, p.length() - 1);
		}
		if (marketPart.getPartTypes() != null) {
			for(String partType : marketPart.getPartTypes()) {
				partTypeList.append("'" + partType + "',");
			}
			pt = partTypeList.toString();
			pt = pt.substring(0, pt.length() - 1);
		}
		if (ConstantInterface.YES.equals(marketPart.getIsPage())) {
			return marketPartMapper.getIncomingPartDataPage(page, marketPart, s, p, pt);
		} else {
			return marketPartMapper.getIncomingPartData(marketPart, s, p, pt);
		}
	}
	
	@Override
	public List<String> getOldPartNumber(MarketPart marketPart) {
		return marketPartMapper.getOldPartNumber(marketPart);
	}
	
	@Override
	public List<String> getNewPartNumber(MarketPart marketPart) {
		return marketPartMapper.getNewPartNumber(marketPart);
	}
	
	@Override
	public MarketPart getSupplierId(MarketPart marketPart) {
		return marketPartMapper.getSupplierId(marketPart);
	}

	@Override
	public MarketPart getPartId(MarketPart marketPart) {
		return marketPartMapper.getPartId(marketPart);
	}

	@Override
	public MarketPart getFaultTypeId(MarketPart marketPart) {
		return marketPartMapper.getFaultTypeId(marketPart);
	}

	@Override
	public MarketPart getFaultReasonId(MarketPart marketPart) {
		return marketPartMapper.getFaultReasonId(marketPart);
	}
	
	@Override
	public int deleteMarketShipPart(MarketPart marketPart) {
		return marketPartMapper.deleteMarketShipPart(marketPart);
	}
	
	@Override
	public int deleteMarketRepairPart(MarketPart marketPart) {
		return marketPartMapper.deleteMarketRepairPart(marketPart);
	}
	
	@Override
	public List<Integer> shipDataRecord(MarketPart marketPart) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(marketPartMapper.deleteMarketShipPart(marketPart));
		list.add(marketPartMapper.shipDataRecord(marketPart));
		list.add(marketPartMapper.updateSupplierByShip(marketPart));
		list.add(marketPartMapper.shipDataAll(marketPart));
		return list;
	}
	
	@Override
	public List<Integer> repairDataRecord(MarketPart marketPart) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(marketPartMapper.deleteMarketRepairPart(marketPart));
		list.add(marketPartMapper.repairDataRecord(marketPart));
		list.add(marketPartMapper.updateSupplierNew(marketPart));
		list.add(marketPartMapper.deleteMarketRepairPartSum(marketPart));
		list.add(marketPartMapper.updateMarketRepairPartSum(marketPart));
		//list.add(marketPartMapper.updateSupplierByRepair(marketPart));弃用
		return list;
	}
	
	@Override
	public int updateSupplierByShip(MarketPart marketPart) {
		return marketPartMapper.updateSupplierByShip(marketPart);
	}
	
	@Override
	public int updateSupplierByRepair(MarketPart marketPart) {
		return marketPartMapper.updateSupplierByRepair(marketPart);
	}

	@Override
	public List<MarketPart> getSerialPartData(PageParameter page, MarketPart marketPart) {
		String supplierNumberList = "";
		String partNumberList = "";
		String partTypeList = "";
		String regionList = "";
		if (marketPart.getSupplierNumbers() != null) {
			for(String supplierNumber : marketPart.getSupplierNumbers()) {
				supplierNumberList += ("'" + supplierNumber + "',");
			}
			supplierNumberList = supplierNumberList.substring(0, supplierNumberList.length() - 1);
		}
		if (marketPart.getPartNumbers() != null) {
			for(String partNumber : marketPart.getPartNumbers()) {
				partNumberList += ("'" + partNumber + "',");
			}
			partNumberList = partNumberList.substring(0, partNumberList.length() - 1);
		}
		if (marketPart.getPartTypes() != null) {
			for(String partType : marketPart.getPartTypes()) {
				partTypeList += ("'" + partType + "',");
			}
			partTypeList = partTypeList.substring(0, partTypeList.length() - 1);
		}
		if (marketPart.getRegions() != null) {
			for (String region : marketPart.getRegions()) {
				regionList += ("'" + region + "',");
			}
			regionList = regionList.substring(0, regionList.length() - 1);
		}
		if (ConstantInterface.YES.equals(marketPart.getIsPage())) {
			return marketPartMapper.getSerialPartDataPage(page, marketPart, supplierNumberList, partNumberList, partTypeList, regionList);
		} else {
			return marketPartMapper.getSerialPartData(marketPart, supplierNumberList, partNumberList, partTypeList, regionList);
		}
	}
	
	public List<MarketPart> getDefectTrendNew(MarketPart marketPart) throws Exception {
		List<MarketPart> list = new ArrayList<MarketPart>();
		List<MarketPart> selectList = new ArrayList<MarketPart>();
		switch(marketPart.getTitle()) {
			case ConstantInterface.PRODUCTION_REPAIR_SINGLE_TREND:
				marketPart.setStartTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -(marketPart.getxCount() - 1)));
				marketPart.setEndTime(marketPart.getQueryMonth());
				list = marketPartMapper.getRepair(marketPart);
				break;
			case ConstantInterface.PRODUCTION_REPAIR_TOTAL_TREND:
				marketPart.setStartTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -(marketPart.getxCount() - 1) - 11));
				marketPart.setEndTime(marketPart.getQueryMonth());
				selectList = marketPartMapper.getRepair(marketPart);
				for(int i = 0; i < marketPart.getxCount(); i++) {
					String month = WebUtil.rebackMonths(marketPart.getQueryMonth(), -(marketPart.getxCount() - 1) + i);
					Long sum = 0L;
					int j = 0;
					for(MarketPart temp : selectList) {
						if(temp.getCommonName().compareTo(month) <= 0 && temp.getCommonName().compareTo(WebUtil.rebackMonths(month, -11)) >= 0) {
							sum += temp.getRepairCount();
							j++;
						}
					}
					MarketPart sumMarketPart = new MarketPart();
					sumMarketPart.setCommonName(month);
					sumMarketPart.setRepairCount(j != 0 ? sum / 12 : 0);
					list.add(sumMarketPart);
				}
				break;
			case ConstantInterface.PRODUCTION_PART_SINGLE_TREND:
				marketPart.setStartTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -(marketPart.getxCount() - 1)));
				marketPart.setEndTime(marketPart.getQueryMonth());
				list = marketPartMapper.getDefectTrendNew(marketPart);
				break;
			case ConstantInterface.PRODUCTION_PART_TOTAL_TREND:
				marketPart.setStartTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -(marketPart.getxCount() - 1) - 11));
				marketPart.setEndTime(marketPart.getQueryMonth());
				selectList = marketPartMapper.getDefectTrendNew(marketPart);
				for(int i = 0; i < marketPart.getxCount(); i++) {
					String month = WebUtil.rebackMonths(marketPart.getQueryMonth(), -(marketPart.getxCount() - 1) + i);
					Long sum = 0L;
					int j = 0;
					for(MarketPart temp : selectList) {
						if(temp.getCommonName().compareTo(month) <= 0 && temp.getCommonName().compareTo(WebUtil.rebackMonths(month, -11)) >= 0) {
							sum += temp.getRepairCount();
							j++;
						}
					}
					MarketPart sumMarketPart = new MarketPart();
					sumMarketPart.setCommonName(month);
					sumMarketPart.setRepairCount(j != 0 ? sum / 12 : 0);
					list.add(sumMarketPart);
				}
				break;
			case ConstantInterface.PRODUCTION_SUPPLIER_PART_SINGLE_TREND: //单月维修产品更换某物料某供应商维修数
				marketPart.setStartTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -(marketPart.getxCount() - 1)));
				marketPart.setEndTime(marketPart.getQueryMonth());
				list = marketPartMapper.getDefectTrendNew(marketPart);
				break;
			case ConstantInterface.PRODUCTION_SUPPLIER_PART_TOTAL_TREND: //累计12个月维修产品更换某物料某供应商平均维修数
				marketPart.setStartTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -(marketPart.getxCount() - 1) - 11));
				marketPart.setEndTime(marketPart.getQueryMonth());
				 selectList = marketPartMapper.getDefectTrendNew(marketPart);
				for(int i = 0; i < marketPart.getxCount(); i++) {
					String month = WebUtil.rebackMonths(marketPart.getQueryMonth(), -(marketPart.getxCount() - 1) + i);
					Long sum = 0L;
					int j = 0;
					for(MarketPart temp : selectList) {
						if(temp.getCommonName().compareTo(month) <= 0 && temp.getCommonName().compareTo(WebUtil.rebackMonths(month, -11)) >= 0) {
							sum += temp.getRepairCount();
							j++;
						}
					}
					MarketPart sumMarketPart = new MarketPart();
					sumMarketPart.setCommonName(month);
					sumMarketPart.setRepairCount(j != 0 ? sum / 12 : 0);
					list.add(sumMarketPart);
				}
				break;
		}
		return list;
	}
	
	public List<MarketPart> getDefectTrendDownLineData(MarketPart marketPart) throws Exception {
		List<MarketPart> list = new ArrayList<MarketPart>();
		switch(marketPart.getTitle()) {
			case ConstantInterface.PRODUCTION_REPAIR_SINGLE_TREND:
			case ConstantInterface.PRODUCTION_REPAIR_TOTAL_TREND:
			case ConstantInterface.PRODUCTION_PART_SINGLE_TREND:				
			case ConstantInterface.PRODUCTION_PART_TOTAL_TREND:
				//T_SHIP_TOTAL_RECORD
				marketPart.setStartTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -(marketPart.getxCount() - 1) - 14));
				marketPart.setEndTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -3));
				List<MarketPart> selectShipLists = marketPartMapper.getShip(marketPart);
				for(int i = 0; i < marketPart.getxCount(); i++) {
					String month = WebUtil.rebackMonths(marketPart.getQueryMonth(), -(marketPart.getxCount() - 1) + i);
					Long sum = 0L;
					int j = 0;
					for(MarketPart temp : selectShipLists) {
						if(temp.getCommonName().compareTo(WebUtil.rebackMonths(month,-3)) <= 0 && temp.getCommonName().compareTo(WebUtil.rebackMonths(month, -14)) >= 0) {
							sum += temp.getQuantity();
							j++;
						}
					}
					MarketPart sumMarketPart = new MarketPart();
					sumMarketPart.setCommonName(month);
					sumMarketPart.setQuantity(j != 0 ? sum / 12 : 0);
					list.add(sumMarketPart);
				}
				break;				
			case ConstantInterface.PRODUCTION_SUPPLIER_PART_SINGLE_TREND:
			case ConstantInterface.PRODUCTION_SUPPLIER_PART_TOTAL_TREND: //前推12 + 3个月包含这个物料供应商的累计平均入库数
				marketPart.setStartTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -(marketPart.getxCount() - 1) - 14));
				marketPart.setEndTime(WebUtil.rebackMonths(marketPart.getQueryMonth(), -3));
				List<MarketPart> selectList = marketPartMapper.getDownLineTrend(marketPart);
				for(int i = 0; i < marketPart.getxCount(); i++) {
					String month = WebUtil.rebackMonths(marketPart.getQueryMonth(), -(marketPart.getxCount() - 1) + i);
					Long sum = 0L;
					int j = 0;
					for(MarketPart temp : selectList) {
						//月份小于等于当前日期 且大于等于当前日期11个月的
						if(temp.getCommonName().compareTo(WebUtil.rebackMonths(month,-3)) <= 0 && temp.getCommonName().compareTo(WebUtil.rebackMonths(month, -14)) >= 0) {
							sum += temp.getQuantity();
							j++;
						}
					}
					MarketPart sumMarketPart = new MarketPart();
					sumMarketPart.setCommonName(month);
					sumMarketPart.setQuantity(j != 0 ? sum / 12 : 0);
					list.add(sumMarketPart);
				}
				break;
		}
		return list;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(WebUtil.rebackMonths("2018-03", -14));
	}
}