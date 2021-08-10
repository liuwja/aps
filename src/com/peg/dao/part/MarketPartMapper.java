package com.peg.dao.part;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.part.MarketPart;

public interface MarketPartMapper {
	
	/**
	 * 获取供应商入库数
	 * */
	List<MarketPart> getQuantityBySupplier(MarketPart marketPart);
	
	/**
	 * 获取零部件入库数
	 * */
	List<MarketPart> getQuantityByPart(MarketPart marketPart);
	
	/**
	 * 日期入库数
	 * */
	List<MarketPart> getQuantityByDate(MarketPart marketPart);
	
	/**
	 * 物料类型入库数
	 */
	List<MarketPart> getQuantityByPartType(MarketPart marketPart);
	
	/**
	 * 总入库数
	 * */
	List<MarketPart> getQuantity(MarketPart marketPart);
	
	/**
	 * 获取供应商不良数
	 * */
	List<MarketPart> getDefectBySupplier(MarketPart marketPart);
	
	/**
	 * 获取零部件不良数
	 * */
	List<MarketPart> getDefectByPart(MarketPart marketPart);
	
	/**
	 * 获取区域不良数
	 * */
	List<MarketPart> getDefectByRegion(MarketPart marketPart);
	
	/**
	 * 获取故障大类不良数
	 * */
	List<MarketPart> getDefectByFaultType(MarketPart marketPart);
	
	/**
	 * 获取故障小类不良数
	 * */
	List<MarketPart> getDefectByFaultReason(MarketPart marketPart);
	
	/**
	 * 获取物料级别不良数
	 * */
	List<MarketPart> getDefectByPartLevel(MarketPart marketPart);
	
	/**
	 * 获取物料类型不良数
	 */
	List<MarketPart> getDefectByPartType(MarketPart marketPart);
	
	/**
	 * 获取总不良数
	 * */
	List<MarketPart> getDefect(MarketPart marketPart);
	
	/**
	 * 获取不良明细数据
	 * */
	List<MarketPart> getDefectDataPage(@Param("page")PageParameter page,@Param("marketPart")MarketPart marketPart,
			@Param("supplierNumberList")String supplierNumberList,@Param("regionList")String regionList,
			@Param("faultTypeCodeList")String faultTypeCodeList,@Param("faultReasonCodeList")String faultReasonCodeList,
			@Param("partNumberList")String partNumberList,@Param("partTypeList")String partTypeList,
			@Param("mesPartNumberList")String mesPartNumberList);
	
	List<MarketPart> getDefectData(@Param("marketPart")MarketPart marketPart,
			@Param("supplierNumberList")String supplierNumberList,@Param("regionList")String regionList,
			@Param("faultTypeCodeList")String faultTypeCodeList,@Param("faultReasonCodeList")String faultReasonCodeList,
			@Param("partNumberList")String partNumberList,@Param("partTypeList")String partTypeList,
			@Param("mesPartNumberList")String mesPartNumberList);
	
	/**
	 * 供应商发货数
	 * */
	List<MarketPart> getShipCountBySupplier(MarketPart marketPart);
	
	/**
	 * 零部件发货数
	 * */
	List<MarketPart> getShipCountByPart(MarketPart marketPart);
	
	/**
	 * 区域发货数
	 * */
	List<MarketPart> getShipCountByRegion(MarketPart marketPart);
	
	/**
	 * 故障大类，原因发货数
	 * */
	List<MarketPart> getShipCount(MarketPart marketPart);
	
	/**
	 * 三角阵数据
	 * */
	List<MarketPart> getMatrixData(MarketPart marketPart);
	
	/**
	 * 发货数按日期汇总
	 * */
	List<MarketPart> getShipPartByDate(MarketPart marketPart);
	
	/**
	 * 趋势图
	 * */
	List<MarketPart> getDefectTrend(MarketPart marketPart);
	
	/**
	 * 物料级别发货数
	 * */
	List<MarketPart> getShipCountByPartLevel(MarketPart marketPart);
	
	/**
	 * 物料类型发货数
	 */
	List<MarketPart> getShipCountByPartType(MarketPart marketPart);
	
	/**
	 * 趋势发货数
	 * */
	List<MarketPart> getShipTrend(MarketPart marketPart);
	
