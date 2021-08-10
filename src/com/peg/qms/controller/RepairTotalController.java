package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.FaultReason;
import com.peg.model.MachineType;
import com.peg.model.MarketRepairTotal;
import com.peg.service.CommonServiceI;
import com.peg.service.MachineTypeServiceI;
import com.peg.service.MarketRepairTotalServiceI;
import com.peg.web.util.ExcelUtilities;

@Controller
@RequestMapping("base/repairTotal")
public class RepairTotalController extends BaseController{
	@Autowired
	private CommonServiceI comService;
	@Autowired
	private MarketRepairTotalServiceI totalService;
	@Autowired
	private MachineTypeServiceI machineTypeService;
	
	/**
	 * 维修汇总表
	 */
	@RequestMapping("/repairTotal")
	public String repairTotal(MarketRepairTotal total,FaultReason faultReason,Model model,PageParameter page){
		
		List<MachineType> typelist = machineTypeService.getAll();
		int sum = 0;
		List<MarketRepairTotal> list = totalService.findRepairTotalByPage(total, faultReason, page);
		sum = totalService.sumRepair(total, faultReason);
		model.addAttribute("typelist",typelist);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("sum", sum);
		model.addAttribute("vo", total);
		model.addAttribute("faultReason", faultReason);
		return "qms/total/repairTotal";
	}
	/**
	 * 导出excel
	 * @return
	 */
	@RequestMapping("/excelOutput")
	public String excelOutput(MarketRepairTotal total,FaultReason faultReason,HttpServletRequest request, 
			HttpServletResponse response,Model model) {
		String retView = "error/err";
		List<MarketRepairTotal> list = null;
		try {
			list = totalService.findAllRepairTotal(total, faultReason);
			String[] CN = { "机型类别", "下线月份","维修月份", "型号", "区域", 
					"产线编号", "故障大类代码", "故障小类代码", "维修数量","是否过期"};
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				MarketRepairTotal temp = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = temp.getProductType();
				tmpStr[1] = temp.getDownlineMonth();
				tmpStr[2] = temp.getRepairedMonth();
				tmpStr[3] = temp.getPartType();
				tmpStr[4] = temp.getRegion();
				tmpStr[5] = temp.getProductlineNumber();
				tmpStr[6] = temp.getFaultTypeCode();
				tmpStr[7] = temp.getFaultReasonCode();
				tmpStr[8] = temp.getRepairedCount()+"";
				tmpStr[9] = temp.getIsOver();
				excelList.add(tmpStr);
			}
			// 获取项目根目录
			String rootPath = request.getSession().getServletContext()
					.getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath,
					contentType, "维修汇总" + fname);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			model.addAttribute("message", "导出失败！请缩小查询范围！");
		}
		return retView;
	}
}
