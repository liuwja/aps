package com.peg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.BoxDefectRecordMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.BoxdefectrecordNew;
import com.peg.model.CommonVo;
import com.peg.model.bph.BoxDefectRecord;
import com.peg.model.system.SysMesUser;
import com.peg.service.BoxDefectRecordService;

@Service
public class BoxDefectRecordServiceImpl implements BoxDefectRecordService {

	@Autowired
	private BoxDefectRecordMapper boxDefectRecordMapper;
	@Override
	public List<BoxdefectrecordNew> list(CommonVo vo,
			PageParameter page) {		
		BaseSearch bs = new BaseSearch();
		//设置分页信息
		bs.setPage(page);
		//设置查询条件
		bs.put("factory", vo.getBaseFactory());
		bs.put("group", vo.getBaseGroup());
		bs.put("billType", vo.getBillType());
		bs.put("partType", vo.getPartType());
		bs.put("productNum", vo.getProductNum());
		bs.put("startTime", vo.getStartTime());
		bs.put("endTime", vo.getEndTime());
		bs.put("region", vo.getRegion());
		String regions = vo.getRegionListTxt();
		String[] regionArr = null;
		if(regions != null && regions !=""){
			String[] r = regions.split(";");
			regionArr = new String[r.length];
			for(int i =0;i<r.length;i++){
				String s1 = r[i].substring(0,r[i].length()-4);
				regionArr[i] = s1;
			}
		}
		//bs.put("region",vo.getRegionListTxt().split(","));
		if(vo.getSupplierListTxt()!=null && !StringUtils.isNotEmpty(vo.getSupplierListTxt())){
			bs.put("suppliers",vo.getSupplierListTxt().split(","));
		}
		List<BoxdefectrecordNew> list = boxDefectRecordMapper.getNewBoxDefectAllByPage(bs);
		return list;
	}
	@Override
	public BoxdefectrecordNew findOne(BoxdefectrecordNew boxDefectRecord) {
		
		return boxDefectRecordMapper.findOne(boxDefectRecord);
	}
	@Override
	public void saveDuty(BoxdefectrecordNew boxDefectRecord, SysMesUser user) {
		String name = user.getDescription();
		String primaryDutyS = boxDefectRecord.getDuty1();
		if(primaryDutyS!=null&&!primaryDutyS.equals("")) {
			boxDefectRecord.setDuty1Name(name);
		}
		String ultimateDutyS = boxDefectRecord.getDuty2();
		if(ultimateDutyS!=null&&!ultimateDutyS.equals("")) {
			boxDefectRecord.setDuty2Name(name);
		}
		boxDefectRecordMapper.saveDuty(boxDefectRecord);
	}
	@Override
	public List<BoxDefectRecord> findAll(BoxDefectRecord boxDefectRecord) {
		return boxDefectRecordMapper.findAll(boxDefectRecord);
	}

}
