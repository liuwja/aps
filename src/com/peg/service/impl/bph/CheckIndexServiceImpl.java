package com.peg.service.impl.bph;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.CheckIndexMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.CheckIndex;
import com.peg.service.bph.CheckIndexServiceI;
@Service("CheckIndexService")
public class CheckIndexServiceImpl implements CheckIndexServiceI{

	@Autowired
	private CheckIndexMapper checkIndexMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		return checkIndexMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public List<CheckIndex> getAllByPage(BaseSearch bs) {
		
		return checkIndexMapper.getAllByPage(bs);
	}

	@Override
	public int insert(CheckIndex record) {
		checkIndexMapper.insert(record);
		return 0;
	}

	@Override
	public CheckIndex selectByPrimaryKey(Long id) {
		
		return checkIndexMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(CheckIndex record) {
		
		return checkIndexMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(CheckIndex record) {
		
		return checkIndexMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<CheckIndex> getItem(BaseSearch bs) {
		
		return checkIndexMapper.getItem(bs);
	}

	@Override
	public List<CheckIndex> getCheckIndex(BaseSearch bs) {
		
		return checkIndexMapper.getCheckIndex(bs);
	}

	@Override
	public List<CheckIndex> getCheckIndexList(BaseSearch bs) {
		
		return checkIndexMapper.getCheckIndexList(bs);
	}

	@Override
	public List<CheckIndex> getCategory(CheckIndex index) {
		
		return checkIndexMapper.getCategory(index);
	}

	
	
}
