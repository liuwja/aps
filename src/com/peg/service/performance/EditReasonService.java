package com.peg.service.performance;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.performance.EditReason;
import com.peg.service.BaseService;

/**
 * 指标修改日志操作接口
 * @author xuanm
 *
 */
public interface EditReasonService extends BaseService<EditReason, Long> {
	
	/**
	 * 获取所有绩效指标修改记录
	 * @param bs
	 * @return
	 */
    List<EditReason> getAllByPage(BaseSearch bs);
    
    /**
     * 插入一条绩效指标修改记录（全部字段）
     */
    @Override
    int insert(EditReason record);
    
    /**
     * 插入一条绩效指标修改记录（可选字段）
     */
    @Override
    int insertSelective(EditReason record);
}
