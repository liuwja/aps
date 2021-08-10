package com.peg.service.impl.bph;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.MonthlyAssessmentMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.MonthlyAssessment;
import com.peg.service.bph.MonthlyAssessmentServiceI;

@Service("MonthlyAssessmentService")
public class MonthlyAssementServiceImpl implements MonthlyAssessmentServiceI{

	@Autowired
	private MonthlyAssessmentMapper monthlyAssessmentMapper ;
	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return monthlyAssessmentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<MonthlyAssessment> getAllByPage(BaseSearch bs) {

		return monthlyAssessmentMapper.getAllByPage(bs);
	}

	@Override
	public int insert(MonthlyAssessment record) {
        monthlyAssessmentMapper.insert(record);
		return 0;
	}

	@Override
	public int insertSelective(MonthlyAssessment record) {
		monthlyAssessmentMapper.insertSelective(record);
		return 0;
	}

	@Override
	public MonthlyAssessment selectByPrimaryKey(Long id) {

		return monthlyAssessmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(MonthlyAssessment record) {
		
		return monthlyAssessmentMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(MonthlyAssessment record) {
		return monthlyAssessmentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<MonthlyAssessment> getAssessment(BaseSearch bs) {
		
		return monthlyAssessmentMapper.getAssessment(bs);
	}

	@Override
	public List<MonthlyAssessment> getIndex(BaseSearch bs) {
		
		return monthlyAssessmentMapper.getIndex(bs);
	}

	@Override
	public List<MonthlyAssessment> getScale(BaseSearch bs) {
		
		return monthlyAssessmentMapper.getScale(bs);
	}

	@Override
	public List<MonthlyAssessment> getkey(BaseSearch bs) {
		// TODO Auto-generated method stub
		return monthlyAssessmentMapper.getkey(bs);
	}

	@Override
	public List<MonthlyAssessment> getIndexCode(MonthlyAssessment month) {
		
		return monthlyAssessmentMapper.getIndexCode(month);
	}

	@Override
	public List<MonthlyAssessment> fingMonthIndex(String month, String factory,
			String group, String indexCode) {
		
		return monthlyAssessmentMapper.fingMonthIndex(month, factory, group, indexCode);
	}

	@Override
	public int deleteMonthIndex(String month, String factory, String group,
			String indexCode) {
		
		return monthlyAssessmentMapper.deleteMonthIndex(month, factory, group, indexCode);
	}

	@Override
	public List<MonthlyAssessment> findItemId(String factory, String category,
			String itemCode) {
		
		return monthlyAssessmentMapper.findItemId(factory, category, itemCode);
	}

	@Override
	public List<MonthlyAssessment> findIndexId(String factory, String category,
			String indexCode) {
		
		return monthlyAssessmentMapper.findIndexId(factory, category, indexCode);
	}


	@Override
	public List<MonthlyAssessment> getMonthAssemByFacMon(String factory,
			String month) {
		
		return monthlyAssessmentMapper.getMonthAssemByFacMon(factory, month);
	}

	@Override
	public List<MonthlyAssessment> getMonthAssemByFacAreaMon(String factory,
			String area, String month) {
		
		return monthlyAssessmentMapper.getMonthAssemByFacAreaMon(factory, area, month);
	}

	@Override
	public List<MonthlyAssessment> getMonthAssemByExcel(MonthlyAssessment month) {
		
		return monthlyAssessmentMapper.getMonthAssemByExcel(month);
	}

	@Override
	public List<MonthlyAssessment> selectGroupIndexByPeriod(String group,
			String startTime, String endTime) {

		return monthlyAssessmentMapper.selectGroupIndexByPeriod(group, startTime, endTime);
	}

}
