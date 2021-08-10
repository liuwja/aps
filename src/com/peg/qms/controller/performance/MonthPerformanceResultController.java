package com.peg.qms.controller.performance;

import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.performance.EditReason;
import com.peg.model.performance.MonthIndex;
import com.peg.model.performance.MonthPerformance;
import com.peg.model.performance.PerformanceIndex;
import com.peg.model.performance.YearPerformance;
import com.peg.qms.controller.BaseController;
import com.peg.qms.controller.ExcelReaderJXL;
import com.peg.service.CommonServiceI;
import com.peg.service.performance.DepartmentService;
import com.peg.service.performance.EditReasonService;
import com.peg.service.performance.IndexImportService;
import com.peg.service.performance.MonthPerformanceService;
import com.peg.service.performance.PerformanceCommon;
import com.peg.service.performance.PerformanceIndexCrudService;
import com.peg.service.performance.YearPerformanceService;
import com.peg.web.util.ColumnDealUtil;
import com.peg.web.util.DateEditor;
import com.peg.web.util.ExcelUtilities;

import net.sf.json.JSONObject;

/**
 * 月度绩效结果设定控制层
 * @author xuanm
 *
 */
@Controller
@RequestMapping("ptm/monthPerformanceResult")
public class MonthPerformanceResultController extends BaseController implements PerformanceCommon{
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
	 * 月度绩效指标操作接口
	 */
	@Autowired
	private MonthPerformanceService monthPerformanceService;

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
	
	@Autowired
	private IndexImportService indexImportService;
	
	/**
	 * 月份顺序，用于前台页面横向显示12个月
	 */
	private String monthString = "0";
	
	/**
	 * 字段检查结果
	 */
	private String checkColumnResult = "";
	
	/**
	 * 存储变更信息的字段
	 */
	private StringBuffer stringBuffer = new StringBuffer("");
	
	
	
	/**
	 * 获取月度绩效指标结果
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
		
		List<PerformanceIndex> list = performanceIndexCrudService.getMonthPerformance(bs);//实际上以分页的形式查询
		for (PerformanceIndex performanceIndex2 : list) {
			//设置月份信息
			setMonthInfo(performanceIndex2);
		}
		model.addAttribute("list",list);
		model.addAttribute("performanceIndex",performanceIndex);
		model.addAttribute("page",page);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		
		return "qms/bph/base/performance/monthPerformanceResult/list";
	}		

	/**
	 * 跳转到月度绩效指标结果设定新增页面
	 * @param id 待修改记录id
	 * @param model 回传待新增数据
	 * @return 修改页面视图
	 */
	@RequestMapping("/toAdd")
	public String toAdd(@RequestParam(value="id",required=false)Long id,Model model,PerformanceIndex performanceIndex){
		performanceIndex=performanceIndexCrudService.getOneMonthPerformanceById(id);
		//设置月份信息
		setMonthInfo(performanceIndex);
		model.addAttribute("vo",performanceIndex);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/performance/monthPerformanceResult/add";
	}
	
