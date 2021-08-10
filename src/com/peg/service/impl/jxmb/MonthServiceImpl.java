package com.peg.service.impl.jxmb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.jxmb.PerMonthMapper;
import com.peg.model.jxmb.PerMonth;
import com.peg.service.AbstractService;
import com.peg.service.jxmb.MonthServicel;

@Service("permonthService")
public class MonthServiceImpl extends AbstractService<PerMonth, Long> implements MonthServicel{

	@Autowired
	private PerMonthMapper permonthMapper;
	
	@Override
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(permonthMapper);
	}

	@Override
	public List<PerMonth> findeByGroupAndMonth(Long groupKey, String queryMonth) {
		
		return permonthMapper.findeByGroupAndMonth(groupKey, queryMonth);
	}

	@Override
	public List<PerMonth> findeByGroupAndMonthAndIndex(Long groupKey, String queryMonth, Long indexKey) {
		// TODO Auto-generated method stub
		return permonthMapper.findeByGroupAndMonthAndIndex(groupKey, queryMonth, indexKey);
	}

	@Override
	public List<PerMonth> selectByGroupAndMonth(Long groupKey) {
		// TODO Auto-generated method stub
		return permonthMapper.selectByGroupAndMonth(groupKey);
	}

	@Override
	public List<PerMonth> getMonthAllByPage(BaseSearch bs) {
		// TODO Auto-generated method stub
		return permonthMapper.getMonthAllByPage(bs);
	}

}
