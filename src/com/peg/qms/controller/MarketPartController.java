package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.dao.interceptor.PageParameter;
import com.peg.mes.highcharts.ChartObj;
import com.peg.model.CommonVo;
import com.peg.model.TimeMatrixResultVo;
import com.peg.model.part.MarketPart;
import com.peg.model.system.SumOperationLog;
import com.peg.service.CommonServiceI;
import com.peg.service.part.MarketPartServiceI;
import com.peg.service.part.TestInstanceServiceI;
import com.peg.service.system.SumOperationLogServiceI;
import com.peg.web.util.ConditionUtil;
import com.peg.web.util.ConstantInterface;
import com.peg.web.util.ExcelUtilities;
import com.peg.web.util.MarketPartUtil;
import com.peg.web.util.SortUtils;
import com.peg.web.util.WebUtil;

/**
 * 物料系统市场模块
 * @author sgh
 * */
@Controller
@RequestMapping("quality/marketPart")
public class MarketPartController extends BaseController {
	
	@Autowired
	private CommonServiceI commonService;
	
	@Autowired
	private TestInstanceServiceI testInstanceService;
	
	@Autowired
	private MarketPartServiceI marketPartService;
	
	@Autowired
	private SumOperationLogServiceI sumOpService;
	
	/**
	 * 市场质量不良排列图
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/marketDefectIndex")
	public String marketDefectIndex(Model model, MarketPart vo) {
		testInstanceService.loadData(model);
		setBaseData(model);
		ConditionUtil.loadSupplier(model, vo, testInstanceService);
		ConditionUtil.loadRegion(model, null, commonService);
		vo.setxCount(9);
		model.addAttribute("vo", vo);
		return "qms/part/market/marketDefect";
	}
	
	/**
	 * 市场不良趋势图-物料
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/marketDefectTrendIndex")
	public String marketDefectTrendIndex(Model model, MarketPart vo) {
		testInstanceService.loadData(model);
		setBaseData(model);
		ConditionUtil.loadSupplier(model, vo, testInstanceService);
		ConditionUtil.loadRegion(model, null, commonService);
		model.addAttribute("vo", vo);
		return "qms/part/market/marketDefectTrend";
	}
	
	/**
	 * 市场不良趋势图（新）
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/marketDefectTrendNewIndex")
	public String marketDefectTrendProductIndex(CommonVo o,Model model, MarketPart vo) {
		testInstanceService.loadData(model);
		setBaseData(model);
		ConditionUtil.loadSupplier(model, vo, testInstanceService);
		ConditionUtil.loadRegion(model, null, commonService);
		ConditionUtil.loadProductFamily(model, o, commonService);
		ConditionUtil.loadProductType(model, o, commonService);
		model.addAttribute("vo", vo);
		model.addAttribute("o",o);
		
		return "qms/part/market/marketDefectTrendNew";
	}
	
	/**
	 * 市场质量三角阵
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/marketDefectMatrixIndex")
	public String marketDefectMatrixIndex(Model model, MarketPart vo) {
		testInstanceService.loadData(model);
		setBaseData(model);
		ConditionUtil.loadSupplier(model, vo, testInstanceService);
		ConditionUtil.loadRegion(model, null, commonService);
		model.addAttribute("vo", vo);
		return "qms/part/market/marketDefectMatrix";
	}
	
	/**
	 * 市场不良明细 
	 * @throws Exception 
	 * */
	@RequestMapping("marketPartData")
	public String marketPartData(Model model,CommonVo commonVo,MarketPart vo, PageParameter page) throws Exception {
		if (vo.getTitle() != null ) {
			vo = marketPartService.getDefectDetailPart(page, model, vo);
		}
		if(StringUtils.isNotEmpty(vo.getCrmPartCategoryTwoName())){
			vo.setCrmPartCategoryTwoList(Arrays.asList(vo.getCrmPartCategoryTwoName().split(",")));
		}
		if(StringUtils.isNotEmpty(vo.getMesPartCategoryTwoName())){
			vo.setMesPartCategoryTwoList(Arrays.asList(vo.getMesPartCategoryTwoName().split(",")));
		}
		if(StringUtils.isNotEmpty(commonVo.getProductFamilyTxt())){
			vo.setProductFamilyList(Arrays.asList(commonVo.getProductFamilyTxt().split(";")));
		}
		if(StringUtils.isNotEmpty(commonVo.getPartTypeListTxt())){
			vo.setPartTypeList(Arrays.asList(commonVo.getPartTypeListTxt().split(";")));
		}
		testInstanceService.loadData(model);
		setBaseData(model);
		ConditionUtil.loadSupplier(model, vo, testInstanceService);
		ConditionUtil.loadRegion(model, null, commonService);
		model.addAttribute("vo", vo);
		//7912 【明细-部件不良】中【机型类别】更改为非必选项
//		if (StringUtils.isEmpty(vo.getProductType())) {
//			return "qms/part/market/marketDefectData";
//		}
		ConditionUtil.loadProductType(model, commonVo, commonService);
		ConditionUtil.loadProductFamily(model, commonVo, commonService);
		model.addAttribute("commonVo", commonVo);
		
		vo = MarketPartUtil.setData(marketPartService, vo);
		vo.setIsPage(ConstantInterface.YES);
		List<MarketPart> marketPartList = marketPartService.getDefectDetailData(page, vo);
		model.addAttribute("list", marketPartList);
		model.addAttribute("page", page);
		return "qms/part/market/marketDefectData";
	}
	
