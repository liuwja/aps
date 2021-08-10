package com.peg.qms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.model.CommonVo;
import com.peg.model.LaterSumtime;
import com.peg.model.NewInstallation;
import com.peg.service.CommonServiceI;
import com.peg.service.LaterSumtimeServiceI;
import com.peg.service.NewInstallationCountService;
import com.peg.web.util.ConditionUtil;
import com.peg.web.util.ExcelUtilities;
import com.peg.web.util.StatisUtils;
import com.peg.web.util.WebUtil;

@RequestMapping("newInstall")
@Controller
public class NewInstallationCountController extends BaseController {

	@Autowired
	private CommonServiceI commonService;
	@Autowired
	private NewInstallationCountService newInstallationCountService;
	@Autowired
	private LaterSumtimeServiceI laterSumtimeService;
	

	
	/**
	 * 正三角阵
	 * @param model
	 * @return
	 */
	@RequestMapping("trgNewInstallationCount")
	public String trgMatrixInsSigleCount(Model model, CommonVo vo) {
		setBaseData(model);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadGasType(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		if(vo.getInsEndTime()==null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			vo.setInsEndTime(format.format(new Date()));
		}
		List<NewInstallation> list = null;
		if(vo.getProductType()!=null){
				list = loadConDiffData(model, vo);
		}
		model.addAttribute("vo", vo);
		model.addAttribute("list", list);
		return "qms/table/time/trgNewInstallationCount";
	}
	@RequestMapping("/excelOutput_trgNewInstallationCount")
	public String excelOutput_trgInsSigleCount(CommonVo vo, Model model,HttpServletRequest request, HttpServletResponse response) {
		String retView = "error/err";
		setBaseData(model);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadGasType(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		LaterSumtime laterTime = laterSumtimeService.getLaterDate();
		if (vo.getInsEndTime() == null) {
			vo.setInsEndTime(laterTime.getSumMonth());
		}
		try {
			List<NewInstallation> list = loadConDiffData(model, vo);
			String[] column = {"M3","M3累计","M6","M6累计","M9","M9累计","M12","M12累计"};
			//表头
			String[] CN = null;
			if(vo.getStartI()==null){
			CN = new String[column.length + 3];
			CN[0] = "安装月份";
			CN[1] = "安装数";
			CN[2] = "累计安装数";
				for(int j=2;j<=CN.length-2;j++){
					CN[j+1]=column[j-2];
				}
			}else{
				CN = new String[5];
				CN[0] = "安装月份";
				CN[1] = "安装数";
				CN[2] = "累计安装数";
				CN[3] = vo.getStartI()+"-"+vo.getEndI()+"维修";
				CN[4]= vo.getStartI()+"-"+vo.getEndI()+"累计维修";
			}
			//表体数据
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				NewInstallation tmpVO = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getBaseMonth();
				tmpStr[1] = Long.toString(tmpVO.getBaseCount());
				tmpStr[2] = Long.toString(tmpVO.getAllBaseCount());
				for (int j = 0; j < tmpVO.getReCount().size(); j++) {
					if(j+3>=tmpStr.length){
						continue;
					}
					String Count = null;
					boolean f = false;
					if(vo.getStartI()!=null){
						if(tmpVO.getL()>=vo.getEndI()) f=true;
					}else{
						if(j<=1&&tmpVO.getL()>=3||j>1&&j<=3&&tmpVO.getL()>=6||j>3&&j<=5&&tmpVO.getL()>=9||j>5&&j<=7&&tmpVO.getL()>=12) f=true;
					}
					if(f){
						if (tmpVO.getReCount().get(j) == null) {
							Count = " ";
						} else if(vo.getStatisData() != null && vo.getStatisData().equals("repairRate1")) {
								Count = Long.toString(tmpVO.getRepairCount().get(j));
						} else if(vo.getStatisData() != null && vo.getStatisData().equals("repairRate2")){
								Count = Double.toString(tmpVO.getRepair2Count().get(j));
						}else {
								Count = Long.toString(tmpVO.getReCount().get(j));					
						}
						tmpStr[j + 3] = Count;
					}
				}
				excelList.add(tmpStr);
			}
			// 获取项目根目录
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath,
					contentType, "新品安装分析正三角阵" + fname);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			model.addAttribute("message", "导出失败！请联系管理人员");
		}
		return retView;
	}
	
