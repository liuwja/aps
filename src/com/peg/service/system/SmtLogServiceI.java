package com.peg.service.system;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.smt.SmtLog;

import java.util.List;

/**
 * @createTime 2019-06-19 16:21
 */
public interface SmtLogServiceI {

    int insertSmtLog(SmtLog smtLog);

    List<SmtLog> getAllByPage(PageParameter page, SmtLog smtLog);
}
