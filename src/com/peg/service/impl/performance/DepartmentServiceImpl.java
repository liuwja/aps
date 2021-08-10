package com.peg.service.impl.performance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.performance.DepartmentMapper;
import com.peg.model.performance.Department;
import com.peg.service.AbstractService;
import com.peg.service.performance.DepartmentService;
/**
 * 部门操作接口实现类
 * @author xuanm
 *
 */
@Service("departmentServiceA")
public class DepartmentServiceImpl extends AbstractService<Department, Long> implements DepartmentService {

	/**
	 * 部门操作Mapper对象
	 */
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public void setBaseMapper() {
		// TODO Auto-generated method stub

	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Department record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Department record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Department selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Department record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Department record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Department> getAllByPage(BaseSearch bs) {
		return departmentMapper.getAllByPage(bs);
	}

	@Override
	public Department selectByDepartmentNumber(String departmentNumber) {
		return departmentMapper.selectByDepartmentNumber(departmentNumber);
	}

	@Override
	public List<Department> getFactoryAndDepartmentByName(BaseSearch bs) {
		return departmentMapper.getFactoryAndDepartmentByName(bs);
	}
}