	/**
	 * 趋势发货数-单月维修
	 * */
	List<MarketPart> getShipTrendByMonth(MarketPart marketPart);
	
	/**
	 * 明细-来料入库
	 * */
	List<MarketPart> getIncomingPartDataPage(@Param("page")PageParameter page, @Param("marketPart")MarketPart marketPart, @Param("supplierNumberList")String supplierNumberList, @Param("partNumberList")String partNumberList, @Param("partTypeList")String partTypeList);
	
	List<MarketPart> getIncomingPartData(@Param("marketPart")MarketPart marketPart, @Param("supplierNumberList")String supplierNumberList, @Param("partNumberList")String partNumberList, @Param("partTypeList")String partTypeList);
	
	/**
	 * 明细-扫码入库
	 */
	List<MarketPart> getSerialPartDataPage(@Param("page")PageParameter page, @Param("marketPart")MarketPart marketPart, @Param("supplierNumberList")String supplierNumberList, @Param("partNumberList")String partNumberList, @Param("partTypeList")String partTypeList, @Param("regionList")String regionList);
	
	List<MarketPart> getSerialPartData(@Param("marketPart")MarketPart marketPart, @Param("supplierNumberList")String supplierNumberList, @Param("partNumberList")String partNumberList, @Param("partTypeList")String partTypeList, @Param("regionList")String regionList);
	
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
	 * 根据新物料代码获取旧物料代码
	 * @param marketPart
	 * @return
	 */
	List<String> getOldPartNumber(MarketPart marketPart);
	
	/**
	 * 根据旧物料代码获取新物料代码
	 * @param marketPart
	 * @return
	 */
	List<String> getNewPartNumber(MarketPart marketPart);
	
	/**
	 * 删除发货物料记录
	 * @param marketPart
	 * @return
	 */
	int deleteMarketShipPart(MarketPart marketPart);
	
	/**
	 * 插入发货物料数据
	 * @param marketPart
	 * @return
	 */
	int shipDataRecord(MarketPart marketPart);
	/**
	 * 汇总发货物流数据(不良趋势图)
	 */
	int shipDataAll(MarketPart marketPart);
	
	/**
	 * 更新发货物料供应商
	 * @param marketPart
	 * @return
	 */
	int updateSupplierByShip(MarketPart marketPart);
	
	/**
	 * 删除物料维修记录
	 * @param marketPart
	 * @return
	 */
	int deleteMarketRepairPart(MarketPart marketPart);
	
	/**
	 * 插入物料维修数据
	 * @param marketPart
	 * @return
	 */
	int repairDataRecord(MarketPart marketPart);
	
	/**
	 * 更新物料维修记录供应商代码
	 * @param marketPart
	 * @return
	 */
	int updateSupplierCodeByRepair(MarketPart marketPart);
	
	/**
	 * 更新物料维修记录供应商
	 * @param marketPart
	 * @return
	 */
	int updateSupplierByRepair(MarketPart marketPart);
	
	/**
	 * 市场不良趋势图（新）维修量
	 * @param marketPart
	 * @return
	 */
	List<MarketPart> getDefectTrendNew(MarketPart marketPart);
	
	/**
	 * 市场不良趋势图（新）入库量
	 * @param marketPart
	 * @return
	 */
	List<MarketPart> getDownLineTrend(MarketPart marketPart);

	/**
	 * 市场不良趋势图(新) 发货数
	 * @param marketPart
	 * @return
	 */
	List<MarketPart> getShip(MarketPart marketPart);

	/**
	 * 获取维修数
	 * @param marketPart
	 * @return
	 */
	List<MarketPart> getRepair(MarketPart marketPart);

	/**
	 * 更新物料维修记录供应商(新)
	 * @param marketPart
	 * @return
	 */
	Integer updateSupplierNew(MarketPart marketPart);
	
	/**
	 * 更新市场物料维修汇总
	 * */
	Integer updateMarketRepairPartSum(MarketPart marketPart);
	
	/**
	 * 删除市场物料维修汇总
	 * @param marketPart
	 * @return
	 */
	Integer deleteMarketRepairPartSum(MarketPart marketPart);
}
