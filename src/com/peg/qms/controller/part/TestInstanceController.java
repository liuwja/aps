package com.peg.qms.controller.part;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.peg.beans.part.TestInstanceBean;
import com.peg.beans.part.TestInstanceUtil;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.dao.part.ErpPartReturnMapper;
import com.peg.dao.part.TestInstanceMapper;
import com.peg.model.part.ErpPartReturn;
import com.peg.model.part.PartCategory;
import com.peg.model.part.TestInstance;
import com.peg.qms.controller.BaseController;
import com.peg.service.part.TestInstanceServiceI;
import com.peg.web.util.DateEditor;
import com.peg.web.util.ExcelUtilities;
import com.peg.web.util.TmStringUtils;
import com.peg.web.util.WebUtil;

/**
 * 全质量进料部分控制器
 * @author fjt
 *
 */
@Controller
@RequestMapping("quality/testInstance")
public class TestInstanceController extends BaseController{

	@Autowired
	private TestInstanceServiceI testInstanceService;
	
	@Autowired
	private TestInstanceMapper testInstanceMapper;
	
	@Autowired
	private ErpPartReturnMapper erpPartReturnMapper;
	
	/**
	 * 物料首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/supplerDefectRate")
	public String supplierDefectRate(Model model){
		List<TestInstance> proList = testInstanceService.getProductType();
		List<TestInstance> supList = testInstanceService.getSupplier();
		List<TestInstance> parList = testInstanceService.getPartType();
		Map<String,String> supMap = TestInstanceUtil.getSupMap(supList);
		Map<String,String> partMap = TestInstanceUtil.getPartMap(parList);
		JSONObject supObject = JSONObject.fromObject( supMap );
		JSONObject partObject = JSONObject.fromObject( partMap );
		model.addAttribute("proList", proList);
		model.addAttribute("supMap", supObject);
		model.addAttribute("partMap", partObject);
		return "qms/part/testInstance/supplerDefectRate";
	}

	@RequestMapping("/lodeSupplierDefectRate")
	public void lodeSupplierDefectRate(TestInstance testInstance,HttpServletResponse response){
//		int result = 1;
//		String msg = "";
		String sql = "";
		Map<String,List<TestInstance>> productType = testInstanceService.getMesProductType();
		try{
	        TestInstanceBean testInstanceBean = new TestInstanceBean();
			sql = testInstanceBean.getSql(testInstance,productType);
			System.out.println(sql);
		}catch(Exception e){
			
		}
	}
	
	/**
	 * 供应商不良
	 * @param model
	 * @return
	 */
	@RequestMapping("/supplerDefect")
	public String supplierDefect(Model model){
		testInstanceService.loadData(model);
		
		return "qms/part/testInstance/supplerDefect";
	}

