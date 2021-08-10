package com.peg.service.impl.jxmb;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.dao.jxmb.PerGroupMapper;
import com.peg.model.jxmb.PerGroup;
import com.peg.model.jxmb.PerItem;
import com.peg.service.jxmb.PerGoupServicel;

@Service("perGoupService")
public class PerGoupServiceImpl implements PerGoupServicel {

	@Autowired
	private PerGroupMapper perGroupMapper;

	@Override
	public int insert(PerGroup perfGroup) {
		
		return perGroupMapper.insert(perfGroup);
	}

	@Override
	public int delete(Long id) {
		
		return 0;
	}

	@Override
	public List<PerGroup> getItemAllByPage(BaseSearch bs) {
		
		return perGroupMapper.getItemAllByPage(bs);
	}

	@Override
	public List<PerGroup> getIndexAllByPage(BaseSearch bs) {
		return perGroupMapper.getIndexAllByPage(bs);
	}

	@Override
	public List<PerGroup> getMonthAllByPage(BaseSearch bs) {
		
		return perGroupMapper.getMonthAllByPage(bs);
	}

	@Override
	public PerGroup selectByPrimaryKey(Long id) {
		
		return perGroupMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<PerGroup> getAllGroupCategory() {
		return perGroupMapper.getAallGroupCategoryFromMes();
	}

	@Override
	public List<PerGroup> getAllGroupCategoryFromMes() {
		return perGroupMapper.getAallGroupCategoryFromMes();
	}

	@Override
	public Map<String, String> getIndexDescription() {
		BaseSearch bs=new BaseSearch();
		PageParameter page=new PageParameter();
		page.setNumPerPage(1000);
		bs.setPage(page);
//		List<PerGroup>list=perGroupMapper.getIndexAllByPage(bs);
		Map<String ,String >map=new LinkedHashMap<String, String>();
		return map;
	}

	@Override
	public List<PerItem> getAllBypage(BaseSearch bs) {
		
		return null;
	}
	
	
	
}
