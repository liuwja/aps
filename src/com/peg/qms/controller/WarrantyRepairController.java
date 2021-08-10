package com.peg.qms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.model.CommonVo;
import com.peg.model.LaterSumtime;
import com.peg.model.TimeMatrixResultVo;
import com.peg.service.CommonServiceI;
import com.peg.service.LaterSumtimeServiceI;
import com.peg.service.TimeMatrxServiceI;
import com.peg.web.util.ConditionUtil;
import com.peg.web.util.ExcelUtilities;
import com.peg.web.util.StatisUtils;
import com.peg.web.util.WebUtil;

@Controller
@RequestMapping("warrantyRepair")
public class WarrantyRepairController extends BaseController {
	
	@Autowired
	private CommonServiceI commonService;
	@Autowired
	private LaterSumtimeServiceI laterSumtimeService;
	@Autowired
	private TimeMatrxServiceI timeMatrxService;
	
	private static final int CUL_MONTHS = 120; //安装数据计算最大值
	
	private static double allInsCountGroup=0L;
	
	/**
	 * 保内维修正三角阵(30天间隔)
	 * @param model
	 * @return
	 */
	@RequestMapping("trgWarrantyRepairCount")
	public String trgMatrixInsSigleCount(Model model, CommonVo vo) {
		//加载搜索条件
		setBaseData(model);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		//创建前台数据返回格式
		List<TimeMatrixResultVo> list = null;
		String[] column = null;

		//类别不为空,productType 类别
		if(vo.getProductType()!=null){
			list = loadConDiffData(model, vo, CUL_MONTHS);
			Integer monthQuantity = WebUtil.getMonthQuantity(vo.getInsStartTime(), vo.getInsEndTime());
			//获得一个从1到指定长度的连续字符串数组
			column = StatisUtils.getArryStr(1,monthQuantity);
			
			if ("year".equals(vo.getStatisType())) { // 年月统计
				int length = list.get(0).getReCount().size() / 12;
				if (list.get(0).getReCount().size() % 12 != 0) {
					length++;
				}
				column = StatisUtils.getArryStr(1, length);
				List<TimeMatrixResultVo> yearList = yearCount(list, model, vo);					
			}
		}
		
		model.addAttribute("vo", vo);
		model.addAttribute("list", list);
		model.addAttribute("column", column);

		return "qms/table/time/trgWarrantyRepairCount";
	}
	
	/**
	 * 选择年时的统计
	 * @param list
	 * @param model
	 * @param vo
	 * @return
	 */
	private List<TimeMatrixResultVo> yearCount(List<TimeMatrixResultVo> list, Model model, CommonVo vo){
		Long maxCount = 0L;
		double maxPercent =0;
		//遍历list集合
		for(TimeMatrixResultVo v:list){
			List<Long> reCount = v.getReCount();				
			List<Long> totalCount = new ArrayList<Long>();
			Long sum =0L;
			int count = 0;
			for(int i=0;i<reCount.size();i++){
				sum+=reCount.get(i);
				count++;
				if(count==12||i==reCount.size()-1){
					totalCount.add(sum);
					if(sum>maxCount){
						maxCount=sum;
					}
					sum=0L;
					count=0;
				}
			}
			if(list.get(0)!= v){
				v.setPreDiff(list.get(0).getReCount().size() - totalCount.size());				
			}
			v.setReCount(totalCount);		
		}
		//当选择维修率时
		if(vo.getStatisData()!=null && vo.getStatisData().equals("repairRate")){
			for(TimeMatrixResultVo v:list){
				List<Long> newPercent = new ArrayList<Long>();
				for(Long l:v.getReCount()){
					Long d = Math.round((l*1000000)/allInsCountGroup);
					newPercent.add(d);
					if(d>maxPercent){
						maxPercent = d;
					}
				}

			

				v.setRepairPercent(newPercent);
			}
		}
		//维修数
		model.addAttribute("rangeList", StatisUtils.getArrageIntList(maxCount.intValue(), null));
		//维修率
		model.addAttribute("rangePercentList", StatisUtils.getArrageList(maxPercent, null));
		return list;
	}

