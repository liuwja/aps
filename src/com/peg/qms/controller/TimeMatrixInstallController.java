package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.model.CommonVo;
import com.peg.model.LaterSumtime;
import com.peg.model.TimeMatrixResultVo;
import com.peg.model.TimeMatrx;
import com.peg.service.CommonServiceI;
import com.peg.service.LaterSumtimeServiceI;
import com.peg.service.TimeMatrxServiceI;
import com.peg.web.util.ConditionUtil;
import com.peg.web.util.ExcelUtilities;
import com.peg.web.util.ParseDataUtil;
import com.peg.web.util.StatisUtils;
import com.peg.web.util.WebUtil;

@Controller
@RequestMapping("timeMatrixInstall")
public class TimeMatrixInstallController extends BaseController {

	@Autowired
	private CommonServiceI commonService;
	@Autowired
	private LaterSumtimeServiceI laterSumtimeService;
	@Autowired
	private TimeMatrxServiceI timeMatrxService;
	
	private static final int CUL_MONTHS = 120; //安装数据计算最大值
	
	/**
	 * 安装正三角阵(单月安装数)
	 * @param model
	 * @return
	 */
	@RequestMapping("trgMatrixInsSigleCount")
	public String trgMatrixInsSigleCount(Model model, CommonVo vo) {
		setBaseData(model);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		ConditionUtil.loadGasType(model, vo, commonService);
		List<TimeMatrixResultVo> list = null;
		String[] column = null;
		if(vo.getProductType()!=null){
			list = loadConDiffData(model, vo, CUL_MONTHS);
			column = StatisUtils.getArryStr(1, list.get(0).getReCount().size());
			if ("year".equals(vo.getStatisType())) { // 年月季度统计
				int length = list.get(0).getReCount().size() / 12;
				if (list.get(0).getReCount().size() % 12 != 0) {
					length++;
				}
				column = StatisUtils.getArryStr(1, length);
				List<TimeMatrixResultVo> yearList = ParseDataUtil.splitData(list, model, vo, 12);
				model.addAttribute("list", yearList);
			} else if ("quarter".equals(vo.getStatisType())) {
				int length = list.get(0).getReCount().size() / 3;
				if (list.get(0).getReCount().size() % 3 != 0) {
					length++;
				}
				column = StatisUtils.getArryStr(1, length);
				List<TimeMatrixResultVo> quarterList = ParseDataUtil.splitData(list, model, vo, 3);
				model.addAttribute("list", quarterList);
			} else{
				model.addAttribute("list", list);
			}
		}
		model.addAttribute("vo", vo);
		model.addAttribute("list", list);
		model.addAttribute("column", column);
		return "qms/table/time/trgMatrixInsSigleCount";
	}

	/**
	 * 安装倒三角阵(单月安装数)
	 * @param model
	 * @return
	 */
	@RequestMapping("trgMatrixDownInsSigleCount")
	public String trgMatrixDownInsSigleCount(Model model, CommonVo vo) {
		setBaseData(model);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		ConditionUtil.loadGasType(model, vo, commonService);
		String laterSumtime = (String) model.asMap().get("laterSumtime");
		if (vo.getInsEndTime() == null) {
			vo.setInsEndTime(laterSumtime);
		}
		List<TimeMatrixResultVo> list = null;
		Set<String> column = null;
		if(vo.getProductType()!=null){
			 list = loadConDiffData(model, vo, CUL_MONTHS);
			 column = WebUtil.getBetweenMonth(list.get(0).getBaseMonth(), vo.getInsEndTime());
			// 年月季度统计
			if ("year".equals(vo.getStatisType())) {
				List<TimeMatrixResultVo> yearList = ParseDataUtil.splitData(list, model, vo, 12);
				model.addAttribute("list", yearList);
				column = WebUtil.getBetweenYear(column);
			} else if ("quarter".equals(vo.getStatisType())) {
				List<TimeMatrixResultVo> quarterList = ParseDataUtil.splitData(list, model, vo, 3);
				model.addAttribute("list", quarterList);
				column = WebUtil.getBetweenQuarter(column);
			} else{
				for(int i=0;i<list.size();i++){
					list.get(i).setPreDiff(i);
				}
				model.addAttribute("list", list);
			}
		}
		model.addAttribute("vo", vo);
		model.addAttribute("column", column);
//		return "qms/table/time/trgMatrixDownInsSigleCount";
		return "qms/table/time/trgMatrixInsSigleCount";
	}
	
