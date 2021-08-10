package com.peg.dao.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.FormerProcessFqcCheck;


public interface FormerProcessFqcCheckMapper {

	List<FormerProcessFqcCheck> getFormerProcessAllByPage(BaseSearch bs);

	List<FormerProcessFqcCheck> getFqcCheckList(
			FormerProcessFqcCheck formerProcessFqcCheck);
}