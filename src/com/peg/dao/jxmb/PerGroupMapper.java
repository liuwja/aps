package com.peg.dao.jxmb;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.PerGroup;

public interface PerGroupMapper {
    int insert(PerGroup record);

    int insertSelective(PerGroup record);
    
    int deleteByPrimaryKey(Long id);
    
    /**
     * 按页查询考核项目
     * @param bs
     * @return
     */
    List<PerGroup>getItemAllByPage(BaseSearch bs);
    
    List<PerGroup>getIndexAllByPage(BaseSearch bs);
    
    List<PerGroup> getMonthAllByPage(BaseSearch bs);
    PerGroup selectByPrimaryKey(Long id);
    List<PerGroup> getAllGroup();
    List<PerGroup>getAallGroupCategoryFromMes();
    List<PerGroup> getMonthAllByMonth(@Param("queryMonth")String queryMonth);
  
}