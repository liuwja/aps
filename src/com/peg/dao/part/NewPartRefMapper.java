package com.peg.dao.part;

import java.util.List;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.part.NewPartRef;

public interface NewPartRefMapper extends BaseMapper<NewPartRef, Long>{
  
	List<NewPartRef> findAllByPage(BaseSearch bs);
}