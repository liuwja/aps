package com.peg.service.impl.system;

import com.peg.dao.interceptor.PageParameter;
import com.peg.dao.system.SmtLogMapper;
import com.peg.model.smt.SmtLog;
import com.peg.service.system.SmtLogServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @createTime 2019-06-19 16:20
 */
@Service("smtLogService")
public class SmtLogServiceImpl implements SmtLogServiceI {

    @Autowired
    private SmtLogMapper smtLogMapper;

    @Override
    public int insertSmtLog(SmtLog smtLog) {
        return smtLogMapper.insertSmtLog(smtLog);
    }

    @Override
    public List<SmtLog> getAllByPage(PageParameter page, SmtLog smtLog) {
        return smtLogMapper.getAllByPage(page, smtLog);
    }
}
