package com.peg.qms.controller.performance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.performance.EditReason;
import com.peg.model.performance.PerformanceIndex;
import com.peg.model.performance.YearPerformance;
import com.peg.qms.controller.BaseController;
import com.peg.service.CommonServiceI;
import com.peg.service.performance.DepartmentService;
import com.peg.service.performance.EditReasonService;
import com.peg.service.performance.PerformanceCommon;
import com.peg.service.performance.PerformanceIndexCrudService;
import com.peg.service.performance.YearPerformanceService;
import com.peg.web.util.ColumnDealUtil;
import com.peg.web.util.DateEditor;
import com.peg.web.util.ExcelUtilities;

/**
 * 年度绩效考核指标操作控制层
 * @author xuanm
 *
 */
@Controller
@RequestMapping("ptm/yearPerformanceSet")
public class YearPerformanceController extends BaseController implements PerformanceCommon {

	/**
	 * 绩效指标操作接口
	 */
	@Autowired
	private PerformanceIndexCrudService performanceIndexCrudService;
	
	/**
	 * 年度绩效指标操作接口
	 */
	@Autowired
	private YearPerformanceService yearPerformanceService;

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
	 * 字段检查结果
	 */
	private String checkColumnResult = "";
	
	/**
	 * 记录修改记录或删除记录内容
	 */
	private StringBuffer stringBuffer = new StringBuffer("");
	
	/**
	 * 获取年度绩效指标
	 * @param performanceIndex 绩效指标类对象
	 * @param model
	 * @param page 分页信息
	 * @return
	 */
	@RequestMapping("/list")
	public String list(PerformanceIndex performanceIndex,Model model, PageParameter page){
		//设置年度查询条件，用于回传到前台页面的查询条件中
		String year = performanceIndex.getYear();
		
		BaseSearch bs=new BaseSearch();
	    bs.setPage(page); 
	    bs.put("checkYear", year);//这里使用字符串year替代checkYear当做年度的查询条件
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
		
		return "qms/bph/base/performance/yearPerformance/list";
	}	
	
	/**
	 * 跳转到年度绩效指标新增页面
	 * @param model 回传信息
	 * @return
	 */
	@RequestMapping("/add")
	public String add(@RequestParam(value="id",required=false) Long id,Model model){
		PerformanceIndex performanceIndex=performanceIndexCrudService.selectByPrimaryKey(id);
		model.addAttribute("vo",performanceIndex);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/performance/yearPerformance/add";
	}
	
	/**
	 * 年度绩效指标保存
	 * @param performanceIndex 绩效指标对象
	 * @return 添加状态
	 */
	@RequestMapping("/save")
	public ModelAndView save(@RequestParam(value="id",required=false) Long id,@RequestParam(value="performanceType",required=false) String performanceType,YearPerformance yearPerformance){
		/* 当年度指标值不为空时，不允许再次增加 */
		//根据指标ID查询年度指标信息
		YearPerformance ypf = yearPerformanceService.selectByPiid(id);
		if(ypf != null){
			return ajaxDoneError(INSERT_YEAR_ERROR);
		}
		
		//字段完整性检查
		if(performanceType.equals(INDEX_TYEP)){
			//当指标类型为指标型时
			if(checkColumn(yearPerformance,CREATE)){
				return ajaxDoneError(checkColumnResult);
			}
		}else{
			//当指标类型为项目型时，字段可以填字符型数据
			if(checkColumnA(yearPerformance,CREATE)){
				return ajaxDoneError(checkColumnResult);
			}
		}
		
		//设置待操作记录信息
		setYearPerformance(yearPerformance,CREATE,id);
		
		try {		
			yearPerformanceService.setBaseMapper();
			
			//插入数据库(插入指定的字段)
			yearPerformanceService.insertSelective(yearPerformance);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if(StringUtils.isNotBlank(e.getMessage()) && e.getMessage().contains("ORA-00001")){
				return ajaxDoneError(INSERT_YEAR_ERROR);
			}
			e.printStackTrace();
			return ajaxDoneError(INSERT_ERROR_INFO);
		}
		return ajaxDoneSuccess(INSERT_SUCCESS_INFO);
	} 
	
