package com.peg.service.impl.performance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.performance.Department;
import com.peg.model.performance.MonthPerformance;
import com.peg.model.performance.PerformanceIndex;
import com.peg.model.performance.YearPerformance;
import com.peg.service.performance.DepartmentService;
import com.peg.service.performance.IndexImportService;
import com.peg.service.performance.MonthPerformanceService;
import com.peg.service.performance.PerformanceCommon;
import com.peg.service.performance.PerformanceIndexCrudService;
import com.peg.service.performance.YearPerformanceService;
import com.peg.web.util.DateEditor;

/**
 * 绩效指标Excel导入实现类
 * @author xuanm
 *
 */
@Service("indexImportService")
public class IndexImportServiceImpl implements IndexImportService,PerformanceCommon {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 年 格式：2018
	 */
	private SimpleDateFormat shortDate = new  SimpleDateFormat("yyyy");
	
	/**
	 * 年-月 格式： 2018-01
	 */
	private SimpleDateFormat shortMonthDate = new SimpleDateFormat("yyyy-MM");
	
	/**
	 * 年-月-日 时:分:秒 格式：2018-01-01 00:00:00
	 */
	private SimpleDateFormat longDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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
	 * 查询部门基础信息接口
	 */
	@Autowired
	private DepartmentService departmentService;
	
	private String monthString = "00";
	
	@Override
	public String[] checkIndexImportData(List<String[]> dataList) {
		String result = "" ;
		StringBuffer buffer = null;
		int idx = 0;
		int count = 0 ;
		buffer = new StringBuffer();
		for (String[] arryString:dataList) {
			idx++;
			//用户导入固定格式
			//年度	工厂名称	部门名称	绩效目标大类	绩效类型	指标内容	指标类型	权重	单位	计算公式	上年度实际值	上半年基准值	本年度基准值	本年度目标值	上半年目标值	下半年目标值	月份	当月目标值	当月累计目标值	当月挑战目标值	当月实际值	累计目标值	状态	创建人	创建时间
			String month = arryString[0].trim() ;
			if (month == null || month.trim().isEmpty())
			{
				count++;
				if(count>=2){
					break;
				}
				continue;
			}
			try{
				logger.debug("正在解析Excel第"+(idx+1)+"行...");
			}catch(Exception e){
				result = "excel第"+idx+"行存在错误数据:"+e.getMessage();
				return new String[]{null,result};
			}

			for (int i = 0; i < arryString.length; i++) {
				if(arryString[i].equals("")){
					buffer.append(" ");
				}
				buffer.append(arryString[i]);
				if(i < arryString.length - 1){
					buffer.append("@");
				}
			}
			buffer.append("&");
			
		}	
		return new String[]{buffer.toString().substring(0,buffer.length()-1),result};
	}

