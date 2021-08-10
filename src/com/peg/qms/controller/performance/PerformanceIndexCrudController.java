package com.peg.qms.controller.performance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.DataValidationConstraint.OperatorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.ProductionLine;
import com.peg.model.bph.Group;
import com.peg.model.bph.GroupCategory;
import com.peg.model.bph.Index;
import com.peg.model.bph.Item;
import com.peg.model.bph.MonthAssessment;
import com.peg.model.performance.Department;
import com.peg.model.performance.EditReason;
import com.peg.model.performance.MonthPerformance;
import com.peg.model.performance.PerformanceIndex;
import com.peg.model.performance.YearPerformance;
import com.peg.qms.controller.BaseController;
import com.peg.service.CommonServiceI;
import com.peg.service.performance.DepartmentService;
import com.peg.service.performance.EditReasonService;
import com.peg.service.performance.PerformanceCommon;
import com.peg.service.performance.PerformanceIndexCrudService;
import com.peg.web.util.DateEditor;
import com.peg.web.util.ExcelUtilities;

/**
 * 绩效指标操作控制层
 * @author xuanm
 *
 */
@Controller
@RequestMapping("ptm/performanceIndex")
public class PerformanceIndexCrudController extends BaseController implements PerformanceCommon{

	/**
	 * 绩效指标操作接口
	 */
	@Autowired
	private PerformanceIndexCrudService performanceIndexCrudService;

	/**
	 * 通用操作接口
	 */
	@Autowired
	private CommonServiceI commonService;
	
	/**
	 * 查询部门基础信息接口
	 */
	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * 绩效修改记录操作接口
	 */
	@Autowired
	private EditReasonService editReasonService;
	
	/**
	 * 获取绩效指标
	 * @param performanceIndex 绩效指标类对象
	 * @param model
	 * @param page 分页信息
	 * @return
	 */
	
	/**
	 * 前台页面字段检查结果
	 */
	private String checkColumnResult = "";
	
	private StringBuffer stringBuffer = new StringBuffer("");
	
	@RequestMapping("/list")
	public String list(PerformanceIndex performanceIndex,Model model, PageParameter page){
		//设置年度查询条件，用于回传到前台页面的查询条件中
		String year = performanceIndex.getYear();
//	    String pattern = "YYYY";
//	    Date parseDate = DateEditor.parseDate(year, pattern);
//	    performanceIndex.setCheckYear(parseDate);
		
		BaseSearch bs=new BaseSearch();
	    bs.setPage(page); 
	    bs.put("checkYear", performanceIndex.getYear());//这里使用字符串year替代checkYear当做年度的查询条件
	    bs.put("factoryNumber", performanceIndex.getFactoryNumber());
	    bs.put("departmentNumber", performanceIndex.getDepartmentNumber() == null || "null".equals(performanceIndex.getDepartmentNumber()) ? "" : performanceIndex.getDepartmentNumber());
		bs.put("performanceTargetClass", performanceIndex.getPerformanceTargetClass());
		bs.put("performanceType", performanceIndex.getPerformanceType());
		bs.put("indexContent", performanceIndex.getIndexContent());
		bs.put("indexType", performanceIndex.getIndexType());
		bs.put("weight", performanceIndex.getWeight());
		bs.put("assessmentMethod", performanceIndex.getAssessmentMethod());
		bs.put("createTime", performanceIndex.getCreateTime());
		
		List<PerformanceIndex> list = performanceIndexCrudService.getAllByPage(bs);
		model.addAttribute("list",list);
		model.addAttribute("performanceIndex",performanceIndex);
		model.addAttribute("page",page);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		
/*		List<PerformanceIndex> performanceList = performanceIndexCrudService.getMonthPerformance(bs);
		System.out.println("年份列表长度"+performanceList.size());
		for (PerformanceIndex performanceIndex2 : performanceList) {
			List<MonthPerformance>  monthList = performanceIndex2.getMonthList();
			System.out.println("月份列表长度："+monthList.size());
			for (MonthPerformance monthPerformance : monthList) {
				System.out.println(monthPerformance);
			}
		}*/
		
		return "qms/bph/base/performance/performanceIndex/list";
	}
	
