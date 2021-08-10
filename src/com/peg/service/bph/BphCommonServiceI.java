package com.peg.service.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.EntityClass;
import com.peg.model.bph.BphCommonVo;
import com.peg.model.bph.Contrast;
import com.peg.model.bph.Group;
import com.peg.model.bph.Index;
import com.peg.model.bph.IndexScroe;
import com.peg.model.bph.Item;
import com.peg.model.bph.ItemScore;
import com.peg.model.bph.MesDataSum;

public interface BphCommonServiceI {

	/**
	 * 插入MES数据总和表
	 * @method: insertMesDataSum() -by fjt
	 * @TODO:  
	 * @return int
	 */
     int insertMesDataSumMonth(MesDataSum mesDataSum)  throws Exception;
	
	 
     /**
 	 * 插入MES数据总和表日
 	 */
      int insertMesDataSumDay(MesDataSum mesDataSum) throws Exception;

	    
	    /**
		 * 根据班组名称获取班组绩效
		 */
		List<BphCommonVo> getShiftGroupPerFormanceByGroup(BphCommonVo vo);
		
		/**
		 * 获取班组绩效列表
		 * @method: getShiftGroupPerformanceList() -by fjt
		 * @TODO:  
		 */
		List<BphCommonVo> getShiftGroupPerformanceList( BaseSearch bs);
		
		/**
		 * 获取日考核指标列表 
		 */
		List<BphCommonVo> getIndexScoreAllByPage(BaseSearch bs);
		
		 /**
	     * 查询指标得分月
	     * @method: getIndexScoreListByMonth() -by fjt
	     */
	    List<IndexScroe> getIndexScoreMonthListByPage(BaseSearch bs);
	    

	    /**
	     * 查询指标得分总分
	     * @method: getSumIndexScore() -by fjt
	     * @TODO:  
	     */
	    List<IndexScroe> getSumIndexScore(BaseSearch bs);
	    
	    /**
	     * 查询指标得分分月查询
	     * @method: getIndexScoreByMonth() -by fjt
	     * @TODO:  
	     */
	    List<IndexScroe> getIndexScoreByMonth(BaseSearch bs);
	    
	    /**
	     * 插入qms_data_month表月份
	     * @method: insertQmsdataMonth() -by fjt
	     * @TODO:  
	     * @param startTime
	     */
	    int insertQmsdataMonth(String startTime, String endTime);
	    
	    /**
	     * 获取班组绩效得分月-by fjt
	     */
	    List<ItemScore> sumItemScore(ItemScore record);
	    
	    /**
	     * 从月度考核指标设定获取月度指标基准
	     */
	    List<IndexScroe> getMonthIndexScoreListByPage(BaseSearch bs);
	    /**
	     * 获取班组绩效得分月(跟进班组)
	     */
	    List<ItemScore> sumItemScoreByGroup(ItemScore record);
	    
	    /**
	     * 获取首页班组得分
	     */
//	    Map<String, Map<String,BphCommonVo>> getHomePageGroupPerformance();

	    //获取班组绩效（过滤）
		List<BphCommonVo> getShiftGroupPerformanceByMonth(BaseSearch bs);
		
		//查询一个班组一年中每个月的得分
		List<BphCommonVo> sumItemScoreByYear(BphCommonVo vo);
		
		//查询一个班组期间中每个月的得分
		List<BphCommonVo> sumItemScoreBySelectMonth(BphCommonVo vo);
		
		//查询一个班组排名
		List<BphCommonVo> selectGroupRank(BphCommonVo vo);
		
		/**
		 * 插入qms_data表数据
		 * @param mesDataSum
		 * @return
		 */
		int insertQmsData(MesDataSum mesDataSum);
		
		/**
		 * 根据工厂车间班组类别查班组名称
		 * @param is
		 * @return
		 */
		List<Group> getGroupList(IndexScroe is);
		/**
		 * 根据id班组名称
		 * @param list
		 * @return
		 */
		List<Group> getGroupList2(List<String> list);
		/**
		 * 根据班组类别查考核项目
		 * @param is
		 * @return
		 */
		List<Item> getItemList(IndexScroe is);
		
		/**
		 * 根据考核项目查考核指标
		 * @param str
		 * @return
		 */
		List<Index> getIndexList(List<String> str);
		/**
		 * 查绩效对比数据
		 * @param grouplist
		 * @param itemlist
		 * @param indexlist
		 * @param monthly
		 * @return
		 */
		List<Contrast> getContrast(List<String> grouplist,List<String> itemlist,
				List<String> indexlist,String monthly);
		/**
		 * 根据条件查实际值
		 * @param indexkey
		 * @param strgroupname
		 * @return
		 */
		List<EntityClass> getEntity(String indexkey,String[] strgroupname,String monthly);
		/**
		 * 查班组下所有考核项目
		 * @param vo
		 * @return
		 */
		List<Item> getallitemlist(IndexScroe vo);
		/**
		 * 查项目下所有考核指标
		 * @param item
		 * @return
		 */
		List<Index> getallindexlist(List<String> item);
		/**
		 * 查指标对象
		 * @param item
		 * @return
		 */
		List<Index> getindexlistkey(List<String> indexkey);
		/**
		 * 查指标对象
		 * @param item
		 * @return
		 */
		List<Item> getitemlistkey(List<String> itemkey);
		
		
		
}
