package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.ManageMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.Manage;
import com.peg.service.ManageServiceI;
@Service("ManageService")
public class ManageServiceImpl implements ManageServiceI{
	
	@Autowired
	private ManageMapper ManageMapper;
	@Override
	public int insert(Manage record) {
		ManageMapper.insert(record);
		return 0;
	}

	@Override
	public List<Manage> getAllByPage(BaseSearch bs) {
		
		return ManageMapper.getAllByPage(bs);
	}

	@Override
	public List<Manage> findAll() {
		// TODO Auto-generated method stub
		return ManageMapper.getAll();
	}

	@Override
	public int updateByPrimaryKeySelective(Manage record) {
		// TODO Auto-generated method stub
		return ManageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Manage selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return ManageMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return  ManageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Manage selectByPrimaryKeyForPartManager(Long id) {
		// TODO Auto-generated method stub
		return ManageMapper.selectByPrimaryKey(id);
	}

}
