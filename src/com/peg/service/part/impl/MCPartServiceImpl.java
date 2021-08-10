package com.peg.service.part.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.part.MCPartMapper;
import com.peg.model.part.MCPart;
import com.peg.service.part.MCPartServiceI;
import com.peg.web.util.ConstantInterface;

@Service("MCPartService")
public class MCPartServiceImpl implements MCPartServiceI {

	@Autowired
	private MCPartMapper MCPartmapper;
	
	@Override
	public List<MCPart> findAll(BaseSearch bs) {
		if (ConstantInterface.YES.equals(bs.getHashMap().get("isPage"))) {
			return MCPartmapper.findAllPage(bs);
		} else {
			return MCPartmapper.findAll(bs);
		}
	}

	@Override
	public MCPart selectByPrimaryKey(Long id) {
		return MCPartmapper.selectByPrimaryKey(id);
	}

	@Override
	public int insert(MCPart vo) {
		return MCPartmapper.insert(vo);
	}

	@Override
	public int update(MCPart vo) {
		return MCPartmapper.update(vo);
	}

	@Override
	public int delete(MCPart vo) {
		return MCPartmapper.delete(vo);
	}

}
