package com.peg.service.jxmb;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.PerSetup;
import com.peg.service.BaseService;

public interface PerIndexServicel extends BaseService<PerSetup, Long> {
	List<PerSetup>getIndexByItemKey(@Param("itemKey")Long itemKey);
	
	PerSetup findByIndexCode(@Param("department")String department,@Param("targetclass")String targetclass,
			@Param("indexcontent")String indexcontent,@Param("referencevalue")String referencevalue );
	
	List<PerSetup>getMonthAllByPage(BaseSearch bs);
}
