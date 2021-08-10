package com.peg.dao.jxmb;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.TempVo;

public interface TempMapper {

	List<TempVo> tempGetMonthPage(BaseSearch bs);
	
	List<TempVo> tempGetMonth(BaseSearch bs);
}
