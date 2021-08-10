package com.peg.service.impl.jxmb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.jxmb.PerSetupMapper;
import com.peg.model.jxmb.PerSetup;
import com.peg.service.jxmb.PerSetupServicel;

@Service("perSetupService")
public class PerSetupServiceImpl implements PerSetupServicel{
	
	@Autowired
	private PerSetupMapper perSetupMapper;

	@Override
	public List<PerSetup> getMonthAllByPage(BaseSearch bs) {
		// TODO Auto-generated method stub
		return perSetupMapper.getMonthAllByPage(bs);
	}

	@Override
	public void setBaseMapper() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(PerSetup record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(PerSetup record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PerSetup selectByPrimaryKey(Long monthKey) {
		// TODO Auto-generated method stub
		return perSetupMapper.selectByPrimaryKey(monthKey);
	}

	@Override
	public int updateByPrimaryKeySelective(PerSetup record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(PerSetup record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
