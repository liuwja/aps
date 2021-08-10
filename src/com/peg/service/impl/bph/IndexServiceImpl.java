package com.peg.service.impl.bph;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.IndexMapper;
import com.peg.model.bph.Index;
import com.peg.service.AbstractService;
import com.peg.service.bph.IndexServiceI;
@Service("indexService")
public class IndexServiceImpl extends AbstractService<Index, Long> implements IndexServiceI{

	@Autowired
	private IndexMapper indexMapper;
	@Override
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(indexMapper);
	}
	@Override
	public List<Index> getIndexByItemKey(Long itemKey) {
		return indexMapper.getIndexByItemKey(itemKey);
	}
	@Override
	public Index findByIndexCode(String factory, String area, String category,
			String indexCode) {
		return indexMapper.findByIndexCode(factory, area, category, indexCode);
	}
	

}