	/**
	 * 跳转到绩效指标新增页面
	 * @param model 回传信息
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model){
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/performance/performanceIndex/add";
	}
	
	/**
	 * 新增绩效指标保存方法
	 * @param performanceIndex 绩效指标对象
	 * @return 添加状态信息：成功或异常信息
	 */
	@RequestMapping("/save")
	public ModelAndView save(PerformanceIndex performanceIndex){
		
		//字段完整性检查
		if(checkColumn(performanceIndex,CREATE)){
			return ajaxDoneError(checkColumnResult);
		}
		
		//设置待插入记录信息
		setPerformanceIndexInfo(performanceIndex,CREATE);
		
		try {			
			performanceIndexCrudService.setBaseMapper();
						
			//插入数据库(插入指定的字段)
			performanceIndexCrudService.insertSelective(performanceIndex);
		} catch (Exception e) {
			if(StringUtils.isNotBlank(e.getMessage()) && e.getMessage().contains("ORA-00001")){
				return ajaxDoneError(INPUT_UPDATE_ERROR_INFO);
			}
			e.printStackTrace();
			return ajaxDoneError(INSERT_ERROR_INFO);
		}
		return ajaxDoneSuccess(INSERT_SUCCESS_INFO);
	} 
	
	/**
	 * 跳转到修改页面
	 * @param id 待修改记录id
	 * @param model 回传待修改数据
	 * @return 修改页面视图
	 */
	@RequestMapping("/edit")
	public String edit(@RequestParam(value="id",required=false)Long id,Model model){
		PerformanceIndex performanceIndex=performanceIndexCrudService.selectByPrimaryKey(id);
		model.addAttribute("vo",performanceIndex);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/performance/performanceIndex/edit";
	}
	
	/**
	 * 修改绩效指标
	 * @param performanceIndex 绩效指标类对象
	 * @return 修改是否成功的状态
	 */
	@RequestMapping("/update")
	public ModelAndView update(PerformanceIndex performanceIndex){
		Date date=new Date();
		//字段完整性检查
		if(checkColumn(performanceIndex,UPDATE)){
			return ajaxDoneError(checkColumnResult);
		}
		
		//设置待修改记录字段信息
		setPerformanceIndexInfo(performanceIndex,UPDATE);
		
		//比较字段，记录修改信息,存入到基础查询类中
		EditReason editReason = onUpdateCheck(performanceIndex);
		
		try {
			//获取工厂名称
			String factoryName = getFactoryName(performanceIndex.getFactoryNumber());
			
			//获取部门名称
			String departmentName = getDepartmentName(performanceIndex.getDepartmentNumber());
			
			
			performanceIndexCrudService.setBaseMapper();
			//重置工厂部门信息
			performanceIndex.setFactoryName(factoryName);
			performanceIndex.setDepartmentName(departmentName);
			performanceIndex.setUpdateTime(date);
			performanceIndex.setUpdateUser(getCurrentUserName());
			//执行修改
			performanceIndexCrudService.updateByPrimaryKeySelective(performanceIndex);
			
			//修改成功后记录修改记录,返回的int类型数据代表修改的记录条数。
			//如果想获取插入记录的主键，使用editReason.getId()可以获取
			editReasonService.insertSelective(editReason);
		} catch (Exception e) {
			if(StringUtils.isNotBlank(e.getMessage()) && e.getMessage().contains("ORA-00001")){
				return ajaxDoneError(INPUT_UPDATE_ERROR_INFO);
			}
			e.printStackTrace();
			return ajaxDoneError(UPDATE_ERROR_INFO);
		}
		return ajaxDoneSuccess(UPDATE_SUCCESS_INFO);
	}
	
