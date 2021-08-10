package com.peg.service.part.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.part.NewPartRefMapper;
import com.peg.model.part.NewPartRef;
import com.peg.service.AbstractService;
import com.peg.service.part.NewPartRefServiceI;

@Service("newPaertRefService")
public class NewPartRefServiceImpl extends AbstractService<NewPartRef, Long> implements NewPartRefServiceI{

	@Autowired
	private NewPartRefMapper newPartRefMapper;
	@Autowired
	@Override
	public void setBaseMapper() {
		super.setBaseMapper(newPartRefMapper);
	}

	@Override
	public List<NewPartRef> findAllByPage(BaseSearch bs) {
		return newPartRefMapper.findAllByPage(bs);
	}

}
