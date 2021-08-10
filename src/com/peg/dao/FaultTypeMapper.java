package com.peg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.FaultType;

public interface FaultTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FaultType record);

    int insertSelective(FaultType record);

    FaultType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FaultType record);

    int updateByPrimaryKey(FaultType record);

	List<FaultType> findAllByPage(@Param("faultType")FaultType faultType,@Param("page")PageParameter page);
	
	List<FaultType> findAll(@Param("faultType")FaultType faultType);

	List<FaultType> getAll();
	
	FaultType getfaultbykey(String u);
}