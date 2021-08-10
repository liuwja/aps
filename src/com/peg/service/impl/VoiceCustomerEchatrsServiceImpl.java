package com.peg.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.VoiceCustomerEchatrsMapper;
import com.peg.mes.highcharts.ChartObj;
import com.peg.model.CommonVo;
import com.peg.model.VoiceEchart;
import com.peg.service.VoiceCustomerEchatrsService;

@Service("voiceCustomerEchatrsService")
public class VoiceCustomerEchatrsServiceImpl implements VoiceCustomerEchatrsService {

	@Autowired
	private VoiceCustomerEchatrsMapper voiceCustomerEchatrsMapper;
	
	@Override
	public ChartObj findXAndY(CommonVo vo) {
		System.out.println(vo.getRegions());
		System.out.println(vo.getPartTypes());
		List<VoiceEchart> findXY = new ArrayList<VoiceEchart>();
		if(vo.getProductFamilyTxt()!=null&&!vo.getProductFamilyTxt().equals("")){
			vo.setProductFamilyList(Arrays.asList(vo.getProductFamilyTxt().split(",")));
		}
		if(vo.getTitle().equals("DATE_TIME")){
			 findXY = voiceCustomerEchatrsMapper.findXYByTime(vo);
		}else{
			 findXY = voiceCustomerEchatrsMapper.findXY(vo);			
		}
		ChartObj obj = new ChartObj();
		List<String> xValues = new ArrayList<String>();
		List<Long> yValues = new ArrayList<Long>();
		int count = 1;
		int maxCount = vo.getxCount(); 
		for(VoiceEchart v :findXY ){
			String xName = v.getxName();
			Long yValue = v.getyValue();
			if(count>maxCount){
				break;
			}
			if(vo.getTitle().equals("SERVICE_CENTER")){
				if(xName.indexOf("服务中心")!=-1){
					xName = xName.substring(0,xName.length()-4);
				}
			}
			if(vo.getTitle().equals("DATE_TIME")){
				/*if(xName!=null && !xName.equals("")){
					xName = xName.substring(0,xName.length()-8);					
				}*/
			}
			if(xName == null||xName.equals("")){
				xName = "未知";
			}
			xValues.add(xName);
			yValues.add(yValue);
			count ++;
		}
		obj.setxValue(xValues);
		obj.setyValue(yValues);
		if(vo.getTitle().equals("DATE_TIME")){
			obj.setChartType("line");
		}else{
			obj.setChartType("bar");
			
		}
		return obj;
	}

}
