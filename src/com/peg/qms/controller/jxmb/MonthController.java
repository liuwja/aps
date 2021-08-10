package com.peg.qms.controller.jxmb;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.jxmb.PerGroup;
import com.peg.model.jxmb.PerItem;
import com.peg.model.jxmb.PerSetup;
import com.peg.qms.controller.BaseController;
import com.peg.service.CommonServiceI;
import com.peg.service.jxmb.PerGoupServicel;
import com.peg.service.jxmb.PerIndexServicel;
import com.peg.service.jxmb.PerItemServicel;

@Controller
@RequestMapping("per/index")
public class MonthController extends BaseController {
	
	@Autowired
	private PerIndexServicel perindexService;
	
	@Autowired
	private PerGoupServicel perGoupService;
	
	@Autowired
	private PerItemServicel peritemService;
	
	@Autowired
	private CommonServiceI commonService;
	
	@RequestMapping("/list")
	public String list(Model model,PerGroup pergroup,PageParameter page){
		BaseSearch bs=new BaseSearch();
		bs.setPage(page);
		bs.put("chekyear", pergroup.getYear());
		bs.put("department", pergroup.getDepartment());
		bs.put("factoryNumber", pergroup.getFactoryNumber());
		List<PerGroup>list=perGoupService.getIndexAllByPage(bs);
		bs=new BaseSearch();
		bs.setPage(page); 
		List<PerGroup>list2=perGoupService.getIndexAllByPage(bs);
		System.out.println("list.size:"+list.size());
		model.addAttribute("list",list);
		model.addAttribute("list2",list2);
		model.addAttribute("page",page);
		model.addAttribute("vo",pergroup);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/jxmb/list";
	}
	@RequestMapping("/add")
	public String addCheckItem(Model model,@RequestParam(value="id" ,required=false)Long id,
			@RequestParam(value="itemKey",required=false)Long itemKey){
		PerGroup group=perGoupService.selectByPrimaryKey(id);
		PerItem item=peritemService.selectByPrimaryKey(itemKey);
		List<PerSetup>list=perindexService.getIndexByItemKey(itemKey);
		model.addAttribute("vo",group);
		model.addAttribute("item",item);
		model.addAttribute("list",list);
		return "qms/bph/base/jxmb/add";
	}
	@RequestMapping("/edit")
	public String editCheckItem(@RequestParam(value="id",required=false)Long id,
			@RequestParam(value="itemKey",required=false)Long itemKey,
			@RequestParam(value="indexKey",required=false)Long indexKey,Model model){
			PerGroup group=perGoupService.selectByPrimaryKey(id);
			PerItem item=peritemService.selectByPrimaryKey(itemKey);
			PerSetup index=perindexService.selectByPrimaryKey(indexKey);
			model.addAttribute("group",group);
			model.addAttribute("item",item);
			model.addAttribute("index",index);
			return "qms/bph/base/jxmb/edit";
	}
	@RequestMapping("/update")
	public ModelAndView updateCheckItem(PerSetup index){
		try {
			index.setLastupdatetime(new Date());
			index.setLastupdateuser(getCurrentUserName());
			perindexService.updateByPrimaryKeySelective(index);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/save")
	public ModelAndView saveCheckItem(PerItem item ){
		Date date=new Date();
		try {
			Long itemKey =item.getItemKey();
			List<PerSetup> list=item.getUiindexs();
			for(PerSetup index :list){
				index.setItemKey(itemKey);
				index.setRecord(getCurrentUserName());
				index.setRecordtime(date);
				index.setMonth(item.getMonth2());
				perindexService.insert(index);
			}
		} catch (Exception e) {
		logger.error(e.getMessage(),e);
		return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("保存成功");
	}
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value="indexKey",required=false)Long indexKey){
		
		try {
			perindexService.deleteByPrimaryKey(indexKey);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("删除成功");
	}
	
//	@RequestMapping("/save2")
//	public ModelAndView saveCheckItem2(PerItem item) throws ParseException{
//		Date date=new Date();
//		
//		
//	    String [] months = item.getMonth2().split(",");
//	    String [] values = item.getValues().split(",");
//	    if(months.length != values.length){
//	    	return ajaxDoneError("月份与月度目标不符");
//	    }else{
//	    	for(String mon : months){
//	    		int yue =Integer.parseInt(mon.substring(5)) ;
//	    		for(String val :values){
//	    			int va = Integer.parseInt(val.substring(0,val.indexOf("-")));
//	    			System.out.println(va);
//	    			if(yue==va){
//	    				PerItem ref = new PerItem();
//	    				ref.setQueryMonth(mon);
//	    				ref.setTargetValue( new BigDecimal(val.substring(val.indexOf("-"))) );
//	    				List<CountPerformanceMonth> list= countPerformanceMonthService.selectCommonMonth(ref);
//	    				if(!list.isEmpty()){
//	    					return ajaxDoneError("重复月份:"+mon);				
//	    				}	
//	    				
//	    			}
//	    		}
//	    	}
//	    }
//	    for(String mon : months){
//    		int yue =Integer.parseInt(mon.substring(5)) ;
//    		for(String val :values){
//    			int va = Integer.parseInt(val.substring(0,val.indexOf("-")));
//    			System.out.println(va);
//    			if(yue==va){
//    				CountPerformanceMonth ref = new CountPerformanceMonth();
//    				ref.setFactory(factory);
//    				ref.setCheckIndexName(checkIndexName);
//    				ref.setCheckMonth(sdf.parse(mon));
//    				ref.setTargetValue( new BigDecimal(val.substring(val.indexOf("-")+1)) );
//    				try{			
//    					countPerformanceMonthService.insert(ref);
//    								
//    					}catch(Exception e){
//    						logger.error(e.getMessage(), e);
//    						return ajaxDoneError(e.getMessage());
//    					}
//    			}
//    				
//    			}
//    		}
//		return ajaxDoneSuccess("保存成功");
//		
//	}
}
