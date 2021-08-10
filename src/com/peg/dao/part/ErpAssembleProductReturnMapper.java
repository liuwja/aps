package com.peg.dao.part;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.model.part.ErpAssembleProductReturn;
/**
 *  ERP组装生产退次数
 * @author Administrator
 *
 */
public interface ErpAssembleProductReturnMapper extends BaseMapper<ErpAssembleProductReturn, Long>{

	int deleteByPeriod(@Param("startTime")String startTime, @Param("endTime")String endTime);

	/**
	 * 更新新旧供应商，物料
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int updateSupPartByPeriod(@Param("startTime")String startTime, @Param("endTime")String endTime);
   
}