package com.peg.qms.controller.bph;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.bph.MesDataSum;
import com.peg.qms.controller.BaseController;
import com.peg.service.bph.MesDataSumServiceI;

@Controller
@RequestMapping("system/mesDataSum")
public class MesDataSumController extends BaseController{

	@Autowired
	private MesDataSumServiceI mesDataSumService;
	
	@RequestMapping("/list")
	public String list(Model model,MesDataSum mesDataSum, PageParameter page){
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("factory", mesDataSum.getBaseFactory());
		bs.put("area", mesDataSum.getBaseArea());
		bs.put("shiftGroup", mesDataSum.getBaseGroup());
		bs.put("monthly", mesDataSum.getQueryMonth());
		LoadFAPG(model, mesDataSum);
		List<MesDataSum> list = mesDataSumService.findAllByPage(bs);
		
		model.addAttribute("list", list);
		model.addAttribute("vo", mesDataSum);
		model.addAttribute("page", page);
		return "qms/bph/groupPerformance/mesDataSumList";
	}
}