	private List<NewInstallation> loadConDiffData(Model model,CommonVo vo){
		//装载查询条件
		if (StringUtils.isNotEmpty(vo.getPartTypeListTxt())) {
			vo.setPartTypes(vo.getPartTypeListTxt().replaceAll("'", "").split(","));
		}
		if (StringUtils.isNotEmpty(vo.getRegionListTxt())) {
			vo.setRegions(vo.getRegionListTxt().replaceAll("'", "").split(","));
		}
		if (StringUtils.isNotEmpty(vo.getProductFamilyTxt())) {
			vo.setProductFamilys(vo.getProductFamilyTxt().replaceAll("'", "").split(","));
		}
		// 故障小类
		if (StringUtils.isNotEmpty(vo.getFaultReasonTxt())) {
			vo.setFaultReasons(vo.getFaultReasonCode().split(","));
		}
		//合并故障小类
		if(StringUtils.isNotEmpty(vo.getMeshFaultReasonCode())){
			vo.setMeshFaultReasons(vo.getMeshFaultReasonCode().split(","));
		}
		// 故障大类
		if (StringUtils.isNotEmpty(vo.getFaultTypeTxt())) {
			vo.setFaultTypes(vo.getFaultTypeCode().split(","));
		}
		//气源
		if(StringUtils.isNotEmpty(vo.getGasCategoryTxt())){
			vo.setGasCategorys(vo.getGasCategoryTxt().replaceAll("'","").split(","));
		}
		//获取安装月份INSTALL_MONTH和安装数install_count
		List<CommonVo> insCountGroupByReMonth = commonService.getInsCountGroupByReMonth(vo);
		List<String> betweenMonth = WebUtil.getBetweenMonthList(vo.getInsStartTime(),vo.getInsEndTime());
		for(int i=0;i<betweenMonth.size();i++){
			if(i>insCountGroupByReMonth.size()-1){
				CommonVo vc = new CommonVo();
				vc.setInstallMonth(betweenMonth.get(i));
				vc.setInstallCount(0L);
				insCountGroupByReMonth.add(vc);
			}else if(!insCountGroupByReMonth.get(i).getInstallMonth().equals(betweenMonth.get(i))){
				CommonVo vc = new CommonVo();
				vc.setInstallMonth(betweenMonth.get(i));
				vc.setInstallCount(0L);
				insCountGroupByReMonth.add(i,vc);
			}
		}
		//获取安装月份,维修月份和维修数
		List<CommonVo> insCountGroupByMonth = commonService.getInsCountGroupByMonth(vo);
		List<NewInstallation> resultList = new ArrayList<NewInstallation>();
		//获取当前时间的日
		Integer newDay = Calendar.getInstance().get(Calendar.DATE);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String newTime = format.format(new Date());
		if(vo.getStartI()!=null){
			Map<String,Long> resultMap = new HashMap<String,Long>();
			int startI = vo.getStartI();
			int endI = vo.getEndI();
			for(CommonVo o:insCountGroupByMonth){
				String insMonth = o.getInstallMonth();
				String repMonth = o.getRepairMonth();
				Long count = o.getRepairCount();
				Integer l = WebUtil.getMonthQuantity(insMonth, repMonth);
				if(l>=startI-1 && l<=endI){
					if(!resultMap.containsKey(insMonth)){
						resultMap.put(insMonth,count);
					}else{
						resultMap.put(insMonth,resultMap.get(insMonth)+count);
					}
				}
			}
			Long allBaseCount = 0L;
			Long m = 0L;
			Integer c = vo.getEndI()-vo.getStartI()+1;
			Integer countM = 0;
			for(CommonVo o:insCountGroupByReMonth){
				
				NewInstallation resultVo = new NewInstallation();
				//基准月份
				resultVo.setBaseMonth(o.getInstallMonth());
				Integer l = WebUtil.getMonthQuantity(o.getInstallMonth(),newTime);				
				resultVo.setL(l);
				//安装数
				resultVo.setBaseCount(o.getInstallCount());
				//维修数List
				Long installCount = o.getInstallCount();
				List<Long> reCountList = new ArrayList<Long>();
				List<Long> repairCountList = new ArrayList<Long>();
				List<Double> repair2CountList = new ArrayList<Double>();
				for(int i=0;i<2;i++){
					repairCountList.add(0L);
					repair2CountList.add(0.00);
				}
				Long long1 = resultMap.get(o.getInstallMonth());
				if(long1==null) long1=0L;
				if(countM>11){
					m = m - (resultMap.get(insCountGroupByReMonth.get(countM-12).getInstallMonth())==null?0:resultMap.get(insCountGroupByReMonth.get(countM-12).getInstallMonth()));
					allBaseCount = allBaseCount - insCountGroupByReMonth.get(countM-12).getInstallCount();
				}
				allBaseCount = allBaseCount + o.getInstallCount();
				//累计安装数
				resultVo.setAllBaseCount(allBaseCount);
				
					m = m + long1;
					//维修数
					reCountList.add(long1);//0
					reCountList.add(m);
					//维修率
					if(installCount!=0){
						repair2CountList.set(0,Double.parseDouble(String.format("%.2f",reCountList.get(0)*100*12/(installCount.doubleValue()*c))));						
						repairCountList.set(0,reCountList.get(0)*1000000*12/(installCount*c));
					}
					if(allBaseCount!=0){
						repair2CountList.set(1,Double.parseDouble(String.format("%.2f",reCountList.get(1)*100*12/(allBaseCount.doubleValue()*c))));						
						repairCountList.set(1,reCountList.get(1)*1000000*12/(allBaseCount*c));						
					}
					
				
				
				resultVo.setReCount(reCountList);
				resultVo.setRepairCount(repairCountList);
				resultVo.setRepair2Count(repair2CountList);
				resultList.add(resultVo);
				countM++;
			}		
		}else{
			Map<String,Long[]> resultMap = new HashMap<String,Long[]>();
			//map集合key对应安装月份 value对应该安装月份下的维修数Long[]数组,长度为4,每个位置储存3个月的维修数的和
			for(CommonVo o:insCountGroupByMonth){
				String insMonth = o.getInstallMonth();
				String repMonth = o.getRepairMonth();
				Long count = o.getRepairCount();
				if(!resultMap.containsKey(insMonth)){
					Long[] l = {0L,0L,0L,0L};
					resultMap.put(insMonth,l);
				}
				int l = WebUtil.getMonthQuantity(insMonth, repMonth);
				//0 1 2 3 4 5 6 7 8 9 10 11 
				//1 2 3 4 5 6 7 8 9 10 11 12
				if(l<=2){
					resultMap.get(insMonth)[0] = resultMap.get(insMonth)[0]+count;
				}else if(l>2&&l<=5){
					
					resultMap.get(insMonth)[1] = resultMap.get(insMonth)[1]+count;
				}else if(l>5&&l<=8){
					
					resultMap.get(insMonth)[2] = resultMap.get(insMonth)[2]+count;
				}else if(l>8&&l<=11){
					
					resultMap.get(insMonth)[3] = resultMap.get(insMonth)[3]+count;
				}
			}
			Long m3 = 0L;
			Long m6 = 0L;
			Long m9 = 0L;
			Long m12 = 0L;
			Long allBaseCount = 0L;
			
			int countM = 0;
			for(CommonVo o:insCountGroupByReMonth){
				NewInstallation resultVo = new NewInstallation();
				//基准月份
				resultVo.setBaseMonth(o.getInstallMonth());
				//安装数
				resultVo.setBaseCount(o.getInstallCount());
				//显示控制器
				int l = WebUtil.getMonthQuantity(o.getInstallMonth(), newTime);
				resultVo.setL(l);
				//维修数List
				Long installCount = o.getInstallCount();
				List<Long> reCountList = new ArrayList<Long>();
				List<Long> repairCountList = new ArrayList<Long>();
				List<Double> repair2CountList = new ArrayList<Double>();
				Long[] repairCount =  resultMap.get(o.getInstallMonth());
				if(countM>11){
					Long[] longs = resultMap.get(insCountGroupByReMonth.get(countM-12).getInstallMonth());
					if(longs == null) {
						Long[] longss = {0L,0L,0L,0L};
						longs = longss;
					}
					m3 = m3 - longs[0];
					m6 = m6 - longs[0]-longs[1];
					m9 = m9 - longs[0]-longs[1]-longs[2];
					m12 = m12 - longs[0]-longs[1]-longs[2]-longs[3];
					allBaseCount = allBaseCount - insCountGroupByReMonth.get(countM-12).getInstallCount();
				}
				allBaseCount = allBaseCount + o.getInstallCount();
				//累计安装数
				resultVo.setAllBaseCount(allBaseCount);
				
				if(repairCount == null){
					Long[] logs = {0L,0L,0L,0L};
					repairCount = logs;
				}
				
					m3 = m3 + repairCount[0];
					m6 = m6 + repairCount[0]+repairCount[1];
					m9 = m9 + repairCount[0]+repairCount[1]+repairCount[2];
					m12 = m12 +repairCount[0]+repairCount[1]+repairCount[2]+repairCount[3];
					//维修数
					reCountList.add(repairCount[0]);//0
					reCountList.add(m3);			
					reCountList.add(reCountList.get(0)+repairCount[1]);
					reCountList.add(m6);
					reCountList.add(reCountList.get(2)+repairCount[2]);
					reCountList.add(m9);
					reCountList.add(reCountList.get(4)+repairCount[3]);
					reCountList.add(m12);
					//维修率
					for(int i=0;i<8;i++){
						repairCountList.add(0L);
						repair2CountList.add(0.00);
					}
					if(installCount!=0){
						repair2CountList.set(0,Double.parseDouble(String.format("%.2f",reCountList.get(0)*100*4/installCount.doubleValue())));
						repair2CountList.set(2,Double.parseDouble(String.format("%.2f",reCountList.get(2)*100*2/installCount.doubleValue())));
						repair2CountList.set(4,Double.parseDouble(String.format("%.2f",reCountList.get(4)*100*12/(installCount.doubleValue()*9))));
						repair2CountList.set(6,Double.parseDouble(String.format("%.2f",reCountList.get(6)*100/installCount.doubleValue())));
						repairCountList.set(0,reCountList.get(0)*1000000*4/installCount);
						repairCountList.set(2,reCountList.get(2)*1000000*2/installCount);
						repairCountList.set(4,reCountList.get(4)*1000000*12/(installCount*9));
						repairCountList.set(6,reCountList.get(6)*1000000/installCount);
					}
					if(allBaseCount!=0){
						repair2CountList.set(1,Double.parseDouble(String.format("%.2f",reCountList.get(1)*100*4/allBaseCount.doubleValue())));
						repair2CountList.set(3,Double.parseDouble(String.format("%.2f",reCountList.get(3)*100*2/allBaseCount.doubleValue())));
						repair2CountList.set(5,Double.parseDouble(String.format("%.2f",reCountList.get(5)*100*12/(allBaseCount.doubleValue()*9))));
						repair2CountList.set(7,Double.parseDouble(String.format("%.2f",reCountList.get(7)*100/allBaseCount.doubleValue())));
						repairCountList.set(1,reCountList.get(1)*1000000*4/allBaseCount);						
						repairCountList.set(3,reCountList.get(3)*1000000*2/allBaseCount);						
						repairCountList.set(5,reCountList.get(5)*1000000*12/(allBaseCount*9));						
						repairCountList.set(7,reCountList.get(7)*1000000/allBaseCount);
					}					
				
				
				resultVo.setReCount(reCountList);
				resultVo.setRepairCount(repairCountList);
				resultVo.setRepair2Count(repair2CountList);
				resultList.add(resultVo);
				countM++;
			}
		}
		Long maxRepairCount = 0L;
		Double maxRepair2Count = 0.00;
		if(!vo.getStatisData().equals("repairCount")){
				if(vo.getStartI()!=null){
					for(int i=0;i<resultList.size();i++){
						//if(resultList.get(i).getReCount().get(0)>maxCount) maxCount=resultList.get(i).getReCount().get(0);
						if(resultList.get(i).getRepairCount().get(0)>maxRepairCount) maxRepairCount = resultList.get(i).getRepairCount().get(0);
						if(resultList.get(i).getRepair2Count().get(0)>maxRepair2Count) maxRepair2Count = resultList.get(i).getRepair2Count().get(0);
					}
				}else{
					for(int i=0;i<resultList.size();i++){
						List<Long> repairCount = resultList.get(i).getRepairCount();
						List<Double> repair2Count = resultList.get(i).getRepair2Count();
						if(repairCount.get(0)>maxRepairCount) maxRepairCount = repairCount.get(0);
						if(repairCount.get(2)>maxRepairCount) maxRepairCount = repairCount.get(2);
						if(repairCount.get(4)>maxRepairCount) maxRepairCount = repairCount.get(4);
						if(repairCount.get(6)>maxRepairCount) maxRepairCount = repairCount.get(6);
						
						if(repair2Count.get(0)>maxRepair2Count) maxRepair2Count = repair2Count.get(0);
						if(repair2Count.get(2)>maxRepair2Count) maxRepair2Count = repair2Count.get(2);
						if(repair2Count.get(4)>maxRepair2Count) maxRepair2Count = repair2Count.get(4);
						if(repair2Count.get(6)>maxRepair2Count) maxRepair2Count = repair2Count.get(6);
					}
				}

		}
		if(StringUtils.isNotEmpty(vo.getMaxCount())){
			vo.setRepairPercent(vo.getMaxCount());
			 List<Integer> arrageIntList = StatisUtils.getArrageIntList(maxRepairCount.intValue(),null);
			model.addAttribute("maxRepairCount",arrageIntList);
			 List<Double> arrageList = StatisUtils.getArrageList(maxRepair2Count,null);
			model.addAttribute("maxRepair2Count",arrageList);
		}else{
			model.addAttribute("maxRepairCount", StatisUtils.getArrageIntList(maxRepairCount.intValue(), null));
			model.addAttribute("maxRepair2Count", StatisUtils.getArrageList(maxRepair2Count, null));			
		}
		return resultList;
	}
}
