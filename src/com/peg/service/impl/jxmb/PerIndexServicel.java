package com.peg.service.impl.jxmb;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.peg.model.jxmb.PerIndex;
import com.peg.service.BaseService;

public interface PerIndexServicel extends BaseService<PerIndex, Long> {

	List<PerIndex> getIndexByItemKey(@Param("itemKey")Long itemKey);

	PerIndex findByIndexCode(@Param("department") String factory,@Param("area") String area,
			@Param("category")String category,@Param("indexCode") String indexCode);
}
