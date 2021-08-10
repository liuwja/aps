package com.peg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ProductInstall;

public interface ProductInstallMapper {
	List<ProductInstall> findAllByPage(@Param("ins")ProductInstall proInstall,@Param("page")PageParameter page);
	
	List<ProductInstall> findRecordAllByPage(@Param("ins")ProductInstall proInstall,@Param("page")PageParameter page);
}