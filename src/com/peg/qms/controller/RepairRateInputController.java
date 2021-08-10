package com.peg.qms.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.MachineType;
import com.peg.model.RepairRate;
import com.peg.model.RepairRateInput;
import com.peg.model.RepairTotalrateInput;
import com.peg.model.bph.CheckItem;
import com.peg.service.MachineTypeServiceI;
import com.peg.service.RepairRateInputServiceI;
import com.peg.service.RepairRateServiceI;
import com.peg.service.RepairTotalrateInputServiceI;
import com.peg.web.util.WebUtil;

@Controller
@RequestMapping("base/repairRateInput")
public class RepairRateInputController extends BaseController{

	@Autowired
	private RepairRateInputServiceI repairRateInputService;
	
	@Autowired
	private MachineTypeServiceI machineTypeService;
	
	@Autowired
	private RepairTotalrateInputServiceI repairTotalrateInputService;
	
	@Autowired 
	private RepairRateServiceI repairRateService;
	
	@RequestMapping("/list")
	public String list(Model model,RepairRateInput input,PageParameter page) throws Exception
	{
		String queryMonth = DateFormatUtils.format(new Date(),"yyyy-MM");
		queryMonth = WebUtil.rebackMonths(queryMonth, -1);
		String endMonth = WebUtil.rebackMonths(queryMonth, -11);
		List<String> monthList = WebUtil.getDateList(queryMonth, 12);
		Collections.sort(monthList,new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				int startMon = NumberUtils.toInt(o1.replace("-", ""));
            	int endMon = NumberUtils.toInt(o2.replace("-", ""));
            	if(startMon > endMon){
            		return 1;
            	}
				return -1;
			}});
		Map<String, List<RepairRateInput>> resultMap = init(monthList,endMonth,queryMonth);
		model.addAttribute("resultMap", resultMap);
		model.addAttribute("monthList", monthList);
		return "qms/base/repairRateInput/list";
		
	}
	
	@RequestMapping("/homeList")
	public String homeList(Model model,RepairRateInput input,PageParameter page) throws Exception
	{
		String queryMonth = DateFormatUtils.format(new Date(),"yyyy-MM");
		queryMonth = WebUtil.rebackMonths(queryMonth, -1);
		String endMonth = WebUtil.rebackMonths(queryMonth, -11);
		List<String> monthList = WebUtil.getDateList(queryMonth, 12);
		Collections.sort(monthList,new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				int startMon = NumberUtils.toInt(o1.replace("-", ""));
            	int endMon = NumberUtils.toInt(o2.replace("-", ""));
            	if(startMon > endMon){
            		return 1;
            	}
				return -1;
			}});
		Map<String, List<RepairRateInput>> resultMap = init(monthList,endMonth,queryMonth);
		model.addAttribute("resultMap", resultMap);
		model.addAttribute("monthList", monthList);
		return "qms/base/repairRateInput/homeList";
		
	}
	
	
	@RequestMapping("/add")
	public String add(Model model,RepairRateInput input) throws Exception
	{
		String queryMonth = DateFormatUtils.format(new Date(),"yyyy-MM");
		queryMonth = WebUtil.rebackMonths(queryMonth, -1);
		String endMonth = WebUtil.rebackMonths(queryMonth, -11);
		
		List<String> monthList = WebUtil.getDateList(queryMonth, 12);
		Collections.sort(monthList,new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				int startMon = NumberUtils.toInt(o1.replace("-", ""));
            	int endMon = NumberUtils.toInt(o2.replace("-", ""));
            	if(startMon > endMon){
            		return 1;
            	}
				return -1;
			}});
		Map<String,  List<RepairRateInput>> resultMap = init(monthList,endMonth,queryMonth);