	/**
	 * 明细-来料入库
	 * @param model
	 * @param vo
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/incomingPartData")
	public String incomingPartData(Model model, MarketPart vo, PageParameter page) throws Exception {
		testInstanceService.loadData(model);
		setBaseData(model);
		List<MarketPart> list = new ArrayList<MarketPart>();
		if (vo.getTitle() != null) {
			vo = marketPartService.getDefectDetailPart(page, model, vo);
		}
		model.addAttribute("vo", vo);
		if (StringUtils.isEmpty(vo.getProductType())) {
			return "qms/part/market/incomingPartData";
		}
		vo = MarketPartUtil.setData(marketPartService, vo);
		vo.setIsPage(ConstantInterface.YES);
		list = marketPartService.getIncomingPartData(page, vo);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "qms/part/market/incomingPartData";
	}
	
	/**
	 * 明细-扫码入库
	 * @param model
	 * @param vo
	 * @param page
	 * @return
	 */
	@RequestMapping("/serialPartData")
	public String serialPartData(Model model, MarketPart vo, PageParameter page) {
		setBaseData(model);
		setPartType(model);
		ConditionUtil.loadSupplier(model, vo, testInstanceService);
		ConditionUtil.loadRegion(model, null, commonService);
		List<MarketPart> list = new ArrayList<MarketPart>();
		vo = MarketPartUtil.setData(marketPartService, vo);
		vo.setIsPage(ConstantInterface.YES);
		list = marketPartService.getSerialPartData(page, vo);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "qms/part/market/serialPartData";
	}
	
