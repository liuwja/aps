package com.peg.dao.system;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.model.system.GroupPrivilege;

public interface GroupPrivilegeMapper extends BaseMapper<GroupPrivilege, Long>{
	int deleteByGroupKeyNPlgKey(@Param("groupKey")long groupKey, @Param("plgKey")long plgKey);
}