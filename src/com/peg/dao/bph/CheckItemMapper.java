package com.peg.dao.bph;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.CheckItem;

public interface CheckItemMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(CheckItem record);

    int insertSelective(CheckItem record);
    
	List<CheckItem> getAllByPage(BaseSearch bs);

	CheckItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CheckItem record);

    int updateByPrimaryKey(Long id);
    
    List<CheckItem> getItemAll();
    
    //获取班组类别
    List<CheckItem> getCategory(CheckItem checkItem);

    List<CheckItem> getCheckItem(@Param("factory")String factory,@Param("category")String category);
}
