package com.peg.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.PageParameter;
import com.peg.dao.system.SysOperateLogMapper;
import com.peg.model.system.SysOperateLog;
import com.peg.service.AbstractService;
import com.peg.service.system.SysOperateLogServiceI;

@Service("sysOperateLogService")
public class SysOperateLogServiceImpl extends AbstractService<SysOperateLog, Long> implements SysOperateLogServiceI{

	@Autowired
	private SysOperateLogMapper sysOperateLogMapper;
	
	@Autowired
	@Override
	public void setBaseMapper() {
		super.setBaseMapper(sysOperateLogMapper);
		
	}

	@Override
	public List<SysOperateLog> findAllByPage(PageParameter page,
			SysOperateLog log) {
		return sysOperateLogMapper.findAllByPage(page, log);
	}

}
