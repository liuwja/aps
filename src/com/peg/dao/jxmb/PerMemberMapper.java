package com.peg.dao.jxmb;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.PerItem;
import com.peg.model.jxmb.PerMember;

public interface  PerMemberMapper extends BaseMapper<PerMember, Long>{
	List<PerMember>getAllGroup();
	/**
	 * 按页查询月度基准目标设定
	 * @param bs
	 * @return
	 */
	List<PerItem>getMonthAllByPage(BaseSearch bs);
	
	/**
	 * 按查询月度基准目标设定
	 * @param bs
	 * @return
	 */
	List<PerItem>getMonthAll(BaseSearch bs);
	
	/**
	 * 按月查询所有月度考核基准目标
	 * @param queryMonth
	 * @return
	 */
	List<PerMember>getMonthAllByMonth(@Param("queryMonth")String queryMonth);
	
	/* 查询考核指标
	 * @param factory
	 * @param area
	 * @param groupName
	 * @return
	 */
	List<PerMember>getIndexAllByGroup(@Param("factory")String factory,
			@Param("area")String area,@Param("groupName")String groupName);
}