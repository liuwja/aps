package com.peg.qms.controller.part;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.echarts.EChartObj;
import com.peg.model.bph.Onlinelookup;
import com.peg.model.part.MarketPart;
import com.peg.model.part.TestInstance;
import com.peg.qms.controller.BaseController;
import com.peg.service.CommonServiceI;
import com.peg.service.part.OnlineServiceI;
import com.peg.service.part.TestInstanceServiceI;
import com.peg.web.util.TmStringUtils;

@Controller
@RequestMapping("base/quality")
public class QualityController extends BaseController {
	@Autowired
	private CommonServiceI commonService;
	@Resource
	private OnlineServiceI onlineService;
	@Autowired
	private TestInstanceServiceI testInstanceService;
	
	/**
	 * 在线主页面
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/onlineHomePage")
	private String findHomePage(Model model,Onlinelookup vo,TestInstance t){	
		testInstanceService.loadData(model);
		if(t.getAnalysisType()==null || t.getAnalysisType().equals("")){
			t.setAnalysisType("0");
		}
		String msg=null;
		int result=0;
		try {
			vo=ordercondition(vo,t);
			vo=datefand(vo);
			for (int i = 1; i < 9; i++) {
				EChartObj chart = new EChartObj();
				vo.setBs(i);
				chart=onlineService.getEChartObj(vo);
				JSONObject resultObject = JSONObject.fromObject(chart);
				model.addAttribute("info"+i, resultObject);
			}
		} catch (Exception e) {
			result=-1;
			logger.error(e.getMessage(), e);
			msg=e.getMessage();
		}
		model.addAttribute("result", result);
		model.addAttribute("msg", msg);
		model.addAttribute("vo", t);
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//日期格式
		Date date=new Date();
		model.addAttribute("id_end", sdf.format(date));
		return "qms/part/quality/onlineHomePage";
	}
	
	/**
	 * 在线物料明细
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/onlineHomeChar")
	public String findHomeChar(Model model,Onlinelookup vo){
		testInstanceService.loadData(model);
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//日期格式
		Date date=new Date();
		model.addAttribute("id_end", sdf.format(date));
		return "qms/part/quality/onlineHomeChar";
	}
	
	/**
	 * 在线批次明细
	 * @param model
	 * @param vo
	 */
	@RequestMapping("/onlinebatch")
	public String findpoorbatch(Model model,Onlinelookup vo,TestInstance t,PageParameter page,String jsonStr){
		BaseSearch bs=new BaseSearch();
		try {
			if(jsonStr!=null){
				vo= setobj(vo,jsonStr);
				if(vo.getStrone()!=null && !vo.getStrone().equals("")){
					vo.setNumberstxt(TmStringUtils.getStringBysplit(vo.getStrone(),vo.getBs()));
				}
				if(vo.getStrtwo()!=null && !vo.getStrtwo().equals("")){
					vo.setNumberstxtstr(vo.getStrtwo());
				}
				if(vo.getSpareparts()!=null && !vo.getSpareparts().equals("")){
					vo.setPartname(vo.getSpareparts());
				}
				if(vo.getSpareparts2()!=null && !vo.getSpareparts2().equals("")){
					vo.setPartnumbertxt(TmStringUtils.getStringBysplit(vo.getSpareparts2(),vo.getBs()));
				}
				if((vo.getBs()==4 || vo.getBs()==5) && vo.getDimension()!=null && !vo.getDimension().equals("") && vo.getEndtime()!=null && !vo.getEndtime().equals("")){
					vo.setBs(16);
					vo.setEndtime(vo.getTrendTime());
				}
			}else {
				vo=ordercondition(vo,t);
			}
			bs = setbs(model, vo, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Onlinelookup> list=onlineService.gettablist(bs);
		model.addAttribute("list", list);
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//日期格式
		Date date=new Date();
		model.addAttribute("id_end", sdf.format(date));
		t=ordercondition2(vo,t);
		model.addAttribute("vo", t);
		return "qms/part/quality/onlinebatch";
	}
	//将json转成实体
	private Onlinelookup setobj(Onlinelookup vo,String jsonStr) throws Exception{
		String str=decode(jsonStr);
		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(str);  
		vo = (Onlinelookup) net.sf.json.JSONObject.toBean(jsonObject, Onlinelookup.class);
		return vo;
	}
	/**
	 * 请求参数返回json
	 * @param model
	 * @param vo
	 * @param respon
	 */
	@RequestMapping("/onlinesetvo")
	public void setvovalue(Model model,Onlinelookup vo,TestInstance t,HttpServletResponse respon){
		Map<String, Object> map = new HashMap<String, Object>();
		if (vo.getHiddenId()!=null && vo.getHiddenId().equals("1")) {//供应商不良柱形图
			vo.setNumberstxt(vo.getStrone());
			vo.setNumberstxtstr(vo.getStrtwo());
		}
		vo=ordercondition(vo,t);
		map.put("obj",vo);
		JSONObject resultObject = JSONObject.fromObject(map);
		printResponContent(respon, resultObject.toString());
	}
	/**
	 * 在线不良明细(mes)
	 * @param model
	 * @param vo
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/onlinenum")
	public String findpoornum(Model model,Onlinelookup vo,TestInstance t,PageParameter page,String jsonStr) throws Exception{
		BaseSearch bs=new BaseSearch();	
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//日期格式
		try {
			if(jsonStr!=null){
				vo= setobj(vo,jsonStr);
			}else {
				vo=ordercondition(vo,t);
			}
			bs = setbs(model, vo, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Onlinelookup> list=onlineService.getTabList(bs);
		model.addAttribute("list", list);
		Date date=new Date();
		t=ordercondition2(vo,t);
		model.addAttribute("id_end", sdf.format(date));
		model.addAttribute("vo", t);
		return "qms/part/quality/onlinenum";
	}
	/**
	 * 在线不良明细ERP
	 * @param model
	 * @param vo
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/onlineERPnum")
	public String findpoorERPnum(Model model,TestInstance t,Onlinelookup vo,PageParameter page,String jsonStr) throws Exception{
		BaseSearch bs=new BaseSearch();	
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//日期格式
		try {
			if(jsonStr!=null){
				vo= setobj(vo,jsonStr);
				if(vo.getStrone()!=null && !vo.getStrone().equals("")){
					vo.setNumberstxt(TmStringUtils.getStringBysplit(vo.getStrone(),vo.getBs()));
				}
				if(vo.getStrtwo()!=null && !vo.getStrtwo().equals("")){
					vo.setNumberstxtstr(vo.getStrtwo());
				}
				if(vo.getSpareparts()!=null && !vo.getSpareparts().equals("")){
					vo.setPartname(vo.getSpareparts());
				}
				if(vo.getSpareparts2()!=null && !vo.getSpareparts2().equals("")){
					vo.setPartnumbertxt(TmStringUtils.getStringBysplit(vo.getSpareparts2(),vo.getBs()));
				}
				if((vo.getBs()==9 || vo.getBs()==10 || vo.getBs()==14 || vo.getBs()==15) &&vo.getDimension()!=null && !vo.getDimension().equals("") && vo.getEndtime()!=null && !vo.getEndtime().equals("")){
					vo.setBs(16);
					vo.setEndtime(vo.getTrendTime());
				}
			}else {
				vo=ordercondition(vo,t);
			}
			bs = setbs(model, vo, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Onlinelookup> list=onlineService.getERPTabList(bs);
		model.addAttribute("list", list);
		Date date=new Date();
		t=ordercondition2(vo,t);
		model.addAttribute("id_end", sdf.format(date));
		model.addAttribute("vo", t);
		return "qms/part/quality/onlineERPnum";
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
	public String findpoorStorage(Model model,TestInstance t,PageParameter page, String type, String commonName, String commonNumber){
		BaseSearch bs=new BaseSearch();	
		try {
			if (type != null && type.equals("-2")) {
				Subject subject = SecurityUtils.getSubject();
				MarketPart tempVo = (MarketPart)subject.getSession().getAttribute("sessionVo");
				MarketPart vo = tempVo.clone();
				commonName = decode(commonName);
				commonNumber = decode(commonNumber);
				List<String> supplierNumbers = new ArrayList<String>();
				supplierNumbers.add(commonNumber);
				bs.setPage(page);
				bs.put("type", vo.getProductType());
				bs.put("suplist", supplierNumbers);
				bs.put("partlist", Arrays.asList(vo.getPartNumbers()));
				bs.put("maturity", vo.getPartMaturity());
				bs.put("startdate", vo.getStartTime() + "-01");
				Integer month = Integer.parseInt(vo.getEndTime().substring(5));
				if (month.equals("12")) {
					Integer year = Integer.parseInt(vo.getEndTime().substring(0, 4)) + 1;
					vo.setEndTime(year + "-01-01");
				} else {
					String year = vo.getEndTime().substring(0, 4);
					month++;
					vo.setEndTime(year + "-" + month + "-01");
				}
				bs.put("enddate", vo.getEndTime());
				Map<String, String> partMap = new LinkedHashMap<String, String>();
				JSONObject partJsonObject = JSONObject.fromObject(partMap);
				model.addAttribute("partMap", partJsonObject);
				model.addAttribute("page", page);
				t.setProductType(vo.getProductType());
				t.setSupplier(commonName);
				t.setSupplierData(vo.getSupplierId());
				t.setSupplierCode(commonNumber);
				t.setPartName(vo.getPartDescription());
				t.setPartData(vo.getPartId());
				t.setPartNumber(vo.getPartNumber());
				t.setPartVersion(vo.getHasVersion().equals("1") ? "是" : "否");
				t.setDateType(vo.getSelectDate());
				t.setDateT(vo.getQueryMonth());
//				t.setIsNew(vo.getPartMaturity());
			} else {
				Onlinelookup vo=new Onlinelookup();
				vo=ordercondition(vo,t);
				bs = setbs(model, vo, page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Onlinelookup> list=onlineService.gettabStoragelist(bs);
		model.addAttribute("vo", t);
		model.addAttribute("list", list);
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//日期格式
		Date date=new Date();
		model.addAttribute("id_end", sdf.format(date));
		return "qms/part/quality/onlineStorage";
	}
	@RequestMapping("/poor")
	public void findAccountpoor(Model model,Onlinelookup vo,TestInstance t,HttpServletResponse respon) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		vo=ordercondition(vo,t);
		int result=vo.getBs();
		String msg=null;
		EChartObj chart = new EChartObj();
		try {
			vo=datefand(vo);
			chart=onlineService.getEChartObj(vo);
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
	//将公共页面的查询条件转换成自己的查询条件
	private Onlinelookup ordercondition(Onlinelookup vo,TestInstance t){
		String datatype=t.getDateType()==null?"":t.getDateType();
		String time=t.getDateT();
		if(time!=null && !time.equals("")){
			if(datatype.equals("天")){
				vo.setDimension("1");
			}else if(datatype.equals("周")){
				vo.setDimension("2");
			}else if(datatype.equals("月")){
				vo.setDimension("3");
				vo.setDate_type("yyyy-mm");
			}else if(datatype.equals("年")){
				vo.setDimension("4");
			}else {
				
			}
		}
		vo.setFactoryS(t.getFactory());
		vo.setEndtime(t.getDateT());
		vo.setCharNumber(t.getColumnNum());
		vo.setNumberstxt(TmStringUtils.getStringBysplit(t.getSupplierData(),vo.getBs()));
		//vo.setStrone(t.getSupplierData());
		vo.setNumberstxtstr(t.getSupplier());
		vo.setLevel(t.getPartClass());
		vo.setPartnumbertxt(TmStringUtils.getStringBysplit(t.getPartData(),vo.getBs()));
		vo.setPartnumbertxtstr(t.getPartName());
		vo.setMaturity(t.getIsNew());
		vo.setType(t.getProductType());
		vo.setBatchtime(t.getLotTime());
		vo.setBatch(Handlebatch(t.getLotTime()));
		vo.setIsEdition(t.getPartVersion());
		String str=new String();
		if(t.getIscrux()!=null && t.getIscrux().equals("0")){
			str="是";
		}else{
			str="否";
		}
		vo.setIscrux(str);
		return vo;
	}
	//将自己的查询条件转换成公共页面的查询条件
	private TestInstance ordercondition2(Onlinelookup vo,TestInstance t){
		t.setProductType(vo.getType());
		String datatype=vo.getDimension()==null?"":vo.getDimension();
		String time=vo.getEndtime();
		if(time!=null && !time.equals("")){
			if(datatype.equals("1")){
				t.setDateType("日");
			}else if(datatype.equals("2")){
				t.setDateType("周");
			}else if(datatype.equals("3")){
				t.setDateType("月");
			}else if(datatype.equals("4")){
				t.setDateType("年");
			}else {
				
			}
		}
		t.setFactory(vo.getFactoryS());
		t.setDateT(vo.getEndtime());
		t.setColumnNum(vo.getCharNumber());
		t.setSupplierData(vo.getStrone()==null?t.getSupplierData():vo.getStrone());
		t.setSupplier(vo.getNumberstxtstr());
		t.setPartClass(vo.getLevel());
		t.setPartData(vo.getSpareparts2()==null?t.getPartData():vo.getSpareparts2());
		t.setPartName(vo.getPartnumbertxtstr());
		t.setIsNew(vo.getMaturity());
		t.setLotTime(vo.getBatchtime());
		t.setPartVersion(vo.getIsEdition());
		String str=new String();
		if(vo.getIscrux()!=null && vo.getIscrux().equals("是")){
			str="0";
		}else{
			str="1";
		}
		t.setIscrux(str);
		return t;
	}
//	private void setfactory(Model model,Onlinelookup vo){
//		List<String> list=commonService.getFactory();
//		List<String> listtype=new ArrayList<String>();
//		if(vo.getFactoryS()!=null && vo.getFactoryS()!=""){
//			listtype=commonService.gettype(vo.getFactoryS());
//		}
//		model.addAttribute("factorylist", list);
//		model.addAttribute("listtype", listtype);
//	}
//	@RequestMapping("findtype")
//	public void gettype(Model model,String factory,HttpServletResponse respon){
//		int result = 0;
//		String msg = null;
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<String> list = new ArrayList<String>();
//		try {
//			list=commonService.gettype(factory);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			result = -1;
//			msg = e.getMessage();
//		}
//		map.put("listtype", list);
//		map.put("result", result);
//		map.put("msg", msg);
//		JSONObject resultObject = JSONObject.fromObject(map);
//		logger.debug(resultObject);
//		printResponContent(respon, resultObject.toString());
//	}
	//条件处理
	private BaseSearch setbs(Model model,Onlinelookup vo,PageParameter page) throws Exception{
		testInstanceService.loadData(model);
		vo=datefand(vo);
		BaseSearch bs=new BaseSearch();
		bs.setList(vo.getSuplist());
		bs.setList2(vo.getPartclasslist());
		bs.setList3(vo.getPartlist());
		bs.setPage(page);
		bs.put("factoryS", vo.getFactoryS());
		bs.put("type", vo.getType());
		bs.put("startdate", vo.getStartdate());
		bs.put("enddate", vo.getEnddate());
		bs.put("suplist", vo.getSuplist());
		bs.put("partclasslist", vo.getPartclasslist());
		bs.put("partlist", vo.getPartlist());
		bs.put("partlevel", vo.getLevel());
		bs.put("maturity", vo.getMaturity());
		bs.put("isEdition", vo.getIsEdition());
		bs.put("badphenomenon", vo.getBadphenomenon());
		bs.put("spareparts", vo.getSpareparts());
		bs.put("iscrux", vo.getIscrux());
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		return bs;
	}
	//条件处理（时间维度，供应商，物料分类，物料编号）
	private Onlinelookup datefand(Onlinelookup vo) throws Exception{
		String time=vo.getEndtime();//截止日期
		String dimension=vo.getDimension()==null?"1":vo.getDimension();//时间维度
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式
        Calendar curr = Calendar.getInstance();//获取一个日历对象
        if(vo.getCharNumber()==null){
        	vo.setCharNumber(10);
        }
        int i = 0;
        if(judge(vo)){
        	i = 0;
        }else {
        	i=vo.getCharNumber()==0?0:vo.getCharNumber();
		}
        //日期处理
        if(time==null || time.equals("")){
        	Date date = new Date();
        	time=sdf.format(date);
        }
        if(dimension.equals("4")){
        	sdf = new SimpleDateFormat("yyyy");
        	Date date = sdf.parse(time);
        	curr.setTime(date);
        	vo.setEnddate(sdf.format(curr.getTime())+"-12"+"-31");
        	curr.add(Calendar.YEAR, -1*i);
        	vo.setStartdate(sdf.format(curr.getTime())+"-01"+"-01");
        	vo.setDate_type("yyyy");
        }
        if(dimension.equals("3")){
        	sdf = new SimpleDateFormat("yyyy-MM");
        	Date date = sdf.parse(time);
        	curr.setTime(date);
           	int lastDay = curr.getActualMaximum(Calendar.DAY_OF_MONTH);
        	vo.setEnddate(sdf.format(curr.getTime())+"-"+lastDay);
        	curr.add(Calendar.MONTH, -1*i);
        	vo.setStartdate(sdf.format(curr.getTime())+"-01");
        	vo.setDate_type("yyyy-mm");
        }
        if(dimension.equals("2")){
        	Date date = sdf.parse(time);
        	curr.setTime(date);
        	vo.setEnddate(sdf.format(curr.getTime()));
        	if(i==0){
        		i=1;
        	}
        	curr.add(Calendar.DAY_OF_YEAR, -7*i);
        	vo.setStartdate(sdf.format(curr.getTime()));
        	vo.setDate_type("yyyy-IW");
        }
        if(dimension.equals("1")){
        	Date date = sdf.parse(time);
        	curr.setTime(date);
        	vo.setStartdate(sdf.format(curr.getTime()));
        	curr.add(Calendar.DAY_OF_YEAR, 1);
        	vo.setEnddate(sdf.format(curr.getTime()));
        	vo.setDate_type("yyyy-mm-dd");
        }
        //供应商处理
        if(vo.getNumberstxt()!=null && !vo.getNumberstxt().equals("")){
        	List<String> list=new ArrayList<String>();
			String[] suparr=vo.getNumberstxt().split(",");
			Collections.addAll(list, suparr);
			vo.setSuplist(list);
		}
        if(vo.getPartnumbertxt()!=null && !vo.getPartnumbertxt().equals("")){
//        	List<String> list=new ArrayList<String>();
//        	String[] part=vo.getPartnumbertxt().split(",");
//        	Collections.addAll(list, part);
//			vo.setPartlist(list);
        	List<String> list=new ArrayList<String>();
        	String[] part=vo.getPartnumbertxt().split(",");
        	for (int j = 0; j < part.length; j++) {
				if(vo.getIsEdition()!=null && vo.getIsEdition().equals("否")){
					String str=part[j];
					list.add(str.substring(0,str.length()-1));
				}else {
					list.add(part[j]);
				}
			}
        	vo.setPartlist(list);
        }
        return vo;
    }
	private String Handlebatch(String dateTime){
		StringBuilder sb=new StringBuilder();
		if(dateTime!=null && !dateTime.equals("")){
			String[] array = dateTime.split("-");
			String[] array2 = {"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v"};
			sb.append(array[0].substring(3, 4));
			String mm=array[1].substring(0, 1);
			if(mm.equals("0")){
				mm=array[1].substring(1, 2);
			}
			int month=Integer.parseInt(mm);
			sb.append(array2[month-1]);
			int day=Integer.parseInt(array[2]);
			sb.append(array2[day-1]);
		}
		return sb.toString();
	}
	
	private boolean judge(Onlinelookup vo){
		List<Integer> places = Arrays.asList(1,2,3,6,7,8,11,12,13,16);
		boolean result = places.contains(vo.getBs());
		return result;
	}
}
