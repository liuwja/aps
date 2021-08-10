package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ClaimsSheet;
import com.peg.model.ReworkSheet;
import com.peg.model.bph.Onlinelookup;
import com.peg.model.system.SysMesUser;
import com.peg.service.ClaimsSheetServicel;
import com.peg.service.CommonServiceI;
import com.peg.service.ReworkSheetServicel;
import com.peg.service.system.SysMesUserServiceI;
import com.peg.web.util.ExcelUtilities;

@Controller
@RequestMapping("system/claimsSheet")
public class ClaimsSheetController extends BaseController{
	@Autowired
	private ReworkSheetServicel reworkSheetService;//返工/停线单service
	
	@Autowired
	private ClaimsSheetServicel claimsSheetService;//索赔/处罚单service
	
	@Autowired
	private SysMesUserServiceI sysUserService;//用户管理service

	@Autowired
	private CommonServiceI commonService;//通用方法service
	
	/**
	 * 
	* @Title: list   
	* @Description: 索赔处罚列表  
	* @return String    返回类型   
	* @throws   
	* @author dingzc 
	* @author 2018-3-21 上午9:22:52
	 */
	@RequestMapping("/list")
	public String list(ClaimsSheet claimsSheet,Model model,PageParameter page){
		List<ClaimsSheet> list =claimsSheetService.getAllByPage(claimsSheet,page);
		model.addAttribute("list",list);
		model.addAttribute("claimsSheet",claimsSheet);
		model.addAttribute("page",page);
		model.addAttribute("product_categorys", commonService.gettype(null));
		return "qms/system/claimsSheet/list";
	}
	/**
	 * 
	* @Title: procurementList   
	* @Description: 采购登记查询  
	* @return String    返回类型   
	* @throws   
	* @author dingzc 
	* @author 2018-3-21 上午9:21:37
	 */
	@RequestMapping("/procurementList")
	public String procurementList(ClaimsSheet claimsSheet,Model model,PageParameter page){
		List<ClaimsSheet> list =claimsSheetService.getAllByPage(claimsSheet,page);
		model.addAttribute("list",list);
		model.addAttribute("claimsSheet",claimsSheet);
		model.addAttribute("page",page);
		model.addAttribute("product_categorys", commonService.gettype(null));
		return "qms/system/claimsSheet/procurementList";
	}
	/**
	 * 
	* @Title: addTicket   
	* @Description: 新建处罚单  
	* @return String    返回类型   
	* @throws   
	* @author dingzc 
	* @author 2018-3-19 下午2:25:26
	 */
	@RequestMapping("/addTicket")
	public String addTicket(Model model){
		Subject currentUser = SecurityUtils.getSubject( );
		SysMesUser user = (SysMesUser)currentUser.getSession().getAttribute("user");
		ClaimsSheet claimsSheet=new ClaimsSheet();
		claimsSheet.setClaims_type("处罚");
		claimsSheet.setRegistrar(user.getDescription());
		model.addAttribute("claimsSheet",claimsSheet);
		model.addAttribute("product_categorys", commonService.gettype(null));
		return "qms/system/claimsSheet/addTicket";
	}
	/**
	 * 新建索赔单
	* @Title: add   
	* @return String    返回类型   
	* @throws   
	* @author dingzc 
	* @author 2018-3-19 下午2:25:41
	 */
	@RequestMapping("/addClaim")
	public String add(Model model){
		Subject currentUser = SecurityUtils.getSubject( );
		SysMesUser user = (SysMesUser)currentUser.getSession().getAttribute("user");
		ClaimsSheet claimsSheet=new ClaimsSheet();
		claimsSheet.setClaims_type("索赔");
		claimsSheet.setRegistrar(user.getDescription());
		model.addAttribute("claimsSheet",claimsSheet);
		model.addAttribute("product_categorys", commonService.gettype(null));
		return "qms/system/claimsSheet/addClaim";
	}
	
