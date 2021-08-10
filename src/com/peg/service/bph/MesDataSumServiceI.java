package com.peg.service.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.MesDataSum;

public interface MesDataSumServiceI {

	/**
	 * 查询所有数据
	 * @param bs
	 * @return
	 */
	List<MesDataSum> findAllByPage(BaseSearch bs);
}
