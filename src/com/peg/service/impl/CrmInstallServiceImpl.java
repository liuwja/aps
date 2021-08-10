package com.peg.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.peg.dao.CrmInstallMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CrmInstall;
import com.peg.service.CrmInstallService;
@Service("crmInstallService")
public class CrmInstallServiceImpl implements CrmInstallService{
	@Autowired
	private CrmInstallMapper crmInstallMapper;
	@Override
	public List<CrmInstall> findAllByPage(CrmInstall crmInstall,PageParameter page) {
		// TODO Auto-generated method stub
		List<CrmInstall> list = crmInstallMapper.findAllByPage(crmInstall, page);
		return list;
	}
	
	@Override
	public List<CrmInstall> findRecordAllByPage(CrmInstall crmInstall,PageParameter page) {
		return crmInstallMapper.findRecordAllByPage(crmInstall, page);
	}

}
