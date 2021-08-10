package com.peg.service.jxmb;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.model.jxmb.PerMonthly;
import com.peg.service.BaseService;

public interface MonthlyServicel  extends BaseService<PerMonthly, Long> {

	List<PerMonthly>selectByGroupAndMonth(@Param("monthKey")Long monthKey);
}
