package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ProductInstall;

public interface ProductInstallServiceI {
	List<ProductInstall> findAllByPage(ProductInstall proInstall,PageParameter page);
	
	List<ProductInstall> findRecordAllByPage(ProductInstall proInstall,PageParameter page);
}
