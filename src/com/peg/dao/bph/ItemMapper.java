package com.peg.dao.bph;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.model.bph.Item;


public interface ItemMapper extends BaseMapper<Item, Long>{
	
   List<Item> getAllItems(@Param("factory") String factory,@Param("area") String area,
		   @Param("category") String category);
   
   List<Item> getItemByGckey(Long gcKey);
   /*
    * 通过班组类别key删除考核项目
    */
   int deleItemsByGckey(Long gcKey);
   

}
