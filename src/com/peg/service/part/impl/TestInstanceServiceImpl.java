package com.peg.service.part.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.peg.beans.part.TestInstanceUtil;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.dao.part.TestInstanceMapper;
import com.peg.echarts.EChartObj;
import com.peg.echarts.EChartsType;
import com.peg.model.part.TestInstance;
import com.peg.service.part.TestInstanceServiceI;
import com.peg.web.util.DateEditor;
import com.peg.web.util.TmStringUtils;

@Service("testInstanceService")
public class TestInstanceServiceImpl implements TestInstanceServiceI{

	@Autowired
	private TestInstanceMapper testInstanceMapper;
	
   private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
   private static final DateFormat MONTHFORMAT = new SimpleDateFormat("yyyy-MM");
   private static final DateFormat YEARFORMAT = new SimpleDateFormat("yyyy");
	/**
	 * 获取机型类别
	 */
	@Override
	public List<TestInstance> getProductType() {
		return testInstanceMapper.getProductType();
	}
	@Override
	public List<TestInstance> getSupplier() {
		return testInstanceMapper.getSupplier();
	}
	@Override
	public List<TestInstance> getPartType() {
		return testInstanceMapper.getPartType();
	}
	@Override
	public List<TestInstance> getPartAllByPage(BaseSearch bs) {
		return testInstanceMapper.getPartAllByPage(bs);
	}
	@Override
	public List<TestInstance> getTestInstance(String sql) {
		return testInstanceMapper.getTestInstance(sql);
	}
	@Override
	public TestInstance getWeek(String date) {
		return testInstanceMapper.getWeek(date);
	}
	
//	private DateEditor dateEditor;
	/**
	 * 获取机型类别
	 */
	@Override
	public Map<String, List<TestInstance>> getMesProductType() {
		Map<String,List<TestInstance>> map = new LinkedHashMap<String, List<TestInstance>>();
		List<TestInstance> list = testInstanceMapper.getMesProductType();
		for(TestInstance in : list){
			if(!map.containsKey(in.getFactory())){
				map.put(in.getFactory(), new ArrayList<TestInstance>());
			}
			map.get(in.getFactory()).add(in);
		}
		return map;
	}
	/**
	 * 获取开始结束时间
	 */
	@Override
	public void getDate(TestInstance test, BaseSearch bs) {
		try{
			String dateType = test.getDateType();
			String startTime = test.getStartTime();
			String endTime = test.getEndTime();
			String date = decode(test.getDateT());
			String dayTime = decode(test.getDayTime());
			Calendar cal = Calendar.getInstance();
			String startDate ="";
			String endDate = "";
			if(date !=null && !"".equals(date)){
				if("天".equals(dateType)){
					startDate = date;
					endDate = date;
				}else if("周".equals(dateType)){
					cal.setTime(DATEFORMAT.parse(date));
		    		cal.add(Calendar.DAY_OF_MONTH, -6);
					startDate = DATEFORMAT.format(cal.getTime());
					endDate = date;
				}else if("月".equals(dateType)){
					if(TmStringUtils.isNotEmpty(dayTime) && "上".equals(dayTime)){
						cal.setTime(MONTHFORMAT.parse(date));
						cal.add(Calendar.MONTH, -1);
						startDate = DATEFORMAT.format(cal.getTime());
						cal.add(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)-1);
						endDate = DATEFORMAT.format(cal.getTime());
					}else{
						cal.setTime(MONTHFORMAT.parse(date));
						cal.add(Calendar.DAY_OF_MONTH, 0);
						startDate = DATEFORMAT.format(cal.getTime());
						cal.setTime(MONTHFORMAT.parse(date));
						cal.add(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)-1);
						endDate = DATEFORMAT.format(cal.getTime());
					}
				}else if("年".equals(dateType)){
					cal.setTime(YEARFORMAT.parse(date));
					cal.add(Calendar.DAY_OF_MONTH, 0);
					startDate = DATEFORMAT.format(cal.getTime());
					cal.setTime(YEARFORMAT.parse(date));
					cal.add(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR)-1);
					endDate = DATEFORMAT.format(cal.getTime());
				}
				test.setStartTime(startDate);
				test.setEndTime(endDate);
			}else{
				if(startTime==null || "".equals(startTime)){
					startTime = DateEditor.formatDate(new Date(), "yyyy-MM-dd");
					endTime = DateEditor.formatDate(new Date(), "yyyy-MM-dd");
					test.setStartTime(startTime);
					test.setEndTime(endTime);
				}
				startDate = startTime;
				endDate = endTime;
			}
			
			bs.put("startDate", startDate);
			bs.put("endDate", endDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	 
	/**
	 * 分页查询
	 */
	@Override
	public List<TestInstance> findAllByPage(BaseSearch bs) {
		
		return testInstanceMapper.findAllByPage(bs);
	}
	/**
	 * 转码
	 */
	@Override
	public TestInstance decode(TestInstance testInstance) {
		testInstance.setDateType(decode(testInstance.getDateType()));
		testInstance.setFactory(decode(testInstance.getFactory()));
		testInstance.setPartName(decode(testInstance.getPartName()));
		testInstance.setPartType(decode(testInstance.getPartType()));
		testInstance.setProductType(decode(testInstance.getProductType()));
		testInstance.setResultS(decode(testInstance.getResultS()));
		testInstance.setSupplier(decode(testInstance.getSupplier()));
		testInstance.setWareHouse(decode(testInstance.getWareHouse()));
		testInstance.setDefectType(decode(testInstance.getDefectType()));
		testInstance.setPartClass(decode(testInstance.getPartClass()));
		testInstance.setPartVersion(decode(testInstance.getPartVersion()));
		testInstance.setDayTime(decode(testInstance.getDayTime()));
		if(TmStringUtils.isNotEmpty(testInstance.getSupplierCode()) && TmStringUtils.isEmpty(testInstance.getSupplierData())){
			BaseSearch bs = new BaseSearch();
			bs.setPage(new PageParameter());
			bs.put("supplierList", TmStringUtils.getSupStringByComma(testInstance.getSupplierCode()));
			List<TestInstance> supList = testInstanceMapper.getSupplierByPage(bs);
			String supData = "";
			String supStr = "";
			for(TestInstance test: supList){
				supData += test.getId()+","+test.getSupplierCode()+","+test.getSupplier()+";";
				supStr += test.getSupplier() + ",";
			}
			testInstance.setSupplierData(supData);
			testInstance.setSupplier(supStr);
		}
		if(TmStringUtils.isNotEmpty(testInstance.getPartNumber()) && TmStringUtils.isEmpty(testInstance.getPartData())){
			BaseSearch bs = new BaseSearch();
			bs.setPage(new PageParameter());
			if(testInstance.getType() != 2 && testInstance.getType() != 7  && testInstance.getDistinction() != null && testInstance.getDistinction() ==1){
				bs.put("distinction",null);
			}else{
				bs.put("distinction","是".equals(testInstance.getPartVersion()) ? null : "1" );
			}
			bs.put("partList", TmStringUtils.getSupStringByComma(testInstance.getPartNumber()));
			List<TestInstance> partList = testInstanceMapper.getPartAllByPage(bs);
			String partData = "";
			String partStr = "";
			String partNumberStr = "";
			for(TestInstance test: partList){
				partData += test.getId()+","+test.getPartNumber()+","+test.getPartName()+";";
				partStr += test.getPartName() + ",";
				partNumberStr += test.getPartNumber() +",";
			}
			testInstance.setPartData(partData);
			testInstance.setPartName(partStr);
			testInstance.setPartNumber(partNumberStr);
		}
		return testInstance;
	}
	
	protected String decode(String value) {
		try{
			if (StringUtils.isNotBlank(value)) {
				if(value.equals(new String(value.getBytes("iso8859-1"), "iso8859-1")))
				{
					value = new String(value.getBytes("ISO-8859-1"),"utf-8");
				}			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return value;
	}
	/**
	 * erp原料退料
	 */
	@Override
	public EChartObj getErpPartReturn(TestInstance testInstance) throws Exception{
		int columNum = testInstance.getColumnNum();
		List<TestInstance> list = testInstanceMapper.getErpPartReturn(testInstance);
//		String option = TestInstanceUtil.getData(testInstance,list);
		EChartObj chart = new EChartObj();
		chart.setBarWidth("30");
		chart.setChartType(EChartsType.CHART_BAR);                      //图形类型
		chart.setTitle(testInstance.getDateT()+testInstance.getDateType()+"ERP退料批次排列图");       //设置标题
		chart.setToolboxShow(true);                                       //开启工具配置项
		chart.setToolboxFeatureSaveAsImageShow(true);                     //开启图片
		chart.setToolboxFeatureMagicTypeShow(true);                       //开启动态转换
		chart.setToolboxFeatureMagicTypeType(new String[]{"bar","line"}); //配置动态类型切换
		chart.setToolboxFeatureDataViewShow(true);                        //配置toolBox数据不可编辑
		chart.setyAxisAxisLabelFormatter("{value}批");                    //配置坐标单位  
		chart.setSeriesType(new String[]{"bar"} );                //设置系列类型
		chart.setyLeftUnit("批");                                        //左单位
		String[] seriesNames ={"不良批次数"};
		List<String> xValue = new ArrayList<String>();
		List<Double> valueList = new ArrayList<Double>();
		if(columNum > list.size()){
			columNum = list.size();
		}
		for(int i =0 ; i<columNum ; i++){
			xValue.add(list.get(i).getSupplier());
			valueList.add(new Double(list.get(i).getDefectLot()));
		}
		List<List<Double>> ylist = new ArrayList<List<Double>>();
		ylist.add(valueList);
		chart.setSeriesNames(seriesNames);
		chart.setxValue(xValue);
		chart.setyValues(ylist);
		return chart;
	}
	/**
	 * 加载查询条件
	 */
	@Override
	public void loadData(Model model) {
		Map<String,List<TestInstance>> productType = getMesProductType();
		List<TestInstance> parList = getPartType();
		Map<String,String> partMap = TestInstanceUtil.getPartMap(parList);
		JSONObject partObject = JSONObject.fromObject( partMap );
		model.addAttribute("partMap", partObject);
		model.addAttribute("productType", productType);
		model.addAttribute("id_end",System.currentTimeMillis());
	}
	/**
	 * 比较月份
	 * @throws Exception 
	 */
	@Override
	public List<String> compareDate(List<TestInstance> supList,
			TestInstance testInstance) throws Exception {
		List<TestInstance> nList = new ArrayList<TestInstance>();
		String dateType = testInstance.getDateType();
		String date = testInstance.getDateT();
		int columNum = testInstance.getColumnNum()-1;
		List<String> dateList = new ArrayList<String>();
		if("天".equals(dateType)){
			 dateList = DateEditor.getBackDayList(date, columNum);
		}else if("周".equals(dateType)){
			dateList = DateEditor.getBackWeekList(date, columNum);
		}else if("月".equals(dateType)){
			dateList = DateEditor.getBackMonthList(date, columNum);
		}
		for(String str : dateList){
			boolean flag = false;
			for(TestInstance test : supList){
				if("周".equals(dateType)){
					if(getWeek(str).getWeek().toString().equals(test.getDateT())){
						flag = true;
						test.setDateT(str);
					}
				}else{
					if(str.equals(test.getDateT())){
						flag = true;
					}
				}
			}
			if(!flag){
				TestInstance t = new TestInstance();
				t.setDateT(str);
				nList.add(t);
			}
		}
		supList.addAll(nList);
		Collections.sort(supList,new Comparator<TestInstance>() {

			@Override
			public int compare(TestInstance o1, TestInstance o2) {
				if(Long.valueOf(o1.getDateT().replace("-", ""))<Long.valueOf(o2.getDateT().replace("-", ""))){
					return -1;
				}else{
					return 1;
				}
			}
		});
		return dateList;
	}
	@Override
	public List<String> setSupplierAndPart(List<TestInstance> supList,int type) {
		List<String> list = new ArrayList<String>();
		int i = 0;
		for(TestInstance test: supList){
			if(type==1 || type == 6 || type==8){
//				list.add(test.getSupplierCode());
				//换成新供应商编号
				list.add(test.getNewSupplierNumber());
			}else if(type==2 || type == 7){
//				list.add(test.getPartNumber());
				//换成新物料编号
				list.add(test.getNewPartNumber());
			}
			i++;
			if(i>=60){
				break;
			}
		}
		return list;
	}
	/**
	 * 更新testInstance表
	 */
	@Override
	public void updateTestInstance(String startTime, String endTime) throws Exception{
		//删除
//	    int t =	testInstanceMapper.deleteTestInstanceByPeriod(startTime,endTime);
	    //插入
	    int j = testInstanceMapper.insertTestInstanceByPeriod(startTime,endTime);
	    System.out.println(j);
	}
	/**
	 * 获取单个总数
	 */
	@Override
	public Map<String,Long> getTotal(List<TestInstance> supList,TestInstance test) {
		Map<String,Long> map = new HashMap<String, Long>();
		int type = test.getType();
		//供应商
		if(type==1 || type == 6){
			//批
			if("0".equals(test.getAnalysisType())){
				for(TestInstance  t : supList){
					map.put(t.getSupplierBrief(), Long.valueOf(t.getTotalLot()));
				}
			}else{
				for(TestInstance  t : supList){
					map.put(t.getSupplierBrief(), Long.valueOf(t.getTotalQty()));
				}
			}
		//零部件	
		}else if(type==2 ||  type == 7){
			//批
			if("0".equals(test.getAnalysisType())){
				for(TestInstance  t : supList){
					map.put(t.getPartName(), Long.valueOf(t.getTotalLot()));
				}
			}else{
				for(TestInstance  t : supList){
					map.put(t.getPartName(), Long.valueOf(t.getTotalQty()));
				}
			}
		}else if(type == 4 || type ==5){
			//批
			if("0".equals(test.getAnalysisType())){
				for(TestInstance  t : supList){
					map.put(t.getDateT(), Long.valueOf(t.getTotalLot()));
				}
			}else{
				for(TestInstance  t : supList){
					map.put(t.getDateT(), Long.valueOf(t.getTotalQty()));
				}
			}
		}else if(type == 3){
			//批
			if("0".equals(test.getAnalysisType())){
				for(TestInstance  t : supList){
					//性能
					map.put("性能", Long.valueOf(t.getDefectLot()));
					//尺寸
					map.put("尺寸", Long.valueOf(t.getDefectLot()));
					//外观
					map.put("外观", Long.valueOf(t.getDefectLot()));
					//其他
					map.put("其他", Long.valueOf(t.getDefectLot()));
				}
			}else{
				for(TestInstance  t : supList){
					//性能
					map.put("性能", Long.valueOf(t.getTotalQty()));
					//尺寸
					map.put("尺寸", Long.valueOf(t.getTotalQty()));
					//外观
					map.put("外观", Long.valueOf(t.getTotalQty()));
					//其他
					map.put("其他", Long.valueOf(t.getTotalQty()));
				}
			}
		}
		
		return map;
	}
	/**
	 * 获取供应商map
	 */
	@Override
	public Map<String, String> getScodeMap(List<TestInstance> supList, int type) {
	    Map<String, String> map = new HashMap<String, String>();
		for(TestInstance test : supList){
			if(type == 1 ){
				map.put(test.getSupplierBrief(), test.getNewSupplierNumber());
			}else if(type == 2){
				map.put(test.getPartName(), test.getNewPartNumber());
			}
		}
		return map;
	}
	
}
