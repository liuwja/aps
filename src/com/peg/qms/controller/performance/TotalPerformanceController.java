package com.peg.qms.controller.performance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.performance.MonthPerformance;
import com.peg.model.performance.PerformanceIndex;
import com.peg.qms.controller.BaseController;
import com.peg.service.CommonServiceI;
import com.peg.service.performance.DepartmentService;
import com.peg.service.performance.EditReasonService;
import com.peg.service.performance.MonthPerformanceService;
import com.peg.service.performance.PerformanceCommon;
import com.peg.service.performance.PerformanceIndexCrudService;
import com.peg.service.performance.YearPerformanceService;
import com.peg.web.util.DateEditor;
/**
 * 综合绩效明细控制层
 * @author xuanm
 *
 */
@Controller
@RequestMapping("ptm/totalPerformanceList")
public class TotalPerformanceController extends BaseController implements PerformanceCommon{
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
	
	/**
	 * 月份顺序，用于前台页面横向显示12个月
	 */
	private String monthString = "0";
	
	/**
	 * 获取年度绩效明细信息
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
		
		List<PerformanceIndex> list = performanceIndexCrudService.getTotalPerformanceByPage(bs);
		
		//设置月份序列信息
		for (PerformanceIndex performanceIndex2 : list) {
			setMonthInfo(performanceIndex2);
		}
		
		
		for (PerformanceIndex performanceIndex2 : list) {
			//如果为空，则说明年度指标值不存在，暂时不处理本条记录
			if(performanceIndex2.getYearPerformance() == null){
				continue;
			}
			//获取年度基准值
			String referenceValue = performanceIndex2.getYearPerformance().getReferenceValue();
			//获取上半年基准值
			String firstYearReferenceValue = performanceIndex2.getYearPerformance().getFirstYearReferenceValue();
			double referenceValueToDouble;
			double firstYearReferenceValueToDouble;
			if(performanceIndex2.getPerformanceType().equals(INDEX_TYEP)){
				referenceValueToDouble = Double.parseDouble(referenceValue);	
				firstYearReferenceValueToDouble = Double.parseDouble(firstYearReferenceValue);
			}else{
				referenceValueToDouble = 0;		
				firstYearReferenceValueToDouble=0;
			}

			
			//如果月度指标设定为空，则跳过本次循环
			if(performanceIndex2.getMonthList() == null){
				continue;
			}
			List<MonthPerformance> monthList = performanceIndex2.getMonthList();

			//当项目类型为指标型时
			if(performanceIndex2.getPerformanceType().equals(INDEX_TYEP)){
				for (MonthPerformance monthPerformance2 : monthList) {
					//如果月份序列为空，说明不存在月度指标设定记录
					if(monthPerformance2.getMonthIndex() == null){
						continue;
					}			
					//如果当月累计实际值为空，说明不存在月度指标设定结果记录
					if(monthPerformance2.getMonRealityTotalTargetValue() == null){
						continue;
					}
					
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
					
					String indexType = performanceIndex2.getIndexType();
					//1.当指标类型为“望大型”时					
					if(indexType.equals(BIG_THAN_TARGET_TYPE)){
						
						/*if(monRealityTotalTargetValueToDouble < referenceValueToDouble){
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
						}*/
						if(monthPerformance2.getCheckResult().equals(NOT_TARGET)){
							//(1)当月累计实际值>本年度基准值，显示成：未达标，红色
							monthPerformance2.setTrColor1("red");
						}else if(monthPerformance2.getCheckResult().equals(INDEX_MIDDLEVALUE)){
							//(2)当月挑战目标值<当月累计实际值<=本年度基准值时，显示成：基准与目标之间，黄色
							monthPerformance2.setTrColor1("yellow");
						}else if(monthPerformance2.getCheckResult().equals(IS_TRAGET)){
							//(3)当月累计实际值时<=当月挑战目标值 时，显示成：已达标，绿色
							monthPerformance2.setTrColor1("green");
						}else{
							monthPerformance2.setTrColor1("white");
						}
					}
					else if(indexType.equals(LESS_THAN_TARGET_TYPE)){//2.当指标类型为“望小型”时
						/*if(monRealityTotalTargetValueToDouble > referenceValueToDouble){
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
						}*/
						if(monthPerformance2.getCheckResult().equals(NOT_TARGET)){
							//(1)当月累计实际值>本年度基准值，显示成：未达标，红色
							monthPerformance2.setTrColor2("red");
						}else if(monthPerformance2.getCheckResult().equals(INDEX_MIDDLEVALUE)){
							//(2)当月挑战目标值<当月累计实际值<=本年度基准值时，显示成：基准与目标之间，黄色
							monthPerformance2.setTrColor2("yellow");
						}else if(monthPerformance2.getCheckResult().equals(IS_TRAGET)){
							//(3)当月累计实际值时<=当月挑战目标值 时，显示成：已达标，绿色
							monthPerformance2.setTrColor2("green");
						}else{
							monthPerformance2.setTrColor2("white");
						}
					}
					else if(indexType.equals(EQUAL_TARGET_TYPE)){//3.当指标类型为“望目型”时
						/*if(monRealityTotalTargetValueToDouble == monChallengeTargetValueToDouble){
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
						}*/
						if(monthPerformance2.getCheckResult().equals(NOT_TARGET)){
							//(1)当月累计实际值>本年度基准值，显示成：未达标，红色
							monthPerformance2.setTrColor3("red");
						}else if(monthPerformance2.getCheckResult().equals(INDEX_MIDDLEVALUE)){
							//(2)当月挑战目标值<当月累计实际值<=本年度基准值时，显示成：基准与目标之间，黄色
							monthPerformance2.setTrColor3("yellow");
						}else if(monthPerformance2.getCheckResult().equals(IS_TRAGET)){
							//(3)当月累计实际值时<=当月挑战目标值 时，显示成：已达标，绿色
							monthPerformance2.setTrColor3("green");
						}else{
							monthPerformance2.setTrColor3("white");
						}
					}
					else{
						monthPerformance2.setTrColor1("white");
						monthPerformance2.setTrColor2("white");
						monthPerformance2.setTrColor3("white");
					}					
				}//end for
			}else if(performanceIndex2.getPerformanceType().equals(PROGRAME_TYPE)){//当指标类型为“项目型”
				for (MonthPerformance monthPerformance2 : monthList) {
					//如果月份序列为空，说明不存在月度指标设定记录
					if(monthPerformance2.getMonthIndex() == null){
						continue;
					}					
					//如果当月累计实际值为空，说明不存在月度指标设定结果记录
					if(monthPerformance2.getMonRealityTotalTargetValue() == null){
						continue;
					}
					
					//“已达标”显示成绿色，“未达标”显示成红色
					if(monthPerformance2.getCheckResult().equals(IS_TRAGET)){
						monthPerformance2.setTrColor1("green");
					}else if(monthPerformance2.getCheckResult().equals(NOT_TARGET)){
						monthPerformance2.setTrColor1("red");
					}else{
						monthPerformance2.setTrColor1("white");
					}
				}//end for
			}else{
				for (MonthPerformance monthPerformance2 : monthList) {
					//如果月份序列为空，说明不存在月度指标设定记录
					if(monthPerformance2.getMonthIndex() == null){
						continue;
					}				
					//如果当月累计实际值为空，说明不存在月度指标设定结果记录
					if(monthPerformance2.getMonRealityTotalTargetValue() == null){
						continue;
					}
					
					monthPerformance2.setTrColor1("white");
					monthPerformance2.setTrColor2("white");
					monthPerformance2.setTrColor3("white");
				}//end for
			}
			
		}
		model.addAttribute("list",list);
		model.addAttribute("performanceIndex",performanceIndex);
		model.addAttribute("page",page);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		
		return "qms/bph/base/performance/totalPerformance/list";
	}	
	
	/**
	 * 获取年度绩效明细信息
	 * @param performanceIndex 绩效指标类对象
	 * @param model
	 * @param page 分页信息
	 * @return
	 */
	@RequestMapping("/list1")
	public String list1(PerformanceIndex performanceIndex,Model model, PageParameter page){
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
		
		List<PerformanceIndex> list = performanceIndexCrudService.getTotalPerformanceByPage(bs);
		
		//设置月份序列信息
		for (PerformanceIndex performanceIndex2 : list) {
			setMonthInfo(performanceIndex2);
		}
		
		
		for (PerformanceIndex performanceIndex2 : list) {
			//如果为空，则说明年度指标值不存在，暂时不处理本条记录
			if(performanceIndex2.getYearPerformance() == null){
				continue;
			}
			//获取年度基准值
			String referenceValue = performanceIndex2.getYearPerformance().getReferenceValue();
			//获取上半年基准值
			String firstYearReferenceValue = performanceIndex2.getYearPerformance().getFirstYearReferenceValue();
			double referenceValueToDouble;
			double firstYearReferenceValueToDouble;
			if(performanceIndex2.getPerformanceType().equals(INDEX_TYEP)){
				referenceValueToDouble = Double.parseDouble(referenceValue);	
				firstYearReferenceValueToDouble = Double.parseDouble(firstYearReferenceValue);
			}else{
				referenceValueToDouble = 0;		
				firstYearReferenceValueToDouble=0;
			}

			
			//如果月度指标设定为空，则跳过本次循环
			if(performanceIndex2.getMonthList() == null){
				continue;
			}
			List<MonthPerformance> monthList = performanceIndex2.getMonthList();

			//当项目类型为指标型时
			if(performanceIndex2.getPerformanceType().equals(INDEX_TYEP)){
				for (MonthPerformance monthPerformance2 : monthList) {
					//如果月份序列为空，说明不存在月度指标设定记录
					if(monthPerformance2.getMonthIndex() == null){
						continue;
					}			
					//如果当月累计实际值为空，说明不存在月度指标设定结果记录
					if(monthPerformance2.getMonRealityTotalTargetValue() == null){
						continue;
					}
					
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
					
					//1.当指标类型为“望大型”时
					String indexType = performanceIndex2.getIndexType();
					if(indexType.equals(BIG_THAN_TARGET_TYPE)){
						//1.当指标类型为“望大型”时
						if(monRealityTotalTargetValueToDouble < referenceValueToDouble){
							//(1)当月累计实际值<本年度基准值，显示成：红色
							monthPerformance2.setTrColor1("red");
						}else if(monRealityTotalTargetValueToDouble >= referenceValueToDouble && monRealityTotalTargetValueToDouble < monChallengeTargetValueToDouble){
							//(2)本年度基准值=<当月累计实际值<当月累计目标值时，显示成：黄色
							monthPerformance2.setTrColor1("yellow");
						}else if(monChallengeTargetValueToDouble <= monRealityTotalTargetValueToDouble){
							//(3)当月累计目标值=<当月累计实际值时，显示成：绿色
							monthPerformance2.setTrColor1("green");
						}else{
							monthPerformance2.setTrColor1("white");
						}
					}
					else if(indexType.equals(LESS_THAN_TARGET_TYPE)){
						//2.当指标类型为“望小型”时
						if(monRealityTotalTargetValueToDouble > referenceValueToDouble){
							//(1)当月累计实际值>本年度基准值，显示成：红色
							monthPerformance2.setTrColor2("red");
						}else if(monChallengeTargetValueToDouble <= monRealityTotalTargetValueToDouble && monRealityTotalTargetValueToDouble < referenceValueToDouble){
							//(2)当月累计目标值=<当月累计实际值<本年度基准值时，显示成：黄色
							monthPerformance2.setTrColor2("yellow");
						}else if(monRealityTotalTargetValueToDouble <= monChallengeTargetValueToDouble){
							//(3)当月累计实际值时<=当月累计目标值 时，显示成：绿色
							monthPerformance2.setTrColor2("green");
						}else{
							monthPerformance2.setTrColor2("white");
						}
					}
					else if(indexType.equals(EQUAL_TARGET_TYPE)){
						//3.当指标类型为“望目型”时
						if(monRealityTotalTargetValueToDouble == monChallengeTargetValueToDouble){
							//(1)当月累计实际值＝当月累计目标值，显示成：绿色
							monthPerformance2.setTrColor3("green");
						}else if(monRealityTotalTargetValueToDouble != monChallengeTargetValueToDouble){
							//(2)当月累计实际值不等于当月累计目标值，显示成：红色
							monthPerformance2.setTrColor3("red");
						}else{
							monthPerformance2.setTrColor3("white");
						}
					}
					else{
						monthPerformance2.setTrColor1("white");
						monthPerformance2.setTrColor2("white");
						monthPerformance2.setTrColor3("white");
					}					
				}//end for
			}else if(performanceIndex2.getPerformanceType().equals(PROGRAME_TYPE)){//当指标类型为“项目型”
				for (MonthPerformance monthPerformance2 : monthList) {
					//如果月份序列为空，说明不存在月度指标设定记录
					if(monthPerformance2.getMonthIndex() == null){
						continue;
					}					
					//如果当月累计实际值为空，说明不存在月度指标设定结果记录
					if(monthPerformance2.getMonRealityTotalTargetValue() == null){
						continue;
					}
					
					//“已达标”显示成绿色，“未达标”显示成红色
					if(monthPerformance2.getCheckResult().equals(IS_TRAGET)){
						monthPerformance2.setTrColor1("green");
					}else if(monthPerformance2.getCheckResult().equals(NOT_TARGET)){
						monthPerformance2.setTrColor1("red");
					}else{
						monthPerformance2.setTrColor1("white");
					}
				}//end for
			}else{
				for (MonthPerformance monthPerformance2 : monthList) {
					//如果月份序列为空，说明不存在月度指标设定记录
					if(monthPerformance2.getMonthIndex() == null){
						continue;
					}				
					//如果当月累计实际值为空，说明不存在月度指标设定结果记录
					if(monthPerformance2.getMonRealityTotalTargetValue() == null){
						continue;
					}
					
					monthPerformance2.setTrColor1("white");
					monthPerformance2.setTrColor2("white");
					monthPerformance2.setTrColor3("white");
				}//end for
			}
			
		}
		model.addAttribute("list",list);
		model.addAttribute("performanceIndex",performanceIndex);
		model.addAttribute("page",page);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		
		return "qms/bph/base/performance/totalPerformance/list";
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
	 * 根据月份字符串信息，设置月份的顺序。值为1,2,3,···，10,11,12
	 * @param month
	 * @return
	 */
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
}
