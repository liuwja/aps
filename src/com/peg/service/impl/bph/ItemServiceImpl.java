package com.peg.service.impl.bph;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.ItemMapper;
import com.peg.model.bph.Item;
import com.peg.service.AbstractService;
import com.peg.service.bph.ItemServiceI;
@Service("itemService")
public class ItemServiceImpl extends AbstractService<Item, Long> implements ItemServiceI{

	@Autowired
	private ItemMapper itemMapper;
	
	@Override
	@Autowired
	public void setBaseMapper() {
       super.setBaseMapper(itemMapper);		
	}

	@Override
	public List<Item> getAllItems(String factory, String area, String category) {
		return itemMapper.getAllItems(factory, area, category);
	}

	@Override
	public List<Item> getItemByGckey(Long gcKey) {
		return itemMapper.getItemByGckey(gcKey);
	}

}
