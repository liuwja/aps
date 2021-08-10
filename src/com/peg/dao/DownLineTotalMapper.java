package com.peg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.DownLineTotal;

public interface DownLineTotalMapper extends BaseMapper<DownLineTotal, Long> {
	// 分页查询所有
	List<DownLineTotal> findAllByPage(@Param("downLine") DownLineTotal downLine,@Param("page") PageParameter page);
	// 不分页查询所有
	List<DownLineTotal> findAll(@Param("downLine") DownLineTotal downLine);
	// 下线总数
	int sumDownLine(@Param("downLine") DownLineTotal downLine);

}