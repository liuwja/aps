package com.peg.service.impl.bph;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.NMesDataSumMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.MesDataSum;
import com.peg.service.bph.MesDataSumServiceI;

@Service("mesDataSumService")
public class MesDataSumServiceImpl implements MesDataSumServiceI{

	@Autowired
	private NMesDataSumMapper mesDataSumMapper;
	
	@Override
	public List<MesDataSum> findAllByPage(BaseSearch bs) {
		
		return mesDataSumMapper.findAllByPage(bs);
	}

}
