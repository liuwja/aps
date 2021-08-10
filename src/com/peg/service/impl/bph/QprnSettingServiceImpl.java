package com.peg.service.impl.bph;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.QprnSettingMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.QprnSetting;
import com.peg.service.bph.QprnSettingServiceI;

@Service("QprnSettingService")
public class QprnSettingServiceImpl implements QprnSettingServiceI{

	@Autowired
	private QprnSettingMapper qprnSettingMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return qprnSettingMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<QprnSetting> getAllByPage(BaseSearch bs) {
		
		return qprnSettingMapper.getAllByPage(bs);
	}

	@Override
	public int insert(QprnSetting record) {
		qprnSettingMapper.insert(record);
		return 0;
	}

	@Override
	public int insertSelective(QprnSetting record) {
		qprnSettingMapper.insertSelective(record);
		return 0;
	}

	@Override
	public QprnSetting selectByPrimaryKey(Long id) {
		
		return qprnSettingMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(QprnSetting record) {
		
		return qprnSettingMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(QprnSetting record) {
		
		return qprnSettingMapper.updateByPrimaryKeySelective(record);
	}

}
