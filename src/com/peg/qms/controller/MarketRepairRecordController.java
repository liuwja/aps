package com.peg.qms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CommonVo;
import com.peg.model.MarketRepairRecord;
import com.peg.service.CommonServiceI;
import com.peg.service.MarketRepairRecordServiceI;
import com.peg.web.util.ConditionUtil;
import com.peg.web.util.ConstantInterface;
import com.peg.web.util.ExcelUtilities;
import com.peg.web.util.TmStringUtils;
import com.peg.web.util.WebUtil;

@Controller
@RequestMapping("base/repairRecord")
public class MarketRepairRecordController extends BaseController{

	@Autowired
	private MarketRepairRecordServiceI marketRepairRecordService;
	@Autowired
	private CommonServiceI commonService;
	
	@RequestMapping("/repairRecord")
	public String marketRepairRecord(Model model,MarketRepairRecord record,PageParameter page,CommonVo comVo) throws ParseException
	{
		//图表连接进入转UTF-8格式
		record.setProductType(WebUtil.ISOToUTF8(record.getProductType()));
		record.setFaultReasonValid(WebUtil.ISOToUTF8(record.getFaultReasonValid()));
		record.setIsConsumed(WebUtil.ISOToUTF8(record.getIsConsumed()));  //是否消耗配件
		record.setIsOver(WebUtil.ISOToUTF8(record.getIsOver()));
		record.setMeshFaultReasonName(WebUtil.ISOToUTF8(record.getMeshFaultReasonName()));
		record.setFaultReasonName(WebUtil.ISOToUTF8(comVo.getFaultReasonName()));
		record.setFaultTypeName(WebUtil.ISOToUTF8(record.getFaultTypeName()));
		
		comVo.setProductFamilyTxt(WebUtil.ISOToUTF8(comVo.getProductFamilyTxt()));
		comVo.setPartTypeListTxt(WebUtil.ISOToUTF8(comVo.getPartTypeListTxt()));
		comVo.setRegionListTxt(WebUtil.ISOToUTF8(comVo.getRegionListTxt()));
		comVo.setMergeRegionListTxt(WebUtil.ISOToUTF8(comVo.getMergeRegionListTxt()));
		comVo.setMeshFaultName(WebUtil.ISOToUTF8(comVo.getMeshFaultName()));
		comVo.setFaultTypeTxt(WebUtil.ISOToUTF8(comVo.getFaultTypeTxt()));
		comVo.setFaultReasonTxt(WebUtil.ISOToUTF8(comVo.getFaultReasonTxt()));
		if(TmStringUtils.isNotEmpty(comVo.getPartTypeListTxt())){
			String[] partTypeArry = comVo.getPartTypeListTxt().replaceAll("'", "").split(";");
			record.setPartTypeList(Arrays.asList(partTypeArry));
		}
		if(TmStringUtils.isNotEmpty(comVo.getProductFamilyTxt())){
			String[] productFamilyArry = comVo.getProductFamilyTxt().replaceAll("'", "").split(";");
			record.setProductFamilyList(Arrays.asList(productFamilyArry));
		}
		if(TmStringUtils.isNotEmpty(comVo.getRegionListTxt())){
			String[] regionArry = comVo.getRegionListTxt().replaceAll("'", "").split(";");
			record.setRegionList(Arrays.asList(regionArry));
		}
		if(TmStringUtils.isNotEmpty(comVo.getMergeRegionListTxt())) {
			String[] regionArry = comVo.getMergeRegionListTxt().replaceAll("'", "").split(";");
			record.setMergeRegionList(Arrays.asList(regionArry));
		}
//		if(TmStringUtils.isNotEmpty(comVo.getProductFamilyTxt())){
//			String[] prodFamilyArry = comVo.getProductFamilyTxt().replaceAll("'", "").split(",");
//			record.setProductFamily(prodFamilyArry[0]);
//		}
		if(TmStringUtils.isNotEmpty(comVo.getFaultTypeCode())){
			String[] faultTypeArry = comVo.getFaultTypeCode().split(",");
			record.setFaultTypeCodeList(Arrays.asList(faultTypeArry));
		}
		if(TmStringUtils.isNotEmpty(comVo.getMeshFaultReasonCode())){
			String[] meshfaultReasonArry = comVo.getMeshFaultReasonCode().split(",");
			record.setMeshfaultReasonCodeList(Arrays.asList(meshfaultReasonArry));
		}
		if(TmStringUtils.isNotEmpty(comVo.getFaultReasonCode())){
			String[] faultReasonArry = comVo.getFaultReasonCode().split(",");
			record.setFaultReasonCodeList(Arrays.asList(faultReasonArry));
		}
		if(TmStringUtils.isNotEmpty(comVo.getVocTypeID())){
			String[] vocTypeArry = comVo.getVocTypeID().split(",");
			record.setVocTypeList(Arrays.asList(vocTypeArry));
		}
		
		setBaseData(model);
		ConditionUtil.loadProductFamily(model, comVo, commonService);
		ConditionUtil.loadProductType(model, comVo, commonService);
		ConditionUtil.loadRegion(model, comVo, commonService);
		ConditionUtil.loadMergeRegion(model, comVo, commonService);
		model.addAttribute("comVo", comVo);
		if(record.getInsStartTime()!=null&&record.getInsStartTime()!=""){
			record.setInsStartTime(record.getInsStartTime());
			record.setInsEndTime(record.getInsEndTime());
		}
		List<MarketRepairRecord> list = marketRepairRecordService.findAllRepair(page,record);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", record);
		return "/qms/base/repairRecord/repairRecord";
	}
	
