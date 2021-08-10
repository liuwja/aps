package com.peg.service.bph;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.CheckItem;

public interface CheckItemServiceI {
	
	int deleteByPrimaryKey(Long id);

    int insert(CheckItem record);
    
	List<CheckItem> getAllByPage(BaseSearch bs);

	CheckItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CheckItem record);

    int updateByPrimaryKey(Long id);

    //获取班组类别
    List<CheckItem> getCategory(CheckItem checkItem);
    
    List<CheckItem> getItemAll();
    
    List<CheckItem> getCheckItem(@Param("factory")String factory,@Param("category")String category);
}
