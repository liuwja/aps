package com.peg.service.impl.bph;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.ProcessScoreSettingMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.ProcessScoreSetting;
import com.peg.service.bph.ProcessScoreSettingServiceI;

@Service("ProcessScoreSettingService")
public class ProcessScoreSettingServiceImpl implements ProcessScoreSettingServiceI{

	@Autowired
	private ProcessScoreSettingMapper processScoreSettingMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return processScoreSettingMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<ProcessScoreSetting> getAllByPage(BaseSearch bs) {
		
		return processScoreSettingMapper.getAllByPage(bs);
	}

	@Override
	public int insert(ProcessScoreSetting record) {
		processScoreSettingMapper.insert(record);
		return 0;
	}

	@Override
	public int insertSelective(ProcessScoreSetting record) {
		
		return processScoreSettingMapper.insertSelective(record);
	}

	@Override
	public ProcessScoreSetting selectByPrimaryKey(Long id) {
		
		return processScoreSettingMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(ProcessScoreSetting record) {
		
		return processScoreSettingMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ProcessScoreSetting record) {
		
		return processScoreSettingMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<ProcessScoreSetting> getProcessScore(String item1,String item2) {
		
		return processScoreSettingMapper.getProcessScore(item1,item2);
	}

	

}