	/**
	 * 倒安装三角阵导出
	 */
	@RequestMapping("/excelOutput_trgDownInsSigleCount")
	public String excelOutput_trgDownInsSigleCount(CommonVo vo, Model model,HttpServletRequest request, HttpServletResponse response) {
		String retView = "error/err";
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		ConditionUtil.loadGasType(model, vo, commonService);
		LaterSumtime laterTime = laterSumtimeService.getLaterDate();
		if (vo.getInsEndTime() == null) {
			vo.setInsEndTime(laterTime.getSumMonth());
		}
		try {
			List<TimeMatrixResultVo> list = loadConDiffData(model, vo, CUL_MONTHS);
			Set<String> column = null;
			column = WebUtil.getBetweenMonth(list.get(0).getBaseMonth(), vo.getInsEndTime());
			
			// 年月季度统计
			if ("year".equals(vo.getStatisType())) {
				list = ParseDataUtil.splitData(list, model, vo, 12);
				column = WebUtil.getBetweenYear(column);
			} else if ("quarter".equals(vo.getStatisType())) {
				list = ParseDataUtil.parseToQuarter(list, model);
				column = WebUtil.getBetweenQuarter(column);
			} else{
				for(int i=0;i<list.size();i++){
					list.get(i).setPreDiff(i);
				}
			}
			//表头
			String[] CN = new String[column.size() + 2];
			CN[0] = "安装月份";
			CN[1] = "安装数";
			List<String> coluList = new ArrayList<String>();
			coluList.addAll(column);
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
					String Count = null;
					if (tmpVO.getReTotalCount().get(j) == null) {
						Count = " ";
					} else if(vo.getStatisData() != null && vo.getStatisData().equals("repairRate")) {
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
					contentType, "安装倒三角阵(单月维修数)" + fname);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			model.addAttribute("message", "导出失败！请联系管理人员");
		}
		return retView;
	}
	/**
	 * 正安装三角阵导出
	 */
	@RequestMapping("/excelOutput_trgInsSigleCount")
	public String excelOutput_trgInsSigleCount(CommonVo vo, Model model,HttpServletRequest request, HttpServletResponse response) {
		String retView = "error/err";
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		ConditionUtil.loadGasType(model, vo, commonService);
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
				list = ParseDataUtil.splitData(list, model, vo, 12);
			} else if ("quarter".equals(vo.getStatisType())) {
				int length = list.get(0).getReCount().size() / 3;
				if (list.get(0).getReCount().size() % 3 != 0) {
					length++;
				}
				column = StatisUtils.getArryStr(1, length);
				list = ParseDataUtil.splitData(list, model, vo, 3);
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
					if (tmpVO.getReTotalCount().get(j) == null) {
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
					contentType, "安装正三角阵(单月维修数)" + fname);
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
	 */
	private List<TimeMatrixResultVo> loadConDiffData(Model model, CommonVo vo, int maxMonths) {
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
		//VOC分类
		if(StringUtils.isNotEmpty(vo.getVocTypeID())){
			vo.setVocTypeIDs(vo.getVocTypeID().split(","));
		}
		//气源
		if(StringUtils.isNotEmpty(vo.getGasCategoryTxt())){
			vo.setGasCategorys(vo.getGasCategoryTxt().replaceAll("'","").split(","));
		}
		Long maxCount = 0l;
		Long maxPercent = 0l;
		Calendar cal = Calendar.getInstance();
		List<CommonVo> reinsList = commonService.getInsCountGroupByMonth(vo);
		List<CommonVo> InsCountList = commonService.getInsCountGroupByReMonth(vo);
		Map<String, Long> reinsMap = new HashMap<String, Long>();
		for (CommonVo insVo:reinsList) {
			reinsMap.put(insVo.getInstallMonth()+"&"+insVo.getRepairMonth(),insVo.getRepairCount());
		}
		int l = WebUtil.getMonthQuantity(vo.getInsStartTime(), vo.getInsEndTime());
		InsCountList = WebUtil.supplementMonth(InsCountList, "InstallMonth", vo.getInsStartTime(), vo.getInsEndTime());
		TimeMatrx record = new TimeMatrx();
		record.setMachineType(vo.getProductType());
		Map<String, TimeMatrx> timeIsEffectiveMap = timeMatrxService.getAllByMachineType(record);
		List<TimeMatrixResultVo> list = new ArrayList<TimeMatrixResultVo>();
		for (int i = 0; i <= l; i++) {
			CommonVo installVo = InsCountList.get(i);
			if (installVo.getInstallMonth().compareTo(vo.getInsEndTime()) > 0) {// 只统计到基准月份
				break;
			}
			String[] timeArr = installVo.getInstallMonth().split("-");
			TimeMatrixResultVo tmpVo = new TimeMatrixResultVo();
			tmpVo.setBaseMonth(installVo.getInstallMonth());
			tmpVo.setBaseCount(installVo.getInstallCount());
			
			if (timeIsEffectiveMap.containsKey(tmpVo.getBaseMonth())) {
				tmpVo.setEffective(false);
			} else {
				tmpVo.setEffective(true);
			}
			cal.set(Integer.parseInt(timeArr[0]),Integer.parseInt(timeArr[1]) - 1, 1);
			List<Long> reclist = new ArrayList<Long>();// 单月安装数
			List<Long> recPercentlist = new ArrayList<Long>(); //单月安装率
			List<Long> recTotallist = new ArrayList<Long>();// 累计安装数
			List<Long> recTotalPercentlist = new ArrayList<Long>(); //累计安装率
			for (int j = 0; j < maxMonths; j++) {
				if (j != 0) {
					cal.add(Calendar.MONTH, 1);
				}
				String month = DateFormatUtils.format(cal, "yyyy-MM");
				if (month.compareTo(vo.getInsEndTime()) > 0) {// 只到当前月份，未来月份不作统计
//					reclist.add(null);
//					recPercentlist.add(null);
//					recTotallist.add(null);
//					recTotalPercentlist.add(null);
					continue;
				}
				Long mapValue = reinsMap.get(installVo.getInstallMonth()+"&"+month);
				Long mapTotalValue = reinsMap.get(installVo.getInstallMonth()+"&"+month);
				Long mapPercent = 0L;
				if (mapValue == null) {
					mapValue = 0L;
					mapTotalValue = 0L;
				} else {
					mapPercent = Math.round(mapValue * 1000000.0 / tmpVo.getBaseCount());
				}
				if (mapValue > maxCount) {
					maxCount = mapValue;
				}
				if (mapPercent > maxPercent) {
					maxPercent = mapPercent;
				}
				reclist.add(mapValue);// 添加单月安装数
				if (recTotallist.size() != 0) {
					mapTotalValue += recTotallist.get(recTotallist.size() - 1);
				}
				recTotallist.add(mapTotalValue);// 添加单月累计安装数
				if(tmpVo.getBaseCount() != 0L) { //添加单月安装数
					recPercentlist.add(Math.round(mapValue * 1000000.0 / tmpVo.getBaseCount())); //单月安装率
					recTotalPercentlist.add(Math.round(mapTotalValue * 1000000.0 / tmpVo.getBaseCount())); //累计安装率
				} else {
					recPercentlist.add(0L);
					recTotalPercentlist.add(0L);
				}
			}
			tmpVo.setReCount(reclist);
			tmpVo.setReTotalCount(recTotallist);
			tmpVo.setRepairPercent(recPercentlist);
			tmpVo.setRepairTotalPercent(recTotalPercentlist);
			list.add(tmpVo);
		}
		model.addAttribute("rangeList", StatisUtils.getArrageIntList(maxCount.intValue(), null));
		model.addAttribute("rangePercentList", StatisUtils.getArrageList(maxPercent, null));
		model.addAttribute("vo", vo);
		return list;
	}
}
