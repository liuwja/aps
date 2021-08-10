package com.peg.qms.controller;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.LaterSumtime;
import com.peg.model.MachineType;
import com.peg.model.RepairRateInput;
import com.peg.service.LaterSumtimeServiceI;
import com.peg.service.MachineTypeServiceI;
import com.peg.service.RepairCountInputServiceI;
import com.peg.web.util.WebUtil;

@Controller
@RequestMapping("base/repairCountInput")
public class RepairCountInputController extends BaseController {

	@Autowired
	private MachineTypeServiceI machineTypeService;

	@Autowired
	private RepairCountInputServiceI repairCountInputService;
	
	@Autowired
	private LaterSumtimeServiceI laterSumtimeService;

	@RequestMapping("/list")
	public String list(Model model,RepairRateInput input,PageParameter page) throws Exception
	{
		LaterSumtime laterTime = laterSumtimeService.getLaterDate();
		String queryMonth = laterTime.getSumMonth();
		String startMonth = WebUtil.rebackMonths(queryMonth, -11);
		List<String> monthList = WebUtil.getDateList(queryMonth, 12);
		Collections.reverse(monthList);
		List<RepairRateInput> list = repairCountInputService.queryAlarm(startMonth,queryMonth);
		model.addAttribute("list", list);
		model.addAttribute("monthList", monthList);
		model.addAttribute("queryMonth", queryMonth);
		return "qms/base/repairRateInput/list";
	}
	
	@RequestMapping("/homeList")
	public String homeList(Model model,RepairRateInput input,PageParameter page) throws Exception
	{
		LaterSumtime laterTime = laterSumtimeService.getLaterDate();
		String queryMonth = laterTime.getSumMonth();
		String startMonth = WebUtil.rebackMonths(queryMonth, -11);
		List<String> monthList = WebUtil.getDateList(queryMonth, 12);
		Collections.reverse(monthList);
		List<RepairRateInput> list = repairCountInputService.queryAlarm(startMonth,queryMonth);
		model.addAttribute("list", list);
		model.addAttribute("monthList", monthList);
		model.addAttribute("queryMonth", queryMonth);
		return "qms/base/repairRateInput/homeList";
		
	}

	/**
	 * 跳转 添加发货数，维修数页面
	 */
	@RequestMapping("/create")
	public String add(Model model) throws Exception {

		List<MachineType> productTypes = machineTypeService.getAll();
		model.addAttribute("productTypes", productTypes);
		return "qms/base/repairRateInput/create";
	}

	/**
	 * 保存或更新发货数，维修数
	 */
	@RequestMapping("/save")
	public ModelAndView save(RepairRateInput rateInput) throws Exception {
		try {
			RepairRateInput input = repairCountInputService.getByTypeAndMonth(
					rateInput.getRepairMonth(), rateInput.getTypeCategory());
			if (input == null) {
				rateInput.setCreateTime(new Date());
				rateInput.setCreateUser(getCurrentUserName());
				repairCountInputService.insert(rateInput);
			} else {
				rateInput.setId(input.getId());
				repairCountInputService.updateByPrimaryKeySelective(rateInput);
			}
			//计算维修率、累计维修率
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			String currentMonth = DateFormatUtils.format(cal.getTime(), "yyyy-MM");
			repairCountInputService.updateCalculate(rateInput.getTypeCategory(), rateInput.getRepairMonth(),currentMonth);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("保存成功");
	}

	
}
