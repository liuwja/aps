package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.ProductInstallMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ProductInstall;
import com.peg.service.ProductInstallServiceI;

@Service("productInstallService")
public class ProductInstallServiceImpl implements ProductInstallServiceI {

	@Autowired
	private ProductInstallMapper productInstallMapper;
	@Override
	public List<ProductInstall> findAllByPage(ProductInstall proInstall,PageParameter page) {
		// TODO Auto-generated method stub
		List<ProductInstall> list = productInstallMapper.findAllByPage(proInstall, page);
		return list;
	}
	
	@Override
	public List<ProductInstall> findRecordAllByPage(ProductInstall proInstall,PageParameter page) {
		return productInstallMapper.findRecordAllByPage(proInstall, page);
	}
}
