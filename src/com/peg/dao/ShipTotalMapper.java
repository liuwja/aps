package com.peg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ShipTotal;

public interface ShipTotalMapper extends BaseMapper<ShipTotal, Long>{
	//分页查询所有
	List<ShipTotal> findAllByPage(@Param("shipTotal") ShipTotal shipTotal,@Param("page") PageParameter page);
	//不分页查询所有
	List<ShipTotal> findAll(@Param("shipTotal") ShipTotal shipTotal);
	//发货总数
	int sumship(@Param("shipTotal") ShipTotal shipTotal);
    
}