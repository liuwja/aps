package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.LocationRegion;

public interface LocationRegionServiceI {

	int insert(LocationRegion locationRegion);
	
	List<LocationRegion>  getAllByPage(BaseSearch bs);
	
	public List<LocationRegion> findAll();
		
	int updateByPrimaryKeySelective(LocationRegion locationRegion);

	LocationRegion selectByPrimaryKey(Long id);

	int deleteByPrimaryKey(Long id);

	public List<LocationRegion> getAllLocation();

	int updatelocation(LocationRegion locationRegion);

	int insertlocation(LocationRegion locationRegion);

	List<LocationRegion> findAllByPage(LocationRegion locationRegion, PageParameter page);
	
	int saveMesh(List<String> regionList, String mergeRegion);
	
	int breakMesh(List<String> regionList);
}
