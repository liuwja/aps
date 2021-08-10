package com.peg.service.impl.jxmb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.jxmb.PerMemberMapper;
import com.peg.model.jxmb.PerItem;
import com.peg.model.jxmb.PerMember;
import com.peg.service.AbstractService;
import com.peg.service.jxmb.PerMemberServicel;

@Service("permenberService")
public class PerMemberServiceImpl  extends AbstractService<PerMember, Long>implements PerMemberServicel{

	@Autowired
	private PerMemberMapper memberMapper;
	
	@Override
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(memberMapper);
		
	}

	@Override
	public List<PerMember> getAllGroup() {
		// TODO Auto-generated method stub
		return memberMapper.getAllGroup();
	}

	@Override
	public List<PerMember> getAllGroupFromMes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerMember getGroupByFag(String factory, String area, String groupCategory, String groupName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PerMember> getGroupByFa(String factory, String area) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PerItem> getMonthAllByPage(BaseSearch bs,  boolean isPage) {
		// TODO Auto-generated method stub
		if(isPage) {
			return memberMapper.getMonthAllByPage(bs);
		} else {
			return memberMapper.getMonthAll(bs);
		}
	}

	@Override
	public List<PerMember> getGroupScoreByMonth(String factory, String area, String groupName, String monthly) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PerMember> getIndexAllByGroup(String factory, String area, String groupName) {
		// TODO Auto-generated method stub
		return null;
	}

}
