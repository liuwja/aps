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
import com.peg.model.CommonVo;
import com.peg.model.InstallTotal;
import com.peg.service.CommonServiceI;
import com.peg.service.InstallTotalServiceI;
import com.peg.service.MachineTypeServiceI;
import com.peg.web.util.ConditionUtil;
import com.peg.web.util.ExcelUtilities;
import com.peg.web.util.TmStringUtils;

@Controller
@RequestMapping("base/installTotal")
public class InstallTotalController extends BaseController{
	@Autowired
	private MachineTypeServiceI machineTypeService;
	@Autowired
	private InstallTotalServiceI installTotalService;
	@Autowired
	private CommonServiceI commonService;
	
	/**
	 * 安装汇总表
	 */
	@RequestMapping("/installTotal")
	public String installTotal(CommonVo vo,Model model,PageParameter page){
		setBaseData(model);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		List<InstallTotal> list = null;
		int sum =0;
//		int insReSum = 0;
		if(vo.getProductType()!=null){
			if(TmStringUtils.isNotEmpty(vo.getFaultReasonCode())){
				String[] faultReasonArry = vo.getFaultReasonCode().split(",");
				vo.setFaultReasons(faultReasonArry);
			}
			if(TmStringUtils.isNotEmpty(vo.getFaultTypeCode())){
				String[] faultTypeArry = vo.getFaultTypeCode().split(",");
				vo.setFaultTypes(faultTypeArry);
			}
			
			if(TmStringUtils.isNotEmpty(vo.getRegionListTxt())){
				String[] regionArry = vo.getRegionListTxt().replaceAll("'", "").split(",");
				vo.setRegions(regionArry);
			}
			if(TmStringUtils.isNotEmpty(vo.getPartTypeListTxt())){
				String[] partTypeArry = vo.getPartTypeListTxt().replaceAll("'", "").split(",");
				vo.setPartTypes(partTypeArry);
			}
			list = installTotalService.findAllRecord(vo, page);
		    sum = installTotalService.sumInstall(vo);
//			insReSum = installTotalService.sumInsRepire(vo);
//			Map<String,InstallTotal> map = new HashMap<String,InstallTotal>();
//			for(int i = 0; i < list.size(); i++){
//				InstallTotal uivo = list.get(i);
//				String tm = uivo.getProductType() + "&" + uivo.getInstallMonth();
//				if(map.containsKey(tm)){
//					InstallTotal revo = map.get(tm);
//					revo.getInsList().add(uivo);
//					list.remove(i);
//					i--;
//				}else{
//					map.put(tm, uivo);
//				}
//			}
		}
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("sum", sum);
//		model.addAttribute("insReSum", insReSum);
		model.addAttribute("vo", vo);
		return "qms/total/installTotal";
	}
	
	/**
	 * 发货汇总导出excel
	 * @return
	 */
	@RequestMapping("/excelOutput")
	public String excelOutput(CommonVo vo, Model model,HttpServletRequest request, HttpServletResponse response) {
		String retView = "error/err";
		List<InstallTotal> list = null;
		try {
			if(TmStringUtils.isNotEmpty(vo.getFaultReasonCode())){
				String[] faultReasonArry = vo.getFaultReasonCode().split(",");
				vo.setFaultReasons(faultReasonArry);
			}
			if(TmStringUtils.isNotEmpty(vo.getFaultTypeCode())){
				String[] faultTypeArry = vo.getFaultTypeCode().split(",");
				vo.setFaultTypes(faultTypeArry);
			}
			
			if(TmStringUtils.isNotEmpty(vo.getRegionListTxt())){
				String[] regionArry = vo.getRegionListTxt().replaceAll("'", "").split(";");
				vo.setRegions(regionArry);
			}
			if(TmStringUtils.isNotEmpty(vo.getPartTypeListTxt())){
				String[] partTypeArry = vo.getPartTypeListTxt().replaceAll("'", "").split(";");
				vo.setPartTypes(partTypeArry);
			}
			int total = installTotalService.getTotalNumber(vo);
			if (total > 15000) {
				model.addAttribute("message", "查询的数据记录超出15000条，请缩小查询范围！");
				return "/error/err";
			}
			list = installTotalService.findAll(vo);
			String[] CN = {"主机条码", "服务工单", "机型类别", "安装月份", "物料编码", "系列", "型号","区域"};
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				InstallTotal tmpVO = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getSerialNumber();
				tmpStr[1] = tmpVO.getServiceOrder();
				tmpStr[2] = tmpVO.getProductType();
				tmpStr[3] = tmpVO.getInstallMonth();
				tmpStr[4] = tmpVO.getPartNumber();
				tmpStr[5] = tmpVO.getPartFamily();
				tmpStr[6] = tmpVO.getPartType();
				tmpStr[7] = tmpVO.getRegion();
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
					contentType, "安装汇总" + fname);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			model.addAttribute("message", "导出失败！请缩小查询范围！");
		}
		return retView;
	}
	
}
