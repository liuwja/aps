package com.peg.dao.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.QualityImprovementRfp;

public interface QualityImprovementRfpMapper {

	List<QualityImprovementRfp> getQualityAllByPage(BaseSearch bs);

	List<QualityImprovementRfp> getQualityImp(
			QualityImprovementRfp qualityImprovementRfp);
}