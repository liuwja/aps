package com.peg.dao.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.OqcCheck;

public interface OqcCheckMapper {

	List<OqcCheck> getOqcCheckAllByPage(BaseSearch bs);

	List<OqcCheck> getOqcCheck(OqcCheck oqcCheck);
}