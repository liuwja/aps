package com.peg.service.jxmb;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.PerSetup;
import com.peg.service.BaseService;

public interface PerSetupServicel extends BaseService<PerSetup, Long> {
	List<PerSetup>getMonthAllByPage(BaseSearch bs);
}
