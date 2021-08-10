package com.peg.service.part;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.part.NewPartRef;
import com.peg.service.BaseService;

public interface NewPartRefServiceI extends BaseService<NewPartRef, Long>{

	List<NewPartRef> findAllByPage(BaseSearch bs);
}
