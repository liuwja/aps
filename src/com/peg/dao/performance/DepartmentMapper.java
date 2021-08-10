package com.peg.dao.performance;

import java.util.List;

import com.peg.dao.BaseMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.performance.Department;

/**
 * 部门操作Mapper
 * @author xuanm
 *
 */
public interface DepartmentMapper extends BaseMapper<Department, Long> {
	
	/**
	 * 查询所有部门
	 * @param bs
	 * @return
	 */
	List<Department> getAllByPage(BaseSearch bs);
	
	/**
	 * 根据部门编号查询部门信息
	 * @param departmentNumber 部门编号
	 * @return
	 */
	Department selectByDepartmentNumber(String departmentNumber);
	
	/**
	 * 根据工厂名称和部门名称查询工厂部门信息（主要为了获得工厂和部门的编号）
	 * @param bs
	 * @return
	 */
	List<Department> getFactoryAndDepartmentByName(BaseSearch bs);
}
