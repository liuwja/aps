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
import com.peg.model.performance.MonthIndex;
import com.peg.model.performance.MonthPerformance;
import com.peg.model.performance.PerformanceIndex;
import com.peg.model.performance.YearPerformance;
import com.peg.qms.controller.BaseController;
import com.peg.service.CommonServiceI;
import com.peg.service.performance.DepartmentService;
import com.peg.service.performance.EditReasonService;
import com.peg.service.performance.MonthPerformanceService;
import com.peg.service.performance.PerformanceCommon;
import com.peg.service.performance.PerformanceIndexCrudService;
import com.peg.service.performance.YearPerformanceService;
import com.peg.web.util.ColumnDealUtil;
import com.peg.web.util.DateEditor;
import com.peg.web.util.ExcelUtilities;

/**
 * 月度绩效控制层
 * @author xuanm
 *
 */
@Controller
@RequestMapping("ptm/monthPerformanceSet")
public class MonthPerformanceController extends BaseController implements PerformanceCommon{
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
	 * 字段值检查结果
	 */
	private String checkColumnResult = "";
	
	/**
	 * 修改内容
	 */
	private StringBuffer stringBuffer = new StringBuffer("");
	
	/**
	 * 获取月度绩效指标
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
		
		List<PerformanceIndex> list = performanceIndexCrudService.getMonthPerformance(bs);
		model.addAttribute("list",list);
		model.addAttribute("performanceIndex",performanceIndex);
		model.addAttribute("page",page);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		
		List<Long> idList = new ArrayList<Long>();
		for(PerformanceIndex pi:list){
			idList.add(pi.getId());
		}
		model.addAttribute("idList",idList);
		return "qms/bph/base/performance/monthPerformance/list";
	}		
	
	/**
	 * 跳转到月度绩效指标新增页面
	 * @param model 回传信息
	 * @return
	 */
	@RequestMapping("/add")
	

	public String add(@RequestParam(value="id",required=false) Long id,Model model,PerformanceIndex performanceIndex){		
		performanceIndex=performanceIndexCrudService.getOneMonthPerformanceById(id);
		model.addAttribute("vo",performanceIndex);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/performance/monthPerformance/add";
	}
	
	/**
	 * 月度绩效指标保存
	 * @param performanceIndex 绩效指标对象
	 * @return 添加状态
	 */
	@RequestMapping("/save")
	public ModelAndView save(@RequestParam(value="id",required=false) Long id,PerformanceIndex performanceIndex,MonthPerformance monthPerformance){
		
		//如果没有新增任何指标，则提示用户
		if(performanceIndex.getItem() == null){
			return ajaxDoneError(NO_MONTH_INDEX);
		}
		
		List<MonthPerformance> monthList = performanceIndex.getItem();
		
		String performanceType = performanceIndex.getPerformanceType();
		for (MonthPerformance monthPerformance2 : monthList) {
			//前台会传递一些空的月度绩效指标设定记录，如果是空记录，跳过本次循环
			//空记录的当月目标值和当月累计目标值都为null,任意判断一个即可
			if(monthPerformance2.getMonTargetValue() == null || monthPerformance2.getMonTotalTargetValue() == null){
				continue;
			}
				
			//将待插入记录的PI_ID设置为指标表的主键
			monthPerformance2.setPiid(performanceIndex.getId());
			//检查和设置值
			if(performanceType.equals(PROGRAME_TYPE)){
				if(!onAddMonthIndexA(monthPerformance2,CREATE)){
					return ajaxDoneError(checkColumnResult);
				}
			}else{
				if(!onAddMonthIndex(monthPerformance2,CREATE)){
					return ajaxDoneError(checkColumnResult);
				}
			}
		}	
		
		try {		
			monthPerformanceService.setBaseMapper();
			
			//循环插入数据库(插入指定的字段)
			for (MonthPerformance monthPerformance2 : monthList) {
				monthPerformanceService.insertSelective(monthPerformance2);
			}		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if(StringUtils.isNotBlank(e.getMessage()) && e.getMessage().contains("ORA-00001")){
				return ajaxDoneError(MONTH_INDEX_EXIST);
			}
			e.printStackTrace();
			return ajaxDoneError(INSERT_ERROR_INFO);
		}
		return ajaxDoneSuccess(INSERT_SUCCESS_INFO);
	}
	
