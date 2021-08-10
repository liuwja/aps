package com.peg.dao.bph;

import java.util.List;

import com.peg.model.bph.ItemScore;
import com.peg.model.bph.ItemScoreKey;

/**
 * 
 * 扁平化绩效-项目得分数据库操作类
 * <p>
 * @author Lin, 2015-7-24 上午10:25:56
 */
public interface ItemScoreMapper {
    int deleteByPrimaryKey(ItemScoreKey key);

    int insert(ItemScore record);
    
    int insertList(List<ItemScore> recordList);

    int insertSelective(ItemScore record);

    ItemScore selectByPrimaryKey(ItemScoreKey key);

    int updateByPrimaryKeySelective(ItemScore record);

    int updateByPrimaryKey(ItemScore record);
    
    /**
     * 获取班组绩效得分月
     */
    List<ItemScore> sumItemScore(ItemScore record);
    /**
     * 获取班组绩效得分月(跟进班组)
     */
    List<ItemScore> sumItemScoreByGroup(ItemScore record);
    
}