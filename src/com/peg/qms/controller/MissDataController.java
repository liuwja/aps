package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.MissData;
import com.peg.service.MissDataServiceI;
import com.peg.web.util.ExcelUtilities;

@Controller
@RequestMapping("base/missData")
public class MissDataController extends BaseController {
	
	private static final String SHIP = "ship";
	
	private static final String INSTALL = "install";
	
	private static final String REPAIR = "repair";
	
	@Autowired
	private MissDataServiceI missDataService;
	
	@RequestMapping("/init")
	public String init(Model model, MissData vo, PageParameter page) {
		vo.setStatisType(SHIP);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "/qms/base/missData/missDataList";
	}
	
	@RequestMapping("/list")
	public String list(Model model, MissData vo, PageParameter page) {
		BaseSearch bs = new BaseSearch();
		List<MissData> list = new ArrayList<MissData>();
		bs.setPage(page);
		if (StringUtils.isNotEmpty(vo.getStartTime())) {
			bs.put("startTime", vo.getStartTime());
		}
		if (StringUtils.isNotEmpty(vo.getEndTime())) {
			bs.put("endTime", vo.getEndTime());
		}
		if (StringUtils.isEmpty(vo.getStatisType())) {
			return "/qms/base/missData/missDataList";
		}
		if (vo.getStatisType().equals(SHIP)) {
			list = missDataService.getMissShipDataPage(bs);
		} else if (vo.getStatisType().equals(INSTALL)) {
			list = missDataService.getMissInstallDataPage(bs);
		} else if (vo.getStatisType().equals(REPAIR)) {
			list = missDataService.getMissRepairDataPage(bs);
		}
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "/qms/base/missData/missDataList";
	}
	
	@RequestMapping("/partList")
	public String partList(Model model, MissData vo, PageParameter page) {
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("partNumber", vo.getPartNumber());
		bs.put("partName", vo.getPartName());
		List<MissData> list = missDataService.getMissPartDataPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "/qms/base/missData/missPartDataList";
	}
	
	@RequestMapping("/partListByProduct")
	public String partListByProduct(Model model, MissData vo, PageParameter page) {
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("partNumber", vo.getPartNumber());
		bs.put("partName", vo.getPartName());
		bs.put("showDescription", vo.getShowDescription());
		List<MissData> list = missDataService.getMissPartDataByProductPage(bs, vo);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "/qms/base/missData/missPartDataListByProduct";
	}
	
