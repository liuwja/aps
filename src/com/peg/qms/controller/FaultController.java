package com.peg.qms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.FaultReason;
import com.peg.model.FaultType;
import com.peg.service.FaultReasonServiceI;
import com.peg.service.FaultTypeServiceI;


@Controller
@RequestMapping("base/fault")
public class FaultController extends BaseController{
	@Autowired
	private FaultReasonServiceI faultReasonService;
	@Autowired
	private FaultTypeServiceI faultTypeService;
	
	
	@RequestMapping("/faultReason")
	public String faultReason(Model model,FaultReason faultReason,PageParameter page)
	{
		
		List<FaultReason> list = faultReasonService.findAllByPage(faultReason, page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "/qms/Fault/FaultReason";
		
	}
	@RequestMapping("/faultType")
	public String faultType(Model model,FaultType faultType,PageParameter page)
	{
		List<FaultType> list = faultTypeService.findAllByPage(faultType, page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", faultType);
		return "/qms/Fault/FaultType";
		
	}
	
}
