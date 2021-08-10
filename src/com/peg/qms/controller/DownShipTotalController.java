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
import com.peg.model.DownLineTotal;
import com.peg.model.MachineType;
import com.peg.model.ShipTotal;
import com.peg.service.DownLineTotalServiceI;
import com.peg.service.MachineTypeServiceI;
import com.peg.service.ShipTotalServiceI;
import com.peg.web.util.ExcelUtilities;

@Controller
@RequestMapping("base/downShipTotal")
public class DownShipTotalController  extends BaseController{
	
	@Autowired
	private MachineTypeServiceI machineTypeService;
	
	@Autowired
	private ShipTotalServiceI shipTotalService;
	
	@Autowired
	private DownLineTotalServiceI downLineTotalService;
	
	/**
	 * 发货汇总表
	 */
	@RequestMapping("/shipmentsTotal")
	public String shipmentsTotal(ShipTotal shipTotal,Model model,PageParameter page){
		List<ShipTotal> list = shipTotalService.findAllByPage(shipTotal,page);
		int sum = shipTotalService.sumship(shipTotal);
		List<MachineType> typelist = machineTypeService.getAll();
		model.addAttribute("typelist",typelist);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("sum", sum);
		model.addAttribute("vo", shipTotal);
		return "qms/total/shipmentsTotal";
	}
	
	/**
	 * 下线汇总表
	 */
	@RequestMapping("/downLineTotal")
	public String downLineTotal(DownLineTotal downLineTotal, Model model,PageParameter page){
		List<DownLineTotal> list = downLineTotalService.findAllByPage(downLineTotal, page);
		int sum = downLineTotalService.sumDownLine(downLineTotal);
		List<MachineType> typelist = machineTypeService.getAll();
		model.addAttribute("typelist",typelist);
		model.addAttribute("list", list);
		model.addAttribute("sum", sum);
		model.addAttribute("page", page);
		model.addAttribute("vo", downLineTotal);
		return "qms/total/downLineTotal";
	}
	
	/**
	 * 发货汇总导出excel
	 * @return
	 */
	@RequestMapping("/shipTotalExcelOutput")
	public String shipTotalExcelOutput(ShipTotal shipTotal, Model model,HttpServletRequest request, HttpServletResponse response) {
		String retView = "error/err";
		List<ShipTotal> list = null;
		try {
			list = shipTotalService.findAll(shipTotal);
			String[] CN = { "机型类别", "发货月份", "型号", "区域", "产线编号","发货数量"};
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				ShipTotal tmpVO = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getProductType();
				tmpStr[1] = tmpVO.getStatisticsMonth();
				tmpStr[2] = tmpVO.getPartType();
				tmpStr[3] = tmpVO.getRegion();
				tmpStr[4] = tmpVO.getProductlineNumber();
				tmpStr[5] = tmpVO.getShipCount()+"";
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
					contentType, "发货汇总" + fname);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			model.addAttribute("message", "导出失败！请缩小查询范围！");
		}
		return retView;
	}
	
	/**
	 * 下线汇总导出excel
	 * @return
	 */
	@RequestMapping("/downTotalExcelOutput")
	public String downTotalExcelOutput(DownLineTotal downLineTotal, Model model,HttpServletRequest request, HttpServletResponse response) {
		String retView = "error/err";
		List<DownLineTotal> list = null;
		try {
			list = downLineTotalService.findAll(downLineTotal);
			String[] CN = { "机型类别", "下线月份","型号", "区域", "产线编号", "下线数量"};
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				DownLineTotal tmpVO = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getProductType();
				tmpStr[1] = tmpVO.getStatisticsMonth();
				tmpStr[2] = tmpVO.getPartType();
				tmpStr[3] = tmpVO.getRegion();
				tmpStr[4] = tmpVO.getProductlineNumber();
				tmpStr[5] = tmpVO.getDownlineCount()+"";
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
					contentType, "下线汇总" + fname);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			model.addAttribute("message", "导出失败！请缩小查询范围！");
		}
		return retView;
	}


}
