package com.peg.service.impl.jxmb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.jxmb.PerMonthlyMapper;
import com.peg.model.jxmb.PerMonthly;
import com.peg.service.AbstractService;
import com.peg.service.jxmb.MonthlyServicel;

@Service("permonthlyService")
public class MonthlyServiceImpl  extends AbstractService<PerMonthly, Long> implements MonthlyServicel {

	@Autowired 
	private PerMonthlyMapper monlyMapper;
	@Override
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(monlyMapper);
		
	}

	@Override
	public List<PerMonthly> selectByGroupAndMonth(Long monthKey) {
		// TODO Auto-generated method stub
		return  monlyMapper.selectByGroupAndMonth(monthKey);
	}

	

}
