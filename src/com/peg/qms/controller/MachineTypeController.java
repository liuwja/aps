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
import com.peg.service.MachineTypeServiceI;

@Controller
@RequestMapping("base/machineType")
public class MachineTypeController extends BaseController{

	@Autowired
	private MachineTypeServiceI machineTypeService;
	
	@RequestMapping("/machineType")
	public String list(Model model,MachineType machineType,PageParameter page)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);

		bs.put("machineType", machineType.getMachineType());
		
		
		
		
		List<MachineType> list = machineTypeService.getAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "qms/base/machineType/machineType";
		
	}
	
	@RequestMapping("/addMachine")
	public String addMachine()
	{
		return "qms/base/machineType/addMachine";
	}
	
	@RequestMapping("/editMachine")
	public String editMachine( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		MachineType machineType = machineTypeService.selectByPrimaryKey(id);		
		model.addAttribute("machineType", machineType);
		return "qms/base/machineType/editMachine";
	}
	
	
	@RequestMapping("/updateMachineType")
	public ModelAndView updateMachineType(MachineType machineType)
	{
		machineType.setUpdateUser(getCurrentUserName());
		machineTypeService.updateByPrimaryKeySelective(machineType);
		
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/saveMachineType")
	public ModelAndView saveMachineType(MachineType machineType)
	{	
		machineType.setCreateUser(getCurrentUserName());
		machineTypeService.insert(machineType);
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(MachineType machineType)
	{
		machineTypeService.deleteByPrimaryKey(machineType.getId());
		return ajaxDoneSuccess("删除成功");	
	}	
}
