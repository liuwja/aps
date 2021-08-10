package com.peg.service;

import java.util.List;
import java.util.Map;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CommonVo;
import com.peg.model.FaultType;
import com.peg.model.VoiceCategory;

public interface VoiceCategoryServiceI {

	Integer findAll(CommonVo vo);

	Map<String,Object> updateVoiceCategory(CommonVo vo);

	List<VoiceCategory> findAllByPage(CommonVo vo,PageParameter page);

	List<VoiceCategory> findAllByPage(VoiceCategory voice, PageParameter page);

	List<VoiceCategory> findAllPater(VoiceCategory voice);

	VoiceCategory getVoiceCategorybykey(Long u);

	List<VoiceCategory> findAll(VoiceCategory voice);

}
