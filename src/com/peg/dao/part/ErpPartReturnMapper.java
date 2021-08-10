package com.peg.dao.part;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.part.ErpPartReturn;
/**
 *  ERP原料退料数
 * @author Administrator
 *
 */
public interface ErpPartReturnMapper extends BaseMapper<ErpPartReturn, Long>{
	/**
	 * 分页查询
	 * @param bs
	 * @return
	 */
	List<ErpPartReturn> findAllByPage(BaseSearch bs);
	
	int deleteByPeriod(@Param("startTime")String startTime,@Param("endTime")String endTime);

	/**
	 * 刷新旧物料，供应商
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int updateSupPartByPeriod(@Param("startTime")String startTime,@Param("endTime")String endTime);
}