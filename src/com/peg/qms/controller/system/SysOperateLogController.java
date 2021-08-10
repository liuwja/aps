package com.peg.qms.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.system.SysOperateLog;
import com.peg.qms.controller.BaseController;
import com.peg.service.system.SysOperateLogServiceI;

/**
 * 系统操作日志
 * @author fjt
 *
 */
@Controller
@RequestMapping("system/sysOperateLog")
public class SysOperateLogController extends BaseController{

	@Autowired
	private SysOperateLogServiceI sysOperatorLogService;
	
	@RequestMapping("/list")
	public String list(Model model,PageParameter page,SysOperateLog log)
	{
		List<SysOperateLog> list = sysOperatorLogService.findAllByPage(page, log);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "/qms/system/sysOperateLog/sysOperateLog";
	}
}
