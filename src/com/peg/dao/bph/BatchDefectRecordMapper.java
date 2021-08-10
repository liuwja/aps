package com.peg.dao.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.BatchDefectRecord;


public interface BatchDefectRecordMapper {

	List<BatchDefectRecord> getBatchDefectAllByPage(BaseSearch bs);

	List<BatchDefectRecord> getBatchDefectList(
			BatchDefectRecord getBatchDefectList);
}