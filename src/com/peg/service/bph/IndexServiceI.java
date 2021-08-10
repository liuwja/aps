package com.peg.service.bph;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.model.bph.Index;
import com.peg.service.BaseService;

public interface IndexServiceI extends BaseService<Index, Long>{

	List<Index> getIndexByItemKey(@Param("itemKey")Long itemKey);

	Index findByIndexCode(@Param("factory") String factory,@Param("area") String area,
			@Param("category")String category,@Param("indexCode") String indexCode);
}
