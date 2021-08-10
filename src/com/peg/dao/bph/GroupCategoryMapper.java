package com.peg.dao.bph;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.GroupCategory;


public interface GroupCategoryMapper {
	
     int insert(GroupCategory groupCategory);
     
     int deleteByPrimaryKey(Long gcKey);

     /**
      * 按页查询考核项目
      * @param bs
      * @return
      */
     List<GroupCategory> getItemAllByPage(BaseSearch bs);
     
     /**
      * 按业查询考核指标
      * @param bs
      * @return
      */
     List<GroupCategory> getIndexAllByPage(BaseSearch bs);
     
     /**
      * 按页查询月度考核基准
      * @param bs
      * @return
      */
     List<GroupCategory> getMonthAllByPage(BaseSearch bs);
     
     GroupCategory selectByPrimaryKey(Long id);
     /**
      * 从本地查询所有的班组类别
      * @return
      */
     List<GroupCategory> getAllGroupCategory();
     
     /**
      * 从MES系统获取所有班组类别
      * @return
      */
     List<GroupCategory> getAllGroupCategoryFromMes();
     
     /**
      * 按月查询所有月度基准目标
      */
     List<GroupCategory> getMonthAllByMonth(@Param("queryMonth")String queryMonth);
}
