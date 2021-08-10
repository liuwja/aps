package com.peg.dao.jxmb;

import java.util.List;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.PerMonthly;

public interface PerMonthlyMapper extends BaseMapper<PerMonthly, Long> {
    int insert(PerMonthly record);

    int insertSelective(PerMonthly record);
    
    List<PerMonthly> getMonthAllByPage(BaseSearch bs);
    
    List<PerMonthly>selectByGroupAndMonth(Long monthKey);
}