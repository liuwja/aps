package com.peg.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.system.SumOperationLogMapper;
import com.peg.model.system.SumOperationLog;
import com.peg.service.system.SumOperationLogServiceI;


@Service("sumOperationLogService")
public class SumOperationLogServiceImpl implements SumOperationLogServiceI{

	@Autowired
	private SumOperationLogMapper sumOperationLogMapper;
	
	@Override
	public int insert(SumOperationLog record) {
		// TODO Auto-generated method stub
		sumOperationLogMapper.insert(record);
		return 0;
	}

	@Override
	public List<SumOperationLog> getAllByPage(BaseSearch bs) {
		// TODO Auto-generated method stub
		return sumOperationLogMapper.getAllByPage(bs);
	}

	@Override
	public List<SumOperationLog> findAll() {
		// TODO Auto-generated method stub
		return sumOperationLogMapper.getAll();
	}

	@Override
	public int updateByPrimaryKeySelective(SumOperationLog record) {
		// TODO Auto-generated method stub
		return sumOperationLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SumOperationLog selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return sumOperationLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return sumOperationLogMapper.deleteByPrimaryKey(id);
	}
	
}
