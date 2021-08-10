package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.ReworkSheetMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ReworkSheet;
import com.peg.service.AbstractService;
import com.peg.service.ReworkSheetServicel;
/**
 * 
 * <p>Title: ReworkSheetServiceImpl</p>
 * <p>Description: 返工、停线单service实现层</p>
 * <p>Company: Fotile</p> 
 * @author dingzc 
 * @date 2018-3-9 下午2:49:28
 */
@Service("reworkSheetService")
public class ReworkSheetServiceImpl extends AbstractService<ReworkSheet, Long> implements ReworkSheetServicel{
	
	@Autowired
	private ReworkSheetMapper reworkSheetMapper;

	@Override
	public void setBaseMapper() {
		super.setBaseMapper(reworkSheetMapper);
		
	}

	@Override
	public List<ReworkSheet> getAllByPage(ReworkSheet reworkSheet,PageParameter page) {
		return reworkSheetMapper.getAllByPage(reworkSheet,page);
	}

	@Override
	public ReworkSheet getByid(ReworkSheet reworkSheet) {
		return reworkSheetMapper.getByid(reworkSheet);
	}
	
	@Override
	public ReworkSheet getByReworkNumber(ReworkSheet reworkSheet) {
		return reworkSheetMapper.getByReworkNumber(reworkSheet);
	}

	@Override
	public int insertSelective(ReworkSheet reworkSheet) {
		return reworkSheetMapper.insertSelective(reworkSheet);
	}

	@Override
	public int updateByIdSelective(ReworkSheet reworkSheet) {
		return reworkSheetMapper.updateByIdSelective(reworkSheet);
	}

	@Override
	public int deleteById(ReworkSheet reworkSheet) {
		return reworkSheetMapper.deleteById(reworkSheet);
	}

}
