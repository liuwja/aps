package com.peg.qms.controller.jxmb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.jxmb.PerDeparment;
import com.peg.model.jxmb.PerformanceCheck;
import com.peg.qms.controller.BaseController;
import com.peg.service.CommonServiceI;
import com.peg.service.jxmb.DeparmentServicel;
import com.peg.service.jxmb.PerformanceCheckServicel;

@Controller
@RequestMapping("per/department")
public class DeparmentController  extends BaseController{

	@Autowired
	private DeparmentServicel dtService;
	
	@Autowired
	private CommonServiceI commonService;
	
	@Autowired
	private PerformanceCheckServicel percheck;
	
	@RequestMapping("/list")
	public String list(Model model ,PerDeparment dt,PageParameter page,PerformanceCheck check){
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("factoryNumber", dt.getFactoryNumber());
		bs.put("departmentNumber", dt.getDepartmentNumber());
		List<PerDeparment>list = dtService.getAllByPage(bs, true);
		model.addAttribute("list", list);
		model.addAttribute("dt", dt);
    	model.addAttribute("page", page);
    	model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/department/list";
	}
	
	@RequestMapping("/add")
	public String addCheckItem(Model model){
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/department/add";
	}
	
	@RequestMapping("/save")
	public ModelAndView saveCheckItem(PerDeparment dt ){
		try {
			if(dt.getId() == null || dt.getId() <= 0) {
				dtService.insert(dt);
			} else {
				dtService.update(dt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxDoneError("更新失败");
		}
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/edit")
	public String edit(@RequestParam(value="id",required=false)Long id,Model model){
		PerDeparment dt =dtService.selectByPrimaryKey(id);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		model.addAttribute("vo",dt);	
		return "qms/bph/base/department/edit";
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(PerDeparment DT){
		dtService.deleteByPrimaryKey(DT.getId());
		return ajaxDoneSuccess("删除成功");
	}
}
