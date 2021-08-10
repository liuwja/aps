package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.InstallTotalMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CommonVo;
import com.peg.model.InstallTotal;
import com.peg.service.AbstractService;
import com.peg.service.InstallTotalServiceI;

@Service("installTotalService")
public class InstallTotalServiceImpl extends
		AbstractService<InstallTotal, Long> implements InstallTotalServiceI {
	@Autowired
	InstallTotalMapper installTotalMapper;

	@Override
	public void setBaseMapper() {
		super.setBaseMapper(installTotalMapper);
	}

	@Override
	public List<InstallTotal> findAllRecord(CommonVo comVo,PageParameter page) {
		// TODO Auto-generated method stub
		long startRow = (page.getPageNum() - 1) * page.getNumPerPage();
		long endRow = page.getPageNum() * page.getNumPerPage();
		int total = installTotalMapper.getTotalNumber(comVo);
        page.setTotalCount(total);
        int totalPage = total / page.getNumPerPage() + ((total % page.getNumPerPage() == 0) ? 0 : 1);
        page.setTotalPage(totalPage);	
		return installTotalMapper.findAllRecord(comVo, page, startRow, endRow);
	}

	@Override
	public List<InstallTotal> findAll(CommonVo comVo) {
		// TODO Auto-generated method stub
		return installTotalMapper.findAll(comVo);
	}

	@Override
	public int sumInstall(CommonVo comVo) {
		// TODO Auto-generated method stub
		return installTotalMapper.sumInstall(comVo);
	}

	@Override
	public int sumInsRepire(CommonVo comVo) {
		// TODO Auto-generated method stub
		return installTotalMapper.sumInsRepire(comVo);
	}

	@Override
	public int getTotalNumber(CommonVo comVo) {
		// TODO Auto-generated method stub
		return installTotalMapper.getTotalNumber(comVo);
	}

}
