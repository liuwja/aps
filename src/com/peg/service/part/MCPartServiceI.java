package com.peg.service.part;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.part.MCPart;

public interface MCPartServiceI {
	
	/**
	 * 查询MES与CRM关系对应表
	 * @param bs
	 * @return
	 */
	List<MCPart> findAll(BaseSearch bs);
	
	/**
	 * 根据ID查询MES与CRM关系实体
	 * @param id
	 * @return
	 */
	MCPart selectByPrimaryKey(Long id);
	
	/**
	 * 插入MES与CRM关系实体
	 * @param vo
	 * @return
	 */
	int insert(MCPart vo);
	
	/**
	 * 更新MES与CRM关系实体
	 * @param vo
	 * @return
	 */
	int update(MCPart vo);
	
	/**
	 * 删除MES与CRM关系实体
	 * @param vo
	 * @return
	 */
	int delete(MCPart vo);
}
