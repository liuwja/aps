package com.peg.qms.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.dao.system.SysLoginHistoryMapper;
import com.peg.model.system.SysLoginHistory;
import com.peg.qms.controller.BaseController;

@Controller
@RequestMapping("system/sysLoginHistory")
public class SysLoginHistoryController extends BaseController{

	@Autowired
	private SysLoginHistoryMapper sysLoginHistoryMapper;
	
	@RequestMapping("/list")
	public String list(Model model,PageParameter page, 
			@RequestParam(value = "username", required = false) String username, 
			@RequestParam(value = "userDesc", required = false) String userDesc, 
			@RequestParam(value = "startDate", required = false) String date){
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("username", username);
		bs.put("name", userDesc);
		bs.put("startDate", date);
		List<SysLoginHistory> list = sysLoginHistoryMapper.findALLByPage(bs);
		
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "/qms/system/loginhistory/list";
	}
}
