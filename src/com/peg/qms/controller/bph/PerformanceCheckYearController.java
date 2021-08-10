package com.peg.qms.controller.bph;

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
import com.peg.model.bph.PerformanceCheckYear;
import com.peg.qms.controller.BaseController;
import com.peg.service.bph.PerformanceCheckYearServiceI;

/**
 * 年度考核基准设定
 * 
 * @Class: PerformanceCheckYearController @TODO:
 */
@Controller
@RequestMapping("system/performanceCheckYear")
public class PerformanceCheckYearController extends BaseController {

	@Autowired
	private PerformanceCheckYearServiceI performanceCheckServiceYear;

	@RequestMapping("/list")
	public String list(Model model, PerformanceCheckYear performanceCheckYear, PageParameter page) {
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("factory", performanceCheckYear.getFactory());
		bs.put("checkIndexName", performanceCheckYear.getCheckIndexName());
		bs.put("checkYear", performanceCheckYear.getCheckYear());

		List<PerformanceCheckYear> list = performanceCheckServiceYear.getAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "qms/bph/base/performanceCheckYear/list";

	}

	@RequestMapping("/add")
	public String add() {
		return "qms/bph/base/performanceCheckYear/add";
	}

	@RequestMapping("/edit")
	public String edit(@RequestParam(value = "id", required = false) Long id, Model model) {
		PerformanceCheckYear performanceCheckYear = performanceCheckServiceYear.selectByPrimaryKey(id);

		model.addAttribute("vo", performanceCheckYear);

		return "qms/bph/base/performanceCheckYear/edit";
	}

	@RequestMapping("/update")
	public ModelAndView update(PerformanceCheckYear performanceCheckYear) {

		performanceCheckYear.setLastUpdateUser(getCurrentUserName());
		performanceCheckYear.setLastUpdateTime(Calendar.getInstance().getTime());
		performanceCheckServiceYear.updateByPrimaryKeySelective(performanceCheckYear);
		return ajaxDoneSuccess("修改成功");
	}

	@RequestMapping("/save")
	public ModelAndView saveCheckItem(PerformanceCheckYear performanceCheckYear) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		try {
			if (performanceCheckYear.getQueryYear() != null) {
				performanceCheckYear.setCheckYear(sdf.parse(performanceCheckYear.getQueryYear()));
				System.out.println("performanceCheckYear.getQueryYear()" + performanceCheckYear.getQueryYear());
			}
			System.out.println(
					"------------/nperformanceCheckYear.getQueryYear()" + performanceCheckYear.getQueryYear() + "/n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		performanceCheckYear.setCreateUser(getCurrentUserName());
		performanceCheckServiceYear.insert(performanceCheckYear);
		return ajaxDoneSuccess("保存成功");
	}

	@RequestMapping("/delete")
	public ModelAndView delete(PerformanceCheckYear performanceCheckYear) {
		performanceCheckServiceYear.deleteByPrimaryKey(performanceCheckYear.getId());
		return ajaxDoneSuccess("删除成功");
	}

}
