package com.peg.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.CommonSelectedBoxMapper;
import com.peg.model.CommonSelectedBox;
import com.peg.service.CommonSelectedBoxService;
@Service("commonSelectedBoxService")
public class CommonSelectedBoxServiceImpl implements CommonSelectedBoxService{

	@Autowired
	private CommonSelectedBoxMapper comSelBoxMapper;
	
	@Override
	public List<CommonSelectedBox> getCommonArea(String factory) {
		
		return comSelBoxMapper.getCommonArea(factory);
	}

	@Override
	public List<CommonSelectedBox> getCommonShiftGroup(String factory, String area) {
		
		return comSelBoxMapper.getCommonShiftGroup(factory,area);
	}

	@Override
	public List<CommonSelectedBox> getCommonShiftGroupCategory(
			String factory) {
		
		return comSelBoxMapper.getCommonShiftGroupCategory(factory);
	}
    
	@Override
	public List<CommonSelectedBox> getCommonGroupCategoryByFacAr(
			String factory, String area) {
		
		return comSelBoxMapper.getCommonGroupCategoryByFacAr(factory, area);
	}

	@Override
	public List<CommonSelectedBox> getCommonCheckIndex(String area, String factory, String shiftGroupCategory) {
		
		return comSelBoxMapper.getCommonCheckIndex(area, factory, shiftGroupCategory);
	}

	@Override
	public List<CommonSelectedBox> getCommonFactory() {
		
		return comSelBoxMapper.getCommonFactory();
	}

	@Override
	public List<CommonSelectedBox> getCommonCheckIndexByFac(String factory,
			String area) {
		
		return comSelBoxMapper.getCommonCheckIndexByFac(factory, area);
	}

	@Override
	public List<CommonSelectedBox> getCommonCheckItem(String factory,
			String area) {
		
		return comSelBoxMapper.getCommonCheckItem(factory, area);
	}

	@Override
	public List<CommonSelectedBox> getGroupScore(String date, String group,String factory) {
		
		return comSelBoxMapper.getGroupScore(date, group,factory);
	}

	@Override
	public List<CommonSelectedBox> getFixGroupScore(String date, String group,String factory) {
		
		return comSelBoxMapper.getFixGroupScore(date, group, factory);
	}

	@Override
	public List<CommonSelectedBox> getShiftGroup(String factory) {
		
		return comSelBoxMapper.getShiftGroup(factory);
	}

	@Override
	public List<CommonSelectedBox> getFacAreaByGroup(String factory,String group) {
		
		return comSelBoxMapper.getFacAreaByGroup(factory,group);
	}

	@Override
	public List<CommonSelectedBox> getAreaS() {
		
		return comSelBoxMapper.getAreaS();
	}

	@Override
	public List<CommonSelectedBox> getCategoryS() {
		
		return comSelBoxMapper.getCategoryS();
	}

	@Override
	public List<CommonSelectedBox> getGroupS() {
		
		return comSelBoxMapper.getGroupS();
	}

	@Override
	public List<CommonSelectedBox> getCheckItemS() {
		
		return comSelBoxMapper.getCheckItemS();
	}

}
