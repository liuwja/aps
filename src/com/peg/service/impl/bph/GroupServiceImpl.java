package com.peg.service.impl.bph;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.GroupMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.Group;
import com.peg.service.AbstractService;
import com.peg.service.bph.GroupServiceI;

@Service("groupService")
public class GroupServiceImpl extends AbstractService<Group, Long> implements GroupServiceI{

	@Autowired
	private GroupMapper groupMapper;
	@Override
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(groupMapper);
	}
	@Override
	public List<Group> getAllGroup() {
		return groupMapper.getAllGroup();
	}
	@Override
	public List<Group> getAllGroupFromMes() {
		return groupMapper.getAllGroupFromMes();
	}
	@Override
	public Group getGroupByFag(String factory, String area,String groupCategory, String groupName) {
		return groupMapper.getGroupByFag(factory,area,groupCategory,groupName);
	}
	@Override
	public List<Group> getMonthAllByPage(BaseSearch bs) {
		return groupMapper.getMonthAllByPage(bs);
	}
	@Override
	public List<Group> getGroupScoreByMonth(String factory, String area,
			String groupName, String monthly) {
		return groupMapper.getGroupScoreByMonth(factory, area, groupName, monthly);
	}
	@Override
	public List<Group> getIndexAllByGroup(String factory, String area,
			String groupName) {
		return groupMapper.getIndexAllByGroup(factory, area, groupName);
	}
	@Override
	public List<Group> getGroupByFa(String factory, String area) {
		return groupMapper.getGroupByFa(factory, area);
	}

}
