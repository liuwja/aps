package com.peg.service.part;

import java.util.List;

import org.springframework.ui.Model;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.part.MarketPart;

public interface MarketPartServiceI {
	
	/**
	 * 获取趋势不良入库数
	 * */
	List<MarketPart> getQuantityByDate(MarketPart marketPart);
	
	/**
	 * 获取趋势不良入库数
	 * */
	List<MarketPart> getQuantity(MarketPart marketPart);
	
	/**
	 * 获取趋势不良发货数或入库数
	 * */
	List<MarketPart> getDefectTrendShipData(MarketPart marketPart) throws Exception;
	
	/**
	 * 发货数按日期汇总
	 * */
	List<MarketPart> getShipPartByDate(MarketPart marketPart);
	
	/**
	 * 获取不良数
	 * */
	List<MarketPart> getDefectPartList(MarketPart marketPart) throws Exception;
	
	/**
	 * 获取总不良数
	 * */
	List<MarketPart> getDefectByAll(MarketPart marketPart);
	
	/**
	 * 获取不良明细物料条件
	 * */
	MarketPart getDefectDetailPart(PageParameter page, Model model, MarketPart marketPart) throws Exception;
	
	/**
	 * 获取不良明细数据
	 * */
	List<MarketPart> getDefectDetailData(PageParameter page, MarketPart marketPart);
	
	/**
	 * 获取发货数或者入库数
	 * */
	List<MarketPart> getTotalityShipCountList(MarketPart marketPart) throws Exception;
	
	
	/**
	 * 故障大类，原因发货数
	 * */
	List<MarketPart> getShipCount(MarketPart marketPart);
	
	/**
	 * 三角阵数据
	 * */
	List<MarketPart> getMatrixData(MarketPart marketPart);
	
	/**
	 * 物料与成品取值方式一致
	 * 市场不良趋势图维修数 = 当月物料维修数<br>
	 * 市场不良单月维修图维修数 = 当月物料维修数<br>
	 * 市场不良百台维修图维修数 = 累计12个月的平均物料维修数<br>
	 * */
	List<MarketPart> getDefectTrend(MarketPart marketPart) throws Exception;
	
	/**
	 * 物料<br>
	 * 市场不良趋势图发货数 = 当月扫码入库数（绑定关键件）<br>
	 * 市场不良单月维修图发货数 = 前推3个月后12个月的平均发货数<br>
	 * 市场不良百台维修图发货数 = 前推3个月后12个月的平均发货数<br>
	 * */
	List<MarketPart> getShipTrendByPart(MarketPart marketPart) throws Exception;
	
	/**
	 * 明细-来料入库
	 * */
	List<MarketPart> getIncomingPartData(PageParameter page, MarketPart marketPart);
	
	/**
	 * 明细-扫码入库
	 */
	List<MarketPart> getSerialPartData(PageParameter page, MarketPart marketPart);
	
	/**
	 * 获取供应商ID
	 * @param marketPart
	 * @return
	 */
	MarketPart getSupplierId(MarketPart marketPart);
	
	/**
	 * 获取物料ID
	 * @param marketPart
	 * @return
	 */
	MarketPart getPartId(MarketPart marketPart);
	
	/**
	 * 获取故障大类ID
	 * @param marketPart
	 * @return
	 */
	MarketPart getFaultTypeId(MarketPart marketPart);
	
	/**
	 * 获取故障小类ID
	 * @param marketPart
	 * @return
	 */
	MarketPart getFaultReasonId(MarketPart marketPart);
	
	/**
	 * 获取老物料代码
	 * @param marketPart
	 * @return
	 */
	List<String> getOldPartNumber(MarketPart marketPart);
	
	/**
	 * 获取新物料代码
	 * @param marketPart
	 * @return
	 */
	List<String> getNewPartNumber(MarketPart marketPart);
	
	int deleteMarketShipPart(MarketPart marketPart);
	
	int deleteMarketRepairPart(MarketPart marketPart);
	
	List<Integer> shipDataRecord(MarketPart marketPart);
	
	List<Integer> repairDataRecord(MarketPart marketPart);
	
	int updateSupplierByShip(MarketPart marketPart);
	
	int updateSupplierByRepair(MarketPart marketPart);
	
	/**
	 * 市场不良趋势图（新）
	 * @param marketPart
	 * @return
	 */
	List<MarketPart> getDefectTrendNew(MarketPart marketPart) throws Exception;
	
	/**
	 * 市场不良趋势图入库量
	 * @param marketPart
	 * @return
	 * @throws Exception
	 */
	List<MarketPart> getDefectTrendDownLineData(MarketPart marketPart) throws Exception;
}
