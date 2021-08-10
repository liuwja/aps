/*
 * @(#) ComputeScoreServiceImpl.java 2015-7-27 上午09:13:39
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.service.impl.bph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.CheckItemMapper;
import com.peg.dao.bph.GroupCategoryMapper;
import com.peg.dao.bph.GroupMapper;
import com.peg.dao.bph.IndexMapper;
import com.peg.dao.bph.IndexScroeMapper;
import com.peg.dao.bph.ItemScoreMapper;
import com.peg.dao.bph.MesDataSumMapper;
import com.peg.dao.bph.MonthAssessmentMapper;
import com.peg.dao.bph.QprnSettingMapper;
import com.peg.model.bph.Group;
import com.peg.model.bph.IndexScroe;
import com.peg.model.bph.ItemScore;
import com.peg.model.bph.MesDataSum;
import com.peg.service.bph.NComputeScoreServiceI;

/**
 * TODO
 * <p>
 * @author Lin, 2015-7-27 上午09:13:39
 */
@Service("ncomputeScoreService")
public class NComputeScoreServiceImpl implements NComputeScoreServiceI
{
	@Autowired
	private IndexScroeMapper indexScroeMapper;
	
	@Autowired
	private ItemScoreMapper itemScoreMapper;

	@Autowired
	private IndexMapper indexMapper;
	
	@Autowired
	private CheckItemMapper checkItemMapper;
	
	@Autowired
	private MesDataSumMapper mesDataSumMapper;
	
	@Autowired
	private QprnSettingMapper qprnMap;
	
	@Autowired
	private MonthAssessmentMapper monthAssessMap;
	
	@Autowired
	private GroupCategoryMapper groupCategoryMapper;
	
	@Autowired
	private MonthAssessmentMapper monthAssessmentMapper;
	
	@Autowired
	private GroupMapper groupMapper;
	

	@Override
	public int insertIndexScore(IndexScroe record)
	{
		
		return indexScroeMapper.insert(record);
	}


	@Override
	public int insertIndexScore(List<IndexScroe> recordList)
	{
		
		return indexScroeMapper.insertList(recordList);
	}


	@Override
	public int insertItemScore(List<ItemScore> recordList)
	{
		
		return itemScoreMapper.insertList(recordList);
	}


	@Override
	public int insertItemScore(ItemScore record)
	{
		return itemScoreMapper.insert(record);
	}

	@Override
	public Map<String, Map<String,Long>> QueryBoxDefectSource(
			MesDataSum mesDataSum) {
		Map<String,Map<String,Long>> boxMap = new HashMap<String, Map<String,Long>>();
		List<MesDataSum> list = mesDataSumMapper.boxDefectSource(mesDataSum);
		if(list == null || list.isEmpty()){
			return boxMap;
		}
		Map<String,Long> bMap = null;
		String facShift = null;
		for(MesDataSum mesData : list){
			facShift = mesData.getFactory() + "-" + mesData.getShiftGroup();
			if(!boxMap.containsKey(facShift)){
				bMap = new HashMap<String,Long>();
				boxMap.put(facShift,bMap);
			}
			boxMap.get(facShift).put(mesData.getDefectSource(),mesData.getCol16());
		}
		return boxMap;
	}


	@Override
	public int insertIndexScoreDay(List<IndexScroe> recordList) {
		
		return indexScroeMapper.insertDayList(recordList);
	}


	@Override
	public Map<String, Map<String, Long>> QueryBoxDefectSourceByDay(
			MesDataSum mesDataSum) {
		Map<String,Map<String,Long>> boxMap = new HashMap<String, Map<String,Long>>();
		List<MesDataSum> list = mesDataSumMapper.boxDefectSourceByDay(mesDataSum);
		if(list == null || list.isEmpty()){
			return boxMap;
		}
		Map<String,Long> bMap = null;
		String facShift = null;
		for(MesDataSum mesData : list){
			facShift = mesData.getFactory() + "-" + mesData.getShiftGroup();
			if(!boxMap.containsKey(facShift)){
				bMap = new HashMap<String,Long>();
				boxMap.put(facShift,bMap);
			}
			boxMap.get(facShift).put(mesData.getDefectSource(),mesData.getCol16());
		}
		return boxMap;
	}


	@Override
	public List<MesDataSum> getMesDataAll(MesDataSum mesDataSum) {
		
		return mesDataSumMapper.getMesDataAll(mesDataSum);
	}


	@Override
	public Map<String, Map<String, Long>> QueryProcessAuditRecordByMonth(
			MesDataSum mesDataSum) {
		Map<String,Map<String, Long>> proMap = new HashMap<String, Map<String,Long>>();
		List<MesDataSum> list = mesDataSumMapper.processAuditRecordByMonth(mesDataSum);
		if(list ==null || list.isEmpty()){
			return proMap;
		}
		Map<String, Long> pMap = null;
		String facShift = null;
		for(MesDataSum mesData : list){
			facShift  = mesData.getFactory() +"-" +mesData.getShiftGroup();
			if(!proMap.containsKey(facShift)){
				pMap = new HashMap<String, Long>();
				proMap.put(facShift, pMap);
			}
			proMap.get(facShift).put(mesData.getCol14(), mesData.getCol2());
		}
		return proMap;
	}