	/**
	 * 增加月度绩效指标考核结果
	 * @param performanceIndex 绩效指标类对象
	 * @return 修改是否成功的状态
	 */
	@RequestMapping("/add")
	public ModelAndView add(@RequestParam(value="toAdd",required=false)String toAdd,
			@RequestParam(value="id",required=false)Long id,
			PerformanceIndex performanceIndex,
			MonthPerformance monthPerformance){
		Date date=new Date();
		if(toAdd == null){
			return ajaxDoneError("未选中任何要增加月度考核结果的记录");
		}
		
		Long[] piids = changeStringToArray(toAdd);
		int number = piids.length;
		
		//如果月度绩效指标结果已经存在，则不允许再次增加
		PerformanceIndex p1 = performanceIndexCrudService.getOneMonthPerformanceById(id);
		List<MonthPerformance> m1 = p1.getMonthList();
		for (MonthPerformance monthPerformance2 : m1) {
			for (int i = 0; i < number; i++) {
				//只对比选中的记录
				if(monthPerformance2.getId().equals(piids[i])){
					if(monthPerformance2.getCreateUser2() != null){
						return ajaxDoneError(MONTH_INDEX_RESULT_EXIST);
					}
				}
			}
		}
		
		List<MonthPerformance> monthList = performanceIndex.getItem();		
		//设置月份信息
		setMonthInfoByItem(performanceIndex);
		
		String performanceType = performanceIndex.getPerformanceType();
		//校验字段完整性
		for (MonthPerformance monthPerformance2 : monthList) {
			for (int i = 0; i < number; i++) {
				//只对比选中的记录
				if(monthPerformance2.getId().equals(piids[i])){
					
					if(performanceType.equals(PROGRAME_TYPE)){
						if(!onAddMonthIndexResultA(monthPerformance2,CREATE)){
							return ajaxDoneError(checkColumnResult);
						}
					}else{
						if(!onAddMonthIndexResult(monthPerformance2,CREATE)){
							return ajaxDoneError(checkColumnResult);
						}
					}
				}
			}		
		}
				
		//获取本年度基准值、上半年基准值
		YearPerformance yearPerformance = performanceIndex.getYearPerformance();
		//如果本年度基准值或者上半年基准值为空，则说明没有设置年度指标值，提示用户进行设置
		if(yearPerformance.getReferenceValue() == null || yearPerformance.getFirstYearReferenceValue() == null){
			return ajaxDoneError(YEAR_INDEX_VALUE_NULL);
		}
		
		//当绩效类型为“指标型”时：
		if(performanceIndex.getPerformanceType().equals(INDEX_TYEP)){
			//此时年度基准值和上年度不为字符串
			String referenceValue = yearPerformance.getReferenceValue();
			String firstYearReferenceValue = yearPerformance.getFirstYearReferenceValue();
			double referenceValueToDouble = Double.parseDouble(referenceValue);	
			double firstYearReferenceValueToDouble = Double.parseDouble(firstYearReferenceValue);	
			
			for (MonthPerformance monthPerformance2 : monthList) {
				for (int i = 0; i < number; i++) {
					//只处理选中的记录
					if(monthPerformance2.getId().equals(piids[i])){
						//如果月份为当年前6个月，那么年度基准值设置为上半年基准值
						if(monthPerformance2.getMonthIndex().equals("1") || monthPerformance2.getMonthIndex().equals("2") || monthPerformance2.getMonthIndex().equals("3") 
								|| monthPerformance2.getMonthIndex().equals("4") || monthPerformance2.getMonthIndex().equals("5") || monthPerformance2.getMonthIndex().equals("6") ){
							referenceValueToDouble = firstYearReferenceValueToDouble;
						}
						//当月累计目标值
						String monTotalTargetValue = monthPerformance2.getMonTotalTargetValue();
						//当月挑战目标值
						String monChallengeTargetValue = monthPerformance2.getMonChallengeTargetValue();
						//当月实际累计目标值
						String monRealityTotalTargetValue = monthPerformance2.getMonRealityTotalTargetValue();
						
						double monTotalTargetValueToDouble = Double.parseDouble(monTotalTargetValue);
						double monChallengeTargetValueToDouble = Double.parseDouble(monChallengeTargetValue);
						double monRealityTotalTargetValueToDouble = Double.parseDouble(monRealityTotalTargetValue);
						
						String indexType = performanceIndex.getIndexType();
						//1.当指标类型为“望大型”时					
						if(indexType.equals(BIG_THAN_TARGET_TYPE)){
							
							if(monRealityTotalTargetValueToDouble < referenceValueToDouble){
								//(1)累计实际值<本年度基准值（前6个月使用上半年基准值），显示成：未达标，红色
								monthPerformance2.setCheckResult(NOT_TARGET);
								monthPerformance2.setTrColor1("red");
							}else if(monRealityTotalTargetValueToDouble >= referenceValueToDouble && monRealityTotalTargetValueToDouble < monChallengeTargetValueToDouble){
								//(2)本年度基准值=<当月累计实际值<当月挑战目标值（前6个月使用上半年基准值），显示成：基准与目标之间，黄色
								monthPerformance2.setCheckResult(INDEX_MIDDLEVALUE);
								monthPerformance2.setTrColor1("yellow");
							}else if(monChallengeTargetValueToDouble <= monRealityTotalTargetValueToDouble){
								//(3)当月挑战目标值=<当月累计实际值时，显示成：已达标，绿色
								monthPerformance2.setCheckResult(IS_TRAGET);
								monthPerformance2.setTrColor1("green");
							}else{
								monthPerformance2.setCheckResult("无法确定");
								monthPerformance2.setTrColor1("white");
							}
						}
						else if(indexType.equals(LESS_THAN_TARGET_TYPE)){//2.当指标类型为“望小型”时
							/**
							 * BUG #6626 
							 * 当指标类型为“望小型”时，优先用【累计实际值】和【挑战目标值】比较，若比较结果为“已达标”，则不用再跟 【基准值】 比较。
							 */
							if(monRealityTotalTargetValueToDouble < monChallengeTargetValueToDouble){
								monthPerformance2.setCheckResult(IS_TRAGET);
								monthPerformance2.setTrColor2("green");
							}else{
								if(monRealityTotalTargetValueToDouble > referenceValueToDouble){
									//(1)当月累计实际值>本年度基准值，显示成：未达标，红色
									monthPerformance2.setCheckResult(NOT_TARGET);
									monthPerformance2.setTrColor2("red");
								}else if(monChallengeTargetValueToDouble < monRealityTotalTargetValueToDouble && monRealityTotalTargetValueToDouble <= referenceValueToDouble){
									//(2)当月挑战目标值<当月累计实际值<=本年度基准值时，显示成：基准与目标之间，黄色
									monthPerformance2.setCheckResult(INDEX_MIDDLEVALUE);
									monthPerformance2.setTrColor2("yellow");
								}else if(monRealityTotalTargetValueToDouble <= monChallengeTargetValueToDouble){
									//(3)当月累计实际值时<=当月挑战目标值 时，显示成：已达标，绿色
									monthPerformance2.setCheckResult(IS_TRAGET);
									monthPerformance2.setTrColor2("green");
								}else{
									monthPerformance2.setCheckResult("无法确定");
									monthPerformance2.setTrColor2("white");
								}
							}
							
						}
						else if(indexType.equals(EQUAL_TARGET_TYPE)){//3.当指标类型为“望目型”时
							if(monRealityTotalTargetValueToDouble == monChallengeTargetValueToDouble){
								//(1)当月累计实际值＝当月挑战目标值，显示成：已达标
								monthPerformance2.setCheckResult(IS_TRAGET);
								monthPerformance2.setTrColor3("green");
							}else if(monRealityTotalTargetValueToDouble != monChallengeTargetValueToDouble){
								//(2)当月累计实际值!=当月挑战目标值，显示成：未达标
								monthPerformance2.setCheckResult(NOT_TARGET);
								monthPerformance2.setTrColor3("red");
							}else{
								monthPerformance2.setCheckResult("无法确定");
								monthPerformance2.setTrColor3("white");
							}
						}
						monthPerformance2.setCreateTime2(date);//创建时间
						monthPerformance2.setCreateUser2(getCurrentUserName());//创建人
					}
				}		
			}//end outer for		
		}else if(performanceIndex.getPerformanceType().equals(PROGRAME_TYPE)){
			for (MonthPerformance monthPerformance2 : monthList) {
				for (int i = 0; i < number; i++) {
					//只对比选中的记录
					if(monthPerformance2.getId().equals(piids[i])){
						//当月份为6月和12月时，评判标准为A、B+、B、C
						if(monthPerformance2.getMonthIndex().equals("6") || monthPerformance2.getMonthIndex().equals("12") ){
							if(monthPerformance2.getColumn1().equals("A") || monthPerformance2.getColumn1().equals("B+")){
								monthPerformance2.setCheckResult(IS_TRAGET);
								monthPerformance2.setTrColor1("green");
							}else if(monthPerformance2.getColumn1().equals("B") || monthPerformance2.getColumn1().equals("C")){
								monthPerformance2.setCheckResult(NOT_TARGET);
								monthPerformance2.setTrColor1("red");
							}else{
								monthPerformance2.setTrColor1("white");
								return ajaxDoneError(MONTH_INDEX_ERROR_FROM_DATABASE);
							}
						}else{
						//当月份不为6月和12月时，评判标准为 正常进行，延期，终止
							if(monthPerformance2.getColumn1().equals(INDEX_NORMAL)){
								monthPerformance2.setCheckResult(IS_TRAGET);
								monthPerformance2.setTrColor1("green");
							}else if(monthPerformance2.getColumn1().equals(INDEX_DELAY) || monthPerformance2.getColumn1().equals(INDEX_END)){
								monthPerformance2.setCheckResult(NOT_TARGET);
								monthPerformance2.setTrColor1("red");
							}else{
								monthPerformance2.setTrColor1("white");
								return ajaxDoneError(MONTH_INDEX_ERROR_FROM_DATABASE);
							}
						}
						monthPerformance2.setCreateTime2(date);//创建时间
						monthPerformance2.setCreateUser2(getCurrentUserName());//创建人
					}
				}//end inner for		
			}
		}else{
			return ajaxDoneError(MONTH_INDEX_ERROR_FROM_DATABASE);
		}
		
		//将记录插入到数据库中，实际上是修改月度绩效指标记录
		for (MonthPerformance monthPerformance2 : monthList) {
			for (int i = 0; i < number; i++) {
				//只对比选中的记录
				if(monthPerformance2.getId().equals(piids[i])){
					try {
						monthPerformanceService.updateByPrimaryKeySelective(monthPerformance2);
					} catch (Exception e) {
						return ajaxDoneError(INSERT_ERROR_INFO);
					}
				}
			}		
		}
		return ajaxDoneSuccess("增加成功");
	}
	