	/**
	 * 修改信息时记录修改信息
	 * @param performanceIndex
	 * @return
	 */
	public EditReason onUpdateCheck(PerformanceIndex performanceIndex){	
		//新建一个PerformanceIndex（绩效考核指标）对象，用来存储待修改前记录
		PerformanceIndex pfi = null;
		//获取待修改前的记录
		pfi = performanceIndexCrudService.selectByPrimaryKey(performanceIndex.getId());
		
		String oldFactoryNumber = pfi.getFactoryNumber();//修改前的工厂编号
		String newFactoryNumber = performanceIndex.getFactoryNumber();//修改后的工厂编号
		String newFactoryName = getFactoryName(newFactoryNumber);//修改后工厂名称
		
		String oldDepartmentNumber = pfi.getDepartmentNumber();//修改前部门编号
		String newDepartmentNumber = performanceIndex.getDepartmentNumber();//修改后的部门编号
		String newDepartmentName = getDepartmentName(newDepartmentNumber);//修改后部门名称


		//年度修改前后信息
		Date oldCheckYear = pfi.getCheckYear();//修改前年度（java.util.Date类型）
		Date newCheckYear = performanceIndex.getCheckYear();//修改后年度(java.util.Date类型)
		//System.out.println("oldCheckYear: "+ oldCheckYear);
		//System.out.println("newCheckYear: "+ newCheckYear);
		//修改前年度格式化，只包含年份信息，如时间2012-09-06 14:16:37格式化之后为2012
		String oldCheckYearFormat = DateEditor.formatDate(oldCheckYear, "yyyy");
		//修改后年度格式化，只包含年份信息
		String newCheckYearFormat = DateEditor.formatDate(newCheckYear, "yyyy");
		//System.out.println("oldCheckYearFormat: "+ oldCheckYearFormat);
		//System.out.println("newCheckYearFormat: "+ newCheckYearFormat);
		
		//修改前后信息
		String oldPerformanceTargetClass = pfi.getPerformanceTargetClass();//修改前绩效目标大类
		String oldPerformanceType = pfi.getPerformanceType();//修改前绩效类型
		String oldIndexContent = pfi.getIndexContent();
		String oldIndexType = pfi.getIndexType();
		String oldWeight = pfi.getWeight();
		String oldCompany = pfi.getCompany();
		String oldFormula = pfi.getFormula();
		String oldReferenceValue = pfi.getReferenceValue();
		String oldMiddleValue = pfi.getMiddleValue();
		String oldTargetValue = pfi.getTargetValue();
		
		//修改后信息
		String newPerformanceTargetClass = performanceIndex.getPerformanceTargetClass();//修改后绩效目标大类
		String newPerformanceType = performanceIndex.getPerformanceType();
		String newIndexContent = performanceIndex.getIndexContent();
		String newIndexType = performanceIndex.getIndexType();
		String newWeight = performanceIndex.getWeight();
		String newCompany = performanceIndex.getCompany();
		String newFormula = performanceIndex.getFormula();
		String newReferenceValue = performanceIndex.getReferenceValue();
		String newMiddleValue = performanceIndex.getMiddleValue();
		String newTargetValue = performanceIndex.getTargetValue();
		String newUpdateReason = performanceIndex.getUpdateReason();
		
		//清空stringBuffer变量
		stringBuffer.setLength(0);
		compareChangeInfo(oldCheckYearFormat,newCheckYearFormat,CHECKYEAR);
		//如果工厂编号不一致，说明修改了工厂信息
		if(!oldFactoryNumber.equals(newFactoryNumber)){
			//获取工厂列表
			List<ProductionLine> productionLineList = (List<ProductionLine>)commonService.getFactorysBySite(null);
			for (ProductionLine productionLine : productionLineList) {
				System.out.println(productionLine);
			}
			stringBuffer.append(FACTORYNAME+"：").append(pfi.getFactoryName()).append("->").append(newFactoryName).append(";");
		}
		if(!oldDepartmentNumber.equals(newDepartmentNumber)){
			//说明修改了部门信息，但是部门编号改变，部门名称不一定改变
			
			//String oldDepartmentName = getDepartmentName(oldDepartmentNumber);//原来部门名称
			String oldDepartmentName = pfi.getDepartmentName();
			if(!oldDepartmentName.equals(newDepartmentName)){
				//说明部门名称改变
				stringBuffer.append(DEPARTMENTNAME+"：").append(oldDepartmentName).append("->").append(newDepartmentName).append(";");
			}
			//System.out.println("现在字符串的内容为："+updateContentString);
		}
		compareChangeInfo(oldPerformanceTargetClass,newPerformanceTargetClass,PERFORMANCETARGETCLASS);
		compareChangeInfo(oldPerformanceType,newPerformanceType,PERFORMANCETYPE);
		compareChangeInfo(oldIndexContent,newIndexContent,INDEXCONTENT);
		compareChangeInfo(oldIndexType,newIndexType,INDEXTYPE);
		compareChangeInfo(oldWeight,newWeight,WEIGHT);
		compareChangeInfo(oldCompany,newCompany,COMPANY);
		compareChangeInfo(oldFormula,newFormula,FORMULA);
		compareChangeInfo(oldReferenceValue,newReferenceValue,INDEX_REFERENCEVALUE);
		compareChangeInfo(oldMiddleValue,newMiddleValue,INDEX_MIDDLEVALUE);
		compareChangeInfo(oldTargetValue,newTargetValue,INDEX_TARGETVALUE);
		
		//如果什么都没有修改，那么设置修改内容为无
		if(stringBuffer.length() == 0){
			stringBuffer.append(UPDATECONTENT);
		}
		
		EditReason editReason = new EditReason();
		editReason.setUpdateTime(new Date());
		editReason.setUpdateUser(getCurrentUserName());
		editReason.setUpdateReason(newUpdateReason);
		editReason.setUpdateType(UPDATESTRING);
		editReason.setOperatorModule(INDEX_SET);
		editReason.setUpdateContent(stringBuffer.toString());//StringBuffer要转化为String类型的数据
		editReason.setCreateTime(new Date());
		editReason.setCreateUser(getCurrentUserName());
		editReason.setFactoryNumber(newFactoryNumber);
		editReason.setFactoryName(newFactoryName);
		editReason.setDepartmentNumber(newDepartmentNumber);
		editReason.setDepartmentName(newDepartmentName);
		editReason.setPerformanceTargetClass(newPerformanceTargetClass);
		editReason.setPerformanceType(newPerformanceType);
		editReason.setIndexContent(newIndexContent);
		editReason.setIndexType(newIndexType);
		editReason.setWeight(newWeight);
		editReason.setCompany(newCompany);
		editReason.setColumn1("");
		editReason.setColumn2("");
		editReason.setColumn3("");
		return editReason;
	}
	
