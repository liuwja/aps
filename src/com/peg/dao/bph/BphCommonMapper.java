package com.peg.dao.bph;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.EntityClass;
import com.peg.model.bph.BphCommonVo;
import com.peg.model.bph.Contrast;
import com.peg.model.bph.Group;
import com.peg.model.bph.Index;
import com.peg.model.bph.IndexScroe;
import com.peg.model.bph.Item;

public interface BphCommonMapper {
	
	/**
	 * 根据班组名称获取班组绩效
	 */
	List<BphCommonVo> getShiftGroupPerFormanceByGroup(BphCommonVo vo);
	
	/**
	 * 获取班组绩效列表
	 * @method: getShiftGroupPerformanceList() -by fjt
	 * @TODO:  
	 */
	List<BphCommonVo> getShiftGroupPerformanceList(BaseSearch bs);

	
    //获取班组绩效（过滤）
	List<BphCommonVo> getShiftGroupPerformanceByMonth(BaseSearch bs);
	
	//查询一个班组一年中每个月的得分
	List<BphCommonVo> sumItemScoreByYear(BphCommonVo vo);
	
	//查询一个班组期间中每个月的得分
	List<BphCommonVo> sumItemScoreBySelectMonth(BphCommonVo vo);
	
	//查询一个班组排名
	List<BphCommonVo> selectGroupRank(BphCommonVo vo);

	List<BphCommonVo> getIndexScoreAllByPage(BaseSearch bs);
	//根据班组类别查考核指标
	List<Group> getgroupName(IndexScroe is);
	//根据id查考核指标
	List<Group> getgroupName2(@Param("list")List<String> list);
	//根据班组类别查考核指标
	List<Item> getItemName(IndexScroe is);
	//根据考核指标查考核项目
	List<Index> getIndexName(@Param("list")List<String> str);
	//根据条件查考核指标得分
	List<Contrast> getgrouplist(@Param("group")List<String> grouplist,@Param("item")List<String> itemlist,
			@Param("indexl")List<String> indexlist,@Param("mon")String monthly);
	//根据条件查实际值
	List<EntityClass> getentitylist(@Param("key")String indexkey,@Param("str")String[] strgroupname,@Param("mon")String monthly);
	
	//查班组下所有的考核项目
	List<Item> getnullitemlist(@Param("entity")IndexScroe vo);
	//查项目下所有的考核项目
	List<Index> getnullindexlist(@Param("list")List<String> item);
	//查指标对象
	List<Index> getindexlistkey(@Param("list")List<String> indexkey);
	//查项目对象
	List<Item> getitemlistkey(@Param("list")List<String> itemkey);
	
}
