package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.dao.VoiceOfCustomerMapper;
import com.peg.model.VoiceOfCustomer;
import com.peg.model.part.MarketPart;
import com.peg.qms.controller.BaseController;
import com.peg.web.util.ConstantInterface;
import com.peg.web.util.ExcelUtilities;
import com.peg.web.util.MarketPartUtil;
@Controller
@RequestMapping("base/voiceOfCustomer")
public class VoiceOfCustomerController extends BaseController{

	@Autowired
	private VoiceOfCustomerMapper voiceOfCustomerMapper;
	@RequestMapping("/voiceofcustomerList")
	public String list(Model model,PageParameter page, 
			@RequestParam(value = "typeAppeal", required = false) String typeAppeal, 
			@RequestParam(value = "serviceCenter", required = false) String serviceCenter, 
			@RequestParam(value = "serialNumber", required = false) String serialNumber,
			@RequestParam(value = "nameProduct", required = false) String nameProduct, 
			@RequestParam(value = "numberAppeal", required = false) String numberAppeal, 
			@RequestParam(value = "orderNumber", required = false) String orderNumber,
			@RequestParam(value = "bfaultClass", required = false) String bfaultClass, 
			@RequestParam(value = "sfaultClass", required = false) String sfaultClass,
		@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime){
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("typeAppeal", typeAppeal);
		bs.put("serviceCenter", serviceCenter);
		bs.put("serialNumber", serialNumber);
		bs.put("nameProduct", nameProduct);
		bs.put("numberAppeal", numberAppeal);
		bs.put("orderNumber", orderNumber);
		bs.put("bfaultClass", bfaultClass);
		bs.put("sfaultClass", sfaultClass);
		bs.put("startTime", startTime);
		bs.put("endTime", endTime);
		List<VoiceOfCustomer> list = voiceOfCustomerMapper.findALLByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "/qms/base/repairRecord/VoiceOfCustomer";
	}
	/**
	 * 导出CRM数据
	 */
	@RequestMapping("/exportExcel")
	private void exportExcel(Model model,PageParameter page,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "typeAppeal", required = false) String typeAppeal, 
			@RequestParam(value = "serviceCenter", required = false) String serviceCenter, 
			@RequestParam(value = "serialNumber", required = false) String serialNumber,
			@RequestParam(value = "nameProduct", required = false) String nameProduct, 
			@RequestParam(value = "numberAppeal", required = false) String numberAppeal, 
			@RequestParam(value = "orderNumber", required = false) String orderNumber,
			@RequestParam(value = "bfaultClass", required = false) String bfaultClass, 
			@RequestParam(value = "sfaultClass", required = false) String sfaultClass,
		@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime) {

		BaseSearch bs = new BaseSearch();
		bs.put("typeAppeal", typeAppeal);
		bs.put("serviceCenter", serviceCenter);
		bs.put("serialNumber", serialNumber);
		bs.put("nameProduct", nameProduct);
		bs.put("numberAppeal", numberAppeal);
		bs.put("orderNumber", orderNumber);
		bs.put("bfaultClass", bfaultClass);
		bs.put("sfaultClass", sfaultClass);
		bs.put("startTime", startTime);
		bs.put("endTime", endTime);
		List<VoiceOfCustomer> list = voiceOfCustomerMapper.findALL(bs);
		
		
		String[] CN = {"序号","时间","诉求类型","客户","省","市","区县",
				"服务中心","产品名称","简化型号","产品类型","产品编号","VOC一级分类","VOC二级分类","诉求编号","诉求详细信息",
				"派工单号","服务网点","服务技师","服务项目","故障大类","故障小类"};
		List<String[]> excelList = new ArrayList<String[]>();
		try {
			for (int i = 0; i < list.size(); i++) {
				String[] itemStr = new String[CN.length];
				itemStr[0] = (i+1)+"";
				itemStr[1] = list.get(i).getDateTime();
				itemStr[2] = list.get(i).getTypeAppeal();
				itemStr[3] = list.get(i).getCustomer();
				itemStr[4] = list.get(i).getProvince() + "";
				itemStr[5] = list.get(i).getCity();
				itemStr[6] = list.get(i).getCounty();
				itemStr[7] = list.get(i).getServiceCenter();
				itemStr[8] = list.get(i).getNameProduct();
				itemStr[9] = list.get(i).getModelProduct();
				itemStr[10] = list.get(i).getTypeProduct();
				itemStr[11] = list.get(i).getSerialNumber();
				itemStr[12] = list.get(i).getType1();
				itemStr[13] = list.get(i).getType2();
				itemStr[14] = list.get(i).getNumberAppeal();
				itemStr[15] = list.get(i).getDetail();
				itemStr[16] = list.get(i).getOrderNumber();
				itemStr[17] = list.get(i).getServiceLocation();
				itemStr[18] = list.get(i).getTechnician();
				itemStr[19] = list.get(i).getServiceProject();
				itemStr[20] = list.get(i).getBfaultClass();
				itemStr[21] = list.get(i).getSfaultClass();
				excelList.add(itemStr);
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType =  "application/msexcel" ;
			ExcelUtilities.downloadExcel(request, response, filePath, contentType,  "CRM客户之声"+fname);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
}
