package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.ShipTotalMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ShipTotal;
import com.peg.service.AbstractService;
import com.peg.service.ShipTotalServiceI;

@Service("shipTotalService")
public class ShipTotalServiceImpl extends AbstractService<ShipTotal, Long>
		implements ShipTotalServiceI {

	@Autowired
	private ShipTotalMapper shipTotalMapper;

	@Override
	public void setBaseMapper() {
		super.setBaseMapper(shipTotalMapper);
	}

	@Override
	public List<ShipTotal> findAllByPage(ShipTotal shipTotal, PageParameter page) {
		// TODO Auto-generated method stub
		return shipTotalMapper.findAllByPage(shipTotal, page);
	}

	@Override
	public List<ShipTotal> findAll(ShipTotal shipTotal) {
		// TODO Auto-generated method stub
		return shipTotalMapper.findAll(shipTotal);
	}

	@Override
	public int sumship(ShipTotal shipTotal) {
		// TODO Auto-generated method stub
		return shipTotalMapper.sumship(shipTotal);
	}

}
