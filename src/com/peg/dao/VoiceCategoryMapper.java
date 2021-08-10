package com.peg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CommonVo;
import com.peg.model.FaultType;
import com.peg.model.VoiceCategory;

public interface VoiceCategoryMapper {

	Integer findAllCount();

	List<VoiceCategory> findNew();

	void updateTime(VoiceCategory v);

	void add(VoiceCategory v);

	void update(List<VoiceCategory> list);

	void delete(List<VoiceCategory> list);

	List<VoiceCategory> findByPag(@Param("start")Integer start,@Param("end")Integer end);

	List<VoiceCategory> findAllByPage(BaseSearch bs);

	List<VoiceCategory> findAllPater(VoiceCategory voice);

	VoiceCategory getVoiceCategorybykey(Long u);

	List<VoiceCategory> findAll(VoiceCategory voice);

}
