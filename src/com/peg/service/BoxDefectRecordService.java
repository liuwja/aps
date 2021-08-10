package com.peg.service;

import java.util.List;
import java.util.Map;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.BoxdefectrecordNew;
import com.peg.model.CommonVo;
import com.peg.model.bph.BoxDefectRecord;
import com.peg.model.system.SysMesUser;

public interface BoxDefectRecordService {

	/**
	 * 分页查询所有信息
	 * @param boxDefectRecord
	 * @param page
	 * @return
	 */
	List<BoxdefectrecordNew> list(CommonVo vo,
			PageParameter page);

	/**
	 * 查询一条信息
	 * @param boxDefectRecord
	 * @return
	 */
	BoxdefectrecordNew findOne(BoxdefectrecordNew boxDefectRecord);

	void saveDuty(BoxdefectrecordNew boxDefectRecord, SysMesUser user);

	/**
	 * 趋势图查询指定时间区间内的总数
	 * @param boxDefectRecord
	 * @return
	 */
	List<BoxDefectRecord> findAll(BoxDefectRecord boxDefectRecord);

}
