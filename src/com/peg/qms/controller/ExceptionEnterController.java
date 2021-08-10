package com.peg.qms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ExceptionEnter;
import com.peg.model.ShiftGroup;
import com.peg.service.CommonSelectedBoxService;
import com.peg.service.ExceptionEnterServiceI;
import com.peg.service.ShiftGroupServiceI;

@Controller
@RequestMapping("system/exceptionEnter")
public class ExceptionEnterController extends BaseController{
	
	@Autowired
	private ExceptionEnterServiceI exceptionEnterService;
	
	@Autowired
	private CommonSelectedBoxService comSelBoxService;
	
	@Autowired
	private ShiftGroupServiceI shiftGroupService;
	
	@RequestMapping("/list")
	public String list(Model model,ExceptionEnter exceptionEnter,PageParameter page)
	{
	
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("factory", exceptionEnter.getBaseFactory());
		bs.put("area", exceptionEnter.getBaseArea());
		bs.put("groupName", exceptionEnter.getBaseGroup());
     
		LoadFAPG(model, exceptionEnter);
		List<ExceptionEnter> list = exceptionEnterService.getAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", exceptionEnter);
		return "qms/bph/base/exceptionEnter/list";
		
	}
	
	@RequestMapping("/add")
	public String add(Model model, ExceptionEnter exceptionEnter)
	{
		LoadFAPG(model, exceptionEnter);
		return "qms/bph/base/exceptionEnter/add";
	}
	
	@RequestMapping("/edit")
	public String editCheckItem( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		
		ExceptionEnter enter = exceptionEnterService.selectByPrimaryKey(id);
		enter.setBaseFactory(enter.getFactory());
		enter.setBaseArea(enter.getArea());
		enter.setBaseGroup(enter.getGroupName());
        LoadFAPG(model, enter);
		model.addAttribute("vo", enter);
		return "qms/bph/base/exceptionEnter/edit";
	}
	
	
	@RequestMapping("/update")
	public ModelAndView updateCheckItem(ExceptionEnter exceptionEnter)
	{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			exceptionEnter.setLastUpdateUser(getCurrentUserName());
			exceptionEnter.setLastUpdateTime(Calendar.getInstance().getTime());
			exceptionEnter.setFactory(exceptionEnter.getBaseFactory());
			exceptionEnter.setArea(exceptionEnter.getBaseArea());
			exceptionEnter.setGroupName(exceptionEnter.getBaseGroup());
			exceptionEnter.setOccurTime(sdf.parse(exceptionEnter.getStartTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		exceptionEnterService.updateByPrimaryKeySelective(exceptionEnter);
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/save")
	public ModelAndView saveCheckItem(ExceptionEnter exceptionEnter)
	{
		try {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    exceptionEnter.setCreateUser(getCurrentUserName());
			exceptionEnter.setOccurTime(sdf.parse(exceptionEnter.getStartTime()));
			exceptionEnter.setFactory(exceptionEnter.getBaseFactory());
			exceptionEnter.setArea(exceptionEnter.getBaseArea());
			exceptionEnter.setGroupName(exceptionEnter.getBaseGroup());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    exceptionEnterService.insert(exceptionEnter);
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(ExceptionEnter exceptionEnter)
	{
		exceptionEnterService.deleteByPrimaryKey(exceptionEnter.getId());
		return ajaxDoneSuccess("删除成功");	
	}


	public void getShiftGroup(Model model,String factory,String area){

		List<ShiftGroup> list = shiftGroupService.getShiftGroupByFoArea(factory, area);
		model.addAttribute("groupList", list);
	}

}
