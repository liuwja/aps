/*
 * @(#) LoginController.java 2014-8-8 下午04:21:37
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.peg.qms.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.peg.qms.controller.BaseController;
import com.peg.service.system.SysPrivilegeServiceI;

/**
 * TODO
 * <p>
 * @author Lin, 2014-8-8 下午04:21:37
 */
@Controller
@RequestMapping("system/privilege")
public class PrivilegeController extends BaseController
{
	@Autowired
	private SysPrivilegeServiceI sysPrivilegeService;
	
	@RequestMapping("/initPrivilege")
	public String initPrivilege()
	{
		return "error";
	}
	
	@RequestMapping("/updateAllPrivilege")
	public ModelAndView updateAllPrivilege()
	{
		try
		{
			logger.info("start init...");
			logger.info("finish init.");
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("初始化成功");
	}
	
	
}
