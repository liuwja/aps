package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ClaimsSheet;
import com.peg.model.ReworkSheet;
import com.peg.service.BaseService;

public interface ClaimsSheetServicel  extends BaseService<ClaimsSheet, Long>{

	List<ClaimsSheet>getAllByPage(ClaimsSheet claimsSheet,PageParameter page);
	ClaimsSheet getByid(ClaimsSheet claimsSheet);
	ClaimsSheet getByClaimsNumber(ClaimsSheet claimsSheet);
	List<ClaimsSheet> getByReworkId(ReworkSheet reworkSheet);
	int insertSelective(ClaimsSheet claimsSheet);
	int updateByIdSelective(ClaimsSheet claimsSheet);
	int deleteById(ClaimsSheet claimsSheet);
}
