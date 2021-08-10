package com.peg.dao;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.MachineType;

public interface MachineTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MachineType record);

    int insertSelective(MachineType record);

    MachineType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MachineType record);

    int updateByPrimaryKey(MachineType record);

	List<MachineType> getAllByPage(BaseSearch bs);

	List<MachineType> getAll();
}