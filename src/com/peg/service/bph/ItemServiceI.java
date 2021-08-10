package com.peg.service.bph;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.model.bph.Item;
import com.peg.service.BaseService;

public interface ItemServiceI extends BaseService<Item, Long> {

	 List<Item> getAllItems(@Param("factory") String factory,@Param("area") String area,
			   @Param("category") String category);
	 
	 List<Item> getItemByGckey(Long gcKey);
}
