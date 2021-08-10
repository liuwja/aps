package com.peg.service.system;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.system.SumOperationLog;

public interface SumOperationLogServiceI {
	int insert(SumOperationLog record);

	
	List<SumOperationLog>  getAllByPage(BaseSearch bs);
	
	public List<SumOperationLog> findAll();
	
	
	int updateByPrimaryKeySelective(SumOperationLog sumOperationLog);

	SumOperationLog selectByPrimaryKey(Long id);

	int deleteByPrimaryKey(Long id);
}
