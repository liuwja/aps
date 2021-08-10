package com.peg.dao;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.CommonVo;
import com.peg.model.ProductionLine;

public interface CommonMapper {
	
	/**
	 * 区域排列用到的
	 * @param vo
	 * @return
	 */
	List<CommonVo> getReCountGroupByRegion(CommonVo vo);
	List<CommonVo> getShipCountGroupByRegion(CommonVo vo);
	
	/**
	 * 省份排列用到的
	 * @param vo
	 * @return
	 * */
	List<CommonVo> getReCountGroupByProvince(CommonVo vo);
	List<CommonVo> getShipCountGroupByProvince(CommonVo vo);
	
	/**
	 * 产品系列排列用到的
	 * @param vo
	 * @return
	 */
	List<CommonVo> getReCountGroupByProdFamily(CommonVo vo);
	
	/**
	 * 型号排列用到的
	 * @param vo
	 * @return
	 */
	List<CommonVo> getReCountGroupByPartType(CommonVo vo);
	/**
	 * 维修月份分组查询维修数据
	 * @param vo
	 * @return
	 */
	List<CommonVo> getReCountGroupByReMon(BaseSearch bs);
	
	List<CommonVo> getShipCountGroupByPartType(CommonVo vo);
	
	List<CommonVo> getShipCountGroupByProdFamily(CommonVo vo);
	
	int getShipCount(CommonVo vo);
	
	/**
	 * 产线排列用到的
	 * @param vo
	 * @return
	 */
	List<CommonVo> getReCountGroupByPline(CommonVo vo);
	List<CommonVo> getShipCountGroupByPline(CommonVo vo);
	
	/**
	 * 故障大类排列图
	 * @param vo
	 * @return
	 */
	List<CommonVo> getReCountGroupFaultType(CommonVo vo);
	
	/**
	 * 按照机型类别分组查询发货数
	 * @param vo
	 * @return
	 */
	List<CommonVo> getShipCountGroupByProdType(CommonVo vo);
	
	/**
	 * 按照机型类别分组查询维修数
	 * @param vo
	 * @return
	 */
	List<CommonVo> getReCountGroupByProdType(CommonVo vo);
	List<CommonVo> getReCountGroupFaultReason(CommonVo vo);
	/**
	 * 按月获取下线数
	 * @param vo
	 * @return
	 */
	List<CommonVo> getDownLineCountGroupByMonth(CommonVo vo);
	
	/**
	 * 按维修月、按下线月获取维修数
	 * @param vo
	 * @return
	 */
	List<CommonVo> getReCountGroupByReDlMonth(CommonVo vo);
	
	
	List<CommonVo> getReCountGroupFaultsReason(CommonVo vo);
	
	
	/**
	 * 按维修月、按下线月获取维修数
	 * @param vo
	 * @return
	 */
	List<CommonVo> getInsCountGroupByMonth(CommonVo vo);
	
	/**
	 * 
	 */
	List<CommonVo> getInsCountGroupByReMonth(CommonVo vo);
	/**
	 * 获取物料小类（型号）
	 * @param vo
	 * @return
	 */
	List<CommonVo> getPartTypeFromMes(CommonVo vo);
	/**
	 * 获取产品系列
	 * @param vo
	 * @return
	 */
	List<CommonVo> getProductFamilyFromMes(CommonVo vo);
	
	/**
	 * 获取合并区域
	 * @param vo
	 * @return
	 */
	List<CommonVo> getMergeRegion(CommonVo vo);
	
	/**
	 * 获取区域
	 * @param vo
	 * @return
	 */
	List<CommonVo> getRegion(CommonVo vo);
	
	/**
	 * 获取工厂
	 * @param vo
	 * @return
	 */
	List<ProductionLine> getFactorys(ProductionLine vo);
	
	/**
	 * 根据工厂基础数据表获取工厂
	 * @param vo
	 * @return
	 */
	List<ProductionLine> getFactorysBySite(ProductionLine vo);
	
	/**
	 * 获取产线
	 * @param vo
	 * @return
	 */
	List<ProductionLine> getLines(ProductionLine vo);
	
	/**
	 * 错误维修条码汇总表
	 * @param bs
	 * @return
	 */
	List<CommonVo> getFaultCodeTotal(BaseSearch bs);
	
	/**
	 * 维修汇总表维修数量总和
	 * @param bs
	 * @return
	 */
	
	/**
	 * 匹配维修汇总表维修数量总和
	 * @param bs
	 * @return
	 */
	int sumMatchRepair(BaseSearch bs);
	
	List<CommonVo> getRepairTotalByPages(BaseSearch bs);
	
	/**
	 * 查询型号
	 * @param vo
	 * @return
	 */
	List<CommonVo> getPartTypeListFromMes(CommonVo vo);
	
	List<CommonVo> getInsCountGroupByPartType(CommonVo vo);
	
	List<CommonVo> getInsCountGroupByRePartType(CommonVo vo);
	
	List<CommonVo> getInsCountGroupByRegion(CommonVo vo);
	
	List<CommonVo> getInsCountGroupByReRegion(CommonVo vo);
	
	List<CommonVo> getInsCountGroupByMonthAndInterval(CommonVo vo);
	/**
	 * 获取指定区间总的安装数
	 * @param vo
	 * @return
	 */
	Long getAllInsCountGroup(CommonVo vo);
	/**
	 * 获取客户之声二级分类
	 */
	List<CommonVo> getVoiceCategory(CommonVo vo);
}
