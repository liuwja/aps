package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ClaimsSheet;
import com.peg.model.ReworkSheet;
import com.peg.qms.controller.BaseController;
import com.peg.service.ClaimsSheetServicel;
import com.peg.service.CommonServiceI;
import com.peg.service.ReworkSheetServicel;
import com.peg.web.util.ExcelUtilities;

/**
 * 
 * <p>Title: ReworkSheetController</p>
 * <p>Description: 返工、停线单逻辑层</p>
 * <p>Company: Fotile</p> 
 * @author dingzc 
 * @date 2018-3-9 下午2:52:13
 */
@Controller
@RequestMapping("system/reworkSheet")
public class ReworkSheetController  extends BaseController{
	
	@Autowired
	private ReworkSheetServicel reworkSheetService;//返工/停线单service
	
	@Autowired
	private ClaimsSheetServicel claimsSheetService;//索赔/处罚单service

	@Autowired
	private CommonServiceI commonService;//通用方法service
	
	@RequestMapping("/list")
	public String list(ReworkSheet reworkSheet,Model model,PageParameter page){
		List<ReworkSheet> list =reworkSheetService.getAllByPage(reworkSheet,page);
		model.addAttribute("list",list);
		model.addAttribute("reworkSheet",reworkSheet);
		model.addAttribute("page",page);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/system/reworkSheet/list";
	}
	
	@RequestMapping("/add")
	public String add(Model model){
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/system/reworkSheet/add";
	}
	
	@RequestMapping("/save")
	public ModelAndView saveReworkSheet(ReworkSheet reworkSheet){
//		Date date=new Date();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");	
		try {
			ReworkSheet reworkSheetExist=reworkSheetService.getByReworkNumber(reworkSheet);
			if(reworkSheetExist==null){
				reworkSheetService.insertSelective(reworkSheet);
			}else{
				return ajaxDoneError("该单号已存在，请重新填写单号");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxDoneError("保存失败，请联系相关人员");
		}
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/edit")
	public String edit(ReworkSheet reworkSheet,Model model){
		ReworkSheet reworkSheetEdit=reworkSheetService.getByid(reworkSheet);
		model.addAttribute("reworkSheet",reworkSheetEdit);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/system/reworkSheet/edit";
	}
	@RequestMapping("/update")
	public ModelAndView update(ReworkSheet reworkSheet){
		try {
			reworkSheetService.updateByIdSelective(reworkSheet);
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxDoneError("更新失败，请联系相关人员");
		}
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(ReworkSheet reworkSheet){
		try {
			ReworkSheet reworkSheets=reworkSheetService.getByid(reworkSheet);
			List<ClaimsSheet> claimsSheetDel=claimsSheetService.getByReworkId(reworkSheet);
			if("关闭".equals(reworkSheets.getStatus())){
				return ajaxDoneError("关闭状态不允许删除");
			}else if(claimsSheetDel!=null && claimsSheetDel.size()>0){
				return ajaxDoneError("该返工/停线单已存在对应的索赔/处罚单不允许删除");
			}
			else{
				reworkSheetService.deleteById(reworkSheet);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxDoneError("删除失败，请联系相关人员");
		}
		return ajaxDoneSuccess("删除成功");
	}
	/**
	 * 
	* @Title: exportExcel   
	* @Description: 返工停线单导出 
	* @return String    返回类型   
	* @throws   
	* @author dingzc 
	* @author 2018-3-22 上午9:55:34
	 */
	  @RequestMapping("/exportExcel")
	    public String exportExcel(Model model,ReworkSheet reworkSheet,
	            HttpServletRequest request, HttpServletResponse response) {
	        String retView = "/error/err";
	        try {
	            PageParameter page = new PageParameter();
	            page.setNumPerPage(50000);
	            List<ReworkSheet> list =reworkSheetService.getAllByPage(reworkSheet,page);
	            String[] CN = { "工厂","返工/停线单号","工时","耗材费用","金额","状态","开单日期"};
	            List<String[]> excelList = new ArrayList<String[]>();
	            int index = 0;
	            int cols = CN.length;
	            for (int i = 0; i < list.size(); i++) {
	            	ReworkSheet tmpVO = list.get(i);
	                String[] tmpStr = new String[cols];
	                tmpStr[index++] = tmpVO.getFactory();
	                tmpStr[index++] = tmpVO.getRework_number();
	                tmpStr[index++] = tmpVO.getWorkhour();
	                tmpStr[index++] = tmpVO.getSupplies_expense();
	                tmpStr[index++] = tmpVO.getMoney();
	                tmpStr[index++] = tmpVO.getStatus();
	                tmpStr[index++] = tmpVO.getCreation_time();
	                index = 0;
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
	                    contentType, "返工/停线单" + fname);
	            return null;
	        } catch (Exception e) {
	            logger.error(e.getMessage(), e);
	        }
	        return retView;
	    }
}