	/**
	 * 跳转到月度绩效指标设定修改页面
	 * @param id 待修改记录id
	 * @param model 回传待修改数据
	 * @return 修改页面视图
	 */
	@RequestMapping("/edit")
	public String edit(@RequestParam(value="id",required=false)Long id,Model model,PerformanceIndex performanceIndex){
		performanceIndex=performanceIndexCrudService.getOneMonthPerformanceById(id);
		model.addAttribute("vo",performanceIndex);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/performance/monthPerformance/edit";
	}
	
	/**
	 * 修改月度绩效指标设定
	 * @param performanceIndex 绩效指标类对象
	 * @return 修改是否成功的状态
	 */
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam(value="updateReason",required=false)String updateReason,
			@RequestParam(value="toUpdate",required=false)String toUpdate,
			@RequestParam(value="id",required=false)Long id,
			PerformanceIndex performanceIndex,
			MonthPerformance monthPerformance){
		if(toUpdate == null){
			return ajaxDoneError(NO_INDEX_TO_UPDATE);
		}
		
		Long[] piids = changeStringToArray(toUpdate);
		int number = piids.length;

		List<MonthPerformance> monthList = performanceIndex.getItem();
				
		String perfromanceType = performanceIndex.getPerformanceType();
		//检查和设置值，此时只校验数据的完整性，不涉及数据库的改动
		for(int i = 0; i < number; i++){//循环次数与选中条数相等
			for (MonthPerformance monthPerformance2 : monthList) {	
				if(monthPerformance2.getId().equals(piids[i])){	//只检查选中的记录相关值是否符合要求	
					if(perfromanceType.equals(PROGRAME_TYPE)){
						if(!onAddMonthIndexA(monthPerformance2,UPDATE)){//如果某一条记录值验证错误，直接返回错误提示
							return ajaxDoneError(checkColumnResult);
						}
					}else{						
						if(!onAddMonthIndex(monthPerformance2,UPDATE)){//如果某一条记录值验证错误，直接返回错误提示
							return ajaxDoneError(checkColumnResult);
						}
					}
				}
			}
		}

		//修改记录，并写入日志
		for(int i = 0; i < number; i++){
			for (MonthPerformance monthPerformance2 : monthList) {
				if(monthPerformance2.getId().equals(piids[i])){
					try {
						//修改前月度指标记录
						MonthPerformance mpf = monthPerformanceService.selectByPrimaryKey(monthPerformance2.getId());
						
						//对比修改前后信息
						EditReason editReason = onUpdateCheck(performanceIndex,monthPerformance2,mpf);
										
						//修改数据库记录
						monthPerformanceService.updateByPrimaryKeySelective(monthPerformance2);
						
						//插入修改记录，如果想获取插入记录的主键，使用editReason.getId()可以获取
						editReasonService.insertSelective(editReason);

					} catch (Exception e) {
						return ajaxDoneError(UPDATE_ERROR_INFO);
					}
				}
			}
		}
		return ajaxDoneSuccess(UPDATE_SUCCESS_INFO);
	}
	
	/**
	 * 月度指标设定修改时调用方法
	 * @param performanceIndex
	 * @param monthPerformance2
	 * @param mpf
	 * @return
	 */
	public EditReason onUpdateCheck(PerformanceIndex performanceIndex, MonthPerformance monthPerformance2,
			MonthPerformance mpf) {
		stringBuffer.setLength(0);
		
		//String oldCheckMethod = mpf.getCheckMethod();
		String oldMonTargetValue = mpf.getMonTargetValue();
		String oldMonTotalTargetValue = mpf.getMonTotalTargetValue();
		String oldMonChallengeTargetValue = mpf.getMonChallengeTargetValue();
		
		//String newCheckMethod = monthPerformance2.getCheckMethod();
		String newMonTargetValue = monthPerformance2.getMonTargetValue();
		String newMonTotalTargetValue = monthPerformance2.getMonTotalTargetValue();
		String newMonChallengeTargetValue = monthPerformance2.getMonChallengeTargetValue();
		
		compareChangeInfo(oldMonTargetValue,newMonTargetValue,MONTARGETVALUE);
		compareChangeInfo(oldMonTotalTargetValue,newMonTotalTargetValue,MONTOTALTARGETVALUE);
		compareChangeInfo(oldMonChallengeTargetValue,newMonChallengeTargetValue,MONCHALLENGETARGETVALUE);
		
		/*if(!oldCheckMethod.equals(newCheckMethod)){
			updateContentString.append("考核方法：").append(oldCheckMethod).append("->").append(newCheckMethod).append(";");
		}*/
/*		if(!oldMonTargetValue.equals(newMonTargetValue)){
			stringBuffer.append("当月目标值：").append(oldMonTargetValue).append("->").append(newMonTargetValue).append(";");
		}
		if(!oldMonTotalTargetValue.equals(newMonTotalTargetValue)){
			stringBuffer.append("当月累计值：").append(oldMonTotalTargetValue).append("->").append(newMonTotalTargetValue).append(";");
		}
		if(!oldMonChallengeTargetValue.equals(newMonChallengeTargetValue)){
			stringBuffer.append("当月挑战目标值：").append(oldMonChallengeTargetValue).append("->").append(newMonChallengeTargetValue).append(";");
		}*/
		
		EditReason editReason = new EditReason();
		editReason.setUpdateTime(new Date());
		editReason.setUpdateUser(getCurrentUserName());
		editReason.setUpdateReason(performanceIndex.getUpdateReason());
		editReason.setUpdateType(UPDATESTRING);
		editReason.setOperatorModule(MONTH_INDEX_SET);
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
	 * 跳转到月度绩效指标设定删除页面
	 * @param id 待删除记录id
	 * @param model 回传待修改数据
	 * @return 删除页面视图
	 */
	@RequestMapping("/toDelete")
	public String toDelete(@RequestParam(value="id",required=false)Long id,Model model,PerformanceIndex performanceIndex){
		performanceIndex=performanceIndexCrudService.getOneMonthPerformanceById(id);
		model.addAttribute("vo",performanceIndex);
		model.addAttribute("factorys", commonService.getFactorysBySite(null));
		return "qms/bph/base/performance/monthPerformance/delete";
	}
	
	/**
	 * 根据指标ID删除月度绩效指标记录
	 * @param performanceCheck
	 * @return
	 */
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value="toDel",required=false)String toDel,
			@RequestParam(value="id",required=false)Long id,
			PerformanceIndex performanceIndex,
			MonthPerformance monthPerformance){
		if(toDel == null){
			return ajaxDoneSuccess(NO_INDEX_TO_DELETE);
		}
		
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
					//修改数据库记录
					monthPerformanceService.deleteByPrimaryKey(piids[i]);
					
					EditReason editReason = onDelete(performanceIndex,monthPerformance2);
					editReasonService.insertSelective(editReason);
				}
			}
		}
		
		return ajaxDoneSuccess(DELETE_SUCCESS_INFO);
	}
	
	public EditReason onDelete(PerformanceIndex performanceIndex, MonthPerformance mpf){
		StringBuffer updateContentString = new StringBuffer();
		
		String oldMyMonth = DateEditor.formatDate(mpf.getMyMonth(),"yyyy-MM");
		//String oldCheckMethod = mpf.getCheckMethod();
		String oldMonTargetValue = mpf.getMonTargetValue();
		String oldMonTotalTargetValue = mpf.getMonTotalTargetValue();
		String oldMonChallengeTargetValue = mpf.getMonChallengeTargetValue();
		
		updateContentString.append(MONTH_INDEX_SET+"信息【")
		.append(MYMONTH+"：").append(oldMyMonth).append(";")
		//.append("考核方法：").append(oldCheckMethod).append(";")
		.append(MONTARGETVALUE+"：").append(oldMonTargetValue).append(";")
		.append(MONTOTALTARGETVALUE+"：").append(oldMonTotalTargetValue).append(";")
		.append(MONCHALLENGETARGETVALUE+"：").append(oldMonChallengeTargetValue).append("】");
		
		EditReason editReason = new EditReason();
		editReason.setUpdateTime(new Date());
		editReason.setUpdateUser(getCurrentUserName());
		//editReason.setUpdateReason(performanceIndex.getUpdateReason());
		editReason.setUpdateReason("数据作废");//暂定为数据作废
		editReason.setUpdateType(DELETESTRING);
		editReason.setOperatorModule(MONTH_INDEX_SET);
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
	 * 检查月度绩效设定字段是否正确(当指标类型为“指标型”时)
	 * @param monthPerformance
	 * @return
	 */
	public boolean onAddMonthIndex(MonthPerformance monthPerformance,String operatorType){
		Date date = new Date();
		String monTargetValue = monthPerformance.getMonTargetValue().trim();//当月目标
		String monTotalTargetValue = monthPerformance.getMonTotalTargetValue().trim();//当月累计目标
		String monChallengeTargetValue = monthPerformance.getMonChallengeTargetValue().trim();//当月挑战目标
		
		if(ColumnDealUtil.checkColumnIsEmptyOrNull(monTargetValue)){
			checkColumnResult = MONTARGETVALUE+INPUTERROR;
			return false;
		}else if(!ColumnDealUtil.checkStringToNumber(monTargetValue)){
			checkColumnResult = MONTARGETVALUE+REQUIRED_NUMBER_TYPE;
			return false;
		}else{
			monthPerformance.setMonTargetValue(monTargetValue);
		}
		
		if(ColumnDealUtil.checkColumnIsEmptyOrNull(monTotalTargetValue)){
			checkColumnResult = MONTOTALTARGETVALUE+INPUTERROR;
			return false;
		}else if(!ColumnDealUtil.checkStringToNumber(monTotalTargetValue)){
			checkColumnResult = MONTOTALTARGETVALUE+REQUIRED_NUMBER_TYPE;
			return false;
		}else{
			monthPerformance.setMonTotalTargetValue(monTotalTargetValue);
		}
		
		if(ColumnDealUtil.checkColumnIsEmptyOrNull(monChallengeTargetValue)){
			monthPerformance.setMonChallengeTargetValue(monTotalTargetValue);
		}else if(!ColumnDealUtil.checkStringToNumber(monChallengeTargetValue)){
			checkColumnResult = MONCHALLENGETARGETVALUE+REQUIRED_NUMBER_TYPE;
			return false;
		}
		
		if(operatorType.equals("create")){
			monthPerformance.setCreateTime1(date);
			monthPerformance.setCreateUser1(getCurrentUserName());
		}
		
		return true;
	}
	
	/**
	 * 检查月度绩效设定字段是否正确(当指标类型为“项目型”时)
	 * @param monthPerformance
	 * @return
	 */
	public boolean onAddMonthIndexA(MonthPerformance monthPerformance,String operatorType){
		Date date = new Date();
		String monTargetValue = monthPerformance.getMonTargetValue().trim();//当月目标
		String monTotalTargetValue = monthPerformance.getMonTotalTargetValue().trim();//当月累计目标
		String monChallengeTargetValue = monthPerformance.getMonChallengeTargetValue().trim();//当月挑战目标
		
		if(ColumnDealUtil.checkColumnIsEmptyOrNull(monTargetValue)){
			checkColumnResult = MONTARGETVALUE+INPUTERROR;
			return false;
		}else{
			monthPerformance.setMonTargetValue(monTargetValue);
		}
		
		if(ColumnDealUtil.checkColumnIsEmptyOrNull(monTotalTargetValue)){
			checkColumnResult = MONTOTALTARGETVALUE+INPUTERROR;
			return false;
		}else{
			monthPerformance.setMonTotalTargetValue(monTotalTargetValue);
		}
		
		if(ColumnDealUtil.checkColumnIsEmptyOrNull(monChallengeTargetValue)){
			monthPerformance.setMonChallengeTargetValue(monTotalTargetValue);
		}
		
		if(operatorType.equals("create")){
			monthPerformance.setCreateTime1(date);
			monthPerformance.setCreateUser1(getCurrentUserName());
		}
		
		return true;
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
}
