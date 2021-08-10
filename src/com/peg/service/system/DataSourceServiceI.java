package com.peg.service.system;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.system.DataSource;

public interface DataSourceServiceI {

	List<DataSource> findPage(BaseSearch bs);
	
	List<DataSource> findAll(BaseSearch bs);
	
	DataSource selectByPrimaryKey(Long id);
	
	List<DataSource> findByName(DataSource vo);
	
	int insert(DataSource vo);
	
	int update(DataSource vo);
	
	int delete(DataSource vo);
}
