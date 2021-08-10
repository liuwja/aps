package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.DownLineTotal;


public interface DownLineTotalServiceI extends BaseService<DownLineTotal, Long>{
	
	/**
	 * 分页查询所有
	 * @param shipTotal
	 * @return
	 */
	List<DownLineTotal> findAllByPage(DownLineTotal downLineTotal,PageParameter page);
	/**
	 * 查询所有不分页（导出excel）
	 * @param shipTotal
	 * @return
	 */
	List<DownLineTotal> findAll(DownLineTotal downLineTotal);
	/**
	 * 查询发货总数
	 * @param bs
	 * @return
	 */
	int sumDownLine(DownLineTotal downLineTotal);

}
