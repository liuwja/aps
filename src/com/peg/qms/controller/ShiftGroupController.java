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
import com.peg.model.ShiftGroup;
import com.peg.service.ShiftGroupServiceI;

@Controller
@RequestMapping("system/shiftGroup")
public class ShiftGroupController extends BaseController{
	@Autowired
	private ShiftGroupServiceI shiftGroupService;
	
	@RequestMapping("/shiftGrouplist")
	public String list(Model model,ShiftGroup shiftGroup,PageParameter page)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("code", shiftGroup.getCode());
		bs.put("area", shiftGroup.getArea());
		bs.put("name", shiftGroup.getName());
		
		List<ShiftGroup> list = shiftGroupService.getAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "qms/system/shiftGroup/shiftGrouplist";
		
	}
	
	@RequestMapping("/addShiftGroup")
	public String addShiftGroup()
	{
		return "qms/system/shiftGroup/addShiftGroup";
	}
	
	@RequestMapping("/editShiftGroup")
	public String editShiftGroup( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		ShiftGroup group = shiftGroupService.selectByPrimaryKey(id);
        String factory = group.getFactory();
        List<ShiftGroup> list = shiftGroupService.getArea(factory);
		model.addAttribute("group", group);
		model.addAttribute("area", list);
		return "qms/system/shiftGroup/edit";
	}
	
	
	@RequestMapping("/update")
	public ModelAndView update(ShiftGroup shiftGroup)
	{
		System.out.println(shiftGroup.getId());
		shiftGroupService.updateByPrimaryKeySelective(shiftGroup);
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/saveShiftGroup")
	public ModelAndView saveShiftGroup(ShiftGroup shiftGroup)
	{
		shiftGroup.setCreateUser(getCurrentUserName());
		shiftGroupService.insert(shiftGroup);
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(ShiftGroup shiftGroup)
	{
		shiftGroupService.deleteByPrimaryKey(shiftGroup.getId());
		return ajaxDoneSuccess("删除成功");	
	}	
	
	@RequestMapping("/getarea")
	public String getArea(Model model,ShiftGroup shiftGroup){
		String factory = shiftGroup.getFactory();
		List<ShiftGroup> list = shiftGroupService.getArea(factory);
		
		model.addAttribute("list",list);
		return "qms/system/shiftGroup/area";
	}
	
}
