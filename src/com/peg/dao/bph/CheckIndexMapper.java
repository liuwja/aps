package com.peg.dao.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.CheckIndex;

public interface CheckIndexMapper {

	int deleteByPrimaryKey(Long id);

    int insert(CheckIndex record);

    int insertSelective(CheckIndex record);
    
	List<CheckIndex> getAllByPage(BaseSearch bs);

	CheckIndex selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CheckIndex record);

    int updateByPrimaryKey(CheckIndex record);

    List<CheckIndex> getItem(BaseSearch bs);
    
    /**
     * 获取指标（根据工厂，车间，班组类别,是否关键值）
     * @method: getCheckIndex() -by fjt
     * @TODO:  
     */
    List<CheckIndex> getCheckIndex(BaseSearch bs);
    
    /**
     * 获取所有指标
     * @method: getAllIndex() -by fjt
     * @TODO:  
     * @return List<CheckIndex>
     */
    List<CheckIndex> getAllIndex();
    
    /**
     * 获取指标（根据工厂，车间，班组类别）
     * @method: getCheckIndexList() -by fjt
     * @TODO:  
     */
    List<CheckIndex> getCheckIndexList(BaseSearch bs);
    
    //根据工厂车间获取班组类别
    List<CheckIndex> getCategory(CheckIndex index);
}
