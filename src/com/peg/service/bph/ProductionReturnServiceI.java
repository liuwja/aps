package com.peg.service.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.ProductionReturn;

public interface ProductionReturnServiceI {
	
	int deleteByPrimaryKey(Long id);

    int insert(ProductionReturn record);

    int insertSelective(ProductionReturn record);

    ProductionReturn selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductionReturn record);

    int updateByPrimaryKey(ProductionReturn record);
    
	List<ProductionReturn> getAllByPage(BaseSearch bs);

}
