package com.peg.qms.controller.bph;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import oracle.net.aso.e;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.base.exception.QmsException;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.echarts.EChartObj;
import com.peg.echarts.EChartsType;
import com.peg.model.CommonVo;
import com.peg.model.EntityClass;
import com.peg.model.MachineType;
import com.peg.model.ShiftGroup;
import com.peg.model.bph.AssemblyProductBack;
import com.peg.model.bph.BatchDefectRecord;
import com.peg.model.bph.BoxDefectRecord;
import com.peg.model.bph.FormerProcessFqcCheck;
import com.peg.model.bph.IpqcInspects;
import com.peg.model.bph.OqcCheck;
import com.peg.model.bph.PaintingDailyReport;
import com.peg.model.bph.ProcessAuditRecord;
import com.peg.model.bph.ProcessScoreSetting;
import com.peg.model.bph.QualityImprovementRfp;
import com.peg.model.bph.StampingDailyReport;
import com.peg.model.system.SysMesUser;
import com.peg.qms.controller.BaseController;
import com.peg.service.AreaServiceI;
import com.peg.service.CommonServiceI;
import com.peg.service.MachineTypeServiceI;
import com.peg.service.ShiftGroupServiceI;
import com.peg.service.bph.CommonSelectService;
import com.peg.service.bph.ProcessScoreSettingServiceI;
import com.peg.service.system.SysMesUserServiceI;

/**公共查询类控制器
 * @Class: CommonSelectController @TODO:
 */
@SuppressWarnings("unused")
@Controller
@RequestMapping("base/commonselect")
public class CommonSelectController extends BaseController{

	@Autowired
	private CommonSelectService commonSelectService;
	
	@Autowired
	private SysMesUserServiceI sysMesUserService;
	
	@Autowired
	private AreaServiceI areaService;
	
	@Autowired 
	private ShiftGroupServiceI shiftGroupService;
	
	@Autowired
	private ProcessScoreSettingServiceI processScoreSettingService;
	
	@Autowired
	private MachineTypeServiceI machineTypeService;
	
	@Autowired
	private CommonServiceI commonService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	
	/**
	 * 生产退次查询表
	 * @param model
	 * @param assemblyProductBack
	 * @param page
	 * @return
	 */
	@RequestMapping("/assemblyList")
	public String assemblyList(Model model,AssemblyProductBack assemblyProductBack,PageParameter page) throws Exception
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("startnum", assemblyProductBack.getStartnum());
		bs.put("endnum", assemblyProductBack.getEndnum());
		String groupS = decode(assemblyProductBack.getShifS());
		String dateZ = decode(assemblyProductBack.getTimS());
		String facS = decode(assemblyProductBack.getFacS());
		Date startTime = null;
		Date endTime = null;
		if(groupS!=null && !"".equals(groupS) && dateZ !=null && !"".equals(dateZ)){
			Calendar cal = Calendar.getInstance();       	
			cal.setTime(sdf.parse(dateZ));
			/*按照财务月*/
//			cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.DAY_OF_MONTH, 26);
//		    startTime = cal.getTime();
//			cal.add(Calendar.MONTH, 1);
//			cal.set(Calendar.DAY_OF_MONTH, 25); 
			/*按照自然月*/
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		    startTime = cal.getTime();
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 			
		    endTime = cal.getTime();
			bs.put("group", groupS);
			bs.put("startTime", startTime);
			bs.put("endTime", endTime);
			assemblyProductBack.setBaseFactory(facS);
			assemblyProductBack.setBaseGroup(groupS);
			assemblyProductBack.setStartTime(startTime);
			assemblyProductBack.setEndTime(endTime);
		}else{
			bs.put("factoryS", assemblyProductBack.getBaseFactory());
			bs.put("group", assemblyProductBack.getBaseGroup());
			bs.put("startTime", assemblyProductBack.getStartTime());
			bs.put("endTime", assemblyProductBack.getEndTime());
		}
		String PartTypeListTxt=assemblyProductBack.getPartTypeListTxt();
		String[] strarr=null;
		List<String> parttype=new ArrayList<String>();
		StringBuilder sb=new StringBuilder();
		if(PartTypeListTxt!=null && PartTypeListTxt!=""){
			strarr=PartTypeListTxt.split(";");
			Collections.addAll(parttype,strarr);
		}
		for (int i = 0; i < parttype.size(); i++) {
			if(i != parttype.size()-1){
				sb.append('"'+parttype.get(i)+'"'+",");
			}else {
				sb.append('"'+parttype.get(i)+'"');
			}
			
		}
		
		bs.setList(parttype);
		bs.put("areaS", assemblyProductBack.getBaseArea());		
		bs.put("dutyS", assemblyProductBack.getDutyS());
		bs.put("lineS", assemblyProductBack.getProductTypeS());
		bs.put("itemNameS", assemblyProductBack.getItemNameS());
		bs.put("types", "组装");
		
			
		
		String factory = "";
		
		if(assemblyProductBack.getBaseFactory() == null)
		{
			SysMesUser sysMesUser = getCurrentUser();
			factory = sysMesUser.getUda1();
		}
		else
		{
			factory = assemblyProductBack.getBaseFactory();
		}
		LoadFAPG(model, assemblyProductBack);
		
		Map<String, String> partMap=new HashMap<String, String>();
		CommonVo vo=new CommonVo();
		vo.setProductType(assemblyProductBack.getProductTypeS());
		List<CommonVo> partlist = commonService.getPartTypeFromMes(vo);
		if (StringUtils.isNotEmpty(vo.getProductType())) {
			for (int i = 0; i < partlist.size(); i++) {
				partMap.put(partlist.get(i).getPartType(), partlist.get(i).getPartType());
			}
		}

