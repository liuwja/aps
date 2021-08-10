package com.peg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.model.UserGroup;
import com.peg.model.system.SysGroup;
import com.peg.model.system.SysMesUser;

public interface UserGroupMapper  extends BaseMapper<UserGroup, Long>{
	int delUserGroup(UserGroup ugroup);
	List<UserGroup> findUsersByGroupKey(@Param("groups")List<SysGroup> list);
	List<UserGroup> findGroupsByuserKey(@Param("users")List<SysMesUser> list);
}