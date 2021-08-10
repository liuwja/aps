package com.peg.qms.controller.jxmb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ProductionLine;
import com.peg.model.jxmb.PerDeparment;
import com.peg.model.jxmb.PerformanceCheck;
import com.peg.model.system.EditReason;
import com.peg.qms.controller.BaseController;
import com.peg.service.CommonServiceI;
import com.peg.service.jxmb.DeparmentServicel;
import com.peg.service.jxmb.PerformanceCheckServicel;
import com.peg.service.system.EditReasonServiceI;
import com.peg.web.util.DateEditor;


/**年度绩效目标考核设定
 * @Class: PerformanceCheckController @TODO:
 */
@Controller
@RequestMapping("system/performanceCheck")
public class PerformanceCheckController  extends BaseController{
	
	@Autowired
	private PerformanceCheckServicel performanceCheckService;

	@Autowired
	private CommonServiceI commonService;
	
	/**
	 * 查询部门基础信息接口
	 */
	@Autowired
	private DeparmentServicel dtService;
	
	/**
	 * 绩效修改记录操作接口
	 */
	@Autowired
	private EditReasonServiceI editReasonServiceI;
	
	@RequestMapping("/list")
	public String list(PerformanceCheck performanceCheck,Model model,PageParameter page){
		BaseSearch bs=new BaseSearch();
	    bs.setPage(page); 
		bs.put("chekyear", performanceCheck.getYear());
		bs.put("department", performanceCheck.getDepartment() == null || "null".equals(performanceCheck.getDepartment()) ? "" : performanceCheck.getDepartment());
		bs.put("targetclass", performanceCheck.getTargetclass());
		bs.put("indexcontent", performanceCheck.getIndexcontent());
		bs.put("performancecontent", performanceCheck.getPerformancecontent());
		bs.put("weight", performanceCheck.getWeight());
		bs.put("assessmentmethod", performanceCheck.getAssessmentmethod());
		bs.put("recordtime", performanceCheck.getRecordtime());
		bs.put("factoryNumber", performanceCheck.getFactoryNumber());
		List<PerformanceCheck> list =performanceCheckService.getAllBypage(bs);
		model.addAttribute("list",list);
//		bs=new BaseSearch();
//		bs.setPage(page); 
//		List<PerformanceCheck> list2 =performanceCheckService.getAllBypage(bs);
//		model.addAttribute("list2",list2);
		model.addAttribute("performanceCheck",performanceCheck);
		model.addAttribute("page",page);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/performanceCheck/list";
		
	}
	@RequestMapping("/add")
	public String add(Model model){
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/performanceCheck/add";
	}
	
