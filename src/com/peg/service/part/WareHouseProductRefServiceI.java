package com.peg.service.part;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.part.WareHouseProductRef;
import com.peg.service.BaseService;

public interface WareHouseProductRefServiceI extends BaseService<WareHouseProductRef, Long>{
   
	List<WareHouseProductRef> findAllByPage(BaseSearch bs);
}
