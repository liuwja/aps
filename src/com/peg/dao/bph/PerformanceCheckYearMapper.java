package com.peg.dao.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.PerformanceCheckYear;

public interface PerformanceCheckYearMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PerformanceCheckYear record);

    int insertSelective(PerformanceCheckYear record);

    PerformanceCheckYear selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PerformanceCheckYear record);

    int updateByPrimaryKey(PerformanceCheckYear record);
    
    List<PerformanceCheckYear> getAllByPage(BaseSearch bs);
}