package com.peg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CommonVo;
import com.peg.model.InstallTotal;

public interface InstallTotalMapper extends BaseMapper<InstallTotal, Long>{
	/**
	 * 分页查询所有
	 */
	List<InstallTotal> findAllRecord(@Param("comVo") CommonVo comVo,
			@Param("page") PageParameter page,@Param("start")long start, @Param("end")long end);
	/**
	 * 查询所有不分页（导出excel）
	 */
	List<InstallTotal> findAll(@Param("comVo") CommonVo comVo);
	
	int getTotalNumber(@Param("comVo") CommonVo comVo);
	/**
	 * 查询安装总数
	 */
	int sumInstall(@Param("comVo") CommonVo comVo);
	
	int sumInsRepire(@Param("comVo") CommonVo comVo);
}
