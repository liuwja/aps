package com.peg.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.system.EditReason;

/**
 * 绩效管理修改日志
 * @author xuanm
 *
 */
public interface EditReasonMapper extends BaseMapper<EditReason, Long>{
	
	/**
	 * 获取所有记录
	 * @return 绩效修改记录
	 */
	List<EditReason> getAll();
	/**
	 * 以分页的形式获取所有记录
	 * @param page 分页信息
	 * @param log 记录
	 * @return 绩效修改记录
	 */
    List<EditReason> findAllByPage(@Param("page")PageParameter page,@Param("log")EditReason log);
    
    /**
     * 以分页的形式获取所有记录
     * @param bs 分页信息和查询条件
     * @return 绩效修改日志记录
     */
    List<EditReason>getAllByPage(BaseSearch bs);
    
    /**
     * 绩效管理修改记录插入
     */
    @Override
    int insertSelective(EditReason editReason);
        
}
