package com.peg.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.system.SysLoginHistory;

public interface SysLoginHistoryMapper {
    int insert(SysLoginHistory record);

    int insertSelective(SysLoginHistory record);
    
    List<SysLoginHistory> findALLByPage(BaseSearch bs);
    
    int updateLogoutTimeBySessionId(@Param("sessionid")String sessionId);
}