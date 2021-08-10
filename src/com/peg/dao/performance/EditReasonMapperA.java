package com.peg.dao.performance;

import java.util.List;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.performance.EditReason;

/**
 * 绩效指标修改日志
 * @author xuanm
 *
 */
public interface EditReasonMapperA extends BaseMapper<EditReason, Long>{
	
	/**
	 * 获取所有绩效指标修改记录
	 * @param bs 查询条件
	 * @return 绩效指标修改记录集合列表
	 */
    List<EditReason> getAllByPage(BaseSearch bs);
    
    /**
     * 插入一条绩效指标修改记录(全部字段)
     */
    @Override
    int insert(EditReason record);
    
    /**
     * 插入一条绩效指标修改记录(可选字段)
     */
    @Override
    int insertSelective(EditReason record);
}
