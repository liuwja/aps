/*
 * @(#) ExcuteSumTask.java 2015-3-31 上午09:52:32
 *
 * Copyright 2015 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.web.task;

import com.peg.model.CommonVo;
import com.peg.model.DownLineTotal;
import com.peg.model.MarketRepairTotal;
import com.peg.model.ShipTotal;
import com.peg.model.bph.CountPerformanceMonth;
import com.peg.model.bph.MesDataSum;
import com.peg.model.part.MarketPart;
import com.peg.model.smt.SmtLog;
import com.peg.model.system.SumOperationLog;
import com.peg.service.CommonServiceI;
import com.peg.service.ImportDataServiceI;
import com.peg.service.LaterSumtimeServiceI;
import com.peg.service.bph.BphCommonServiceI;
import com.peg.service.bph.ComputeScoreServiceI;
import com.peg.service.bph.CountPerformanceMonthServiceI;
import com.peg.service.part.MarketPartServiceI;
import com.peg.service.part.OnlineServiceITwo;
import com.peg.service.part.TestInstanceServiceI;
import com.peg.service.smt.SmtServiceI;
import com.peg.service.system.SmtLogServiceI;
import com.peg.service.system.SumOperationLogServiceI;
import com.peg.web.util.DateEditor;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据汇总定时任务类
 * <p>
 * @author Lin, 2015-3-31 上午09:52:32
 */
@Component("sumTask")
public class SumTask
{
	@Autowired
	private CommonServiceI comService;
	
	@Autowired
	private SumOperationLogServiceI sumOpService;
	
	@Autowired
	private ComputeScoreServiceI computeScoreService;
	
	@Autowired
	private LaterSumtimeServiceI laterSumtimeService;
	
	@Autowired 
	private CountPerformanceMonthServiceI countPerformanceMonthService;
	
	@Autowired
	private BphCommonServiceI bphCommonService;
	
	@Autowired
	private ImportDataServiceI importDataService;
	
	@Autowired
	private OnlineServiceITwo onlineService;
	
	@Autowired
	private MarketPartServiceI marketPartService;
	
	@Autowired 
	private TestInstanceServiceI testInstanceService;

	@Autowired
	private SmtServiceI smtService;

	@Autowired
	private SmtLogServiceI smtLogService;

	protected Logger logger = Logger.getLogger(this.getClass());

	
	/**
	 * 每月3日上午02:00触发
	 * 导入前两个月的MES生产数据及CRM维修数据，并进行相关的汇总
	 */
	@Scheduled(cron="0 0 2 3 * ?")
	public void sumMonData() {	
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -2);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String startDate = DateFormatUtils.format(cal, "yyyy-MM-dd");
		String startMonth = DateFormatUtils.format(cal, "yyyy-MM");
		
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		String endDate = DateFormatUtils.format(cal, "yyyy-MM-dd");
		String endMonth = DateFormatUtils.format(cal, "yyyy-MM");
		
		//先导入MES生产数据和CRM维修数据
		CommonVo vo = new CommonVo();
		vo.setStartTime(startDate);
		vo.setEndTime(endDate);
		convertDate(vo);
		importMesData(vo);
		importCRMData(vo);
		updateShipDate(vo);//更新发货时间
		
		//再汇总计算
		//下线
		DownLineTotal downline = new DownLineTotal();
		downline.setStatisticsMonth(startMonth);
		updateDownLineTotal(downline);
		downline.setStatisticsMonth(endMonth);
		updateDownLineTotal(downline);
		
		//发货
		ShipTotal ship = new ShipTotal();
		ship.setStatisticsMonth(startMonth);
		updateShipTotal(ship);
		ship.setStatisticsMonth(endMonth);
		updateShipTotal(ship);
		
		//维修
		MarketRepairTotal repair = new MarketRepairTotal();
		repair.setRepairedMonth(startMonth);
		updateMarketRepairTotal(repair);
		sumInstallRepair(repair);
		repair.setRepairedMonth(endMonth);
		updateMarketRepairTotal(repair);
		sumInstallRepair(repair);
		
