package com.peg.web.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.peg.model.CommonSelectedBox;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.ui.Model;

import com.peg.model.CommonVo;
import com.peg.model.ProductionLine;
import com.peg.model.part.MarketPart;
import com.peg.model.part.TestInstance;
import com.peg.service.CommonServiceI;
import com.peg.service.part.TestInstanceServiceI;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 前台多选择
 * @author Administrator
 *
 */
public class ConditionUtil {
	
	/**
	 * 加载产线
	 * @param model
	 * @param vo
	 */
	public static void loadPline(Model model, CommonVo vo,CommonServiceI commonService){
		model.addAttribute("factorys", commonService.getFactorys(null));
		ProductionLine factory = new ProductionLine();
		factory.setFactory(vo.getFactory());
		Map<String, String> lineMap = new LinkedHashMap<String, String>();
		if(!StringUtils.isEmpty(factory.getFactory())){
			List<ProductionLine> linelist = commonService.getLines(factory);
			for (int i = 0; i < linelist.size(); i++) {
				lineMap.put(linelist.get(i).getProductionLineNumber(), linelist.get(i).getProductionLineName());
			}
		}
		JSONObject lineJsonObject = JSONObject.fromObject( lineMap );
		model.addAttribute("jsonLines", lineJsonObject);
		if(StringUtils.isNotBlank(vo.getPlineListTxt())){
			vo.setPlineListTxt(WebUtil.parseToSelectedStr(vo.getPlineListTxt()));
		}
	}
	
	/**
	 * 加载产品系列
	 */
	public static void loadProductFamily(Model model, CommonVo vo,CommonServiceI commonService) {
		List<CommonVo> proFamilylist = commonService.getProductFamilyFromMes(vo);
		Map<String, String> proFamilyMap = new LinkedHashMap<String, String>();
		if (StringUtils.isNotEmpty(vo.getProductType())) {
			for (int i = 0; i < proFamilylist.size(); i++) {
				proFamilyMap.put(proFamilylist.get(i).getProductFamily(), proFamilylist.get(i).getProductFamily());
			}
		}
		JSONObject proFamilyJsonObj = JSONObject.fromObject(proFamilyMap);
		model.addAttribute("jsonProFamily", proFamilyJsonObj);
		if (StringUtils.isNotBlank(vo.getProductFamilyTxt())) {
			vo.setProductFamilyTxt(WebUtil.parseToSelectedStr(vo.getProductFamilyTxt()));
		}
	}
	
