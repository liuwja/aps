package com.peg.service.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.QprnSetting;

public interface QprnSettingServiceI {
	
	int deleteByPrimaryKey(Long id);

    int insert(QprnSetting record);

    int insertSelective(QprnSetting record);

    QprnSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QprnSetting record);

    int updateByPrimaryKey(QprnSetting record);
    
    List<QprnSetting> getAllByPage(BaseSearch bs);

}
