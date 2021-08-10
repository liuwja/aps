package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.DownLineTotalMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.DownLineTotal;
import com.peg.service.AbstractService;
import com.peg.service.DownLineTotalServiceI;
@Service("downLineService")
public class DownLineTotalServiceImpl extends AbstractService<DownLineTotal, Long>
implements DownLineTotalServiceI{

	@Autowired
	private DownLineTotalMapper downLineMapper;

	@Override
	public void setBaseMapper() {
		super.setBaseMapper(downLineMapper);
	}

	@Override
	public List<DownLineTotal> findAllByPage(DownLineTotal downLineTotal,
			PageParameter page) {
		// TODO Auto-generated method stub
		return downLineMapper.findAllByPage(downLineTotal, page);
	}

	@Override
	public List<DownLineTotal> findAll(DownLineTotal downLineTotal) {
		// TODO Auto-generated method stub
		return downLineMapper.findAll(downLineTotal);
	}

	@Override
	public int sumDownLine(DownLineTotal downLineTotal) {
		// TODO Auto-generated method stub
		return downLineMapper.sumDownLine(downLineTotal);
	}

}
