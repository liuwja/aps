package com.peg.dao;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.Manage;


public interface ManageMapper {
	int deleteByPrimaryKey(Long id);

    int insert(Manage record);

    int insertSelective(Manage record);
    
	List<Manage> getAllByPage(BaseSearch bs);

    Manage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Manage record);

    int updateByPrimaryKey(Manage record);

    List<Manage> getAll();
}