	@Override
	public String setUploadData(String uploadData) {
		
		
		// TODO Auto-generated method stub
		logger.debug("设置上传绩效数据...");
		//logger.info(uploadData);
		
		int result = 0;
		int errorLineNumber = 1;//设置默认出错行数
		String[] arrStrings = uploadData.split("&");
		//打印获取的数据信息，数组的每一个元素代表一条记录
		/*for (String string : arrStrings) {
			logger.info(string.toString());
		}*/
		for (int i = 0; i < arrStrings.length; i++) {
			errorLineNumber++;
			String[] arrString = arrStrings[i].split("@");
			String checkYearString = arrString[0].trim();//年度
			String factoryName = arrString[1].trim();
			String departmentName = arrString[2].trim();
			String performanceTargetClass = arrString[3].trim();
			String performanceType = arrString[4].trim();
			String indexContent = arrString[5].trim();
			String indexType = arrString[6].trim();
			
			String weight = arrString[7].trim();
			String company = arrString[8].trim();
			String formula = arrString[9].trim();
			String referenceValue = arrString[10].trim();
			String middleValue = arrString[11].trim();
			String targetValue = arrString[12].trim();
			
			String lastYearRealityValue = arrString[13].trim();
			String firstYearReferenceValue = arrString[14].trim();
			String yearReferenceValue = arrString[15].trim();
			String yearTargetValue = arrString[16].trim();
			String firstYearTargetValue = arrString[17].trim();
			String secondYearTargetValue = arrString[18].trim();
			
			String myMonthString = arrString[19].trim();
			String monTargetValue = arrString[20].trim();//当月目标
			String monTotalTargetValue = arrString[21].trim();//当月累计目标
			String monChallengeTargetValue = arrString[22].trim();//当月挑战目标
			String monRealityTargetValue = arrString[23].trim();//当月实际目标值
			String monRealityTotalTargetValue = arrString[24].trim();//当月实际累计目标值
			String checkResult = arrString[25].trim();//考核结果
			String checkStatus = arrString[26].trim();//考核状态
			
			String createUser = arrString[27].trim();
			String createTimeString = arrString[28].trim();
			//System.out.println(checkYearString +"-"+ factoryName +"-" + departmentName);
			//System.out.println(i + "myMonthString:"+ myMonthString);
			
			Date checkYear = new Date();
			
			try {
				//logger.debug(checkYearString);
				checkYear = shortDate.parse(checkYearString);
			} catch (ParseException e) {
				logger.debug(e.getMessage());				
				return result+"第"+errorLineNumber+"行数据不符合规范";
			}			 
			//logger.debug("转换后的数据："+ i + ":" + checkYear);		
			
			Date myMonth = new Date();
			try {
				myMonth = shortMonthDate.parse(myMonthString);
			} catch (Exception e) {
				logger.debug(e.getMessage());				
				return result+"第"+errorLineNumber+"行数据不符合规范";
			}
			
			Date createTime = new Date();
			try {
				createTime = longDate.parse(createTimeString+" 00:00:00");
			} catch (Exception e) {
				logger.debug(e.getMessage());				
				return result+"第"+errorLineNumber+"行数据不符合规范";
			}
			
			PerformanceIndex performanceIndex = new PerformanceIndex();
			//以下7项内容为联合主键
			performanceIndex.setCheckYear(checkYear);
			performanceIndex.setFactoryName(factoryName);
			performanceIndex.setDepartmentName(departmentName);
			performanceIndex.setPerformanceTargetClass(performanceTargetClass);
			performanceIndex.setIndexContent(indexContent);
			performanceIndex.setPerformanceType(performanceType);
			performanceIndex.setIndexType(indexType);
			
			performanceIndex.setWeight(weight);
			performanceIndex.setCompany(company);
			performanceIndex.setFormula(formula);
			performanceIndex.setReferenceValue(referenceValue);
			performanceIndex.setMiddleValue(middleValue);
			performanceIndex.setTargetValue(targetValue);
			
			
			performanceIndex.setUpdateUser(createUser);
			performanceIndex.setUpdateTime(createTime);
			
			performanceIndex.setYear(checkYearString);
			
			//设置年度指标值
			YearPerformance yearPerformance = new YearPerformance();
			yearPerformance.setLastYearRealityValue(lastYearRealityValue);
			yearPerformance.setFirstYearReferenceValue(firstYearReferenceValue);
			yearPerformance.setReferenceValue(yearReferenceValue);
			yearPerformance.setTargetValue(yearTargetValue);
			yearPerformance.setFirstYearTargetValue(firstYearTargetValue);
			yearPerformance.setSecondYearTargetValue(secondYearTargetValue);
			
			yearPerformance.setUpdateUser(createUser);
			yearPerformance.setUpdateTime(createTime);
			
			//将年度指标值对象作为指标对象的域
			performanceIndex.setYearPerformance(yearPerformance);
			
			
			//设置月度指标值
			MonthPerformance monthPerformance = new  MonthPerformance();
			monthPerformance.setMyMonth(myMonth);
			monthPerformance.setMonTargetValue(monTargetValue);
			monthPerformance.setMonTotalTargetValue(monTotalTargetValue);
			monthPerformance.setMonChallengeTargetValue(monChallengeTargetValue);
			
			if(!monRealityTargetValue.equals("")&&!monRealityTotalTargetValue.equals("")){
				monthPerformance.setMonRealityTargetValue(monRealityTargetValue);
				monthPerformance.setMonRealityTotalTargetValue(monRealityTotalTargetValue);
				monthPerformance.setColumn1(checkStatus);
			}
			
			
			
			if(!setFactoryAndDepartmentInfo(performanceIndex)){
				return "第"+errorLineNumber+"行查询工厂部门信息异常,请检查是否有对应的工厂部门";
			}else{
				List<PerformanceIndex> indexList = getPerformanceIndexListByCondition(performanceIndex);
				//当记录不存在时，插入新的指标记录
				if(indexList.size() == 0){
					performanceIndex.setCreateUser(createUser);
					performanceIndex.setCreateTime(createTime);
					
					yearPerformance.setCreateUser(createUser);
					yearPerformance.setCreateTime(createTime);
					
					monthPerformance.setCreateUser1(createUser);
					monthPerformance.setCreateTime1(createTime);
					monthPerformance.setCreateUser2(createUser);
					monthPerformance.setCreateTime2(createTime);
					//1.插入绩效指标
					performanceIndexCrudService.insertSelective(performanceIndex);
					long performanceIndexID = performanceIndex.getId();
					logger.debug("新插入绩效指标记录的主键为："+ performanceIndexID);
					
					yearPerformance.setPiid(performanceIndexID);
					
					//2.插入年度绩效指标
					yearPerformanceService.insertSelective(yearPerformance);
					
					monthPerformance.setPiid(performanceIndexID);
					//将月度指标值对象作为指标对象的域
					List<MonthPerformance> monthList = new ArrayList<MonthPerformance>();
					monthList.add(monthPerformance);
					performanceIndex.setMonthList(monthList);
					
					//设置月度序列
					setMonthIndex(performanceIndex);
					//设置月度检查结果
					setMonthPerformanceCheckResult(performanceIndex);
					//3.插入月度绩效指标
					monthPerformanceService.insertSelective(monthPerformance);
					
				}else if(indexList.size() >= 1){
					//当记录已经存在时，依次更新年度指标和月度指标，而绩效指标不需要更新
					logger.debug("正在处理Excel第"+errorLineNumber+"行数据");
					logger.debug("第"+errorLineNumber+"行查询到的记录长度为"+indexList.size());
					//将月度指标值对象作为指标对象的域
					/*List<MonthPerformance> monthList = new ArrayList<MonthPerformance>();
					monthPerformance.setCreateUser1(createUser);
					monthPerformance.setCreateTime1(createTime);
					monthList.add(monthPerformance);
					performanceIndex.setMonthList(monthList);*/
					
					for (PerformanceIndex performanceIndex2 : indexList) {
						if(performanceIndex2.getYearPerformance() == null){//当指标存在，而年度指标为空时，插入年度指标记录和月度指标记录
							logger.debug("年度绩效指标值为空，准备插入年度绩效指标值...");
							yearPerformance.setCreateUser(createUser);
							yearPerformance.setCreateTime(createTime);
							yearPerformance.setPiid(performanceIndex2.getId());
							yearPerformanceService.insertSelective(yearPerformance);
							logger.debug("第"+errorLineNumber+"行插入年度指标值成功！");
							//重新获取指标信息
							performanceIndex = getPerformanceIndexListByCondition(performanceIndex).get(0);
							performanceIndex.setYearPerformance(yearPerformance);
							List<MonthPerformance> monthList = new ArrayList<MonthPerformance>();
							monthPerformance.setCreateUser1(createUser);
							monthPerformance.setCreateTime1(createTime);
							monthList.add(monthPerformance);
							performanceIndex.setMonthList(monthList);
							
							//插入新的月度绩效指标值
							monthPerformance.setPiid(performanceIndex2.getId());
							//设置月度序列
							setMonthIndex(performanceIndex);
							
							if(monthPerformance.getMonRealityTargetValue() != null&&monthPerformance.getMonRealityTotalTargetValue() != null){
								setMonthPerformanceCheckResult(performanceIndex);
								monthPerformanceService.insertSelective(monthPerformance);
							}else{
								monthPerformance.setMonRealityTargetValue("");
								monthPerformance.setMonRealityTotalTargetValue("");
								monthPerformanceService.insertSelective(monthPerformance);
							}
							monthPerformanceService.insertSelective(monthPerformance);
						}else{//当年度指标值不为空，且导入的月份不存在时
							logger.debug("第"+errorLineNumber+"行年度指标值不为空，开始处理月度指标值");
							if(performanceIndex2.getMonthList().size() == 0){//当月度绩效指标值为空时，直接插入一条新的记录
								logger.debug("第"+errorLineNumber+"行月度绩效指标值为空");
								monthPerformance.setPiid(performanceIndex2.getId());
								List<MonthPerformance> monthList = new ArrayList<MonthPerformance>();
								monthPerformance.setCreateUser1(createUser);
								monthPerformance.setCreateTime1(createTime);
								monthList.add(monthPerformance);
								performanceIndex.setMonthList(monthList);
								//设置月度序列
								setMonthIndex(performanceIndex);
								//设置月度检查结果（当月实际值和累计实际值不为空时，才设置考核状态和考核结果）
								if(monthPerformance.getMonRealityTargetValue() != null&&monthPerformance.getMonRealityTotalTargetValue() != null){
									setMonthPerformanceCheckResult(performanceIndex);
								}
								//3.插入月度绩效指标
								if(monthPerformance.getMonRealityTargetValue() != null&&monthPerformance.getMonRealityTotalTargetValue() != null){
									setMonthPerformanceCheckResult(performanceIndex);
									monthPerformanceService.insertSelective(monthPerformance);
								}else{
									monthPerformance.setMonRealityTargetValue("");
									monthPerformance.setMonRealityTotalTargetValue("");
									monthPerformanceService.insertSelective(monthPerformance);
								}
							}else{//当年度指标值不为空，且导入的月份存在时
								logger.debug("第"+errorLineNumber+"月度绩效指标值不为空");
								logger.debug("数据库中已经存在的月度绩效列表长度为："+performanceIndex2.getMonthList().size());
								List<MonthPerformance> monthList = new ArrayList<MonthPerformance>();
								monthList.add(monthPerformance);
								performanceIndex.setMonthList(monthList);
								
								setMonthIndex(performanceIndex);
								setMonthIndex(performanceIndex2);
								boolean monthExist = false;//标志变量，记录待插入的月份信息是否存在
								String targetMonthString = monthPerformance.getMonthIndex();
								long id = 0L;
								Long piid = 0L;
								List<MonthPerformance> monthList2 = performanceIndex2.getMonthList();
								for (MonthPerformance monthPerformance2 : monthList2) {
									if(monthPerformance2.getMonthIndex().equals(targetMonthString)){
										monthExist = true;//如果月份序列相等，说明待插入月度指标存在
										id = monthPerformance2.getId();
										piid = monthPerformance2.getPiid();
										break;
									}
								}
								if(monthExist){//修改对应的月度指标
									monthPerformance.setId(id);
									monthPerformance.setPiid(piid);
									//设置月度检查结果（当月实际值和累计实际值不为空时，才设置考核状态和考核结果）
									if(monthPerformance.getMonRealityTargetValue() != null&&monthPerformance.getMonRealityTotalTargetValue() != null){
										setMonthPerformanceCheckResult(performanceIndex);
										monthPerformanceService.updateByPrimaryKeySelective(monthPerformance);
									}else{
										monthPerformance.setMonRealityTargetValue("");
										monthPerformance.setMonRealityTotalTargetValue("");
										monthPerformanceService.updateByPrimaryKeySelective(monthPerformance);
									}
								}else{
									monthPerformance.setCreateUser1(createUser);
									monthPerformance.setCreateTime1(createTime);
									monthPerformance.setPiid(performanceIndex2.getId());
									if(monthPerformance.getMonRealityTargetValue() != null&&monthPerformance.getMonRealityTotalTargetValue() != null){
										setMonthPerformanceCheckResult(performanceIndex);
										monthPerformance.setCreateUser2(createUser);
										monthPerformance.setCreateTime2(createTime);
										monthPerformanceService.insertSelective(monthPerformance);
									}else{
										monthPerformance.setMonRealityTargetValue("");
										monthPerformance.setMonRealityTotalTargetValue("");
										monthPerformance.setCreateUser2(createUser);
										monthPerformance.setCreateTime2(createTime);
										monthPerformanceService.insertSelective(monthPerformance);
									}
								}
							}
						}//end outer if			
					}//end for
					//return "第"+errorLineNumber+"行数据已经存在，不允许导入！";
				}else{
					return "第"+errorLineNumber+"查询到的记录数大于两条，请检查数据库记录！";
				}						
			}
			
			
		}
		return result+"";
	}
	