	@Override
	public Map<String, Map<String, Long>> QueryProcessAuditRecordByDay(
			MesDataSum mesDataSum) {
		Map<String,Map<String,Long>> proMap = new HashMap<String, Map<String,Long>>();
		List<MesDataSum> list = mesDataSumMapper.processAuditRecordByDay(mesDataSum);
		if(list == null || list.isEmpty()){
			return proMap;
		}
		Map<String ,Long> pMap = null;
		String facShift = null;
		for(MesDataSum mesData : list){
			facShift = mesData.getFactory() + "-" + mesData.getShiftGroup();
			if(!proMap.containsKey(facShift)){
				pMap = new HashMap<String, Long>();
				proMap.put(facShift, pMap);
			}
			proMap.get(facShift).put(mesData.getCol14(),mesData.getCol2());
		}
		return proMap;
	}


	@Override
	public Map<String, Map<String, Double>> QueryBatchDeFectByMonth(
			MesDataSum mesDataSum) {
		Map<String,Map<String,Double>> batchMap = new HashMap<String, Map<String,Double>>();
		List<MesDataSum> list = mesDataSumMapper.batchDefectByMonth(mesDataSum);
		if(list == null || list.isEmpty()){
			return batchMap;
		}
		Map<String,Double> bMap = null;
		String facShift = null;
		for(MesDataSum mesData : list){
			facShift = mesData.getFactory() + "-" + mesData.getShiftGroup();
			if(!batchMap.containsKey(facShift)){
				bMap = new HashMap<String, Double>();
				batchMap.put(facShift, bMap);
			}
			batchMap.get(facShift).put(mesData.getCol14(), mesData.getCol9());
		}
		return batchMap;
	}


	@Override
	public Map<String, Map<String, Double>> QueryBatchDefectByDay(
			MesDataSum mesDataSum) {
		Map<String,Map<String,Double>> batchMap = new HashMap<String, Map<String,Double>>();
		List<MesDataSum> list = mesDataSumMapper.batchDefectByDay(mesDataSum);
		if(list == null || list.isEmpty()){
			return batchMap;
		}
		Map<String,Double> bMap = null;
		String facShift = null;
		for(MesDataSum mesData : list){
			facShift = mesData.getFactory() + "-" + mesData.getShiftGroup();
			if(!batchMap.containsKey(facShift)){
				bMap = new HashMap<String, Double>();
				batchMap.put(facShift, bMap);
			}
			batchMap.get(facShift).put(mesData.getCol14(), mesData.getCol9());
		}
		return batchMap;
	}

//
//	@Override
//	public Map<String, Object> QueryQPRNScore() {
//		Map<String,Object> QPRNMap = new HashMap<String, Object>();
//		List<QprnSetting> list = qprnMap.getAll();
//		if(list == null || list.isEmpty()){
//			return QPRNMap;
//		}
//		for(QprnSetting qprn : list){
//			QPRNMap.put(qprn.getQprn(), qprn.getScore());
//		}
//		return QPRNMap;
//	}


	@Override
	public List<MesDataSum> getMesDataDayAll(MesDataSum mesDataSum) {
		return mesDataSumMapper.getMesDataDayAll(mesDataSum);
	}

	@Override
	public Map<String, Group> QueryMonthAssessment(MesDataSum mesDataSum) {
		String mon = null;
		if(mesDataSum.getQueryMonth() != null && ! "".equals(mesDataSum.getQueryMonth())) {
			mon = mesDataSum.getQueryMonth();
		}
		Map<String,Group> groupMap = new HashMap<String,Group>();
		List<Group> list = groupMapper.getMonthAllByMonth(mon);
		if(list == null || list.isEmpty()) {
			return groupMap;
		}
		String facShift = null;
		for(Group group : list){
			facShift = group.getFactory()+ "-" + group.getGroupName(); //工厂——班组
			if(!groupMap.containsKey(facShift)){
				groupMap.put(facShift, group);
			}
		}
		return groupMap;
	}


	@Override
	public Map<String, Map<String, Double>> QueryIpqcByMonth(
			MesDataSum mesDataSum) {
		Map<String,Map<String,Double>> map = new HashMap<String, Map<String,Double>>();
		List<MesDataSum> list = mesDataSumMapper.getIpqcByMonth(mesDataSum);
		if(list == null || list.isEmpty()){
			return map;
		}
		Map<String,Double> smap = null;
		String facS = null;
		for(MesDataSum data : list){
			facS = data.getFactory() +"-" + data.getShiftGroup();
			if(!map.containsKey(facS)){
				smap = new HashMap<String, Double>();
				map.put(facS, smap);
			}
			map.get(facS).put(data.getCol14(), data.getCol9());
		}
		return map;
	}


	@Override
	public Map<String, Map<String, Double>> QueryIpqcByDay(MesDataSum mesDataSum) {
		Map<String,Map<String,Double>> map = new HashMap<String, Map<String,Double>>();
		List<MesDataSum> list = mesDataSumMapper.getIpqcByDay(mesDataSum);
		if(list == null || list.isEmpty()){
			return map;
		}
		Map<String,Double> smap = null;
		String facS = null;
		for(MesDataSum data : list){
			facS = data.getFactory() +"-" + data.getShiftGroup();
			if(!map.containsKey(facS)){
				smap = new HashMap<String, Double>();
				map.put(facS, smap);
			}
			map.get(facS).put(data.getCol14(), data.getCol9());
		}
		return map;
	}

}
