package com.peg.service.impl.bph;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.CountPerformanceMonthMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.CountPerformanceMonth;
import com.peg.service.bph.CountPerformanceMonthServiceI;

@Service("countPerformanceMonthService")
public class CountPerformanceMonthServiceImpl implements CountPerformanceMonthServiceI{

	@Autowired
	private CountPerformanceMonthMapper countPerformanceMonthMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return countPerformanceMonthMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CountPerformanceMonth record) {
		
		return countPerformanceMonthMapper.insert(record);
	}

	@Override
	public int insertSelective(CountPerformanceMonth record) {
		
		return countPerformanceMonthMapper.insertSelective(record);
	}

	@Override
	public CountPerformanceMonth selectByPrimaryKey(Long id) {
		
		return countPerformanceMonthMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CountPerformanceMonth record) {
		
		return countPerformanceMonthMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CountPerformanceMonth record) {
		
		return countPerformanceMonthMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<CountPerformanceMonth> getAllByPage(BaseSearch bs) {
		
		return countPerformanceMonthMapper.getAllByPage(bs);
	}

	@Override
	public List<CountPerformanceMonth> getAllByMonth(BaseSearch bs) {
		
		return countPerformanceMonthMapper.getAllByMonth(bs);
	}

	@Override
	public List<CountPerformanceMonth> selectCommonMonth(
			CountPerformanceMonth ref) {
		
		return countPerformanceMonthMapper.selectCommonMonth(ref);
	}

	@Override
	public int CaculateCountPerformaceMonth(CountPerformanceMonth ref) {
		
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    		String start = ref.getQueryMonth().substring(0, 4);
            Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(start));
			String startTime = DateFormatUtils.format(cal.getTime(), "yyyy-MM");
			ref.setStartTime(startTime);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		try{
			//1.更新开箱不良数
			countPerformanceMonthMapper.updateBoxdefectQtyYAJ(ref);
			countPerformanceMonthMapper.updateBoxdefectQtyXDG(ref);
			countPerformanceMonthMapper.updateBoxdefectQtyZJ(ref);
			countPerformanceMonthMapper.updateBoxdefectQtyRSQ(ref);
			countPerformanceMonthMapper.updateBoxdefectQtyWZK(ref);
			
			//2.跟新OQC不良数
			countPerformanceMonthMapper.updateOqcdefectQtyYYJ(ref);
			countPerformanceMonthMapper.updateOqcdefectQtyXDG(ref);
			countPerformanceMonthMapper.updateOqcdefectQtyZJ(ref);
			countPerformanceMonthMapper.updateOqcdefectQtyRSQ(ref);
			countPerformanceMonthMapper.updateOqcdefectQtyWZK(ref);
			//3.更新实绩值
			countPerformanceMonthMapper.updateAccountActualBox(ref);
			countPerformanceMonthMapper.updateAccountTotalBox(ref);
			countPerformanceMonthMapper.updateAccountActualOqc(ref);
			countPerformanceMonthMapper.updateAccountTotalOqc(ref);
			
			//跟新合格率
			countPerformanceMonthMapper.updatePaintingQty(ref);        //更新喷涂一次合格率（不良数，总数）
			countPerformanceMonthMapper.updateAssembleDefectQty(ref);  //更新组装一次合格率（不合格数）
			countPerformanceMonthMapper.updateAssembleTotalQty(ref);   //更新组装一次合格率（总数）
			
			//更新实绩合格率
			countPerformanceMonthMapper.updateAccountActualFitRate(ref);
			//跟新累计合格率
			countPerformanceMonthMapper.updateAccountTotalFitRate(ref);
		}catch (Exception e){
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

}
