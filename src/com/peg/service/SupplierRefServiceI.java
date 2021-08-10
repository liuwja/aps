package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.SupplierRef;

public interface SupplierRefServiceI {

	List<SupplierRef> findPage(BaseSearch bs);
	
	List<SupplierRef> findAll(SupplierRef vo);
	
	int insert(SupplierRef vo);
	
	int update(SupplierRef vo);
	
	int delete(SupplierRef vo);
	
	SupplierRef selectByPrimaryKey(Long id);
	
	void insert(List<String[]> list);
	
	List<SupplierRef> findSupplierPartPage(BaseSearch bs);
	
	List<SupplierRef> findSupplierPartAll(SupplierRef vo);
	
	int insertSupplierPart(SupplierRef vo);
	
	int updateSupplierPart(SupplierRef vo);
	
	int deleteSupplierPart(SupplierRef vo);
	
	SupplierRef selectSupplierPartByPrimaryKey(Long id);
}