	/**
	 * 初始化维修记录页面
	 */
	@RequestMapping("/init")
	public String init(Model model,MarketRepairRecord marketRepairRecord,PageParameter page,CommonVo vo) {
		setBaseData(model);
		ConditionUtil.loadProductFamily(model, vo, commonService);
		ConditionUtil.loadProductType(model, vo, commonService);
		ConditionUtil.loadRegion(model, vo, commonService);
		ConditionUtil.loadMergeRegion(model, vo, commonService);
		model.addAttribute("comVo", vo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());
		marketRepairRecord.setStartTime(nowDate);
		marketRepairRecord.setEndTime(nowDate);
		model.addAttribute("page", page);
		return "/qms/base/repairRecord/repairRecord";
	}
	
	/**
	 * 时间序列矩阵-累计维修数导出excel
	 * 
	 */
	@RequestMapping("/excelOutput")
	public String excelOutput(MarketRepairRecord record, Model model,CommonVo comVo,
			HttpServletRequest request, HttpServletResponse response) {
		String retView = "/error/err";
		if(TmStringUtils.isNotEmpty(comVo.getMeshFaultReasonCode())){
			String[] meshfaultReasonArry = comVo.getMeshFaultReasonCode().split(",");
			record.setMeshfaultReasonCodeList(Arrays.asList(meshfaultReasonArry));
		}
		if(TmStringUtils.isNotEmpty(comVo.getFaultReasonCode())){
			String[] faultReasonArry = comVo.getFaultReasonCode().split(",");
			record.setFaultReasonCodeList(Arrays.asList(faultReasonArry));
		}
		if(TmStringUtils.isNotEmpty(comVo.getFaultTypeCode())){
			String[] faultTypeArry = comVo.getFaultTypeCode().split(",");
			record.setFaultTypeCodeList(Arrays.asList(faultTypeArry));
		}
		if(TmStringUtils.isNotEmpty(comVo.getRegionListTxt())){
			String[] regionArry = comVo.getRegionListTxt().replaceAll("'", "").split(";");
			record.setRegionList(Arrays.asList(regionArry));
		}
		if(TmStringUtils.isNotEmpty(comVo.getPartTypeListTxt())){
			String[] partTypeArry = comVo.getPartTypeListTxt().replaceAll("'", "").split(";");
			record.setPartTypeList(Arrays.asList(partTypeArry));
		}
		if(TmStringUtils.isNotEmpty(comVo.getProductFamilyTxt())){
			String[] productFamilyArry = comVo.getProductFamilyTxt().replaceAll("'", "").split(";");
			record.setProductFamilyList(Arrays.asList(productFamilyArry));
		}
		if(TmStringUtils.isNotEmpty(comVo.getMergeRegionListTxt())) {
			String[] regionArry = comVo.getMergeRegionListTxt().replaceAll("'", "").split(";");
			record.setMergeRegionList(Arrays.asList(regionArry));
		}
		try {
			//没有EMQ权限不允许导出手机和家庭电话
			Subject subject = SecurityUtils.getSubject();
			Boolean hasCode = subject.isPermitted("base:repairRecord:emq");
			SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int total = marketRepairRecordService.getTotalNumber(record);
			if (!hasCode && total > 40000) {
				model.addAttribute("message", "您没有MQE权限，查询的数据记录超出40000条，不能导出excel，请缩小查询范围！");
				return "/error/err";
			}
			List<MarketRepairRecord> list = marketRepairRecordService.findAll(record);
			String[] CN = null;
			if(hasCode){
				CN = new String[] { "服务中心", "工单编号", "物料名称", "物料编号", "机型类别", "产品系列", "产品型号", "主机条码","生产时间","安装时间", "故障大类代码",
						"故障小类代码", "故障大类名称", "故障小类", "结算说明", "实际完成时间", "故障描述现象","故障现场现象", "详细信息", "服务网点", "服务工程师", "家庭电话",
						"手机", "开票日期", "创建时间", "购买途径"};
			}else{
				CN = new String[] { "服务中心", "工单编号", "物料名称", "物料编号", "机型类别", "产品系列", "产品型号", "主机条码","生产时间","安装时间", "故障大类代码",
						"故障小类代码", "故障大类名称", "故障小类", "结算说明", "实际完成时间", "故障描述现象","故障现场现象", "详细信息", "服务网点", "服务工程师",
						"开票日期", "创建时间", "购买途径"};
			}
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				MarketRepairRecord tmpVO = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getRawServiceCenter();
				tmpStr[1] = tmpVO.getOrderNumber();
				tmpStr[2] = tmpVO.getPartName();
				tmpStr[3] = tmpVO.getPartNumber();
				tmpStr[4] = tmpVO.getProductType();
				tmpStr[5] = tmpVO.getProductFamily();
				tmpStr[6] = tmpVO.getPartType();
				tmpStr[7] = tmpVO.getPartCode();
				tmpStr[8] = tmpVO.getDownlineDate();
				if(tmpVO.getIntallDate()!=null){
					tmpStr[9] = dateTimeFormat.format(tmpVO.getIntallDate());
				}
				tmpStr[10] = tmpVO.getFaultTypeCode();
				
				tmpStr[11] = tmpVO.getFaultReasonCode();
				tmpStr[12] = tmpVO.getFaultTypeName();
				tmpStr[13] = tmpVO.getFaultReasonName();
				tmpStr[14] = tmpVO.getSettlementDesc();
				if(tmpVO.getFinishDate()!=null){
					tmpStr[15] = dateTimeFormat.format(tmpVO.getFinishDate());
				}
				tmpStr[16] = tmpVO.getFaultDesc();
				tmpStr[17] = tmpVO.getFaultCurrentDesc();
				tmpStr[18] = tmpVO.getInfoDesc();
				tmpStr[19] = tmpVO.getServiceSite();
				tmpStr[20] = tmpVO.getServiceEngineer();
				if(hasCode){
					tmpStr[21] = tmpVO.getHomePhone();
					tmpStr[22] = tmpVO.getCellphone();
					if(tmpVO.getInvoiceDate()!=null){
						tmpStr[23] = dateTimeFormat.format(tmpVO.getInvoiceDate());
					}
					if(tmpVO.getRecordTime()!=null){
						tmpStr[24] = dateTimeFormat.format(tmpVO.getRecordTime());
					}
					tmpStr[25] = tmpVO.getBuyType();
				}else{
					if(tmpVO.getInvoiceDate()!=null){
						tmpStr[21] = dateTimeFormat.format(tmpVO.getInvoiceDate());
					}
					if(tmpVO.getRecordTime()!=null){
						tmpStr[22] = dateTimeFormat.format(tmpVO.getRecordTime());
					}
					tmpStr[23] = tmpVO.getBuyType();
				}
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
					contentType, "CRM市场维修记录" + fname);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retView;
	}
	