	//BUG #6955 QMS??????????????????????????????????????????????????????????????????????????????????????????????????????????????????   liuwjg  2019???2???20???00:23:49
	@RequestMapping("/editByProduct")
	public String editByProduct(MissData vo,Model model){
		System.out.println(vo.getPartNumber());
		List<MissData> list =missDataService.selectMissDatabyPart(vo.getPartNumber());
		System.out.println(list.get(0).getPartNumber());
		model.addAttribute("list", list.get(0));
		return "/qms/base/missData/editByProduct";
	}
	@RequestMapping("/updateByProduct")
	public ModelAndView updateByProduct(MissData vo,Model model){
		try{
			missDataService.updateMissDatabyPart(vo);
		}catch (Exception e) {
			// TODO: handle exception
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess("????????????");
	}
	
	@RequestMapping("/supplierList")
	public String supplierList(Model model, MissData vo, PageParameter page) {
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		if (StringUtils.isNotEmpty(vo.getSupplierName())) {
			bs.put("supplierName", vo.getSupplierName());
		}
		if (StringUtils.isNotEmpty(vo.getSupplierNumber())) {
			bs.put("supplierNumber", vo.getSupplierNumber());
		}
		List<MissData> list = missDataService.getMissSupplierDataPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "/qms/base/missData/missSupplierDataList";
	}
	
	@RequestMapping("/exportExcel")
	public String exportExcel(Model model, MissData vo, HttpServletRequest request, HttpServletResponse response) {
		try {
			int total = 0;
			if (vo.getStatisType().equals(SHIP)) {
				total = missDataService.getMissShipDataTotalNumber(vo);
			} else if (vo.getStatisType().equals(INSTALL)) {
				total = missDataService.getMissInstallDataTotalNumber(vo);
			} else if (vo.getStatisType().equals(REPAIR)) {
				total = missDataService.getMissRepairDataTotalNumber(vo);
			}
			if (total > 10000) {
				model.addAttribute("message", "?????????MQE????????????????????????????????????10000??????????????????excel???????????????????????????");
				return "/error/err";
			}
			List<String[]> excelList = new ArrayList<String[]>();
			List<MissData> list = new ArrayList<MissData>();
			String[] CN = new String[] {};
			if (vo.getStatisType().equals(SHIP)) {
				list = missDataService.getAllMissShipData(vo);
				CN = new String[] {"????????????", "????????????", "????????????", "????????????", "????????????", "????????????", "????????????", "????????????", "????????????"};
				for (MissData temp : list) {
					String[] tempStr = new String[CN.length];
					tempStr[0] = temp.getSerialNumber();
					tempStr[1] = temp.getProductType();
					tempStr[2] = temp.getPartNumber();
					tempStr[3] = temp.getPartFamily();
					tempStr[4] = temp.getPartType();
					tempStr[5] = temp.getPartName();
					tempStr[6] = temp.getRegion();
					tempStr[7] = temp.getProductLineNumber();
					tempStr[8] = temp.getShipDate();
					excelList.add(tempStr);
				}
			} else if (vo.getStatisType().equals(INSTALL)) {
				list = missDataService.getAllMissInstallData(vo);
				CN = new String[] {"????????????", "????????????", "????????????", "????????????", "????????????"};
				for (MissData temp : list) {
					String[] tempStr = new String[CN.length];
					tempStr[0] = temp.getSerialNumber();
					tempStr[1] = temp.getOrderNumber();
					tempStr[2] = temp.getPartName();
					tempStr[3] = temp.getRegion();
					tempStr[4] = temp.getInstallDate();
					excelList.add(tempStr);
				}
			} else if (vo.getStatisType().equals(REPAIR)) {
				list = missDataService.getAllMissRepairData(vo);
				CN = new String[] {"????????????", "????????????", "????????????", "????????????", "????????????", "????????????"};
				for (MissData temp : list) {
					String[] tempStr = new String[CN.length];
					tempStr[0] = temp.getSerialNumber();
					tempStr[1] = temp.getOrderNumber();
					tempStr[2] = temp.getPartName();
					tempStr[3] = temp.getFaultTypeName();
					tempStr[4] = temp.getFaultReasonName();
					tempStr[5] = temp.getRepairDate();
					excelList.add(tempStr);
				}
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel????????????
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath, contentType, "??????????????????" + fname);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "/error/err";
		}
	}
	
	@RequestMapping("/exportPartExcel")
	public String exportPartExcel(Model model, MissData vo, HttpServletRequest request, HttpServletResponse response) {
		try {
			int total = missDataService.getMissPartDataTotalNumber(vo);
			if (total > 10000) {
				model.addAttribute("message", "?????????MQE????????????????????????????????????10000??????????????????excel???????????????????????????");
				return "/error/err";
			}
			List<String[]> excelList = new ArrayList<String[]>();
			List<MissData> list = new ArrayList<MissData>();
			if (vo.getType().equals("0")) {
				list = missDataService.getMissPartData(vo);
			} else if(vo.getType().equals("1")) {
				list = missDataService.getMissPartDataByProduct(vo);
			}
			String[] CN = new String[] {"????????????", "????????????", "????????????", "????????????", "???????????????", "????????????"};
			for (MissData temp : list) {
				String[] tempStr = new String[CN.length];
				tempStr[0] = temp.getPartNumber();
				tempStr[1] = temp.getPartName();
				tempStr[2] = temp.getPartType();
				tempStr[3] = temp.getIsNewS();
				tempStr[4] = temp.getPartLevelS();
				excelList.add(tempStr);
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel????????????
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath, contentType, "????????????????????????" + fname);
			return null;
		} catch (Exception e) {
			logger.error(e);
		}
		return "/error/err";
	}
	
	@RequestMapping("/exportProductExcel")
	public String exportProductExcel(Model model, MissData vo, HttpServletRequest request, HttpServletResponse response) {
		try {
			int total = missDataService.getMissPartDataTotalNumber(vo);
			if (total > 10000) {
				model.addAttribute("message", "?????????MQE????????????????????????????????????10000??????????????????excel???????????????????????????");
				return "/error/err";
			}
			List<String[]> excelList = new ArrayList<String[]>();
			List<MissData> list = new ArrayList<MissData>();
			if (vo.getType().equals("0")) {
				list = missDataService.getMissPartData(vo);
			} else if(vo.getType().equals("1")) {
				list = missDataService.getMissPartDataByProduct(vo);
			}
			String[] CN = new String[] {"????????????", "????????????", "????????????", "????????????", "????????????", "??????????????????"};
			for (MissData temp : list) {
				String[] tempStr = new String[CN.length];
				tempStr[0] = temp.getPartNumber();
				tempStr[1] = temp.getPartName();
				tempStr[2] = temp.getProductType();
				tempStr[3] = temp.getPartFamily();
				tempStr[4] = temp.getPartType();
				tempStr[5] = temp.getDescription();
				excelList.add(tempStr);
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel????????????
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath, contentType, "????????????????????????" + fname);
			return null;
		} catch (Exception e) {
			logger.error(e);
		}
		return "/error/err";
	}
	
	@RequestMapping("/exportSupplierExcel")
	public String exportSupplierExcel(Model model, MissData vo, HttpServletRequest request, HttpServletResponse response) {
		try {
			int total = missDataService.getMissSupplierDataTotalNumber(vo);
			if (total > 10000) {
				model.addAttribute("message", "?????????MQE????????????????????????????????????10000??????????????????excel???????????????????????????");
				return "/error/err";
			}
			List<String[]> excelList = new ArrayList<String[]>();
			List<MissData> list = missDataService.getMissSupplierData(vo);
			String[] CN = new String[] {"???????????????", "???????????????", "??????", "??????", "???????????????"};
			for (MissData temp : list) {
				String[] tempStr = new String[CN.length];
				tempStr[0] = temp.getSupplierNumber();
				tempStr[1] = temp.getSupplierName();
				tempStr[2] = temp.getProductLineName();
				tempStr[3] = temp.getFactory();
				tempStr[4] = temp.getShortSupplierName();
				excelList.add(tempStr);
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel????????????
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath, contentType, "???????????????????????????" + fname);
			return null;
		} catch(Exception e) {
			logger.error(e);
		}
		return "/error/err";
	}
}
