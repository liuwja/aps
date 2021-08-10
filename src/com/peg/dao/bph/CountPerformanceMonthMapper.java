package com.peg.dao.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.CountPerformanceMonth;

public interface CountPerformanceMonthMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CountPerformanceMonth record);

    int insertSelective(CountPerformanceMonth record);

    CountPerformanceMonth selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CountPerformanceMonth record);

    int updateByPrimaryKey(CountPerformanceMonth record);
    
    List<CountPerformanceMonth> getAllByPage(BaseSearch bs);
    
    List<CountPerformanceMonth> getAllByMonth(BaseSearch bs);
    
    //插入基础数据
    int insertBaseData(CountPerformanceMonth record);
    
    //删除基础数据
    int deleteByMonth(CountPerformanceMonth record);
    
    //跟新油烟机开箱不良
    int updateBoxdefectQtyYAJ(CountPerformanceMonth record);
    
    //跟新消毒柜开箱不良
    int updateBoxdefectQtyXDG(CountPerformanceMonth record);
    
    //跟新灶具开箱不良
    int updateBoxdefectQtyZJ(CountPerformanceMonth record);
    
    //跟新热水器开箱不良
    int updateBoxdefectQtyRSQ(CountPerformanceMonth record);
    
    //更新微蒸烤开箱不良
    int updateBoxdefectQtyWZK(CountPerformanceMonth record);
    
    //更新开箱不良实绩值
    int updateAccountActualBox(CountPerformanceMonth record);
   //更新开箱不良累计值
    int updateAccountTotalBox(CountPerformanceMonth record);
    
    
    
    //跟新油烟机OQC不良
    int updateOqcdefectQtyYYJ(CountPerformanceMonth record);
    
    //跟新消毒柜OQC不良
    int updateOqcdefectQtyXDG(CountPerformanceMonth record);
    
    //跟新灶具OQC不良
    int updateOqcdefectQtyZJ(CountPerformanceMonth record);
    
    //跟新热水器OQC不良
    int updateOqcdefectQtyRSQ(CountPerformanceMonth record);
    
    //更新微蒸烤OQC不良
    int updateOqcdefectQtyWZK(CountPerformanceMonth record);
    
    //更新OQC不良实际值
    int updateAccountActualOqc(CountPerformanceMonth record);
    //更新OQC不良累计值
    int updateAccountTotalOqc(CountPerformanceMonth record);
    
    
    //更新喷涂一次合格率（不良数，总数）
    int updatePaintingQty(CountPerformanceMonth record);
    
    //更新组装一次合格率（不合格数）
    int updateAssembleDefectQty(CountPerformanceMonth record);
    
    //更新组装一次合格率（总数）
    int updateAssembleTotalQty(CountPerformanceMonth record);
    
    
    //更新实际值合格率
    int updateAccountActualFitRate(CountPerformanceMonth record);
    //更新累计值合格率
    int updateAccountTotalFitRate(CountPerformanceMonth record);
    
    //查询是否存在相同的月份
	List<CountPerformanceMonth> selectCommonMonth(CountPerformanceMonth ref);
	
	
    
}