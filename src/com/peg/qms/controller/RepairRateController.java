package com.peg.qms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.MachineType;
import com.peg.model.RepairRate;
import com.peg.service.MachineTypeServiceI;
import com.peg.service.RepairRateServiceI;


@Controller
@RequestMapping("base/repairRate")
public class RepairRateController extends BaseController{
	@Autowired
	private RepairRateServiceI repairRateService;
	
	@Autowired
	private MachineTypeServiceI machineTypeService;
	
	@RequestMapping("/repairRateList")
	public String repairRateList(Model model,RepairRate repairRate,
			PageParameter page, @RequestParam(value = "queryYearMonyh", required = false) String queryYearMonyh)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		List<MachineType> productTypeList = machineTypeService.getAll();
		bs.put("machineType", repairRate.getMachineType());
		bs.put("yearMon", queryYearMonyh);
		
		List<RepairRate> list = repairRateService.getAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("productTypeList", productTypeList);
		model.addAttribute("page", page);
		return "qms/base/repairRate/repairRateList";
		
	}
	
	@RequestMapping("/addMachine")
	public String addMachine(Model model)
	{
		List<MachineType> productTypeList = machineTypeService.getAll();
		model.addAttribute("productTypeList", productTypeList);
		return "qms/base/repairRate/addMachine";
	}
	
	@RequestMapping("/editMachine")
	public String editMachine( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		RepairRate repairRate = repairRateService.selectByPrimaryKey(id);		
		model.addAttribute("repairRate", repairRate);
		return "qms/base/repairRate/editMachine";
	}
	
	
	@RequestMapping("/updateMachine")
	public ModelAndView updateMachine(RepairRate repairRate)
	{
		repairRateService.updateByPrimaryKeySelective(repairRate);
		
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/saveMachine")
	public ModelAndView saveMachine(RepairRate repairRate)
	{
		repairRate.setUserName(getCurrentUserName());
		repairRateService.insert(repairRate);
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(RepairRate repairRate)
	{
		repairRateService.deleteByPrimaryKey(repairRate.getId());
		return ajaxDoneSuccess("删除成功");	
	}	
}
