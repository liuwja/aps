package com.peg.qms.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.SupplierRef;
import com.peg.service.SupplierRefServiceI;
import com.peg.web.util.ExcelUtilities;
import com.peg.web.util.FileUtil;

@Controller
@RequestMapping("base/supplierRef")
public class SupplierRefController extends BaseController {
	
	@Autowired
	private SupplierRefServiceI supplierRefService;
	
	@RequestMapping("/list")
	public String list(Model model, SupplierRef vo, PageParameter page) {
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("supplierNumberN", vo.getSupplierNumberN());
		bs.put("supplierNameN", vo.getSupplierNameN());
		List<SupplierRef> list = supplierRefService.findPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "/qms/base/supplierRef/supplierRefList";
	}
	
	@RequestMapping("/importSupplierRef")
	public String importSupplierRef() {
		return "/qms/base/supplierRef/supplierRefImport";
	}
	
	@RequestMapping("/exportExcel")
	public String exportExcel(Model model, SupplierRef vo, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<String[]> excelList = new ArrayList<String[]>();
			List<SupplierRef> list = supplierRefService.findAll(vo);
			String[] CN = new String[] {"??????????????????", "??????????????????", "??????????????????", "??????????????????", "???????????????"};
			for (SupplierRef temp : list) {
				String[] tempStr = new String[CN.length];
				tempStr[0] = temp.getSupplierNumber();
				tempStr[1] = temp.getSupplierNumberN();
				tempStr[2] = temp.getSupplierName();
				tempStr[3] = temp.getSupplierNameN();
				tempStr[4] = temp.getSupplierShortName();
				excelList.add(tempStr);
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel????????????
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath, contentType, "???????????????" + fname);
			return null;
		} catch (Exception e) {
			logger.error(e);
		}
		return "/error/err";
	}
	
	@RequestMapping("/addSupplierRef")
	public String addSupplierRef() {
		return "qms/base/supplierRef/addSupplierRef";
	}
	
	@RequestMapping("/editSupplierRef")
	public String editSupplierRef(@RequestParam(value = "id", required = false) Long id, Model model) {
		SupplierRef supplierRef = supplierRefService.selectByPrimaryKey(id);		
		model.addAttribute("vo", supplierRef);
		return "qms/base/supplierRef/editSupplierRef";
	}
	
	@RequestMapping("/save")
	public ModelAndView save(SupplierRef vo) {
		supplierRefService.insert(vo);
		return ajaxDoneSuccess("????????????");
	}
	
	@RequestMapping("/update")
	public ModelAndView update(SupplierRef vo) {
		if (vo.getId() != null) {
			supplierRefService.update(vo);
		}
		return ajaxDoneSuccess("????????????");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(SupplierRef supplierRef) {
		supplierRefService.delete(supplierRef);
		return ajaxDoneSuccess("????????????");
	}
	
	@RequestMapping("/upload")
	public void upload(HttpServletResponse response, @RequestParam(value = "excelFile", required = false)MultipartFile excelFile) {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		String msg = "";
		long totalRows = 0;
		PrintWriter out = null;
		StopWatch watch = new StopWatch();
		try {
			out = response.getWriter();
			if (excelFile != null) {
				CommonsMultipartFile cf = (CommonsMultipartFile) excelFile;
				FileItem item = (FileItem)cf.getFileItem();
				List<String[]> dataList = new ArrayList<String[]>();
				dataList = FileUtil.parseCsvExlFile(item);
				totalRows = dataList.size();
				double useTime = watch.getTime() / 1000d;
				supplierRefService.insert(dataList);
				msg = "??????" + totalRows + " ??????????????????(s)" + useTime;
			}
		} catch (Exception e) {
			msg = "???????????????\n" + e.getMessage();
			e.printStackTrace();
		} finally {
			out.write(msg);
			if (out != null) {
				out.close();
			}
		}
	}
	
	@RequestMapping("/supplierPartList")
	public String supplierPartList(Model model, SupplierRef vo, PageParameter page) {
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("partNumber", vo.getPartNumber());
		bs.put("supplierNumberN", vo.getSupplierNumberN());
		List<SupplierRef> list = supplierRefService.findSupplierPartPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "/qms/base/supplierPart/supplierPartList";
	}
	
	@RequestMapping("/addSupplierPart")
	public String addSupplierPart() {
		return "qms/base/supplierPart/addSupplierPart";
	}
	
	@RequestMapping("/editSupplierPart")
	public String editSupplierPart(@RequestParam(value = "id", required = false) Long id, Model model) {
		SupplierRef supplierRef = supplierRefService.selectSupplierPartByPrimaryKey(id);		
		model.addAttribute("vo", supplierRef);
		return "qms/base/supplierPart/editSupplierRef";
	}
	
	@RequestMapping("/saveSupplierPart")
	public ModelAndView saveSupplierPart(SupplierRef vo) {
		supplierRefService.insertSupplierPart(vo);
		return ajaxDoneSuccess("????????????");
	}
	
	@RequestMapping("/updateSupplierPart")
	public ModelAndView updateSupplierPart(SupplierRef vo) {
		if (vo.getId() != null) {
			supplierRefService.updateSupplierPart(vo);
		}
		return ajaxDoneSuccess("????????????");
	}
	
	@RequestMapping("/deleteSupplierPart")
	public ModelAndView deleteSupplierPart(SupplierRef supplierRef) {
		supplierRefService.delete(supplierRef);
		return ajaxDoneSuccess("????????????");
	}
	
	@RequestMapping("/exportSupplierPartExcel")
	public String exportSupplierPartExcel(Model model, SupplierRef vo, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<String[]> excelList = new ArrayList<String[]>();
			List<SupplierRef> list = supplierRefService.findSupplierPartAll(vo);
			String[] CN = new String[] {"???????????????", "???????????????", "??????????????????", "????????????", "????????????", "???????????????", "???????????????"};
			for (SupplierRef temp : list) {
				String[] tempStr = new String[CN.length];
				tempStr[0] = temp.getSupplierNumber();
				tempStr[1] = temp.getSupplierName();
				tempStr[2] = temp.getSupplierNumberN();
				tempStr[3] = temp.getPartNumber();
				tempStr[4] = temp.getPartName();
				tempStr[5] = temp.getSupplierCode();
				tempStr[6] = temp.getSupplierShortName();
				excelList.add(tempStr);
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel????????????
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath, contentType, "???????????????????????????" + fname);
			return null;
		} catch (Exception e) {
			logger.error(e);
		}
		return "/error/err";
	}
}