	/**
	 * 跳转到年度绩效指标修改页面
	 * @param id 待修改记录id
	 * @param model 回传待修改数据
	 * @return 修改页面视图
	 */
	@RequestMapping("/edit")
	public String edit(@RequestParam(value="id",required=false)Long id,Model model){
		PerformanceIndex performanceIndex=performanceIndexCrudService.selectByPrimaryKey(id);
		model.addAttribute("vo",performanceIndex);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/performance/yearPerformance/edit";
	}
	
	/**
	 * 修改年度绩效指标
	 * @param performanceIndex 绩效指标类对象
	 * @return 修改是否成功的状态
	 */
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam(value="id",required=false)Long id,@RequestParam(value="performanceType",required=false)String performanceType,PerformanceIndex performanceIndex,YearPerformance yearPerformance){
		
		/* 当年度指标为空时，不允许修改 */
		//根据指标ID查询年度指标信息
		YearPerformance ypf = yearPerformanceService.selectByPiid(performanceIndex.getId());
		//如果获取到的值为空，说明年度绩效指标还未添加，则不能进行修改
		if(ypf == null){
			return ajaxDoneError(YEAR_INDEX_NOT_EXIST);
		}
		
		//字段完整性检查
		if(performanceType.equals(INDEX_TYEP)){
			//当指标类型为指标型时
			if(checkColumn(yearPerformance,UPDATE)){
				return ajaxDoneError(checkColumnResult);
			}
		}else{
			//当指标类型为项目型时，字段可以填字符型数据
			if(checkColumnA(yearPerformance,UPDATE)){
				return ajaxDoneError(checkColumnResult);
			}
		}
		
		//设置待操作记录信息
		setYearPerformance(yearPerformance,UPDATE,id);
		
		//比较字段，记录修改信息,存入到基础查询类中
		EditReason editReason = onUpdateCheck(performanceIndex,yearPerformance);
		
		try {
			yearPerformanceService.updateByPrimaryKeySelective(yearPerformance);
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
	 * 修改时记录
	 * @param performanceIndex
	 * @param yearPerformance
	 * @return
	 */
	EditReason onUpdateCheck(PerformanceIndex performanceIndex,YearPerformance yearPerformance){
		stringBuffer.setLength(0);//初始化stringBuffer
		//生成一个YearPerformance对象，存储修改前年度绩效信息
		YearPerformance ypf = yearPerformanceService.selectByPiid(performanceIndex.getId());
		
		String oldLastYearRealityValue = ypf.getLastYearRealityValue();
		String oldFirstYearReferenceValue = ypf.getFirstYearReferenceValue();
		String oldReferenceValue= ypf.getReferenceValue();
		String oldTargetValue= ypf.getTargetValue();
		String oldFirstYearTargetValue= ypf.getFirstYearTargetValue();
		String oldSecondYearTargetValue= ypf.getSecondYearTargetValue();
		
		String newLastYearRealityValue = yearPerformance.getLastYearRealityValue();
		String newFirstYearReferenceValue = yearPerformance.getFirstYearReferenceValue();
		String newReferenceValue= yearPerformance.getReferenceValue();
		String newTargetValue= yearPerformance.getTargetValue();
		String newFirstYearTargetValue= yearPerformance.getFirstYearTargetValue();
		String newSecondYearTargetValue= yearPerformance.getSecondYearTargetValue();
		
		compareChangeInfo(oldLastYearRealityValue,newLastYearRealityValue,LASTYEARREALITYVALUE);
		compareChangeInfo(oldFirstYearReferenceValue,newFirstYearReferenceValue,FIRSTYEARREFERENCEVALUE);
		compareChangeInfo(oldReferenceValue,newReferenceValue,REFERENCEVALUE);
		compareChangeInfo(oldTargetValue,newTargetValue,TARGETVALUE);
		compareChangeInfo(oldFirstYearTargetValue,newFirstYearTargetValue,FIRSTYEARTARGETVALUE);
		compareChangeInfo(oldSecondYearTargetValue,newSecondYearTargetValue,SECONDYEARTARGETVALUE);
		
/*		if(!oldLastYearRealityValue.equals(newLastYearRealityValue)){
			updateContentString.append("上年实际值：").append(oldLastYearRealityValue).append("->").append(newLastYearRealityValue).append(";");
		}
		if(!oldReferenceValue.equals(newReferenceValue)){
			updateContentString.append("本年基准值：").append(oldReferenceValue).append("->").append(newReferenceValue).append(";");
		}
		if(!oldTargetValue.equals(newTargetValue)){
			updateContentString.append("本年目标值：").append(oldTargetValue).append("->").append(newTargetValue).append(";");
		}
		if(!oldFirstYearTargetValue.equals(newFirstYearTargetValue)){
			updateContentString.append("上半年目标值：").append(oldFirstYearTargetValue).append("->").append(newFirstYearTargetValue).append(";");
		}
		if(!oldSecondYearTargetValue.equals(newSecondYearTargetValue)){
			updateContentString.append("下半年目标值：").append(oldSecondYearTargetValue).append("->").append(newSecondYearTargetValue).append(";");
		}*/
		
		EditReason editReason = new EditReason();
		editReason.setUpdateTime(new Date());
		editReason.setUpdateUser(getCurrentUserName());
		editReason.setUpdateReason(performanceIndex.getUpdateReason());
		editReason.setUpdateType(UPDATESTRING);
		editReason.setOperatorModule(YEAR_INDEX_SET);
		editReason.setUpdateContent(stringBuffer.toString());//StringBuffer要转化为String类型的数据
		editReason.setCreateTime(new Date());
		editReason.setCreateUser(getCurrentUserName());
		editReason.setFactoryNumber(performanceIndex.getFactoryNumber());
		editReason.setFactoryName(performanceIndex.getFactoryName());
		editReason.setDepartmentNumber(performanceIndex.getDepartmentNumber());
		editReason.setDepartmentName(performanceIndex.getDepartmentName());
		editReason.setPerformanceTargetClass(performanceIndex.getPerformanceTargetClass());
		editReason.setPerformanceType(performanceIndex.getPerformanceType());
		editReason.setIndexContent(performanceIndex.getIndexContent());
		editReason.setIndexType(performanceIndex.getIndexType());
		editReason.setWeight(performanceIndex.getWeight());
		editReason.setCompany(performanceIndex.getCompany());
		editReason.setColumn1("");
		editReason.setColumn2("");
		editReason.setColumn3("");
		
		return editReason;
	}
	
	/**
	 * 根据指标ID删除年度绩效指标记录
	 * @param performanceCheck
	 * @return
	 */
	@RequestMapping("/delete")
	public ModelAndView delete(PerformanceIndex performanceIndex){
		
		
		//插入删除记录
		performanceIndex = performanceIndexCrudService.selectByPrimaryKey(performanceIndex.getId());
		PerformanceIndex pfi = performanceIndexCrudService.getOneMonthPerformanceById(performanceIndex.getId());
		//判断年度指标值是否为空，如果为空，则提示不能进行删除
		if(performanceIndex.getYearPerformance() == null){
			return ajaxDoneError(DELETE_YEAR_ERROR_INFO);
		}else if(pfi.getMonthList().size() != 0){
			//如果年度指标值下面有月度指标，也不允许进行删除操作
			return ajaxDoneError(YEAT_INDEX_HAS_MONTH_INDEX);
		}
		EditReason editReason = onDelete(performanceIndex);
		yearPerformanceService.deleteByPiid(performanceIndex.getId());
		editReasonService.insertSelective(editReason);
		
		return ajaxDoneSuccess(DELETE_SUCCESS_INFO);
	}
	
	public EditReason onDelete(PerformanceIndex performanceIndex){
		StringBuffer updateContentString = new StringBuffer();
		updateContentString.append("删除年度绩效指标内容");
		
		EditReason editReason = new EditReason();
		editReason.setUpdateTime(new Date());
		editReason.setUpdateUser(getCurrentUserName());
		editReason.setUpdateReason(performanceIndex.getUpdateReason());
		editReason.setUpdateType("删除");
		editReason.setOperatorModule("年度指标设定");
		editReason.setUpdateContent(updateContentString.toString());//StringBuffer要转化为String类型的数据
		editReason.setCreateTime(new Date());
		editReason.setCreateUser(getCurrentUserName());
		editReason.setFactoryNumber(performanceIndex.getFactoryNumber());
		editReason.setFactoryName(performanceIndex.getFactoryName());
		editReason.setDepartmentNumber(performanceIndex.getDepartmentNumber());
		editReason.setDepartmentName(performanceIndex.getDepartmentName());
		editReason.setPerformanceTargetClass(performanceIndex.getPerformanceTargetClass());
		editReason.setPerformanceType(performanceIndex.getPerformanceType());
		editReason.setIndexContent(performanceIndex.getIndexContent());
		editReason.setIndexType(performanceIndex.getIndexType());
		editReason.setWeight(performanceIndex.getWeight());
		editReason.setCompany(performanceIndex.getCompany());
		editReason.setColumn1("");
		editReason.setColumn2("");
		editReason.setColumn3("");
		
		return editReason;
	}
	
	/**
	 * 导出excel
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
					INDEX_TARGETVALUE,LASTYEARREALITYVALUE,FIRSTYEARREFERENCEVALUE,REFERENCEVALUE,TARGETVALUE,
					FIRSTYEARTARGETVALUE,SECONDYEARTARGETVALUE,CREATE_USER,CREATE_TIME};
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
				
				//如果年度指标值为空
				if(per.getYearPerformance() == null){
					itemStr[13] = "";
					itemStr[14] = "";
					itemStr[15] = "";
					itemStr[16] = "";
					itemStr[17] = "";
					itemStr[18] = "";
				}else{
					YearPerformance yea = per.getYearPerformance();
					itemStr[13] = yea.getLastYearRealityValue();
					itemStr[14] = yea.getFirstYearReferenceValue();
					itemStr[15] = yea.getReferenceValue();
					itemStr[16] = yea.getTargetValue();
					itemStr[17] = yea.getFirstYearTargetValue();
					itemStr[18] = yea.getSecondYearTargetValue();
				}		
				itemStr[19] = per.getCreateUser();
				itemStr[20] = DateEditor.formatDate(per.getCreateTime(), "yyyy-MM-dd");
				excelList.add(itemStr);				
			}
			
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			
				ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
				String contentType =  "application/msexcel" ;
				ExcelUtilities.downloadExcel(request, response, filePath, contentType, YEAR_INDEX_SET+fname);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	
	/**
	 * 字段完整性检查
	 * @param yearPerformance 年度指标信息
	 * @param operatorType operatorType 操作类型：增加、修改
	 * @return 字段检查结果，符合条件返回true，否则返回fasle
	 */
	public boolean checkColumn(YearPerformance yearPerformance,String operatorType){
		
		String lastYearRealityValue = yearPerformance.getLastYearRealityValue().trim();
		String firstYearReferenceValue = yearPerformance.getFirstYearReferenceValue().trim();
		String referenceValue= yearPerformance.getReferenceValue().trim();
		String targetValue= yearPerformance.getTargetValue().trim();
		String firstYearTargetValue= yearPerformance.getFirstYearTargetValue().trim();
		String secondYearTargetValue= yearPerformance.getSecondYearTargetValue().trim(); 
		
		
		if(PerformanceIndexCrudController.checkColumnIsEmptyOrNull(lastYearRealityValue)){
			checkColumnResult = INPUTERROR+LASTYEARREALITYVALUE+INFORMATION;
			return true;
		}else if(!ColumnDealUtil.checkStringToNumber(lastYearRealityValue)){
			checkColumnResult = LASTYEARREALITYVALUE+REQUIRED_NUMBER_TYPE;
			return true;
		}else{
			yearPerformance.setLastYearRealityValue(lastYearRealityValue);
		}
		
		if(PerformanceIndexCrudController.checkColumnIsEmptyOrNull(firstYearReferenceValue)){
			checkColumnResult = INPUTERROR+FIRSTYEARREFERENCEVALUE+INFORMATION;
			return true;
		}else if(!ColumnDealUtil.checkStringToNumber(firstYearReferenceValue)){
			checkColumnResult = FIRSTYEARREFERENCEVALUE+REQUIRED_NUMBER_TYPE;
			return true;
		}else{
			yearPerformance.setFirstYearReferenceValue(firstYearReferenceValue);
		}
		
		if(PerformanceIndexCrudController.checkColumnIsEmptyOrNull(referenceValue)){
			checkColumnResult = INPUTERROR+REFERENCEVALUE+INFORMATION;
			return true;
		}else if(!ColumnDealUtil.checkStringToNumber(referenceValue)){
			checkColumnResult = REFERENCEVALUE+REQUIRED_NUMBER_TYPE;
			return true;
		}else{
			yearPerformance.setReferenceValue(referenceValue);
		}
		
		if(PerformanceIndexCrudController.checkColumnIsEmptyOrNull(targetValue)){
			checkColumnResult = INPUTERROR+TARGETVALUE+INFORMATION;
			return true;
		}else if(!ColumnDealUtil.checkStringToNumber(targetValue)){
			checkColumnResult = TARGETVALUE+REQUIRED_NUMBER_TYPE;
			return true;
		}else{
			yearPerformance.setTargetValue(targetValue);
		}
		
		if(PerformanceIndexCrudController.checkColumnIsEmptyOrNull(firstYearTargetValue)){
			checkColumnResult = INPUTERROR+FIRSTYEARTARGETVALUE+INFORMATION;
			return true;
		}else if(!ColumnDealUtil.checkStringToNumber(firstYearTargetValue)){
			checkColumnResult = FIRSTYEARTARGETVALUE+REQUIRED_NUMBER_TYPE;
			return true;
		}else{
			yearPerformance.setFirstYearTargetValue(firstYearTargetValue);
		}
		
		if(PerformanceIndexCrudController.checkColumnIsEmptyOrNull(secondYearTargetValue)){
			checkColumnResult = INPUTERROR+SECONDYEARTARGETVALUE+INFORMATION;
			return true;
		}else if(!ColumnDealUtil.checkStringToNumber(secondYearTargetValue)){
			checkColumnResult = SECONDYEARTARGETVALUE+REQUIRED_NUMBER_TYPE;
			return true;
		}else{
			yearPerformance.setSecondYearTargetValue(secondYearTargetValue);
		}
		return false;
	}
	
	public boolean checkColumnA(YearPerformance yearPerformance,String operatorType){
		
		String lastYearRealityValue = yearPerformance.getLastYearRealityValue().trim();
		String firstYearReferenceValue = yearPerformance.getFirstYearReferenceValue().trim();
		String referenceValue= yearPerformance.getReferenceValue().trim();
		String targetValue= yearPerformance.getTargetValue().trim();
		String firstYearTargetValue= yearPerformance.getFirstYearTargetValue().trim();
		String secondYearTargetValue= yearPerformance.getSecondYearTargetValue().trim(); 
		
		
		if(PerformanceIndexCrudController.checkColumnIsEmptyOrNull(lastYearRealityValue)){
			checkColumnResult = INPUTERROR+LASTYEARREALITYVALUE+INFORMATION;
			return true;
		}else{
			yearPerformance.setLastYearRealityValue(lastYearRealityValue);
		}
		
		if(PerformanceIndexCrudController.checkColumnIsEmptyOrNull(firstYearReferenceValue)){
			checkColumnResult = INPUTERROR+FIRSTYEARREFERENCEVALUE+INFORMATION;
			return true;
		}else{
			yearPerformance.setFirstYearReferenceValue(firstYearReferenceValue);
		}
		
		if(PerformanceIndexCrudController.checkColumnIsEmptyOrNull(referenceValue)){
			checkColumnResult = INPUTERROR+REFERENCEVALUE+INFORMATION;
			return true;
		}else{
			yearPerformance.setReferenceValue(referenceValue);
		}
		
		if(PerformanceIndexCrudController.checkColumnIsEmptyOrNull(targetValue)){
			checkColumnResult = INPUTERROR+TARGETVALUE+INFORMATION;
			return true;
		}else{
			yearPerformance.setTargetValue(targetValue);
		}
		
		if(PerformanceIndexCrudController.checkColumnIsEmptyOrNull(firstYearTargetValue)){
			checkColumnResult = INPUTERROR+FIRSTYEARTARGETVALUE+INFORMATION;
			return true;
		}else{
			yearPerformance.setFirstYearTargetValue(firstYearTargetValue);
		}
		
		if(PerformanceIndexCrudController.checkColumnIsEmptyOrNull(secondYearTargetValue)){
			checkColumnResult = INPUTERROR+SECONDYEARTARGETVALUE+INFORMATION;
			return true;
		}else{
			yearPerformance.setSecondYearTargetValue(secondYearTargetValue);
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
	
	/**
	 * 设置待操作记录信息
	 * @param yearPerformance
	 * @param operatorType
	 */
	public void setYearPerformance(YearPerformance yearPerformance,String operatorType,Long id){
		Date date = new Date();
		yearPerformance.setPiid(id);
		
		//如果是增加操作，则不修改创建时间
		if(operatorType.equals("create")){
			yearPerformance.setCreateTime(date);
			yearPerformance.setCreateUser(getCurrentUserName());
		}
		yearPerformance.setUpdateTime(date);
		yearPerformance.setUpdateUser(getCurrentUserName());
		
		yearPerformance.setColumn1("");//备用字段暂时设置为空
		yearPerformance.setColumn2("");
	}
}