	/**
	 * 
	* @Title: saveClaimsSheet   
	* @Description: 保存方法 
	* @return ModelAndView    返回类型   
	* @throws   
	* @author dingzc 
	* @author 2018-3-19 下午2:25:56
	 */
	@RequestMapping("/save")
	public ModelAndView saveClaimsSheet(ClaimsSheet claimsSheet){
//		Date date=new Date();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");	
		try {
			if((claimsSheet.getId())!=null){
				claimsSheetService.updateByIdSelective(claimsSheet);
			}else{
				ClaimsSheet claimsSheetExist=claimsSheetService.getByClaimsNumber(claimsSheet);
				if(claimsSheetExist==null){
					claimsSheetService.insertSelective(claimsSheet);
				}else{
					return ajaxDoneError("该单号已存在，请重新填写单号");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxDoneError("保存失败，请联系相关人员");
		}
		return ajaxDoneSuccess("保存成功");
	}
	/**
	 * 
	* @Title: edit   
	* @Description:修改索赔处罚单方法  
	* @return String    返回类型   
	* @throws   
	* @author dingzc 
	* @author 2018-3-19 下午2:26:16
	 */
	@RequestMapping("/edit")
	public String edit(ClaimsSheet claimsSheet,Model model){
		ClaimsSheet claimsSheetEdit=claimsSheetService.getByid(claimsSheet);
		model.addAttribute("claimsSheet",claimsSheetEdit);
		model.addAttribute("product_categorys", commonService.gettype(null));
		if("处罚".equals(claimsSheetEdit.getClaims_type())){
			return "qms/system/claimsSheet/editTicket";
		}else{
			return "qms/system/claimsSheet/editClaim";
		}
	}
	/**
	 * 
	* @Title: editProcurement   
	* @Description: 索赔处罚采购登记  
	* @return String    返回类型   
	* @throws   
	* @author dingzc 
	* @author 2018-3-20 下午3:58:38
	 */
	@RequestMapping("/editProcurement")
	public String editProcurement(ClaimsSheet claimsSheet,Model model){
		ClaimsSheet claimsSheetEdit=claimsSheetService.getByid(claimsSheet);
		model.addAttribute("claimsSheet",claimsSheetEdit);
		model.addAttribute("product_categorys", commonService.gettype(null));
		if("处罚".equals(claimsSheetEdit.getClaims_type())){
			return "qms/system/claimsSheet/editProcurementTicket";
		}else{
			return "qms/system/claimsSheet/editProcurementClaim";
		}
	}
	
	/**
	 * 
	* @Title: 查找供应商  
	* @return String    返回类型   
	* @throws   
	* @author dingzc 
	* @author 2018-3-19 下午1:27:40
	 */
	@RequestMapping("/supplierList")
	public String findsupplier(Model model,PageParameter page,Onlinelookup vo,String flag,String data){
		BaseSearch bs=new BaseSearch();
		bs.setPage(page);
		bs.put("factoryS", vo.getFactoryS());
		bs.put("numbers", vo.getNumbers());
		bs.put("name", vo.getName());
		List<Onlinelookup> list=commonService.getAccountpages(bs);
		List<String> listfactory=commonService.getFactory();
		model.addAttribute("factorylist", listfactory);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "qms/system/claimsSheet/supplierList";
	}
	/**
	 * 
	* @Title: list   
	* @Description: 查找返工停线单
	* @return String    返回类型  
	* @throws   
	* @author dingzc 
	* @author 2018-3-19 下午1:28:12
	 */
	@RequestMapping("/reworkList")
	public String list(ReworkSheet reworkSheet,Model model,PageParameter page){
		List<ReworkSheet> list =reworkSheetService.getAllByPage(reworkSheet,page);
		model.addAttribute("list",list);
		model.addAttribute("reworkSheet",reworkSheet);
		model.addAttribute("page",page);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/system/claimsSheet/reworkSheetList";
	}
	
	/**
	 * 
	* @Title: list   
	* @Description: 查找用户  
	* @return String    返回类型   
	* @throws   
	* @author dingzc 
	* @author 2018-3-19 上午9:12:20
	 */
	@RequestMapping("/userList")
	public String list(Model model, SysMesUser user,PageParameter page)
	{
		List<SysMesUser> list = sysUserService.getAllByPage(page, user.getUserName(), user.getDescription());
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", user);
		return "qms/system/claimsSheet/userList";
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(ClaimsSheet claimsSheet){
		try {
			ClaimsSheet claimsSheetDel=claimsSheetService.getByid(claimsSheet);
			if("关闭".equals(claimsSheetDel.getIs_close())){
				return ajaxDoneError("关闭状态不允许删除");
			}else{
				claimsSheetService.deleteById(claimsSheet);
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
	* @Description: 导出索赔处罚  
	* @return String    返回类型   
	* @throws   
	* @author dingzc 
	* @author 2018-3-22 上午10:04:07
	 */
	  @RequestMapping("/exportExcel")
	    public String exportExcel(Model model,ClaimsSheet claimsSheet,
	            HttpServletRequest request, HttpServletResponse response) {
	        String retView = "/error/err";
	        try {
	            PageParameter page = new PageParameter();
	            page.setNumPerPage(50000);
	            List<ClaimsSheet> list =claimsSheetService.getAllByPage(claimsSheet,page);
	            String[] CN = { "类别","返工/停线单号","开单日期","责任部门","责任比例","原因","实际返工数","产品线","索赔/处罚单号"
	            		,"供应商","索赔/处罚原因","金额","申请人","开具日期","登记人","是否回签","未回签原因"};
	            List<String[]> excelList = new ArrayList<String[]>();
	            int index = 0;
	            int cols = CN.length;
	            for (int i = 0; i < list.size(); i++) {
	            	ClaimsSheet tmpVO = list.get(i);
	                String[] tmpStr = new String[cols];
	                tmpStr[index++] = tmpVO.getClaims_type();
	                tmpStr[index++] = tmpVO.getRework_number();
	                tmpStr[index++] = tmpVO.getRework_creation_time();
	                tmpStr[index++] = tmpVO.getDuty_depart();
	                tmpStr[index++] = tmpVO.getDuty_proportion();
	                tmpStr[index++] = tmpVO.getRework_reason();
	                tmpStr[index++] = tmpVO.getRework_count();
	                tmpStr[index++] = tmpVO.getProduct_category();
	                tmpStr[index++] = tmpVO.getClaims_number();
	                tmpStr[index++] = tmpVO.getClaims_supplier();
	                tmpStr[index++] = tmpVO.getClaims_reason();
	                tmpStr[index++] = tmpVO.getClaims_amount();
	                tmpStr[index++] = tmpVO.getClaims_applicant();
	                tmpStr[index++] = tmpVO.getCreation_time();
	                tmpStr[index++] = tmpVO.getRegistrar();
	                tmpStr[index++] = tmpVO.getIs_response();
	                tmpStr[index++] = tmpVO.getResponse_reason();
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
	                    contentType, "索赔/处罚单" + fname);
	            return null;
	        } catch (Exception e) {
	            logger.error(e.getMessage(), e);
	        }
	        return retView;
	    }
	  /**
	   * 
	  * @Title: exportProcurementExcel   
	  * @Description: 导出索赔处罚采购登记 
	  * @return String    返回类型   
	  * @throws   
	  * @author dingzc 
	  * @author 2018-3-22 上午10:14:26
	   */
	  @RequestMapping("/exportProcurementExcel")
	    public String exportProcurementExcel(Model model,ClaimsSheet claimsSheet,
	            HttpServletRequest request, HttpServletResponse response) {
	        String retView = "/error/err";
	        try {
	            PageParameter page = new PageParameter();
	            page.setNumPerPage(50000);
	            List<ClaimsSheet> list =claimsSheetService.getAllByPage(claimsSheet,page);
	            String[] CN = { "类别","返工/停线单号","开单日期","责任部门","责任比例","原因","实际返工数","产品线","索赔/处罚单号"
	            				,"供应商","索赔/处罚原因","金额","申请人","开具日期","登记人","是否回签","未回签原因","采购签收",
	            				"实际索赔金额","入账月份","关闭状态","备注"};
	            List<String[]> excelList = new ArrayList<String[]>();
	            int index = 0;
	            int cols = CN.length;
	            for (int i = 0; i < list.size(); i++) {
	            	ClaimsSheet tmpVO = list.get(i);
	                String[] tmpStr = new String[cols];
	                tmpStr[index++] = tmpVO.getClaims_type();
	                tmpStr[index++] = tmpVO.getRework_number();
	                tmpStr[index++] = tmpVO.getRework_creation_time();
	                tmpStr[index++] = tmpVO.getDuty_depart();
	                tmpStr[index++] = tmpVO.getDuty_proportion();
	                tmpStr[index++] = tmpVO.getRework_reason();
	                tmpStr[index++] = tmpVO.getRework_count();
	                tmpStr[index++] = tmpVO.getProduct_category();
	                tmpStr[index++] = tmpVO.getClaims_number();
	                tmpStr[index++] = tmpVO.getClaims_supplier();
	                tmpStr[index++] = tmpVO.getClaims_reason();
	                tmpStr[index++] = tmpVO.getClaims_amount();
	                tmpStr[index++] = tmpVO.getClaims_applicant();
	                tmpStr[index++] = tmpVO.getCreation_time();
	                tmpStr[index++] = tmpVO.getRegistrar();
	                tmpStr[index++] = tmpVO.getIs_response();
	                tmpStr[index++] = tmpVO.getResponse_reason();
	                tmpStr[index++] = tmpVO.getIs_sign_in();
	                tmpStr[index++] = tmpVO.getActual_claim_amount();
	                tmpStr[index++] = tmpVO.getBooked_month();
	                tmpStr[index++] = tmpVO.getIs_close();
	                tmpStr[index++] = tmpVO.getRemark();
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
	                    contentType, "索赔/处罚单采购登记" + fname);
	            return null;
	        } catch (Exception e) {
	            logger.error(e.getMessage(), e);
	        }
	        return retView;
	    }
}
