package com.peg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.FaultReason;
import com.peg.model.MarketRepairTotal;

public interface MarketRepairTotalMapper extends BaseMapper<MarketRepairTotal, Long>{
	List<MarketRepairTotal> findRepairTotalByPage(@Param("total")MarketRepairTotal total,@Param("faultReason")FaultReason faultReason ,@Param("page")PageParameter page); 
	List<MarketRepairTotal> findAllRepairTotal(@Param("total")MarketRepairTotal total,@Param("faultReason")FaultReason faultReason); 
	int sumRepair(@Param("total")MarketRepairTotal total,@Param("faultReason")FaultReason faultReason);
}