package com.peg.dao.bph;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.Group;

public interface GroupMapper extends BaseMapper<Group, Long>{

	List<Group> getAllGroup();

	List<Group> getAllGroupFromMes();
    /**
     * 根据工厂车间班组名称查询班组
     * @param factory
     * @param area
     * @param groupName
     * @return
     */
	Group getGroupByFag(@Param("factory")String factory,
			@Param("area") String area,@Param("groupCategory") String groupCategory, @Param("groupName")String groupName);
	
	 /**
     * 根据工厂车间查询班组
     * @param factory
     * @param area
     * @param groupName
     * @return
     */
	List<Group> getGroupByFa(@Param("factory")String factory,@Param("area") String area);
	/**
	 * 按页查询月度基准目标设定
	 * @param bs
	 * @return
	 */
	List<Group> getMonthAllByPage(BaseSearch bs);
	/**
	 * 按月查询所有月度考核基准目标
	 * @param queryMonth
	 * @return
	 */
	List<Group> getMonthAllByMonth(@Param("queryMonth")String queryMonth);
	/**
	 * 查询班组绩效
	 * @param factory
	 * @param area
	 * @param groupName
	 * @param monthly
	 * @return
	 */
	List<Group> getGroupScoreByMonth(@Param("factory")String factory,
			@Param("area")String area,@Param("groupName")String groupName,@Param("monthly")String monthly);
	
	/**
	 * 查询考核指标
	 * @param factory
	 * @param area
	 * @param groupName
	 * @return
	 */
	List<Group> getIndexAllByGroup(@Param("factory")String factory,
			@Param("area")String area,@Param("groupName")String groupName);
}
