package com.peg.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.model.system.SysPrivilege;

public interface SysPrivilegeMapper extends BaseMapper<SysPrivilege, Long>{
	/**
	 * 查询所有权限
	 * @return
	 */
	List<SysPrivilege> findAllSysPrivilege();
	
	/**
	 * 获得某个用户所有的权限
	 * @param userName
	 * @return
	 */
	List<SysPrivilege> findPrivilegeByUserName(@Param("userName")String userName);
	
	/**
	 * 获得某个组的所有权限
	 * @param groupKey
	 * @return
	 */
	List<SysPrivilege> findPrivilegeByGroupKey(@Param("groupKey")long groupKey);
	
	/**
	 * 根据operationCode删除Privilege
	 * @param operationCode
	 * @return
	 */
	int deletePrivilegeByOperationCode(@Param("operationCode")String operationCode);
	
	int deleteGroupPlgByOperationCode(@Param("operationCode")String operationCode);
}