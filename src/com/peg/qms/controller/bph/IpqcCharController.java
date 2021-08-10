package com.peg.qms.controller.bph;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.mes.highcharts.ChartObj;
import com.peg.model.CommonVo;
import com.peg.qms.controller.BaseController;
import com.peg.service.bph.CommonSelectService;
import com.peg.web.util.DateEditor;
/**
 * 
 * @author Administrator
 *IPQC报表查询
 */
@Controller
@RequestMapping("base/ipqcChar")
public class IpqcCharController extends BaseController{

	@Resource  CommonSelectService commonSelectService;
	
	private final static String CODE1 = "巡检批次不良率";
	/**
	 * IPQC不良现象统计
	 * @param model
	 * @param vo
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/ipqcDefectChar")
	private String ipqcDefectChar(Model model,CommonVo vo) throws UnsupportedEncodingException{
		vo.setFactory(decode(vo.getFactory()));
		vo.setShiftGroupTxt(decode(vo.getShiftGroupTxt()));
		vo.setStartTime(decode(vo.getStartTime()));
		vo.setEndTime(decode(vo.getEndTime()));
		if(vo.getStartTime()==null || "".equals(vo.getStartTime())){
			vo.setStartTime(new DateEditor().getStartTime());
			vo.setEndTime(new DateEditor().getEndTime());
		}
		model.addAttribute("vo",vo);
		return "qms/chart/ipqcChar/ipqcDefectChar";
	}
	
	@RequestMapping("/loadIpqcDefectChar")
	private void loadIpqcDefectChar(CommonVo vo,HttpServletResponse respon){
		int result = 0;
		String msg = null ;
		Map<String, Object> map = new HashMap<String, Object>();
		ChartObj chartsInfo = null;
		try {
			chartsInfo = createPerformanceData(vo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("chartsInfo", chartsInfo);
		map.put("result", result);
		map.put("msg", msg);
		
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	/**
	 * 巡检批次不良率时间序列
	 * @param vo
	 * @return
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	@RequestMapping("/ipqcScoreChar")
	private String ipqcScoreChar(Model model,CommonVo vo) throws UnsupportedEncodingException{
		vo.setFactory(decode(vo.getFactory()));
		vo.setShiftGroupTxt(decode(vo.getShiftGroupTxt()));
		model.addAttribute("vo",vo);
		return "qms/chart/ipqcChar/ipqcScoreChar";
	}
	

	@RequestMapping("/loadIpqcScoreChar")
	private void loadIpqcScoreChar(CommonVo vo,HttpServletResponse respon){
		int result = 0;
		String msg = null ;
		Map<String, Object> map = new HashMap<String, Object>();
		ChartObj chartsInfo = null;
		try {
			chartsInfo = createIpqcScoreData(vo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("chartsInfo", chartsInfo);
		map.put("result", result);
		map.put("msg", msg);
		
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	private ChartObj createPerformanceData(CommonVo vo) throws Exception{
//		int xCount = 20;
		int width = vo.getWidth();
		
		
		//获取班组绩效		
		List<CommonVo> defectList = commonSelectService.getIpqcDefectType(vo);

		
		//构造图表
		ChartObj chartVo = new ChartObj();
		
		chartVo.setChartHight(600);
		chartVo.setChartWidth(width);
		chartVo.setChartType("column");
          
		chartVo.setTitle("IPQC巡检不良现象统计图");
		chartVo.setxTitle("不良现象");
		chartVo.setyLeftTitle("频次");
		chartVo.setyLeftUnit("次");
		String[] seriesNames = {"频次"};
		return setData(defectList,chartVo,seriesNames);
	}
	
	private ChartObj setData(List<CommonVo> reList,ChartObj chartVo, String[] seriesNames){
		List<String> xvalues = new ArrayList<String>();
		List<List<Double>> yValues = new ArrayList<List<Double>>();
		
		List<Double> yvalue1 = new ArrayList<Double>();
		for (int i = 0; i < reList.size(); i++) {
			CommonVo tmpVo = reList.get(i);
			xvalues.add(tmpVo.getFaultTypeName());
			yvalue1.add(tmpVo.getDefectNum()+0.0);
			
		}
		yValues.add(yvalue1);	
		chartVo.setxValue(xvalues);
		chartVo.setSeriesNames(seriesNames);
		chartVo.setyValues(yValues);
		
		return chartVo;
		
	}
	
	@SuppressWarnings("static-access")
	private ChartObj createIpqcScoreData(CommonVo vo) throws Exception{
//		int xCount = 20;
		int width = vo.getWidth();
		vo.setIndexName(CODE1);
		vo.setStartTime(new DateEditor().getMonth(-12));
		vo.setEndTime(new DateEditor().getMonth(-1));
		List<CommonVo> defectList = commonSelectService.getIpqcScore(vo);

		
		//构造图表
		ChartObj chartVo = new ChartObj();
		
		chartVo.setChartHight(600);
		chartVo.setChartWidth(width);
		chartVo.setChartType("lines");
          
		chartVo.setTitle(vo.getShiftGroupTxt()+"巡检批次不良率得分序列图");
		chartVo.setxTitle("月份");
		chartVo.setyLeftTitle("分数");
		chartVo.setyLeftUnit("分");
		String[] seriesNames = {"实绩值","分数"};
		return setScoreData(defectList,chartVo,seriesNames);
	}
	
	private ChartObj setScoreData(List<CommonVo> reList,ChartObj chartVo, String[] seriesNames){
		List<String> xvalues = new ArrayList<String>();
		List<List<Double>> yValues = new ArrayList<List<Double>>();
		
		List<Double> yvalue1 = new ArrayList<Double>();
		List<Double> yvalue2 = new ArrayList<Double>();
		
		for (int i = 0; i < reList.size(); i++) {
			CommonVo tmpVo = reList.get(i);
			xvalues.add(tmpVo.getBaseMonth());
			yvalue1.add(tmpVo.getIndexActValue());
			yvalue2.add(tmpVo.getShiftGroupScore());
			
			
		}
		yValues.add(yvalue1);
		yValues.add(yvalue2);
		chartVo.setxValue(xvalues);
		chartVo.setSeriesNames(seriesNames);
		chartVo.setyValues(yValues);
		
		return chartVo;
		
	}
	
}
