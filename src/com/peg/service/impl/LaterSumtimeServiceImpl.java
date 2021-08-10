package com.peg.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.LaterSumtimeMapper;
import com.peg.model.LaterSumtime;
import com.peg.service.AbstractService;
import com.peg.service.LaterSumtimeServiceI;

@Service("laterSumtimeService")
public class LaterSumtimeServiceImpl extends AbstractService<LaterSumtime, Long> implements LaterSumtimeServiceI{

	@Autowired
	private LaterSumtimeMapper laterSumtimeMapper;
	@Override
	public void setBaseMapper() {
		super.setBaseMapper(laterSumtimeMapper);
	}
	@Override
	public LaterSumtime getLaterDate() {
		return laterSumtimeMapper.getLaterDate();
	}
	@Override
	public void updateToLater(String sumMonth) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String endDate = DateFormatUtils.format(cal.getTime(), "yyyy-MM");
		LaterSumtime laterTime = laterSumtimeMapper.getLaterDate();
		//保存最新日期必须：小于当前月份&&大于数据库保存月份
		if(laterTime==null){
			LaterSumtime sumtime = new LaterSumtime();
			sumtime.setSumMonth(sumMonth);
			sumtime.setCreateTime(new Date());
			laterSumtimeMapper.insert(sumtime);
		}else if(StringUtils.isNotEmpty(sumMonth) && sumMonth.compareTo(endDate)<=0 && sumMonth.compareTo(laterTime.getSumMonth())>0){
			laterTime.setSumMonth(sumMonth);
			laterTime.setCreateTime(new Date());
			laterSumtimeMapper.updateByPrimaryKeySelective(laterTime);
		}
	}

}
