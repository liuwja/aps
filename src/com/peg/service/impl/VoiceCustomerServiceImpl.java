package com.peg.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peg.dao.VoiceCategoryMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CommonVo;
import com.peg.model.FaultType;
import com.peg.model.VoiceCategory;
import com.peg.service.VoiceCategoryServiceI;

@Service
@Transactional
public class VoiceCustomerServiceImpl implements VoiceCategoryServiceI {
	@Autowired
	private VoiceCategoryMapper voiceCategoryMapper;

	@Override
	public Integer findAll(CommonVo vo) {
		return voiceCategoryMapper.findAllCount();
	}

	@Override
	public Map<String,Object> updateVoiceCategory(CommonVo vo) {
		Map<String,Object> countMap = new HashMap<String,Object>();
		try{
			//从中间表获取新的需要更新的分类数据
			List<VoiceCategory> newList = voiceCategoryMapper.findNew();
			List<VoiceCategory> updateList = new ArrayList<VoiceCategory>();
			List<VoiceCategory> addList = new ArrayList<VoiceCategory>();
			List<VoiceCategory> deleteList = new ArrayList<VoiceCategory>();
			
			//根据状态码,调用不同的方法做处理
			for(VoiceCategory v:newList){
				v.setUpdateTime(new Date());
				if(v.getState()==null){
					v.setState(0);
				}
				if(v.getState() == 0){
					//新增
					addList.add(v);
				}else if(v.getState() == 1){
					//更新
					updateList.add(v);
				}else if(v.getState() == 2){
					//删除
					deleteList.add(v);
				}
			}
			
			if(addList.size()>0) {
				for(VoiceCategory v : addList){
					voiceCategoryMapper.add(v);					
				}
				countMap.put("add",addList.size());
			}
			if(updateList.size()>0) {
				voiceCategoryMapper.update(addList);
				countMap.put("update",updateList.size());
			}
			if(deleteList.size()>0) {
				voiceCategoryMapper.delete(deleteList);
				countMap.put("delete",deleteList.size());
			}
			//修改完成,保存修改时间
			for(VoiceCategory v: newList){
				voiceCategoryMapper.updateTime(v);
			}
			countMap.put("result",0);
			return countMap;
		}catch(Exception e){
			e.printStackTrace();
			countMap.put("result",1);
			countMap.put("msg",e.toString());
			return countMap;
		}
		
	}

	@Override
	public List<VoiceCategory> findAllByPage(CommonVo vo,PageParameter page) {
		Integer end = page.getPageNum()*page.getNumPerPage()+1;
		Integer start = (page.getPageNum()-1)*page.getNumPerPage();
		return voiceCategoryMapper.findByPag(start,end);
	}

	@Override
	public List<VoiceCategory> findAllByPage(VoiceCategory voice, PageParameter page) {
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("paterName", voice.getPaterName());
		bs.setHashMap(hashMap);
		return voiceCategoryMapper.findAllByPage(bs);
	}

	@Override
	public List<VoiceCategory> findAllPater(VoiceCategory voice) {
		return voiceCategoryMapper.findAllPater(voice);
	}

	@Override
	public VoiceCategory getVoiceCategorybykey(Long u) {
		// TODO Auto-generated method stub
		return voiceCategoryMapper.getVoiceCategorybykey(u);
	}

	@Override
	public List<VoiceCategory> findAll(VoiceCategory voice) {
		
		return voiceCategoryMapper.findAll(voice);
	}

}
