package com.peg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ReworkSheet;
/**
 * 
 * <p>Title: ReworkSheetMapper</p>
 * <p>Description: 返工停线单dao</p>
 * <p>Company: Fotile</p> 
 * @author dingzc 
 * @date 2018-3-9 上午10:06:05
 */
public interface ReworkSheetMapper extends BaseMapper<ReworkSheet, Long>{
	
	List<ReworkSheet> getAllByPage(@Param("reworkSheet")ReworkSheet reworkSheet,@Param("page")PageParameter page);
	ReworkSheet getByid(ReworkSheet reworkSheet);
	ReworkSheet getByReworkNumber(ReworkSheet reworkSheet);
	int insertSelective(ReworkSheet reworkSheet);
	int updateByIdSelective(ReworkSheet reworkSheet);
	int deleteById(ReworkSheet reworkSheet);
}
