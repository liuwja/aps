package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.FaultReason;
import com.peg.model.FaultType;
import com.peg.service.FaultReasonServiceI;
import com.peg.web.util.ExcelUtilities;
import com.peg.web.util.TmStringUtils;



@Controller
@RequestMapping("base/fault/faultReason")
public class FaultReasonController extends BaseController{
	
	@Autowired
	private FaultReasonServiceI faultReasonService;
	
	public static final String NEW = "新建";
	
	public static final String UPDATE = "更新";
	
	public static final String MESH = "合并";
	
	public static final String BREAK_MESH = "拆分";
	
	@RequestMapping("/faultReason")
	public String faultReason(Model model,FaultReason faultReason,PageParameter page)
	{
		setBaseData(model);
		List<FaultReason> list = faultReasonService.findAllByPage(faultReason, page);
		Map<String,FaultReason> map = new HashMap<String,FaultReason>();
		
//		for(int i = 0; i < list.size(); i++){ //合并故障名称
//			FaultReason fare = list.get(i);
//			String meshCode = fare.getMeshFaultCode();
//			if(map.containsKey(meshCode)){
//				FaultReason faultRe = map.get(meshCode);
//				faultRe.getFaultList().add(fare);
//				list.remove(i);
//				i--;
//			}else{
//				map.put(meshCode, fare);
//			}
//		}
		for(int i = 0; i < list.size(); i++){ //合并故障名称
			FaultReason fare = list.get(i);
			String meshName = fare.getMeshFaultName();
			String meshCode = fare.getMeshFaultCode() == null ? "" : fare.getMeshFaultCode();
			if(map.containsKey(meshCode + "" + meshName)){
				FaultReason faultRe = map.get(meshCode + "" + meshName);
				faultRe.getFaultList().add(fare);
				list.remove(i);
				i--;
			}else{
				map.put(meshCode + "" + meshName, fare);
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", faultReason);
		
		return "/qms/base/Fault/faultReason/FaultReason";
		
	}
	/**
	 * 合并查找
	 * @param faultReason
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("/faultReasonSelect")
	public String faultReasonSelect(FaultReason faultReason, Model model,PageParameter page) 
	{
		setBaseData(model);
		List<FaultReason> list = faultReasonService.findAllByPage(faultReason, page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo",faultReason);
		return "qms/base/Fault/faultReason/select";
	}
	
	@RequestMapping("/addReason")
	public String addReason(Model model)
	{
		setBaseData(model);
		return "qms/base/Fault/faultReason/addReason";
	}
	
	@RequestMapping("/editReason")
	public String editReason( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		setBaseData(model);
		FaultReason faultReason = faultReasonService.selectByPrimaryKey(id);		
		model.addAttribute("faultReason", faultReason);
		return "qms/base/Fault/faultReason/editReason";
	}
	
	
	@RequestMapping("/updateFaultReason")
	public ModelAndView updateReason(FaultReason faultReason)
	{
		faultReason.setLastUpdateUser(getCurrentUserName());
		faultReason.setLastUpdateType(UPDATE);
		faultReasonService.updateByPrimaryKeySelective(faultReason);
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/saveFaultReason")
	public ModelAndView saveReason(FaultReason faultReason)
	{
		faultReason.setCreateUser(getCurrentUserName());
		faultReason.setLastUpdateType(NEW);
		faultReasonService.insert(faultReason);
		return ajaxDoneSuccess("保存成功");
	}
	
	

	@RequestMapping("/delete")
	public ModelAndView delete(FaultReason faultReason)
	{
		faultReasonService.deleteByPrimaryKey(faultReason.getId());
		return ajaxDoneSuccess("删除成功");	
	}
	
	@RequestMapping("/mesh")
	public ModelAndView mesh(FaultReason faultReason){
		ModelAndView mav = new ModelAndView("/qms/base/Fault/faultReason/mesh");	
		mav.addObject("keys", faultReason.getKeys());
		return mav;
	}
	
	/**
	 * 合并故障小类
	 * @param faultReason
	 * @param response
	 */
	@RequestMapping("/saveMesh")
	public void saveMesh(FaultReason faultReason,HttpServletResponse response) {
		try {
			
			if (TmStringUtils.isNotEmpty(faultReason.getKeys())){
				String[] meshkey = faultReason.getKeys().split(",");
				List<Long> idList = new ArrayList<Long>();
				for(int i=0;i<meshkey.length;i++){
					idList.add(Long.parseLong(meshkey[i]));
				}
				if(idList.size()!=0){
//					String meshCode = UUID.randomUUID().toString();
					faultReasonService.saveMesh(idList, faultReason.getMeshFaultCode(), faultReason.getMeshFaultName(), getCurrentUserName(), FaultReasonController.MESH);
				}
			}
			response.getWriter().print("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 拆分合并故障小类
	 * @param faultReason
	 * @param response
	 */
	@RequestMapping("/breakMesh")
	public void breakMesh(FaultReason faultReason,HttpServletResponse response) {
		try {
			if(TmStringUtils.isNotEmpty(faultReason.getKeys())){
				String[] meshkey = faultReason.getKeys().split(",");
				List<Long> idList = new ArrayList<Long>();
				for(int i=0;i<meshkey.length;i++){
					idList.add(Long.parseLong(meshkey[i]));
				}
				if(idList.size()!=0){
					faultReasonService.breakMesh(idList, getCurrentUserName(), FaultReasonController.BREAK_MESH);
				}
			}
			response.getWriter().print("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/synchronous")
	public ModelAndView synchronous(FaultReason faultReason)
	{
		faultReasonService.synchronous();
		return ajaxDoneSuccess("同步成功");
	}
	
	@RequestMapping("/getMeshName")
	public void getMeshName(FaultReason faultReason, HttpServletResponse response) {
		FaultReason f = faultReasonService.getMeshNameByMeshCode(faultReason);
		try {
			if (f != null && f.getMeshFaultName() != null && f.getMeshFaultName() != "") {
				response.getWriter().print(new String(f.getMeshFaultName().getBytes("UTF-8"), "ISO8859-1"));
			} else {
				response.getWriter().print("null");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/exportExcelFaultReason")
	public void exportExcelFaultReason(FaultReason faultReason, Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			String valid = faultReason.getValid();
			String str=valid.replace(",", "");
			faultReason.setValid(str);
			List<FaultReason> list = faultReasonService.findAllResult(faultReason);
			Map<String,FaultReason> map = new HashMap<String,FaultReason>();
			String[] CN = new String[]{"合并故障代码", "合并故障名称", "机型类别", "故障代码", "故障名称","是否有效","创建时间","创建用户","上次修改时间","上次修改用户","上次修改类型"};
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				FaultReason tmpVO = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getMeshFaultCode();
				tmpStr[1] = tmpVO.getMeshFaultName();
				tmpStr[2] = tmpVO.getProductType();
				tmpStr[3] = tmpVO.getCode();
				tmpStr[4] = tmpVO.getName();
				tmpStr[5] = tmpVO.getValid();
				if(tmpVO.getCreateTime()!= null){
					tmpStr[6] = tmpVO.getCreateTime().toString();
				}else{
					tmpStr[6] = "";
				}
				tmpStr[7] = tmpVO.getCreateUser();
				if(tmpVO.getLastUpdateTime()!= null){
				tmpStr[8] = tmpVO.getLastUpdateTime().toString();
				}else{
					tmpStr[8] = "";
				}
				tmpStr[9] = tmpVO.getLastUpdateUser();
				tmpStr[10] = tmpVO.getLastUpdateType();
				excelList.add(tmpStr);
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath, contentType, "故障小类" + fname);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
