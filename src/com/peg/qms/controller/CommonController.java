package com.peg.qms.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CommonVo;
import com.peg.model.ProductionLine;
import com.peg.model.bph.MesDataSum;
import com.peg.model.bph.Onlinelookup;
import com.peg.model.jxmb.PerDeparment;
import com.peg.model.jxmb.PerformanceDepartment;
import com.peg.service.CommonServiceI;
import com.peg.service.jxmb.DeparmentServicel;
import com.peg.web.util.ConditionUtil;
/**
 * 公共操作类
 * @author song
 */
@Controller
@RequestMapping("qms/common")
public class CommonController extends BaseController{
	
	@Autowired
	private CommonServiceI commonService;
	
	@Autowired
	private DeparmentServicel dtService;

	/** 产品系列*/
	public static final String PRODUCT_FAMILY = "1";
	
	/** 产品型号*/
	public static final String PRODUCT_PART_TYPE = "2";
	
	/** 工厂产线*/
	public static final String PRODUCT_LINE = "3";
	
	/**
	 * 市场质量导入汇总页
	 */
	@RequestMapping("/sum")
	public String sum(Model model){	
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String queryMonth = DateFormatUtils.format(cal.getTime(), "yyyy-MM");
		model.addAttribute("queryMonth", queryMonth);
		return "/qms/sum/sumPage";
	}
	
	/**
	 * bph导入汇总页
	 */
	@RequestMapping("/mesSum")
	public String mesSum(Model model){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String queryMonth = DateFormatUtils.format(cal.getTime(), "yyyy-MM");
		String queryDay = DateFormatUtils.format(cal.getTime(), "yyyy-MM-dd");			
		MesDataSum mesDataSum = new MesDataSum();
		mesDataSum.setQueryMonth(queryMonth);
		mesDataSum.setQueryDay(queryDay);
		model.addAttribute("list", mesDataSum);
		return "/qms/sum/mesDataSum";
	}
	