		List<AssemblyProductBack> list = commonSelectService.getAssemblyAllByPage(bs);
		for(int i =0; i<list.size(); i++){
			if(dateZ != null && !"".equals(dateZ)){
				if(list.get(i).getDateT().getTime()<startTime.getTime() || list.get(i).getDateT().getTime()>endTime.getTime() ){
					list.remove(i);
					i=i-1;	
				}
			}else if(assemblyProductBack.getStartTime() != null && !"".equals(assemblyProductBack.getStartTime())){
				if(list.get(i).getDateT().getTime()<assemblyProductBack.getStartTime().getTime() || list.get(i).getDateT().getTime()>assemblyProductBack.getEndTime().getTime()){
					list.remove(i);
					i=i-1;	
				}
			}
			
		}
		List<MachineType> mtypelist = machineTypeService.getAll();
		JSONObject res = JSONObject.fromObject(partMap);		
		model.addAttribute("info", res.toString());
		model.addAttribute("infosel", sb.toString());
		model.addAttribute("productTypes", mtypelist);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", assemblyProductBack);
		System.out.println(parttype.toString());
		return "qms/bph/reportTable/assemblyList";
	}
	
	@RequestMapping("/findunhealthy")
	public void unhealthy(Model model,AssemblyProductBack assemblyProductBack,HttpServletResponse respon) throws Exception{
		String PartTypeListTxt=assemblyProductBack.getPartTypeListTxt();
		String[] strarr=null;
		List<String> parttype=new ArrayList<String>();
		if(PartTypeListTxt!=null && PartTypeListTxt!=""){
			strarr=PartTypeListTxt.split(";");
			Collections.addAll(parttype,strarr);
		}
		assemblyProductBack.setList(parttype);
		int result = 0;
		String msg = null;
		Map<String, Object> map = new HashMap<String, Object>();
		EChartObj obj = null;
		try {
			obj = createRepairData(assemblyProductBack);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("info", obj);
		map.put("result", result);
		map.put("msg", msg);

		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	@RequestMapping("/findunhealthyType")
	public void unhealthyType(Model model,AssemblyProductBack assemblyProductBack,HttpServletResponse respon) throws Exception{
		String PartTypeListTxt=assemblyProductBack.getPartTypeListTxt();
		String[] strarr=null;
		List<String> parttype=new ArrayList<String>();
		if(PartTypeListTxt!=null && PartTypeListTxt!=""){
			strarr=PartTypeListTxt.split(";");
			Collections.addAll(parttype,strarr);
		}
		assemblyProductBack.setList(parttype);
		int result = 0;
		String msg = null;
		Map<String, Object> map = new HashMap<String, Object>();
		EChartObj obj = null;
		try {
			obj = createRepairTypeData(assemblyProductBack);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("info", obj);
		map.put("result", result);
		map.put("msg", msg);

		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	//组装生产/喷涂生产排列图
	private EChartObj createRepairTypeData(AssemblyProductBack assemblyProductBack){
		//获取并处理数据
		List<EntityClass> flist=commonSelectService.assemblyTypebad(assemblyProductBack);
		List<String> xlist=new ArrayList<String>();
		List<Double> yvalue1=new ArrayList<Double>();
		List<Double> yvalue2=new ArrayList<Double>();
		double dou1=0.00;
		double dou2=0.00;
		int badcount=0;//总的不良数
		double num=0;//不良叠加
		for (int i = 0; i < flist.size(); i++) {
			badcount=badcount+flist.get(i).getBadnumber();
		}
		for (int i = 0; i < flist.size(); i++) {
			if(i<11){
				xlist.add(flist.get(i).getProduct_type_s());
				double d=flist.get(i).getBadnumber();
				yvalue1.add(d);
				num=num+d;
				double jg=num/badcount*100;
				BigDecimal bg = new BigDecimal(jg);
				yvalue2.add(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

			}else {
				num=num+flist.get(i).getBadnumber();
				dou2=dou2+flist.get(i).getCountnumber();
				dou1=dou1+flist.get(i).getBadnumber();
			}
			if(i>11 && i==flist.size()-1){
				xlist.add("其他");
				yvalue1.add(dou1);
				double jg=num/badcount*100;
				BigDecimal bg = new BigDecimal(jg);
				yvalue2.add(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}	
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		EChartObj chart = new EChartObj();
		chart.setChartType(EChartsType.CHART_LINES);                      //图形类型
		chart.setTitle(assemblyProductBack.getBaseGroup()+fmt.format(assemblyProductBack.getStartTime())+
				"至"+fmt.format(assemblyProductBack.getEndTime())+"排列图");                                //设置标题
		chart.setToolboxShow(true);                                       //开启工具配置项
		chart.setToolboxFeatureSaveAsImageShow(true);                     //开启图片
		chart.setToolboxFeatureMagicTypeShow(true);                       //开启动态转换
		chart.setToolboxFeatureMagicTypeType(new String[]{"line","bar"}); //配置动态类型切换
		chart.setToolboxFeatureDataViewShow(true);                        //配置toolBox数据不可编辑
		chart.setyAxisAxisLabelFormatter("{value}℃");                    //配置坐标单位  
		chart.setSeriesType(new String[]{"bar","line"} );                //设置系列类型
		chart.setyLeftUnit("%");                                        //左单位
		chart.setBarWidth("30");										//柱子宽度
		String[] seriesNames ={"不良数","不良率"};

		List<List<Double>> ylist = new ArrayList<List<Double>>();
		try{
			ylist.add(yvalue1);
			ylist.add(yvalue2);
			chart.setSeriesNames(seriesNames);
			chart.setxValue(xlist);
			chart.setyValues(ylist);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		return chart;
	}
	@RequestMapping("/findunhealthycy")
	public void unhealthycy(Model model,StampingDailyReport stampingDailyReport,HttpServletResponse respon) throws Exception
	{
		int result = 0;
		String msg = null;
		Map<String, Object> map = new HashMap<String, Object>();
		EChartObj obj = null;
		try {
			obj = createRepairDatacy(stampingDailyReport);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("info", obj);
		map.put("result", result);
		map.put("msg", msg);

		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	@RequestMapping("/findarrange")
	public void arrange(Model model,AssemblyProductBack assemblyProductBack,HttpServletResponse respon){
		int result = 0;
		String msg = null;
		Map<String, Object> map = new HashMap<String, Object>();
		EChartObj obj = null;
		try {
			if(assemblyProductBack.getMarking().equals("组装退次率")){
				obj = createsequenceData(assemblyProductBack);
			}
			if(assemblyProductBack.getMarking().equals("喷涂退次率")){
				obj = createsequenceData(assemblyProductBack);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("info", obj);
		map.put("result", result);
		map.put("msg", msg);

		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	@RequestMapping("/findarrangecy")
	public void arrangecy(Model model,StampingDailyReport stampingDailyReport,HttpServletResponse respon){
		int result = 0;
		String msg = null;
		Map<String, Object> map = new HashMap<String, Object>();
		EChartObj obj = null;
		try {
			obj = createsequenceDatacy(stampingDailyReport);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("info", obj);
		map.put("result", result);
		map.put("msg", msg);

		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	@RequestMapping("/assemblyExcelOutput")
	public void assemblyExcelOutput(AssemblyProductBack assemblyProductBack, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
		try{
			commonSelectService.assemblyExcelOutput(assemblyProductBack,request, response);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
			
		}
	
	/**
	 * 前工序FQC查检表
	 * @param model
	 * @param formerProcessFqcCheck
	 * @param page
	 * @return
	 * @throws ParseException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/formerProcessList")
	public String formerProcessList(Model model,FormerProcessFqcCheck formerProcessFqcCheck,PageParameter page) throws QmsException, ParseException, UnsupportedEncodingException
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		String facS = decode(formerProcessFqcCheck.getFacS());
		String groupS = decode(formerProcessFqcCheck.getShifS());
		String dateZ = decode(formerProcessFqcCheck.getTimS());
		Date startTime = null;
		Date endTime = null;
		if(groupS!=null && !"".equals(groupS) && dateZ !=null && !"".equals(dateZ)){
			Calendar cal = Calendar.getInstance();       	
			cal.setTime(sdf.parse(dateZ));
			/*按照财务月*/
//			cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.DAY_OF_MONTH, 26);
//		    startTime = cal.getTime();
//			cal.add(Calendar.MONTH, 1);
//			cal.set(Calendar.DAY_OF_MONTH, 25); 
			/*按照自然月*/
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		    startTime = cal.getTime();
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		    endTime = cal.getTime();
			bs.put("group", groupS);
			bs.put("startTime", startTime);
			bs.put("endTime", endTime);
			formerProcessFqcCheck.setBaseFactory(facS);
			formerProcessFqcCheck.setGroup(groupS);
			formerProcessFqcCheck.setStartTime(startTime);
			formerProcessFqcCheck.setEndTime(endTime);
		}else{
			bs.put("factoryS", formerProcessFqcCheck.getBaseFactory());
			bs.put("group", formerProcessFqcCheck.getGroup());
			bs.put("groupS", formerProcessFqcCheck.getGroupS());
			bs.put("startTime", formerProcessFqcCheck.getStartTime());
			bs.put("endTime", formerProcessFqcCheck.getEndTime());
		}
		bs.put("areaS", formerProcessFqcCheck.getBaseArea());
		bs.put("typeS", formerProcessFqcCheck.getTypeS());
		bs.put("itemNameS", formerProcessFqcCheck.getItemNameS());
		bs.put("defectS", formerProcessFqcCheck.getDefectS());
		bs.put("methodS", formerProcessFqcCheck.getMethodS());
		bs.put("checkResultS", formerProcessFqcCheck.getCheckResultS());

		
	    String factory = "";
		
		if(formerProcessFqcCheck.getBaseFactory() == null)
		{
			SysMesUser sysMesUser = getCurrentUser();
			factory = sysMesUser.getUda1();
		}
		else
		{
			factory = formerProcessFqcCheck.getBaseFactory();
		}
		LoadFAPG(model, formerProcessFqcCheck);
//		List<Area> areaList = areaService.selectByFactory(factory);
		List<ShiftGroup> shiftGroupList = shiftGroupService.getShiftGroup(factory);
		List<FormerProcessFqcCheck> list = commonSelectService.getFormerProcessAllByPage(bs);
		List<FormerProcessFqcCheck> blist = new ArrayList<FormerProcessFqcCheck>();		
		for(FormerProcessFqcCheck former : list){
			former.setGroupS(former.getGroupS().substring(0, former.getGroupS().indexOf("_")));
			blist.add(former);
		}

		for(int i =0; i<blist.size(); i++){
			if(dateZ != null && !"".equals(dateZ)){
				if(blist.get(i).getDateT().getTime()<startTime.getTime() || blist.get(i).getDateT().getTime()>endTime.getTime()){
					blist.remove(i);
					i=i-1;	
				}
			}else if(formerProcessFqcCheck.getStartTime() != null && !"".equals(formerProcessFqcCheck.getStartTime())){
				if(blist.get(i).getDateT().getTime()<formerProcessFqcCheck.getStartTime().getTime() || blist.get(i).getDateT().getTime()>formerProcessFqcCheck.getEndTime().getTime()){
					blist.remove(i);
					i=i-1;
				}
			}
			
		}
		model.addAttribute("list", blist);
		model.addAttribute("page", page);
//		model.addAttribute("area",areaList);
		model.addAttribute("group",shiftGroupList);
		model.addAttribute("vo", formerProcessFqcCheck);
		return "qms/bph/reportTable/formerProcessList";
		
	}
	
	@RequestMapping("/fqcCheckExcelOutput")
	public void fqcCheckExcelOutput(FormerProcessFqcCheck formerProcessFqcCheck, 
			HttpServletRequest request, HttpServletResponse response) {
		try{
			commonSelectService.fqcCheckExcelOutput(formerProcessFqcCheck, request, response);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
			
		}
	/**
	 * 过程批量不良记录单
	 * @param model
	 * @param batchDefectRecord
	 * @param page
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	@RequestMapping("/batchDefectList")
	public String batchDefectList(Model model,BatchDefectRecord batchDefectRecord,PageParameter page) throws QmsException, UnsupportedEncodingException, ParseException
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		String facS = decode(batchDefectRecord.getFacS());
		String groupS = decode(batchDefectRecord.getShifS());
		String dateZ = decode(batchDefectRecord.getTimS());
		Date startTime = null;
		Date endTime = null;
		if(groupS!=null && !"".equals(groupS) && dateZ !=null && !"".equals(dateZ)){
			Calendar cal = Calendar.getInstance();       	
			cal.setTime(sdf.parse(dateZ));
			/*按照财务月*/
//			cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.DAY_OF_MONTH, 26);
//		    startTime = cal.getTime();
//			cal.add(Calendar.MONTH, 1);
//			cal.set(Calendar.DAY_OF_MONTH, 25); 
			/*按照自然月*/
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		    startTime = cal.getTime();
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		    endTime = cal.getTime();
			bs.put("group", groupS);
			bs.put("startTime", startTime);
			bs.put("endTime", endTime);
			batchDefectRecord.setBaseFactory(facS);
			batchDefectRecord.setBaseGroup(groupS);
			batchDefectRecord.setStartTime(startTime);
			batchDefectRecord.setEndTime(endTime);
		}else{
			bs.put("factoryS", batchDefectRecord.getBaseFactory());
			bs.put("group", batchDefectRecord.getBaseGroup());
			bs.put("startTime", batchDefectRecord.getStartTime());
			bs.put("endTime", batchDefectRecord.getEndTime());
		}
		bs.put("areaS", batchDefectRecord.getBaseArea());
		bs.put("typeS", batchDefectRecord.getTypeS());
		bs.put("productNameS", batchDefectRecord.getProductNameS());
		bs.put("defectS", batchDefectRecord.getDefectS());
		bs.put("batchS", batchDefectRecord.getBatchS());
		bs.put("processNodeS", batchDefectRecord.getProcessNodeS());
		bs.put("resultS", batchDefectRecord.getResultS());
		bs.put("topRistScore", batchDefectRecord.getTopRiskScore());
		bs.put("lowRistScore", batchDefectRecord.getLowRistScore());
	
        String factory = "";
		if(batchDefectRecord.getBaseFactory() == null)
		{
			SysMesUser sysMesUser = getCurrentUser();
			factory = sysMesUser.getUda1();
		}
		else
		{
			factory = batchDefectRecord.getBaseFactory();
			
		}
		LoadFAPG(model, batchDefectRecord);
		List<ProcessScoreSetting>  processList = processScoreSettingService.getProcessScore("发生流程节点","电器一厂");
		List<ProcessScoreSetting>  resultList = processScoreSettingService.getProcessScore("质量后果","电器一厂");
		List<BatchDefectRecord> list = commonSelectService.getBatchDefectAllByPage(bs);
		List<BatchDefectRecord> btlist = new ArrayList<BatchDefectRecord>();
		if(!list.isEmpty() && list != null){
			for(BatchDefectRecord batch : list){
				batch.setGroup1S(batch.getGroup1S() == null ? null :batch.getGroup1S().substring(0,batch.getGroup1S().indexOf("_")));
				batch.setProcessNodeS(batch.getProcessNodeS()==null ? null :batch.getProcessNodeS().substring(0, 1));
				batch.setBatchS(batch.getBatchS()== null ? null :batch.getBatchS().substring(0,1));
				batch.setResultS(batch.getResultS()==null ? null :batch.getResultS().substring(0,1));
				btlist.add(batch);
			}

		}
		for(int i =0; i<btlist.size(); i++){
			if(dateZ != null && !"".equals(dateZ)){
				if(btlist.get(i).getRecordDateT().getTime() < startTime.getTime() || btlist.get(i).getRecordDateT().getTime() >endTime.getTime()){
					btlist.remove(i);
					i=i-1;	
				}
			}else if(batchDefectRecord.getStartTime() != null && !"".equals(batchDefectRecord.getStartTime())){
				if(btlist.get(i).getRecordDateT().getTime()<batchDefectRecord.getStartTime().getTime() || btlist.get(i).getRecordDateT().getTime() >batchDefectRecord.getEndTime().getTime()){
					btlist.remove(i);
					i=i-1;	
				}
			}		
		}
		model.addAttribute("list", btlist);
		model.addAttribute("page", page);
		model.addAttribute("process", processList);
		model.addAttribute("result", resultList);
		model.addAttribute("vo", batchDefectRecord);
		return "qms/bph/reportTable/batchDefectList";
		
	}
	@RequestMapping("/batchDefectExcelOutput")
	public void batchDefectExcelOutput(BatchDefectRecord batchDefectRecord, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
			try{
				commonSelectService.batchDefectExcelOutput(batchDefectRecord, request, response);
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
	
	/**
	 * 冲压质量日报表
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	@RequestMapping("/stampingDaliyList")
	public String stampingDaliyList(Model model,StampingDailyReport stampingDailyReport,PageParameter page) throws QmsException, UnsupportedEncodingException, ParseException
	{
		BaseSearch bs = new BaseSearch();
		bs.put("startnum", stampingDailyReport.getBadnum());
		bs.put("endnum",  stampingDailyReport.getBadnum2());		
		bs.setPage(page);
		String facS = decode(stampingDailyReport.getFacS());
		String groupS = decode(stampingDailyReport.getShifS());
		String dateZ = decode(stampingDailyReport.getTimS());
		Date startTime = null ;
		Date endTime = null ;
		if(groupS!=null && !"".equals(groupS) && dateZ !=null && !"".equals(dateZ)){
			Calendar cal = Calendar.getInstance();       	
			cal.setTime(sdf.parse(dateZ));
			/*按照财务月*/
//			cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.DAY_OF_MONTH, 26);
//		    startTime = cal.getTime();
//			cal.add(Calendar.MONTH, 1);
//			cal.set(Calendar.DAY_OF_MONTH, 25); 
			/*按照自然月*/
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		    startTime = cal.getTime();
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
			endTime = cal.getTime();
			bs.put("groupS", groupS);
			bs.put("startTime", startTime);
			bs.put("endTime", endTime);
			stampingDailyReport.setBaseFactory(facS);
			stampingDailyReport.setBaseGroup(groupS);
			stampingDailyReport.setStartTime(startTime);
			stampingDailyReport.setEndTime(endTime);
		}else{
			bs.put("factoryS",stampingDailyReport.getBaseFactory());
			bs.put("groupS", stampingDailyReport.getBaseGroup());
			bs.put("startTime", stampingDailyReport.getStartTime());
			bs.put("endTime", stampingDailyReport.getEndTime());
		}
		bs.put("areaS", stampingDailyReport.getBaseArea());
		bs.put("typeS", stampingDailyReport.getTypeS());
		bs.put("productNameS", stampingDailyReport.getProductNameS());
		bs.put("defectS", stampingDailyReport.getDefectS());
		bs.put("types", "冲压");

		
		String factory = "";
		if(stampingDailyReport.getBaseFactory() == null)
		{
			SysMesUser sysMesUser = getCurrentUser();
			factory = sysMesUser.getUda1();
		}
		else
		{
			factory = stampingDailyReport.getBaseFactory();
		}
		LoadFAPG(model, stampingDailyReport);
		List<StampingDailyReport> list = commonSelectService.getStampingDailyAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", stampingDailyReport);
		return "qms/bph/reportTable/stampingDaliyList";
		
	}
	
	@RequestMapping("/stampingDaliyExcelOutput")
	public void stampingDaliyExcelOutput(StampingDailyReport stampingDailyReport, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
			try{
				commonSelectService.stampingDaliyExcelOutput(stampingDailyReport, request, response);
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
	
	/**
	 * 过程审核记录表
	 * @param model
	 * @param processAuditRecord
	 * @param page
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	@RequestMapping("/processAuditList")
	public String processAuditList(Model model,ProcessAuditRecord processAuditRecord,PageParameter page) throws QmsException, UnsupportedEncodingException, ParseException
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		String facS = decode(processAuditRecord.getFacS());
		String groupS = decode(processAuditRecord.getShifS());
		String dateZ = decode(processAuditRecord.getTimS());
		Date startTime = null;
		Date endTime = null ;
		if(groupS!=null && !"".equals(groupS) && dateZ !=null && !"".equals(dateZ)){
			Calendar cal = Calendar.getInstance();       	
			cal.setTime(sdf.parse(dateZ));
			/*按照财务月*/
//			cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.DAY_OF_MONTH, 26);
//		    startTime = cal.getTime();
//			cal.add(Calendar.MONTH, 1);
//			cal.set(Calendar.DAY_OF_MONTH, 25); 
			/*按照自然月*/
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		    startTime = cal.getTime();
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
			endTime = cal.getTime();
			bs.put("groupS", groupS);
			bs.put("startTime", startTime);
			bs.put("endTime", endTime);
			processAuditRecord.setBaseFactory(facS);
			processAuditRecord.setBaseGroup(groupS);
			processAuditRecord.setStartTime(startTime);
			processAuditRecord.setEndTime(endTime);
		}else{
			bs.put("factoryS",processAuditRecord.getBaseFactory());
			bs.put("groupS", processAuditRecord.getBaseGroup());
			bs.put("startTime", processAuditRecord.getStartTime());
			bs.put("endTime", processAuditRecord.getEndTime());
		}
		bs.put("areaS", processAuditRecord.getBaseArea());
		bs.put("defectS", processAuditRecord.getDefectS());
		bs.put("dutyS", processAuditRecord.getDutyS());
		bs.put("followManS", processAuditRecord.getFollowManS());
		bs.put("isCloseS", processAuditRecord.getIsCloseS());
	
		String factory = "";
		if(processAuditRecord.getBaseFactory() == null)
		{
			SysMesUser sysMesUser = getCurrentUser();
			factory = sysMesUser.getUda1();
		}
		else
		{
			factory = processAuditRecord.getBaseFactory();
		}
		LoadFAPG(model, processAuditRecord);
		List<ProcessAuditRecord> list = commonSelectService.getProcessAuditAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", processAuditRecord);
		return "qms/bph/reportTable/processAuditList";
		
	}
	
	@RequestMapping("/processAuditExcelOutput")
	public void processAuditExcelOutput(ProcessAuditRecord processAuditRecord, Model model,
			HttpServletRequest request, HttpServletResponse response) {
	
			try{
				commonSelectService.processAuditExcelOutput(processAuditRecord, request, response);
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		
		}
	/**
	 * 质量改善课题申报表
	 * @param model
	 * @param qualityImprovementRfp
	 * @param page
	 */
	@RequestMapping("/qualityImpList")
	public String qualityImpList(Model model,QualityImprovementRfp qualityImprovementRfp,PageParameter page) throws Exception
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		String facS = decode(qualityImprovementRfp.getFacS());
		String groupS = decode(qualityImprovementRfp.getShifS());
		String dateZ = decode(qualityImprovementRfp.getTimS());
		Date startTime = null ;
		Date endTime = null ;
		if(groupS!=null && !"".equals(groupS) && dateZ !=null && !"".equals(dateZ)){
			Calendar cal = Calendar.getInstance();       	
			cal.setTime(sdf.parse(dateZ));
			/*按照财务月*/
//			cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.DAY_OF_MONTH, 26);
//		    startTime = cal.getTime();
//			cal.add(Calendar.MONTH, 1);
//			cal.set(Calendar.DAY_OF_MONTH, 25); 
			/*按照自然月*/
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		    startTime = cal.getTime();
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
			endTime = cal.getTime();
			bs.put("dutyGroupS", groupS);
			bs.put("startTime", startTime);
			bs.put("endTime", endTime);
			qualityImprovementRfp.setBaseFactory(facS);
			qualityImprovementRfp.setBaseGroup(groupS);
			qualityImprovementRfp.setStartTime(startTime);
			qualityImprovementRfp.setEndTime(endTime);
		}else{
			bs.put("factoryS",qualityImprovementRfp.getBaseFactory());
			bs.put("dutyGroupS", qualityImprovementRfp.getBaseGroup());
			bs.put("startTime", qualityImprovementRfp.getStartTime());
			bs.put("endTime", qualityImprovementRfp.getEndTime());
		}
		bs.put("areaS", qualityImprovementRfp.getBaseArea());
		bs.put("typeS", qualityImprovementRfp.getRfpNameS());
		bs.put("productNameS", qualityImprovementRfp.getFinderS());
		bs.put("defectS", qualityImprovementRfp.getTotalScoreI());
	
		String factory = "";
		if(qualityImprovementRfp.getBaseFactory() == null)
		{
			SysMesUser sysMesUser = getCurrentUser();
			factory = sysMesUser.getUda1();
		}
		else
		{
			factory = qualityImprovementRfp.getBaseFactory();
		}
		LoadFAPG(model, qualityImprovementRfp);
		List<QualityImprovementRfp> list = commonSelectService.getQualityAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", qualityImprovementRfp);
		return "qms/bph/reportTable/qualityImpList";
		
	}
	
	@RequestMapping("/qualityImpExcelOutput")
	public void qualityImpExcelOutput(QualityImprovementRfp qualityImprovementRfp, Model model,
			HttpServletRequest request, HttpServletResponse response) {
			try{
				commonSelectService.qualityImpExcelOutput(qualityImprovementRfp, request, response);
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		
		}
	/**
	 * 市场开箱不良记录单
	 * @param model
	 * @param boxDefectRecord
	 * @param page
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	@RequestMapping("/boxDefectList")
	public String boxDefectList(Model model,BoxDefectRecord boxDefectRecord,PageParameter page) throws QmsException, UnsupportedEncodingException, ParseException
	{
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		String facS = decode(boxDefectRecord.getFacS());
		String groupS = decode(boxDefectRecord.getShifS());
		String dateZ = decode(boxDefectRecord.getTimeS());
		Date startTime = null;
		Date endTime = null;
		if(groupS!=null && !"".equals(groupS) && dateZ !=null && !"".equals(dateZ)){
			Calendar cal = Calendar.getInstance();       	
			cal.setTime(sdf.parse(dateZ));
			/*按照财务月*/
//			cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.DAY_OF_MONTH, 26);
//		    startTime = cal.getTime();
//			cal.add(Calendar.MONTH, 1);
//			cal.set(Calendar.DAY_OF_MONTH, 25); 
			/*按照自然月*/
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		    startTime = cal.getTime();
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		    endTime = cal.getTime();
			bs.put("group", groupS);
			bs.put("startTime", startTime);
			bs.put("endTime", endTime);
			boxDefectRecord.setBaseFactory(facS);
			boxDefectRecord.setBaseGroup(groupS);
			boxDefectRecord.setStartTime(startTime);
			boxDefectRecord.setEndTime(endTime);
		}else{
			bs.put("factoryS",boxDefectRecord.getBaseFactory());
			bs.put("group", boxDefectRecord.getBaseGroup());
			bs.put("startTime", boxDefectRecord.getStartTime());
			bs.put("endTime", boxDefectRecord.getEndTime());
		}
		bs.put("defectSourceS", boxDefectRecord.getDefectSourceS());
		bs.put("defectTypeS", boxDefectRecord.getDefectTypeS());
		bs.put("productNumberS", boxDefectRecord.getProductNumberS());
		bs.put("processNodeS", boxDefectRecord.getProcessNodeS());
		bs.put("lotQtyI", boxDefectRecord.getLotQtyS());
		bs.put("qualityResultS", boxDefectRecord.getQualityResultS());
		bs.put("lowRistScore", boxDefectRecord.getLowRistScore());
		bs.put("topRistScore", boxDefectRecord.getTopRistScore());
	
		String factory = "";
		if(boxDefectRecord.getBaseFactory() == null)
		{
			SysMesUser sysMesUser = getCurrentUser();
			factory = sysMesUser.getUda1();
		}
		else
		{
			factory = boxDefectRecord.getBaseFactory();
		
		}
		LoadFAPG(model, boxDefectRecord);
//		List<Area> areaList = areaService.selectByFactory(factory);
//		List<ShiftGroup> shiftGroupList = shiftGroupService.getShiftGroup(factory);
		List<ProcessScoreSetting>  processList = processScoreSettingService.getProcessScore("发生流程节点","电器一厂");
		List<ProcessScoreSetting>  resultList = processScoreSettingService.getProcessScore("质量后果","电器一厂");
		List<BoxDefectRecord> list = commonSelectService.getBoxDefectAllByPage(bs);
		List<BoxDefectRecord> btlist = new ArrayList<BoxDefectRecord>();
		if(!list.isEmpty() && list != null){
			for(BoxDefectRecord batch : list){
				batch.setGroup1S(batch.getGroup1S().substring(0,batch.getGroup1S().indexOf("_")));
//				batch.setProcessNodeS(batch.getProcessNodeS().substring(0, 1));
//				batch.setLotQtyS(batch.getLotQtyS().substring(0,1));
//				batch.setQualityResultS(batch.getQualityResultS().substring(0,1));
				btlist.add(batch);
			}

		}
		for(int i =0; i<btlist.size(); i++){
			if(dateZ != null && !"".equals(dateZ)){
				if(btlist.get(i).getRecordDateT().getTime() < startTime.getTime() || btlist.get(i).getRecordDateT().getTime() >endTime.getTime()){
					btlist.remove(i);
					i=i-1;	
				}
			}else if(boxDefectRecord.getStartTime() != null && !"".equals(boxDefectRecord.getStartTime())){
				if(btlist.get(i).getRecordDateT().getTime()<boxDefectRecord.getStartTime().getTime() || btlist.get(i).getRecordDateT().getTime()>boxDefectRecord.getEndTime().getTime()){
					btlist.remove(i);
					i=i-1;	
				}
			}
			
		}
		model.addAttribute("list", btlist);
		model.addAttribute("page", page);
		model.addAttribute("process", processList);
		model.addAttribute("result", resultList);
		model.addAttribute("vo", boxDefectRecord);
		return "qms/bph/reportTable/boxDefectList";
		
	}
	
	@RequestMapping("/boxDefectExcelOutput")
	public void boxDefectExcelOutput(BoxDefectRecord boxDefectRecord, Model model,
			HttpServletRequest request, HttpServletResponse response) {

			try{
				commonSelectService.boxDefectExcelOutput(boxDefectRecord, request, response);
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
	/**
	 * 喷涂质量日报表
	 * @param model
	 * @param paintingDailyReport
	 * @param page
	 * @return
	 * @throws QmsException
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	@RequestMapping("/paintingDailyList")
	public String paintingDailyList(Model model,PaintingDailyReport paintingDailyReport,PageParameter page) throws QmsException, UnsupportedEncodingException, ParseException
	{
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		String facS = decode(paintingDailyReport.getFacS());
		String groupS = decode(paintingDailyReport.getShifS());
		String dateZ = decode(paintingDailyReport.getTimS());
		Date startTime = null;
		Date endTime = null ;
		if(groupS!=null && !"".equals(groupS) && dateZ !=null && !"".equals(dateZ)){
			Calendar cal = Calendar.getInstance();       	
			cal.setTime(sdf.parse(dateZ));
			/*按照财务月*/
//			cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.DAY_OF_MONTH, 26);
//		    startTime = cal.getTime();
//			cal.add(Calendar.MONTH, 1);
//			cal.set(Calendar.DAY_OF_MONTH, 25); 
			/*按照自然月*/
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		    startTime = cal.getTime();
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
			endTime = cal.getTime();
			bs.put("groupS", groupS);
			bs.put("startTime", startTime);
			bs.put("endTime", endTime);
			paintingDailyReport.setBaseFactory(facS);
			paintingDailyReport.setBaseGroup(groupS);
			paintingDailyReport.setStartTime(startTime);
			paintingDailyReport.setEndTime(endTime);
		}else{
			bs.put("factoryS",paintingDailyReport.getBaseFactory());
			bs.put("groupS", paintingDailyReport.getBaseGroup());
			bs.put("startTime", paintingDailyReport.getStartTime());
			bs.put("endTime", paintingDailyReport.getEndTime());
		}
		bs.put("areaS",paintingDailyReport.getBaseArea());
		bs.put("typeS", paintingDailyReport.getTypeS());
		bs.put("productNameS",paintingDailyReport.getProductNameS());
		bs.put("defectS", paintingDailyReport.getDefectS());
	
		String factory = null;
		
		if(paintingDailyReport.getBaseFactory() == null)
		{
			SysMesUser sysMesUser = getCurrentUser();
			factory = sysMesUser.getUda1();
		}
		else
		{
			factory = paintingDailyReport.getBaseFactory();
		}
		LoadFAPG(model, paintingDailyReport);
//		List<Area> areaList = areaService.selectByFactory(factory);
//		List<ShiftGroup> shiftGroupList = shiftGroupService.getShiftGroup(factory);
		List<PaintingDailyReport> list = commonSelectService.getPaintingDailyAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
//		model.addAttribute("area",areaList);
//		model.addAttribute("group",shiftGroupList);
		model.addAttribute("vo", paintingDailyReport);
		return "qms/bph/reportTable/paintingDailyList";
		
	}
	
	/**
	 * 喷涂一次合格率不良现象排列图
	 * @param model
	 * @param paintingDailyReport
	 * @param respon
	 */
	@RequestMapping("/findunhealthypt")
	public void unhealthypt(Model model,PaintingDailyReport paintingDailyReport,HttpServletResponse respon){
		List<EntityClass> list=commonSelectService.getPaintingDailybad(paintingDailyReport);
		List<String> xlist=new ArrayList<String>();
		List<Double> yvalue1=new ArrayList<Double>();
		List<Double> yvalue2=new ArrayList<Double>();
		double dou1=0.00;
		double dou2=0.00;
		int badcount=0;//总的不良数
		double num=0;//不良叠加
		for (int i = 0; i < list.size(); i++) {
			badcount=badcount+list.get(i).getBadnumber();
		}
		for (int i = 0; i < list.size(); i++) {
			if(i<12){
				xlist.add(list.get(i).getBadname());
				double d=list.get(i).getBadnumber();
				yvalue1.add(d);
				num=num+d;
				double jg=num/badcount*100;
				BigDecimal bg = new BigDecimal(jg);
				yvalue2.add(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

			}else {
				num=num+list.get(i).getBadnumber();
				dou2=dou2+list.get(i).getCountnumber();
			}
			if(i>12 && i==list.size()-1){
				xlist.add("其他");
				yvalue1.add(dou1);
				double jg=num/badcount*100;
				BigDecimal bg = new BigDecimal(jg);
				yvalue2.add(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}	
		DateFormat fmt = new SimpleDateFormat("YYYY-MM-dd");
		EChartObj chart = new EChartObj();
		chart.setChartType(EChartsType.CHART_LINES);                      //图形类型
		chart.setTitle(paintingDailyReport.getBaseGroup()+fmt.format(paintingDailyReport.getStartTime())+
				"至"+fmt.format(paintingDailyReport.getEndTime())+"排列图");                                //设置标题
		chart.setToolboxShow(true);                                       //开启工具配置项
		chart.setToolboxFeatureSaveAsImageShow(true);                     //开启图片
		chart.setToolboxFeatureMagicTypeShow(true);                       //开启动态转换
		chart.setToolboxFeatureMagicTypeType(new String[]{"line","bar"}); //配置动态类型切换
		chart.setToolboxFeatureDataViewShow(true);                        //配置toolBox数据不可编辑
		chart.setyAxisAxisLabelFormatter("{value}℃");                    //配置坐标单位  
		chart.setSeriesType(new String[]{"bar","line"} );                //设置系列类型
		chart.setyLeftUnit("℃");                                        //左单位
		chart.setBarWidth("30");										//柱子宽度
		String[] seriesNames ={"不良数","不良率"};

		List<List<Double>> ylist = new ArrayList<List<Double>>();
		int result=0;
		String msg=null;
		try{
			ylist.add(yvalue1);
			ylist.add(yvalue2);
			chart.setSeriesNames(seriesNames);
			chart.setxValue(xlist);
			chart.setyValues(ylist);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			result=-1;
			msg=e.getMessage();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("info", chart);
		map.put("result", result);
		map.put("msg", msg);

		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	@RequestMapping("findarrangept")
	public void arrangept(Model model,PaintingDailyReport paintingDailyReport,HttpServletResponse respon){
		//获取并处理数据
		List<EntityClass> flist=commonSelectService.paintingDailyarrange(paintingDailyReport);//真实值
		List<EntityClass> flist2=commonSelectService.paintingDailyarrangetwo(paintingDailyReport);//获取着真实分数
		EChartObj chart = new EChartObj();
		chart.setChartType(EChartsType.CHART_LINES);                      //图形类型
		chart.setTitle("时间序列图");                                //设置标题
		chart.setToolboxShow(true);                                       //开启工具配置项
		chart.setToolboxFeatureSaveAsImageShow(true);                     //开启图片
		chart.setToolboxFeatureMagicTypeShow(true);                       //开启动态转换
		//chart.setToolboxFeatureMagicTypeType(new String[]{"line","bar"}); //配置动态类型切换
		chart.setToolboxFeatureDataViewShow(true);                        //配置toolBox数据不可编辑
		chart.setyAxisAxisLabelFormatter("{value}");                    //配置坐标单位  
		chart.setSeriesType(new String[]{"line","line","line"} );       //设置系列类型
		chart.setyLeftUnit("");                                        //左单位
		String[] seriesNames ={"实际值","基准值","目标值"};
		List<String> xValue = datefand2();
		Collections.reverse(xValue); 
		List<Double> baiList = new ArrayList<Double>();//分数
		List<Double> yeList = new ArrayList<Double>();//基准值
		List<Double> yeList2 = new ArrayList<Double>();//目标值
		for (int i = 0; i < xValue.size(); i++) {
			String m=xValue.get(i);
			String m2="";
			for (int j = 0; j <flist.size(); j++) {
				m2=flist.get(j).getMonth();
				if(j==flist.size()-1 && !m.equals(m2)){
					yeList.add(0.00);
					yeList2.add(0.00);
					break;
				}
				if(m.equals(m2)){
					yeList.add(Double.valueOf(flist.get(j).getBasevalue()));
					yeList2.add(Double.valueOf(flist.get(j).getTargetvalue()));
					break;
				}else {}
			}
			for (int k = 0; k <flist2.size(); k++) {
				m2=flist2.get(k).getMonth().substring(0, 7);
				if(k==flist2.size()-1 && !m.equals(m2)){
					baiList.add(0.00);
					break;
				}
				if(m.equals(m2)){
					baiList.add(Double.valueOf(flist2.get(k).getIndexscore()));
					break;
				}else {}
			}
		}
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		int result=0;
		String msg=null;
		try{
			ylist.add(baiList);
			ylist.add(yeList);
			ylist.add(yeList2);
			chart.setSeriesNames(seriesNames);
			chart.setxValue(xValue);
			chart.setyValues(ylist);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			result=-1;
			msg=e.getMessage();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("info", chart);
		map.put("result", result);
		map.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	@RequestMapping("/paintingDailyExcelOutput")
	public void paintingDailyExcelOutput(PaintingDailyReport paintingDailyReport, Model model,
			HttpServletRequest request, HttpServletResponse response) {

			try{
				commonSelectService.paintingDailyExcelOutput(paintingDailyReport, request, response);
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
	/**
	 * IPQC巡检表
	 * @method: ipqcInspects() -by fjt
	 * @TODO:  
	 * @param model
	 * @param ipqcInspects
	 * @param page
	 */
	@RequestMapping("/ipqcInspects")
	private String ipqcInspects(Model model,IpqcInspects ipqcInspects, PageParameter page) throws Exception{
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		String facS = decode(ipqcInspects.getFacS());
		String groupS = decode(ipqcInspects.getShifS());
		String dateZ = decode(ipqcInspects.getTimS());
		Date startTime = null;
		Date endTime = null;
		if(groupS!=null && !"".equals(groupS) && dateZ !=null && !"".equals(dateZ)){
			Calendar cal = Calendar.getInstance();       	
			cal.setTime(sdf.parse(dateZ));
			/*按照财务月*/
//			cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.DAY_OF_MONTH, 26);
//		    startTime = cal.getTime();
//			cal.add(Calendar.MONTH, 1);
//			cal.set(Calendar.DAY_OF_MONTH, 25); 
			/*按照自然月*/
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		    startTime = cal.getTime();
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
			endTime = cal.getTime();
			bs.put("dutyGroup", groupS);
			bs.put("startTime", startTime);
			bs.put("endTime", endTime);
			ipqcInspects.setBaseFactory(facS);
			ipqcInspects.setDutyGroup(groupS);
			ipqcInspects.setStartTime(startTime);
			ipqcInspects.setEndTime(endTime);
		}else{
			bs.put("factoryS",ipqcInspects.getBaseFactory());			
			bs.put("dutyGroup", ipqcInspects.getDutyGroup());
			bs.put("startTime", ipqcInspects.getStartTime());
			bs.put("endTime", ipqcInspects.getEndTime());
		}
		bs.put("groupS", ipqcInspects.getGroup());
		bs.put("areaS",ipqcInspects.getBaseArea());
		bs.put("inspectResultS", ipqcInspects.getInspectResultS());
		bs.put("partName", ipqcInspects.getPartName());
		
		String factory = null;
		
		if(ipqcInspects.getBaseFactory() == null)
		{
			SysMesUser sysMesUser = getCurrentUser();
			factory = sysMesUser.getUda1();
		}
		else
		{
			factory = ipqcInspects.getBaseFactory();
		}
		LoadFAPG(model, ipqcInspects);
//		List<Area> areaList = areaService.selectByFactory(factory);
		List<ShiftGroup> shiftGroupList = shiftGroupService.getShiftGroup(factory);
		List<IpqcInspects> list = commonSelectService.getIpqcInspectsAllByPage(bs);
		for(IpqcInspects in : list){
			in.setProcessNode(getNode(in.getProcessNode()));
			in.setBatch(getNode(in.getBatch()));
			in.setResult(getNode(in.getResult()));
		}
		model.addAttribute("list", list);
		model.addAttribute("page", page);
//		model.addAttribute("area",areaList);
		model.addAttribute("group",shiftGroupList);
		model.addAttribute("vo", ipqcInspects);
		return "qms/bph/reportTable/ipqcInspectsList";
	}
	@RequestMapping("/ipqcInspectsExcelOutput")
	public void ipqcInspectsExcelOutput(IpqcInspects ipqcInspects, Model model,
			HttpServletRequest request, HttpServletResponse response) {
			try{
				commonSelectService.ipqcInspectsExcelOutput(ipqcInspects, request, response);
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
	
	/**
	 * OQC抽检表
	 * @method: oqcCheck() -by fjt
	 * @TODO:  
	 * @param model
	 * @param oqcCheck
	 * @param page
	 */
	@RequestMapping("/oqcCheck")
	private String oqcCheck(Model model,OqcCheck oqcCheck, PageParameter page) throws Exception{
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		String facS = decode(oqcCheck.getFacS());
		String groupS = decode(oqcCheck.getShifS());
		String dateZ = decode(oqcCheck.getTimS());
		Date startTime = null ;
		Date endTime = null;
		if(groupS!=null && !"".equals(groupS) && dateZ !=null && !"".equals(dateZ)){
			Calendar cal = Calendar.getInstance();       	
			cal.setTime(sdf.parse(dateZ));
			/*按照财务月*/
//			cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.DAY_OF_MONTH, 26);
//		    startTime = cal.getTime();
//			cal.add(Calendar.MONTH, 1);
//			cal.set(Calendar.DAY_OF_MONTH, 25); 
			/*按照自然月*/
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		    startTime = cal.getTime();
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
			endTime = cal.getTime();
			bs.put("factoryS", facS);
			bs.put("group", groupS);
			bs.put("startTime", startTime);
			bs.put("endTime", endTime);
			oqcCheck.setBaseFactory(facS);
			oqcCheck.setBaseGroup(groupS);
			oqcCheck.setStartTime(startTime);
			oqcCheck.setEndTime(endTime);
		}else{
			bs.put("factoryS", oqcCheck.getBaseFactory());
			bs.put("group", oqcCheck.getBaseGroup());
			bs.put("startTime", oqcCheck.getStartTime());
			bs.put("endTime", oqcCheck.getEndTime());
		}
	
		
		String factory = null;
		
		if(oqcCheck.getBaseFactory() == null)
		{
			SysMesUser sysMesUser = getCurrentUser();
			factory = sysMesUser.getUda1();
		}
		else
		{
			factory = oqcCheck.getBaseFactory();
		}
		LoadFAPG(model, oqcCheck);
//		List<ShiftGroup> shiftGroupList = shiftGroupService.getShiftGroup(factory);
		List<OqcCheck> list = commonSelectService.getOqcCheckAllByPage(bs);
		List<OqcCheck> blist = new ArrayList<OqcCheck>();
		for(OqcCheck check : list){
			String [] str = new String[3];
			if(check.getUda5() != null){
				str = check.getUda5().split(",");
				check.setGroup1(str);
			}else{
				check.setGroup1(str);
			}
			blist.add(check);
		}
		model.addAttribute("list", blist);
		model.addAttribute("page", page);
//		model.addAttribute("group",shiftGroupList);
		model.addAttribute("vo", oqcCheck);
		return "qms/bph/reportTable/oqcCheckList";
	}
	@RequestMapping("/oqcCheckExcelOutput")
	public void oqcCheckExcelOutput(OqcCheck oqcCheck, Model model,
			HttpServletRequest request, HttpServletResponse response) {
			try{
				commonSelectService.oqcCheckExcelOutput(oqcCheck, request, response);
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
	
	/**
	 * 组装维修日报
	 * @method: assemblyRepaired() -by fjt
	 * @TODO:  
	 * @return String
	 */
	@RequestMapping("/assemblyRepaired")
	public String assemblyRepaired(AssemblyProductBack assemblyProductBack,Model model,PageParameter page) throws Exception{
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String facS = decode(assemblyProductBack.getFacS());
		String groupS = decode(assemblyProductBack.getShifS());
		String dateZ = decode(assemblyProductBack.getTimS());
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		Date startTime = null ;
		Date endTime = null;
		if(groupS!=null && !"".equals(groupS) && dateZ !=null && !"".equals(dateZ)){
			Calendar cal = Calendar.getInstance();       	
			cal.setTime(sdf.parse(dateZ));
			/*按照财务月*/
//			cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.DAY_OF_MONTH, 26);
//		    startTime = cal.getTime();
//			cal.add(Calendar.MONTH, 1);
//			cal.set(Calendar.DAY_OF_MONTH, 25); 
			/*按照自然月*/
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		    startTime = cal.getTime();
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
			endTime = cal.getTime();			
			bs.put("factoryS", facS);
			bs.put("group", groupS);
			bs.put("startTime", DateFormatUtils.format(startTime, "yyyy-MM-dd"));
			bs.put("endTime", DateFormatUtils.format(endTime, "yyyy-MM-dd"));
			assemblyProductBack.setBaseFactory(facS);
			assemblyProductBack.setBaseGroup(groupS);
			assemblyProductBack.setDutyGroup1S(DateFormatUtils.format(startTime, "yyyy-MM-dd"));
			assemblyProductBack.setDutyGroup2S(DateFormatUtils.format(endTime, "yyyy-MM-dd"));
		}else{		
			if(assemblyProductBack.getDutyGroup1S()!=null && assemblyProductBack.getDutyGroup2S()!=null){
				bs.put("startTime", assemblyProductBack.getDutyGroup1S());
				bs.put("endTime", assemblyProductBack.getDutyGroup2S());
			}else{
				bs.put("startTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
				bs.put("endTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
				assemblyProductBack.setDutyGroup1S(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
				assemblyProductBack.setDutyGroup2S(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			}
			
			bs.put("factoryS", assemblyProductBack.getBaseFactory());			
			bs.put("group", assemblyProductBack.getBaseGroup());
		}
		
		
        String factory = null;
		
		if(assemblyProductBack.getBaseFactory() == null)
		{
			SysMesUser sysMesUser = getCurrentUser();
			factory = sysMesUser.getUda1();
		}
		else
		{
			factory = assemblyProductBack.getBaseFactory();
		}
		LoadFAPG(model, assemblyProductBack);
//		List<ShiftGroup> shiftGroupList = shiftGroupService.getShiftGroup(factory);
		List<AssemblyProductBack> list = commonSelectService.getAssembleRepariedByPage(bs);
		model.addAttribute("list", list);
//		model.addAttribute("group",shiftGroupList);
		model.addAttribute("vo", assemblyProductBack);
		model.addAttribute("page", page);
		return "qms/bph/reportTable/assemblyRepairedList";
	}
	@RequestMapping("/assemblyRepairedExcelOutput")
	public void assemblyRepairedExcelOutput(AssemblyProductBack assemblyProductBack, Model model,
			HttpServletRequest request, HttpServletResponse response) {

			try{
				commonSelectService.assemblyRepairedExcelOutput(assemblyProductBack, request, response);
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
	
	private String getOccurGroup(String group){
		String groups ="";
		if(group!=null){
			groups = group.substring(0, group.indexOf("_"));
		}
		return groups;
	}

	private String getNode(String str){
		String obj = null;
		if(str!=null && !"".equals(str)){
			obj = str.substring(0,1);
		}
		return obj;
	}
	
	/**
	 * 喷涂退次表查询
	 * @param model
	 * @param assemblyProductBack
	 * @param page
	 * @return
	 */
	@RequestMapping("/paintingProductReturn")
	public String paintingProductReturn(Model model,AssemblyProductBack assemblyProductBack,PageParameter page) throws Exception
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("startnum", assemblyProductBack.getStartnum());
		bs.put("endnum", assemblyProductBack.getEndnum());
		String groupS = decode(assemblyProductBack.getShifS());
		String dateZ = decode(assemblyProductBack.getTimS());
		String facS = decode(assemblyProductBack.getFacS());
		Date startTime = null;
		Date endTime = null;
		if(groupS!=null && !"".equals(groupS) && dateZ !=null && !"".equals(dateZ)){
			Calendar cal = Calendar.getInstance();       	
			cal.setTime(sdf.parse(dateZ));
			/*按照财务月*/
//			cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.DAY_OF_MONTH, 26);
//		    startTime = cal.getTime();
//			cal.add(Calendar.MONTH, 1);
//			cal.set(Calendar.DAY_OF_MONTH, 25); 
			/*按照自然月*/
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		    startTime = cal.getTime();
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		    endTime = cal.getTime();
			bs.put("group", groupS);
			bs.put("startTime", startTime);
			bs.put("endTime", endTime);
			assemblyProductBack.setBaseFactory(facS);
			assemblyProductBack.setBaseGroup(groupS);
			assemblyProductBack.setStartTime(startTime);
			assemblyProductBack.setEndTime(endTime);
		}else{
			bs.put("factoryS", assemblyProductBack.getBaseFactory());
			bs.put("group", assemblyProductBack.getBaseGroup());
			bs.put("startTime", assemblyProductBack.getStartTime());
			bs.put("endTime", assemblyProductBack.getEndTime());
		}
				
		bs.put("areaS", assemblyProductBack.getBaseArea());		
		bs.put("dutyS", assemblyProductBack.getDutyS());
		bs.put("productTypeS", assemblyProductBack.getProductTypeS());
		bs.put("itemNameS", assemblyProductBack.getItemNameS());
		bs.put("types", "喷涂");
			
		
		String factory = "";
		
		if(assemblyProductBack.getBaseFactory() == null)
		{
			SysMesUser sysMesUser = getCurrentUser();
			factory = sysMesUser.getUda1();
		}
		else
		{
			factory = assemblyProductBack.getBaseFactory();
		}
		LoadFAPG(model, assemblyProductBack);	
		List<AssemblyProductBack> list = commonSelectService.getAssemblyAllByPage(bs);
		for(int i =0; i<list.size(); i++){
			if(dateZ != null && !"".equals(dateZ)){
				if(list.get(i).getDateT().getTime()<startTime.getTime() || list.get(i).getDateT().getTime()>endTime.getTime() ){
					list.remove(i);
					i=i-1;	
				}
			}else if(assemblyProductBack.getStartTime() != null && !"".equals(assemblyProductBack.getStartTime())){
				if(list.get(i).getDateT().getTime()<assemblyProductBack.getStartTime().getTime() || list.get(i).getDateT().getTime()>assemblyProductBack.getEndTime().getTime()){
					list.remove(i);
					i=i-1;	
				}
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", assemblyProductBack);
		return "qms/bph/reportTable/paintingProductReturn";
		
	}
	
	/**
	 * 精加工直通率
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	@RequestMapping("/finishingDaliyList")
	public String finishingDaliyList(Model model,StampingDailyReport stampingDailyReport,PageParameter page) throws QmsException, UnsupportedEncodingException, ParseException
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		String facS = decode(stampingDailyReport.getFacS());
		String groupS = decode(stampingDailyReport.getShifS());
		String dateZ = decode(stampingDailyReport.getTimS());
		Date startTime = null ;
		Date endTime = null ;
		if(groupS!=null && !"".equals(groupS) && dateZ !=null && !"".equals(dateZ)){
			Calendar cal = Calendar.getInstance();       	
			cal.setTime(sdf.parse(dateZ));
			/*按照财务月*/
//			cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.DAY_OF_MONTH, 26);
//		    startTime = cal.getTime();
//			cal.add(Calendar.MONTH, 1);
//			cal.set(Calendar.DAY_OF_MONTH, 25); 
			/*按照自然月*/
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		    startTime = cal.getTime();
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
			endTime = cal.getTime();
			bs.put("groupS", groupS);
			bs.put("startTime", startTime);
			bs.put("endTime", endTime);
			stampingDailyReport.setBaseFactory(facS);
			stampingDailyReport.setBaseGroup(groupS);
			stampingDailyReport.setStartTime(startTime);
			stampingDailyReport.setEndTime(endTime);
		}else{
			bs.put("factoryS",stampingDailyReport.getBaseFactory());
			bs.put("groupS", stampingDailyReport.getBaseGroup());
			bs.put("startTime", stampingDailyReport.getStartTime());
			bs.put("endTime", stampingDailyReport.getEndTime());
		}
		bs.put("areaS", stampingDailyReport.getBaseArea());
		bs.put("typeS", stampingDailyReport.getTypeS());
		bs.put("productNameS", stampingDailyReport.getProductNameS());
		bs.put("defectS", stampingDailyReport.getDefectS());
		bs.put("types", "精加工");

		
		String factory = "";
		if(stampingDailyReport.getBaseFactory() == null)
		{
			SysMesUser sysMesUser = getCurrentUser();
			factory = sysMesUser.getUda1();
		}
		else
		{
			factory = stampingDailyReport.getBaseFactory();
		}
		LoadFAPG(model, stampingDailyReport);
		List<StampingDailyReport> list = commonSelectService.getStampingDailyAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", stampingDailyReport);
		return "qms/bph/reportTable/finishingDaliyList";
		
	}
	//冲压一次合格率排列图
	private EChartObj createRepairDatacy(StampingDailyReport stampingDailyReport){
		//获取并处理数据
		List<EntityClass> flist=commonSelectService.stampingdailyreportbad(stampingDailyReport);
		List<String> xlist=new ArrayList<String>();
		List<Double> yvalue1=new ArrayList<Double>();
		List<Double> yvalue2=new ArrayList<Double>();
		double dou1=0.00;
		double dou2=0.00;
		int badcount=0;//总的不良数
		double num=0;//不良叠加
		for (int i = 0; i < flist.size(); i++) {
			badcount=badcount+flist.get(i).getBadnumber();
		}
		for (int i = 0; i < flist.size(); i++) {
			if(i<12){
				xlist.add(flist.get(i).getBadname());
				double d=flist.get(i).getBadnumber();
				yvalue1.add(d);
				num=num+d;
				double jg=num/badcount*100;
				BigDecimal bg = new BigDecimal(jg);
				yvalue2.add(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}else {
				num=num+flist.get(i).getBadnumber();
				dou2=dou2+flist.get(i).getCountnumber();
			}
			if(i>12 && i==flist.size()-1){
				xlist.add("其他");
				yvalue1.add(dou1);
				double jg=num/badcount*100;
				BigDecimal bg = new BigDecimal(jg);
				yvalue2.add(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}	
		EChartObj chart = new EChartObj();
		chart.setChartType(EChartsType.CHART_LINES);                      //图形类型
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		chart.setTitle(stampingDailyReport.getBaseGroup()+fmt.format(stampingDailyReport.getStartTime())+
				"至"+fmt.format(stampingDailyReport.getEndTime())+"排列图");   //设置标题
		chart.setToolboxShow(true);                                       //开启工具配置项
		chart.setToolboxFeatureSaveAsImageShow(true);                     //开启图片
		chart.setToolboxFeatureMagicTypeShow(true);                       //开启动态转换
		chart.setToolboxFeatureMagicTypeType(new String[]{"line","bar"}); //配置动态类型切换
		chart.setToolboxFeatureDataViewShow(true);                        //配置toolBox数据不可编辑
		chart.setyAxisAxisLabelFormatter("{value}℃");                    //配置坐标单位  
		chart.setSeriesType(new String[]{"bar","line"} );                //设置系列类型
		chart.setyLeftUnit("%");                                        //左单位
		chart.setBarWidth("30");										//柱子宽度
		String[] seriesNames ={"不良数","不良率"};

		List<List<Double>> ylist = new ArrayList<List<Double>>();
		try{
			ylist.add(yvalue1);
			ylist.add(yvalue2);
			chart.setSeriesNames(seriesNames);
			chart.setxValue(xlist);
			chart.setyValues(ylist);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		return chart;
	}
	//组装生产/喷涂生产排列图
	private EChartObj createRepairData(AssemblyProductBack assemblyProductBack){
		//获取并处理数据
		List<EntityClass> flist=commonSelectService.assemblyRepairedbad(assemblyProductBack);
		List<String> xlist=new ArrayList<String>();
		List<Double> yvalue1=new ArrayList<Double>();
		List<Double> yvalue2=new ArrayList<Double>();
		double dou1=0.00;
		double dou2=0.00;
		int badcount=0;//总的不良数
		double num=0;//不良叠加
		for (int i = 0; i < flist.size(); i++) {
			badcount=badcount+flist.get(i).getBadnumber();
		}
		for (int i = 0; i < flist.size(); i++) {
			if(i<11){
				xlist.add(flist.get(i).getBadname());
				double d=flist.get(i).getBadnumber();
				yvalue1.add(d);
				num=num+d;
				double jg=num/badcount*100;
				BigDecimal bg = new BigDecimal(jg);
				yvalue2.add(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

			}else {
				num=num+flist.get(i).getBadnumber();
				dou2=dou2+flist.get(i).getCountnumber();
				dou1=dou1+flist.get(i).getBadnumber();
			}
			if(i>11 && i==flist.size()-1){
				xlist.add("其他");
				yvalue1.add(dou1);
				double jg=num/badcount*100;
				BigDecimal bg = new BigDecimal(jg);
				yvalue2.add(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}	
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		EChartObj chart = new EChartObj();
		chart.setChartType(EChartsType.CHART_LINES);                      //图形类型
		chart.setTitle(assemblyProductBack.getBaseGroup()+fmt.format(assemblyProductBack.getStartTime())+
				"至"+fmt.format(assemblyProductBack.getEndTime())+"排列图");                                //设置标题
		chart.setToolboxShow(true);                                       //开启工具配置项
		chart.setToolboxFeatureSaveAsImageShow(true);                     //开启图片
		chart.setToolboxFeatureMagicTypeShow(true);                       //开启动态转换
		chart.setToolboxFeatureMagicTypeType(new String[]{"line","bar"}); //配置动态类型切换
		chart.setToolboxFeatureDataViewShow(true);                        //配置toolBox数据不可编辑
		chart.setyAxisAxisLabelFormatter("{value}℃");                    //配置坐标单位  
		chart.setSeriesType(new String[]{"bar","line"} );                //设置系列类型
		chart.setyLeftUnit("%");                                        //左单位
		chart.setBarWidth("30");										//柱子宽度
		String[] seriesNames ={"不良数","不良率"};

		List<List<Double>> ylist = new ArrayList<List<Double>>();
		try{
			ylist.add(yvalue1);
			ylist.add(yvalue2);
			chart.setSeriesNames(seriesNames);
			chart.setxValue(xlist);
			chart.setyValues(ylist);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		return chart;
	}
	//组装退次率/涂装生产推辞率数据处理，时间序列图
	private EChartObj createsequenceData(AssemblyProductBack assemblyProductBack){
		//获取并处理数据
		List<EntityClass> flist=commonSelectService.assemblyRepairedarrange(assemblyProductBack);
		List<EntityClass> flist2=commonSelectService.assemblyRepairedarrangetwo(assemblyProductBack);//获取着真实分数
		EChartObj chart = new EChartObj();
		chart.setChartType(EChartsType.CHART_LINES);                      //图形类型
		chart.setTitle("时间序列图");                                //设置标题
		chart.setToolboxShow(true);                                       //开启工具配置项
		chart.setToolboxFeatureSaveAsImageShow(true);                     //开启图片
		chart.setToolboxFeatureMagicTypeShow(true);                       //开启动态转换
		//chart.setToolboxFeatureMagicTypeType(new String[]{"line","bar"}); //配置动态类型切换
		chart.setToolboxFeatureDataViewShow(true);                        //配置toolBox数据不可编辑
		chart.setyAxisAxisLabelFormatter("{value}");                    //配置坐标单位  
		chart.setSeriesType(new String[]{"line","line","line"} );       //设置系列类型
		chart.setyLeftUnit("");                                        //左单位
		String[] seriesNames ={"指标实际值","基准值","目标值"};
		List<String> xValue = datefand2();
		Collections.reverse(xValue); 
		List<Double> baiList = new ArrayList<Double>();//分数
		List<Double> yeList = new ArrayList<Double>();//基准值
		List<Double> yeList2 = new ArrayList<Double>();//目标值
		for (int i = 0; i < xValue.size(); i++) {
			String m=xValue.get(i);
			String m2="";
			for (int j = 0; j <flist.size(); j++) {
				m2=flist.get(j).getMonth();
				if(j==flist.size()-1 && !m.equals(m2)){
					yeList.add(0.00);
					yeList2.add(0.00);
					break;
				}
				if(m.equals(m2)){
					yeList.add(Double.valueOf(flist.get(j).getBasevalue()));
					yeList2.add(Double.valueOf(flist.get(j).getTargetvalue()));
					break;
				}else {}
			}
			for (int k = 0; k <flist2.size(); k++) {
				m2=flist2.get(k).getMonth().substring(0, 7);
				if(k==flist2.size()-1 && !m.equals(m2)){
					baiList.add(0.00);
					break;
				}
				if(m.equals(m2)){
					baiList.add(Double.valueOf(flist2.get(k).getIndexscore()));
					break;
				}else {}
			}
		}
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		try{
			ylist.add(baiList);
			ylist.add(yeList);
			ylist.add(yeList2);
			chart.setSeriesNames(seriesNames);
			chart.setxValue(xValue);
			chart.setyValues(ylist);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		return chart;
	}
	//冲压一次合格率数据处理，时间序列图
	private EChartObj createsequenceDatacy(StampingDailyReport stampingDailyReport){
		//获取并处理数据
		List<EntityClass> flist=commonSelectService.stampingdailyreportarrange(stampingDailyReport);
		List<EntityClass> flist2=commonSelectService.stampingdailyreportarrangetwo(stampingDailyReport);//获取着真实分数
		EChartObj chart = new EChartObj();
		chart.setChartType(EChartsType.CHART_LINES);                      //图形类型
		chart.setTitle("时间序列图");                                //设置标题
		chart.setToolboxShow(true);                                       //开启工具配置项
		chart.setToolboxFeatureSaveAsImageShow(true);                     //开启图片
		chart.setToolboxFeatureMagicTypeShow(true);                       //开启动态转换
		//chart.setToolboxFeatureMagicTypeType(new String[]{"line","bar"}); //配置动态类型切换
		chart.setToolboxFeatureDataViewShow(true);                        //配置toolBox数据不可编辑
		chart.setyAxisAxisLabelFormatter("{value}");                    //配置坐标单位  
		chart.setSeriesType(new String[]{"line","line","line"} );       //设置系列类型
		chart.setyLeftUnit("");                                        //左单位
		String[] seriesNames ={"指标得数","基准值","目标值"};
		List<String> xValue = datefand2();
		Collections.reverse(xValue); 
		List<Double> baiList = new ArrayList<Double>();//分数
		List<Double> yeList = new ArrayList<Double>();//基准值
		List<Double> yeList2 = new ArrayList<Double>();//目标值
		for (int i = 0; i < xValue.size(); i++) {
			String m=xValue.get(i);
			String m2="";
			for (int j = 0; j <flist.size(); j++) {
				m2=flist.get(j).getMonth();
				if(j==flist.size()-1 && !m.equals(m2)){
					yeList.add(0.00);
					yeList2.add(0.00);
					break;
				}
				if(m.equals(m2)){
					yeList.add(Double.valueOf(flist.get(j).getBasevalue()));
					yeList2.add(Double.valueOf(flist.get(j).getTargetvalue()));
					break;
				}else {}
			}
			for (int k = 0; k <flist2.size(); k++) {
				m2=flist2.get(k).getMonth().substring(0, 7);
				if(k==flist2.size()-1 && !m.equals(m2)){
					baiList.add(0.00);
					break;
				}
				if(m.equals(m2)){
					baiList.add(Double.valueOf(flist2.get(k).getIndexscore()));
					break;
				}else {}
			}
		}
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		try{
			ylist.add(baiList);
			ylist.add(yeList);
			ylist.add(yeList2);
			chart.setSeriesNames(seriesNames);
			chart.setxValue(xValue);
			chart.setyValues(ylist);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		return chart;
	}
	@RequestMapping("/findModel")
	public void getproductType(String productType,HttpServletResponse respon){
		// 型号
		int result = 0;
		String msg = null;
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> partMap = new LinkedHashMap<String, String>();
		try {
			CommonVo vo=new CommonVo();
			vo.setProductType(productType);
			List<CommonVo> partlist = commonService.getPartTypeFromMes(vo);
			if (StringUtils.isNotEmpty(vo.getProductType())) {
				for (int i = 0; i < partlist.size(); i++) {
					partMap.put(partlist.get(i).getPartType(), partlist.get(i).getPartType());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("info", partMap);
		map.put("result", result);
		map.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
		
	}
	//生产日期list，默认3个月
	private List<String> datesetlist(Date d1,Date d2){
		List<String> dlist=new ArrayList<String>();
		if(d1!=null && d2!=null){
			dlist=datefand(d1,d2);
		}else{
			Date t=new Date();
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -3);
			Date t2=c.getTime();
			dlist=datefand(t2,t);
		}
		return dlist;
	}
	//生产日期，格式：2016-08-08
	private List<String> datefand(Date beginDate, Date endDate){
		DateFormat fmt = new SimpleDateFormat("YYYY-MM-dd");
		List<String> lDate = new ArrayList<String>();
		lDate.add(fmt.format(beginDate));//把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		//使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			//根据日历的规则，为给定的日历字段添加或减去指定的时间量
			if(lDate.size()>=120){
				bContinue = false;
				break;
			}
		    cal.add(Calendar.DAY_OF_MONTH, 1);
		    // 测试此日期是否在指定日期之后
		    if (endDate.after(cal.getTime())) {
	            lDate.add(fmt.format(cal.getTime()));
	        } else {
	            break;
	        }
		}
		lDate.add(fmt.format(endDate));//把结束时间加入集合
		return lDate;
	}
	//生成月份，格式：2016-08
	private List<String> datefand2(){
		List<String> list=new ArrayList<String>();
        Calendar curr = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date d=curr.getTime();
        list.add(sdf.format(d));
        for (int i = 0; i < 11; i++) {
        	curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)-1);
            Date date=curr.getTime();
            list.add(sdf.format(date));
		}
        return list;
	}
}
