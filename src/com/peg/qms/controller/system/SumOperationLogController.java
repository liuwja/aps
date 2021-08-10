package com.peg.qms.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.system.SumOperationLog;
import com.peg.qms.controller.BaseController;
import com.peg.service.system.SumOperationLogServiceI;


@Controller
@RequestMapping("system/sumOperationLog")
public class SumOperationLogController extends BaseController{

	@Autowired
	private SumOperationLogServiceI sumOperationLogService;
	
	
	
	@RequestMapping("/sumOperationLog")
	public String sumOperationLog(Model model,SumOperationLog sumOperationLog,PageParameter page)
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		
		bs.put("name", sumOperationLog.getName());
		bs.put("rusult", sumOperationLog.getRusult());
		bs.put("operationType", sumOperationLog.getOperationType());
		
		
		
		
		List<SumOperationLog> list = sumOperationLogService.getAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "/qms/system/sumOperationLog/sumOperationLog";
		
	}
}
