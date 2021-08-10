package com.peg.service.jxmb;

import java.util.List;
import java.util.Map;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.PerGroup;
import com.peg.model.jxmb.PerItem;


public interface PerGoupServicel {
	int insert(PerGroup perfGroup);
	int delete(Long id);
	List<PerGroup>getItemAllByPage(BaseSearch bs);
	List<PerGroup>getIndexAllByPage(BaseSearch bs);
	List<PerGroup>getMonthAllByPage(BaseSearch bs);
	PerGroup selectByPrimaryKey(Long id);
	/**
     * 从本地查询PerGroup所有的类别
     * @return
     */
	List<PerGroup>getAllGroupCategory();
	
	List<PerGroup>getAllGroupCategoryFromMes();
	
	//查找指标公式
	Map<String ,String>getIndexDescription();
	
	List<PerItem> getAllBypage(BaseSearch bs);
	
}
