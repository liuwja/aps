package com.peg.service.impl.jxmb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.jxmb.PerSetupMapper;
import com.peg.model.jxmb.PerSetup;
import com.peg.service.AbstractService;
import com.peg.service.jxmb.PerIndexServicel;

@Service("perindexService")
public class PerIndexServiceImpl extends AbstractService<PerSetup, Long>implements PerIndexServicel {

	@Autowired
	private PerSetupMapper setupMapper;
	
	@Override
	@Autowired
	public void setBaseMapper() {
	super.setBaseMapper(setupMapper);
		
	}

	@Override
	public List<PerSetup> getIndexByItemKey(Long itemKey) {
		
		return setupMapper.getIndexByItemKey(itemKey);
	}

	@Override
	public PerSetup findByIndexCode(String department, String targetclass, 
			String indexcontent, String referencevalue) {
		return setupMapper.findByIndexCode(department, targetclass, indexcontent, referencevalue);
	}

	@Override
	public List<PerSetup> getMonthAllByPage(BaseSearch bs) {
		// TODO Auto-generated method stub
		return setupMapper.getMonthAllByPage(bs);
	}
}
