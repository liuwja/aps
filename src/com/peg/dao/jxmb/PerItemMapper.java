package com.peg.dao.jxmb;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.PerItem;

public interface PerItemMapper extends BaseMapper<PerItem, Long> {
	List<PerItem>getAllItems(@Param("department")String department,@Param("targetclass")String targetclass,
			@Param("indexcontent")String indexcontent);
	
	List<PerItem>getItemByGckey(Long id);
	/*	
	 * 通过类别id删除考核项目
	 * */
	int deleItemsByGckey(Long id);
	
    int insert(PerItem record);

    int insertSelective(PerItem record);

    List<PerItem>getMonthAllByPage(BaseSearch bs);
}