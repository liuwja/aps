package com.peg.service.impl.jxmb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.jxmb.PerDepartmentMapper;
import com.peg.model.jxmb.PerDeparment;
import com.peg.service.jxmb.DeparmentServicel;

@Service("departmentService")
public class DeparmentServiceImpl implements DeparmentServicel {

	@Autowired
	private PerDepartmentMapper dtMapper;
	
	@Override
	public List<PerDeparment> getAllByPage(BaseSearch bs, boolean isPage) {
		if(isPage) {
			return dtMapper.getAllByPage(bs) ;
		} else {
			return dtMapper.getAll(bs);
		}
	}

	@Override
	public int insert(PerDeparment dt) {
		return dtMapper.insert(dt);
	}
	
	@Override
	public PerDeparment selectByPrimaryKey(Long id) {
		return dtMapper.selectByPrimaryKey(id);
	}

	@Override
	public PerDeparment selectByDepartmentNumber(String departmentNumber) {
		return dtMapper.selectByDepartmentNumber(departmentNumber);
	}
	
	@Override
	public PerDeparment selectByDepartmentName(String departmentName) {
		return dtMapper.selectByDepartmentName(departmentName);
	}
	
	@Override
	public int update(PerDeparment dt) {
		return dtMapper.update(dt);
	}
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return dtMapper.deleteByPrimaryKey(id);
	}
}
