package com.peg.service.impl.bph;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.MonthAssessmentMapper;
import com.peg.model.bph.MonthAssessment;
import com.peg.service.AbstractService;
import com.peg.service.bph.MonthAssessmentServiceI;
@Service("monthAssessmentService")
public class MonthAssessmentServiceImpl extends AbstractService<MonthAssessment, Long> implements MonthAssessmentServiceI{

	@Autowired
	private MonthAssessmentMapper monthAssessmentMapper;
	@Override
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(monthAssessmentMapper);
	}
	@Override
	public List<MonthAssessment> findeByGroupAndMonth(Long groupKey,
			String queryMonth) {
		return monthAssessmentMapper.findeByGroupAndMonth(groupKey,queryMonth);
	}
	@Override
	public List<MonthAssessment> findeByGroupAndMonthAndIndex(Long groupKey,
			String queryMonth, Long indexKey) {
		return monthAssessmentMapper.findeByGroupAndMonthAndIndex(groupKey, queryMonth, indexKey);
	}

}
