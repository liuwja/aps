package com.peg.service.impl.bph;

import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.GroupCategoryMapper;
import com.peg.dao.bph.IndexMapper;
import com.peg.dao.bph.ItemMapper;
import com.peg.dao.bph.MonthAssessmentMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.bph.GroupCategory;
import com.peg.model.bph.Index;
import com.peg.model.bph.Item;
import com.peg.service.bph.GroupCategorySerivceI;

@Service("groupCategoryService")
public class GroupCategoryServiceImpl implements GroupCategorySerivceI{

	@Autowired
	private GroupCategoryMapper groupCategoryMapper;
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private IndexMapper indexMapper;
	
	@Autowired
	private MonthAssessmentMapper monthAssessmentMapper;
	
	@Override
	public int insert(GroupCategory groupCategory) {
		return groupCategoryMapper.insert(groupCategory);
	}

	@Override
	public int delete(Long gcKey) {
		try{
			monthAssessmentMapper.deleteMonthByGcKey(gcKey);
			indexMapper.deleteIndexByGcKey(gcKey);
			itemMapper.deleItemsByGckey(gcKey);
		}catch(Exception e){
			e.printStackTrace();
		}
		return groupCategoryMapper.deleteByPrimaryKey(gcKey);
	}

	@Override
	public List<GroupCategory> getItemAllByPage(BaseSearch bs) {
		return groupCategoryMapper.getItemAllByPage(bs);
	}

	@Override
	public List<GroupCategory> getIndexAllByPage(BaseSearch bs) {
		return groupCategoryMapper.getIndexAllByPage(bs);
	}

	@Override
	public List<GroupCategory> getMonthAllByPage(BaseSearch bs) {
		return groupCategoryMapper.getMonthAllByPage(bs);
	}

	@Override
	public GroupCategory selectByPrimaryKey(Long id) {
		return groupCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<GroupCategory> getAllGroupCategory() {
		return groupCategoryMapper.getAllGroupCategory();
	}

	@Override
	public List<GroupCategory> getAllGroupCategoryFromMes() {
		return groupCategoryMapper.getAllGroupCategoryFromMes();
	}

	@Override
	public Map<String, String> getIndexDescription() {
		BaseSearch bs = new BaseSearch();
		PageParameter page = new PageParameter();
		page.setNumPerPage(1000);
		bs.setPage(page);
		List<GroupCategory> list = groupCategoryMapper.getIndexAllByPage(bs);
		Map<String,String> map = new LinkedHashMap<String, String>();
		for(GroupCategory cate : list)
		{
			for(Item item : cate.getItem())
			{
				for(Index index : item.getUiindexs())
				{
					String key = cate.getFactory() +"-" + cate.getCategory()+"-"+item.getItemName()+"-"+index.getIndexName()+"("+index.getIndexCode()+")";
					if(!map.containsKey(key))
					{
						map.put(key, index.getIndexDescription());
					}
				}
			}
		}
		return map;
	}

}
