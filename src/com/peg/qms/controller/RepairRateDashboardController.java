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
import com.peg.model.RepairRateDashboard;
import com.peg.service.RepairRateDashboardServiceI;

@Controller
@RequestMapping("base/repairRateDashboard")
public class RepairRateDashboardController extends BaseController {
	
	@Autowired
	private RepairRateDashboardServiceI repairRateDashboardService;
	
	@RequestMapping("/list")
	public String list(Model model, RepairRateDashboard vo, PageParameter page) {
		setBaseData(model);
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("productType", vo.getProductType());
		bs.put("month", vo.getMonth());
		List<RepairRateDashboard> list = repairRateDashboardService.getAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		return "qms/base/repairRateDashboard/list";
	}
	
	@RequestMapping("/add")
	public String add(Model model) {
		setBaseData(model);
		return "qms/base/repairRateDashboard/add";
	}
	
	@RequestMapping("/edit")
	public String edit(@RequestParam(value = "id", required = false) Long id, Model model) {
		setBaseData(model);
		RepairRateDashboard vo = repairRateDashboardService.selectByPrimaryKey(id);
		model.addAttribute("vo", vo);
		return "qms/base/repairRateDashboard/edit";
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(RepairRateDashboard vo) {
		repairRateDashboardService.deleteByPrimaryKey(vo.getId());
		return ajaxDoneSuccess("删除成功");	
	}
	
	@RequestMapping("/save")
	public ModelAndView save(RepairRateDashboard vo) {
		vo.setCreateUser(getCurrentUserName());
		try {
			repairRateDashboardService.insert(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxDoneError("保存失败，请检查是否存在相同月份下的机型！");
		}
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/update")
	public ModelAndView update(RepairRateDashboard vo) {
		repairRateDashboardService.updateByPrimaryKey(vo);
		return ajaxDoneSuccess("修改成功");
	}
}