		//安装维修
		sumInstallTotal(startMonth, endMonth);
		logger.info("市场质量月数据汇总");
	}
	
	/**
	 * 导入Mes数据
	 * @param record
	 * @return
	 */
	public void importMesData(CommonVo vo) {
		String name = "MES数据采集";
		String rusult = "成功";
		Date startTime = Calendar.getInstance().getTime();
		String remark = "";
		try {
			importDataService.importDataFromMES(vo);
		} catch (Exception e) {
			rusult = "失败";
			logger.error(e.getMessage(), e);
			remark = e.getMessage().substring(0, 230);
		}
		Date endTime = Calendar.getInstance().getTime();
		String statisticsTime = vo.getStartTime()+" 至 "+vo.getEndTime();
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime, startTime, endTime, rusult, "auto", "数据采集", remark);
		sumOpService.insert(opLog);
	}
	
	/**
	 * 导入CRM数据
	 * @param record
	 * @return
	 */
	public void importCRMData(CommonVo vo) {
		String name = "CRM数据采集";
		String rusult = "成功";
		Date startTime = Calendar.getInstance().getTime();
		String remark = "";
		try {
			importDataService.importRepairdataFromCrm(vo);
		} catch (Exception e) {
			rusult = "失败";
			logger.error(e.getMessage(), e);
			remark = e.getMessage().substring(0, 230);
		}
		Date endTime = Calendar.getInstance().getTime();
		String statisticsTime = vo.getStartTime()+" 至 "+vo.getEndTime();
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime, startTime, endTime, rusult, "auto", "数据采集", remark);
		sumOpService.insert(opLog);
	}
	
	/**
	 * 更新发货时间
	 * @param vo
	 */
	public void updateShipDate(CommonVo vo) {
		String name = "更新发货时间";
		String rusult = "成功";
		Date startTime = Calendar.getInstance().getTime();
		String remark = "";
		try {
			importDataService.updateShipDateForMES(vo);
		} catch (Exception e){
			rusult = "失败";
			logger.error(e.getMessage(),e);
			remark = e.getMessage().substring(0,230);
		}
		Date endTime = Calendar.getInstance().getTime();
		SumOperationLog opLog = new SumOperationLog(name, null, startTime, endTime, rusult, "manual", "数据更新", remark);
		sumOpService.insert(opLog);
	}
	
	/**
	 * 下线汇总
	 * @param record
	 * @return
	 */
	public void updateDownLineTotal(DownLineTotal record) {
		String name = "下线汇总";
		String statisticsTime = record.getStatisticsMonth();
		String rusult = "成功";
		Date startTime = Calendar.getInstance().getTime();
		String remark = "";
		try {
			importDataService.updateDownlineTotalByMonth(record);
		} catch (Exception e){
			rusult = "失败";
			logger.error(e.getMessage(),e);
			remark = e.getMessage().substring(0,230);
		}
		Date endTime = Calendar.getInstance().getTime();
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime, startTime, endTime, rusult, "auto", "数据汇总", remark);
		sumOpService.insert(opLog);		
	}
	
	/**
	 * 发货汇总
	 * @param record
	 * @return
	 */
	public void updateShipTotal(ShipTotal record) {
		String name = "发货汇总";
		String statisticsTime = record.getStatisticsMonth();
		String rusult = "成功";
		Date startTime = Calendar.getInstance().getTime();
		String remark = "";
		try {
			importDataService.updateShipTotalByMonth(record);
		} catch (Exception e) {
			rusult = "失败";
			logger.error(e.getMessage(),e);
			remark = e.getMessage().substring(0,230);
		}
		Date endTime = Calendar.getInstance().getTime();
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime, startTime, endTime, rusult, "auto", "数据汇总", remark);
		sumOpService.insert(opLog);
	}
	
	/**
	 * 维修汇总
	 * @param record
	 * @return
	 */
	@RequestMapping("/updateMarketRepairTotal")
	public void updateMarketRepairTotal(MarketRepairTotal record) {
		String name = "维修汇总";
		String rusult = "成功";
		Date startTime = Calendar.getInstance().getTime();
		String remark = "";
		try {
			int row = importDataService.updateMarketRepairTotalByMonth(record);
			if(row > 0 && record.getRepairedMonth()!=null){
				laterSumtimeService.updateToLater(record.getRepairedMonth());
			}
		} catch (Exception e) {
			rusult = "失败";
			logger.error(e.getMessage(),e);
			remark = e.getMessage().substring(0,230);
		}
		Date endTime = Calendar.getInstance().getTime();
		String statisticsTime = record.getRepairedMonth();
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime, startTime, endTime, rusult, "auto", "数据汇总", remark);
		sumOpService.insert(opLog);
	}
	
	/**
	 * 安装维修汇总
	 * @param statisticsMonth
	 */
	private void sumInstallRepair(MarketRepairTotal repair) {
		String name = "安装维修汇总";
		String rusult = "成功";
		String remark = "";
		Date startTime = Calendar.getInstance().getTime();
		try {
			importDataService.sumInstallRepairData(repair);
		} catch (Exception e) {
			rusult = "失败";
			logger.error(e.getMessage(), e);
			remark = e.getMessage().substring(0,230);
		}
		Date endTime = Calendar.getInstance().getTime();
		SumOperationLog opLog = new SumOperationLog(name, repair.getRepairedMonth(), startTime, endTime, rusult, "auto", "数据汇总", remark);
		sumOpService.insert(opLog);
	}
	
	/**
	 * 安装汇总
	 * @param statMonth
	 * @param endMonth
	 */
	public void sumInstallTotal(String statMonth, String endMonth) {
		String name = "安装汇总";
		String result = "成功";
		Date startTime = Calendar.getInstance().getTime();
		String remark = "";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("startDate", "".equals(statMonth) ? null : statMonth);
		paramMap.put("endDate", "".equals(endMonth) ? null : endMonth);
		try {
			importDataService.sumInstallMonthData(paramMap);
		} catch (Exception e) {
			result = "失败";
			logger.error(e.getMessage(),e);
			remark = e.getMessage();
		}
		Date endTime = Calendar.getInstance().getTime();
		SumOperationLog opLog = new SumOperationLog(name, statMonth +" 到 " + endMonth, startTime, endTime, result, "auto", "数据汇总", remark);
		sumOpService.insert(opLog);
	}
	
	/**
	 * 每月3日凌晨2点   自动更新MES数据汇总表月份
	 * @method: insertMesDataSumMomth() -by fjt
	 * @TODO:   void
	 */
	@Scheduled(cron="0 0 2 3 * ?") 
	public void insertMesDataSumMomth()
	{	
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
        String queryMonth = DateFormatUtils.format(cal.getTime(), "yyyy-MM");
        MesDataSum mesDataSum = new MesDataSum();
        mesDataSum.setQueryMonth(queryMonth);
        try {
			bphCommonService.insertMesDataSumMonth(mesDataSum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        //更新年度指标
        CountPerformanceMonth count = new CountPerformanceMonth();
        count.setQueryMonth(queryMonth);
        countPerformanceMonthService.CaculateCountPerformaceMonth(count);
	}
	
	/**
	 * 每月一日凌晨4点插入qms_data数据
	 * @method: insertQmsData() -by fjt
	 * @TODO:   void
	 */
	@Scheduled(cron="0 0 4 1 * ?")  //
	public void insertQmsData() {	
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
        String queryMonth = DateFormatUtils.format(cal.getTime(), "yyyy-MM");
        MesDataSum mesDataSum = new MesDataSum();
        mesDataSum.setQueryMonth(queryMonth);
        bphCommonService.insertQmsData(mesDataSum);
	}
	
	/**
	 * 每天凌晨03:00更新在线-MES更换数据
	 */
	@Scheduled(cron="0 0 3 ? * *")
	public void sumReplaceDayData() {
		String name = "在线-MES更换数据日汇总";
		String rusult = "成功";
		Date startTime = Calendar.getInstance().getTime();
		String remark = "";
		String yesterday = getYesterday();
		try {
			onlineService.SumDataDay(yesterday, yesterday);
		} catch (Exception e) {
			logger.error(e.getMessage());
			rusult = "失败";
			remark = e.getMessage().substring(0,230);
		}
		Date endTime = Calendar.getInstance().getTime();
		String statisticsTime = yesterday + " 00:00:00 至 " + yesterday + " 23:59:59";
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime, startTime, endTime, rusult, "auto", "数据采集", remark);
		sumOpService.insert(opLog);
	}
	
	/**
	 * 每天凌晨01:00触发 关键件绑定汇总
	 */
	@Scheduled(cron="0 0 1 ? * *")
	public void sumcountDayData() {
		String name = "在线-关键件绑定汇总";
		String rusult = "成功";
		Date startTime = Calendar.getInstance().getTime();
		String remark = "";
		String yesterday = getYesterday();
		try {
			onlineService.insertdataTkeypartsum();
		} catch (Exception e) {
			logger.error(e.getMessage());
			rusult = "失败";
			remark = e.getMessage().substring(0,230);
		}
		Date endTime = Calendar.getInstance().getTime();
		String statisticsTime = yesterday + " 00:00:00 至 " + yesterday + " 23:59:59";
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime, startTime, endTime, rusult, "auto", "数据采集", remark);
		sumOpService.insert(opLog);
	}

	/**
	 * 每天凌晨02:00触发 更新mes退次数据
	 */
	@Scheduled(cron="0 0 2 ? * *")
	public void sumtreturnwareData() {
		String name = "在线-MES退次汇总";
		String rusult = "成功";
		Date startTime = Calendar.getInstance().getTime();
		String remark = "";
		String yesterday = getYesterday();
		try {
			onlineService.insertTreturnwaresum();
		} catch (Exception e) {
			logger.error(e.getMessage());
			rusult = "失败";
			remark = e.getMessage().substring(0,230);
		}
		Date endTime = Calendar.getInstance().getTime();
		String statisticsTime = yesterday + " 00:00:00 至 " + yesterday + " 23:59:59";
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime,
				startTime, endTime, rusult, "auto", "数据采集", remark);
		sumOpService.insert(opLog);
	}
	
	/**
	 * 每月4号02:00更新物料系统的市场发货数及市场维修数
	 * */
	@Scheduled(cron="0 0 2 4 * ?")
	public void updateMarketData() {
		MarketPart vo = getMarketPart();
		updateMarketShipData(vo);
		updateMarketRepairData(vo);
	}
	
	/**
	 * 更新物料系统市场发货
	 * @param vo
	 */
	private void updateMarketShipData(MarketPart vo) {
		String name = "物料系统市场发货汇总";
		String rusult = "成功";
		Date startTime = Calendar.getInstance().getTime();
		String remark = "";
		try {
			marketPartService.shipDataRecord(vo);
		} catch (Exception e) {
			rusult = "失败";
			logger.error(e.getMessage(), e);
			remark = e.getMessage().substring(0,230);
		}
		Date endTime = Calendar.getInstance().getTime();
		String statisticsTime = vo.getStartTime()+" 至 "+vo.getEndTime();
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime, startTime, endTime, rusult, "auto", "数据采集", remark);
		sumOpService.insert(opLog);
	}
	
	/**
	 * 更新物料系统市场维修
	 * @param vo
	 */
	private void updateMarketRepairData(MarketPart vo) {
		String name = "物料系统市场维修汇总";
		String rusult = "成功";
		Date startTime = Calendar.getInstance().getTime();
		String remark = "";
		try {
			marketPartService.repairDataRecord(vo);
		} catch (Exception e) {
			rusult = "失败";
			logger.error(e.getMessage(), e);
			remark = e.getMessage().substring(0,230);
		}
		Date endTime = Calendar.getInstance().getTime();
		String statisticsTime = vo.getStartTime()+" 至 "+vo.getEndTime();
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime,
				startTime, endTime, rusult, "auto", "数据采集", remark);
		sumOpService.insert(opLog);
	}
	
	/**
	 * 每月1号23:00更新物料系统IQC进料
	 * */
	@Scheduled(cron="0 0 23 * * ?")
	public void updaTestInstanceRecord() {
		String name = "物料系统进料部分数据汇总";
		String rusult = "成功";
	    Calendar cal = Calendar.getInstance();       	
		cal.setTime(new Date());
		Date endTime = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date startTime = cal.getTime();
		String remark = "";
		try {
			testInstanceService.updateTestInstance(DateEditor.formatDate(startTime, "yyyy-MM-dd"), DateEditor.formatDate(endTime, "yyyy-MM-dd"));
		} catch (Exception e) {
			rusult = "失败";
			logger.error(e.getMessage(), e);
			remark = e.getMessage().substring(0,230);
		}
		String statisticsTime = DateEditor.formatDate(startTime, "yyyy-MM-dd")+" 至 "+DateEditor.formatDate(endTime, "yyyy-MM-dd");
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime,
				startTime, endTime, rusult, "auto", "数据采集", remark);
		sumOpService.insert(opLog);
	}
	
	/**
	 * 每天5:00更新物料系统IQC进料
	 * */
	@Scheduled(cron="0 0 5 ? * *")
	public void updaTestInstanceRecordByDay() {
		String name = "物料系统进料部分数据汇总";
		String rusult = "成功";
	    Calendar cal = Calendar.getInstance();       	
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date startTime = cal.getTime();
		Date endTime = cal.getTime();
		String remark = "";
		try {
			testInstanceService.updateTestInstance(DateEditor.formatDate(startTime, "yyyy-MM-dd"), DateEditor.formatDate(endTime, "yyyy-MM-dd"));
		} catch (Exception e) {
			rusult = "失败";
			logger.error(e.getMessage(), e);
			remark = e.getMessage().substring(0,230);
		}
		String statisticsTime = DateEditor.formatDate(startTime, "yyyy-MM-dd")+" 至 "+DateEditor.formatDate(endTime, "yyyy-MM-dd");
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime,
				startTime, endTime, rusult, "auto", "数据采集", remark);
		sumOpService.insert(opLog);
	}

	/**
	 * 每2小时新增SMT上料记录
	 */
	@Scheduled(cron = "0 0 */2 * * ?")
	public void insertPFSAPANAMvData() {
		String failureReason = null;
		try {
			smtService.insertPFSAPANAMvData();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			failureReason = e.getMessage();
		}
		if (failureReason != null) {
			SmtLog smtLog = new SmtLog("上料记录", "pfsa_pana_mv_data", failureReason);
			smtLogService.insertSmtLog(smtLog);
		}
	}

	/**
	 * 每2小时新增SMT物料追溯
	 */
	@Scheduled(cron = "0 0 */2 * * ?")
	public void insertPFSAPANATraceData() {
		String failureReason = null;
		try {
			smtService.insertPFSAPANATraceData();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			failureReason = e.getMessage();
		}
		if (failureReason != null) {
			SmtLog smtLog = new SmtLog("物料追溯", "pfsa_pana_trace_data", failureReason);
			smtLogService.insertSmtLog(smtLog);
		}
	}

	/**
	 * 每2小时新增SMT抛料率
	 */
	@Scheduled(cron = "0 0 */2 * * ?")
	public void insertPFSAPANAScrapData() {
		String failureReason = null;
		try {
			smtService.insertPFSAPANAScrapData();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			failureReason = e.getMessage();
		}
		if (failureReason != null) {
			SmtLog smtLog = new SmtLog("抛料率", "pfsa_pana_scrap_data", failureReason);
			smtLogService.insertSmtLog(smtLog);
		}
	}
	
	/**
	 * 获取前一天的日期
	 * @return
	 */
	private String getYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
	}
	
	/**
	 * 获取上一个月的时间区间
	 * @return
	 */
	private MarketPart getMarketPart() {
		Calendar cal = Calendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
		Integer month = cal.get(Calendar.MONTH);
		String day = "31";
		if (month.equals("0")) {
			year = year - 1;
			month = 12;
		}
		if (month.equals("2")) {
			if (year % 4 > 0) {
				day = "28";
			} else {
				day = "29";
			}
		} else if (month.equals("1") || month .equals("3") || month .equals("5") || month .equals("7") || month .equals("8") || month .equals("10") || month .equals("12")) {
			day = "31";
		} else {
			day = "30";
		}
		String startTime = year + "-" + month + "-01";
		String endTime = year + "-" + month + "-" + day;
		MarketPart vo = new MarketPart();
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		return vo;
	}
	
	/**
	 * 格式化开始及结束日期为"yyyy-MM-dd HH:mm:ss"
	 * @param vo
	 */
	private void convertDate(CommonVo vo) {
		if(StringUtils.isNotBlank(vo.getStartTime())) {
			vo.setStartTime(vo.getStartTime() + " 00:00:00");
		}
		if(StringUtils.isNotBlank(vo.getEndTime())) {
			vo.setEndTime(vo.getEndTime() + " 23:59:59");
		}
	}
	
	public CommonServiceI getComService() {
		return comService;
	}


	public void setComService(CommonServiceI comService) {
		this.comService = comService;
	}


	public SumOperationLogServiceI getSumOpService() {
		return sumOpService;
	}


	public void setSumOpService(SumOperationLogServiceI sumOpService) {
		this.sumOpService = sumOpService;
	}


	public Logger getLogger() {
		return logger;
	}


	public void setLogger(Logger logger) {
		this.logger = logger;
	}


	public ComputeScoreServiceI getComputeScoreService() {
		return computeScoreService;
	}


	public void setComputeScoreService(ComputeScoreServiceI computeScoreService) {
		this.computeScoreService = computeScoreService;
	}


	public CountPerformanceMonthServiceI getCountPerformanceMonthService() {
		return countPerformanceMonthService;
	}


	public void setCountPerformanceMonthService(
			CountPerformanceMonthServiceI countPerformanceMonthService) {
		this.countPerformanceMonthService = countPerformanceMonthService;
	}

	public ImportDataServiceI getImportDataService() {
		return importDataService;
	}

	public void setImportDataService(ImportDataServiceI importDataService) {
		this.importDataService = importDataService;
	}
	
}
