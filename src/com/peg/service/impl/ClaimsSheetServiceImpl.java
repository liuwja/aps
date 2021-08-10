package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.peg.dao.ClaimsSheetMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ClaimsSheet;
import com.peg.model.ReworkSheet;
import com.peg.service.AbstractService;
import com.peg.service.ClaimsSheetServicel;

/**
 * 
 * <p>Title: ClaimsSheetServiceImpl</p>
 * <p>Description: 索赔处罚单service实现层</p>
 * <p>Company: Fotile</p> 
 * @author dingzc 
 * @date 2018-3-14 下午3:19:39
 */
@Service("claimsSheetServicel")
public class ClaimsSheetServiceImpl extends AbstractService<ClaimsSheet, Long> implements ClaimsSheetServicel{

	@Autowired
	private ClaimsSheetMapper claimsSheetMapper;
	
	@Override
	public void setBaseMapper() {
		super.setBaseMapper(claimsSheetMapper);
	}


	@Override
	public List<ClaimsSheet> getAllByPage(ClaimsSheet claimsSheet,
			PageParameter page) {
		return claimsSheetMapper.getAllByPage(claimsSheet, page);
	}

	@Override
	public ClaimsSheet getByid(ClaimsSheet claimsSheet) {
		return claimsSheetMapper.getByid(claimsSheet);
	}

	@Override
	public ClaimsSheet getByClaimsNumber(ClaimsSheet claimsSheet) {
		return claimsSheetMapper.getByClaimsNumber(claimsSheet);
	}

	@Override
	public int insertSelective(ClaimsSheet claimsSheet) {
		return claimsSheetMapper.insertSelective(claimsSheet);
	}

	@Override
	public int updateByIdSelective(ClaimsSheet claimsSheet) {
		return claimsSheetMapper.updateByIdSelective(claimsSheet);
	}

	@Override
	public int deleteById(ClaimsSheet claimsSheet) {
		return claimsSheetMapper.deleteById(claimsSheet);
	}


	@Override
	public List<ClaimsSheet> getByReworkId(ReworkSheet reworkSheet) {
		return claimsSheetMapper.getByReworkId(reworkSheet);
	}


}
