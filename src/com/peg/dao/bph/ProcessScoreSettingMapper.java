package com.peg.dao.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.ProcessScoreSetting;

public interface ProcessScoreSettingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProcessScoreSetting record);

    int insertSelective(ProcessScoreSetting record);

    ProcessScoreSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProcessScoreSetting record);

    int updateByPrimaryKey(ProcessScoreSetting record);
    
    List<ProcessScoreSetting> getAllByPage(BaseSearch bs);
    
    List<ProcessScoreSetting> getProcessScore(String item1,String item2);
}