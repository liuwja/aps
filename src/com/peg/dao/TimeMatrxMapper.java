package com.peg.dao;

import java.util.List;

import com.peg.model.TimeMatrx;

public interface TimeMatrxMapper {
    int deleteByPrimaryKey(TimeMatrx record);

    int insert(TimeMatrx record);

    int insertSelective(TimeMatrx record);

    TimeMatrx selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TimeMatrx record);

    int updateByPrimaryKey(TimeMatrx record);

	List<TimeMatrx> getAllByMachineType(TimeMatrx record);
}