	/**
	 * 根据工厂名称和部门名称，设置工厂编号和部门编号
	 * @param performanceIndex 指标对象
	 * @return true:查询到工厂和部门信息,并且设置工厂部门信息 <br>false:查询工厂部门信息异常
	 */
	public boolean setFactoryAndDepartmentInfo(PerformanceIndex performanceIndex){

		List<Department> departmentList = new ArrayList<Department>();
		BaseSearch departmentBS = new BaseSearch();
		departmentBS.put("factoryName", performanceIndex.getFactoryName());
		departmentBS.put("departmentName", performanceIndex.getDepartmentName());
		departmentList = departmentService.getFactoryAndDepartmentByName(departmentBS);
		if(departmentList.size() >= 1){
			performanceIndex.setFactoryNumber(departmentList.get(0).getFactoryNumber());
			performanceIndex.setDepartmentNumber(departmentList.get(0).getDepartmentNumber());
			return true;
		}
		return false;
		
	}
	
	/**
	 * 根据年度、工厂名称、部门名称、绩效目标大类、绩效类型、指标内容和指标类型查询查询指标信息
	 * @param performanceIndex 指标对象
	 * @return
	 */
	public List<PerformanceIndex> getPerformanceIndexListByCondition(PerformanceIndex performanceIndex){
		BaseSearch bs=new BaseSearch();
		bs.put("checkYear", performanceIndex.getYear());//这里使用字符串year替代checkYear当做年度的查询条件
	    bs.put("factoryName", performanceIndex.getFactoryName());
	    bs.put("departmentName", performanceIndex.getDepartmentName());
		bs.put("performanceTargetClass", performanceIndex.getPerformanceTargetClass());
		bs.put("performanceType", performanceIndex.getPerformanceType());
		bs.put("indexContent", performanceIndex.getIndexContent());
		bs.put("indexType", performanceIndex.getIndexType());
		return performanceIndexCrudService.getOnePerformanceByObject(bs);
	}
	
