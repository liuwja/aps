package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.RepairRateDashboardMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.RepairRateDashboard;
import com.peg.service.RepairRateDashboardServiceI;

@Service("repairRateDashboardService")
public class RepairRateDashboardServiceImpl implements RepairRateDashboardServiceI {

	@Autowired
	private RepairRateDashboardMapper repairRateDashboardMapper;
	
	@Override
	public RepairRateDashboard selectByPrimaryKey(Long id) {
		return repairRateDashboardMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<RepairRateDashboard> getAll(BaseSearch bs) {
		return repairRateDashboardMapper.getAll(bs);
	}
	
	@Override
	public List<RepairRateDashboard> getAllByPage(BaseSearch bs) {
		return repairRateDashboardMapper.getAllByPage(bs);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return repairRateDashboardMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RepairRateDashboard record) {
		return repairRateDashboardMapper.insert(record);
	}

	@Override
	public int insertSelective(RepairRateDashboard record) {
		return repairRateDashboardMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKey(RepairRateDashboard record) {
		return repairRateDashboardMapper.updateByPrimaryKey(record);
	}

}
