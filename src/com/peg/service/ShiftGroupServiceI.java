package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.ShiftGroup;

public interface ShiftGroupServiceI {
	
	    int deleteByPrimaryKey(Long id);

	    int insert(ShiftGroup record);

	    ShiftGroup selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(ShiftGroup record);

	    int updateByPrimaryKey(Long id);
	    
	    List<ShiftGroup>  getAllByPage(BaseSearch bs);
	    
	    List<ShiftGroup> getArea(String factory);
	    
	    List<ShiftGroup> getShiftGroup(String factory);
	    
	    List<ShiftGroup> getName();
	    
	    List<ShiftGroup> getShiftGroupByFoArea(String factory,String area);

}
