package com.peg.dao.system;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.system.SumOperationLog;

public interface SumOperationLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SumOperationLog record);

    int insertSelective(SumOperationLog record);

    SumOperationLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SumOperationLog record);

    int updateByPrimaryKey(SumOperationLog record);

	List<SumOperationLog> getAllByPage(BaseSearch bs);

	List<SumOperationLog> getAll();
}