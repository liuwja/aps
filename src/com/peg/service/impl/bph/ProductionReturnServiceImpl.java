package com.peg.service.impl.bph;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.ProductionReturnMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.ProductionReturn;
import com.peg.service.bph.ProductionReturnServiceI;

@Service("ProductionReturnService")
public class ProductionReturnServiceImpl implements ProductionReturnServiceI{

	@Autowired
	private ProductionReturnMapper productionReturnMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return productionReturnMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<ProductionReturn> getAllByPage(BaseSearch bs) {

		return productionReturnMapper.getAllByPage(bs);
	}

	@Override
	public int insert(ProductionReturn record) {
        productionReturnMapper.insert(record);
		return 0;
	}

	@Override
	public int insertSelective(ProductionReturn record) {
		productionReturnMapper.insertSelective(record);
		return 0;
	}

	@Override
	public ProductionReturn selectByPrimaryKey(Long id) {
		
		return productionReturnMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(ProductionReturn record) {
		
		return productionReturnMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ProductionReturn record) {

		return productionReturnMapper.updateByPrimaryKeySelective(record);
	}

}
