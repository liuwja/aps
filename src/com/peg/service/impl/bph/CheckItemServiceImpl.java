package com.peg.service.impl.bph;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.CheckItemMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.CheckItem;
import com.peg.service.bph.CheckItemServiceI;
@Service("CheckItemService")
public class CheckItemServiceImpl implements CheckItemServiceI{

	@Autowired
	private CheckItemMapper checkItemMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		return checkItemMapper.deleteByPrimaryKey(id);
		
	}


	@Override
	public List<CheckItem> getAllByPage(BaseSearch bs) {
		
		return checkItemMapper.getAllByPage(bs);
	}

	@Override
	public int insert(CheckItem record) {
		checkItemMapper.insert(record);
		return 0;
	}


	@Override
	public CheckItem selectByPrimaryKey(Long id) {
		return checkItemMapper.selectByPrimaryKey(id);
		
	}

	@Override
	public int updateByPrimaryKey(Long id) {
		
		return checkItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CheckItem record) {
		
		return checkItemMapper.updateByPrimaryKeySelective(record);
	}


	@Override
	public List<CheckItem> getCategory(CheckItem checkItem) {
		
		return checkItemMapper.getCategory(checkItem);
	}


	@Override
	public List<CheckItem> getItemAll() {
		
		return checkItemMapper.getItemAll();
	}


	@Override
	public List<CheckItem> getCheckItem(String factory, String category) {
		return checkItemMapper.getCheckItem(factory, category);
	}


}
