package com.peg.service.jxmb;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.PerItem;
import com.peg.service.BaseService;

public interface PerItemServicel extends BaseService<PerItem, Long> {

	List<PerItem> getAllItems(@Param("department")String department,@Param("targetclass")String targetclass,
			@Param("indexcontent")String indexcontent);
	
	List<PerItem>getItemByGcKey(Long id);
	
	/**
	 * @param bs
	 * @param isPage
	 * @return
	 */
	List<PerItem>getMonthAllByPage(BaseSearch bs, boolean isPage);
}
