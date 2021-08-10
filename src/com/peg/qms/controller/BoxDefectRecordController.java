package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peg.dao.interceptor.PageParameter;
import com.peg.mes.highcharts.ChartObj;
import com.peg.model.BoxdefectrecordNew;
import com.peg.model.CommonSelectedBox;
import com.peg.model.CommonVo;
import com.peg.model.bph.BoxDefectRecord;
import com.peg.model.system.SysMesUser;
import com.peg.service.BoxDefectRecordService;
import com.peg.service.CommonServiceI;
import com.peg.service.part.TestInstanceServiceI;
import com.peg.web.util.ConditionUtil;

@Controller
@RequestMapping("poorOpen")
public class BoxDefectRecordController extends BaseController {
	
	@Autowired
	private CommonServiceI commonService;
	
	@Autowired
	private TestInstanceServiceI testInstanceService;
	

	@Autowired
	private BoxDefectRecordService boxDefectRecordService;
	/**
	 * 分页查询开箱不良记录
	 */
	@RequestMapping(value="list",produces ="text/html;charset=utf-8")
	public String list(Model model,CommonVo vo, PageParameter page){
		ConditionUtil.loadSupplier(model, null, testInstanceService);
		ConditionUtil.loadRegion(model, null, commonService);
		LoadFAPG(model,vo);
		List<BoxdefectrecordNew> list = boxDefectRecordService.list(vo,page);
		model.addAttribute("vo",vo);
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		return "qms/bph/base/poorOpening/list";
	}
	
	@RequestMapping("determine")
	public String determine(Model model,BoxdefectrecordNew boxDefectRecord,Integer type,HttpServletRequest request){
		BoxdefectrecordNew boxDefect = boxDefectRecordService.findOne(boxDefectRecord);
		Map<String,List<CommonSelectedBox>> fgroupMap = (Map<String, List<CommonSelectedBox>>) request.getSession().getAttribute("lfgroupMap");
		if(boxDefect.getDuty1()!=null && boxDefect.getDuty1()!=""){
		   List<CommonSelectedBox> fgroupList = fgroupMap.get(boxDefect.getDuty1());
	        model.addAttribute("common_fgroupMap", fgroupMap);
			   model.addAttribute("fgroupList", fgroupList);
		}
		model.addAttribute("type",type);
		model.addAttribute("ap", boxDefect);
		return "qms/bph/base/poorOpening/edit";
	}
	/**
	 * 责任判定
	 * @param boxDefectRecord
	 * @return
	 */
	@RequestMapping(value="save",produces ="text/html;charset=utf-8")
	@ResponseBody
	public String save(BoxdefectrecordNew boxdefectrecordNew){
		String json = "{\"result\":1}";
		ObjectMapper mapper = new ObjectMapper();
		try {
		Map<String,Object> map = new HashMap<String,Object>();
		SysMesUser user = (SysMesUser)SecurityUtils.getSubject().getSession().getAttribute("user");
		boxDefectRecordService.saveDuty(boxdefectrecordNew,user);
		map.put("result",0);
		json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	@RequestMapping("chart")
	public String chart(Model model,BoxDefectRecord boxDefectRecord){
		LoadFAPG(model,boxDefectRecord);
		model.addAttribute("vo",boxDefectRecord);
		return "qms/bph/base/poorOpening/chart";
	}
	
	@RequestMapping(value="showAll",produces ="text/html;charset=utf-8")
	@ResponseBody
	public String showChart(Model model,BoxDefectRecord boxDefectRecord){
		System.out.println("ssss");
		Map<String,Object> map = new HashMap<String,Object>();
		try{
		List<BoxDefectRecord> findAll = boxDefectRecordService.findAll(boxDefectRecord);
		ChartObj obj = new ChartObj();
		List<String> xValue = new ArrayList<String>();
		List<Long> yValue = new ArrayList<Long>();
		if(findAll !=null){
			for(BoxDefectRecord b : findAll){
				xValue.add(b.getTimeS());
				yValue.add(b.getSum());
			}
		}
		obj.setxValue(xValue);
		obj.setyValue(yValue);
		map.put("result",0);
		map.put("chartsInfo",obj);
		}catch(Exception e){
			map.put("result", 1);
			map.put("msg",e.toString());
			e.printStackTrace();
		}
		JSONObject jobject = JSONObject.fromObject(map);
		System.out.println(jobject.toString());
		return jobject.toString();
		
	}
}
