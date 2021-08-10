package com.peg.service.impl.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.UserGroupMapper;
import com.peg.dao.interceptor.PageParameter;
import com.peg.dao.system.SysMesUserMapper;
import com.peg.dao.system.SysPrivilegeMapper;
import com.peg.model.UserGroup;
import com.peg.model.system.SysGroup;
import com.peg.model.system.SysMesUser;
import com.peg.service.AbstractService;
import com.peg.service.system.SysMesUserServiceI;

@Service("sysMesUserServiceImpl")
public class SysMesUserServiceImpl extends AbstractService<SysMesUser, Long> implements SysMesUserServiceI{
	
	@Autowired
	private SysMesUserMapper sysMesUserMapper;
	
	@Autowired
	private SysPrivilegeMapper sysPrivilegeMapper;
	
	@Autowired
	private UserGroupMapper userGroupMapper;

	@Override
	@Autowired
	public void setBaseMapper() {
		// TODO Auto-generated method stub
		super.setBaseMapper(sysMesUserMapper);
		
	}

	@Override
	public SysMesUser selectByUsername(String userName) {
		// TODO Auto-generated method stub
		return sysMesUserMapper.selectByUsername(userName);
	}

	@Override
	public boolean findUsersByGroupKey(List<SysGroup> list) {
		List<UserGroup> ugList = userGroupMapper.findUsersByGroupKey(list);
		setUserToGroups(list, ugList);
		return false;
	}
	
	private void setUserToGroups(List<SysGroup> list, List<UserGroup> ugList)
	{
		Map<Long, List<UserGroup>> map = convertToUserMap(ugList);
		for(SysGroup g : list)
		{
			g.setUgList(map.get(g.getGroupKey()));
		}
	}
	
	/**
	 * <groupKey, usersgroup>
	 * @param ugList
	 * @return
	 */
	private Map<Long, List<UserGroup>> convertToUserMap(List<UserGroup> ugList )
	{
		Map<Long, List<UserGroup>> map = new HashMap<Long, List<UserGroup>>();
		List<UserGroup> tempList = null;
		for(UserGroup ug : ugList)
		{
			if(!map.containsKey(ug.getGroupKey()))
			{
				tempList = new ArrayList<UserGroup>();
				map.put(ug.getGroupKey(), tempList);
			}
			map.get(ug.getGroupKey()).add(ug);
		}
		return map;
	}

	@Override
	public List<SysMesUser> getAllByPage(PageParameter page, String userName,
			String name) {
		return sysMesUserMapper.getAllByPage(page, userName, name);
	}

	@Override
	public List<SysMesUser> findUsersByGroupKey(long groupKey) {
		return sysMesUserMapper.findUsersByGroupKey(groupKey);
	}

	@Override
	public void mergeUser() {
		// TODO Auto-generated method stub
		sysMesUserMapper.mergeUser();
	}

}