	//各报表计算说明
	@RequestMapping("/chartDecription")
	public String chartDecription(){
		return "/qms/chart/chartDecription/list";
	}
	/**
	 * 加载级联的产品型号，产线编号作为条件
	 */
	@RequestMapping("/partTypeLineOps")
	public String partTypeLineOps(CommonVo vo, Model model,
			@RequestParam(value = "partTypeDocId", required = false) String partTypeDocId,
			@RequestParam(value = "plineDocId", required = false) String plineDocId,
			@RequestParam(value = "productFamilyDocId", required = false) String productFamilyDocId) {
		
		setBaseData(model);
		if (vo.getProductFamilyTxt() != null && vo.getProductFamilyTxt().indexOf("null") > -1) {
			vo.setProductFamilyTxt(vo.getProductFamilyTxt().substring(5));
		}
		ConditionUtil.loadProductFamily(model, vo, commonService);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadPline(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		model.addAttribute("vo", vo);
		model.addAttribute("partTypeDocId", partTypeDocId);
		model.addAttribute("plineDocId", plineDocId);
		model.addAttribute("productFamilyDocId", productFamilyDocId);
		return "qms/select/partLineCondition";
	}
	
	/**
	 * 加载级联的产品型号，产线编号作为条件
	 */
	@RequestMapping("/productLineOps")
	public String productLineOps(CommonVo vo, Model model, @RequestParam(value = "plineDocId", required = false) String plineDocId) {
		setBaseData(model);
		ConditionUtil.loadPline(model, vo, commonService);
		model.addAttribute("vo", vo);
		model.addAttribute("plineDocId", plineDocId);
		return "qms/select/prodLineCondition";
	}
	
	/**
	 * 查找供应商
	 * @return
	 */
	@RequestMapping("/supplier")
	public String findsupplier(Model model,PageParameter page,Onlinelookup vo,String idend,String flag,String data){
		BaseSearch bs=new BaseSearch();
		bs.setPage(page);
		bs.put("factoryS", vo.getFactoryS());
		bs.put("numbers", vo.getNumbers());
		bs.put("name", vo.getName());
		List<Onlinelookup> list=commonService.getAccountpages(bs);
		List<String> listfactory=commonService.getFactory();
		model.addAttribute("factorylist", listfactory);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		model.addAttribute("id_end", idend);
		return "qms/common/supplier";
	}
	
	/**
	 * 查找物料分类
	 * @return
	 */
	@RequestMapping("/classification")
	public String findclassification(Model model,PageParameter page,Onlinelookup vo){
		BaseSearch bs=new BaseSearch();
		bs.setPage(page);
		bs.put("factoryS", vo.getFactoryS());
		bs.put("uda_2", vo.getUda_2());
		List<Onlinelookup> list=commonService.getPartpages(bs);
		List<String> listfactory=commonService.getFactory();
		model.addAttribute("factorylist", listfactory);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "qms/common/classification";
	}
	/**
	 * 查找物料编号
	 * @return
	 */
	@RequestMapping("/number")
	public String findnumber(Model model,PageParameter page,Onlinelookup vo,String idend){
		BaseSearch bs=new BaseSearch();
		bs.setPage(page);
		bs.put("uda_3", vo.getUda_3());
		bs.put("partnumber", vo.getPartnumber());
		bs.put("description", vo.getDescription());
		List<Onlinelookup> list=commonService.getPartTwopages(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		model.addAttribute("id_end", idend);
		return "qms/common/number";
	}
	
	/**
	 * 加载机型系列或机型类别
	 * @param vo
	 * @param response
	 */
	@RequestMapping("/loadProductData")
	public void loadProductData(CommonVo vo, HttpServletResponse response) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		String resultJsonObject = null;
		if (StringUtils.isNotEmpty(vo.getProductType())) {
			if (PRODUCT_FAMILY.equals(vo.getTitle())) {
				List<CommonVo> proFamilyList = commonService.getProductFamilyFromMes(vo);
				for (CommonVo tempVo : proFamilyList) {
					resultMap.put(tempVo.getProductFamily(), tempVo.getProductFamily());
				}
				resultJsonObject = JSONObject.fromObject(resultMap).toString();
				printResponContent(response, resultJsonObject.toString());
			} else if (PRODUCT_PART_TYPE.equals(vo.getTitle())) {
				if (StringUtils.isNotEmpty(vo.getProductFamilyTxt())) {
					vo.setProductFamilyList(Arrays.asList(vo.getProductFamilyTxt().replaceAll("'", "").split(";")));
				} else {
					vo.setProductFamilyList(null);
				}
				List<CommonVo> proPartList = commonService.getPartTypeFromMes(vo);
				for (CommonVo tempVo : proPartList) {
					resultMap.put(tempVo.getPartType(), tempVo.getPartType());
				}
				resultJsonObject = JSONObject.fromObject(resultMap).toString();
				printResponContent(response, resultJsonObject.toString());
			}
		}
		if (PRODUCT_LINE.equals(vo.getTitle())) {
			ProductionLine factory = new ProductionLine();
			factory.setFactory(vo.getFactory());
			Map<String, String> lineMap = new LinkedHashMap<String, String>();
			if(!StringUtils.isEmpty(factory.getFactory())){
				List<ProductionLine> linelist = commonService.getLines(factory);
				for (int i = 0; i < linelist.size(); i++) {
					lineMap.put(linelist.get(i).getProductionLineNumber(), linelist.get(i).getProductionLineName());
				}
			}
			JSONObject lineJsonObject = JSONObject.fromObject(lineMap);
			printResponContent(response, lineJsonObject.toString());
		}
	}
	
	@RequestMapping("/loadDepartment")
	public void loadDepartment(CommonVo vo, HttpServletResponse response){
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		BaseSearch bs = new BaseSearch();
		bs.put("factoryNumber", vo.getFactory());
		List<PerDeparment> list = dtService.getAllByPage(bs, false);
		System.out.println("部门记录条数："+list.size());
		resultMap.put("departmentList", list);
		JSONObject lineJsonObject = JSONObject.fromObject(resultMap);
		printResponContent(response, lineJsonObject.toString());
	}
	
	@RequestMapping("/loadVoiceCategory")
	@ResponseBody
	public String loadVoiceCategory(@Param("productType")String productType, HttpServletResponse response){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		String resultJsonObject = "{}";
		if (StringUtils.isNotEmpty(productType)) {
			CommonVo vo = new CommonVo();
			vo.setProductType(productType);
			List<CommonVo> voiceCategory = commonService.getVoiceCategory(vo);
			for (CommonVo tempVo : voiceCategory) {
				resultMap.put(tempVo.getVoiceCategory(), tempVo.getVoiceCategory());
			}
			resultJsonObject = JSONObject.fromObject(resultMap).toString();
			//printResponContent(response, resultJsonObject.toString());
		}
		return resultJsonObject;
	}
}