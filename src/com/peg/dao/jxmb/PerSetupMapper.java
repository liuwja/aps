package com.peg.dao.jxmb;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.PerSetup;

public interface PerSetupMapper extends BaseMapper<PerSetup, Long> {
	/**
	 * 根据项目Id获取所有的考核指标
	 * @param itemKey
	 * @return
	 */
	List<PerSetup> getIndexByItemKey(@Param("itemKey")Long itemKey);
	
	PerSetup findByIndexCode(@Param("department")String department,@Param("targetclass")String targetclass,
			@Param("indexcontent")String indexcontent,@Param("referencevalue")String referencevalue );
		
	/**
     * 通过类别id删除考核指标
     * @param id
     * @return
     */
	int deleteIndexByGcKey(Long id);
	
    int insert(PerSetup record);

    int insertSelective(PerSetup record);
    List<PerSetup> getMonthAllByPage(BaseSearch bs);
}