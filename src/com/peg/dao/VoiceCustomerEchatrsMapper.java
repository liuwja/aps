package com.peg.dao;

import java.util.List;

import com.peg.model.CommonVo;
import com.peg.model.VoiceEchart;

public interface VoiceCustomerEchatrsMapper {

	List<VoiceEchart> findXY(CommonVo vo);

	List<VoiceEchart> findXYByTime(CommonVo vo);

	
}
