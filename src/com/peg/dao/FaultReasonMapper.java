package com.peg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.FaultReason;

public interface FaultReasonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FaultReason record);

    int insertSelective(FaultReason record);

    FaultReason selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FaultReason record);

    int updateByPrimaryKey(FaultReason record);
   
    int setMeshFaultNamebyKey(FaultReason record);

	List<FaultReason> getAllByPage(@Param("faultReason")FaultReason faultReason,@Param("page")PageParameter page);
	
	List<FaultReason> getAllResult(@Param("faultReason")FaultReason faultReason);

	List<FaultReason> getAll();
	
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
	
	int synchronous();
	
	/**
	 * 查询所有合并故障名称
	 */
	List<FaultReason> findAllMeshNamesByPage(@Param("faultReason") FaultReason faultReason,@Param("page") PageParameter page);
	
	/**
	 * 合并故障名
	 */
	int saveMesh(@Param("idList")List<Long> idList,@Param("meshfaultcode")String meshfaultcode,@Param("meshFaultName")String meshFaultName,@Param("lastUpdateUser")String lastUpdateUser,@Param("lastUpdateType")String lastUpdateType);
	
	/**
	 * 拆分故障名
	 */
	int breakMesh(@Param("idList")List<Long> idList, @Param("lastUpdateUser")String lastUpdateUser, @Param("lastUpdateType")String lastUpdateType);
}