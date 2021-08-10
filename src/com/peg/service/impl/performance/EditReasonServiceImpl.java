package com.peg.service.impl.performance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.performance.EditReasonMapperA;
import com.peg.model.performance.EditReason;
import com.peg.service.AbstractService;
import com.peg.service.performance.EditReasonService;
/**
 * 绩效指标修改操作记录实现类
 * @author xuanm
 *
 */
@Service("editReasonServiceA")
public class EditReasonServiceImpl extends AbstractService<EditReason, Long> implements EditReasonService {

	/**
	 * 绩效指标修改操作Mapper接口
	 */
	@Autowired
	private EditReasonMapperA editReasonMapperA;
	
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
	public int insert(EditReason record) {
		return editReasonMapperA.insert(record);
	}

	@Override
	public int insertSelective(EditReason record) {
		return editReasonMapperA.insertSelective(record);
	}

	@Override
	public EditReason selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(EditReason record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(EditReason record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EditReason> getAllByPage(BaseSearch bs) {
		return editReasonMapperA.getAllByPage(bs);
	}

}
