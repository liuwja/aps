package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.RepairRateDashboard;

public interface RepairRateDashboardServiceI {

	RepairRateDashboard selectByPrimaryKey(Long id);
	
	List<RepairRateDashboard> getAllByPage(BaseSearch bs);
	
	List<RepairRateDashboard> getAll(BaseSearch bs);
	
    int deleteByPrimaryKey(Long id);

    int insert(RepairRateDashboard record);

    int insertSelective(RepairRateDashboard record);

    int updateByPrimaryKey(RepairRateDashboard record);
}
