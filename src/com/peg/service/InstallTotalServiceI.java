package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CommonVo;
import com.peg.model.InstallTotal;

public interface InstallTotalServiceI extends BaseService<InstallTotal, Long>{
	
	/**
	 * 分页查询所有
	 * @param shipTotal
	 * @return
	 */
	List<InstallTotal> findAllRecord(CommonVo comVo,PageParameter page);
	/**
	 * 查询所有不分页（导出excel）
	 * @param shipTotal
	 * @return
	 */
	List<InstallTotal> findAll(CommonVo comVo);
	/**
	 * 查询安装总数
	 * @param bs
	 * @return
	 */
	int sumInstall(CommonVo comVo);
	
	int sumInsRepire(CommonVo comVo);
	
	int getTotalNumber(CommonVo comVo);
	


}
