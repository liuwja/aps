/*
 * @(#) SysPrivilegeServiceImpl.java 2014-8-17 下午10:26:38
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.service.impl.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.system.GroupPrivilegeMapper;
import com.peg.dao.system.SysPrivilegeMapper;
import com.peg.model.system.GroupPrivilege;
import com.peg.model.system.SysPrivilege;
import com.peg.service.AbstractService;
import com.peg.service.system.SysPrivilegeServiceI;
import com.peg.web.menu.Menu;
import com.peg.web.menu.Operation;
import com.peg.web.util.MapperUtils;


/**
 * TODO
 * <p>
 * @author Lin, 2014-8-17 下午10:26:38
 */
@Service("sysPrivilegeService")
public class SysPrivilegeServiceImpl extends AbstractService<SysPrivilege, Long> implements SysPrivilegeServiceI
{
	
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private SysPrivilegeMapper sysPrivilegeMapper;
	
	@Autowired
	private GroupPrivilegeMapper groupPrivilegeMapper;
	
	@Override
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(sysPrivilegeMapper);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return sysPrivilegeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysPrivilege record) {
		return sysPrivilegeMapper.insert(record);
	}

	@Override
	public int insertSelective(SysPrivilege record) {
		return sysPrivilegeMapper.insertSelective(record);
	}

	@Override
	public SysPrivilege selectByPrimaryKey(Long id) {
		return sysPrivilegeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysPrivilege record) {
		return sysPrivilegeMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(SysPrivilege record) {
		return sysPrivilegeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public boolean insertAndUpdateMenus(List<Menu> menus) {
		Map<String, SysPrivilege> dbPlgMap = findAllSysPrivilege();
		List<SysPrivilege> menuPrivilegeList = new ArrayList<SysPrivilege>();
		convertToPrivilegeList(menus, menuPrivilegeList);
		Map<String, SysPrivilege> menuPlgMap = convertToPlgMap(menuPrivilegeList);
		for(SysPrivilege p : menuPrivilegeList)
		{
			if(!dbPlgMap.containsKey(p.getOperationCode()))
			{
				//insert
				sysPrivilegeMapper.insert(p);
				logger.info("insert operationCode: " + p.getOperationCode());
			}
			else
			{
				//update
				p.setPrivilegeKey(dbPlgMap.get(p.getOperationCode()).getPrivilegeKey());
				sysPrivilegeMapper.updateByPrimaryKey(p);
				logger.info("update operationCode: " + p.getOperationCode());
			}
		}
		Collection<SysPrivilege> privilegeList = dbPlgMap.values();
		for(SysPrivilege p : privilegeList)
		{
			if(!menuPlgMap.containsKey(p.getOperationCode()))
			{
				//delete
				sysPrivilegeMapper.deleteGroupPlgByOperationCode(p.getOperationCode());
				sysPrivilegeMapper.deletePrivilegeByOperationCode(p.getOperationCode());
				logger.info("delete operationCode: " + p.getOperationCode());
			}
		}
		return true;
	}

	@Override
	public List<SysPrivilege> findPrivilegeByUserName(String userName) {
		return sysPrivilegeMapper.findPrivilegeByUserName(userName);
	}

	@Override
	public List<SysPrivilege> findPrivilegeByGroupKey(long groupKey) {
		return sysPrivilegeMapper.findPrivilegeByGroupKey(groupKey);
	}
	
	private Map<String, SysPrivilege> convertToPlgMap(List<SysPrivilege> rsList)
	{
		Map<String, SysPrivilege> rsMap = new HashMap<String, SysPrivilege>();
		for(SysPrivilege r : rsList)
		{
			rsMap.put(r.getOperationCode(), r);
		}
		return rsMap;
	}
	
	private void convertToPrivilegeList(List<Menu> menus, List<SysPrivilege> menuPrivilegeList)
	{
		SysPrivilege plg = null;
		for(Menu menu : menus)
		{
			List<Operation> opList = menu.getOptList();
			if(null != opList && !opList.isEmpty())
			{
				for(Operation op : opList)
				{
					plg = new SysPrivilege();
					plg.setMenuName(menu.getName());
					plg.setMenuCode(menu.getPermissionCode());
					plg.setOperationName(op.getName());
					plg.setOperationCode(op.getPermissionCode());
					menuPrivilegeList.add(plg);
				}
			}
		}
	}

	@Override
	public Map<String, SysPrivilege> findAllSysPrivilege() {
		return convertToPlgMap(sysPrivilegeMapper.findAllSysPrivilege());
	}

	@Override
	public int insertNUpdateGroupPrivilege(long groupKey, List<String> operationCodeList) throws Exception {
		MapperUtils.clearCache();
		Map<String, SysPrivilege> plgMap = findAllSysPrivilege();
		//数据库用户组权限
		List<SysPrivilege> groupPlgList = findPrivilegeByGroupKey(groupKey);
		Map<String, SysPrivilege> groupPlgMap = convertToPlgMap(groupPlgList);
		List<GroupPrivilege> insertList = new ArrayList<GroupPrivilege>();
		List<GroupPrivilege> delList = new ArrayList<GroupPrivilege>();
		for(String plgcode : operationCodeList)
		{
			if(plgMap.containsKey(plgcode))
			{
				if(!groupPlgMap.containsKey(plgcode))
				{
					insertList.add(new GroupPrivilege(groupKey, plgMap.get(plgcode).getPrivilegeKey()));
				}
			}
			else
			{
				throw new Exception(plgcode + "对应的操作不存在!");
			}
		}		
		//如果前台列表不包含，则数据库对应用户组的权限要删除
		for(SysPrivilege record : groupPlgList)
		{
			if(!operationCodeList.contains(record.getOperationCode()))
			{
				delList.add(new GroupPrivilege(groupKey, record.getPrivilegeKey()));
			}
		}
		//删除权限
		for(GroupPrivilege record : delList)
		{
			groupPrivilegeMapper.deleteByGroupKeyNPlgKey(record.getGroupKey(), record.getPrivilegeKey());
		}		
		//新增权限
		for(GroupPrivilege record : insertList)
		{
			groupPrivilegeMapper.insert(record);
		}
		return 1;
	}
}
