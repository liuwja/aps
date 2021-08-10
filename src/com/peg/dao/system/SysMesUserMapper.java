package com.peg.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.system.SysMesUser;

public interface SysMesUserMapper extends BaseMapper<SysMesUser, Long> {

	List<SysMesUser> getAllByPage(@Param("page")PageParameter page,
			@Param("userName") String userName, @Param("description") String description);

	List<SysMesUser> findUsersByGroupKey(@Param("groupKey") long groupKey);

	SysMesUser selectByUsername(String username);

	void mergeUser();

}