package com.peg.service.impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.dao.system.EditReasonMapper;
import com.peg.dao.system.SysOperateLogMapper;
import com.peg.model.system.EditReason;
import com.peg.model.system.SysOperateLog;
import com.peg.service.AbstractService;
import com.peg.service.system.EditReasonServiceI;
import com.peg.service.system.SysOperateLogServiceI;

/**
 * 绩效修改日志实现类，继承通用的Service实现类，实现绩效修改日志接口
 * @author xuanm 2018年6月12日09:53:27
 *
 */
@Service("editReasonService")
public class EditReasonServiceImpl extends AbstractService<EditReason, Long> implements EditReasonServiceI{

	/**
	 * 绩效修改日志操作Mapper接口对象，用于和数据库交互
	 */
	@Autowired 
	private EditReasonMapper editReasonMapper;

	@Autowired
	@Override
	public void setBaseMapper() {
		super.setBaseMapper(editReasonMapper);		
	}

	@Override
	public List<EditReason> getAll() {
		return editReasonMapper.getAll();
	}

	@Override
	public List<EditReason> findAllByPage(PageParameter page, EditReason log) {
		return editReasonMapper.findAllByPage(page, log);
	}

	@Override
	public List<EditReason> getAllByPage(BaseSearch bs) {
		return editReasonMapper.getAllByPage(bs);
	}
	
	@Override
	public int insertSelective(EditReason editReason) {
		return super.insertSelective(editReason);
	}
}