	/**
	 * 跳转到月度绩效指标修改页面
	 * @param model 回传信息
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(@RequestParam(value="id",required=false) Long id,Model model,PerformanceIndex performanceIndex){		
		performanceIndex=performanceIndexCrudService.getOneMonthPerformanceById(id);
		//设置月份信息
		setMonthInfo(performanceIndex);
		model.addAttribute("vo",performanceIndex);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/performance/monthPerformanceResult/edit";
	}
	
	/**
	 * 修改月度绩效指标考核结果
	 * @param performanceIndex 绩效指标类对象
	 * @return 修改是否成功的状态
	 */
	@RequestMapping("/updateMonthIndexResult")
	public ModelAndView updateMonthIndexResult(@RequestParam(value="toUpdate",required=false)String toUpdate,
			@RequestParam(value="id",required=false)Long id,
			PerformanceIndex performanceIndex,
			MonthPerformance monthPerformance){
		Date date=new Date();
		if(toUpdate == null){
			return ajaxDoneError("未选中任何要修改的记录");
		}
		
		Long[] piids = changeStringToArray(toUpdate);
		int number = piids.length;
		
		//如果月度绩效指标结果不存在，则不允许修改
		PerformanceIndex p1 = performanceIndexCrudService.getOneMonthPerformanceById(id);
		List<MonthPerformance> m1 = p1.getMonthList();
		for (MonthPerformance monthPerformance2 : m1) {
			for (int i = 0; i < number; i++) {
				//只对比选中的记录
				if(monthPerformance2.getId().equals(piids[i])){
					if(monthPerformance2.getCreateUser2() == null){
						return ajaxDoneError(MONTH_INDEX_RESULT_NOT_EXIST);
					}
				}
			}
		}
		
		List<MonthPerformance> monthList = performanceIndex.getItem();		
		//设置月份信息
		setMonthInfoByItem(performanceIndex);
		
		String performanceType = performanceIndex.getPerformanceType();
		//校验字段完整性
		for (MonthPerformance monthPerformance2 : monthList) {
			for (int i = 0; i < number; i++) {
				//只对比选中的记录
				if(monthPerformance2.getId().equals(piids[i])){
					
					if(performanceType.equals(PROGRAME_TYPE)){
						if(!onAddMonthIndexResultA(monthPerformance2,UPDATE)){
							return ajaxDoneError(checkColumnResult);
						}
					}else{
						if(!onAddMonthIndexResult(monthPerformance2,UPDATE)){
							return ajaxDoneError(checkColumnResult);
						}
					}
					
				}
			}		
		}
		
		
		//获取本年度基准值、上半年基准值
		YearPerformance yearPerformance = performanceIndex.getYearPerformance();
		//如果本年度基准值或者上半年基准值为空，则说明没有设置年度指标值，提示用户进行设置
		if(yearPerformance.getReferenceValue() == null || yearPerformance.getFirstYearReferenceValue() == null){
			return ajaxDoneError(YEAR_INDEX_VALUE_NULL);
		}
		
		//当绩效类型为“指标型”时：
		if(performanceIndex.getPerformanceType().equals(INDEX_TYEP)){
			//此时年度基准值和上年度不为字符串
			String referenceValue = yearPerformance.getReferenceValue();
			String firstYearReferenceValue = yearPerformance.getFirstYearReferenceValue();
			double referenceValueToDouble = Double.parseDouble(referenceValue);	
			double firstYearReferenceValueToDouble = Double.parseDouble(firstYearReferenceValue);	
			
			for (MonthPerformance monthPerformance2 : monthList) {
				for (int i = 0; i < number; i++) {
					//只处理选中的记录
					if(monthPerformance2.getId().equals(piids[i])){
						//如果月份为当年前6个月，那么年度基准值设置为上半年基准值
						if(monthPerformance2.getMonthIndex().equals("1") || monthPerformance2.getMonthIndex().equals("2") || monthPerformance2.getMonthIndex().equals("3") 
								|| monthPerformance2.getMonthIndex().equals("4") || monthPerformance2.getMonthIndex().equals("5") || monthPerformance2.getMonthIndex().equals("6") ){
							referenceValueToDouble = firstYearReferenceValueToDouble;
						}
						//当月累计目标值
						String monTotalTargetValue = monthPerformance2.getMonTotalTargetValue();
						//当月挑战目标值
						String monChallengeTargetValue = monthPerformance2.getMonChallengeTargetValue();
						//当月实际累计目标值
						String monRealityTotalTargetValue = monthPerformance2.getMonRealityTotalTargetValue();
						
						double monTotalTargetValueToDouble = Double.parseDouble(monTotalTargetValue);
						double monChallengeTargetValueToDouble = Double.parseDouble(monChallengeTargetValue);
						double monRealityTotalTargetValueToDouble = Double.parseDouble(monRealityTotalTargetValue);
						
						String indexType = performanceIndex.getIndexType();
						//1.当指标类型为“望大型”时					
						if(indexType.equals(BIG_THAN_TARGET_TYPE)){
							
							if(monRealityTotalTargetValueToDouble < referenceValueToDouble){
								//(1)累计实际值<本年度基准值（前6个月使用上半年基准值），显示成：未达标，红色
								monthPerformance2.setCheckResult(NOT_TARGET);
								monthPerformance2.setTrColor1("red");
							}else if(monRealityTotalTargetValueToDouble >= referenceValueToDouble && monRealityTotalTargetValueToDouble < monChallengeTargetValueToDouble){
								//(2)本年度基准值=<当月累计实际值<当月挑战目标值（前6个月使用上半年基准值），显示成：基准与目标之间，黄色
								monthPerformance2.setCheckResult(INDEX_MIDDLEVALUE);
								monthPerformance2.setTrColor1("yellow");
							}else if(monChallengeTargetValueToDouble <= monRealityTotalTargetValueToDouble){
								//(3)当月挑战目标值=<当月累计实际值时，显示成：已达标，绿色
								monthPerformance2.setCheckResult(IS_TRAGET);
								monthPerformance2.setTrColor1("green");
							}else{
								monthPerformance2.setCheckResult("无法确定");
								monthPerformance2.setTrColor1("white");
							}
						}
						else if(indexType.equals(LESS_THAN_TARGET_TYPE)){//2.当指标类型为“望小型”时
							/**
							 * BUG #6626 
							 * 当指标类型为“望小型”时，优先用【累计实际值】和【挑战目标值】比较，若比较结果为“已达标”，则不用再跟 【基准值】 比较。
							 */
							if(monRealityTotalTargetValueToDouble < monChallengeTargetValueToDouble){
								monthPerformance2.setCheckResult(IS_TRAGET);
								monthPerformance2.setTrColor2("green");
							}else{
								if(monRealityTotalTargetValueToDouble > referenceValueToDouble){
									//(1)当月累计实际值>本年度基准值，显示成：未达标，红色
									monthPerformance2.setCheckResult(NOT_TARGET);
									monthPerformance2.setTrColor2("red");
								}else if(monChallengeTargetValueToDouble < monRealityTotalTargetValueToDouble && monRealityTotalTargetValueToDouble <= referenceValueToDouble){
									//(2)当月挑战目标值<当月累计实际值<=本年度基准值时，显示成：基准与目标之间，黄色
									monthPerformance2.setCheckResult(INDEX_MIDDLEVALUE);
									monthPerformance2.setTrColor2("yellow");
								}else if(monRealityTotalTargetValueToDouble <= monChallengeTargetValueToDouble){
									//(3)当月累计实际值时<=当月挑战目标值 时，显示成：已达标，绿色
									monthPerformance2.setCheckResult(IS_TRAGET);
									monthPerformance2.setTrColor2("green");
								}else{
									monthPerformance2.setCheckResult("无法确定");
									monthPerformance2.setTrColor2("white");
								}
							}
						}
						else if(indexType.equals(EQUAL_TARGET_TYPE)){//3.当指标类型为“望目型”时
							if(monRealityTotalTargetValueToDouble == monChallengeTargetValueToDouble){
								//(1)当月累计实际值＝当月挑战目标值，显示成：已达标
								monthPerformance2.setCheckResult(IS_TRAGET);
								monthPerformance2.setTrColor3("green");
							}else if(monRealityTotalTargetValueToDouble != monChallengeTargetValueToDouble){
								//(2)当月累计实际值!=当月挑战目标值，显示成：未达标
								monthPerformance2.setCheckResult(NOT_TARGET);
								monthPerformance2.setTrColor3("red");
							}else{
								monthPerformance2.setCheckResult("无法确定");
								monthPerformance2.setTrColor3("white");
							}
						}
						monthPerformance2.setCreateTime2(date);//创建时间
						monthPerformance2.setCreateUser2(getCurrentUserName());//创建人
					}
				}		
			}//end outer for		
		}else if(performanceIndex.getPerformanceType().equals(PROGRAME_TYPE)){
			for (MonthPerformance monthPerformance2 : monthList) {
				for (int i = 0; i < number; i++) {
					//只对比选中的记录
					if(monthPerformance2.getId().equals(piids[i])){
						//当月份为6月和12月时，评判标准为A、B+、B、C
						if(monthPerformance2.getMonthIndex().equals("6") || monthPerformance2.getMonthIndex().equals("12") ){
							if(monthPerformance2.getColumn1().equals("A") || monthPerformance2.getColumn1().equals("B+")){
								monthPerformance2.setCheckResult(IS_TRAGET);
								monthPerformance2.setTrColor1("green");
							}else if(monthPerformance2.getColumn1().equals("B") || monthPerformance2.getColumn1().equals("C")){
								monthPerformance2.setCheckResult(NOT_TARGET);
								monthPerformance2.setTrColor1("red");
							}else{
								monthPerformance2.setTrColor1("white");
								return ajaxDoneError(MONTH_INDEX_ERROR_FROM_DATABASE);
							}
						}else{
						//当月份不为6月和12月时，评判标准为 正常进行，延期，终止
							if(monthPerformance2.getColumn1().equals(INDEX_NORMAL)){
								monthPerformance2.setCheckResult(IS_TRAGET);
								monthPerformance2.setTrColor1("green");
							}else if(monthPerformance2.getColumn1().equals(INDEX_DELAY) || monthPerformance2.getColumn1().equals(INDEX_END)){
								monthPerformance2.setCheckResult(NOT_TARGET);
								monthPerformance2.setTrColor1("red");
							}else{
								monthPerformance2.setTrColor1("white");
								return ajaxDoneError(MONTH_INDEX_ERROR_FROM_DATABASE);
							}
						}
						monthPerformance2.setCreateTime2(date);//创建时间
						monthPerformance2.setCreateUser2(getCurrentUserName());//创建人
					}
				}//end inner for		
			}
		}else{
			return ajaxDoneError(MONTH_INDEX_ERROR_FROM_DATABASE);
		}
		
		//将记录插入到数据库中，实际上是修改月度绩效指标记录
		for (MonthPerformance monthPerformance2 : monthList) {
			for (int i = 0; i < number; i++) {
				//只对比选中的记录
				if(monthPerformance2.getId().equals(piids[i])){
					try {
						//修改前月度指标记录
						MonthPerformance mpf = monthPerformanceService.selectByPrimaryKey(monthPerformance2.getId());
						//对比修改前后信息
						EditReason editReason = onUpdateCheck(performanceIndex,monthPerformance2,mpf);
						
						//将修改记录插入数据库，实际上是修改数据
						monthPerformanceService.updateByPrimaryKeySelective(monthPerformance2);
						
						//插入修改记录，如果想获取插入记录的主键，使用editReason.getId()可以获取
						editReasonService.insertSelective(editReason);
					} catch (Exception e) {
						return ajaxDoneError(INSERT_ERROR_INFO);
					}
				}
			}		
		}
		return ajaxDoneSuccess(UPDATE_SUCCESS_INFO);
	}
	
