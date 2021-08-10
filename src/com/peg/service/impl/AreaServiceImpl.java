package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.AreaMapper;
import com.peg.model.Area;
import com.peg.service.AreaServiceI;
@Service("AreaService")
public class AreaServiceImpl implements AreaServiceI{

	@Autowired
	private AreaMapper areaMapper;
	@Override
	public List<Area> selectByFactory(String factory) {
		
		return areaMapper.selectByFactory(factory);
	}

	@Override
	public Area selectByPrimaryKey(Area key) {
		
		return areaMapper.selectByPrimaryKey(key);
	}

}
