package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.RepairRateMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.RepairRate;
import com.peg.service.RepairRateServiceI;
@Service("repairRateService")
public class RepairRateServiceImpl implements RepairRateServiceI{
	@Autowired
	private RepairRateMapper repairRateMapper;

	@Override
	public int insert(RepairRate record) {
		// TODO Auto-generated method stub
		repairRateMapper.insert(record);
		return 0;
	}

	@Override
	public List<RepairRate> getAllByPage(BaseSearch bs) {
		// TODO Auto-generated method stub
		return repairRateMapper.getAllByPage(bs);
	}

	@Override
	public List<RepairRate> findAll(String startTime,String endTime) {
		// TODO Auto-generated method stub
		return repairRateMapper.getAll(startTime,endTime);
	}

	@Override
	public int updateByPrimaryKeySelective(RepairRate record) {
		// TODO Auto-generated method stub
		return repairRateMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public RepairRate selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return repairRateMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return repairRateMapper.deleteByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.peg.service.RepairRateServiceI#findByDateRange(com.peg.dao.interceptor.BaseSearch)
	 */
	@Override
	public List<RepairRate> findByDateRange(BaseSearch bs)
	{
		return repairRateMapper.findByDateRange(bs);
	}
}