	/**
	 * 汇总数据
	 * @param model
	 * @param vo
	 * @param response
	 */
	@RequestMapping("updateData")
	public void updateData(Model model, MarketPart vo, HttpServletResponse response) {
		Map<String,String> map = new HashMap<String,String>();
		String result = "成功";
		String name = "";
		String remark = "";
		String msg = "";
		Date startTime = Calendar.getInstance().getTime();
		try {
			List<Integer> list = new ArrayList<Integer>();
			if (vo.getTitle() != null && vo.getTitle().equals(1)) {
				name = "物料系统市场发货汇总";
				list = marketPartService.shipDataRecord(vo);
				if (list != null && list.size() > 0) {
					msg = vo.getStartTime() + "-" + vo.getEndTime() + "期间共导入" + list.get(1) + "条记录";
				}
				map.put("result", "0");
			} else if (vo.getTitle() != null && vo.getTitle().equals(2)) {
				name = "物料系统市场维修汇总";
				list = marketPartService.repairDataRecord(vo);
				if (list != null && list.size() > 0) {
					msg = vo.getStartTime() + "-" + vo.getEndTime() + "期间共导入" + list.get(1) + "条记录";
				}
				map.put("result", "0");
			}
		} catch (Exception e) {
			result = "失败";
			map.put("result", "-1");
			msg = e.getMessage();
			logger.error(e.getMessage(),e);
			remark = e.getMessage().substring(0,230);;
		}
		Date endTime = Calendar.getInstance().getTime();
		String statisticsTime = vo.getStartTime() + " 至 " + vo.getEndTime();
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime, startTime, endTime, result, "manual", "数据采集", remark);
		sumOpService.insert(opLog);
		model.addAttribute("vo", vo);
		map.put("showResult", result);
		map.put("msg", msg);
		JSONObject jsonObject = JSONObject.fromObject(map);
		printResponContent(response, jsonObject.toString());
	}
	
	/**
	 * 更新供应商
	 * @param model
	 * @param vo
	 * @param response
	 */
	@RequestMapping("/updateShipSupplier")
	public void updateShipSupplier(Model model, MarketPart vo, HttpServletResponse response) {
		Map<String,String> map = new HashMap<String,String>();
		String result = "成功";
		String name = "";
		String remark = "";
		Date startTime = Calendar.getInstance().getTime();
		try {
			if (vo.getTitle() != null && vo.getTitle().equals(1)) {
				name = "物料系统市场发货供应商数据更新";
				marketPartService.updateSupplierByShip(vo);
				map.put("result", "0");
			} else if (vo.getTitle() != null && vo.getTitle().equals(2)) {
				name = "物料系统市场维修供应商数据更新";
				marketPartService.updateSupplierByRepair(vo);
				map.put("result", "0");
			}
		} catch (Exception e) {
			result = "失败";
			map.put("result", "-1");
			map.put("msg", e.getMessage());
			logger.error(e.getMessage(),e);
			remark = e.getMessage().substring(0,230);;
		}
		Date endTime = Calendar.getInstance().getTime();
		String statisticsTime = vo.getStartTime() + " 至 " + vo.getEndTime();
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime,
				startTime, endTime, result, "manual", "数据采集", remark);
		sumOpService.insert(opLog);
		model.addAttribute("vo", vo);
		map.put("showResult", result);
		JSONObject jsonObject = JSONObject.fromObject(map);
		printResponContent(response, jsonObject.toString());
	}
	
	/**
	 * 排列图-市场不良数/率
	 * @param model
	 * @param vo
	 * @param respon
	 * */
	@RequestMapping("/marketDefect")
	public void marketDefect(Model model, MarketPart vo, HttpServletResponse respon, HttpServletRequest request) {
		int result = 0;
		String msg = null;
		ChartObj chart = null;
		Map<String,Object> map = new HashMap<String,Object>();
		vo = MarketPartUtil.setData(marketPartService, vo);
		try {
			chart = getMarketDefectChart(vo);
		} catch (Exception e) {
			result = -1;
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		map.put("chartsInfo", chart);
		map.put("result", result);
		map.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	/**
	 * 趋势图，市场不良数/率
	 * @param model
	 * @param vo
	 * @param respon
	 * */
	@RequestMapping("/marketDefectTrend")
	public void marketDefectTrend(Model model, MarketPart vo, HttpServletResponse respon) {
		int result = 0;
		String msg = null;
		ChartObj chart = null;
		Map<String,Object> map = new HashMap<String,Object>();
		vo = MarketPartUtil.setData(marketPartService, vo);
		try {
			chart = getTrendDefect(vo);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("chartsInfo", chart);
		map.put("result", result);
		map.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	@RequestMapping("/marketDefectTrendNew")
	public void marketDefectTrendNew(String productFamilyTxt,String partTypeListTxt,Model model, MarketPart vo, HttpServletResponse respon) {

		int result = 0;
		String msg = null;
		ChartObj chart = new ChartObj();
		Map<String,Object> map = new HashMap<String,Object>();
		vo = MarketPartUtil.setData(marketPartService, vo);
		if(!vo.getCrmPartCategoryTwoName().isEmpty()){
			vo.setCrmPartCategoryTwoList(Arrays.asList(vo.getCrmPartCategoryTwoName().split(",")));
		}
		if(!vo.getMesPartCategoryTwoName().isEmpty()){
			vo.setMesPartCategoryTwoList(Arrays.asList(vo.getMesPartCategoryTwoName().split(",")));
		}
		if(!productFamilyTxt.isEmpty()){
			vo.setProductFamilyList(Arrays.asList(productFamilyTxt.split(";")));
		}
		if(!partTypeListTxt.isEmpty()){
			vo.setPartTypeList(Arrays.asList(partTypeListTxt.split(";")));
		}
		if(!vo.getRegionListTxt().isEmpty()){
			vo.setRegions(vo.getRegionListTxt().split(";"));
		}
		if(!vo.getFaultTypeCode().isEmpty()){
			vo.setFaultTypes(vo.getFaultTypeCode().split(","));
		}
		if(!vo.getFaultReasonCode().isEmpty()){
			vo.setFaultReasons(vo.getFaultReasonCode().split(","));
		}
		try {
			List<MarketPart> defectList = marketPartService.getDefectTrendNew(vo);
			List<MarketPart> downlineList = marketPartService.getDefectTrendDownLineData(vo);
			chart.setTitle(vo.getTitleContext());
			List<String> commonNameList = new ArrayList<String>(); //X轴值
			List<List<Double>> yValues = new ArrayList<List<Double>>(); //Y轴值
			List<Double> repairCount = new ArrayList<Double>();
			List<Double> repairs = new ArrayList<Double>();
			List<Double> downLine = new ArrayList<Double>();
			for(int i = 0; i < defectList.size(); i++) {
				commonNameList.add(defectList.get(i).getCommonName());
				Double repairQty = defectList.get(i).getRepairCount() + 0.0;
				Double downLineQty = downlineList.get(i).getQuantity() + 0.0;
				if(!downlineList.get(i).getCommonName().equals(defectList.get(i).getCommonName())){
					for(int j=0;j<downlineList.size();j++){
						if(downlineList.get(j).getCommonName().equals(defectList.get(i).getCommonName())){
							downLineQty =  downlineList.get(j).getQuantity() + 0.0;
							break;
						}
					}
				}
				repairCount.add(repairQty);
				downLine.add(downLineQty);
				String v = vo.getIsPorB();
				if(v.equals("0")){
					repairs.add(downLineQty != 0 ? Double.parseDouble(String.format("%.2f", (repairQty / downLineQty) * 100)) : 0);
				}else{
					repairs.add(downLineQty != 0 ? Double.parseDouble(String.format("%.0f", (repairQty / downLineQty) * 1000000)) : 0);
				}
				
			}
			yValues.add(repairCount);
			yValues.add(repairs);
			yValues.add(downLine);
			chart.setxValue(commonNameList);
			chart.setyValues(yValues);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("chartsInfo", chart);
		map.put("result", result);
		map.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	/**
	 * 趋势图导出
	 */
	@RequestMapping("/excelOutput_marketDefectTrendNew")
	public String excelOutput_marketDefectTrendNew(Model model,HttpServletRequest request,HttpServletResponse response,String x,String y1,String y2,String y3) {		
		try {
			//表头
			String[] CN = null;
			
			CN = new String[4];
			CN[0] = "时间";
			CN[1] = "维修数";
			CN[2] = "发货数";
			CN[3] = "维修率";
			
			String[] xl = x.split(",");
			String[] yl1 = y1.split(",");
			String[] yl2 = y2.split(",");
			String[] yl3 = y3.split(",");
			
		//表体数据
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < xl.length; i++) {
				String month = xl[i];
				String count = yl1[i];
				String downLines = yl2[i];
				String repair = yl3[i];
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = month;
				tmpStr[1] = count;
				tmpStr[2] = downLines;
				tmpStr[3] = repair;			
				excelList.add(tmpStr);
			}
			// 获取项目根目录
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath,
					contentType, "市场不良趋势图数据" + fname);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			model.addAttribute("message", "导出失败！请联系管理人员");
		}
		return "error/err";
	}
	
	/**
	 * 物料系统三角阵
	 * @param model
	 * @param vo
	 * */
	@RequestMapping("marketDefectMatrix")
	public String marketDefectMatrix(Model model, MarketPart vo) {
		testInstanceService.loadData(model);
		setBaseData(model);
		ConditionUtil.loadSupplier(model, vo, testInstanceService);
		ConditionUtil.loadRegion(model, null, commonService);
		vo = MarketPartUtil.setData(marketPartService, vo);
		try {
			model.addAttribute("list", MarketPartUtil.getDefectMatrixData(marketPartService, model, vo));
			model.addAttribute("vo", vo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return "qms/part/market/marketDefectMatrix";
	}
	
	/**
	 * 市场不良数/率计算
	 * @param vo
	 * @return
	 * @throws Exception
	 * */
	private ChartObj getMarketDefectChart(MarketPart vo) throws Exception {
		List<MarketPart> marketPartList = getDefectList(vo);
		ChartObj chart = new ChartObj();
		chart.setTitle(vo.getTitleContext());
		List<String> commonNameList = new ArrayList<String>(); //X轴值
		List<List<Double>> yValues = new ArrayList<List<Double>>(); //Y轴值
		List<Double> repairCount = new ArrayList<Double>(); //Y轴不良数
		List<Double> repairs = new ArrayList<Double>(); //Y轴不良率
		List<Double> quantityCount = new ArrayList<Double>(); //Y轴入库数
		List<String> commonNumberList = new ArrayList<String>(); //供应商编码
		int isSort = 0;
		for (MarketPart marketPart : marketPartList) {
			if ((marketPart.getRepairCount() == null ? 0 : marketPart.getRepairCount() + 0.0) > 0 && (marketPart.getQuantity() == null ? 0 : marketPart.getQuantity() + 0.0) > 0) {
				marketPart.setRepairRate(Double.parseDouble(String.format("%.3f", (marketPart.getRepairCount() * 100.0 / marketPart.getQuantity()))));
			} else {
				marketPart.setRepairRate(0D);
//				marketPart.setQuantity(0L);
			}
			isSort+=marketPart.getRepairRate();
		}
		if (isSort > 0 && vo.getSortType().equals("不良率降序")) { //存在不良率及选择不良率降序时才降序
			SortUtils sortUtils = new SortUtils(vo);
			Collections.sort(marketPartList,  sortUtils);
			Collections.reverse(marketPartList);
		}
		for (MarketPart marketPart : marketPartList) {
			commonNameList.add(marketPart.getCommonName());
			repairCount.add(marketPart.getRepairCount() == null ? 0 : marketPart.getRepairCount() + 0.0);
			repairs.add(marketPart.getRepairRate());
			quantityCount.add(marketPart.getQuantity() == null ? 0 : marketPart.getQuantity() + 0.0);
			switch (vo.getTitle()) {
				case 1:
					commonNumberList.add(marketPart.getSupplierNumber() == null || marketPart.getSupplierNumber() == "" ? "无" : marketPart.getSupplierNumber());
					break;
				case 2:
					commonNumberList.add(marketPart.getPartNumber() == null || marketPart.getPartNumber() == "" ? "无" : marketPart.getPartNumber());
					break;
				case 3:
					commonNumberList.add(marketPart.getRegion() == null || marketPart.getRegion() == "" ? "无" : marketPart.getRegion());
					break;
				case 4:
					commonNumberList.add(marketPart.getFaultTypeCode() == null || marketPart.getFaultTypeCode() == "" ? "无" : marketPart.getFaultTypeCode());
					break;
				case 5:
					commonNumberList.add(marketPart.getFaultReasonCode() == null || marketPart.getFaultReasonCode() == "" ? "无" : marketPart.getFaultReasonCode());
				default:
					break;
			}
			
		}
		yValues.add(repairCount);
		yValues.add(repairs);
		yValues.add(quantityCount);
		chart.setTipValues(commonNumberList);
		chart.setxValue(commonNameList);
		chart.setyValues(yValues);
		return chart;
	}
	
	/**
	 * 获取不良趋势数/率
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	private ChartObj getTrendDefect(MarketPart vo) throws Exception {
		List<MarketPart> marketDefectPartList = marketPartService.getDefectTrend(vo); //不良数
		List<MarketPart> marketCountPartList = marketPartService.getDefectTrendShipData(vo); //发货数或入库数
		ChartObj chart = new ChartObj();
		chart.setTitle(vo.getTitleContext());
		List<MarketPart> repairPartList = MarketPartUtil.splitDataByDate(vo, marketDefectPartList, "不良");
		List<MarketPart> shipPartList = MarketPartUtil.splitDataByDate(vo, marketCountPartList, "发货");
		for (MarketPart repairPart : repairPartList) {
			for (MarketPart shipPart : shipPartList) {
				if (repairPart.getCommonName().equals(shipPart.getCommonName())) {
					repairPart.setQuantity(shipPart.getQuantity() == null ? 0 : shipPart.getQuantity());
				}
			}
		}
		if (ConstantInterface.DATE_FORMAT_MONTH.equals(vo.getSelectDate()) && StringUtils.isNotEmpty(vo.getIsConsumed())) {
//			repairPartList = MarketPartUtil.getCtrLine(marketPartService, vo, repairPartList);
		}
		List<String> commonNameList = new ArrayList<String>(); //X轴值
		List<List<Double>> yValues = new ArrayList<List<Double>>(); //Y轴值
		List<Double> repairCount = new ArrayList<Double>(); //Y轴不良数
		List<Double> repairs = new ArrayList<Double>(); //Y轴不良率
		List<Double> shipCount = new ArrayList<Double>(); //发货数或者入库数
		List<Double> topCtrLineValue = new ArrayList<Double>(); //上控制线
		List<Double> lowCtrLineValue = new ArrayList<Double>(); //下控制线
		for (MarketPart marketPart : repairPartList) {
			commonNameList.add(marketPart.getCommonName());
			repairCount.add(marketPart.getRepairCount() == null ? 0 : marketPart.getRepairCount() + 0.0);
			if ((marketPart.getRepairCount() == null ? 0 : marketPart.getRepairCount() + 0.0) > 0 && (marketPart.getQuantity() == null ? 0 : marketPart.getQuantity() + 0.0) > 0) {
				repairs.add(Double.parseDouble(String.format("%.2f", (marketPart.getRepairCount() * 100.0 / marketPart.getQuantity()))));
				shipCount.add(Double.parseDouble(marketPart.getQuantity().toString()));
			} else {
				repairs.add(0D);
				shipCount.add(0D);
			}
			if (vo.getSelectDate().equals("月") && StringUtils.isNotEmpty(vo.getIsConsumed())) {
//				topCtrLineValue.add(marketPart.getTopCtrLineValue() * 100);
//				lowCtrLineValue.add(marketPart.getLowerCtrLineValue() * 100);
			} else {
				topCtrLineValue.add(0d);
				lowCtrLineValue.add(0d);
			}
		}
		yValues.add(repairCount);
		yValues.add(repairs);
		yValues.add(shipCount);
		yValues.add(topCtrLineValue);
		yValues.add(lowCtrLineValue);
		chart.setxValue(commonNameList);
		chart.setyValues(yValues);
		return chart;
	}
	
	/**
	 * 导出Excel
	 * @param vo
	 * @param request
	 * @param response
	 */
	@RequestMapping("exportExcel")
	public void exportExcel(Model model, MarketPart vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		vo.setIsPage(ConstantInterface.NO);
		switch (vo.getTitle()) {
			case ConstantInterface.PART_DEFECT_TREND:
			case ConstantInterface.PART_DEFECT_TREND_MONTH:
			case ConstantInterface.PART_DEFECT_TREND_HUNDRED:
				exportExcelByDefectTrend(vo, request, response);
				break;
			case ConstantInterface.PART_DEFECT_MATRIX_POSITIVE:
				exportExcelByDefectMatrix(model, vo, request, response);
				break;
			case ConstantInterface.PART_DEFECT_MATRIX_NEGATIVE:
				exportExcelByDefectDownMatrix(model, vo, request, response);
				break;
			case ConstantInterface.PART_DEFECT_DETAIL_PART:
				exportExcelByDefectData(model, vo, request, response);
				break;
			case ConstantInterface.PART_DEFECT_DETAIL_IN_COMING_PART:
				exportExcelByIncomingData(vo, request, response);
				break;
			case ConstantInterface.PART_DEFECT_DETAIL_SERIAL_PART:
				exportExcelBySerialPartData(vo, request, response);
				break;
			default:
				exportExcelByDefect(vo, request, response);
				break;
		}
	}
	
	/**
	 * 导出扫码数据
	 * @param vo
	 * @param request
	 * @param response
	 */
	private void exportExcelBySerialPartData(MarketPart vo, HttpServletRequest request, HttpServletResponse response) {
		vo = MarketPartUtil.setData(marketPartService, vo);
		vo.setIsPage(ConstantInterface.NO);
		List<MarketPart> list = marketPartService.getSerialPartData(null, vo);
		String[] CN = {"主机条码", "物料名称", "物料条码", "物料编码", "物料类别", "物料级别", "机型类别", "产品系列", "产品型号", "产品成熟度", "服务中心", "供应商名称", "供应商简称", "供应商编码", "下线时间"};
		List<String[]> excelList = new ArrayList<String[]>();
		try {
			for (int i = 0; i < list.size(); i++) {
				String[] itemStr = new String[CN.length];
				itemStr[0] = list.get(i).getSupplier();
				itemStr[1] = list.get(i).getPartNumber();
				itemStr[2] = list.get(i).getPartName();
				itemStr[3] = list.get(i).getIsConsumed();
				itemStr[4] = list.get(i).getQuantity() + "";
				itemStr[5] = list.get(i).getProductType();
				itemStr[6] = list.get(i).getPartFamily();
				itemStr[7] = list.get(i).getPartType();
				itemStr[8] = list.get(i).getPartMaturity();
				itemStr[9] = list.get(i).getQueryMonth();
				excelList.add(itemStr);
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType =  "application/msexcel" ;
			ExcelUtilities.downloadExcel(request, response, filePath, contentType,  "明细-扫码入库"+fname);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	/**
	 * 导出趋势图数据
	 * @param vo
	 * @param request
	 * @param response
	 */
	private void exportExcelByDefectTrend(MarketPart vo, HttpServletRequest request, HttpServletResponse response) {
		try {
			vo.setxCount(vo.getxCount() - 1);
			vo = MarketPartUtil.setData(marketPartService, vo);
			ChartObj chart = getTrendDefect(vo);
			List<String> list = chart.getxValue();
			List<Double> repairList = chart.getyValues().get(0);
			List<Double> repairPercentList = chart.getyValues().get(1);
			List<Double> countPartList = chart.getyValues().get(2);
			List<Double> topCtrLineList = chart.getyValues().get(3);
			List<Double> lowCtrLineList = chart.getyValues().get(4);
			List<String[]> excelList = new ArrayList<String[]>();
			String[] CN = {"日期", "发货（入库）数", "维修数", "维修率", "上控制线", "下控制线"};
			for (int i = 0; i < list.size(); i++) {
				String[] itemStr = new String[CN.length];
				itemStr[0] = list.get(i);
				itemStr[1] = countPartList.get(i) + "";
				itemStr[2] = repairList.get(i) + "";
				itemStr[3] = repairPercentList.get(i) + "";
				itemStr[4] = topCtrLineList.get(i) + "";
				itemStr[5] = lowCtrLineList.get(i) + "";
				excelList.add(itemStr);
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType =  "application/msexcel" ;
			ExcelUtilities.downloadExcel(request, response, filePath, contentType, vo.getProductType() + "-市场不良趋势" + fname);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	/**
	 * 导出排列图数据
	 * @param vo
	 * @param request
	 * @param response
	 */
	private void exportExcelByDefect(MarketPart vo, HttpServletRequest request, HttpServletResponse response) {
		try {
			vo.setxCount(vo.getxCount() - 1);
			List<MarketPart> marketPartList = getDefectList(vo);
			for (MarketPart marketPart : marketPartList) {
				if ((marketPart.getRepairCount() == null ? 0 : marketPart.getRepairCount() + 0.0) > 0 && (marketPart.getQuantity() == null ? 0 : marketPart.getQuantity() + 0.0) > 0) {
					marketPart.setRepairRate(Double.parseDouble(String.format("%.3f", (marketPart.getRepairCount() * 100.0 / marketPart.getQuantity()))));
				} else {
					marketPart.setRepairRate(0D);
				}
			}
			String[] CN = {"", "发货（入库）数", "维修数", "维修率"};
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < marketPartList.size(); i++) {
				String[] itemStr = new String[CN.length];
				itemStr[0] = marketPartList.get(i).getCommonName();
				itemStr[1] = marketPartList.get(i).getQuantity() + "";
				itemStr[2] = marketPartList.get(i).getRepairCount() + "";
				itemStr[3] = marketPartList.get(i).getRepairRate() + "";
				excelList.add(itemStr);
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType =  "application/msexcel" ;
			ExcelUtilities.downloadExcel(request, response, filePath, contentType, vo.getProductType() + "-" + MarketPartUtil.getChartTitle(vo) + fname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出正三角阵数据
	 * @param model
	 * @param vo
	 * @param request
	 * @param response
	 */
	private void exportExcelByDefectMatrix(Model model, MarketPart vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			vo = MarketPartUtil.setData(marketPartService, vo);
			List<TimeMatrixResultVo> list = MarketPartUtil.getDefectMatrixData(marketPartService, model, vo);
			int length = 0;
			String selectDate = vo.getSelectDate().equals("year") ? "年" : (vo.getSelectDate().equals("quarter") ? "季度" : "月");
			Set<String> betweenMon = WebUtil.getBetweenMonth(list.get(0).getBaseMonth(), vo.getEndTime());
			if ("year".equals(vo.getSelectDate())) {
				length = WebUtil.getBetweenYear(betweenMon).size();
			} else if ("quarter".equals(vo.getSelectDate())) {
				length = WebUtil.getBetweenQuarter(betweenMon).size();
			} else {
				length = list.size();
			}
			String[] CN = new String[length + 2];
			CN[0] = "发货（入库）月份";
			CN[1] = "发货（入库）数";
			for(int j = 1; j <= CN.length - 2; j++){
				CN[j+1] = "第" + j + selectDate;
			}
			List<String[]> excelList = new ArrayList<String[]>();
			for (TimeMatrixResultVo tmpVO : list) {
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getBaseMonth();
				tmpStr[1] = Long.toString(tmpVO.getBaseCount());
				for (int j = 0; j < tmpVO.getReCount().size(); j++) {
					if(j + 2 >= tmpStr.length){
						continue;
					}
					String Count = null;
					if (tmpVO.getReCount().get(j) == null) {
						Count = " ";
					} else if(vo.getSortType() != null && vo.getSortType().equals("A")) {
						Count = Long.toString(tmpVO.getReCount().get(j));
					} else {
						Count = Long.toString(tmpVO.getRepairPercent().get(j));
					}
					tmpStr[j + 2] = Count;
				}
				excelList.add(tmpStr);
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType =  "application/msexcel" ;
			ExcelUtilities.downloadExcel(request, response, filePath, contentType, vo.getProductType() + "-市场不良正三角阵" + fname);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	/**
	 * 导出倒三角阵数据
	 * @param model
	 * @param vo
	 * @param request
	 * @param response
	 */
	private void exportExcelByDefectDownMatrix(Model model, MarketPart vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			vo = MarketPartUtil.setData(marketPartService, vo);
			List<TimeMatrixResultVo> list = MarketPartUtil.getDefectMatrixData(marketPartService, model, vo);
			Set<String> column = WebUtil.getBetweenMonth(vo.getStartTime(), vo.getEndTime());
			if ("year".equals(vo.getSelectDate())) {
				column = WebUtil.getBetweenYear(column);
			} else if ("quarter".equals(vo.getSelectDate())) {
				column = WebUtil.getBetweenQuarter(column);
			} else {
				for(int i=0;i<list.size();i++){
					list.get(i).setPreDiff(i);
				}
			}
			String[] CN = new String[column.size() + 2];
			CN[0] = "发货（入库）月份";
			CN[1] = "发货（入库）数";
			List<String> coluList = new ArrayList<String>();
			coluList.addAll(column);
			for(int j = 1; j <= CN.length-2; j++){
				CN[j+1] = coluList.get(j-1);
			}
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				TimeMatrixResultVo tmpVO = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getBaseMonth();
				tmpStr[1] = Long.toString(tmpVO.getBaseCount());
				for (int j = 0; j < tmpVO.getReCount().size(); j++) {
					int diff = tmpVO.getPreDiff();
					if(j + diff + 2 >= tmpStr.length){
						continue;
					}
					for(int k = 0; k < diff; k++){
						tmpStr[k+2] = " ";
					}
					String Count = null;
					if (tmpVO.getReCount().get(j) == null) {
						Count = " ";
					} else if(vo.getSortType() != null && vo.getSortType().equals("A")) {
						Count = Long.toString(tmpVO.getReCount().get(j));
					} else {
						Count = Long.toString(tmpVO.getRepairPercent().get(j));
					}
					tmpStr[diff + j + 2] = Count;
				}
				excelList.add(tmpStr);
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType =  "application/msexcel" ;
			ExcelUtilities.downloadExcel(request, response, filePath, contentType, vo.getProductType() + "-市场不良倒三角阵" + fname);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	/**
	 * 导出明细不良
	 * @param model
	 * @param vo
	 * @param request
	 * @param response
	 */
	private void exportExcelByDefectData(Model model, MarketPart vo, HttpServletRequest request, HttpServletResponse response) {

		if(StringUtils.isNotEmpty(vo.getCrmPartCategoryTwoName())){
			vo.setCrmPartCategoryTwoList(Arrays.asList(vo.getCrmPartCategoryTwoName().split(",")));
		}
		if(StringUtils.isNotEmpty(vo.getMesPartCategoryTwoName())){
			vo.setMesPartCategoryTwoList(Arrays.asList(vo.getMesPartCategoryTwoName().split(",")));
		}
		ConditionUtil.loadSupplier(model, vo, testInstanceService);
		ConditionUtil.loadRegion(model, null, commonService);
		List<MarketPart> marketPartList = marketPartService.getDefectDetailData(null, vo);
		String[] CN = {"维修工单", "主机条码", "服务中心", "MES物料编号", "CRM物料编号", "零部件条码", "物料名称", "供应商编号", "供应商名称", "故障大类代码", "故障大类名称", "故障小类代码", "故障小类分析", "维修日期"};
		List<String[]> excelList = new ArrayList<String[]>();
		try {
			for (int i = 0; i < marketPartList.size(); i++) {
				String[] itemStr = new String[CN.length];
				itemStr[0] = marketPartList.get(i).getOrderNumber();
				itemStr[1] = marketPartList.get(i).getSerialNumber();
				itemStr[2] = marketPartList.get(i).getRegion();
				itemStr[3] = marketPartList.get(i).getMesPartNumber();
				itemStr[4] = marketPartList.get(i).getPartNumber();
				itemStr[5] = marketPartList.get(i).getPartSerial();
				itemStr[6] = marketPartList.get(i).getPartName();
				itemStr[7] = marketPartList.get(i).getSupplierNumber();
				itemStr[8] = marketPartList.get(i).getSupplier();
				itemStr[9] = marketPartList.get(i).getFaultTypeCode();
				itemStr[10] = marketPartList.get(i).getFaultTypeName();
				itemStr[11] = marketPartList.get(i).getFaultReasonCode();
				itemStr[12] = marketPartList.get(i).getFaultReasonTxt();
				itemStr[13] = marketPartList.get(i).getRepairDate();
				excelList.add(itemStr);
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType =  "application/msexcel" ;
			ExcelUtilities.downloadExcel(request, response, filePath, contentType,  "明细-市场不良"+fname);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	/**
	 * 导出来料入库数据
	 * @param vo
	 * @param request
	 * @param response
	 */
	private void exportExcelByIncomingData(MarketPart vo, HttpServletRequest request, HttpServletResponse response) {
		vo = MarketPartUtil.setData(marketPartService, vo);
		vo.setIsPage(ConstantInterface.NO);
		List<MarketPart> marketPartList = marketPartService.getIncomingPartData(null, vo);
		String[] CN = {"供应商名称", "物料编码", "物料名称", "是否关键件", "入库数", "机型类别", "产品系列", "产品型号", "产品成熟度", "入库时间"};
		List<String[]> excelList = new ArrayList<String[]>();
		try {
			for (int i = 0; i < marketPartList.size(); i++) {
				String[] itemStr = new String[CN.length];
				itemStr[0] = marketPartList.get(i).getSupplier();
				itemStr[1] = marketPartList.get(i).getPartNumber();
				itemStr[2] = marketPartList.get(i).getPartName();
				itemStr[3] = marketPartList.get(i).getIsConsumed();
				itemStr[4] = marketPartList.get(i).getQuantity() + "";
				itemStr[5] = marketPartList.get(i).getProductType();
				itemStr[6] = marketPartList.get(i).getPartFamily();
				itemStr[7] = marketPartList.get(i).getPartType();
				itemStr[8] = marketPartList.get(i).getPartMaturity();
				itemStr[9] = marketPartList.get(i).getQueryMonth();
				excelList.add(itemStr);
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType =  "application/msexcel" ;
			ExcelUtilities.downloadExcel(request, response, filePath, contentType,  "明细-来料入库"+fname);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	/**
	 * 获取不良物料List
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	private List<MarketPart> getDefectList(MarketPart vo) throws Exception {
		List<MarketPart> marketDefectPartList = marketPartService.getDefectPartList(vo); //不良数
		List<MarketPart> marketCountPartList = marketPartService.getTotalityShipCountList(vo); //发货数或入库数
		if (vo.getIsConsumed().equals("2") && (vo.getTitle() == ConstantInterface.PART_DEFECT_HISTOGRAM_SUPPLIER || vo.getTitle() == ConstantInterface.PART_DEFECT_HISTOGRAM_REGION)) {
			switch (vo.getTitle()) { //供应商及区域非关键件时，无法计算不良率，所以供应商只显示入库数，区域只显示维修数
				case ConstantInterface.PART_DEFECT_HISTOGRAM_SUPPLIER:
					return marketCountPartList.size() > vo.getxCount() + 1 ? marketCountPartList.subList(0, vo.getxCount() + 1) : marketCountPartList;
				case ConstantInterface.PART_DEFECT_HISTOGRAM_REGION:
					return marketDefectPartList.size() > vo.getxCount() + 1 ? marketDefectPartList.subList(0, vo.getxCount() + 1) : marketDefectPartList;
			}
		}
		int i;
		for (i = 0; i < marketDefectPartList.size(); i++) { //只取前xCount条数据
			if (StringUtils.isNotEmpty(marketDefectPartList.get(i).getCommonName())) {
				if (vo.getTitle() == ConstantInterface.PART_DEFECT_HISTOGRAM_FAULT_TYPE || vo.getTitle() == ConstantInterface.PART_DEFECT_HISTOGRAM_FAULT_REASON) { //故障小类，故障大类时，发货数（入库数）为总数
					marketDefectPartList.get(i).setQuantity(marketCountPartList.size() > 0 ? marketCountPartList.get(0).getQuantity() : 0);
				} else {
					for (MarketPart countPart : marketCountPartList) { //设置发货数及入库数
						if (countPart.getCommonName().equals(marketDefectPartList.get(i).getCommonName())) {
							marketDefectPartList.get(i).setQuantity(countPart.getQuantity());
						}
					}
				}
			}
			if (i > vo.getxCount()) {
				break;
			}
		}
		return marketDefectPartList.size() > i ? marketDefectPartList.subList(0, i) : marketDefectPartList;
	}
}