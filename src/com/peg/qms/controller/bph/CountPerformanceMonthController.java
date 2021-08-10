package com.peg.qms.controller.bph;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.bph.CountPerformanceMonth;
import com.peg.qms.controller.BaseController;
import com.peg.service.bph.CountPerformanceMonthServiceI;

@Controller
@RequestMapping("system/countPerformanceMonth")
public class CountPerformanceMonthController extends BaseController {

	@Autowired
	private CountPerformanceMonthServiceI countPerformanceMonthService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	
	@RequestMapping("/list")
	public String list(Model model,CountPerformanceMonth countPerformanceMonth,PageParameter page)
	{		
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("factory", countPerformanceMonth.getFactory());
		bs.put("checkIndexName", countPerformanceMonth.getCheckIndexName());
		bs.put("queryMonth", countPerformanceMonth.getQueryMonth());

		List<CountPerformanceMonth> list = countPerformanceMonthService.getAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "qms/bph/base/countPerformanceMonth/list";
		
	}
	
	@RequestMapping("/add")
	public String add(Model model,CountPerformanceMonth countPerformanceMonth){
		return "qms/bph/base/countPerformanceMonth/add";
	}
	
	@RequestMapping("/edit")
	public String edit( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		CountPerformanceMonth countPerformanceMonth = countPerformanceMonthService.selectByPrimaryKey(id);
        
		model.addAttribute("vo", countPerformanceMonth);

		return "qms/bph/base/countPerformanceMonth/edit";
	}
	
//	@RequestMapping("/save")
//	public ModelAndView save(CountPerformanceMonth countPerformanceMonth) throws ParseException
//	{
//		
//		String factory = countPerformanceMonth.getFactory();
//		String checkIndexName = countPerformanceMonth.getCheckIndexName();
//		Map classMap = new HashMap();
//		classMap.put("items", CommonForm.class);
//		CountPerformanceMonth ap = (CountPerformanceMonth)JSONObject.toBean(JSONObject.fromObject(countPerformanceMonth.getFormJsonValue()),CountPerformanceMonth.class , classMap);
//		List<CommonForm> items = ap.getItems();
//		for(CommonForm from : items){
//			CountPerformanceMonth ref = new CountPerformanceMonth();
//			ref.setFactory(factory);
//			ref.setCheckIndexName(checkIndexName);
//			ref.setQueryMonth(from.getQueryMonth());
//			ref.setTargetValue(from.getTargetValue());
//			List<CountPerformanceMonth> list= countPerformanceMonthService.selectCommonMonth(ref);
//			if(!list.isEmpty()){
//				return ajaxDoneError("重复月份:"+from.getQueryMonth());				
//			}	
//		}
//		for(CommonForm from : items){
//			CountPerformanceMonth ref = new CountPerformanceMonth();
//			ref.setFactory(factory);
//			ref.setCheckIndexName(checkIndexName);
//			ref.setCheckMonth(sdf.parse(from.getQueryMonth()));
//			ref.setTargetValue(from.getTargetValue());			
//			try{			
//				countPerformanceMonthService.insert(ref);
//							
//				}catch(Exception e){
//					logger.error(e.getMessage(), e);
//					return ajaxDoneError(e.getMessage());
//				}
//		}
//	
//		return ajaxDoneSuccess("保存成功");
//		
//	}
	
	
	@RequestMapping("/save")
	public ModelAndView save(CountPerformanceMonth countPerformanceMonth) throws ParseException
	{
		
		String factory = countPerformanceMonth.getFactory();
		String checkIndexName = countPerformanceMonth.getCheckIndexName();
	    String [] months = countPerformanceMonth.getQueryMonth().split(",");
	    String [] values = countPerformanceMonth.getValues().split(",");
	    if(months.length != values.length){
	    	return ajaxDoneError("月份与月度目标不符");
	    }else{
	    	for(String mon : months){
	    		int yue =Integer.parseInt(mon.substring(5)) ;
	    		for(String val :values){
	    			int va = Integer.parseInt(val.substring(0,val.indexOf("-")));
	    			System.out.println(va);
	    			if(yue==va){
	    				CountPerformanceMonth ref = new CountPerformanceMonth();
	    				ref.setFactory(factory);
	    				ref.setCheckIndexName(checkIndexName);
	    				ref.setQueryMonth(mon);
	    				ref.setTargetValue( new BigDecimal(val.substring(val.indexOf("-"))) );
	    				List<CountPerformanceMonth> list= countPerformanceMonthService.selectCommonMonth(ref);
	    				if(!list.isEmpty()){
	    					return ajaxDoneError("重复月份:"+mon);				
	    				}	
	    				
	    			}
	    		}
	    	}
	    }
	    for(String mon : months){
    		int yue =Integer.parseInt(mon.substring(5)) ;
    		for(String val :values){
    			int va = Integer.parseInt(val.substring(0,val.indexOf("-")));
    			System.out.println(va);
    			if(yue==va){
    				CountPerformanceMonth ref = new CountPerformanceMonth();
    				ref.setFactory(factory);
    				ref.setCheckIndexName(checkIndexName);
    				ref.setCheckMonth(sdf.parse(mon));
    				ref.setTargetValue( new BigDecimal(val.substring(val.indexOf("-")+1)) );
    				try{			
    					countPerformanceMonthService.insert(ref);
    								
    					}catch(Exception e){
    						logger.error(e.getMessage(), e);
    						return ajaxDoneError(e.getMessage());
    					}
    			}
    				
    			}
    		}
		return ajaxDoneSuccess("保存成功");
		
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(CountPerformanceMonth countPerformanceMonth)
	{
		countPerformanceMonthService.deleteByPrimaryKey(countPerformanceMonth.getId());
		return ajaxDoneSuccess("删除成功");	
	}
	
	@RequestMapping("/update")
	public ModelAndView update(CountPerformanceMonth countPerformanceMonth){
		try {
			countPerformanceMonth.setCheckMonth(sdf.parse(countPerformanceMonth.getQueryMonth()));
			countPerformanceMonthService.updateByPrimaryKey(countPerformanceMonth);
		} catch (ParseException e) {
			return ajaxDoneError("跟新失败");
		}
		
		return ajaxDoneSuccess("更新成功");	
	}
}
