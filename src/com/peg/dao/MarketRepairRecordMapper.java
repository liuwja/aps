package com.peg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.MarketRepairRecord;

public interface MarketRepairRecordMapper{
    int deleteByPrimaryKey(Long id);

    int insert(MarketRepairRecord record);

    int insertSelective(MarketRepairRecord record);

    MarketRepairRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MarketRepairRecord record);

    int updateByPrimaryKey(MarketRepairRecord record);

	//所有市场维修记录
	List<MarketRepairRecord>  findAllRepair(@Param("page")PageParameter page,@Param("repair")MarketRepairRecord repair,
			@Param("start")long start, @Param("end")long end);
	
	List<MarketRepairRecord> findAll(@Param("repair")MarketRepairRecord repair);
	//根据条件查找总数
	int getTotalNumber(@Param("repair")MarketRepairRecord repair);
	
	List<MarketRepairRecord> findAllRepairIntermediate(MarketRepairRecord repair);
	
	List<MarketRepairRecord> findAllRepairIntermediatePage(@Param("page")PageParameter page, @Param("repair")MarketRepairRecord repair);
}