	/**
	 * 加载供应商不良图表
	 * @param testInstance
	 * @param response
	 */
	@RequestMapping("/lodeSupplierDefect")
	public void lodeSupplierDefect(TestInstance testInstance,HttpServletResponse rs){
		int result = 0;
		String msg = "";
		String sql = "";
		Map<String,Object> map = new HashMap<String, Object>();
		//获取机型
		Map<String,List<TestInstance>> productType = testInstanceService.getMesProductType();
		try{
			//获取供应商批次
			if(TmStringUtils.isNotEmpty(testInstance.getLotStartTime()) && TmStringUtils.isNotEmpty(testInstance.getLotEndTime()) ){
				testInstance.setLotStartTime(DateEditor.getLotTime(testInstance.getLotStartTime()));
				testInstance.setLotEndTime(DateEditor.getLotTime(testInstance.getLotEndTime()));
			}
	        TestInstanceBean testInstanceBean = new TestInstanceBean();
	        //获取周
	        if(testInstance.getDateType().equals("周")){
	        	TestInstance week = testInstanceService.getWeek(testInstance.getDateT());
	        	testInstance.setWeek(week.getWeek());
	        }
	        //获取总批次数，不良批次数
	        TestInstance totTest = testInstance.clone();
	        spiltSupPart(totTest);
			TestInstance totalQty = testInstanceMapper.getPeriodTotalQty(totTest);
			testInstance.setTotalS(totalQty.getTotalLot());
			testInstance.setDefectS(totalQty.getDefectLot());
	        //供应商不良
	        testInstance.setType(1);
			sql = testInstanceBean.getSql(testInstance,productType);
			List<TestInstance> supList = testInstanceService.getTestInstance(sql);
			//传入供应商list
			List<String> supplierCodeList =  testInstanceService.setSupplierAndPart(supList,testInstance.getType());
			map.put("supplierCodeList", supplierCodeList);
			//传入供应商map
			Map<String,String> scodeMap = testInstanceService.getScodeMap(supList,testInstance.getType());
			map.put("supCodeMap", scodeMap);
			//获取单个总数
			Map<String,Long> supplierTotal = testInstanceService.getTotal(supList,testInstance);
			map.put("total1", supplierTotal);
			String option1 = TestInstanceUtil.getData(testInstance,supList);
			JSONObject supJson = JSONObject.fromObject(option1);
			map.put("option1", supJson);
			//零部件不良
			testInstance.setType(2);
			sql = testInstanceBean.getSql(testInstance,productType);
			List<TestInstance> partList = testInstanceService.getTestInstance(sql);
			//传入零部件list
			List<String> partCodeList =  testInstanceService.setSupplierAndPart(partList,testInstance.getType());
			map.put("partCodeList", partCodeList);
			//传入零部件map
			Map<String,String> pcodeMap = testInstanceService.getScodeMap(partList,testInstance.getType());
			map.put("partCodeMap", pcodeMap);
			//获取单个总数
			Map<String,Long> partTotal = testInstanceService.getTotal(partList,testInstance);
			map.put("total2", partTotal);
			String option2 = TestInstanceUtil.getData(testInstance,partList);
			JSONObject partJson = JSONObject.fromObject(option2);
			map.put("option2", partJson);
			//进料不良现象批次
			testInstance.setType(3);
			sql = testInstanceBean.getSql(testInstance,productType);
			List<TestInstance> defList = testInstanceService.getTestInstance(sql);
			//获取单个总数
			Map<String,Long> defectTotal = testInstanceService.getTotal(defList,testInstance);
			map.put("total3", defectTotal);
			if(defList.isEmpty()){
				defList.add(new TestInstance());
			}
			String option3 = TestInstanceUtil.getData(testInstance,defList);
			JSONObject defJson = JSONObject.fromObject(option3);
			map.put("option3", defJson);
			
			//供应商不良数（率）趋势排列控制图
			testInstance.setType(4);
			sql = testInstanceBean.getSql(testInstance,productType);
			List<TestInstance> supTrendList = testInstanceService.getTestInstance(sql);
			//补全时间
			List<String> supDateList = testInstanceService.compareDate(supTrendList,testInstance);
			map.put("supDateList", supDateList);
			//获取单个总数
			Map<String,Long> supDateTotal = testInstanceService.getTotal(supTrendList,testInstance);
			map.put("total4", supDateTotal);
			//获取上线控制线总不良数和总批次数
			TestInstance totalTest = testInstanceMapper.getTotalQty(totTest);
			testInstance.setTotalS(totalTest.getTotalLot());
			testInstance.setDefectS(totalTest.getDefectLot());
			String option4 = TestInstanceUtil.getData(testInstance,supTrendList);
			JSONObject supTrendJson = JSONObject.fromObject(option4);
			map.put("option4", supTrendJson);
			//零部件不良数（率）趋势排列控制图
			testInstance.setType(5);
			sql = testInstanceBean.getSql(testInstance,productType);
			List<TestInstance> partTrendList = testInstanceService.getTestInstance(sql);
			//补全时间
			List<String> partDateList = testInstanceService.compareDate(partTrendList,testInstance);
			map.put("partDateList", partDateList);
			//获取单个总数
			Map<String,Long> partDateTotal = testInstanceService.getTotal(partTrendList,testInstance);
			map.put("total5", partDateTotal);
			String option5 = TestInstanceUtil.getData(testInstance,partTrendList);
			JSONObject partTrendJson = JSONObject.fromObject(option5);
			map.put("option5", partTrendJson);
			//供应商不良批（数）日期对比图
			testInstance.setType(6);
			sql = testInstanceBean.getSql(testInstance,productType);
			List<TestInstance> supComList = testInstanceService.getTestInstance(sql);
			String option6 = TestInstanceUtil.getData(testInstance,supComList);
			JSONObject supComJson = JSONObject.fromObject(option6);
			map.put("option6", supComJson);
			//零部件不良批（数）日期对比图
			testInstance.setType(7);
			sql = testInstanceBean.getSql(testInstance,productType);
			List<TestInstance> partComList = testInstanceService.getTestInstance(sql);
			String option7 = TestInstanceUtil.getData(testInstance,partComList);
			JSONObject partComJson = JSONObject.fromObject(option7);
			map.put("option7", partComJson);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			result =-1;
			msg = e.getMessage();
		}
		map.put("result", result);
		map.put("msg", msg);
		JSONObject resultMap = JSONObject.fromObject(map);
		printResponContent(rs, resultMap.toString());
	}

