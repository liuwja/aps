package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CommonVo;
import com.peg.model.FaultType;
import com.peg.model.MarketRepairRecord;
import com.peg.service.FaultTypeServiceI;
import com.peg.web.util.ConstantInterface;
import com.peg.web.util.ExcelUtilities;
import com.peg.web.util.TmStringUtils;

@Controller
@RequestMapping("base/fault/faultType")
public class FaultTypeController extends BaseController{

	
	@Autowired
	private FaultTypeServiceI faultTypeService;
	
	
	@RequestMapping("/faultType")
	public String faultType(Model model,FaultType faultType,PageParameter page)
	{
		setBaseData(model);
		List<FaultType> list = faultTypeService.findAllByPage(faultType, page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", faultType);
		return "/qms/base/Fault/faultType/FaultType";
		
	}
	
	@RequestMapping("/addType")
	public String addType(Model model)
	{
		setBaseData(model);
		return "qms/base/Fault/faultType/addType";
	}
	
	@RequestMapping("/editType")
	public String editType( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		setBaseData(model);
		FaultType faultType = faultTypeService.selectByPrimaryKey(id);		
		model.addAttribute("faultType", faultType);
		return "qms/base/Fault/faultType/editType";
	}
	
	
	@RequestMapping("/updateFaultType")
	public ModelAndView updateFaultType(FaultType faultType)
	{
		faultType.setLastUpdateUser(getCurrentUserName());
		faultTypeService.updateByPrimaryKeySelective(faultType);
		
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/saveFaultType")
	public ModelAndView saveFaultType(FaultType faultType)
	{
		faultType.setCreateUser(getCurrentUserName());
		faultTypeService.insert(faultType);
		return ajaxDoneSuccess("保存成功");
	}
	
	

	@RequestMapping("/delete")
	public ModelAndView delete(FaultType faultType)
	{
		faultTypeService.deleteByPrimaryKey(faultType.getId());
		return ajaxDoneSuccess("删除成功");	
	}	
	@RequestMapping("/excelOutputFaultType")
	public void excelOutputFaultType(FaultType faultType, Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<FaultType> list = faultTypeService.findAll(faultType);
			String[] CN = new String[]{"机型类别", "故障代码", "故障名称", "创建时间", "创建用户","修改时间","修改用户"};
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				FaultType tmpVO = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getProductType();
				tmpStr[1] = tmpVO.getCode();
				tmpStr[2] = tmpVO.getName();
				if(tmpVO.getCreateTime()!= null){
					tmpStr[3] = tmpVO.getCreateTime().toString();
				}else{
					tmpStr[3] = "";
				}
				tmpStr[4] = tmpVO.getCreateUser();
				if(tmpVO.getLastUpdateTime()!= null){
				tmpStr[5] = tmpVO.getLastUpdateTime().toString();
				}else{
					tmpStr[5] = "";
				}
				tmpStr[6] = tmpVO.getLastUpdateUser();
				excelList.add(tmpStr);
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath, contentType, "故障大类" + fname);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