	/**
	 * 加载产品型号
	 * */
	public static void loadProductType(Model model, CommonVo vo, CommonServiceI commonService) {
		Map<String, String> partMap = new LinkedHashMap<String, String>();
		if (StringUtils.isNotEmpty(vo.getProductType())) {
			List<CommonVo> partlist = commonService.getPartTypeFromMes(vo);
			for (int i = 0; i < partlist.size(); i++) {
				partMap.put(partlist.get(i).getPartType(), partlist.get(i).getPartType());
			}
		}
		JSONObject partJsonObject = JSONObject.fromObject(partMap);
		model.addAttribute("jsonParts", partJsonObject);
		if (StringUtils.isNotBlank(vo.getPartTypeListTxt())) {
			vo.setPartTypeListTxt(WebUtil.parseToSelectedStr(vo.getPartTypeListTxt()));
		}
	}
	/**
	 * 加载气源
	 */
	public static void loadGasType(Model model, CommonVo vo, CommonServiceI commonService){
		Map<String, String> partMap = new LinkedHashMap<String, String>();
		String[] partlist = {"4T","10T","12T","20Y","7RⅡ","7RⅢ","7RⅣ","7RⅤ","6RⅡ","6RⅠ","5RⅠ","5RⅡ","5RⅤ"};
		for (int i = 0; i < partlist.length; i++) {
			partMap.put(partlist[i], partlist[i]);
		}
		JSONObject partJsonObject = JSONObject.fromObject(partMap);
		model.addAttribute("jsonGas", partJsonObject);
		if (StringUtils.isNotBlank(vo.getGasCategoryTxt())) {
			vo.setGasCategoryTxt(WebUtil.parseToSelectedStr(vo.getGasCategoryTxt()));
		}
	}
	
	
	/**
	 * 加载区域
	 * @param model
	 * @param comVo
	 * @param commonService
	 */
	public static void loadRegion(Model model, CommonVo vo, CommonServiceI commonService) {
		if (vo == null) {
			vo = new CommonVo();
		}
		List<CommonVo> regionlist = commonService.getRegion(vo);
		Map<String, String> regionMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < regionlist.size(); i++) {
			regionMap.put(regionlist.get(i).getRegion(), regionlist.get(i).getRegion());
		}
		JSONObject regionJsonObject = JSONObject.fromObject(regionMap);
		model.addAttribute("jsonRegions", regionJsonObject);
		if (StringUtils.isNotBlank(vo.getRegionListTxt())) {
			vo.setRegionListTxt(WebUtil.parseToSelectedStr(vo.getRegionListTxt()));
		}
	}
	/**
	 * 加载客户之声二级分类
	 * @param model
	 * @param comVo
	 * @param commonService
	 */
	public static void loadVoiceCategory(Model model, CommonVo vo, CommonServiceI commonService) {
		Map<String, String> voiceCategoryMap = new LinkedHashMap<String, String>();
		if (StringUtils.isNotEmpty(vo.getProductType())) {		
		//数据库加载客户之声二级分类
		List<CommonVo> regionlist = commonService.getVoiceCategory(vo);
		for (int i = 0; i < regionlist.size(); i++) {
			voiceCategoryMap.put(regionlist.get(i).getVoiceCategory(), regionlist.get(i).getVoiceCategory());
		}
		JSONObject regionJsonObject = JSONObject.fromObject(voiceCategoryMap);
		model.addAttribute("jsonVoiceCategory", regionJsonObject);
		}
		if (StringUtils.isNotBlank(vo.getVoiceCategoryTxt())) {
			vo.setVoiceCategoryTxt(WebUtil.parseToSelectedStr(vo.getVoiceCategoryTxt()));
		}
	}
	
	/**
	 * 加载合并区域
	 * @param model
	 * @param vo
	 * @param commonService
	 */
	public static void loadMergeRegion(Model model, CommonVo vo, CommonServiceI commonService) {
		if (vo == null) {
			vo = new CommonVo();
		}
		List<CommonVo> regionlist = commonService.getMergeRegion(vo);
		Map<String, String> regionMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < regionlist.size(); i++) {
			regionMap.put(regionlist.get(i).getRegion(), regionlist.get(i).getRegion());
		}
		JSONObject regionJsonObject = JSONObject.fromObject(regionMap);
		model.addAttribute("jsonMergeRegions", regionJsonObject);
		if (StringUtils.isNotBlank(vo.getMergeRegionListTxt())) {
			vo.setMergeRegionListTxt(WebUtil.parseToSelectedStr(vo.getMergeRegionListTxt()));
		}
	}
	
	/**
	 * 加载供应商
	 * */
	public static void loadSupplier(Model model, MarketPart vo, TestInstanceServiceI testInstanceService) {
		List<TestInstance> supList = testInstanceService.getSupplier();
		Map<String, String> supMap = new LinkedHashMap<String, String>();
		for(TestInstance sup : supList) {
			supMap.put(sup.getSupplier(), sup.getSupplier());
		}
		JSONObject supJsonObject = JSONObject.fromObject(supMap);
		model.addAttribute("jsonSuppliers", supJsonObject);
	}

	/**
	 * 加载产线
	 * @param model
	 * @param vo
	 */
    public static void loadPlineName(Model model, CommonVo vo, CommonServiceI commonService) {
        //工厂
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        List<CommonSelectedBox> factorys = (List<CommonSelectedBox>) request.getSession().getAttribute("lfactory");

        Map<String, List<ProductionLine>> lineMap = new HashMap<String, List<ProductionLine>>();
        String selFactory = null;
        for (CommonSelectedBox factory : factorys) {
            ProductionLine productionLine = new ProductionLine();
            productionLine.setFactory(factory.getFactory());
            List<ProductionLine> lineList = commonService.getLines(productionLine);
            lineMap.put(factory.getFactory(), lineList);
            //同一工厂下的
            if (factory.getFactory().equals(vo.getFactory())) {
                selFactory = factory.getFactory();
            }
        }
        if (selFactory != null) {
            List<ProductionLine> lineList = lineMap.get(selFactory);
            model.addAttribute("lineList", lineList);
        }
        model.addAttribute("factorys", factorys);
        model.addAttribute("lineMap", lineMap);
    }
}
