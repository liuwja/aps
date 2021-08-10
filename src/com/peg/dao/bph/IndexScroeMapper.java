package com.peg.dao.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.IndexScroe;
import com.peg.model.bph.IndexScroeKey;

/**
 * 
 * 扁平化绩效-指标得分数据库操作类
 * <p>
 * @author Lin, 2015-7-24 上午10:25:27
 */
public interface IndexScroeMapper {
    int deleteByPrimaryKey(IndexScroeKey key);

    int insert(IndexScroe record);
    
    int insertList(List<IndexScroe> record);
    
    int insertDayList(List<IndexScroe> record);

    int insertSelective(IndexScroe record);

    IndexScroe selectByPrimaryKey(IndexScroeKey key);

    int updateByPrimaryKeySelective(IndexScroe record);

    int updateByPrimaryKey(IndexScroe record);
    /**
     * 查询指标得分
     * @method: getIndexScore() -by fjt
     */
    List<IndexScroe> getIndexScoreAllByPage(BaseSearch bs);
    
    /**
     * 查询指标得分月
     * @method: getIndexScoreListByMonth() -by fjt
     */
    List<IndexScroe> getIndexScoreMonthListByPage(BaseSearch bs);
    
    /**
     * 查询指标得分总分
     * @method: getSumIndexScore() -by fjt
     * @TODO:  
     */
    List<IndexScroe> getSumIndexScore(BaseSearch bs);
    /**
     * 查询指标得分分月查询
     * @method: getIndexScoreByMonth() -by fjt
     * @TODO:  
     */
    List<IndexScroe> getIndexScoreByMonth(BaseSearch bs);
    
    /**
     * 从月度考核指标设定获取月度指标基准
     */
    List<IndexScroe> getMonthIndexScoreListByPage(BaseSearch bs);
}