	@RequestMapping("/initIntermediate")
	public String initIntermediate(Model model, MarketRepairRecord marketRepairRecord,PageParameter page,CommonVo vo) {
		model.addAttribute("page", page);
		model.addAttribute("vo", marketRepairRecord);
		model.addAttribute("comVo", vo);
		return "/qms/base/repairRecord/repairRecordIntermediate";
	}
	
	@RequestMapping("/marketRepairRecordIntermediate")
	public String marketRepairRecordIntermediate(Model model, MarketRepairRecord marketRepairRecord,PageParameter page,CommonVo vo) {
		if(TmStringUtils.isNotEmpty(vo.getFaultTypeCode())){
			String[] faultTypeArry = vo.getFaultTypeCode().split(",");
			marketRepairRecord.setFaultTypeCodeList(Arrays.asList(faultTypeArry));
		}
		if(TmStringUtils.isNotEmpty(vo.getFaultReasonCode())){
			String[] faultReasonArry = vo.getFaultReasonCode().split(",");
			marketRepairRecord.setFaultReasonCodeList(Arrays.asList(faultReasonArry));
		}
		if(marketRepairRecord.getStartTime()!=null&&marketRepairRecord.getStartTime()!=""&&marketRepairRecord.getEndTime()!=null&&marketRepairRecord.getEndTime()!=""){
			marketRepairRecord.setStartTime(marketRepairRecord.getStartTime()+" 00:00:00");
			marketRepairRecord.setEndTime(marketRepairRecord.getEndTime()+" 23:59:59");
		}
		model.addAttribute("list", marketRepairRecordService.findAllRepairIntermediate(page, marketRepairRecord, ConstantInterface.YES));
		model.addAttribute("page", page);
		model.addAttribute("vo", marketRepairRecord);
		model.addAttribute("comVo", vo);
		return "/qms/base/repairRecord/repairRecordIntermediate";
	}
	