	/**
	 * 保内维修倒三角阵(间隔30天)
	 * @param model
	 * @return
	 */
	@RequestMapping("trgDownWarrantyRepairCount")
	public String trgMatrixDownInsSigleCount(Model model, CommonVo vo) {
		setBaseData(model);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		String laterSumtime = (String) model.asMap().get("laterSumtime");
		if (vo.getInsEndTime() == null) {
			vo.setInsEndTime(laterSumtime);
		}
		List<TimeMatrixResultVo> list = null;
		String[] column = null;
		if(vo.getProductType()!=null){
			 list = loadConDiffData(model, vo, CUL_MONTHS);
			 column = StatisUtils.getArryStr(1, list.get(0).getReCount().size());
			// 年月统计
			if ("year".equals(vo.getStatisType())) {
				int length = list.get(0).getReCount().size() / 12;
				if (list.get(0).getReCount().size() % 12 != 0) {
					length++;
				}
				column = StatisUtils.getArryStr(1, length);
				List<TimeMatrixResultVo> yearList = yearCount(list, model, vo);
			}else{
				for(int i=0;i<list.size();i++){
					list.get(i).setPreDiff(i);
				}				
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("vo", vo);
		model.addAttribute("column", column);
		return "qms/table/time/trgDownWarrantyRepairCount";
	}
	
	/**
	 * 倒三角阵导出
	 */
	@RequestMapping("/excelOutput_trgDownWarrantyRepairCount")
	public String excelOutput_trgDownInsSigleCount(CommonVo vo, Model model,HttpServletRequest request, HttpServletResponse response) {
		String retView = "error/err";
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		LaterSumtime laterTime = laterSumtimeService.getLaterDate();
		if (vo.getInsEndTime() == null) {
			vo.setInsEndTime(laterTime.getSumMonth());
		}
		try {
			List<TimeMatrixResultVo> list = loadConDiffData(model, vo, CUL_MONTHS);
			String[] column = null;
			column = StatisUtils.getArryStr(1, list.get(0).getReCount().size());
			
			// 年月季度统计
			if ("year".equals(vo.getStatisType())) {
				int length = list.get(0).getReCount().size() / 12;
				if (list.get(0).getReCount().size() % 12 != 0) {
					length++;
				}
				column = StatisUtils.getArryStr(1, length);
				list = yearCount(list, model, vo);
			}else{
				for(int i=0;i<list.size();i++){
					list.get(i).setPreDiff(i);
				}
			}
			//表头
			String[] CN = new String[column.length + 2];
			CN[0] = "安装月份";
			CN[1] = "安装数";
			List<String> coluList = new ArrayList<String>();
			coluList.addAll(Arrays.asList(column));
			for(int j=1;j<=CN.length-2;j++){
				CN[j+1]=coluList.get(j-1);
			}
			//表体数据
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				TimeMatrixResultVo tmpVO = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getBaseMonth();
				tmpStr[1] = Long.toString(tmpVO.getBaseCount());
				for (int j = 0; j < tmpVO.getReCount().size(); j++) {
					int diff = tmpVO.getPreDiff();
					if(j+diff + 2>=tmpStr.length){
						continue;
					}
					for(int k=0;k<diff;k++){
						tmpStr[k+2] = " ";
					}
					String Count = " ";
					if(vo.getStatisData() != null && vo.getStatisData().equals("repairRate")) {
						Count = Long.toString(tmpVO.getRepairPercent().get(j));
					} else {
						Count = Long.toString(tmpVO.getReCount().get(j));
					}
					tmpStr[diff+j + 2] = Count;
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
					contentType, "保内维修分析倒三角阵" + fname);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			model.addAttribute("message", "导出失败！请联系管理人员");
		}
		return retView;
	}
	/**
	 * 正三角阵导出
	 */
	@RequestMapping("/excelOutput_trgWarrantyRepairCount")
	public String excelOutput_trgInsSigleCount(CommonVo vo, Model model,HttpServletRequest request, HttpServletResponse response) {
		String retView = "error/err";
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		LaterSumtime laterTime = laterSumtimeService.getLaterDate();
		if (vo.getInsEndTime() == null) {
			vo.setInsEndTime(laterTime.getSumMonth());
		}
		try {
			List<TimeMatrixResultVo> list = loadConDiffData(model, vo, CUL_MONTHS);
			String[] column = null;
			column = StatisUtils.getArryStr(1, list.get(0).getReCount().size());
			// 年月季度统计
			if ("year".equals(vo.getStatisType())) {
				int length = list.get(0).getReCount().size() / 12;
				if (list.get(0).getReCount().size() % 12 != 0) {
					length++;
				}
				column = StatisUtils.getArryStr(1, length);
				list = yearCount(list, model, vo);
			}
			//表头
			String[] CN = new String[column.length + 2];
			CN[0] = "安装月份";
			CN[1] = "安装数";
			for(int j=1;j<=CN.length-2;j++){
				CN[j+1]=column[j-1];
			}
			//表体数据
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				TimeMatrixResultVo tmpVO = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getBaseMonth();
				tmpStr[1] = Long.toString(tmpVO.getBaseCount());
				for (int j = 0; j < tmpVO.getReCount().size(); j++) {
					if(j+2>=tmpStr.length){
						continue;
					}
					String Count = null;
					if (tmpVO.getReCount().get(j) == null) {
						Count = " ";
					} else if(vo.getStatisData() != null && vo.getStatisData().equals("repairRate")) {
						Count = Long.toString(tmpVO.getRepairPercent().get(j));
					} else {
						Count = Long.toString(tmpVO.getReCount().get(j));
					}
					tmpStr[j + 2] = Count;
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
					contentType, "保内维修分析正三角阵" + fname);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			model.addAttribute("message", "导出失败！请联系管理人员");
		}
		return retView;
	}

	
	
	
	/**
	 * @param model
	 * @param vo
	 * @param maxMonths
	 * @throws ParseException 
	 */
	private List<TimeMatrixResultVo> loadConDiffData(Model model, CommonVo vo, int maxMonths) {
		//添加搜索条件
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
		
		Long maxCount = 0l;
		double maxPercent = 0;
		
		
		//获取安装月份下的保内维修数
		/**
		 * installMonth 安装月份
		 * installCount 维修数
		 * intervalTime 间隔时间
		 */
		List<CommonVo> reinsList = commonService.getInsCountGroupByMonthAndInterval(vo);
				
		//获取安装数
		List<CommonVo> InsCountList = commonService.getInsCountGroupByReMonth(vo);
		//计算两个日期之间相差的月份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar insStartTime = Calendar.getInstance();
        Calendar insEndTime = Calendar.getInstance();
        Calendar overTime = Calendar.getInstance();
        try {
			insStartTime.setTime(sdf.parse(vo.getInsStartTime()));
			insEndTime.setTime(sdf.parse(vo.getInsEndTime()));
			int overYear = insEndTime.get(Calendar.YEAR)-5;
			int overMonth = insEndTime.get(Calendar.MONTH)+1;
			overTime.setTime(sdf.parse(overYear+"-"+overMonth));

        } catch (ParseException e) {
        	e.printStackTrace();
        }
        int month = (insEndTime.get(Calendar.YEAR) - insStartTime.get(Calendar.YEAR)) * 12;
        int result  =insEndTime.get(Calendar.MONTH) - insStartTime.get(Calendar.MONTH);     
        int column = month + result;
        int column2 = column;
        Map<String, Long> reinsMap = new HashMap<String, Long>();
        List<TimeMatrixResultVo> list = new ArrayList<TimeMatrixResultVo>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM"); 
        Set<String> betweenMonth = WebUtil.getBetweenMonth(vo.getInsStartTime(),vo.getInsEndTime());
        List<String> betMonth = new ArrayList<String>(betweenMonth);
	        for(int i = 0;i<=column;i++){
	        	boolean f = true;
	        	if(i>InsCountList.size()-1){
	        		if(InsCountList.size()==0)break;
	        		f=false;
	        		try {
	        		CommonVo cVo = new CommonVo();
	        		String date = InsCountList.get(i-1).getInstallMonth();
					Date d = format.parse(date);
					d.setMonth(d.getMonth()+1);
	        		cVo.setInstallMonth(format.format(d));
	        		cVo.setInstallCount(0L);
	        		InsCountList.add(cVo);
	        		} catch (ParseException e) {
	        			e.printStackTrace();
	        		}
	        	}else{
	        		if(!InsCountList.get(i).getInstallMonth().equals(betMonth.get(i))){
	        			f=false;
	        			CommonVo cVo = new CommonVo();
	        			cVo.setInstallMonth(betMonth.get(i));
		        		cVo.setInstallCount(0L);
		        		InsCountList.add(i,cVo);
	        		}
	        	}
	        	//封装一个TimeMatrixResultVo
	        	TimeMatrixResultVo tvo = new TimeMatrixResultVo();
	        	//月份
	        	tvo.setBaseMonth(InsCountList.get(i).getInstallMonth());
	        	//安装总数
	        	tvo.setBaseCount(InsCountList.get(i).getInstallCount());
	        	//封装该月份维修数的数组
	        	List<Long> l = new ArrayList<Long>();
	        	for(int j=0;j<=column2;j++){
	        		l.add(0L);
	        		if(f){
	        			for(CommonVo r :reinsList){
	        				if(r.getInstallMonth().equals(InsCountList.get(i).getInstallMonth()) && r.getIntervalTime()==j){
	        					l.set(j,r.getInstallCount());
	        					if(r.getInstallCount()>maxCount){
	        						maxCount = r.getInstallCount();
	        					}
	        				}
	        			}	        			
	        		}
	        	}
	        	tvo.setReCount(l);
	        	list.add(tvo);
	        	column2 = column2-1;
	        }   
	    //保内维修率计算
        if(vo.getStatisData().equals("repairRate")){
        	vo.setInsStartTime(sdf.format(overTime.getTime()));
        	allInsCountGroup = commonService.getAllInsCountGroup(vo);
        	for(TimeMatrixResultVo t:list){
        		double fm = allInsCountGroup/60.0;
        		List<Long> reCount = t.getReCount();
        		List<Long> repairPercent = new ArrayList<Long>();
        		for(Long l :reCount){
        			Long rePercent = Math.round((l*1000000)/fm);
        			repairPercent.add(rePercent);
        			if(rePercent>maxPercent){
        				maxPercent = rePercent;
        			}
        		}
        		t.setRepairPercent(repairPercent);
        	}
        	vo.setInsStartTime(sdf.format(insStartTime.getTime()));
        }
		//获取平均范围(整数),获得一个范围内平均数的数组 用于在页面显示不同的颜色,给最大值
		//维修数
		model.addAttribute("rangeList", StatisUtils.getArrageIntList(maxCount.intValue(), null));
		//维修率
		model.addAttribute("rangePercentList", StatisUtils.getArrageList(maxPercent, null));
		model.addAttribute("vo", vo);
		return list;
	}

}
