package com.peg.dao.jxmb;

import java.util.List;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.PerformanceCheck;

public interface PerformanceCheckMapper extends BaseMapper<PerformanceCheck, Long>{
    int insert(PerformanceCheck record);

    int insertSelective(PerformanceCheck record);
    List<PerformanceCheck>getByid(Long formula);
    int deleteByPrimaryKey(Long id);    
    PerformanceCheck selectByPrimaryKey(Long id);  
    int updateByPrimaryKey(PerformanceCheck record);
    int updateByPrimaryKeySelective(PerformanceCheck record);
    List<PerformanceCheck>getAllByPage(BaseSearch bs);
    List<PerformanceCheck>getAll(BaseSearch bs);
}