	/**
	 * 修改月度绩效指标设定结果
	 * @param performanceIndex 绩效指标类对象
	 * @return 修改是否成功的状态
	 */
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam(value="updateReason",required=false)String updateReason,
			@RequestParam(value="toDel",required=false)String toDel,
			@RequestParam(value="id",required=false)Long id,
			PerformanceIndex performanceIndex,
			MonthPerformance monthPerformance){
		Date date=new Date();
		if(toDel == null){
			return ajaxDoneError("未选中任何要修改的记录");
		}
		System.out.println("将要修改的ID值是"+toDel);
		System.out.println("修改原因"+updateReason);
		
		String[] newArr = toDel.split(",");
		int number = newArr.length;
		
		//获取待修改月度绩效目标的主键
		Long[] piids = new Long[newArr.length];
		for(int i = 0; i < number; i++){
			piids[i] = Long.parseLong(newArr[i]);
		}
		
		List<MonthPerformance> monthList = performanceIndex.getItem();
		
		//获取本年度基准值
		YearPerformance yearPerformance = performanceIndex.getYearPerformance();
		String referenceValue = yearPerformance.getReferenceValue();
		Double referenceValueToDouble = Double.parseDouble(referenceValue);
		
		for(int i = 0; i < number; i++){
			for (MonthPerformance monthPerformance2 : monthList) {
				if(monthPerformance2.getId().equals(piids[i])){
					try {
						//修改前月度指标记录
						MonthPerformance mpf = monthPerformanceService.selectByPrimaryKey(monthPerformance2.getId());
						
						//当月累计目标值
						String monTotalTargetValue = monthPerformance2.getMonTotalTargetValue();
						//当月实际累计目标值
						String monRealityTotalTargetValue = monthPerformance2.getMonRealityTotalTargetValue();
						
						Double monTotalTargetValueToDouble = Double.parseDouble(monTotalTargetValue);
						Double monRealityTotalTargetValueToDouble = Double.parseDouble(monRealityTotalTargetValue);
						
						String indexType = performanceIndex.getIndexType();
						//1.当指标类型为“望大型”时
						if(indexType.equals("望大型")){
							if(monRealityTotalTargetValueToDouble < referenceValueToDouble){
								//(1)当月累计实际值>本年度基准值，显示成：未达标
								monthPerformance2.setCheckResult("未达标");
							}else if(monRealityTotalTargetValueToDouble >= referenceValueToDouble && monRealityTotalTargetValueToDouble < monTotalTargetValueToDouble){
								//(2)本年度基准值=<当月累计实际值<当月累计目标值时，显示成：基准与目标之间
								monthPerformance2.setCheckResult("基准与目标之间");
							}else if(monRealityTotalTargetValueToDouble >= monTotalTargetValueToDouble){
								//(3)当月累计目标值=<当月累计实际值时，显示成：已达标
								monthPerformance2.setCheckResult("已达标");
							}else{
								monthPerformance2.setCheckResult("无法确定");
							}
						}
						
						//2.当指标类型为“望小型”时
						if(indexType.equals("望小型")){
							if(monRealityTotalTargetValueToDouble >= referenceValueToDouble){
								//(1)当月累计实际值>本年度基准值，显示成：已达标
								monthPerformance2.setCheckResult("已达标");
							}else if(monRealityTotalTargetValueToDouble >= monTotalTargetValueToDouble && monRealityTotalTargetValueToDouble < referenceValueToDouble){
								//(2)当月累计目标值=<当月累计实际值<本年度基准值时，显示成：基准与目标之间
								monthPerformance2.setCheckResult("基准与目标之间");
							}else if(monRealityTotalTargetValueToDouble < monTotalTargetValueToDouble){
								//(3)当月累计实际值时<=当月累计目标值 时，显示成：未达标
								monthPerformance2.setCheckResult("未达标");
							}else{
								monthPerformance2.setCheckResult("无法确定");
							}
						}
						
						//3.当指标类型为“望目型”时
						if(indexType.equals("望小型")){
							if(monRealityTotalTargetValueToDouble == monTotalTargetValueToDouble){
								//(1)当月累计实际值＝当月累计目标值，显示成：已达标
								monthPerformance2.setCheckResult("已达标");
							}else if(monRealityTotalTargetValueToDouble != monTotalTargetValueToDouble){
								//(2)当月累计实际值不等于当月累计目标值，显示成：未达标
								monthPerformance2.setCheckResult("未达标");
							}else{
								monthPerformance2.setCheckResult("无法确定");
							}
						}
						
						if(monthPerformance.getCheckMethod() == null){
							monthPerformance2.setCheckMethod("自动计算");
						}
						if(monthPerformance.getColumn1() == null){
							monthPerformance2.setColumn1("终结");
						}
						if(monthPerformance.getColumn2() == null){
							monthPerformance2.setColumn2("D");
						}
						
						//对比修改前后信息
						
						EditReason editReason = onUpdateCheck(performanceIndex,monthPerformance2,mpf);
										
						//修改数据库记录
						monthPerformanceService.updateByPrimaryKeySelective(monthPerformance2);
						
						//插入修改记录，如果想获取插入记录的主键，使用editReason.getId()可以获取
						editReasonService.insertSelective(editReason);

					} catch (Exception e) {
						return ajaxDoneError("更新失败，请联系相关人员");
					}
				}
			}
		}
		return ajaxDoneSuccess("修改成功");
	}
	
	/**
	 * 月度指标设定结果修改时调用方法
	 * @param performanceIndex
	 * @param monthPerformance2
	 * @param mpf
	 * @return
	 */
	public EditReason onUpdateCheck(PerformanceIndex performanceIndex, MonthPerformance monthPerformance2,
			MonthPerformance mpf) {
		stringBuffer.setLength(0);
		
		//修改月份
		String oldMyMonth = DateEditor.formatDate(mpf.getMyMonth(), "yyyy-MM");
		String oldCheckResult = mpf.getCheckResult();
		String oldMonRealityTargetValue = mpf.getMonRealityTargetValue();
		String oldMonRealityTotalTargetValue = mpf.getMonRealityTotalTargetValue();
		//String oldMonRealityChallengeTargetValue = mpf.getMonRealityChallengeTargetValue();
		//String oldColumn1 = mpf.getColumn1();
		//String oldColumn2 = mpf.getColumn2();
		
		String newCheckResult = monthPerformance2.getCheckResult();
		String newMonRealityTargetValue = monthPerformance2.getMonRealityTargetValue();
		String newMonRealityTotalTargetValue = monthPerformance2.getMonRealityTotalTargetValue();
		//String newMonRealityChallengeTargetValue = monthPerformance2.getMonRealityChallengeTargetValue();
		//String newColumn1 = monthPerformance2.getColumn1();
		//String newColumn2 = monthPerformance2.getColumn2();
		
		stringBuffer.append("月份：").append(oldMyMonth).append(";【");
		
		compareChangeInfo(oldCheckResult,newCheckResult,MONCHECKRESULT);
		compareChangeInfo(oldMonRealityTargetValue,newMonRealityTargetValue,MONREALITYTARGETVALUE);
		compareChangeInfo(oldMonRealityTotalTargetValue,newMonRealityTotalTargetValue,MONREALITYTOTALTARGETVALUE);
		if(performanceIndex.getPerformanceType().equals(PROGRAME_TYPE)){
			String oldColumn1 = mpf.getColumn1();
			String newColumn1 = monthPerformance2.getColumn1();
			compareChangeInfo(oldColumn1,newColumn1,COLUMN1);
		}
/*		if(oldCheckResult != null && !oldCheckResult.equals(newCheckResult)){
			updateContentString.append("考核结果：").append(oldCheckResult).append("->").append(newCheckResult).append(";");
		}
		if(oldMonRealityTargetValue !=null && !oldMonRealityTargetValue.equals(newMonRealityTargetValue)){
			updateContentString.append("当月实际目标值：").append(oldMonRealityTargetValue).append("->").append(newMonRealityTargetValue).append(";");
		}
		if(oldMonRealityTotalTargetValue != null && !oldMonRealityTotalTargetValue.equals(newMonRealityTotalTargetValue)){
			updateContentString.append("当月实际累计值：").append(oldMonRealityTotalTargetValue).append("->").append(newMonRealityTotalTargetValue).append(";");
		}
		if(oldMonRealityChallengeTargetValue != null && !oldMonRealityChallengeTargetValue.equals(newMonRealityChallengeTargetValue)){
			updateContentString.append("当月实际挑战目标值：").append(oldMonRealityChallengeTargetValue).append("->").append(newMonRealityChallengeTargetValue).append(";");
		}
		if(oldColumn1 != null && !oldColumn1.equals(newColumn1)){
			updateContentString.append("状态：").append(oldColumn1).append("->").append(newColumn1).append(";");
		}
		if(oldColumn2 != null && !oldColumn2.equals(newColumn2)){
			updateContentString.append("评级：").append(oldColumn2).append("->").append(newColumn2).append(";");
		}*/
		stringBuffer.append("】");
		
		EditReason editReason = new EditReason();
		editReason.setUpdateTime(new Date());
		editReason.setUpdateUser(getCurrentUserName());
		editReason.setUpdateReason(performanceIndex.getUpdateReason());
		editReason.setUpdateType(UPDATESTRING);
		editReason.setOperatorModule(MONTH_INDEX_RESULT_SET);
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
	 * 跳转到月度绩效指标设定结果删除页面
	 * @param id 待删除记录id
	 * @param model 回传数据
	 * @return 删除页面视图
	 */
	@RequestMapping("/toDelete")
	public String toDelete(@RequestParam(value="id",required=false)Long id,Model model,PerformanceIndex performanceIndex){
		performanceIndex=performanceIndexCrudService.getOneMonthPerformanceById(id);
		
		//设置月份信息
		setMonthInfo(performanceIndex);
		
		model.addAttribute("vo",performanceIndex);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/performance/monthPerformanceResult/delete";
	}
	
	/**
	 * 删除月度绩效指标设定结果
	 * @param performanceIndex 绩效指标类对象
	 * @return 删除是否成功的状态
	 */
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value="toDel",required=false)String toDel,
			@RequestParam(value="id",required=false)Long id,
			PerformanceIndex performanceIndex,
			MonthPerformance monthPerformance){
		Date date=new Date();
		if(toDel == null){
			return ajaxDoneError("未选中任何要删除的记录");
		}
		System.out.println("将要修改的ID值是"+toDel);
		
		String[] newArr = toDel.split(",");
		int number = newArr.length;
		
		//获取待修改月度绩效目标的主键
		Long[] piids = new Long[newArr.length];
		for(int i = 0; i < number; i++){
			piids[i] = Long.parseLong(newArr[i]);
		}
		
		List<MonthPerformance> monthList = performanceIndex.getItem();
		for(int i = 0; i < number; i++){
			for (MonthPerformance monthPerformance2 : monthList) {
				if(monthPerformance2.getId().equals(piids[i])){
					try {
						//修改前月度指标记录
						MonthPerformance mpf = monthPerformanceService.selectByPrimaryKey(monthPerformance2.getId());
						
						monthPerformance2.setMonRealityTargetValue("");
						monthPerformance2.setMonRealityTotalTargetValue("");
						monthPerformance2.setMonRealityChallengeTargetValue("");
						monthPerformance2.setCheckResult("");
						monthPerformance2.setColumn1("");
						monthPerformance2.setColumn2("");
						monthPerformance2.setCreateTime2(null);
						monthPerformance2.setCreateUser2("");
						//对比修改前后信息
						
						EditReason editReason = onDeleteCheck(performanceIndex,monthPerformance2,mpf);
										
						//删除数据库记录
						monthPerformanceService.updateByPrimaryKeySelective(monthPerformance2);
						
						//插入修改记录，如果想获取插入记录的主键，使用editReason.getId()可以获取
						editReasonService.insertSelective(editReason);

					} catch (Exception e) {
						return ajaxDoneError("删除失败，请联系相关人员");
					}
				}
			}
		}
		return ajaxDoneSuccess("删除成功");
	}
	
	/**
	 * 删除月度绩效指标考核结果
	 * @param performanceIndex 绩效指标类对象
	 * @return 修改是否成功的状态
	 */
	@RequestMapping("/deleteMonthIndexResult")
	public ModelAndView deleteMonthIndexResult(@RequestParam(value="toDel",required=false)String toDel,
			@RequestParam(value="id",required=false)Long id,
			PerformanceIndex performanceIndex,
			MonthPerformance monthPerformance){
		Date date=new Date();
		if(toDel == null){
			return ajaxDoneError("未选中任何要删除的记录");
		}
		
		Long[] piids = changeStringToArray(toDel);
		int number = piids.length;
		
		//如果月度绩效指标结果不存在，则不允许修改
		PerformanceIndex p1 = performanceIndexCrudService.getOneMonthPerformanceById(id);
		List<MonthPerformance> m1 = p1.getMonthList();
		for (MonthPerformance monthPerformance2 : m1) {
			for (int i = 0; i < number; i++) {
				//只对比选中的记录
				if(monthPerformance2.getId().equals(piids[i])){
					if(monthPerformance2.getCreateUser2() == null){
						return ajaxDoneError(MONTH_INDEX_RESULT_NOT_EXIST_DELETE);
					}
				}
			}
		}
		
		List<MonthPerformance> monthList = performanceIndex.getItem();		
		//设置月份信息
		setMonthInfoByItem(performanceIndex);
		
		//校验字段完整性
		for (MonthPerformance monthPerformance2 : monthList) {
			for (int i = 0; i < number; i++) {
				//只对比选中的记录
				if(monthPerformance2.getId().equals(piids[i])){
					if(!onAddMonthIndexResult(monthPerformance2,DELETE)){
						return ajaxDoneError(checkColumnResult);
					}
				}
			}		
		}
		
		
		//获取本年度基准值、上半年基准值
		YearPerformance yearPerformance = performanceIndex.getYearPerformance();
		//如果本年度基准值或者上半年基准值为空，则说明没有设置年度指标值，提示用户进行设置
		if(yearPerformance.getReferenceValue() == null || yearPerformance.getFirstYearReferenceValue() == null){
			return ajaxDoneError(YEAR_INDEX_VALUE_NULL);
		}
		String referenceValue = yearPerformance.getReferenceValue();
		String firstYearReferenceValue = yearPerformance.getFirstYearReferenceValue();
		
		
		double referenceValueToDouble = Double.parseDouble(referenceValue);	
		double firstYearReferenceValueToDouble = Double.parseDouble(firstYearReferenceValue);	
		
		//当绩效类型为“指标型”时：
		if(performanceIndex.getPerformanceType().equals(INDEX_TYEP)){
			for (MonthPerformance monthPerformance2 : monthList) {
				for (int i = 0; i < number; i++) {
					//只处理选中的记录
					if(monthPerformance2.getId().equals(piids[i])){
						//如果月份为当年前6个月，那么年度基准值设置为上半年基准值
						if(monthPerformance2.getMonthIndex().equals("1") || monthPerformance2.getMonthIndex().equals("2") || monthPerformance2.getMonthIndex().equals("3") 
								|| monthPerformance2.getMonthIndex().equals("4") || monthPerformance2.getMonthIndex().equals("5") || monthPerformance2.getMonthIndex().equals("6") ){
							referenceValueToDouble = firstYearReferenceValueToDouble;
						}
						//当月累计目标值
						String monTotalTargetValue = monthPerformance2.getMonTotalTargetValue();
						//当月实际累计目标值
						String monRealityTotalTargetValue = monthPerformance2.getMonRealityTotalTargetValue();
						
						double monTotalTargetValueToDouble = Double.parseDouble(monTotalTargetValue);
						double monRealityTotalTargetValueToDouble = Double.parseDouble(monRealityTotalTargetValue);
						
						String indexType = performanceIndex.getIndexType();
						//1.当指标类型为“望大型”时
						if(indexType.equals("望大型")){
							if(monRealityTotalTargetValueToDouble < referenceValueToDouble){
								//(1)当月累计实际值>本年度基准值，显示成：未达标
								monthPerformance2.setCheckResult("未达标");
							}else if(monRealityTotalTargetValueToDouble >= referenceValueToDouble && monRealityTotalTargetValueToDouble < monTotalTargetValueToDouble){
								//(2)本年度基准值=<当月累计实际值<当月累计目标值时，显示成：基准与目标之间
								monthPerformance2.setCheckResult("基准与目标之间");
							}else if(monRealityTotalTargetValueToDouble >= monTotalTargetValueToDouble){
								//(3)当月累计目标值=<当月累计实际值时，显示成：已达标
								monthPerformance2.setCheckResult("已达标");
							}else{
								monthPerformance2.setCheckResult("无法确定");
							}
						}
						
						//2.当指标类型为“望小型”时
						if(indexType.equals("望小型")){
							if(monRealityTotalTargetValueToDouble >= referenceValueToDouble){
								//(1)当月累计实际值>本年度基准值，显示成：已达标
								monthPerformance2.setCheckResult("已达标");
							}else if(monRealityTotalTargetValueToDouble >= monTotalTargetValueToDouble && monRealityTotalTargetValueToDouble < referenceValueToDouble){
								//(2)当月累计目标值=<当月累计实际值<本年度基准值时，显示成：基准与目标之间
								monthPerformance2.setCheckResult("基准与目标之间");
							}else if(monRealityTotalTargetValueToDouble < monTotalTargetValueToDouble){
								//(3)当月累计实际值时<=当月累计目标值 时，显示成：未达标
								monthPerformance2.setCheckResult("未达标");
							}else{
								monthPerformance2.setCheckResult("无法确定");
							}
						}
						
						//3.当指标类型为“望目型”时
						if(indexType.equals("望目型")){
							if(monRealityTotalTargetValueToDouble == monTotalTargetValueToDouble){
								//(1)当月累计实际值＝当月累计目标值，显示成：已达标
								monthPerformance2.setCheckResult("已达标");
							}else if(monRealityTotalTargetValueToDouble != monTotalTargetValueToDouble){
								//(2)当月累计实际值不等于当月累计目标值，显示成：未达标
								monthPerformance2.setCheckResult("未达标");
							}else{
								monthPerformance2.setCheckResult("无法确定");
							}
						}
						
						monthPerformance2.setCreateTime2(date);//创建时间
						monthPerformance2.setCreateUser2(getCurrentUserName());//创建人
					}
				}		
			}		
		}else if(performanceIndex.getPerformanceType().equals(PROGRAME_TYPE)){
			for (MonthPerformance monthPerformance2 : monthList) {
				for (int i = 0; i < number; i++) {
					//只对比选中的记录
					if(monthPerformance2.getId().equals(piids[i])){
						//当月份为6月和12月时，评判标准为A、B+、B、C
						if(monthPerformance2.getMonthIndex().equals("6") || monthPerformance2.getMonthIndex().equals("12") ){
							if(monthPerformance2.getColumn1().equals("A") || monthPerformance2.getColumn1().equals("B+")){
								monthPerformance2.setCheckResult(IS_TRAGET);
							}else if(monthPerformance2.getColumn1().equals("B") || monthPerformance2.getColumn1().equals("C")){
								monthPerformance2.setCheckResult(NOT_TARGET);
							}else{
								return ajaxDoneError(MONTH_INDEX_ERROR_FROM_DATABASE);
							}
						}else{
						//当月份不为6月和12月时，评判标准为 正常进行，延期，终止
							if(monthPerformance2.getColumn1().equals(INDEX_NORMAL)){
								monthPerformance2.setCheckResult(IS_TRAGET);
							}else if(monthPerformance2.getColumn1().equals(INDEX_DELAY) || monthPerformance2.getColumn1().equals(INDEX_END)){
								monthPerformance2.setCheckResult(NOT_TARGET);
							}else{
								return ajaxDoneError(MONTH_INDEX_ERROR_FROM_DATABASE);
							}
						}
					}
				}//end inner for		
			}
		}else{
			return ajaxDoneError(MONTH_INDEX_ERROR_FROM_DATABASE);
		}
		
		//将记录插入到数据库中，实际上是修改月度绩效指标记录
		for (MonthPerformance monthPerformance2 : monthList) {
			for (int i = 0; i < number; i++) {
				//只对比选中的记录
				if(monthPerformance2.getId().equals(piids[i])){
					try {
						//删除前月度指标记录
						MonthPerformance mpf = monthPerformanceService.selectByPrimaryKey(monthPerformance2.getId());
						
						monthPerformance2.setMonRealityTargetValue("");
						monthPerformance2.setMonRealityTotalTargetValue("");
						monthPerformance2.setMonRealityChallengeTargetValue("");
						monthPerformance2.setCheckResult("");
						monthPerformance2.setColumn1("");
						monthPerformance2.setColumn2("");
						monthPerformance2.setCreateTime2(null);
						monthPerformance2.setCreateUser2("");
						
						//对比删除前后信息
						EditReason editReason = onDeleteCheck(performanceIndex,monthPerformance2,mpf);
						
						
						//将删除记录插入数据库，实际上是修改数据
						monthPerformanceService.updateByPrimaryKeySelective(monthPerformance2);
						
						//插入删除记录，如果想获取插入记录的主键，使用editReason.getId()可以获取
						editReasonService.insertSelective(editReason);
					} catch (Exception e) {
						return ajaxDoneError(DELETE_ERROR_INFO);
					}
				}
			}		
		}
		return ajaxDoneSuccess(DELETE_SUCCESS_INFO);
	}

	public EditReason onDeleteCheck(PerformanceIndex performanceIndex, MonthPerformance monthPerformance2,
			MonthPerformance mpf) {
		StringBuffer updateContentString = new StringBuffer();
		
		String oldCheckResult = mpf.getCheckResult();
		String oldMonRealityTargetValue = mpf.getMonRealityTargetValue();
		String oldMonRealityTotalTargetValue = mpf.getMonRealityTotalTargetValue();
		//String oldMonRealityChallengeTargetValue = mpf.getMonRealityChallengeTargetValue();
		String oldColumn1 = mpf.getColumn1();
		//String oldColumn2 = mpf.getColumn2();
		
		updateContentString.append("删除记录为：【")
		.append(MONREALITYTARGETVALUE+"：").append(oldMonRealityTargetValue).append(";")
		.append(MONREALITYTOTALTARGETVALUE+"：").append(oldMonRealityTotalTargetValue).append(";")
		.append(MONCHECKRESULT+"：").append(oldCheckResult).append(";")
		.append(COLUMN1+"：").append(oldColumn1).append(";】");
		//.append("评价：").append(oldColumn2).append(";")
		//.append("实际挑战目标值：").append(oldMonRealityChallengeTargetValue).append(";】");

		EditReason editReason = new EditReason();
		editReason.setUpdateTime(new Date());
		editReason.setUpdateUser(getCurrentUserName());
		editReason.setUpdateReason("删除");//删除时原因设置为删除
		editReason.setUpdateType(DELETESTRING);
		editReason.setOperatorModule(MONTH_INDEX_RESULT_SET);
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
			//bs.setPage(page); 
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
			//查询数据库
			List<PerformanceIndex> list = performanceIndexCrudService.getMonthPerformanceAll(bs);
			
			String [] CN = {CHECKYEAR,FACTORYNAME,DEPARTMENTNAME,PERFORMANCETARGETCLASS,PERFORMANCETYPE,
					INDEXCONTENT,INDEXTYPE,WEIGHT,COMPANY,FORMULA,INDEX_REFERENCEVALUE,INDEX_MIDDLEVALUE,INDEX_TARGETVALUE,
					LASTYEARREALITYVALUE,FIRSTYEARREFERENCEVALUE,REFERENCEVALUE,TARGETVALUE,FIRSTYEARTARGETVALUE,SECONDYEARTARGETVALUE,
					MYMONTH,MONTARGETVALUE,MONTOTALTARGETVALUE,MONCHALLENGETARGETVALUE,MONREALITYTARGETVALUE,
					MONREALITYTOTALTARGETVALUE,MONCHECKRESULT,COLUMN1,
					CREATE_USER,CREATE_TIME};
			List<String[]> excelList = new ArrayList<String[]>();
			
			List<MonthPerformance> monthList = null;
			for (PerformanceIndex per : list) {
				YearPerformance yea = per.getYearPerformance();
				//如果年度指标值为空
				if(per.getYearPerformance() == null){					
					yea = new YearPerformance();
					yea.setLastYearRealityValue("");
					yea.setFirstYearReferenceValue("");
					yea.setReferenceValue("");
					yea.setTargetValue("");
					yea.setFirstYearTargetValue("");
					yea.setSecondYearTargetValue("");
				}
				//如果月度指标列表为空
				if(per.getMonthList() == null){
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

					itemStr[13] = yea.getLastYearRealityValue();
					itemStr[14] = yea.getFirstYearReferenceValue();
					itemStr[15] = yea.getReferenceValue();
					itemStr[16] = yea.getTargetValue();
					itemStr[17] = yea.getFirstYearTargetValue();
					itemStr[18] = yea.getSecondYearTargetValue();
					
					itemStr[19] = "";
					itemStr[20] = "";
					itemStr[21] = "";
					itemStr[22] = "";
					itemStr[23] = "";
					itemStr[24] = "";
					itemStr[25] = "";
					itemStr[26] = "";
					itemStr[27] = per.getCreateUser();
					itemStr[28] = DateEditor.formatDate(per.getCreateTime(), "yyyy-MM-dd");
					excelList.add(itemStr);	
					continue;//结束本次循环，重新开始下一条指标记录的循环
				}
				monthList = per.getMonthList();
				for(MonthPerformance mon : monthList ){
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

					itemStr[13] = yea.getLastYearRealityValue();
					itemStr[14] = yea.getFirstYearReferenceValue();
					itemStr[15] = yea.getReferenceValue();
					itemStr[16] = yea.getTargetValue();
					itemStr[17] = yea.getFirstYearTargetValue();
					itemStr[18] = yea.getSecondYearTargetValue();
					
					itemStr[19] = DateEditor.formatDate(mon.getMyMonth(), "yyyy-MM");
					itemStr[20] = mon.getMonTargetValue();
					itemStr[21] = mon.getMonTotalTargetValue();
					itemStr[22] = mon.getMonChallengeTargetValue();
					
					//如果月度指标结果为空
					if(mon.getMonRealityTargetValue() == null){
						itemStr[23] = "";
						itemStr[24] = "";
						itemStr[25] = "";
						itemStr[26] = "";
						itemStr[27] = mon.getCreateUser1();
						itemStr[28] = DateEditor.formatDate(mon.getCreateTime1(), "yyyy-MM-dd");
					}else{
						itemStr[23] = mon.getMonRealityTargetValue();
						itemStr[24] = mon.getMonRealityTotalTargetValue();
						itemStr[25] = mon.getCheckResult();
						itemStr[26] = mon.getColumn1();
						itemStr[27] = mon.getCreateUser2();
						itemStr[28] = DateEditor.formatDate(mon.getCreateTime2(), "yyyy-MM-dd");
					}
					
					excelList.add(itemStr);
				}
			}
			
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			
				ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
				String contentType =  "application/msexcel" ;
				ExcelUtilities.downloadExcel(request, response, filePath, contentType, MONTH_INDEX_RESULT_SET+fname);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	
	public String dealMonthValue(String month){
		if(month.equals("01")){
			monthString = "1";
		}else if(month.equals("02")){
			monthString = "2";
		}else if(month.equals("03")){
			monthString = "3";
		}else if(month.equals("04")){
			monthString = "4";
		}else if(month.equals("05")){
			monthString = "5";
		}else if(month.equals("06")){
			monthString = "6";
		}else if(month.equals("07")){
			monthString = "7";
		}else if(month.equals("08")){
			monthString = "8";
		}else if(month.equals("09")){
			monthString = "9";
		}else if(month.equals("10")){
			monthString = "10";
		}else if(month.equals("11")){
			monthString = "11";
		}else if(month.equals("12")){
			monthString = "12";
		}else{
			System.out.println("月份信息转换异常");
		}
		
		return monthString;
	}
	
	/**
	 * 设置月份序列信息
	 * @param performanceIndx
	 */
	public void setMonthInfo(PerformanceIndex performanceIndex){
		//设置月份信息
		List<MonthPerformance> monthList = performanceIndex.getMonthList();
		for (MonthPerformance monthPerformance : monthList) {
			//取月度指标的时间，截取月份信息
			monthString = DateEditor.formatDate(monthPerformance.getMyMonth(), "MM");
			//如果月份转换异常，前台显示时需要处理（若是查询数据库没有异常，该转换一般不会出现异常）
			monthPerformance.setMonthIndex(dealMonthValue(monthString));
		}
	}
	
	/**
	 * 设置月份序列信息
	 * @param performanceIndx
	 */
	public void setMonthInfoByItem(PerformanceIndex performanceIndex){
		//设置月份信息
		List<MonthPerformance> monthList = performanceIndex.getItem();
		for (MonthPerformance monthPerformance : monthList) {
			//取月度指标的时间，截取月份信息
			monthString = DateEditor.formatDate(monthPerformance.getMyMonth(), "MM");
			//如果月份转换异常，前台显示时需要处理（若是查询数据库没有异常，该转换一般不会出现异常）
			monthPerformance.setMonthIndex(dealMonthValue(monthString));
		}
	}
	
	/**
	 * 设置月份序列信息
	 * @param performanceIndx
	 */
	public void setMonthInfoOnUpdate(PerformanceIndex performanceIndex){
		//设置月份信息
		List<MonthPerformance> monthList = performanceIndex.getItem();
		for (MonthPerformance monthPerformance : monthList) {
			//取月度指标的时间，截取月份信息
			monthString = DateEditor.formatDate(monthPerformance.getMyMonth(), "MM");
			//如果月份转换异常，前台显示时需要处理（若是查询数据库没有异常，该转换一般不会出现异常）
			monthPerformance.setMonthIndex(dealMonthValue(monthString));
		}
	}
	
	/**
	 * 将形如“3,7,9,15,26”的字符串转为Long数组
	 * @param targetStr 待转化字符串
	 * @return Long型数组
	 */
	public Long[] changeStringToArray(String targetStr){
		String[] newArr = targetStr.split(",");
		int number = newArr.length;

		Long[] piids = new Long[newArr.length];
		for(int i = 0; i < number; i++){
			piids[i] = Long.parseLong(newArr[i]);
		}
		return piids;		
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
	 * 检查月度绩效结果设定字段是否正确(当项目类型为“指标型”时)
	 * @param monthPerformance
	 * @return 检查全部通过返回true,否则返回false
	 */
	public boolean onAddMonthIndexResult(MonthPerformance monthPerformance,String operatorType){
		Date date = new Date();
		String monRealityTargetValue = monthPerformance.getMonRealityTargetValue().trim();//当月实际目标值
		String monRealityTotalTargetValue = monthPerformance.getMonRealityTotalTargetValue().trim();//当月实际累计目标值
		
		if(ColumnDealUtil.checkColumnIsEmptyOrNull(monRealityTargetValue)){
			checkColumnResult = MONREALITYVALUE+INPUTERROR;
			return false;
		}else if(!ColumnDealUtil.checkStringToNumber(monRealityTargetValue)){
			checkColumnResult = MONREALITYVALUE+REQUIRED_NUMBER_TYPE;
			return false;
		}else{
			monthPerformance.setMonRealityTargetValue(monRealityTargetValue);
		}
		
		if(ColumnDealUtil.checkColumnIsEmptyOrNull(monRealityTotalTargetValue)){
			checkColumnResult = MONREALITYTOTALVALUE+INPUTERROR;
			return false;
		}else if(!ColumnDealUtil.checkStringToNumber(monRealityTotalTargetValue)){
			checkColumnResult = MONREALITYTOTALVALUE+REQUIRED_NUMBER_TYPE;
			return false;
		}else{
			monthPerformance.setMonRealityTotalTargetValue(monRealityTotalTargetValue);
		}
		
		if(operatorType.equals("create")){
			monthPerformance.setCreateTime2(date);
			monthPerformance.setCreateUser2(getCurrentUserName());
		}
		
		return true;
	}
	
	/**
	 * 检查月度绩效结果设定字段是否正确(当项目类型为“项目型”时)
	 * @param monthPerformance
	 * @return 检查全部通过返回true,否则返回false
	 */
	public boolean onAddMonthIndexResultA(MonthPerformance monthPerformance,String operatorType){
		Date date = new Date();
		String monRealityTargetValue = monthPerformance.getMonRealityTargetValue().trim();//当月实际目标值
		String monRealityTotalTargetValue = monthPerformance.getMonRealityTotalTargetValue().trim();//当月实际累计目标值
		
		if(ColumnDealUtil.checkColumnIsEmptyOrNull(monRealityTargetValue)){
			checkColumnResult = MONREALITYVALUE+INPUTERROR;
			return false;
		}else{
			monthPerformance.setMonRealityTargetValue(monRealityTargetValue);
		}
		
		if(ColumnDealUtil.checkColumnIsEmptyOrNull(monRealityTotalTargetValue)){
			checkColumnResult = MONREALITYTOTALVALUE+INPUTERROR;
			return false;
		}else{
			monthPerformance.setMonRealityTotalTargetValue(monRealityTotalTargetValue);
		}
		
		if(operatorType.equals("create")){
			monthPerformance.setCreateTime2(date);
			monthPerformance.setCreateUser2(getCurrentUserName());
		}
		
		return true;
	}
	
	//以下功能为Excel数据批量导入使用
	/**
	 * 跳转到Excel导入页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/indexImportInit")
	public String indexImportInit(Model model){
		return "qms/bph/base/performance/monthPerformanceResult/indexImportInit";
	}
	
	/**
     * 获取excel数据
     * @param model
     * @return
     */
	@RequestMapping("/getExcelData")
	public void getExcelData(@RequestParam(value = "fileUpload", required = false) MultipartFile fileUpload,
			HttpServletResponse respon){
		respon.setContentType("text/html;charset=UTF-8"); 
		respon.setHeader("Cache-Control", "no-cache"); 
		PrintWriter out = null;
		String data="";
		if(fileUpload != null){
			try {
				InputStream inputStream= fileUpload.getInputStream();
				List<String[]> dataList =  ExcelReaderJXL.readExcel(inputStream);
				
				//校验Excel数据
				String[] res = indexImportService.checkIndexImportData(dataList);
				if(res!=null && !res[1].equals("")){ //有错误提示
					data = "错误提示：\n"+res[1];
				}else{
					data = res[0];
				}
				out = respon.getWriter();
				out.write(data);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(out != null)
				{
					out.close();
				}
			}
		}
	}
	
	/**
	 * 写入excel数据
	 */
	@RequestMapping("/uploadData")
	public void uploadData(@RequestParam("uploadData")String uploadData,
			HttpServletRequest req, HttpServletResponse respon) throws Exception {
		PrintWriter out = null;
		String msg = "success";
		String dealUploadDataResult = "";//记录处理数据的结果
		try {
			respon.setContentType("text/json;charset=UTF-8"); 
			respon.setHeader("Cache-Control", "no-cache"); 
			out = respon.getWriter();
			
			if(uploadData==null || uploadData.equals("")){
				throw new Exception("上传数据异常，请联系管理员");
			}
			logger.debug("校验上传绩效指标数据，准备执行插入或更新操作...");
			dealUploadDataResult = indexImportService.setUploadData(uploadData);
			if(!dealUploadDataResult.equals("0")){
				throw new Exception(dealUploadDataResult);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			msg = e.getMessage();
		}
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", msg);

		JSONObject jsonObject = JSONObject.fromObject( map );
		out.write(jsonObject.toString());
	}
}
