package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ShipTotal;

public interface ShipTotalServiceI extends BaseService<ShipTotal, Long>{
	/**
	 * 分页查询所有
	 * @param shipTotal
	 * @return
	 */
	List<ShipTotal> findAllByPage(ShipTotal shipTotal,PageParameter page);
	/**
	 * 查询所有不分页（导出excel）
	 * @param shipTotal
	 * @return
	 */
	List<ShipTotal> findAll(ShipTotal shipTotal);
	/**
	 * 查询发货总数
	 * @param bs
	 * @return
	 */
	int sumship(ShipTotal shipTotal);

}
