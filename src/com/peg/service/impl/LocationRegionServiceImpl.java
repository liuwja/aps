package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.LocationRegionMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.LocationRegion;
import com.peg.service.LocationRegionServiceI;

@Service("locationRegionService")
public class LocationRegionServiceImpl implements LocationRegionServiceI{
	@Autowired
	private LocationRegionMapper locationRegionMapper;

	@Override
	public int insert(LocationRegion record) {
		return locationRegionMapper.insert(record);
	}

	@Override
	public List<LocationRegion> getAllByPage(BaseSearch bs) {
		// TODO Auto-generated method stub
		return locationRegionMapper.getAllByPage(bs);
	}



	@Override
	public int updateByPrimaryKeySelective(LocationRegion record) {
		// TODO Auto-generated method stub
		return locationRegionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public LocationRegion selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return locationRegionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return locationRegionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<LocationRegion> findAll() {
		// TODO Auto-generated method stub
		return locationRegionMapper.getAll();
	}

	@Override
	public List<LocationRegion> getAllLocation() {
		// TODO Auto-generated method stub
		return locationRegionMapper.getAllLocation();
	}

	@Override
	public int updatelocation(LocationRegion record) {
		// TODO Auto-generated method stub
		return locationRegionMapper.updatelocation(record);
	}

	@Override
	public int insertlocation(LocationRegion record) {
		// TODO Auto-generated method stub
		return locationRegionMapper.insertlocation(record);
	}

	@Override
	public List<LocationRegion> findAllByPage(LocationRegion locationRegion, PageParameter page) {
		return locationRegionMapper.findAllByPage(locationRegion, page);
	}
	
	@Override
	public int saveMesh(List<String> regionList, String mergeRegion) {
		return locationRegionMapper.saveMesh(regionList, mergeRegion);
	}
	
	@Override
	public int breakMesh(List<String> regionList) {
		return locationRegionMapper.breakMesh(regionList);
	}
}
