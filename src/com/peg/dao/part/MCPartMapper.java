package com.peg.dao.part;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.part.MCPart;

public interface MCPartMapper {
	
	List<MCPart> findAllPage(BaseSearch bs);
	
	List<MCPart> findAll(BaseSearch bs);
	
	MCPart selectByPrimaryKey(Long id);
	
	int insert(MCPart vo);
	
	int update(MCPart vo);
	
	int delete(MCPart vo);
}
