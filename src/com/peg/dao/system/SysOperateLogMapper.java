package com.peg.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.system.SysOperateLog;

public interface SysOperateLogMapper extends BaseMapper<SysOperateLog, Long>{
    
    List<SysOperateLog> getAll();
 
    List<SysOperateLog> findAllByPage(@Param("page")PageParameter page,@Param("log")SysOperateLog log);
}