	@RequestMapping("/excelOutputIntermediate")
	public void excelOutputIntermediate(MarketRepairRecord record, Model model,CommonVo comVo, HttpServletRequest request, HttpServletResponse response) {
		if(TmStringUtils.isNotEmpty(comVo.getFaultReasonCode())){
			String[] faultReasonArry = comVo.getFaultReasonCode().split(",");
			record.setFaultReasonCodeList(Arrays.asList(faultReasonArry));
		}
		if(TmStringUtils.isNotEmpty(comVo.getFaultTypeCode())){
			String[] faultTypeArry = comVo.getFaultTypeCode().split(",");
			record.setFaultTypeCodeList(Arrays.asList(faultTypeArry));
		}
		try {
			List<MarketRepairRecord> list = marketRepairRecordService.findAllRepairIntermediate(null, record, ConstantInterface.NO);
			String[] CN = new String[]{"服务中心", "工单编号", "主机条码", "物料名称", "故障大类代码", "故障小类代码", "故障大类名称", "故障小类名称", "结算说明", "实际完成时间", "故障描述现象", "故障现场现象", "详细信息", "服务网点", "服务工程师", "家庭电话", "手机", "开票日期", "创建时间", "购买途径"};
			List<String[]> excelList = new ArrayList<String[]>();
			for (int i = 0; i < list.size(); i++) {
				MarketRepairRecord tmpVO = list.get(i);
				String[] tmpStr = new String[CN.length];
				tmpStr[0] = tmpVO.getServiceCenter();
				tmpStr[1] = tmpVO.getOrderNumber();
				tmpStr[2] = tmpVO.getPartCode();
				tmpStr[3] = tmpVO.getPartName();
				tmpStr[4] = tmpVO.getFaultTypeCode();
				tmpStr[5] = tmpVO.getFaultReasonCode();
				tmpStr[6] = tmpVO.getFaultTypeName();
				tmpStr[7] = tmpVO.getFaultReasonName();
				tmpStr[8] = tmpVO.getSettlementDesc();
				if (tmpVO.getFinishDate() != null) {
					tmpStr[9] = tmpVO.getFinishDate().toString();
				} else {
					tmpStr[9] = "";
				}
				tmpStr[10] = tmpVO.getFaultDesc();
				tmpStr[11] = tmpVO.getFaultCurrentDesc();
				tmpStr[12] = tmpVO.getInfoDesc();
				tmpStr[13] = tmpVO.getServiceSite();
				tmpStr[14] = tmpVO.getServiceEngineer();
				tmpStr[15] = tmpVO.getHomePhone();
				tmpStr[16] = tmpVO.getCellphone();
				if (tmpVO.getInvoiceDate() != null) {
					tmpStr[17] = tmpVO.getInvoiceDate().toString();
				} else {
					tmpStr[17] = "";
				}
				if (tmpVO.getRecordTime() != null) {
					tmpStr[18] = tmpVO.getRecordTime().toString();
				} else {
					tmpStr[18] = "";
				}
				tmpStr[19] = tmpVO.getBuyType();
				excelList.add(tmpStr);
			}
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String fname = System.currentTimeMillis() + ".xls";// Excel文件名字
			String filePath = rootPath + "/" + fname;
			ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
			String contentType = "application/msexcel";
			ExcelUtilities.downloadExcel(request, response, filePath, contentType, "CRM市场维修记录-中间表" + fname);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