	/**
	 * 查询物料
	 * @param partName
	 * @param partNumber
	 * @param model
	 * @param page
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/partSelect")
	public String partSelect(@RequestParam(value="partName",required=false)String partName,
			@RequestParam(value="partNumber",required=false)String partNumber,
			@RequestParam(value="flag",required=false)String flag,
			@RequestParam(value="data",required=false)String data,
			@RequestParam(value="isConsumed",required=false)String isConsumed,
			Model model,PageParameter page) throws UnsupportedEncodingException 
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("partNumber", partNumber);
		bs.put("partName", partName);
		isConsumed = decode(isConsumed);
		if (StringUtils.isNotEmpty(isConsumed)) {
			if (isConsumed.equals("是")) {
				bs.put("isConsumed", "SerialNumber");
			} else {
				bs.put("isConsumed", "Quantity");
			}
			model.addAttribute("isConsumed", isConsumed);
		}
		List<TestInstance> list = testInstanceService.getPartAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("partNumber", partNumber);
		model.addAttribute("partName", partName);
		model.addAttribute("data", WebUtil.ISOToUTF8(data));
		for (TestInstance t : list) {
			String id = t.getId() + "";
			String t_partName = t.getPartName();
			String t_partNumber = t.getPartNumber();
			t.setIdNameNumber(id + "," + t_partNumber + "," + t_partName);
		}
		if(flag!=null){
			model.addAttribute("firstLoad", "2");
			return "qms/part/select/partSelectResult";
		}else{
			model.addAttribute("firstLoad", "1");
			return "qms/part/select/partSelect";
		}
		
	}
	/**
	 * 查询物料一二级类
	 */
	@RequestMapping("/partCategorySelect")
	public String partCategorySelect(@RequestParam(value="data",required=false)String data,@RequestParam("categoryName") String partCategoryName,@RequestParam(value="flag",required=false)String flag,Model model,PageParameter page) throws UnsupportedEncodingException 
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		List<PartCategory> partCategoryList = new ArrayList<PartCategory>();
		List<PartCategory> partCategoryTwoList = new ArrayList<PartCategory>();
		partCategoryList = testInstanceMapper.getPartCategoryOne();
		if(!partCategoryName.equals(" ")&&partCategoryName != null&&flag!=null){
			bs.put("partCategoryName", partCategoryName);			
		}
		partCategoryTwoList = testInstanceMapper.getPartCategoryByPage(bs);		
		model.addAttribute("partCategoryList",partCategoryList);
		for (PartCategory t : partCategoryTwoList) {
			String id = t.getId() + "";
			String supplierCode = t.getCategoryName();
			String supplier = t.getCategoryTwoName();
			t.setIdNameNumber(id + "," + supplierCode + "," + supplier);
		}
		model.addAttribute("list",partCategoryTwoList);
		model.addAttribute("page", page);
		model.addAttribute("categoryName",partCategoryName);
		model.addAttribute("data", WebUtil.ISOToUTF8(data));
		if(flag != null ){
			model.addAttribute("firstLoad", "2");
			return "qms/part/select/partCategorySelectResult";
		}else{
			model.addAttribute("firstLoad", "1");
			return "qms/part/select/partCategorySelect";
		}

	}

	/**
	 * 查询供应商
	 * @param partName
	 * @param partNumber
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("/supplierSelect")
	public String supplierSelect(@RequestParam(value="factory",required=false)String factory,
			@RequestParam(value="supplierClass",required=false)String supplierClass,
			@RequestParam(value="supplierNumber",required=false)String supplierNumber,
			@RequestParam(value="supplierName",required=false)String supplierName,
			@RequestParam(value="flag",required=false)String flag,
			@RequestParam(value="data",required=false)String data,
			Model model,PageParameter page){
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("factory", factory);
		bs.put("supplierClass", supplierClass);
		bs.put("supplierNumber", supplierNumber);
		bs.put("supplierName", supplierName);
		List<TestInstance> list = testInstanceMapper.getSupplierByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("factory", factory);
		model.addAttribute("supplierClass", supplierClass);
		model.addAttribute("supplierNumber", supplierNumber);
		model.addAttribute("supplierName", supplierName);
		model.addAttribute("data", WebUtil.ISOToUTF8(data));
		for (TestInstance t : list) {
			String id = t.getId() + "";
			String supplierCode = t.getSupplierCode();
			String supplier = t.getSupplier();
			t.setIdNameNumber(id + "," + supplierCode + "," + supplier);
		}
		if(flag != null ){
			model.addAttribute("firstLoad", "2");
			return "qms/part/select/supplierSelectResult";
		}else{
			model.addAttribute("firstLoad", "1");
			return "qms/part/select/supplierSelect";
		}
	}
	/**
	 * 物料类别
	 * @param factory
	 * @param partType
	 * @return
	 */
	@RequestMapping("/partTypeSelect")
	public String partTypeSelect(@RequestParam(value="factory",required=false)String factory,
			@RequestParam(value="partType",required=false)String partType,
			@RequestParam(value="flag",required=false)String flag,
			@RequestParam(value="data",required=false)String data,
			Model model,PageParameter page){
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("factory", factory);
		bs.put("partType", partType);
		List<TestInstance> list = testInstanceMapper.getPartTypeByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("factory", factory);
		model.addAttribute("partType", partType);
		model.addAttribute("data", WebUtil.ISOToUTF8(data));
		if(flag != null ){
			return "qms/part/select/partTypeSelectResult";
		}else{
			return "qms/part/select/partTypeSelect";
		}
	}
	/**
	 * 进料详细页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/feedDetail")
	public String feedDetail(Model model){
		testInstanceService.loadData(model);
		return "qms/part/testInstance/feedDetail";
	}
	/**
	 * 加载图形
	 * @param testInstance
	 * @param rs
	 */
	@RequestMapping("/loadFeedDetail")
	public void loadFeedDetail(TestInstance testInstance,HttpServletResponse rs){
		int result = 0;
		String msg = "";
		String sql = "";
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			int type = testInstance.getType();
			//原材料退料
			if(type==8){
				 //获取周
		        if(testInstance.getDateType().equals("周")){
		        	TestInstance week = testInstanceService.getWeek(testInstance.getDateT());
		        	testInstance.setWeek(week.getWeek());
		        }
//		        EChartObj  erpPartReturn = testInstanceService.getErpPartReturn(testInstance);
		        spiltSupPart(testInstance);
		        List<TestInstance> list = testInstanceMapper.getErpPartReturn(testInstance);
		       //传入供应商或者物料list
				List<String> comList =  testInstanceService.setSupplierAndPart(list,type);
				map.put("comList", comList);
		        String option = TestInstanceUtil.getData(testInstance,list);
		        JSONObject supJson = JSONObject.fromObject(option);
		        map.put("option", supJson);
			}else{
				//获取机型
				Map<String,List<TestInstance>> productType = testInstanceService.getMesProductType();
				TestInstanceBean testInstanceBean = new TestInstanceBean();
				//供应商批次
				if(TmStringUtils.isNotEmpty(testInstance.getLotStartTime()) && TmStringUtils.isNotEmpty(testInstance.getLotEndTime()) ){
					testInstance.setLotStartTime(DateEditor.getLotTime(testInstance.getLotStartTime()));
					testInstance.setLotEndTime(DateEditor.getLotTime(testInstance.getLotEndTime()));
				}
		        //获取周
		        if(testInstance.getDateType().equals("周")){
		        	TestInstance week = testInstanceService.getWeek(testInstance.getDateT());
		        	testInstance.setWeek(week.getWeek());
		        }
		    	sql = testInstanceBean.getSql(testInstance,productType);
				List<TestInstance> supList = testInstanceService.getTestInstance(sql);
				//传入供应商或者物料list
				List<String> comList =  testInstanceService.setSupplierAndPart(supList,type);
				//传入供应商或物料map
				Map<String,String> codeMap = testInstanceService.getScodeMap(supList,testInstance.getType());
				map.put("codeMap", codeMap);
				//获取单个总数
				Map<String,Long> totalList = testInstanceService.getTotal(supList,testInstance);
				map.put("totalList", totalList);
				map.put("comList", comList);
				if(type == 1 || type ==2){
					spiltSupPart(testInstance);
					TestInstance totalTest = testInstanceMapper.getPeriodTotalQty(testInstance);
					testInstance.setTotalS(totalTest.getTotalLot());
					testInstance.setDefectS(totalTest.getDefectLot());
				}
				if(type== 4 || type==5){
					spiltSupPart(testInstance);
					TestInstance totalTest = testInstanceMapper.getTotalQty(testInstance);
					testInstance.setTotalS(totalTest.getTotalLot());
					testInstance.setDefectS(totalTest.getDefectLot());
					List<String> dateList = testInstanceService.compareDate(supList,testInstance);
					map.put("dateList", dateList);
				}
				String option = TestInstanceUtil.getData(testInstance,supList);
				JSONObject supJson = JSONObject.fromObject(option);
				map.put("option", supJson);
			}
	        
		}catch(Exception e){
				logger.error(e.getMessage(), e);
				result =-1;
				msg = e.getMessage();
			} 
		map.put("result", result);
		map.put("msg", msg);
		JSONObject resultMap = JSONObject.fromObject(map);
		System.out.println(resultMap.toString());
		printResponContent(rs, resultMap.toString());
		}
	
	private void spiltSupPart(TestInstance testInstance){
		testInstance.setSupplierCode(TmStringUtils.getSupStringByComma(testInstance.getSupplierCode()));
		testInstance.setPartType(TmStringUtils.getSupStringBySemicolon(testInstance.getPartType()));
		if("否".equals(testInstance.getPartVersion()) && testInstance.getDistinction() != null && 1!= testInstance.getDistinction()){
			testInstance.setPartNumber(TmStringUtils.getVersionPartNumber(testInstance.getPartNumber(), testInstance.getPartVersion()));
		}else{
			testInstance.setPartNumber(TmStringUtils.getSupStringByComma(testInstance.getPartNumber()));
		}
	}
	
	/**
	 * 进料明细界面
	 * @param model
	 * @return
	 */
	@RequestMapping("/testInstentDetail")
	public String testInstentDetail(PageParameter page,Model model,TestInstance testInstance){
		testInstance = testInstanceService.decode(testInstance);
		testInstanceService.loadData(model);
		testInstance.setPartTypeListText(WebUtil.parseToSelectedStr(testInstance.getPartType()));
		BaseSearch bs = new BaseSearch();
		testInstanceService.getDate(testInstance,bs);
		//获取供应商批次
		String lotStarTime = "";
		String lotEndTime = "";
		if(TmStringUtils.isNotEmpty(testInstance.getLotStartTime())){
			lotStarTime = DateEditor.getLotTime(testInstance.getLotStartTime());
			lotEndTime = DateEditor.getLotTime(testInstance.getLotEndTime());
		}
		bs.setPage(page);
		bs.put("factory", testInstance.getFactory());
		bs.put("productType", testInstance.getProductType());
		bs.put("supplierNumber",TmStringUtils.getSupStringByComma(testInstance.getSupplierCode()));
//		bs.put("supplier", TmStringUtils.getSupStringByComma(testInstance.getSupplier()));
		bs.put("partType", TmStringUtils.getSupStringBySemicolon(testInstance.getPartType()));
		bs.put("partClass", testInstance.getPartClass());
		if("否".equals(testInstance.getPartVersion())){
//			if(testInstance.getDistinction() != null && testInstance.getDistinction() == 1){
//				bs.put("partNumber", TmStringUtils.getSupStringByComma(testInstance.getPartNumber()));
//			}else{
				bs.put("partNumber", TmStringUtils.getVersionPartNumber(testInstance.getPartNumber(), testInstance.getPartVersion()));
//			}
		}else{
			bs.put("partNumber", TmStringUtils.getSupStringByComma(testInstance.getPartNumber()));
		}
//		bs.put("partName", TmStringUtils.getSupStringByComma(testInstance.getPartName()));
		bs.put("partVersion", testInstance.getPartVersion());
		bs.put("isNew", testInstance.getIsNew());
		bs.put("lotStartTime", lotStarTime);
		bs.put("lotEndTime", lotEndTime);
		bs.put("defectType", testInstance.getDefectType());
		bs.put("resultS", testInstance.getResultS());
		bs.put("smallBatch", testInstance.getSmallBatch());
		bs.put("source", testInstance.getSource());
		String defectType = testInstance.getDefectType();
		if("性能".equals(defectType)){
			bs.put("propertyType", "不合格");
		}else if("尺寸".equals(defectType)){
			bs.put("sizeType", "不合格");
		}else if("外观".equals(defectType)){
			bs.put("aspectType", "不合格");
		}else if("其他".equals(defectType)){
			bs.put("otherType", "不合格");
		}
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute("testInstentDetailCondition", bs);
		List<TestInstance> list = testInstanceService.findAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", testInstance);
		
		return "qms/part/testInstance/testInstentDetail";
	}
	
	/**
	 * ERP退料明细界面
	 * @param model
	 * @return
	 */
	@RequestMapping("/erpPartReturnDetail")
	public String erpPartReturnDetail(PageParameter page,Model model,TestInstance testInstance){
		testInstance = testInstanceService.decode(testInstance);
		testInstanceService.loadData(model);
		testInstance.setPartTypeListText(WebUtil.parseToSelectedStr(testInstance.getPartType()));
		BaseSearch bs = new BaseSearch();
		testInstanceService.getDate(testInstance,bs);
		bs.setPage(page);
		bs.put("productType", testInstance.getProductType());
		bs.put("supplier", TmStringUtils.getSupStringByComma(testInstance.getSupplier()));
		bs.put("partType", TmStringUtils.getSupStringBySemicolon(testInstance.getPartType()));
		bs.put("partClass", testInstance.getPartClass());
		bs.put("partName", TmStringUtils.getSupStringByComma(testInstance.getPartName()));
		bs.put("partVersion", testInstance.getPartVersion());
		bs.put("isNew", testInstance.getIsNew());
		bs.put("lotTime", testInstance.getLotTime());
		bs.put("defectType", testInstance.getDefectType());
		bs.put("resultS", testInstance.getResultS());
	
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute("erpPartReturnCondition", bs);
		List<ErpPartReturn> list = erpPartReturnMapper.findAllByPage(bs);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", testInstance);
		
		return "qms/part/testInstance/ErpPartReturn";
	}
	

	 /**
     * excel导出进料明细
     * 
     * @param model
     * @return
     */
    @RequestMapping("/exportTestExcel")
    public String exportTestExcel(Model model,
            HttpServletRequest request, HttpServletResponse response) {
        String retView = "/error/err";
        try {
            
            Subject subject = SecurityUtils.getSubject();
            BaseSearch bs = (BaseSearch)subject.getSession().getAttribute("testInstentDetailCondition");
            PageParameter page = new PageParameter();
            page.setNumPerPage(50000);
            bs.setPage(page);
            List<TestInstance> list = testInstanceService.findAllByPage(bs);
            String[] CN = { "小批量","ROSH标识","国内/外","产品线","仓库","缺陷分类","一级分类","二级分类","物料批次","物料编号","物料名称","仓库",
                    "检验时间","登记数量","检验结果","供应商编号","供应商名称",
                    "性能-结果","性能-抽检不良","性能-抽检总数","尺寸-结果",
                    "尺寸-抽检不良","尺寸-抽检总数","外观-结果","外观-抽检不良",
                    "外观-抽检总数","其他-结果","其他-抽检不良","其他-抽检总数"};
            List<String[]> excelList = new ArrayList<String[]>();
            int index = 0;
            int cols = CN.length;
            for (int i = 0; i < list.size(); i++) {
            	TestInstance tmpVO = list.get(i);
                String[] tmpStr = new String[cols];
                tmpStr[index++] = tmpVO.getSmallBatch();
                tmpStr[index++] = tmpVO.getRohs();
                tmpStr[index++] = tmpVO.getSource();
                tmpStr[index++] = tmpVO.getProductionLineName();
                tmpStr[index++] = tmpVO.getLocation();
                tmpStr[index++] = tmpVO.getDefect();
                tmpStr[index++] = tmpVO.getClass1();
                tmpStr[index++] = tmpVO.getClass2();
                tmpStr[index++] = tmpVO.getLotName();
                tmpStr[index++] = tmpVO.getPartNumber();
                tmpStr[index++] = tmpVO.getPartName();
                tmpStr[index++] = tmpVO.getWareHouse();
                
                tmpStr[index++] = tmpVO.getDateT();
                tmpStr[index++] = tmpVO.getTotalQty()+"";
                tmpStr[index++] = tmpVO.getResultS();
                tmpStr[index++] = tmpVO.getSupplierCode() ;
                tmpStr[index++] = tmpVO.getSupplier() ;
                
                tmpStr[index++] = tmpVO.getPropertyType();
                tmpStr[index++] = tmpVO.getPropertyDnum()+"";
                tmpStr[index++] = tmpVO.getPropertyTnum()+"";
                tmpStr[index++] = tmpVO.getSizeType();
                tmpStr[index++] = tmpVO.getSizeDnum()+"";
                
                tmpStr[index++] = tmpVO.getSizeTnum()+"";
                tmpStr[index++] = tmpVO.getAspectType();
                tmpStr[index++] = tmpVO.getAspectDnum()+"";
                tmpStr[index++] = tmpVO.getAspectTnum()+"";
                tmpStr[index++] = tmpVO.getOtherType() ;  
                tmpStr[index++] = tmpVO.getOtherDnum()+"" ;       
                tmpStr[index++] = tmpVO.getOtherTnum()+"" ;       
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
                    contentType, "进料明细" + fname);
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return retView;
    }
    
    /**
     * excel导出erp原材料退料
     * @param model
     * @return
     */
    @RequestMapping("/exportErpPartExcel")
    public String exportErpPartExcel(Model model,
            HttpServletRequest request, HttpServletResponse response) {
        String retView = "/error/err";
        try {
            
            Subject subject = SecurityUtils.getSubject();
            BaseSearch bs = (BaseSearch)subject.getSession().getAttribute("erpPartReturnCondition");
            PageParameter page = new PageParameter();
            page.setNumPerPage(50000);
            bs.setPage(page);
            List<ErpPartReturn> list = erpPartReturnMapper.findAllByPage(bs);
            String[] CN = { "退次日期","物料编号","物料名称","供应商编号","供应商名称",
                    "退次数量","仓库"};
            List<String[]> excelList = new ArrayList<String[]>();
            int index = 0;
            int cols = CN.length;
            for (int i = 0; i < list.size(); i++) {
            	ErpPartReturn tmpVO = list.get(i);
                String[] tmpStr = new String[cols];
                tmpStr[index++] = tmpVO.getReturnDate();
                tmpStr[index++] = tmpVO.getPartNumber();
                tmpStr[index++] = tmpVO.getPartName();
                tmpStr[index++] = tmpVO.getSupplierNumber();
                tmpStr[index++] = tmpVO.getSupplierName();
                tmpStr[index++] = tmpVO.getWareHouse();
                 
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
                    contentType, "erp原材料退料明细" + fname);
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return retView;
    }
    /**
     * 数据更新
     * @param startTime
     * @param endTime
     * @param response
     */
    @RequestMapping("/updateTestInstance")
    public void updateTestInstance(@RequestParam(value="startTime",required=false)String startTime,
    		@RequestParam(value="endTime",required=false)String endTime,
    		HttpServletResponse response){
    	int result = 0;
    	String msg = "";
    	Map<String,Object> map = new HashMap<String,Object>();
    	try{
    		testInstanceService.updateTestInstance(startTime,endTime);
    	}catch(Exception e){
    		result = -1;
    		msg = e.getMessage();
    		logger.error(e.getMessage(), e);
    	}
    	map.put("result", result);
		map.put("msg", msg);
		JSONObject resultMap = JSONObject.fromObject(map);
		printResponContent(response, resultMap.toString());
    }
    
    //导出汇总EXCEl
    @RequestMapping("/dowmLoadFeedDetail")
    public String dowmLoadFeedDetail(Model model,TestInstance testInstance,
            HttpServletRequest request, HttpServletResponse response) {
        String retView = "/error/err";
        try {
        	//获取机型
			Map<String,List<TestInstance>> productType = testInstanceService.getMesProductType();
        	int type = testInstance.getType();
        	TestInstanceBean testInstanceBean = new TestInstanceBean();
			//供应商批次
			if(TmStringUtils.isNotEmpty(testInstance.getLotStartTime()) && TmStringUtils.isNotEmpty(testInstance.getLotEndTime()) ){
				testInstance.setLotStartTime(DateEditor.getLotTime(testInstance.getLotStartTime()));
				testInstance.setLotEndTime(DateEditor.getLotTime(testInstance.getLotEndTime()));
			}
	        //获取周
	        if(testInstance.getDateType().equals("周")){
	        	TestInstance week = testInstanceService.getWeek(testInstance.getDateT());
	        	testInstance.setWeek(week.getWeek());
	        }
	    	String sql = testInstanceBean.getSql(testInstance,productType);
            List<TestInstance> list = testInstanceMapper.getTestInstance(sql);
            String[] CN = null;
            List<String[]> excelList = new ArrayList<String[]>();
            if(type==1 || type==6){
            	  CN = new String[] { "时间","供应商名称","供应商总批次数","供应商不良批次数","检查总数",
                        "检查不良数"};
            	  int index = 0;
                  int cols = CN.length;
                  for (int i = 0; i < list.size(); i++) {
                	  TestInstance tmpVO = list.get(i);
                      String[] tmpStr = new String[cols];
                      tmpStr[index++] = tmpVO.getDateT();
                      tmpStr[index++] = tmpVO.getSupplierBrief();
                      tmpStr[index++] = tmpVO.getTotalLot()+"";
                      tmpStr[index++] = tmpVO.getDefectLot()+"";
                      tmpStr[index++] = tmpVO.getTotalQty()+"";
                      tmpStr[index++] = tmpVO.getDefectQty()+"";
                      index = 0;
                      excelList.add(tmpStr);
                  }
            }else if(type ==2 || type == 7){
            	  CN = new String[] { "时间","物料编号","物料名称","总批次数","不良批次数","检查总数",
                  "检查不良数"};
		      	  int index = 0;
		            int cols = CN.length;
		            for (int i = 0; i < list.size(); i++) {
		          	  TestInstance tmpVO = list.get(i);
		                String[] tmpStr = new String[cols];
		                tmpStr[index++] = tmpVO.getDateT();
		                tmpStr[index++] = tmpVO.getPartNumber();
		                tmpStr[index++] = tmpVO.getPartName();
		                tmpStr[index++] = tmpVO.getTotalLot()+"";
		                tmpStr[index++] = tmpVO.getDefectLot()+"";
		                tmpStr[index++] = tmpVO.getTotalQty()+"";
		                tmpStr[index++] = tmpVO.getDefectQty()+"";
		                index = 0;
		                excelList.add(tmpStr);
		            }
            }else if(type ==3){
          	  CN = new String[] { "时间","不良批次数","性能不良批次数","尺寸不良批次数","外观不良批次数","其他不良批次数",
              "不良总数","性能不良个数","尺寸不良个数","外观不良个数","其他不良个数",};
	      	  int index = 0;
	            int cols = CN.length;
	            for (int i = 0; i < list.size(); i++) {
	          	  TestInstance tmpVO = list.get(i);
	                String[] tmpStr = new String[cols];
	                tmpStr[index++] = tmpVO.getDateT();
	                tmpStr[index++] = tmpVO.getDefectLot()+"";
	                tmpStr[index++] = tmpVO.getPropertyLot()+"";
	                tmpStr[index++] = tmpVO.getSizeLot()+"";
	                tmpStr[index++] = tmpVO.getAspectLot()+"";
	                tmpStr[index++] = tmpVO.getOtherLot()+"";
	                tmpStr[index++] = tmpVO.getPropertyQty()+"";
	                tmpStr[index++] = tmpVO.getSizeQty()+"";
	                tmpStr[index++] = tmpVO.getAspectQty()+"";
	                tmpStr[index++] = tmpVO.getOtherQty()+"";
	                index = 0;
	                excelList.add(tmpStr);
	            }
        }else if(type ==4 || type == 5){
        	 CN = new String[] { "时间","总到货批次","不良批次","检查总数","检查不良数"};
	      	 int index = 0;
	      	 int cols = CN.length;
	            for (int i = 0; i < list.size(); i++) {
	          	  TestInstance tmpVO = list.get(i);
	                String[] tmpStr = new String[cols];
	                tmpStr[index++] = tmpVO.getDateT();
	                tmpStr[index++] = tmpVO.getTotalLot()+"";
	                tmpStr[index++] = tmpVO.getDefectLot()+"";
	                tmpStr[index++] = tmpVO.getTotalQty()+"";
	                tmpStr[index++] = tmpVO.getDefectQty()+"";
	                index = 0;
	                excelList.add(tmpStr);
	            }
        }

            // 获取项目根目录
            String rootPath = request.getSession().getServletContext()
                    .getRealPath("/");
            String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
            String filePath = rootPath + "/" + fname;
            ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
            String contentType = "application/msexcel";
            ExcelUtilities.downloadExcel(request, response, filePath,
                    contentType, "数据明细" + fname);
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return retView;
    }
}
