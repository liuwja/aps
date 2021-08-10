package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.CommonVo;
import com.peg.model.ProductionLine;
import com.peg.model.bph.Onlinelookup;

/**
 * 公共操作接口
 * @author song
 *
 */
public interface CommonServiceI {
	
	/**
	 * 根据区域查询维修数据
	 * @param vo
	 * @return
	 */
	List<CommonVo> getReCountGroupByRegion(CommonVo vo);
	
	/**
	 * 根据区域查询发货数据
	 * @param vo
	 * @return
	 */
	List<CommonVo> getShipCountGroupByRegion(CommonVo vo);
	
	/**
	 * 根据省份查询维修数据
	 * @param vo
	 * @return
	 */
	List<CommonVo> getReCountGroupByProvince(CommonVo vo);
	
	/**
	 * 根据省份查询发货数据
	 * @param vo
	 * @return
	 */
	List<CommonVo> getShipCountGroupByProvince(CommonVo vo);
	
	/**
	 * 按照型号分组查询维修数据
	 * @param vo
	 * @return
	 */
	List<CommonVo> getReCountGroupByPartType(CommonVo vo);
	
	/**
	 * 按照产品系列分组查询维修数据
	 * @param vo
	 * @return
	 */
	List<CommonVo> getReCountGroupByProdFamily(CommonVo vo);
	/**
	 * 按照维修月份分组查询维修数据
	 * @param vo
	 * @return
	 */
	List<CommonVo> getReCountGroupByReMon(BaseSearch bs);
	/**
	 * 按照型号分组查询发货数据
	 * @param vo
	 * @return
	 */
	List<CommonVo> getShipCountGroupByPartType(CommonVo vo);
	
	/**
	 * 按照型号分组查询发货数据
	 * @param vo
	 * @return
	 */
	List<CommonVo> getShipCountGroupByProdFamily(CommonVo vo);
	
	int getShipCount(CommonVo vo);
	
	/**
	 * 按照产线分组查询维修数据
	 * @param vo
	 * @return
	 */
	List<CommonVo> getReCountGroupByPline(CommonVo vo);
	/**
	 * 按照产线分组查询发货数据
	 * @param vo
	 * @return
	 */
	List<CommonVo> getShipCountGroupByPline(CommonVo vo);
	/**
	 * 按照故障大类分组查询维修数
	 * @param vo
	 * @return
	 */
	List<CommonVo> getReCountGroupFaultType(CommonVo vo);
	List<CommonVo> getReCountGroupFaultReason(CommonVo vo);
	
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
	 * 获取安装数，根据维修月份，类型
	 * @param vo
	 * @return
	 */
	List<CommonVo> getInsCountGroupByMonth(CommonVo vo);
	/**
	 * 获取安装数，根据安装月份，类型
	 * @param vo
	 * @return
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
	 * 获取客户之声二级分类
	 */
	List<CommonVo> getVoiceCategory(CommonVo vo);
	
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
	 * 错误条码维修汇总
	 * @param bs
	 * @return
	 */
	List<CommonVo> getFaultCodeTotal(BaseSearch bs);
	
	
	
	List<CommonVo> getRepairTotalByPages(BaseSearch bs);
	
	/**
	 * 查供应商编号
	 * @param bs
	 * @return
	 */
	List<Onlinelookup> getAccountpages(BaseSearch bs);
	/**
	 * 查物料类型
	 * @param bs
	 * @return
	 */
	List<Onlinelookup> getPartpages(BaseSearch bs);
	/**
	 * 查物料编号
	 * @param bs
	 * @return
	 */
	List<Onlinelookup> getPartTwopages(BaseSearch bs);
	/**
	 * 查工厂
	 * @return
	 */
	List<String> getFactory();
	/**
	 * 查机型类别
	 * @return
	 */
	List<String> gettype(String factory);

	/**
	 * 查询机型类别明细
	 * @param vo
	 * @return
	 */
	List<CommonVo> getPartTypeListFromMes(CommonVo vo);
	
	List<CommonVo> getInsCountGroupByPartType(CommonVo vo);
	
	List<CommonVo> getInsCountGroupByRePartType(CommonVo vo);
	
	List<CommonVo> getInsCountGroupByRegion(CommonVo vo);
	
	List<CommonVo> getInsCountGroupByReRegion(CommonVo vo);
	/**
	 * 获取安装月份下的保内维修数
	 */
	List<CommonVo> getInsCountGroupByMonthAndInterval(CommonVo vo);

	/**
	 * 获取指定日期间总的安装数
	 * @param insStarTime
	 * @param insEndTime
	 * @return 
	 */
	Long getAllInsCountGroup(CommonVo vo);
}
