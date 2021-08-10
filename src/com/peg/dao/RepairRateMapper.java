package com.peg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.RepairRate;

public interface RepairRateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RepairRate record);

    int insertSelective(RepairRate record);

    RepairRate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RepairRate record);

    int updateByPrimaryKey(RepairRate record);

	List<RepairRate> getAllByPage(BaseSearch bs);
	
	List<RepairRate> findByDateRange(BaseSearch bs);
	
	List<RepairRate> getAll(@Param("startTime")String startTime,@Param("endTime")String endTime);
}