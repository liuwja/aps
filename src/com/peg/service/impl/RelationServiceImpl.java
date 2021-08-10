package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.RelationMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.Relation;
import com.peg.service.RelationServiceI;
@Service("relationService")
public class RelationServiceImpl implements RelationServiceI{
	
	@Autowired
	private RelationMapper relationMapper;

	@Override
	public int insert(Relation record) {
		// TODO Auto-generated method stub
		relationMapper.insert(record);
		return 0;
	}

	@Override
	public List<Relation> getAllByPage(BaseSearch bs) {
		// TODO Auto-generated method stub
		return relationMapper.getAllByPage(bs);
	}

	@Override
	public List<Relation> findAll() {
		// TODO Auto-generated method stub
		return relationMapper.getAll();
	}

	@Override
	public int updateByPrimaryKeySelective(Relation record) {
		// TODO Auto-generated method stub
		return relationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Relation selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return relationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return relationMapper.deleteByPrimaryKey(id);
	}
}
