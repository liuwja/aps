package com.peg.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.system.DataSourceMapper;
import com.peg.model.system.DataSource;
import com.peg.service.system.DataSourceServiceI;

@Service("dataSourceService")
public class DataSourceServiceImple implements DataSourceServiceI {

	@Autowired
	private DataSourceMapper dataSourceMapper;
	
	@Override
	public List<DataSource> findPage(BaseSearch bs) {
		return dataSourceMapper.findPage(bs);
	}

	@Override
	public List<DataSource> findAll(BaseSearch bs) {
		return dataSourceMapper.findAll(bs);
	}

	@Override
	public DataSource selectByPrimaryKey(Long id) {
		return dataSourceMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<DataSource> findByName(DataSource vo) {
		return dataSourceMapper.findByName(vo);
	}
	
	@Override
	public int insert(DataSource vo) {
		return dataSourceMapper.insert(vo);
	}

	@Override
	public int update(DataSource vo) {
		return dataSourceMapper.update(vo);
	}

	@Override
	public int delete(DataSource vo) {
		return dataSourceMapper.delete(vo);
	}
}
