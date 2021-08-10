package com.peg.service;
import java.util.List;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CrmInstall;

public interface CrmInstallService {
List<CrmInstall> findAllByPage(CrmInstall crmInstall,PageParameter page);
	
	List<CrmInstall> findRecordAllByPage(CrmInstall crmInstall,PageParameter page);
}
