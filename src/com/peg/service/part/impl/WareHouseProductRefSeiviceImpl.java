package com.peg.service.part.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.part.WareHouseProductRefMapper;
import com.peg.model.part.WareHouseProductRef;
import com.peg.service.AbstractService;
import com.peg.service.part.WareHouseProductRefServiceI;


@Service("wareHouseProductRefService")
public class WareHouseProductRefSeiviceImpl extends AbstractService<WareHouseProductRef, Long> implements WareHouseProductRefServiceI{

	@Autowired
	private WareHouseProductRefMapper wareHouseProductRefMapper;
	@Autowired
	@Override
	public void setBaseMapper() {
		super.setBaseMapper(wareHouseProductRefMapper);
	}

	@Override
	public List<WareHouseProductRef> findAllByPage(BaseSearch bs) {
		return wareHouseProductRefMapper.findAllByPage(bs);
	}

}
