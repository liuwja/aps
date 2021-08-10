package com.peg.qms.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CommonVo;
import com.peg.model.ProductInstall;
import com.peg.service.CommonServiceI;
import com.peg.service.ProductInstallServiceI;
import com.peg.web.util.WebUtil;

@Controller
@RequestMapping("base/productInstall")
public class ProductInstallContoller extends BaseController{
	@Autowired
	private ProductInstallServiceI productInstallService;
	
	@Autowired
	private CommonServiceI commonService;
	
	/**
	 * 产品安装信息
	 * @param model
	 * @param proInstall
	 * @param page
	 * @return
	 */
	@RequestMapping("/list")
	public String productInstall(Model model,ProductInstall proInstall,PageParameter page)
	{
		setBaseData(model);
		getPartType(model, proInstall);
		if (proInstall.getPartTypeListTxt() != null && proInstall.getPartTypeListTxt() != "") {
			String[] partTypeList = proInstall.getPartTypeListTxt().split(";");
			StringBuilder sb = new StringBuilder();
			for (String s : partTypeList) {
				sb.append("\'" + s + "\',");
			}
			String partType = sb.substring(0, sb.length() - 1);
			proInstall.setPartType(partType);
			proInstall.setPartTypes(partTypeList);
		}
		proInstall.setRegionListTxt(WebUtil.parseToSelectedStr(proInstall.getRegionListTxt()));
		if (StringUtils.isNotEmpty(proInstall.getRegionListTxt())) {
			proInstall.setRegions(proInstall.getRegionListTxt().replace("'", "").split(","));
			StringBuilder sb = new StringBuilder();
			for (String s : proInstall.getRegions()) {
				sb.append("\'" + s + "\',");
			}
			String region = sb.substring(0, sb.length() - 1);
			proInstall.setRegion(region);
		}
		List<ProductInstall> list = productInstallService.findRecordAllByPage(proInstall, page);
		model.addAttribute("vo", proInstall);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "/qms/base/productInstall/list";
	}
	
	@RequestMapping("/init")
	public String initInstall(Model model,ProductInstall proInstall,PageParameter page)
	{
		setBaseData(model);
		getPartType(model, proInstall);
		String curDate = DateFormatUtils.format(new Date(), "yyyy-MM");
		proInstall.setStartTime(curDate);
		proInstall.setEndTime(curDate);
		model.addAttribute("vo", proInstall);
		return "/qms/base/productInstall/list";
	}
	
	private void getPartType(Model model, ProductInstall proInstall) {
		CommonVo vo = new CommonVo();
		if (!(StringUtils.isEmpty(proInstall.getnProductType()))) {
			vo.setProductType(proInstall.getnProductType());
		}
		List<CommonVo> partlist = commonService.getPartTypeFromMes(vo);
		Map<String, String> partMap = new LinkedHashMap<String, String>();
		if (StringUtils.isNotEmpty(vo.getProductType())) {
			for (int i = 0; i < partlist.size(); i++) {
				partMap.put(partlist.get(i).getPartType(), partlist.get(i).getPartType());
			}
		}
		JSONObject partJsonObject = JSONObject.fromObject(partMap);
		model.addAttribute("jsonParts", partJsonObject);
		List<CommonVo> regionlist = commonService.getRegion(vo);
		Map<String, String> regionMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < regionlist.size(); i++) {
			regionMap.put(regionlist.get(i).getRegion(), regionlist.get(i).getRegion());
		}
		JSONObject regionJsonObject = JSONObject.fromObject(regionMap);
		model.addAttribute("jsonRegions", regionJsonObject);
	}
}
