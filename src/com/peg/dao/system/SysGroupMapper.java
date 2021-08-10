package com.peg.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.system.SysGroup;

public interface SysGroupMapper extends BaseMapper<SysGroup, Long> {

	List<SysGroup> findAllByPage(@Param("page")PageParameter page, @Param("code") String code,@Param("name") String name);

	List<SysGroup> findGroupsByUserKey(@Param("userKey") long userKey);

}