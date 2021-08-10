package com.peg.service.jxmb;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.PerItem;
import com.peg.model.jxmb.PerMember;
import com.peg.service.BaseService;

public interface PerMemberServicel extends BaseService<PerMember, Long> {

	List<PerMember>getAllGroup();
	
	List<PerMember>getAllGroupFromMes();
	
	PerMember  getGroupByFag(@Param("factory")String factory,
			@Param("area") String area,@Param("groupCategory") String groupCategory,@Param("groupName")String groupName);
	
	List<PerMember> getGroupByFa(@Param("factory")String factory,@Param("area") String area);
	
	/**
	 * @param bs
	 * @param isPage
	 * @return
	 */
	List<PerItem>getMonthAllByPage(BaseSearch bs, boolean isPage);
	
	List<PerMember>getGroupScoreByMonth(@Param("factory")String factory,
			@Param("area")String area,@Param("groupName")String groupName,@Param("monthly")String monthly);
	
	/**
	 * 查询考核指标
	 * 
	 * @return
	 */
	List<PerMember>getIndexAllByGroup(@Param("factory")String factory,
			@Param("area")String area,@Param("groupName")String groupName);
}