	/**
	 * 根据ID删除绩效指标记录
	 * @param performanceCheck
	 * @return
	 */
	@RequestMapping("/delete")
	public ModelAndView delete(PerformanceIndex performanceIndex){
		try {
			performanceIndexCrudService.deleteByPrimaryKey(performanceIndex.getId());
		} catch (Exception e) {
			if(e.getMessage().contains("ORA-02292")){
				return ajaxDoneError(HAS_OTHER_RELEVANT_ORDER);
			}
		}
		return ajaxDoneSuccess(DELETE_SUCCESS_INFO);
	}
	
	/**
	 * 导出excel
	 * 
	 * @param vo
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/excelOutput")
	public void excelOutput(PerformanceIndex performanceIndex, Model model,PageParameter page,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			BaseSearch bs = new BaseSearch();
			bs.setPage(page); 
		    bs.put("checkYear", performanceIndex.getYear());//这里使用字符串year替代checkYear当做年度的查询条件
		    bs.put("factoryNumber", performanceIndex.getFactoryNumber());
		    bs.put("departmentNumber", performanceIndex.getDepartmentNumber() == null || "null".equals(performanceIndex.getDepartmentNumber()) ? "" : performanceIndex.getDepartmentNumber());
			bs.put("performanceTargetClass", performanceIndex.getPerformanceTargetClass());
			bs.put("performanceType", performanceIndex.getPerformanceType());
			bs.put("indexContent", performanceIndex.getIndexContent());
			bs.put("indexType", performanceIndex.getIndexType());
			bs.put("weight", performanceIndex.getWeight());
			bs.put("assessmentMethod", performanceIndex.getAssessmentMethod());
			bs.put("createTime", performanceIndex.getCreateTime());
			List<PerformanceIndex> list = performanceIndexCrudService.getAllByPage(bs);
			String [] CN = {CHECKYEAR,FACTORYNAME,DEPARTMENTNAME,PERFORMANCETARGETCLASS,PERFORMANCETYPE,
					INDEXCONTENT,INDEXTYPE,WEIGHT,COMPANY,FORMULA,INDEX_REFERENCEVALUE,INDEX_MIDDLEVALUE,
					INDEX_TARGETVALUE,CREATE_USER,CREATE_TIME};
			List<String[]> excelList = new ArrayList<String[]>();
			for(PerformanceIndex per : list){
				String[] itemStr = new String[ CN.length];
				itemStr[0] = DateEditor.formatDate(per.getCheckYear(), "yyyy");
				itemStr[1] = per.getFactoryName();
				itemStr[2] = per.getDepartmentName();
				itemStr[3] = per.getPerformanceTargetClass();
				itemStr[4] = per.getPerformanceType();
				itemStr[5] = per.getIndexContent();
				itemStr[6] = per.getIndexType();
				itemStr[7] = per.getWeight();
				itemStr[8] = per.getCompany();
				itemStr[9] = per.getFormula();
				itemStr[10] = per.getReferenceValue();
				itemStr[11] = per.getMiddleValue();
				itemStr[12] = per.getTargetValue();
				itemStr[13] = per.getCreateUser();
				itemStr[14] = DateEditor.formatDate(per.getCreateTime(), "yyyy-MM-dd");
				excelList.add(itemStr);				
			}
			
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			
				ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
				String contentType =  "application/msexcel" ;
				ExcelUtilities.downloadExcel(request, response, filePath, contentType, INDEX_SET+fname);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	
	/**
	 * 根据工厂编号编号获取工厂名称
	 * @param factoryNumber 工厂编号
	 * @return
	 */
	public String getFactoryName(String factoryNumber){
		ProductionLine productionLine = new ProductionLine();
		productionLine.setFactory_number(factoryNumber);
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
		
		Department department= (Department)departmentService.selectByDepartmentNumber(departmentNumber);
		if(department.getDepartmentName() != null && department.getDepartmentName() != ""){
			return department.getDepartmentName();
		}
		return "";
	}
	
