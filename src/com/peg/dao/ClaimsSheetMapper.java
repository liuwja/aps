package com.peg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ClaimsSheet;
import com.peg.model.ReworkSheet;
/**
 * 
 * <p>Title: ClaimsSheetMapper</p>
 * <p>Description: 索赔处罚单DAO</p>
 * <p>Company: Fotile</p> 
 * @author dingzc 
 * @date 2018-3-13 下午3:11:30
 */
public interface ClaimsSheetMapper extends BaseMapper<ClaimsSheet, Long>{
	
	List<ClaimsSheet> getAllByPage(@Param("claimsSheet")ClaimsSheet claimsSheet,@Param("page")PageParameter page);
	ClaimsSheet getByid(ClaimsSheet claimsSheet);
	ClaimsSheet getByClaimsNumber(ClaimsSheet claimsSheet);
	List<ClaimsSheet> getByReworkId(ReworkSheet reworkSheet);
	int insertSelective(ClaimsSheet claimsSheet);
	int updateByIdSelective(ClaimsSheet claimsSheet);
	int deleteById(ClaimsSheet claimsSheet);
}
