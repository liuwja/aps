package com.peg.dao.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.ProcessAuditRecord;

public interface ProcessAuditRecordMapper {

	List<ProcessAuditRecord> getProcessAuditAllByPage(BaseSearch bs);

	List<ProcessAuditRecord> getProcessAudit(
			ProcessAuditRecord processAuditRecord);
}