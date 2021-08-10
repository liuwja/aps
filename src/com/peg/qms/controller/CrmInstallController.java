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
import com.peg.model.CrmInstall;
import com.peg.model.ProductInstall;
import com.peg.service.CommonServiceI;
import com.peg.service.CrmInstallService;
import com.peg.web.util.WebUtil;
@Controller
@RequestMapping("base/crmInstall")
public class CrmInstallController extends BaseController{
	@Autowired
	private CrmInstallService crmInstallService;
	
	@Autowired
	private CommonServiceI commonService;
	
	/**
	 * 产品安装信息
	 * @param model
	 * @param crmInstall
	 * @param page
	 * @return
	 */
	@RequestMapping("/list")
	public String crmInstall(Model model,CrmInstall crmInstall,PageParameter page)
	{
		setBaseData(model);
		getPartType(model, crmInstall);
		crmInstall.setRegionListTxt(WebUtil.parseToSelectedStr(crmInstall.getRegionListTxt()));
		if (StringUtils.isNotEmpty(crmInstall.getRegionListTxt())) {
			crmInstall.setRegions(crmInstall.getRegionListTxt().replace("'", "").split(","));
			StringBuilder sb = new StringBuilder();
			for (String s : crmInstall.getRegions()) {
				sb.append("\'" + s + "\',");
			}
			String region = sb.substring(0, sb.length() - 1);
			crmInstall.setRegion(region);
		}
		List<CrmInstall> list = crmInstallService.findRecordAllByPage(crmInstall, page);
		model.addAttribute("vo", crmInstall);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "/qms/base/crmInstall/list";
	}
	
	@RequestMapping("/init")
	public String initInstall(Model model,CrmInstall crmInstall,PageParameter page)
	{
		setBaseData(model);
		getPartType(model, crmInstall);
		String curDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		crmInstall.setStartTime(curDate);
		crmInstall.setEndTime(curDate);
		crmInstall.setInstallStartTime(curDate);
		crmInstall.setInstallEndTime(curDate);
		model.addAttribute("vo", crmInstall);
		return "/qms/base/crmInstall/list";
	}
	private void getPartType(Model model, CrmInstall crmInstall) {
		CommonVo vo = new CommonVo();
		List<CommonVo> regionlist = commonService.getRegion(vo);
		Map<String, String> regionMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < regionlist.size(); i++) {
			regionMap.put(regionlist.get(i).getRegion(), regionlist.get(i).getRegion());
		}
		JSONObject regionJsonObject = JSONObject.fromObject(regionMap);
		model.addAttribute("jsonRegions", regionJsonObject);
	}

}
