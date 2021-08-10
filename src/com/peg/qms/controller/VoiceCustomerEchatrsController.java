package com.peg.qms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peg.mes.highcharts.ChartObj;
import com.peg.model.CommonVo;
import com.peg.service.CommonServiceI;
import com.peg.service.VoiceCustomerEchatrsService;
import com.peg.web.util.ConditionUtil;


/**
 * 客户之声排列图和趋势图
 * @author by_jzp
 *
 */
@Controller
@RequestMapping("/voiceCustomerEchatrs")
public class VoiceCustomerEchatrsController extends BaseController {
	
	@Autowired
	private CommonServiceI commonService;
	
	@Autowired
	private VoiceCustomerEchatrsService voiceCustomerEchatrsService;
	
	@RequestMapping("/singleChart")
	private String singleChart(Model model, CommonVo vo){
		setBaseData(model);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadVoiceCategory(model, vo, commonService);
		//如果机型类别不为空
		if(vo.getProductType()!=null){
			//获取哪个图表
			voiceCustomerEchatrsService.findXAndY(vo);
		}
		
		model.addAttribute("vo",vo);
		return "qms/chart/voice/voiceChart";
	}
	
	@RequestMapping(value="/showCharts",produces ="text/html;charset=utf-8")
	@ResponseBody
	private String showCharts(Model model, CommonVo vo){
		setBaseData(model);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadVoiceCategory(model, vo, commonService);
			//产品型号
			if(vo.getPartTypeListTxt()!=null && vo.getPartTypeListTxt()!=""){
				vo.setPartTypes(vo.getPartTypeListTxt().split(";"));
			}
			//产品系列
			if(vo.getProductFamilyTxt()!=null && vo.getProductFamilyTxt()!=""){
				vo.setProductFamilys(vo.getProductFamilyTxt().split(";"));
			}
			 //区域
			if(vo.getRegionListTxt()!=null && vo.getRegionListTxt()!=""){
				vo.setRegions(vo.getRegionListTxt().split(";"));
			}
			//二级分类
			if(vo.getVoiceCategoryTxt()!=null && vo.getVoiceCategoryTxt()!=""){
				vo.setVoiceCategorys(vo.getVoiceCategoryTxt().split(";"));
			}
			if(vo.getTitle().equals("type1")){
				vo.setProductType("");
			}
			ChartObj chartsInfo = voiceCustomerEchatrsService.findXAndY(vo);
			
			
			ObjectMapper mapper = new ObjectMapper();
			Map<String,Object> map = new HashMap<String,Object>();
			String json  = null;
			try {
				//图表高度
				chartsInfo.setChartHight(600);
				//图表宽度
				chartsInfo.setChartWidth(1100);
				//图表横坐标名称
				chartsInfo.setxTitle(vo.getTitle());
				map.put("chartsInfo", chartsInfo);
				map.put("result","0");
				map.put("msg",null);
				json  = mapper.writeValueAsString(map);
			} catch (Exception e) {
				map.put("result","-1");
				map.put("msg", e.getMessage());
				try {
					json = mapper.writeValueAsString(map);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		model.addAttribute("vo",vo);
		return json;
	}
	
	
	
	/**
	 * 排列图返回json格式字符串的通用类
	 * @param xName 横坐标名称
	 * @param xValue x轴的值
	 * @param xData y轴的值
	 * @return
	 */
	public String fJson(String xName,List<String> xValue,List<Long> yValue){
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> map = new HashMap<String,Object>();
		String json  = null;
		try {
			ChartObj chartsInfo = new ChartObj();
			//图表高度
			chartsInfo.setChartHight(600);
			//图表宽度
			chartsInfo.setChartWidth(1100);
			//图表横坐标名称
			chartsInfo.setxTitle(xName);
			//x轴的值
			chartsInfo.setxValue(xValue);
			//y轴的值
			chartsInfo.setyValue(yValue);
			map.put("chartsInfo", chartsInfo);
			map.put("result","0");
			map.put("msg",null);
			json  = mapper.writeValueAsString(map);
		} catch (Exception e) {
			map.put("result","-1");
			map.put("msg", e.getMessage());
			try {
				json = mapper.writeValueAsString(map);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return json;
	}

}
