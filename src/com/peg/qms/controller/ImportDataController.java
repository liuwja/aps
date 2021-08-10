package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.peg.model.CommonVo;
import com.peg.model.DownLineTotal;
import com.peg.model.MarketRepairTotal;
import com.peg.model.ShipTotal;
import com.peg.model.bph.MesDataSum;
import com.peg.model.system.SumOperationLog;
import com.peg.service.ImportDataServiceI;
import com.peg.service.LaterSumtimeServiceI;
import com.peg.service.bph.BphCommonServiceI;
import com.peg.service.system.SumOperationLogServiceI;
import com.peg.web.task.SumTask;

@Controller
@RequestMapping("qms/importData")
public class ImportDataController extends BaseController{
	
	@Autowired
	private SumOperationLogServiceI sumOpService;
	
	@Autowired
	private ImportDataServiceI importDataService;
	
	@Autowired
	private LaterSumtimeServiceI laterSumtimeService;
	
	@Autowired
	private BphCommonServiceI bphCommonService;
	

	/**
	 * 导入CRM数据
	 */
	@RequestMapping("/importCRMData")
	public void importCRMData(CommonVo vo, HttpServletResponse respon, Model model) {
		Map<String,String> map = new HashMap<String,String>();
		String name = "CRM数据采集";
		String rusult = "成功";
		String remark = "";
		List<Integer> list = new ArrayList<Integer>();
		Date startTime = Calendar.getInstance().getTime();
		try {
			convertDate(vo);
			list = importDataService.importRepairdataFromCrm(vo);
			map.put("result", "0");
		} catch (Exception e) {
			rusult = "失败";
			map.put("result", "-1");
			map.put("msg", e.getMessage());
			logger.error(e.getMessage(), e);
			remark = ((String) map.get("msg")).substring(0, 230);
		}
		map.put("showResult", name + rusult);
		Date endTime = Calendar.getInstance().getTime();
		String statisticsTime = vo.getStartTime() + " 至 " + vo.getEndTime();
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime, startTime, endTime, rusult, "manual", "数据采集", remark);
		sumOpService.insert(opLog);
		String msg = "";
		if (list != null && list.size() > 0) {
			msg = vo.getStartTime() + "-" + vo.getEndTime() + "期间共导入" + list.get(1) + "条记录，其中有重复记录" + list.get(2) + "条，已将重复数据删除";
		}
		map.put("msg", msg);
		model.addAttribute("vo", vo);
		JSONObject jsonObject = JSONObject.fromObject(map);
		printResponContent(respon, jsonObject.toString());
	}
	
	/**
	 * 导入Mes数据
	 * @param record
	 * @return
	 */
	@RequestMapping("/importMesData")
	public void importMesData(CommonVo vo, HttpServletResponse respon, Model model) {
		Map<String,String> map = new HashMap<String,String>(); 
		String name = "MES数据采集";
		String rusult = "成功";
		String remark = "";
		List<Integer> list = new ArrayList<Integer>();
		Date startTime = Calendar.getInstance().getTime();
		try {
			convertDate(vo);
			list = importDataService.importDataFromMES(vo);
			map.put("result", "0");
		} catch (Exception e){
			rusult = "失败";
			map.put("result", "-1");
			map.put("msg", e.getMessage());
			logger.error(e.getMessage(),e);
			remark = ((String)map.get("msg")).substring(0,230);
		}
		map.put("showResult", name+rusult);
		Date endTime = Calendar.getInstance().getTime();
		String statisticsTime = vo.getStartTime()+" 至 "+vo.getEndTime();
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime, startTime, endTime, rusult, "manual", "数据采集", remark);
		sumOpService.insert(opLog);
		model.addAttribute("vo", vo);
		String msg = "";
		if (list != null && list.size() > 0) {
			msg = vo.getStartTime() + "-" + vo.getEndTime() + "期间共导入" + list.get(1) + "条记录，其中有重复记录" + list.get(2) + "条，已将重复数据删除";
		}
		map.put("msg", msg);
		JSONObject jsonObject = JSONObject.fromObject(map);  		
		printResponContent(respon, jsonObject.toString());
	}
	
	/**
	 * 更新发货时间
	 * @param vo
	 * @param respon
	 * @param model
	 */
	@RequestMapping("/updateShipDate")
	public void updateShipDate(CommonVo vo, HttpServletResponse respon, Model model) {
		Map<String,String> map = new HashMap<String,String>(); 
		String name = "更新发货时间";
		String rusult = "成功";
		String remark = "";
		Date startTime = Calendar.getInstance().getTime();
		try {
			importDataService.updateShipDateForMES(vo);
			map.put("result", "0");
		} catch (Exception e){
			rusult = "失败";
			map.put("result", "-1");
			map.put("msg", e.getMessage());
			logger.error(e.getMessage(),e);
			remark = ((String)map.get("msg")).substring(0,230);
		}
		map.put("showResult", name+rusult);
		Date endTime = Calendar.getInstance().getTime();
		String statisticsTime = vo.getStartTime()+" 至 "+vo.getEndTime();
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime, startTime, endTime, rusult, "manual", "数据更新", remark);
		sumOpService.insert(opLog);
		JSONObject jsonObject = JSONObject.fromObject(map);  		
		printResponContent(respon, jsonObject.toString());
	}
	
	/**
	 * 维修汇总
	 * @param record
	 * @return
	 */
	@RequestMapping("/updateMarketRepairTotal")
	public void updateMarketRepairTotal(MarketRepairTotal record, HttpServletResponse respon, Model model, @RequestParam("statisticsMonth") String statisticsMonth) {
		Map<String,String> map = new HashMap<String,String>(); 
		String name = "维修汇总";
		String rusult = "成功";
		String remark = "";
		Date startTime = Calendar.getInstance().getTime();
		try {
			record.setRepairedMonth(statisticsMonth);
			int row = importDataService.updateMarketRepairTotalByMonth(record);
			if(row > 0){
				laterSumtimeService.updateToLater(statisticsMonth);
			}
			map.put("result", "0");
		} catch (Exception e){
			rusult = "失败";
			map.put("result", "-1");
			map.put("msg", e.getMessage());
			logger.error(e.getMessage(),e);
			remark = ((String)map.get("msg")).substring(0,230);
		}
		map.put("showResult", name+rusult);
		Date endTime = Calendar.getInstance().getTime();
		String statisticsTime = record.getRepairedMonth();
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime, startTime, endTime, rusult, "manual", "数据汇总", remark);
		sumOpService.insert(opLog);
		model.addAttribute("queryMonth", statisticsMonth);
		JSONObject jsonObject = JSONObject.fromObject(map);  		
		printResponContent(respon, jsonObject.toString());
	}
	
	/**
	 * 下线汇总
	 * @param record
	 * @return
	 */
	@RequestMapping("/updateDownLineTotal")
	public void updateDownLineTotal(DownLineTotal record, HttpServletResponse respon, Model model) {
		Map<String,String> map = new HashMap<String,String>(); 
		String name = "下线汇总";
		String rusult = "成功";
		String remark = "";
		Date startTime = Calendar.getInstance().getTime();
		String statisticsTime = record.getStatisticsMonth();
		try {
			importDataService.updateDownlineTotalByMonth(record);
			map.put("result", "0");
		} catch (Exception e){
			rusult = "失败";
			map.put("result", "-1");
			map.put("msg", e.getMessage());
			logger.error(e.getMessage(),e);
			remark = ((String)map.get("msg")).substring(0,230);
		}
		map.put("showResult", name+rusult);
		Date endTime = Calendar.getInstance().getTime();
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime, startTime, endTime, rusult, "manual", "数据汇总", remark);
		sumOpService.insert(opLog);
		model.addAttribute("queryMonth", record.getStatisticsMonth());
		JSONObject jsonObject = JSONObject.fromObject(map);  		
		printResponContent(respon, jsonObject.toString());
	}
	
	/**
	 * 发货汇总
	 * @param record
	 * @return
	 */
	@RequestMapping("/updateShipTotal")
	public void updateShipTotal(ShipTotal record, HttpServletResponse respon, Model model) {
		Map<String,String> map = new HashMap<String,String>(); 
		String name = "发货汇总";
		String statisticsTime = record.getStatisticsMonth();
		String rusult = "成功";
		Date startTime = Calendar.getInstance().getTime();
		String remark = "";
		try {
			importDataService.updateShipTotalByMonth(record);
			map.put("result", "0");
		} catch (Exception e){
			rusult = "失败";
			map.put("result", "-1");
			map.put("msg", e.getMessage());
			logger.error(e.getMessage(),e);
			remark = ((String)map.get("msg")).substring(0,230);
		}
		map.put("showResult", name+rusult);
		Date endTime = Calendar.getInstance().getTime();
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime, startTime, endTime, rusult, "manual", "数据汇总", remark);
		sumOpService.insert(opLog);
		model.addAttribute("queryMonth", record.getStatisticsMonth());
		JSONObject jsonObject = JSONObject.fromObject(map);  		
		printResponContent(respon, jsonObject.toString());
	}
	
	/**
	 * 安装数据汇总
	 */
	@RequestMapping("/sumInstallTotal")
	public void sumInstallTotal(@RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate, HttpServletResponse respon, Model model) {
		Map<String,String> map = new HashMap<String,String>(); 
		String name = "安装汇总";
		String rusult = "成功";
		String remark = "";
		Date startTime = Calendar.getInstance().getTime();
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("startDate", "".equals(startDate) ? null : startDate);
		paramMap.put("endDate", "".equals(endDate) ? null : endDate);
		try {
			importDataService.sumInstallMonthData(paramMap);
			map.put("result", "0");
		} catch (Exception e){
			rusult = "失败";
			map.put("result", "-1");
			map.put("msg", e.getMessage());
			logger.error(e.getMessage(),e);
			remark = ((String)map.get("msg")).substring(0,230);
		}
		map.put("showResult", name+rusult);
		Date endTime = Calendar.getInstance().getTime();
		SumOperationLog opLog = new SumOperationLog(name, startDate +" 到 " + endDate, startTime, endTime, rusult, "manual", "数据汇总", remark);
		sumOpService.insert(opLog);
		model.addAttribute("queryMonth", startDate);
		JSONObject jsonObject = JSONObject.fromObject(map);  		
		printResponContent(respon, jsonObject.toString());
	}

	/**
	 * 安装维修汇总
	 * @param record
	 * @param respon
	 * @param model
	 * @param statisticsMonth
	 */
	@RequestMapping("/sumInstallRepair")
	public void sumInstallRepair(MarketRepairTotal record, HttpServletResponse respon, Model model, @RequestParam("statisticsMonth") String statisticsMonth) {
		Map<String,String> map = new HashMap<String,String>(); 
		String name = "安装维修汇总";
		String rusult = "成功";
		String remark = "";
		Date startTime = Calendar.getInstance().getTime();
		try {
			record.setRepairedMonth(statisticsMonth);
			importDataService.sumInstallRepairData(record);
			map.put("result", "0");
		} catch (Exception e) {
			rusult = "失败";
			map.put("result", "-1");
			map.put("msg", e.getMessage());
			logger.error(e.getMessage(),e);
			remark = e.getMessage().substring(0,230);
		}
		map.put("showResult", name+rusult);
		Date endTime = Calendar.getInstance().getTime();
		SumOperationLog opLog = new SumOperationLog(name, statisticsMonth, startTime, endTime, rusult, "manual", "数据汇总", remark);
		sumOpService.insert(opLog);
		model.addAttribute("queryMonth", statisticsMonth);
		JSONObject jsonObject = JSONObject.fromObject(map);  		
		printResponContent(respon, jsonObject.toString());
	}
	
	/**
	 * 一键汇总
	 * @param record
	 * @return
	 */
	@RequestMapping("/updateTotalCount")
	public void updateTotalCount(DownLineTotal record, HttpServletResponse respon, Model model) {
		
		Map<String,String> map = new HashMap<String,String>(); 
		//日志字段
		String name = "一键汇总";
		String rusult = "成功";
		try{
			//下线
			SumTask task = new SumTask();
			task.setImportDataService(importDataService);
			task.setSumOpService(sumOpService);
			DownLineTotal downline = new DownLineTotal();
			downline.setStatisticsMonth(null);
			task.updateDownLineTotal(downline);
			
			//发货
			ShipTotal ship = new ShipTotal();
			ship.setStatisticsMonth(null);
			task.updateShipTotal(ship);
			
			//维修
			MarketRepairTotal repair = new MarketRepairTotal();
			repair.setRepairedMonth(null);
			task.updateMarketRepairTotal(repair);
			
			map.put("result", "0");
		}catch (Exception e){
			rusult = "失败";
			map.put("result", "-1");
			map.put("msg", e.getMessage());
			logger.error(e.getMessage(),e);
		}
		map.put("showResult", name+rusult);
		
		JSONObject jsonObject = JSONObject.fromObject(map);  		
		printResponContent(respon, jsonObject.toString());
	}
	
	private void convertDate(CommonVo vo)
	{
		if(StringUtils.isNotBlank(vo.getStartTime()))
		{
			vo.setStartTime(vo.getStartTime() + " 00:00:00");
		}
		if(StringUtils.isNotBlank(vo.getEndTime()))
		{
			vo.setEndTime(vo.getEndTime() + " 23:59:59");
		}
	}
	
	/********bph*********/
	@RequestMapping("/updateMesSumDataMonth")
	public void updateMesSumDataMonth(MesDataSum mesDataSum,HttpServletResponse respon, Model model){
		Map<String,Object> map = new HashMap<String,Object>(); 
		//日志字段
		String name = "MES月份汇总";
		String result = "成功";
		String queryMonth = mesDataSum.getQueryMonth();
		Date startTime = Calendar.getInstance().getTime();
		String remark = "";
		
		try{			
			bphCommonService.insertMesDataSumMonth(mesDataSum);
			map.put("result", "0");
		}catch (Exception e){
			result = "失败";
			 map.put("result", "-1");
			 map.put("msg", e.getMessage());
			 logger.error(e.getMessage(),e);
			 remark = ((String)map.get("msg")).substring(0,e.getMessage().length()>230 ? 230 : e.getMessage().length());
		}
		map.put("showResult", name+result);
		
		Date endTime = Calendar.getInstance().getTime();
		SumOperationLog opLog = new SumOperationLog(name, queryMonth,
				startTime, endTime, result, "manual", "数据汇总", remark);
		sumOpService.insert(opLog);
		model.addAttribute("queryMonth", mesDataSum.getQueryMonth());
		JSONObject jsonObject = JSONObject.fromObject(map);  		
		printResponContent(respon, jsonObject.toString());
	}
	@RequestMapping("/updateMesSumDataDay")
	public void updateMesSumDataDay(MesDataSum mesDataSum,HttpServletResponse respon, Model model){
		Map<String,Object> map = new HashMap<String,Object>(); 
		//日志字段
		String name = "MES单日汇总";
		String result = "成功";
		String startDay = mesDataSum.getStartTime();
		String endDay = mesDataSum.getEndTime();
		Date startTime = Calendar.getInstance().getTime();
		String remark = "";
		
		try{			
			bphCommonService.insertMesDataSumDay(mesDataSum);
			map.put("result", "0");
		}catch (Exception e){
			result = "失败";
			 map.put("result", "-1");
			 map.put("msg", e.getMessage());
			 logger.error(e.getMessage(),e);
			 remark = ((String)map.get("msg")).substring(0,230);
		}
		map.put("showResult", name+result);
		
		Date endTime = Calendar.getInstance().getTime();
		SumOperationLog opLog = new SumOperationLog(name, startDay +"至"+endDay,
				startTime, endTime, result, "manual", "数据汇总", remark);
		sumOpService.insert(opLog);
		model.addAttribute("startTime", mesDataSum.getStartTime());
		model.addAttribute("endTime", mesDataSum.getEndTime());
		JSONObject jsonObject = JSONObject.fromObject(map);  		
		printResponContent(respon, jsonObject.toString());
	}

}
