package com.peg.service.bph;

import java.util.List;
import java.util.Map;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.GroupCategory;

public interface GroupCategorySerivceI {

     int insert(GroupCategory groupCategory);
     
     int delete(Long gcKey);

     List<GroupCategory> getItemAllByPage(BaseSearch bs);
     
     List<GroupCategory> getIndexAllByPage(BaseSearch bs);
     
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
     
     //查找指标得分公式
     Map<String,String> getIndexDescription();
}
