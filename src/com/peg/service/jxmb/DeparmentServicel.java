package com.peg.service.jxmb;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.jxmb.PerDeparment;

public interface DeparmentServicel{
	
	/**
	 * 查询部门记录
	 * @param bs
	 * @param isPage 是否分页
	 * @return
	 */
	List<PerDeparment> getAllByPage(BaseSearch bs, boolean isPage);
	
    /**
     * 新增部门记录
     * @param dt
     * @return
     */
    int insert(PerDeparment dt);
    
    /**
     * 根据ID查询部门记录
     * @param id
     * @return
     */
    PerDeparment selectByPrimaryKey(Long id);
    
    /**
     * 根据部门编号来查询部门记录
     * @param departmentNumber 部门编号（字符串类型）
     * @return 部门记录
     */
    PerDeparment selectByDepartmentNumber(String departmentNumber);
    
    /**
     * 根据部门名称来查询部门记录
     * @param departmentName 部门名称（字符串）
     * @return 部门记录
     */
    PerDeparment selectByDepartmentName(String departmentName);
    
    /**
     * 更新部门记录
     * @param dt
     * @return
     */
    int update(PerDeparment dt);
    
    /**
     * 删除部门记录
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);
}