	@RequestMapping("/save")
	public ModelAndView saveCheckltem(PerformanceCheck performanceCheck){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");	
		try {
			if(performanceCheck.getRecordtime() !=null){
				performanceCheck.setRecordtime(sdf.parse(performanceCheck.getRecord()));		
			}
			performanceCheckService.setBaseMapper();
			performanceCheck.setRecordtime(date);
			performanceCheck.setRecord(getCurrentUserName());
			performanceCheckService.insert(performanceCheck);
		} catch (Exception e) {
			if(StringUtils.isNotBlank(e.getMessage()) && e.getMessage().contains("ORA-00001")){
				return ajaxDoneError("不允许添加相同的部门、绩效大类、绩效类");
			}
			e.printStackTrace();
			return ajaxDoneError("更新失败，请联系相关人员");
		}
		return ajaxDoneSuccess("保存成功");
	}
	@RequestMapping("/edit")
	public String edit(@RequestParam(value="id",required=false)Long id,Model model){
		PerformanceCheck performanceCheck=performanceCheckService.selectByPrimaryKey(id);
		model.addAttribute("vo",performanceCheck);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/performanceCheck/edit";
	}
	@RequestMapping("/update")
	public ModelAndView update(PerformanceCheck performanceCheck){
		//在修改绩效考核指标设定时，如果工厂下拉框改变之后，部门下拉框则默认选中“未选择”，那么传递到后台时就是一个空的字符串
		//如果工厂编号和部门编号传递过来是空的字符串，则给出提示信息
		if(performanceCheck.getFactoryNumber() == ""){
			return ajaxDoneError("未选择工厂信息");
		}else if(performanceCheck.getDepartmentNumber() == ""){
			return ajaxDoneError("未选择部门信息");
		}
		//原来将单位设置为当前用户姓名，现在改成页面输入
		//performanceCheck.setCompany(getCurrentUserName());
		
		//获取当前用户
		String currentUserName = getCurrentUserName();
		
		//比较字段，记录修改信息,存入到基础查询类中
		EditReason editReason = onUpdateCheck(performanceCheck,currentUserName);
		
		try {
			performanceCheckService.updateByPrimaryKeySelective(performanceCheck);
			//修改成功后记录修改记录,返回的int类型数据代表修改的记录条数。
			//如果想获取插入记录的主键，使用editReason.getId()可以获取
			int number = editReasonServiceI.insertSelective(editReason);
			System.out.println("number: "+ number);
			System.out.println("插入记录ID: "+ editReason.getId());
		} catch (Exception e) {
			if(StringUtils.isNotBlank(e.getMessage()) && e.getMessage().contains("ORA-00001")){
				return ajaxDoneError("不允许添加相同的部门、绩效大类、绩效类");
			}
			e.printStackTrace();
			return ajaxDoneError("更新失败，请联系相关人员");
		}
		return ajaxDoneSuccess("修改成功");
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(PerformanceCheck performanceCheck){
		performanceCheckService.deleteByPrimaryKey(performanceCheck.getId());
		return ajaxDoneSuccess("删除成功");
	}
	
	/**
	 * 修改绩效考核目标时记录修改信息
	 * @param performanceCheck 记录修改后的信息
	 * @return 修改内容构成的字符串（一般只包含数值的修改，这里包含了大多数字段）
	 */
	public EditReason onUpdateCheck(PerformanceCheck performanceCheck, String currentUserName){
		//获取待修改记录id
		//logger.debug("performanceCheck.getId()待修改记录id: "+ performanceCheck.getId());
		
		String newFactoryNumber = performanceCheck.getFactoryNumber();//修改后的工厂编号
		System.out.println("修改后的工厂编号："+newFactoryNumber);
		String newDepartmentNumber = performanceCheck.getDepartmentNumber();//修改后的部门编号
		//新建一个PerformanceCheck（绩效考核指标）对象，用来存储待修改前记录
		PerformanceCheck pfc = null;
		//获取待修改前的记录
		pfc = performanceCheckService.selectByPrimaryKey(performanceCheck.getId());
		//新建一个PerDeparment对象，用来存储修改前的部门记录
		PerDeparment perDepartment = null;
		//根据部门编号查询部门信息。pfc.getDepartment()获取的是部门的名称
		perDepartment = dtService.selectByDepartmentNumber(pfc.getDepartmentNumber());
		
		StringBuffer updateContentString = new StringBuffer();//新建一个StringBuffer字符串对象，用于存储修改前后变化的内容
		ProductionLine productionLine = null;//声明一个工厂部门对象，用来设置查询工厂的条件
		String oldFactoryNumber = pfc.getFactoryNumber();//修改前工厂编号
		String oldDepartmentNumber = perDepartment.getDepartmentNumber();//修改前部门编号
		
		
		String newFactoryName = ""; //修改后工厂名称
		String newDepartmentName = ""; //修改后部门名称
		productionLine = new ProductionLine();
		productionLine.setFactory_number(newFactoryNumber);//设置工厂编号
		
		//获取修改后工厂名称
		newFactoryName = getFactoryName(productionLine);
		//获取修改后部门名称
		newDepartmentName = getDepartmentName(newDepartmentNumber);
		
		//年度修改前后信息
		Date oldCheckYear = pfc.getChekyear();//修改前年度（java.util.Date类型）
		Date newCheckYear = performanceCheck.getChekyear();//修改后年度(java.util.Date类型)
		//System.out.println("oldCheckYear: "+ oldCheckYear);
		//System.out.println("newCheckYear: "+ newCheckYear);
		//修改前年度格式化，只包含年份信息，如时间2012-09-06 14:16:37格式化之后为2012
		String oldCheckYearFormat = DateEditor.formatDate(oldCheckYear, "yyyy");
		//修改后年度格式化，只包含年份信息
		String newCheckYearFormat = DateEditor.formatDate(newCheckYear, "yyyy");
		//System.out.println("oldCheckYearFormat: "+ oldCheckYearFormat);
		//System.out.println("newCheckYearFormat: "+ newCheckYearFormat);
		
		//修改前后信息
		String oldTargetClass = pfc.getTargetclass();//修改前绩效目标大类
		String oldIndexContent = pfc.getIndexcontent();
		String oldPerformanceContent = pfc.getPerformancecontent();
		String oldWeight = pfc.getWeight();
		String oldCompany = pfc.getCompany();
		String oldFormula = pfc.getFormula();
		String oldReferenceValue = pfc.getReferencevalue();
		String oldMedian = pfc.getMedian();
		String oldTargetvalue = pfc.getTargetvalue();
		String oldRecord = pfc.getRecord();
		
		String newTargetClass = performanceCheck.getTargetclass();//修改后绩效目标大类
		String newIndexContent = performanceCheck.getIndexcontent();
		String newPerformanceContent = performanceCheck.getPerformancecontent();
		String newWeight = performanceCheck.getWeight();
		String newCompany = performanceCheck.getCompany();
		String newFormula = performanceCheck.getFormula();
		String newReferenceValue = performanceCheck.getReferencevalue();
		String newMedian = performanceCheck.getMedian();
		String newTargetvalue = performanceCheck.getTargetvalue();
		String newRecord = performanceCheck.getRecord();
		String newUpdateReason = performanceCheck.getUpdateReason();
		
		/*************对比前后记录不一致信息**************/
		if(!oldCheckYearFormat.equals(newCheckYearFormat)){
			//说明年度发生了改变,将改变信息存储到字符串中
			updateContentString.append("年度：").append(oldCheckYearFormat).append("->").append(newCheckYearFormat).append(";");
			//System.out.println("updateContentString当前内容： "+updateContentString);
		}
		//如果工厂编号不一致，说明修改了工厂信息
		if(!oldFactoryNumber.equals(newFactoryNumber)){
			/*获取工厂列表
			List<ProductionLine> productionLineList = (List<ProductionLine>)commonService.getFactorysBySite(null);
			for (ProductionLine productionLine : productionLineList) {
				System.out.println(productionLine);
			}*/
			updateContentString.append("工厂名称：").append(pfc.getFactoryName()).append("->").append(newFactoryName).append(";");
		}
		if(!oldDepartmentNumber.equals(newDepartmentNumber)){
			//说明修改了部门信息，但是部门编号改变，部门名称不一定改变
			
			String oldDepartmentName = getDepartmentName(oldDepartmentNumber);//原来部门名称
			if(!oldDepartmentName.equals(newDepartmentName)){
				//说明部门名称改变
				updateContentString.append("部门名称：").append(oldDepartmentName).append("->").append(newDepartmentName).append(";");
			}
			//System.out.println("现在字符串的内容为："+updateContentString);
		}	
		
		if(!oldTargetClass.equals(newTargetClass)){
			//说明绩效目标大类修改，记录修改前后信息
			updateContentString.append("绩效目标大类：").append(oldTargetClass).append("->").append(newTargetClass).append(";");
		}
		
		if(!oldIndexContent.equals(newIndexContent)){
			updateContentString.append("指标内容：").append(oldIndexContent).append("->").append(newIndexContent).append(";");
		}
		
		if(!oldPerformanceContent.equals(newPerformanceContent)){
			updateContentString.append("指标类型：").append(oldPerformanceContent).append("->").append(newPerformanceContent).append(";");
		}
		
		if(!oldWeight.equals(newWeight)){
			updateContentString.append("权重：").append(oldWeight).append("->").append(newWeight).append(";");
		}
		
		if(!oldCompany.equals(newCompany)){
			updateContentString.append("单位：").append(oldCompany).append("->").append(newCompany).append(";");
		}
		
		if(!oldFormula.equals(newFormula)){
			updateContentString.append("计算公式：").append(oldFormula).append("->").append(newFormula).append(";");
		}
		
		if(!oldReferenceValue.equals(newReferenceValue)){
			updateContentString.append("基准值：").append(oldReferenceValue).append("->").append(newReferenceValue).append(";");
		}
		
		if(!oldMedian.equals(newMedian)){
			updateContentString.append("中间值：").append(oldMedian).append("->").append(newMedian).append(";");
		}
		
		if(!oldTargetvalue.equals(newTargetvalue)){
			updateContentString.append("目标值：").append(oldTargetvalue).append("->").append(newTargetvalue).append(";");
		}
		
		//System.out.println("现在字符串的内容为："+updateContentString);
		
		/*BaseSearch bs = new BaseSearch();
		bs.put("lastUpdateUser", currentUserName);//修改人记录为当前用户
		bs.put("updateReason", newUpdateReason);
		bs.put("updateType", "绩效考核指标设定修改");
		bs.put("updateContent", updateContentString);//将修改内容存放到键“updateContent”中
		bs.put("createTime", new Date());
		bs.put("lastUpdateTime", new Date());
		bs.put("factoryNumber", newFactoryNumber);
		bs.put("factoryName", newFactoryName);
		bs.put("departmentNumber", newDepartmentNumber);
		bs.put("departmentName", newDepartmentName);
		bs.put("targetClass", newTargetClass);
		bs.put("indexContent", newIndexContent);
		bs.put("performanceContent", newPerformanceContent);
		bs.put("weigth", newWeight);
		bs.put("company", newCompany);
		bs.put("column1", "");
		bs.put("column1", "");
		bs.put("column1", "");*/
		
		EditReason editReason = new EditReason();
		editReason.setLastUpdateUser(currentUserName);
		editReason.setUpdateReason(newUpdateReason);
		editReason.setUpdateType("绩效考核指标设定修改");
		editReason.setUpdateContent(updateContentString.toString());//StringBuffer要转化为String类型的数据
		editReason.setCreateTime(new Date());
		editReason.setLastUpdateTime(new Date());
		editReason.setFactoryNumber(newFactoryNumber);
		editReason.setFactoryName(newFactoryName);
		editReason.setDepartmentNumber(newDepartmentNumber);
		editReason.setDepartmentName(newDepartmentName);
		editReason.setTargetClass(newTargetClass);
		editReason.setIndexContent(newIndexContent);
		editReason.setPerformanceContent(newPerformanceContent);
		editReason.setWeight(newWeight);
		editReason.setCompany(newCompany);
		editReason.setColumn1("");
		editReason.setColumn2("");
		editReason.setColumn3("");
		return editReason;
	}
	
	/**
	 * 根据工厂编号编号获取工厂名称
	 * @param productionLine
	 * @return
	 */
	public String getFactoryName(ProductionLine productionLine){
		String factoryName = "";
		List<ProductionLine> productionLineList = (List<ProductionLine>)commonService.getFactorysBySite(productionLine);//获得新的工厂信息
		for (ProductionLine productionLine2 : productionLineList) {
			//System.out.println("现工厂名称： "+productionLine2.getFactory());
			factoryName = productionLine2.getFactory();
		}
		return factoryName;
	}
	
	/**
	 * 根据部门编号获取部门名称
	 * @param departmentNumber 部门编号
	 * @return 部门名称
	 */
	public String getDepartmentName(String departmentNumber){
		PerDeparment perDeparment = (PerDeparment)dtService.selectByDepartmentNumber(departmentNumber);
		if(perDeparment.getDepartmentName() != null && perDeparment.getDepartmentName() != ""){
			return perDeparment.getDepartmentName();
		}
		return "";
	}
}
