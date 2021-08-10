package com.peg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.LocationRegion;

public interface LocationRegionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LocationRegion record);

    int insertSelective(LocationRegion record);

    LocationRegion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LocationRegion record);

    int updateByPrimaryKey(LocationRegion record);

	List<LocationRegion> getAllByPage(BaseSearch bs);

	List<LocationRegion> getAll();
	
	List<LocationRegion> getAllLocation();
	
	int updatelocation(LocationRegion record);
	
	int insertlocation(LocationRegion record);
	
	List<LocationRegion> findAllByPage(@Param("locationRegion")LocationRegion locationRegion, @Param("page")PageParameter page);
	
	int saveMesh(@Param("regionList")List<String> regionList, @Param("mergeRegion")String mergeRegion);
	
	int breakMesh(@Param("regionList")List<String> regionList);
}