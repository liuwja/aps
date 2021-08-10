package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.SupplierRefMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.SupplierRef;
import com.peg.service.SupplierRefServiceI;

@Service("SupplierRefService")
public class SupplierRefServiceImpl implements SupplierRefServiceI {

	@Autowired
	private SupplierRefMapper supplierRefMapper;
	
	@Override
	public List<SupplierRef> findPage(BaseSearch bs) {
		return supplierRefMapper.findPage(bs);
	}

	@Override
	public List<SupplierRef> findAll(SupplierRef vo) {
		return supplierRefMapper.findAll(vo);
	}

	@Override
	public int insert(SupplierRef vo) {
		return supplierRefMapper.insert(vo);
	}

	@Override
	public int update(SupplierRef vo) {
		return supplierRefMapper.update(vo);
	}

	@Override
	public int delete(SupplierRef vo) {
		return supplierRefMapper.delete(vo);
	}
	
	@Override
	public SupplierRef selectByPrimaryKey(Long id) {
		return supplierRefMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public void insert(List<String[]> list) {
		for(String[] arr : list) {
			SupplierRef supplierRef = new SupplierRef();
			supplierRef.setSupplierNumber(arr[0]);
			supplierRef.setSupplierNumberN(arr[2]);
			supplierRef.setSupplierName(arr[1]);
			supplierRef.setSupplierNameN(arr[3]);
//			supplierRef.setSupplierShortName(arr[4]);
			insert(supplierRef);
		}
	}
	
	@Override
	public List<SupplierRef> findSupplierPartPage(BaseSearch bs) {
		return supplierRefMapper.findSupplierPartPage(bs);
	}
	
	@Override
	public List<SupplierRef> findSupplierPartAll(SupplierRef vo) {
		return supplierRefMapper.findSupplierPartAll(vo);
	}
	
	@Override
	public int insertSupplierPart(SupplierRef vo) {
		return supplierRefMapper.insertSupplierPart(vo);
	}
	
	@Override
	public int updateSupplierPart(SupplierRef vo) {
		return supplierRefMapper.updateSupplierPart(vo);
	}
	
	@Override
	public int deleteSupplierPart(SupplierRef vo) {
		return supplierRefMapper.deleteSupplierPart(vo);
	}
	
	@Override
	public SupplierRef selectSupplierPartByPrimaryKey(Long id) {
		return supplierRefMapper.selectSupplierPartByPrimaryKey(id);
	}
}
