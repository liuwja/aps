package com.peg.dao.bph;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.model.bph.Index;


public interface IndexMapper extends BaseMapper<Index, Long>{

	/**
	 * 根据项目Id获取所有的考核指标
	 * @param itemKey
	 * @return
	 */
	List<Index> getIndexByItemKey(@Param("itemKey")Long itemKey);
	
	/**
	 * 根据指标代码查询考核指标
	 * @param indexCode
	 * @return
	 */
	Index findByIndexCode(@Param("factory") String factory,@Param("area") String area,
			@Param("category")String category,@Param("indexCode") String indexCode);
    
    /**
     * 通过班组类别key删除考核指标
     * @param gcKey
     * @return
     */
    int deleteIndexByGcKey(Long gcKey);

}