	/**
	 * 设置指标检查结果
	 * @param performanceIndex
	 */
	public void setMonthPerformanceCheckResult(PerformanceIndex performanceIndex){
		
		//当绩效类型是“指标型”时
		if(performanceIndex.getPerformanceType().equals(INDEX_TYEP)){
			//此时年度基准值和上年度基准值不为字符串
			YearPerformance yearPerformance = performanceIndex.getYearPerformance();
			String referenceValue = yearPerformance.getReferenceValue();
			String firstYearReferenceValue = yearPerformance.getFirstYearReferenceValue();
			double referenceValueToDouble = Double.parseDouble(referenceValue);	
			double firstYearReferenceValueToDouble = Double.parseDouble(firstYearReferenceValue);
			
			List<MonthPerformance> monthList = performanceIndex.getMonthList();
			
			for (MonthPerformance monthPerformance2 : monthList) {
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
					}else if(monRealityTotalTargetValueToDouble >= referenceValueToDouble && monRealityTotalTargetValueToDouble < monChallengeTargetValueToDouble){
						//(2)本年度基准值=<当月累计实际值<当月挑战目标值（前6个月使用上半年基准值），显示成：基准与目标之间，黄色
						monthPerformance2.setCheckResult(INDEX_MIDDLEVALUE);
					}else if(monChallengeTargetValueToDouble <= monRealityTotalTargetValueToDouble){
						//(3)当月挑战目标值=<当月累计实际值时，显示成：已达标，绿色
						monthPerformance2.setCheckResult(IS_TRAGET);
					}else{
						monthPerformance2.setCheckResult("无法确定");
					}
				}
				else if(indexType.equals(LESS_THAN_TARGET_TYPE)){//2.当指标类型为“望小型”时
					/**
					 * BUG #6626 
					 * 当指标类型为“望小型”时，优先用【累计实际值】和【挑战目标值】比较，若比较结果为“已达标”，则不用再跟 【基准值】 比较。
					 */
					if(monRealityTotalTargetValueToDouble < monChallengeTargetValueToDouble){
						monthPerformance2.setCheckResult(IS_TRAGET);
					}else{
						if(monRealityTotalTargetValueToDouble > referenceValueToDouble){
							//(1)当月累计实际值>本年度基准值，显示成：未达标，红色
							monthPerformance2.setCheckResult(NOT_TARGET);
						}else if(monChallengeTargetValueToDouble < monRealityTotalTargetValueToDouble && monRealityTotalTargetValueToDouble <= referenceValueToDouble){
							//(2)当月挑战目标值<当月累计实际值<=本年度基准值时，显示成：基准与目标之间，黄色
							monthPerformance2.setCheckResult(INDEX_MIDDLEVALUE);
						}else if(monRealityTotalTargetValueToDouble <= monChallengeTargetValueToDouble){
							//(3)当月累计实际值时<=当月挑战目标值 时，显示成：已达标，绿色
							monthPerformance2.setCheckResult(IS_TRAGET);
						}else{
							monthPerformance2.setCheckResult("无法确定");
						}
					}
					
				}
				else if(indexType.equals(EQUAL_TARGET_TYPE)){//3.当指标类型为“望目型”时
					if(monRealityTotalTargetValueToDouble == monChallengeTargetValueToDouble){
						//(1)当月累计实际值＝当月挑战目标值，显示成：已达标
						monthPerformance2.setCheckResult(IS_TRAGET);
					}else if(monRealityTotalTargetValueToDouble != monChallengeTargetValueToDouble){
						//(2)当月累计实际值!=当月挑战目标值，显示成：未达标
						monthPerformance2.setCheckResult(NOT_TARGET);
					}else{
						monthPerformance2.setCheckResult("无法确定");
					}
				}
			}//end for
			
		}else if(performanceIndex.getPerformanceType().equals(PROGRAME_TYPE)){//当绩效类型为“项目型”时
			List<MonthPerformance> monthList = performanceIndex.getMonthList();
			for (MonthPerformance monthPerformance2 : monthList) {
				//当月份为6月和12月时，评判标准为A、B+、B、C
				if(monthPerformance2.getMonthIndex().equals("6") || monthPerformance2.getMonthIndex().equals("12") ){
					if(monthPerformance2.getColumn1().equals("A") || monthPerformance2.getColumn1().equals("B+")){
						monthPerformance2.setCheckResult(IS_TRAGET);
					}else if(monthPerformance2.getColumn1().equals("B") || monthPerformance2.getColumn1().equals("C")){
						monthPerformance2.setCheckResult(NOT_TARGET);
					}else{
						monthPerformance2.setCheckResult("无法确定");
					}
				}else{
				//当月份不为6月和12月时，评判标准为 正常进行，延期，终止
					if(monthPerformance2.getColumn1().equals(INDEX_NORMAL)){
						monthPerformance2.setCheckResult(IS_TRAGET);
					}else if(monthPerformance2.getColumn1().equals(INDEX_DELAY) || monthPerformance2.getColumn1().equals(INDEX_END)){
						monthPerformance2.setCheckResult(NOT_TARGET);
					}else{
						monthPerformance2.setCheckResult("无法确定");
					}
				}
			}//end for
		}//end if		
	}//end method
	
	/**
	 * 设置月份序列信息
	 * @param performanceIndx
	 */
	public void setMonthIndex(PerformanceIndex performanceIndex){
		//设置月份信息
		List<MonthPerformance> monthList = performanceIndex.getMonthList();
		for (MonthPerformance monthPerformance : monthList) {
			//取月度指标的时间，截取月份信息
			monthString = DateEditor.formatDate(monthPerformance.getMyMonth(), "MM");
			//如果月份转换异常，前台显示时需要处理（若是查询数据库没有异常，该转换一般不会出现异常）
			monthPerformance.setMonthIndex(dealMonthValue(monthString));
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
		}
		return monthString;
	}
}
