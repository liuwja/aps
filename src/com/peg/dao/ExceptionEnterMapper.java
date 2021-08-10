package com.peg.dao;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.ExceptionEnter;

public interface ExceptionEnterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExceptionEnter record);

    int insertSelective(ExceptionEnter record);

    ExceptionEnter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExceptionEnter record);

    int updateByPrimaryKey(ExceptionEnter record);
    
    List<ExceptionEnter> getAllByPage(BaseSearch bs);
    
    List<ExceptionEnter> getExceptionByMonth(BaseSearch bs);
}