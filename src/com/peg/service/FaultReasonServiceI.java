package com.peg.service;

import java.util.List;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.FaultReason;

public interface FaultReasonServiceI {
	
    int insert(FaultReason faultReason);
	
	List<FaultReason> findAllByPage(FaultReason faultReason,PageParameter page);
	
	List<FaultReason> findAllResult(FaultReason faultReason);
	/**
	 * 查询所有合并故障名称
	 * @param faultReason
	 * @param page
	 * @return
	 */
	List<FaultReason> findAllMeshNamesByPage(FaultReason faultReason,PageParameter page);
	
	public List<FaultReason> findAll();
	
	List<FaultReason> getMeshFaultReason(FaultReason vo);
	
	/**
	 * 根据合并故障代码获取合并故障名称
	 * @param faultReason
	 * @return
	 */
	FaultReason getMeshNameByMeshCode(FaultReason faultReason);
	
	/**
	 * 根据合并故障名称及机型获取合并故障代码
	 * @param faultReason
	 * @return
	 */
	List<FaultReason> getMeshCodeByMeshName(FaultReason faultReason);
	
	int updateByPrimaryKeySelective(FaultReason faultReason);

	FaultReason selectByPrimaryKey(Long id);

	int deleteByPrimaryKey(Long id);

	int setMeshFaultNamebyKey(FaultReason faultReason);
	
	/**
	 * 合并故障小类
	 * @param idList
	 * @param meshfaultcode 合并故障代码
	 * @param meshFaultName 合并故障名称
	 * @param lastUpdateUser 更新用户
	 * @param lastUpdateType 更新类型
	 * @return
	 */
	int saveMesh(List<Long> idList, String meshfaultcode, String meshFaultName, String lastUpdateUser, String lastUpdateType);
	
	/**
	 * 拆分合并故障小类
	 * @param idList
	 * @param lastUpdateUser 更新用户
	 * @param lastUpdateType 更新类型
	 * @return
	 */
	int breakMesh(List<Long> idList, String lastUpdateUser, String lastUpdateType);
	
	int synchronous();

}
