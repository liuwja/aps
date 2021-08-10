package com.peg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.FaultReasonMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.FaultReason;
import com.peg.service.FaultReasonServiceI;

@Service("faultReasonService")
public class FaultReasonServiceImpl implements FaultReasonServiceI{
	
	@Autowired
	private FaultReasonMapper faultReasonMapper;

	@Override
	public int insert(FaultReason record) {
		faultReasonMapper.insert(record);
		return 0;
	}

	@Override
	public List<FaultReason> findAllByPage(FaultReason faultReason,PageParameter page) {
		return faultReasonMapper.getAllByPage(faultReason, page);
	}

	@Override
	public List<FaultReason> findAllResult(FaultReason faultReason) {
		return faultReasonMapper.getAllResult(faultReason);
	}
	
	@Override
	public List<FaultReason> findAll() {
		return faultReasonMapper.getAll();
	}
	
	@Override
	public List<FaultReason> getMeshFaultReason(FaultReason vo) {
		return faultReasonMapper.getMeshFaultReason(vo);
	}
	
	@Override
	public int updateByPrimaryKeySelective(FaultReason record) {
		return faultReasonMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public FaultReason selectByPrimaryKey(Long id) {
		return faultReasonMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return faultReasonMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int setMeshFaultNamebyKey(FaultReason record) {
		return faultReasonMapper.setMeshFaultNamebyKey(record);
	}

	@Override
	public int synchronous() {
		return faultReasonMapper.synchronous();
	}

	@Override
	public List<FaultReason> findAllMeshNamesByPage(FaultReason faultReason,
			PageParameter page) {
		return faultReasonMapper.findAllMeshNamesByPage(faultReason, page);
	}

	public FaultReason getMeshNameByMeshCode(FaultReason faultReason) {
		return faultReasonMapper.getMeshNameByMeshCode(faultReason);
	}
	
	public List<FaultReason> getMeshCodeByMeshName(FaultReason faultReason) {
		return faultReasonMapper.getMeshCodeByMeshName(faultReason);
	}
	
	@Override
	public int saveMesh(List<Long> idList,String meshfaultcode, String meshFaultName, String lastUpdateUser, String lastUpdateType) {
		return faultReasonMapper.saveMesh(idList, meshfaultcode, meshFaultName, lastUpdateUser, lastUpdateType);
	}

	@Override
	public int breakMesh(List<Long> idList, String lastUpdateUser, String lastUpdateType) {
		return faultReasonMapper.breakMesh(idList, lastUpdateUser, lastUpdateType);
	}
}
