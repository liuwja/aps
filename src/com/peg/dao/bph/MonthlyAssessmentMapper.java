package com.peg.dao.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.MonthlyAssessment;

public interface MonthlyAssessmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MonthlyAssessment record);

    int insertSelective(MonthlyAssessment record);

    MonthlyAssessment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonthlyAssessment record);

    int updateByPrimaryKey(MonthlyAssessment record);
    
	List<MonthlyAssessment> getAllByPage(BaseSearch bs);
	
	List<MonthlyAssessment> getAssessment(BaseSearch bs);
	
	List<MonthlyAssessment> getIndex(BaseSearch bs);
	
	List<MonthlyAssessment> getScale(BaseSearch bs);
	
	List<MonthlyAssessment> getkey(BaseSearch bs);
	
	List<MonthlyAssessment> getAllMonthAssement(String month);
	
	List<MonthlyAssessment> getIndexCode(MonthlyAssessment month);
	
	List<MonthlyAssessment> fingMonthIndex(String month, String factory, String group, String indexCode);
	
	int deleteMonthIndex(String month, String factory, String group, String indexCode);
	//查找项目Id
	List<MonthlyAssessment> findItemId(String factory, String category, String itemCode);  
	//查找指标Id
	List<MonthlyAssessment> findIndexId(String factory, String category, String indexCode);
	
	//查找班组（根据月份，工厂）
	List<MonthlyAssessment> getMonthAssemByFacMon(String factory, String month);
	
	//查找班组（根据月份，工厂,车间）
    List<MonthlyAssessment> getMonthAssemByFacAreaMon(String factory,String area, String month);
    
    List<MonthlyAssessment> getMonthAssemByExcel(MonthlyAssessment month);
    
    //查找单个班组设置了指标的月份
    List<MonthlyAssessment> selectGroupIndexByPeriod(String group, String startTime, String endTime);
}