package com.peg.dao.bph;

import java.util.List;
import java.util.Map;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.BoxdefectrecordNew;
import com.peg.model.bph.BoxDefectRecord;

public interface BoxDefectRecordMapper {

	List<BoxDefectRecord> getBoxDefectAllByPage(BaseSearch bs);

	List<BoxDefectRecord> getBoxDefect(BoxDefectRecord boxDefectRecord);

	BoxdefectrecordNew findOne(BoxdefectrecordNew boxDefectRecord);

	void saveDuty(BoxdefectrecordNew boxDefectRecord);

	List<BoxDefectRecord> findAll(BoxDefectRecord boxDefectRecord);

	List<BoxdefectrecordNew> getNewBoxDefectAllByPage(BaseSearch bs);
}