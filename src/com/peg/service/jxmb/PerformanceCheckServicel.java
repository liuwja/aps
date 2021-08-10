package com.peg.service.jxmb;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.PerformanceCheck;
import com.peg.service.BaseService;

public interface PerformanceCheckServicel  extends BaseService<PerformanceCheck, Long>{


	List<PerformanceCheck> getAllBypage(BaseSearch bs);
	List<PerformanceCheck> getAll(BaseSearch bs);
	List<PerformanceCheck>getByid(Long formula);
	int deleteByPrimaryKey(Long id);
	int insertSelective(PerformanceCheck record);
	PerformanceCheck selectByPrimaryKey(Long id);
	int updateByPrimaryKeySelective(PerformanceCheck record);
	int updateByPrimaryKey(PerformanceCheck record);
}
