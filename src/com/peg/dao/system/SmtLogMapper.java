package com.peg.dao.system;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.smt.SmtLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @createTime 2019-06-19 16:23
 */
public interface SmtLogMapper {

    int insertSmtLog(SmtLog smtLog);

    List<SmtLog> getAllByPage(@Param("page")PageParameter page, @Param("smtLog")SmtLog smtLog);
}
