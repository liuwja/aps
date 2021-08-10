package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ReworkSheet;
import com.peg.service.BaseService;

public interface ReworkSheetServicel  extends BaseService<ReworkSheet, Long>{

	List<ReworkSheet>getAllByPage(ReworkSheet reworkSheet,PageParameter page);
	ReworkSheet getByid(ReworkSheet reworkSheet);
	ReworkSheet getByReworkNumber(ReworkSheet reworkSheet);
	int insertSelective(ReworkSheet reworkSheet);
	int updateByIdSelective(ReworkSheet reworkSheet);
	int deleteById(ReworkSheet reworkSheet);
}
