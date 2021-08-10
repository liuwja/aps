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
import com.peg.model.Manage;
import com.peg.service.ManageServiceI;
@Controller
@RequestMapping("system/sysconfig")
public class ManageController extends BaseController{
	@Autowired
	private ManageServiceI manageService;
	
	@RequestMapping("/managelist")
	public String list(Model model,Manage manage,PageParameter page)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);

		bs.put("code", manage.getCode());
		bs.put("codeName", manage.getCodeName());
		bs.put("codeValue", manage.getCodeValue());
		
		
		
		List<Manage> list = manageService.getAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "qms/system/sysConfig/managelist";
		
	}
	
	@RequestMapping("/addManage")
	public String addManage()
	{
		return "qms/system/sysConfig/addManage";
	}
	
	@RequestMapping("/editManage")
	public String editManage( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		Manage manage = manageService.selectByPrimaryKey(id);		
		model.addAttribute("manage", manage);
		return "qms/system/sysConfig/editManage";
	}
	
	
	@RequestMapping("/updateManage")
	public ModelAndView updateFactory(Manage manage)
	{
		manage.setUpdateUser(getCurrentUserName());
		manageService.updateByPrimaryKeySelective(manage);
		
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/saveManage")
	public ModelAndView saveManage(Manage manage)
	{
		manage.setCreateUser(getCurrentUserName());
		manageService.insert(manage);
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(Manage manage)
	{
		manageService.deleteByPrimaryKey(manage.getId());
		return ajaxDoneSuccess("删除成功");	
	}	
}