	/**
	 * 字段完整性检查<br>对于下拉框，框架不会检测是否选择。对于必填字段，框架会校验，不能填写空字符串，但是能填写以空格开头和结尾的字符串 ，所以需要处理
	 * @param performanceIndex 指标类
	 * @param columnCheckResult 用于接收检查结果
	 * @param operatorType 操作类型：增加、修改、删除
	 * @return 字段检查结果
	 */
	public boolean checkColumn(PerformanceIndex performanceIndex,String operatorType){
		
		String factoryNumber = performanceIndex.getFactoryNumber();//工厂编号
		String departmentNumber = performanceIndex.getDepartmentNumber();//部门编号
		String checkYearString = performanceIndex.getCheckYearString();//年度信息。前台传递到后台的格式为：YYYY(yyyy),如2018
		String performanceTargetClass = performanceIndex.getPerformanceTargetClass().trim();
		String performanceType = performanceIndex.getPerformanceType().trim();
		String indexContent = performanceIndex.getIndexContent().trim();
		String indexType = performanceIndex.getIndexType().trim();
		String company = performanceIndex.getCompany().trim();
		String formula = performanceIndex.getFormula().trim();
		
		String referenceValue = performanceIndex.getReferenceValue().trim();
		String middleValue = performanceIndex.getMiddleValue().trim();
		String targetValue = performanceIndex.getTargetValue().trim();
		
		
				
		//检查工厂编号是否正确获取
		if(checkColumnIsEmptyOrNull(factoryNumber)){
			checkColumnResult = NOTSELECT+FACTORYNAME+INFORMATION;
			return true;
		}
		//检查部门编号是否正确获取
		if (checkColumnIsEmptyOrNull(departmentNumber)) {
			checkColumnResult = NOTSELECT+DEPARTMENTNAME+INFORMATION;
			return true;
		}
		//检查年度信息获取是否正确
		if(checkColumnIsEmptyOrNull(checkYearString)){
			checkColumnResult = NOTSELECT+CHECKYEAR+INFORMATION;
			return true;
		}
		
		if(checkColumnIsEmptyOrNull(performanceTargetClass)){
			checkColumnResult = INPUTERROR+PERFORMANCETARGETCLASS+INFORMATION;
			return true;
		}else{
			performanceIndex.setPerformanceTargetClass(performanceTargetClass);
		}
		//检查绩效类型是否选择（下拉框传递的值前后没有空格，else可以不执行）
		if(checkColumnIsEmptyOrNull(performanceType)){
			checkColumnResult = NOTSELECT+PERFORMANCETYPE+INFORMATION;
			return true;
		}else{
			performanceIndex.setPerformanceType(performanceType);;
		}
		//检查指标内容
		if(checkColumnIsEmptyOrNull(indexContent)){
			checkColumnResult = INPUTERROR+INDEXCONTENT+INFORMATION;
			return true;
		}else{
			performanceIndex.setIndexContent(indexContent);;
		}
		//检查指标类型
		if(checkColumnIsEmptyOrNull(indexType)){
			checkColumnResult = INPUTERROR+INDEXTYPE+INFORMATION;
			return true;
		}else{
			performanceIndex.setIndexType(indexType);;
		}
		//检查单位
		if(checkColumnIsEmptyOrNull(company)){
			checkColumnResult = INPUTERROR+COMPANY+INFORMATION;
			return true;
		}else{
			performanceIndex.setCompany(company);
		}
		//检查计算公式
		if(checkColumnIsEmptyOrNull(formula)){
			checkColumnResult = INPUTERROR+FORMULA+INFORMATION;
			return true;
		}else{
			performanceIndex.setFormula(formula);
		}
		
		//检查小于基准
		if(checkColumnIsEmptyOrNull(referenceValue)){
			checkColumnResult = INPUTERROR+INDEX_REFERENCEVALUE+INFORMATION;
			return true;
		}else{
			performanceIndex.setReferenceValue(referenceValue);
		}
		//检查基准与目标之间
		if(checkColumnIsEmptyOrNull(middleValue)){
			checkColumnResult = INPUTERROR+INDEX_MIDDLEVALUE+INFORMATION;
			return true;
		}else{
			performanceIndex.setMiddleValue(middleValue);
		}
		//检查大于目标
		if(checkColumnIsEmptyOrNull(targetValue)){
			checkColumnResult = INPUTERROR+INDEX_TARGETVALUE+INFORMATION;
			return true;
		}else{
			performanceIndex.setTargetValue(targetValue);
		}
		
		//当操作类型为修改时，才处理修改原因字段
		if(operatorType.equals("update")){
			String updateReason = performanceIndex.getUpdateReason().trim();
			//检查修改原因
			if(checkColumnIsEmptyOrNull(updateReason)){
				checkColumnResult = INPUTERROR+UPDATEREASON+INFORMATION;
				return true;
			}else{
				performanceIndex.setUpdateReason(updateReason);
			}
		}
		
		return false;
	}
	
