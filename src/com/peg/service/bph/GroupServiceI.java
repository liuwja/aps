package com.peg.service.bph;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.Group;
import com.peg.service.BaseService;

public interface GroupServiceI extends BaseService<Group, Long>{

	List<Group> getAllGroup();

	List<Group> getAllGroupFromMes();
	
	Group getGroupByFag(@Param("factory")String factory,
			@Param("area") String area,@Param("groupCategory") String groupCategory,@Param("groupName")String groupName);
	
	List<Group> getGroupByFa(@Param("factory")String factory,@Param("area") String area);
	
	List<Group> getMonthAllByPage(BaseSearch bs);
	
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
