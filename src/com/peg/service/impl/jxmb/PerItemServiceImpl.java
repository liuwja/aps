package com.peg.service.impl.jxmb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.jxmb.PerItemMapper;
import com.peg.model.jxmb.PerItem;
import com.peg.service.AbstractService;
import com.peg.service.jxmb.PerItemServicel;

@Service("peritemService")
public class PerItemServiceImpl extends AbstractService<PerItem, Long>implements PerItemServicel  {

	@Autowired
	private PerItemMapper peritemMapper;
	
	
	@Override
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(peritemMapper);
		
	}

	@Override
	public List<PerItem> getAllItems(String department, String targetclass, String indexcontent) {
		return peritemMapper.getAllItems(department, targetclass, indexcontent);
	}

	@Override
	public List<PerItem> getItemByGcKey(Long id) {
		return peritemMapper.getItemByGckey(id);
	}

	@Override
	public List<PerItem> getMonthAllByPage(BaseSearch bs, boolean isPage) {
		
		return peritemMapper.getMonthAllByPage(bs);
	}

}