//		System.out.println(resultMap.entrySet().iterator().next());
		model.addAttribute("resultMap", resultMap);
		model.addAttribute("monthList", monthList);
		return "qms/base/repairRateInput/add";
	}
	
	@RequestMapping("/edit")
	public String edit( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		return "qms/system/item/edit";
	}
	
	
	@RequestMapping("/update")
	public ModelAndView update(RepairRateInput input)
	{
		
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/save")
	public ModelAndView save(RepairRateInput input) throws Exception
	{
		String queryMonth = DateFormatUtils.format(new Date(),"yyyy-MM");
		queryMonth = WebUtil.rebackMonths(queryMonth, -1);
		String endMonth = WebUtil.rebackMonths(queryMonth, -11);
		List<RepairRateInput> repairList = repairRateInputService.getAllInput(endMonth,queryMonth); 
		List<RepairTotalrateInput> totalList = repairTotalrateInputService.getTotalAll(queryMonth);
		try{
			List<RepairTotalrateInput> total = input.getTotal();
			List<RepairRateInput> list = input.getType();
			for(RepairTotalrateInput to : total){
				if(!iscontains(totalList, to)){
					to.setCreateUser(getCurrentUserName());
					repairTotalrateInputService.insert(to);
				}else{
					repairTotalrateInputService.updateByPrimaryKeySelective(to);
				}
				
			}
			for(RepairRateInput rate : list){
				if(!iscontains(repairList, rate)){
					rate.setCreateUser(getCurrentUserName());
					repairRateInputService.insert(rate);
				}else{
					repairRateInputService.updateByPrimaryKeySelective(rate);
				}
				
			}
		}catch(Exception e){
			logger.info(e.getMessage(), e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(CheckItem checkItem)
	{
		return ajaxDoneSuccess("删除成功");	
	}
	
	
	public Map<String, List<String>> initData(
			List<String> vo) throws Exception
		{
		    List<MachineType> typeList = machineTypeService.getAll(); 
		    Map<String, List<String>> map  = new TreeMap<String,  List<String>>();
		    for(MachineType type : typeList){
		    	String machine = type.getMachineType();
		    	if(!map.containsKey(machine)){
		    		map.put(machine, vo);
		    	}else{
		    		map.get(machine).add("");
		    	}
		    }
			return map;
		}
	
	public Map<String, List<RepairRateInput>> init(
			List<String> vo,String startMonth,String endMonth) throws Exception
		{
		    List<RepairRate> rateList = repairRateService.findAll(startMonth,endMonth);
		
		    List<MachineType> typeList = machineTypeService.getAll(); 
		    List<RepairRateInput> ilist = repairRateInputService.getAllInput(startMonth, endMonth);
		    List<RepairTotalrateInput> tlist =  repairTotalrateInputService.getTotalAll(endMonth);
		    List<RepairRateInput> nilist =  new ArrayList<RepairRateInput>();
		    if(!tlist.isEmpty()){
		    	for(RepairTotalrateInput to : tlist){
		    		RepairRateInput repair = new RepairRateInput();
		    		repair.setId(to.getId());
		    		repair.setTypeCategory(to.getTypeCategory());
		    		repair.setRepairMonth(to.getInsertMonth());
		    		repair.setRepairRate(to.getRepairRate());
		    		repair.setTotalId(new Long(0));
		    		nilist.add(repair);
		    	}
		    	ilist.addAll(nilist);
		    }else{
		    	 int i=1;
		    	  for(String mon : vo){
				    	if(!iscontains(ilist, mon)){
				    		i++;
				    		for(MachineType ma : typeList){
				    			RepairRateInput repair = new RepairRateInput();
				    			repair.setTypeCategory(ma.getMachineType());
				    			repair.setRepairMonth(mon);
				    			repair.setTotalId(new Long(i));
				    			nilist.add(repair);
				    		}
				    	}
				    }
		    	  for(MachineType ma : typeList){
		    			RepairRateInput repair = new RepairRateInput();
		    			repair.setTypeCategory(ma.getMachineType());
		    			repair.setRepairMonth(endMonth);
		    			repair.setTotalId(new Long(0));
		    			nilist.add(repair);
		    		}
				    ilist.addAll(nilist);
		    }
		    Collections.sort(ilist, new Comparator<RepairRateInput>() {

				@Override
				public int compare(RepairRateInput o1, RepairRateInput o2) {
					String s1 = o1.getRepairMonth().replace("-", "");
                	String s2 = o2.getRepairMonth().replace("-", "");
                	if(o1.getTotalId()==null){
                		o1.setTotalId(o1.getId());
                	}
                	if(o2.getTotalId()==null){
                		o2.setTotalId(o2.getId());
                	}
                	int startMon = NumberUtils.toInt(s1);
                	int endMon = NumberUtils.toInt(s2);
                	if(startMon < endMon){
                		return -1;
                	}else if(startMon == endMon){
                		if(o1.getTotalId()<o2.getTotalId()){
                			return 1;
                		}else{
                			return -1;
                		}
                	}else {
                		return 1;
                	}
				}
			});
		    //比较基准值
		    List<RepairRateInput> inlist = initFlag(ilist,rateList);
		    
		    Map<String, List<RepairRateInput>> map  = new TreeMap<String,  List<RepairRateInput>>();
		    List<RepairRateInput> input = null;
		    for(RepairRateInput rate: inlist){
		    	String machineType = rate.getTypeCategory();
		    	input = new ArrayList<RepairRateInput>();
		    	if(!map.containsKey(machineType)){
		    		map.put(machineType, input);
		    	}
		    	map.get(machineType).add(rate);
		    }
			return map;
		}
	
	private boolean iscontains(List<RepairRateInput> list,String str){
		for(RepairRateInput input : list){
			if(input.getRepairMonth().equals(str)){
				return true;
			}
		}
		return false;
	}
	
	private boolean iscontains(List<RepairRateInput> list,RepairRateInput ivo){
		for(RepairRateInput input : list){
			if(input.getId().equals(ivo.getId())&&
					input.getTypeCategory().equals(ivo.getTypeCategory())&&
					input.getRepairMonth().equals(ivo.getRepairMonth())){
				return true;
			}
		}
		return false;
	}
	
	private boolean iscontains(List<RepairTotalrateInput> list,RepairTotalrateInput ivo){
		for(RepairTotalrateInput input : list){
			if(input.getId().equals(ivo.getId())&&
					input.getTypeCategory().equals(ivo.getTypeCategory())&&
					input.getInsertMonth().equals(ivo.getInsertMonth())){
				return true;
			}
		}
		return false;
	}
	
	
	private List<RepairRateInput> initFlag(List<RepairRateInput> input,List<RepairRate> typeList){
		for(RepairRateInput repair : input ){
			for(RepairRate type : typeList){
				if(repair.getTypeCategory().equals(type.getMachineType())&&
						repair.getRepairMonth().equals(type.getYearMon())){
					if(repair.getRepairRate().doubleValue() > type.getHundredRepairRate()*100){
						repair.setFlag(1);
					}else{
						repair.setFlag(0);
					}
				}
			}
		}
		return input;
	}
}
