package com.peg.dao.jxmb;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.model.jxmb.PerIndex;

public interface PerIndexMapper extends BaseMapper<PerIndex, Long>{
	/**
	 * 根据项目Id获取所有的考核指标
	 * @param itemKey
	 * @return
	 */
	List<PerIndex> getIndexByItemKey(@Param("itemKey")Long itemKey);
	
	/**
	 * 根据指标代码查询考核指标
	 * @param indexCode
	 * @return
	 */
	PerIndex findByIndexCode(@Param("department") String department,@Param("area") String area,
			@Param("category")String category,@Param("indexCode") String indexCode);
    
    /**
     * 通过班组类别key删除考核指标
     * @param gcKey
     * @return
     */
    int deleteIndexByGcKey(Long gcKey);

}