package com.peg.qms.controller.part;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.dao.part.TestInstanceMapper;
import com.peg.echarts.EChartObj;
import com.peg.model.bph.MesDataSum;
import com.peg.model.part.OnlineModel;
import com.peg.model.part.OnlineVO;
import com.peg.model.part.TestInstance;
import com.peg.model.system.SumOperationLog;
import com.peg.qms.controller.BaseController;
import com.peg.service.part.OnlineServiceITwo;
import com.peg.service.part.TestInstanceServiceI;
import com.peg.service.system.SumOperationLogServiceI;
import com.peg.web.util.ExcelUtilities;
import com.peg.web.util.TmStringUtils;

@SuppressWarnings("static-access")
@Controller
@RequestMapping("base/online")
public class OnlineController extends BaseController {
	
	@Resource
	private OnlineServiceITwo onlineServiceTwo;
	
	@Autowired
	private TestInstanceServiceI testInstanceService;
	
	@Autowired
	private SumOperationLogServiceI sumOpService;
	
	@Autowired
	private TestInstanceMapper testInstanceMapper;
	
	/**
	 * 在线物料明细
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/onlineHomeChar")
	public String findHomeChar(Model model,OnlineModel o) {
		testInstanceService.loadData(model);
		o=setOnlineModel(o);
		o.setColumnNum(10);
		model.addAttribute("vo", o);
		System.out.println(o.getStartdate());
		return "qms/part/qualityTwo/onlineHomeChar";
	}
	
	@RequestMapping("/findChartonclick")
	public void findChartonclick(Model model,OnlineModel o,HttpServletResponse respon){
		Map<String, Object> map = new HashMap<String, Object>();
		o=setOnlineModel(o);
		String msg=null;
		int result=0;
		EChartObj chart = new EChartObj();
		try {
			o=setParam(o);
			o=setPara(o);
			chart=onlineServiceTwo.getEChartObj(o);
			result=Integer.parseInt(o.getCharthid());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result=-1;
			msg=e.getMessage();
		}
		map.put("info", chart);
		map.put("result", result);
		map.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	//图形标示处理
	private OnlineModel setParam(OnlineModel o){
		String type=o.getAnalysisType();//统计类型，0:不良批次;1:不良数/率
		String bid=o.getButtonId();
		if(type.equals("0")){
			o.setCharthid(bid);
		}else {
			String replaceType=o.getReplaceType();//0:更换;1:退次
			if(replaceType.equals("0")){
				if(bid.equals("1")){
					o.setCharthid("6");
				}else if(bid.equals("2")){
					o.setCharthid("7");
				}else if(bid.equals("3")){
					o.setCharthid("8");
				}else if(bid.equals("4")){
					o.setCharthid("9");
				}else if(bid.equals("5")){
					o.setCharthid("10");
				}
			}else {
				if(bid.equals("1")){
					o.setCharthid("11");
				}else if(bid.equals("2")){
					o.setCharthid("12");
				}else if(bid.equals("3")){
					o.setCharthid("13");
				}else if(bid.equals("4")){
					o.setCharthid("14");
				}else if(bid.equals("5")){
					o.setCharthid("15");
				}
			}
		}
		return o;
	}
	/**
	 * 在线批次明细
	 * @param model
	 * @param vo
	 */
	@RequestMapping("/onlinebatch")
	public String findpoorbatch(Model model,OnlineModel o,PageParameter page,String jsonStr){
		try {
			if(jsonStr!=null){
				o=setobj(o,jsonStr);
			}
			o=setPara(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		o=setOnlineModel(o);
		List<OnlineModel> list=onlineServiceTwo.getlisttab(o,page);
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//日期格式
		Date date=new Date();
		testInstanceService.loadData(model);
		model.addAttribute("id_end", sdf.format(date));
		model.addAttribute("vo", o);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute("parametero", o);
		subject.getSession().setAttribute("parameterpage", page);
		return "qms/part/qualityTwo/onlinebatch";
	}
	/**
	 * 在线不良明细(mes)更换
	 * @param model
	 * @param vo
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/onlinenum")
	public String findpoornum(Model model,OnlineModel o,PageParameter page,String jsonStr) throws Exception{
		try {
			if(jsonStr!=null){
				o=setobj(o,jsonStr);
			}
			o=setPara(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		o=setOnlineModel(o);
		List<OnlineModel> list=onlineServiceTwo.getlisttabMes(o,page);
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//日期格式
		Date date=new Date();
		testInstanceService.loadData(model);
		model.addAttribute("id_end", sdf.format(date));
		model.addAttribute("vo", o);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute("parametero", o);
		subject.getSession().setAttribute("parameterpage", page);
		return "qms/part/qualityTwo/onlinenum";
	}
	/**
	 * 在线不良明细(mes)退次
	 * @param model
	 * @param o
	 * @param page
	 * @param jsonStr
	 * @return
	 */
	@RequestMapping("/onlinenumReturn")
	public String onlinenumReturn(Model model,OnlineModel o,PageParameter page,String jsonStr){
		try {
			if(jsonStr!=null){
				o=setobj(o,jsonStr);
			}
			o=setPara(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		o=setOnlineModel(o);
		List<OnlineModel> list=onlineServiceTwo.getlisttabMesR(o,page);
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//日期格式
		Date date=new Date();
		testInstanceService.loadData(model);
		model.addAttribute("id_end", sdf.format(date));
		model.addAttribute("vo", o);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute("parametero", o);
		subject.getSession().setAttribute("parameterpage", page);
		return "qms/part/qualityTwo/onlineMESnum";
	}
	/**
	 * 在线不良明细ERP(erp组装生产退次)
	 * @param model
	 * @param vo
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/onlineERPnum")
	public String findpoorERPnum(Model model,OnlineModel o,PageParameter page,String jsonStr) throws Exception{
		try {
			if(jsonStr!=null){
				o=setobj(o,jsonStr);
			}
			o=setPara(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		o=setOnlineModel(o);
		List<OnlineModel> list=onlineServiceTwo.getlisttabErp(o,page);
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//日期格式
		Date date=new Date();
		testInstanceService.loadData(model);
		model.addAttribute("id_end", sdf.format(date));
		model.addAttribute("vo", o);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute("parametero", o);
		subject.getSession().setAttribute("parameterpage", page);
		return "qms/part/qualityTwo/onlineERPnum";
	}
	/**
	 * 来料入库
	 * @param model
	 * @param vo
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/onlineStorage")
	public String findpoorStorage(Model model,OnlineModel o,PageParameter page){
		try {
			setPara(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		o=setOnlineModel(o);
		List<OnlineModel> list=onlineServiceTwo.getlisttabMesStorage(o,page);
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//日期格式
		Date date=new Date();
		testInstanceService.loadData(model);
		model.addAttribute("id_end", sdf.format(date));
		model.addAttribute("vo", o);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute("parametero", o);
		subject.getSession().setAttribute("parameterpage", page);
		return "qms/part/qualityTwo/onlineStorage";
	}
	/**
	 * 请求参数返回json
	 * @param model
	 * @param vo
	 * @param respon
	 */
	@RequestMapping("/onlinesetvo")
	public void setvovalue(Model model,OnlineModel o,HttpServletResponse respon){
		Map<String, Object> map = new HashMap<String, Object>();
		o.setColumnNum(0);
		//图形条件转换
		//供应商
//		o.setSupplierData(o.getStrone().equals("")?o.getSupplierData():o.getStrone());
//		o.setSupplier(o.getStrtwo().equals("")?o.getSupplier():o.getStrtwo());
//		o.setSupplierCode(getSupStringBySemicolon(o.getStrone().equals("")?o.getSupplierData():o.getStrone()));
		//零部件
//		o.setPartData(o.getSpareparts2().equals("")?o.getPartData():o.getSpareparts2());
//		o.setPartName(o.getSpareparts().equals("")?o.getPartName():o.getSpareparts());
//		o.setPartNumber(getSupStringBySemicolon(o.getSpareparts2().equals("")?o.getPartData():o.getSpareparts2()));
		if("".equals(o.getStrone()) && "".equals(o.getTrendTime()) && "".equals(o.getBadphenomenon())){
			o.setSupplier("");
			o.setSupplierData("");
			o.setPartNumber(getSupStringBySemicolon("".equals(o.getSpareparts2())?o.getPartData():o.getSpareparts2()));
			o.setPartData("");
			o.setPartName("");
		}else if("".equals(o.getSpareparts2()) && "".equals(o.getTrendTime()) && "".equals(o.getBadphenomenon())){
			o.setPartData("");
			o.setPartName("");
			o.setSupplierCode(getSupStringBySemicolon("".equals(o.getStrone())?o.getSupplierData():o.getStrone()));
			o.setSupplier("");
			o.setSupplierData("");
		}else{
			o.setPartData("");
			o.setPartName("");
			o.setSupplier("");
			o.setSupplierData("");
		}
		//时间
		o=setParam(o);
		if(o.getCharthid().equals("9") || o.getCharthid().equals("10") || o.getCharthid().equals("14") || o.getCharthid().equals("15")){
			if(o.getDateType().equals("年") || o.getDateType().equals("月")){
				String dateT=o.getTrendTime().equals("")?o.getDateT():o.getTrendTime();
				o.setDateT(dateT);//o.setStartdate(dateT);
				o.setDateT_T(dateT);//o.setEnddate(dateT);
			}
			if(o.getDateType().equals("周")){
				try {
					DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式
			        Calendar curr = Calendar.getInstance();//获取一个日历对象
					String dateT=o.getTrendTime().equals("")?o.getDateT():o.getTrendTime();
					Date date = sdf.parse(dateT);
					curr.setTime(date);
		        	curr.add(curr.DATE,-7);
		        	o.setDateT_T(dateT);
					o.setDateT(sdf.format(curr.getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
			if(o.getDateType().equals("天")){
				try {
					DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式
			        Calendar curr = Calendar.getInstance();//获取一个日历对象
					String dateT=o.getTrendTime().equals("")?o.getDateT():o.getTrendTime();
					Date date;
					date = sdf.parse(dateT);
					curr.setTime(date);
		        	curr.add(curr.DATE,1);
		        	o.setDateT_T(sdf.format(curr.getTime()));
					o.setDateT(dateT);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		map.put("obj",o);
		JSONObject resultObject = JSONObject.fromObject(map);
		printResponContent(respon, resultObject.toString());
	}
	/**
	 * 汇总页面跳转
	 */
	@RequestMapping("/onlineReplace")
	public String onlineReplace(Model model){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String queryMonth = DateFormatUtils.format(cal.getTime(), "yyyy-MM");
		String queryDay = DateFormatUtils.format(cal.getTime(), "yyyy-MM-dd");			
		MesDataSum mesDataSum = new MesDataSum();
		mesDataSum.setQueryMonth(queryMonth);
		mesDataSum.setQueryDay(queryDay);
		model.addAttribute("list", mesDataSum);
		return "qms/part/qualityTwo/replaceDataSum";
	}
	/**
	 * 月汇总
	 * @param respon
	 */
	@RequestMapping("/onlineReplaceSumDataMonth")
	public void onlineReplaceSumDataMonth(HttpServletResponse respon,String queryMonth){
		Map<String, Object> map = new HashMap<String, Object>();
		String msg=null;
		String name = "在线-MES更换数据月份汇总";
		int result=0;
		String showResult=new String();
		Date startTime = Calendar.getInstance().getTime();
		try {
			onlineServiceTwo.SumDataMonth(queryMonth);
			showResult="汇总成功！";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result=-1;
			msg=e.getMessage();
		}
		Date endTime = Calendar.getInstance().getTime();
		String statisticsTime = queryMonth;
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime,
				startTime, endTime, result == 0 ? "成功" : "失败", "manual", "数据采集", msg);
		sumOpService.insert(opLog);
		map.put("showResult", showResult);
		map.put("result", result);
		map.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	/**
	 * 日汇总
	 * @param respon
	 */
	@RequestMapping("/onlineReplaceSumDataDay")
	public void onlineReplaceSumDataDay(HttpServletResponse respon,String startTime,String endTime){
		Map<String, Object> map = new HashMap<String, Object>();
		String msg=null;
		String name = "在线-MES更换数据日汇总";
		int result=0;
		String showResult=new String();
		Date upDatestartTime = Calendar.getInstance().getTime();
		try {
			onlineServiceTwo.SumDataDay(startTime, endTime);
			showResult="汇总成功！";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result=-1;
			msg=e.getMessage();
		}
		Date upDateendTime = Calendar.getInstance().getTime();
		String statisticsTime = startTime + "至" + endTime;
		SumOperationLog opLog = new SumOperationLog(name, statisticsTime,
				upDatestartTime, upDateendTime, result == 0 ? "成功" : "失败", "manual", "数据采集", msg);
		sumOpService.insert(opLog);
		map.put("showResult", showResult);
		map.put("result", result);
		map.put("msg", msg);
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	//将json转成实体
	private OnlineModel setobj(OnlineModel o,String jsonStr) throws Exception{
		String str=decode(jsonStr);
		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(str);  
		o = (OnlineModel) net.sf.json.JSONObject.toBean(jsonObject, OnlineModel.class);
		return o;
	}
	
	private OnlineModel setPara(OnlineModel vo) throws Exception{
		String dimension=vo.getDateType()==null?"月":vo.getDateType();//时间维度
		DateFormat sdf = new SimpleDateFormat("yyyy-MM");//日期格式
        Calendar curr = Calendar.getInstance();//获取一个日历对象
        String time=vo.getDateT();//开始日期
        //日期处理
        if(time==null || time.equals("")){
        	Date date = new Date();
        	time=sdf.format(date);
        }
		if(dimension.equals("年")){
			sdf = new SimpleDateFormat("yyyy");
			if(vo.getDateT_T()==null || vo.getDateT_T().equals("")){
				Date date = new Date();
				curr.setTime(date);
				vo.setDateT_T(sdf.format(curr.getTime()));
			}
			Date date = sdf.parse(time);
			curr.setTime(date);
			//curr.add(Calendar.YEAR, -1);
        	vo.setStartdate(sdf.format(curr.getTime())+"-01"+"-01");
        	date = sdf.parse(vo.getDateT_T());
			curr.setTime(date);
        	vo.setEnddate(sdf.format(curr.getTime())+"-12"+"-31");
        	vo.setDate_type("yyyy");
        }
        if(dimension.equals("月")){  	
        	sdf = new SimpleDateFormat("yyyy-MM");
        	if(vo.getDateT_T()==null || vo.getDateT_T().equals("")){
				Date date = new Date();
				curr.setTime(date);
				vo.setDateT_T(sdf.format(curr.getTime()));
			}
        	Date date = sdf.parse(time);
        	curr.setTime(date);
//        	curr.add(Calendar.MONTH, -1);
        	vo.setStartdate(sdf.format(curr.getTime())+"-01");
        	date = sdf.parse(vo.getDateT_T());
			curr.setTime(date);
        	vo.setEnddate(getLastDayOfMonth(sdf.format(curr.getTime())));
        	vo.setDate_type("yyyy-mm");
        	
        }
        if(dimension.equals("周")){
        	sdf = new SimpleDateFormat("yyyy-mm-dd");
        	if(vo.getDateT_T()==null || vo.getDateT_T().equals("")){
				Date date = new Date();
				curr.setTime(date);
				vo.setDateT_T(sdf.format(curr.getTime()));
			}
        	Date date = sdf.parse(time);
        	curr.setTime(date);
        	curr.add(curr.DATE,1);
        	vo.setStartdate(sdf.format(curr.getTime()));
        	date = sdf.parse(vo.getDateT_T());
        	curr.setTime(date);
        	curr.add(curr.DATE,1);
        	vo.setEnddate(sdf.format(curr.getTime()));
        	vo.setDate_type("yyyy-IW");
        }
        if(dimension.equals("天")){
        	sdf = new SimpleDateFormat("yyyy-mm-dd");
        	if(vo.getDateT_T()==null || vo.getDateT_T().equals("")){
				Date date = new Date();
				curr.setTime(date);
				vo.setDateT_T(sdf.format(curr.getTime()));
			}
        	Date date = sdf.parse(time);
        	curr.setTime(date);
        	vo.setStartdate(sdf.format(curr.getTime()));
        	date = sdf.parse(vo.getDateT_T());
        	curr.setTime(date);
        	vo.setEnddate(sdf.format(curr.getTime()));
        	vo.setDate_type("yyyy-mm-dd");
        }
        //构造data
        if(TmStringUtils.isNotEmpty(vo.getSupplierCode()) ){
			BaseSearch bs = new BaseSearch();
			bs.setPage(new PageParameter());
			bs.put("supplierList", TmStringUtils.getSupStringByComma(vo.getSupplierCode()));
			List<TestInstance> supList = testInstanceMapper.getSupplierByPage(bs);
			String supData = "";
			String supStr = "";
			for(TestInstance test: supList){
				supData += test.getId()+","+test.getSupplierCode()+","+test.getSupplier()+";";
				supStr += test.getSupplier() + ",";
			}
			vo.setSupplierData(supData);
			vo.setSupplier(supStr);
		}
		if(TmStringUtils.isNotEmpty(vo.getPartNumber()) ){
			BaseSearch bs = new BaseSearch();
			bs.setPage(new PageParameter());
			bs.put("partList", TmStringUtils.getSupStringByComma(vo.getPartNumber()));
			if(vo.getDistinction() != null && vo.getDistinction() ==1){
				bs.put("distinction", "是".equals(vo.getPartVersion()) ? null : 1);
			}
			List<TestInstance> partList = testInstanceMapper.getPartAllByPage(bs);
			String partData = "";
			String partStr = "";
			String partNumberStr = "";
			for(TestInstance test: partList){
				partData += test.getId()+","+test.getPartNumber()+","+test.getPartName()+";";
				partStr += test.getPartName() + ",";
				partNumberStr += test.getPartNumber() +",";
			}
			vo.setPartData(partData);
			vo.setPartName(partStr);
			vo.setPartNumber(partNumberStr);
		}
        return vo;
	}
	//获取指定月的最后一天
	public static String getLastDayOfMonth(String yearmonth)
    {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		Date date = null;
		try {
			date = sdf.parse(yearmonth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        //设置时间
        cal.setTime(date);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }
	//在线不良批次
	@RequestMapping("/downloadExeclBatch")
	public String downloadBatch(Model model,HttpServletRequest request,HttpServletResponse response) {
		String retView = "/error/err";
		try {
			Subject subject = SecurityUtils.getSubject();
			PageParameter page = (PageParameter)subject.getSession().getAttribute("parameterpage");
			OnlineModel o = (OnlineModel)subject.getSession().getAttribute("parametero");
			List<OnlineModel> list=onlineServiceTwo.getlisttab(o,page);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式
			String[] CN = { "发生日期","产品线","物料编号","物料名称","成熟度",
	                "供应商编号","供应商名称","不良现象","不良数","总数"};
			List<String[]> excelList = new ArrayList<String[]>();
	        int index = 0;
	        int cols = CN.length;
	        for (int i = 0; i < list.size(); i++) {
	        	OnlineModel mo = list.get(i);
	            String[] tmpStr = new String[cols];
	            tmpStr[index++] = sdf.format(mo.getRecord_date_t());
	            tmpStr[index++] = mo.getLine_s();
	            tmpStr[index++] = mo.getPart_number();
	            tmpStr[index++] = mo.getDescription();
	            tmpStr[index++] = mo.getProduct_maturity_s();
	            
	            tmpStr[index++] = mo.getAccount_name();
	            tmpStr[index++] = mo.getDescription_2();
	            tmpStr[index++] = mo.getDefect_s();
	            tmpStr[index++] = mo.getDefect_qty_i()+"";
	            tmpStr[index++] = mo.getTotal_qty_i()+"";
	            index = 0;
	            excelList.add(tmpStr);
	        }
	
	        // 获取项目根目录
	        String rootPath = request.getSession().getServletContext()
	                .getRealPath("/");
	        String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
	        String filePath = rootPath + "/" + fname;
	        ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
	        String contentType = "application/msexcel";
	        ExcelUtilities.downloadExcel(request, response, filePath,
	                contentType, "在线批次" + fname);
	        return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retView;		 
	}
	//mes在线更换
	@RequestMapping("/downloadExeclNum")
	public String downloadExeclNum(Model model,HttpServletRequest request,HttpServletResponse response) {
		String retView = "/error/err";
		try {
			Subject subject = SecurityUtils.getSubject();
			PageParameter page = (PageParameter)subject.getSession().getAttribute("parameterpage");
			page.setNumPerPage(5000);
			OnlineModel o = (OnlineModel)subject.getSession().getAttribute("parametero");
			List<OnlineModel> list=onlineServiceTwo.getlisttabMes(o,page);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式
			String[] CN = { "发生日期","产品线","主机条码","物料编号","物料名称",
	                "供应商编号","供应商名称","不良现象","不良数"};
			List<String[]> excelList = new ArrayList<String[]>();
	        int index = 0;
	        int cols = CN.length;
	        for (int i = 0; i < list.size(); i++) {
	        	OnlineModel mo = list.get(i);
	            String[] tmpStr = new String[cols];
	            tmpStr[index++] = sdf.format(mo.getDate_TT());
	            tmpStr[index++] = mo.getMoldtype();
	            tmpStr[index++] = mo.getSerialNumber();
	            tmpStr[index++] = mo.getPart_number();
	            tmpStr[index++] = mo.getDescription();
	            tmpStr[index++] = mo.getSupcode();
	            tmpStr[index++] = mo.getSupname();
	            tmpStr[index++] = mo.getDefect_s();
	            tmpStr[index++] = mo.getBadnum()+"";
	            index = 0;
	            excelList.add(tmpStr);
	        }
	
	        // 获取项目根目录
	        String rootPath = request.getSession().getServletContext()
	                .getRealPath("/");
	        String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
	        String filePath = rootPath + "/" + fname;
	        ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
	        String contentType = "application/msexcel";
	        ExcelUtilities.downloadExcel(request, response, filePath,
	                contentType, "在线更换" + fname);
	        return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retView;
	}
	//mes在线退次
	@RequestMapping("/downloadExeclMesnum")
	public String downloadExeclMesnum(Model model,HttpServletRequest request,HttpServletResponse response) {
		String retView = "/error/err";
		try {
			Subject subject = SecurityUtils.getSubject();
			PageParameter page = (PageParameter)subject.getSession().getAttribute("parameterpage");
			OnlineModel o = (OnlineModel)subject.getSession().getAttribute("parametero");
			List<OnlineModel> list=onlineServiceTwo.getlisttabMesR(o,page);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式
			String[] CN = { "发生日期","产品线","物料编号","物料名称",
	                "供应商编号","供应商名称","不良现象","不良数"};
			List<String[]> excelList = new ArrayList<String[]>();
	        int index = 0;
	        int cols = CN.length;
	        for (int i = 0; i < list.size(); i++) {
	        	OnlineModel mo = list.get(i);
	            String[] tmpStr = new String[cols];
	            tmpStr[index++] = sdf.format(mo.getDate_TT());
	            tmpStr[index++] = mo.getMoldtype();
	            tmpStr[index++] = mo.getPart_number();
	            tmpStr[index++] = mo.getDescription();
	            tmpStr[index++] = mo.getSupcode();
	            tmpStr[index++] = mo.getSupname();
	            tmpStr[index++] = mo.getDefect_s();
	            tmpStr[index++] = mo.getBadnum()+"";
	            index = 0;
	            excelList.add(tmpStr);
	        }
	
	        // 获取项目根目录
	        String rootPath = request.getSession().getServletContext()
	                .getRealPath("/");
	        String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
	        String filePath = rootPath + "/" + fname;
	        ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
	        String contentType = "application/msexcel";
	        ExcelUtilities.downloadExcel(request, response, filePath,
	                contentType, "在线退次" + fname);
	        return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retView;
	}
	//ERP退次
	@RequestMapping("/downloadExeclErpnum")
	public String downloadExeclErpnum(Model model,HttpServletRequest request,HttpServletResponse response) {
		String retView = "/error/err";
		try {
			Subject subject = SecurityUtils.getSubject();
			PageParameter page = (PageParameter)subject.getSession().getAttribute("parameterpage");
			OnlineModel o = (OnlineModel)subject.getSession().getAttribute("parametero");
			List<OnlineModel> list=onlineServiceTwo.getlisttabErp(o,page);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式
			String[] CN = { "退次日期","物料编号","物料名称",
	                "供应商编号","供应商名称","退次数量","仓库"};
			List<String[]> excelList = new ArrayList<String[]>();
	        int index = 0;
	        int cols = CN.length;
	        for (int i = 0; i < list.size(); i++) {
	        	OnlineModel mo = list.get(i);
	            String[] tmpStr = new String[cols];
	            tmpStr[index++] = sdf.format(mo.getReturn_date());
	            tmpStr[index++] = mo.getPart_number();
	            tmpStr[index++] = mo.getPart_name();
	            tmpStr[index++] = mo.getSupplier_number();
	            tmpStr[index++] = mo.getSupplier_name();
	            tmpStr[index++] = mo.getReturn_number()+"";
	            tmpStr[index++] = mo.getWare_house()+"";
	            index = 0;
	            excelList.add(tmpStr);
	        }
	
	        // 获取项目根目录
	        String rootPath = request.getSession().getServletContext()
	                .getRealPath("/");
	        String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
	        String filePath = rootPath + "/" + fname;
	        ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
	        String contentType = "application/msexcel";
	        ExcelUtilities.downloadExcel(request, response, filePath,
	                contentType, "ERP退次" + fname);
	        return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retView;
	}
	//来料入库
	@RequestMapping("/downloadExeclStorage")
	public String downloadExeclStorage(Model model,HttpServletRequest request,HttpServletResponse response) {
		String retView = "/error/err";
		try {
			Subject subject = SecurityUtils.getSubject();
			PageParameter page = (PageParameter)subject.getSession().getAttribute("parameterpage");
			OnlineModel o = (OnlineModel)subject.getSession().getAttribute("parametero");
			List<OnlineModel> list=onlineServiceTwo.getlisttabMesStorage(o,page);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式
			String[] CN = { "入库日期","物料编号","物料名称",
	                "供应商编号","供应商名称","入库数量","仓库"};
			List<String[]> excelList = new ArrayList<String[]>();
	        int index = 0;
	        int cols = CN.length;
	        for (int i = 0; i < list.size(); i++) {
	        	OnlineModel mo = list.get(i);
	            String[] tmpStr = new String[cols];
	            tmpStr[index++] = sdf.format(mo.getArrival());
	            tmpStr[index++] = mo.getPartnumber();
	            tmpStr[index++] = mo.getPartname();
	            tmpStr[index++] = mo.getSupcode();
	            tmpStr[index++] = mo.getSupname();
	            tmpStr[index++] = mo.getTonum ()+"";
	            tmpStr[index++] = mo.getLocation();
	            index = 0;
	            excelList.add(tmpStr);
	        }
	
	        // 获取项目根目录
	        String rootPath = request.getSession().getServletContext()
	                .getRealPath("/");
	        String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
	        String filePath = rootPath + "/" + fname;
	        ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
	        String contentType = "application/msexcel";
	        ExcelUtilities.downloadExcel(request, response, filePath,
	                contentType, "来料入库" + fname);
	        return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retView;
	}
	/**
	 * 取中间的值
	 * @param supplier
	 * @return
	 */
	private String getSupStringBySemicolon(String supplier) {
		if(supplier.endsWith(";")){
			supplier = supplier.substring(0, supplier.length()-1);
		}
		String sup = "";
		if(supplier != null && !"".equals(supplier)){
			String[] str = supplier.split(",");
			sup=str[1];
		}
		return sup;
	}
	
	private OnlineModel setOnlineModel(OnlineModel o){
		String dateT=o.getDateT();
		if(dateT==null || dateT.equals("")){
			DateFormat sdf = new SimpleDateFormat("yyyy-MM");//日期格式
	        Calendar curr = Calendar.getInstance();//获取一个日历对象
	        curr.add(Calendar.MONTH, -1);
	        dateT=sdf.format(curr.getTime());
		}
		o.setDateT(dateT);
		return o;
	}
	
	/**
	 * 图表下载
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/chartDownload")
	public String chartDownload(Model model,OnlineModel on,HttpServletRequest request,HttpServletResponse response) {
		String retView = "/error/err";
		try {
			int chartid=Integer.parseInt(on.getCharthid());
			String[] CN = null;
//			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式
			List<String[]> excelList = new ArrayList<String[]>();
			Map<String, Class<OnlineModel>> classMap = new HashMap<String, Class<OnlineModel>>();
			classMap.put("onlineList", OnlineModel.class);
			OnlineVO vo = (OnlineVO)JSONObject.toBean(JSONObject.fromObject(on.getItems().substring(1)),OnlineVO.class , classMap);
			List<OnlineModel> list = vo.getOnlineList();
	        int index = 0;
	        int cols  = 0;
			switch (chartid) {
				case 1:
					CN  = new String[] { "供应商编号","供应商名称","不良批次数","总批次数"};
					cols = CN.length;
					for (int i = 0; i < list.size(); i++) {
				        	OnlineModel mo = list.get(i);
				            String[] tmpStr = new String[cols];
				            tmpStr[index++] = mo.getSupplier_number();
				            tmpStr[index++] = mo.getSupplier_name();
				            tmpStr[index++] = mo.getBadcount()+"";
				            tmpStr[index++] = mo.getTotal_qty_i()+"";
				            index = 0;
				            excelList.add(tmpStr);
				        }
					break;
				case 2:
					CN  = new String[] { "物料编号","物料名称","不良批次数","总批次数"};
					cols = CN.length;
					for (int i = 0; i < list.size(); i++) {
				        	OnlineModel mo = list.get(i);
				            String[] tmpStr = new String[cols];
				            tmpStr[index++] = mo.getPart_number();
				            tmpStr[index++] = mo.getPart_name();
				            tmpStr[index++] = mo.getBadcount()+"";
				            tmpStr[index++] = mo.getTotal_qty_i()+"";
				            index = 0;
				            excelList.add(tmpStr);
				        }
					break;
				case 3:
					CN  = new String[] { "不良现象","不良批次数","总批次数"};
					cols = CN.length;
					for (int i = 0; i < list.size(); i++) {
				        	OnlineModel mo = list.get(i);
				            String[] tmpStr = new String[cols];
				            tmpStr[index++] = mo.getDefect_s();
				            tmpStr[index++] = mo.getBadcount()+"";
				            tmpStr[index++] = mo.getTotal_qty_i()+"";
				            index = 0;
				            excelList.add(tmpStr);
				        }
					break;
				case 4:
					CN  = new String[] { "时间","不良批次数","总批次数"};
					cols = CN.length;
					for (int i = 0; i < list.size(); i++) {
				        	OnlineModel mo = list.get(i);
				            String[] tmpStr = new String[cols];
				            tmpStr[index++] = mo.getDate_t();
				            tmpStr[index++] = mo.getBadcount()+"";
				            tmpStr[index++] = mo.getTotal_qty_i()+"";
				            index = 0;
				            excelList.add(tmpStr);
				        }
					break;
				case 5:
					CN  = new String[] { "时间","不良批次数","总批次数"};
					cols = CN.length;
					for (int i = 0; i < list.size(); i++) {
				        	OnlineModel mo = list.get(i);
				            String[] tmpStr = new String[cols];
				            tmpStr[index++] = mo.getDate_t();
				            tmpStr[index++] = mo.getBadcount()+"";
				            tmpStr[index++] = mo.getTotal_qty_i()+"";
				            index = 0;
				            excelList.add(tmpStr);
				        }
					break;
				case 6 :
					CN  = new String[] { "供应商编号","供应商名称","不良数","总数"};
					cols = CN.length;
					for (int i = 0; i < list.size(); i++) {
				        	OnlineModel mo = list.get(i);
				            String[] tmpStr = new String[cols];
				            tmpStr[index++] = mo.getSupcode();
				            tmpStr[index++] = mo.getSupname();
				            tmpStr[index++] = mo.getBadcount()+"";
				            tmpStr[index++] = mo.getTotal_qty_i()+"";
				            index = 0;
				            excelList.add(tmpStr);
				        }
					break;
				case 7:
					CN  = new String[] { "物料编号","物料名称","不良数","总数"};
					cols = CN.length;
					for (int i = 0; i < list.size(); i++) {
				        	OnlineModel mo = list.get(i);
				            String[] tmpStr = new String[cols];
				            tmpStr[index++] = mo.getProductnumber();
				            tmpStr[index++] = mo.getProductname();
				            tmpStr[index++] = mo.getBadcount()+"";
				            tmpStr[index++] = mo.getTotal_qty_i()+"";
				            index = 0;
				            excelList.add(tmpStr);
				        }
					break;
				case 8:
					CN  = new String[] { "不良现象","不良数","总数"};
					cols = CN.length;
					for (int i = 0; i < list.size(); i++) {
				        	OnlineModel mo = list.get(i);
				            String[] tmpStr = new String[cols];
				            tmpStr[index++] = mo.getBadphenomenon();
				            tmpStr[index++] = mo.getBadcount()+"";
				            tmpStr[index++] = mo.getTotal_qty_i()+"";
				            index = 0;
				            excelList.add(tmpStr);
				        }
					break;
				case 9:
					CN  = new String[] { "时间","不良数","总数"};
					cols = CN.length;
					for (int i = 0; i < list.size(); i++) {
				        	OnlineModel mo = list.get(i);
				            String[] tmpStr = new String[cols];
				            tmpStr[index++] = mo.getDate_t();
				            tmpStr[index++] = mo.getBadcount()+"";
				            tmpStr[index++] = mo.getTotal_qty_i()+"";
				            index = 0;
				            excelList.add(tmpStr);
				        }
					break;
				case 10:
					CN  = new String[] { "时间","不良数","总数"};
					cols = CN.length;
					for (int i = 0; i < list.size(); i++) {
				        	OnlineModel mo = list.get(i);
				            String[] tmpStr = new String[cols];
				            tmpStr[index++] = mo.getDate_t();
				            tmpStr[index++] = mo.getBadcount()+"";
				            tmpStr[index++] = mo.getTotal_qty_i()+"";
				            index = 0;
				            excelList.add(tmpStr);
				        }
					break;
				case 11:
					CN  = new String[] { "供应商编号","供应商名称","不良数","总数"};
					cols = CN.length;
					for (int i = 0; i < list.size(); i++) {
				        	OnlineModel mo = list.get(i);
				            String[] tmpStr = new String[cols];
				            tmpStr[index++] = mo.getNumbers();
				            tmpStr[index++] = mo.getSupname();
				            tmpStr[index++] = mo.getBadcount()+"";
				            tmpStr[index++] = mo.getTotal_qty_i()+"";
				            index = 0;
				            excelList.add(tmpStr);
				        }
					break;
				case 12:
					CN  = new String[] { "物料编号","物料名称","不良数","总数"};
					cols = CN.length;
					for (int i = 0; i < list.size(); i++) {
				        	OnlineModel mo = list.get(i);
				            String[] tmpStr = new String[cols];
				            tmpStr[index++] = mo.getProductnumber();
				            tmpStr[index++] = mo.getProductname();
				            tmpStr[index++] = mo.getBadcount()+"";
				            tmpStr[index++] = mo.getTotal_qty_i()+"";
				            index = 0;
				            excelList.add(tmpStr);
				        }
					break;
				case 13:
					CN  = new String[] { "不良现象","不良数","总数"};
					cols = CN.length;
					for (int i = 0; i < list.size(); i++) {
				        	OnlineModel mo = list.get(i);
				            String[] tmpStr = new String[cols];
				            tmpStr[index++] = mo.getDefect_s();
				            tmpStr[index++] = mo.getBadcount()+"";
				            tmpStr[index++] = mo.getTotal_qty_i()+"";
				            index = 0;
				            excelList.add(tmpStr);
				        }
					break;
				case 14:
					CN  = new String[] { "时间","不良数","总数"};
					cols = CN.length;
					for (int i = 0; i < list.size(); i++) {
				        	OnlineModel mo = list.get(i);
				            String[] tmpStr = new String[cols];
				            tmpStr[index++] = mo.getDate_t();
				            tmpStr[index++] = mo.getBadcount()+"";
				            tmpStr[index++] = mo.getTotal_qty_i()+"";
				            index = 0;
				            excelList.add(tmpStr);
				        }
					break;
				case 15:
					CN  = new String[] {"时间","不良数","总数"};
					cols = CN.length;
					for (int i = 0; i < list.size(); i++) {
				        	OnlineModel mo = list.get(i);
				            String[] tmpStr = new String[cols];
				            tmpStr[index++] = mo.getDate_t();
				            tmpStr[index++] = mo.getBadcount()+"";
				            tmpStr[index++] = mo.getTotal_qty_i()+"";
				            index = 0;
				            excelList.add(tmpStr);
				        }
					break;
				default:
					break;
			}

	        // 获取项目根目录
	        String rootPath = request.getSession().getServletContext()
	                .getRealPath("/");
	        String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
	        String filePath = rootPath + "/" + fname;
	        ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
	        String contentType = "application/msexcel";
	        ExcelUtilities.downloadExcel(request, response, filePath,
	                contentType, "在线统计明细" + fname);
	        return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retView;
	}
}