	/**
	 * 设置待操作指标记录信息
	 * @param performanceIndex 绩效指标类对象
	 * @param operatorType 操作类型：增加、修改、删除
	 */
	public void setPerformanceIndexInfo(PerformanceIndex performanceIndex,String operatorType){
		Date date = new Date();
		//获取工厂名称
		String factoryName = getFactoryName(performanceIndex.getFactoryNumber());
		
		//获取部门名称
		String departmentName = getDepartmentName(performanceIndex.getDepartmentNumber());
		
		//获取年度信息
		Date checkYear = DateEditor.parseDate(performanceIndex.getCheckYearString(), "yyyy");
		
		performanceIndex.setCheckYear(checkYear);
		performanceIndex.setFactoryName(factoryName);
		performanceIndex.setDepartmentName(departmentName);
		//增加的时候设置创建日期 ，修改的时候创建日期不变
		if(operatorType.equals("create")){
			performanceIndex.setCreateTime(date);
			performanceIndex.setCreateUser(getCurrentUserName());
		}
		performanceIndex.setUpdateTime(date);
		performanceIndex.setUpdateUser(getCurrentUserName());
		
		performanceIndex.setAssessmentMethod("");//评价方法暂时设置为空
		performanceIndex.setColumn1("");//备用字段暂时设置为空
		performanceIndex.setColumn2("");
		performanceIndex.setColumn3("");
	}
	
	/**
	 * 字符串转换成数值检查
	 * @param checkString 待转化字符串
	 * @return 转换结果
	 */
	public static boolean checkStringToNumber(String checkString){
		try {
			new BigDecimal(checkString);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 检查字段值是否为空或者为null
	 * @param targetStr 待检查字符串
	 * @return 如果字符串为空或者null,返回true,否则返回false
	 */
	public static boolean checkColumnIsEmptyOrNull(String targetStr){
		if(targetStr.equals("") || targetStr == null){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断修改前后信息是否一致
	 * @param oldStr 修改前字段
	 * @param newStr 修改后字段
	 * @param columnName 字段名称
	 */
	public void compareChangeInfo(String oldStr,String newStr,String columnName){
		
		//如果修改前记录和修改后记录不一致，则记录修改信息
		if(!oldStr.equals(newStr)){
			stringBuffer.append(columnName+"：").append(oldStr).append("->").append(newStr).append(";");
		}

	}
}
