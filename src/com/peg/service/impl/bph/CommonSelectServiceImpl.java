package com.peg.service.impl.bph;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.dao.bph.AssemblyProductBackMapper;
import com.peg.dao.bph.BatchDefectRecordMapper;
import com.peg.dao.bph.BoxDefectRecordMapper;
import com.peg.dao.bph.FormerProcessFqcCheckMapper;
import com.peg.dao.bph.IpqcInspectsMapper;
import com.peg.dao.bph.OqcCheckMapper;
import com.peg.dao.bph.PaintingDailyReportMapper;
import com.peg.dao.bph.ProcessAuditRecordMapper;
import com.peg.dao.bph.QualityImprovementRfpMapper;
import com.peg.dao.bph.StampingDailyReportMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.CommonVo;
import com.peg.model.EntityClass;
import com.peg.model.bph.AssemblyProductBack;
import com.peg.model.bph.BatchDefectRecord;
import com.peg.model.bph.BoxDefectRecord;
import com.peg.model.bph.FormerProcessFqcCheck;
import com.peg.model.bph.IpqcInspects;
import com.peg.model.bph.OqcCheck;
import com.peg.model.bph.PaintingDailyReport;
import com.peg.model.bph.ProcessAuditRecord;
import com.peg.model.bph.QualityImprovementRfp;
import com.peg.model.bph.StampingDailyReport;
import com.peg.service.bph.CommonSelectService;
import com.peg.web.util.ExcelUtilities;

@Service("commonSelectService")
public class CommonSelectServiceImpl implements CommonSelectService{

	@Autowired
	private AssemblyProductBackMapper assembyProductBackMapper;
	
	@Autowired
	private BatchDefectRecordMapper batchDefectRecordMapper;
	
	@Autowired
	private FormerProcessFqcCheckMapper formerProcessFqcCheckMapper;
	
	@Autowired
	private StampingDailyReportMapper stampingDailyReportMapper;
	
	@Autowired
	private ProcessAuditRecordMapper processAuditRecordMapper;
	
	@Autowired
	private QualityImprovementRfpMapper QualityImprovementRfpMapper;
	
	@Autowired
	private BoxDefectRecordMapper boxDefectRecordMapper;
	
	@Autowired
	private PaintingDailyReportMapper paitingDailyReportMapper;
	
	@Autowired
	private IpqcInspectsMapper ipqcInspectsMapper;
	
	@Autowired
	private OqcCheckMapper oqcCheckmapper;
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public List<AssemblyProductBack> getAssemblyAllByPage(BaseSearch bs) {
		return assembyProductBackMapper.getAssemblyAllByPage(bs);
	}

	@Override
	public List<BatchDefectRecord> getBatchDefectAllByPage(BaseSearch bs) {
		
		return batchDefectRecordMapper.getBatchDefectAllByPage(bs);
	}

	@Override
	public List<FormerProcessFqcCheck> getFormerProcessAllByPage(BaseSearch bs) {
		
		return formerProcessFqcCheckMapper.getFormerProcessAllByPage(bs);
	}

	@Override
	public List<StampingDailyReport> getStampingDailyAllByPage(BaseSearch bs) {
		
		return stampingDailyReportMapper.getStampingDailyAllByPage(bs);
	}

	@Override
	public List<BoxDefectRecord> getBoxDefectAllByPage(BaseSearch bs) {
		
		return boxDefectRecordMapper.getBoxDefectAllByPage(bs);
	}

	@Override
	public List<PaintingDailyReport> getPaintingDailyAllByPage(BaseSearch bs) {
		
		return paitingDailyReportMapper.getPaintingDailyAllByPage(bs);
	}

	@Override
	public List<ProcessAuditRecord> getProcessAuditAllByPage(BaseSearch bs) {
		
		return processAuditRecordMapper.getProcessAuditAllByPage(bs);
	}

	@Override
	public List<QualityImprovementRfp> getQualityAllByPage(BaseSearch bs) {
		
		return QualityImprovementRfpMapper.getQualityAllByPage(bs);
	}

	@Override
	public List<IpqcInspects> getIpqcInspectsAllByPage(BaseSearch bs) {
		
		return ipqcInspectsMapper.getIpqcInspectsAllByPage(bs);
	}

	@Override
	public List<OqcCheck> getOqcCheckAllByPage(BaseSearch bs) {
		
		return oqcCheckmapper.getOqcCheckAllByPage(bs);
	}

	@Override
	public List<AssemblyProductBack> getAssembleRepariedByPage(
			BaseSearch bs) {
		
		return assembyProductBackMapper.getAssembleRepariedByPage(bs);
	}

	@Override
	public List<AssemblyProductBack> getAssemblyList(
			AssemblyProductBack assemblyProductBack) {
		
		return assembyProductBackMapper.getAssemblyList(assemblyProductBack);
	}

	@Override
	public List<FormerProcessFqcCheck> getFqcCheckList(
			FormerProcessFqcCheck formerProcessFqcCheck) {
		
		return formerProcessFqcCheckMapper.getFqcCheckList(formerProcessFqcCheck);
	}

	@Override
	public List<BatchDefectRecord> getBatchDefectList(
			BatchDefectRecord getBatchDefectList) {
		
		return batchDefectRecordMapper.getBatchDefectList(getBatchDefectList);
	}

	@Override
	public List<StampingDailyReport> getStampingDaliy(
			StampingDailyReport stampingDailyReport) {
		
		return stampingDailyReportMapper.getStampingDaliy(stampingDailyReport);
	}

	@Override
	public List<ProcessAuditRecord> getProcessAudit(
			ProcessAuditRecord processAuditRecord) {
		
		return processAuditRecordMapper.getProcessAudit(processAuditRecord);
	}

	@Override
	public List<BoxDefectRecord> getBoxDefect(BoxDefectRecord boxDefectRecord) {
		
		return boxDefectRecordMapper.getBoxDefect(boxDefectRecord);
	}

	@Override
	public List<QualityImprovementRfp> getQualityImp(
			QualityImprovementRfp qualityImprovementRfp) {
		
		return QualityImprovementRfpMapper.getQualityImp(qualityImprovementRfp);
	}

	@Override
	public List<PaintingDailyReport> getPaintingDaily(
			PaintingDailyReport paintingDailyReport) {
		
		return paitingDailyReportMapper.getPaintingDaily(paintingDailyReport);
	}

	@Override
	public List<IpqcInspects> getIpqcInspects(IpqcInspects ipqcInspects) {
		
		return ipqcInspectsMapper.getIpqcInspects(ipqcInspects);
	}

	@Override
	public List<OqcCheck> getOqcCheck(OqcCheck oqcCheck) {
		
		return oqcCheckmapper.getOqcCheck(oqcCheck);
	}

	@Override
	public List<AssemblyProductBack> getIpqcInspects(
			AssemblyProductBack assemblyProductBack) {
		
		return assembyProductBackMapper.getIpqcInspects(assemblyProductBack);
	}

	@Override
	public List<CommonVo> getIpqcDefectType(CommonVo vo) {
		
		return ipqcInspectsMapper.getIpqcDefetctType(vo);
	}

	@Override
	public List<CommonVo> getIpqcScore(CommonVo vo) {
		return ipqcInspectsMapper.getIpqcScore(vo);
	}

	@Override
	public void assemblyExcelOutput(AssemblyProductBack assemblyProductBack,
			 HttpServletRequest request,
			HttpServletResponse response) throws Exception{

		try {
			
			List<AssemblyProductBack> list = assembyProductBackMapper.getAssemblyList(assemblyProductBack);
			String [] CN = {"发生日期",	"工厂",	"发生车间",	"成品工单",	"成品型号",	"成品线",	"零部件名称",	"不良数量",	"不良现象",	"责任判定",	"责任班组1",	"责任班组2",	"责任班组3",	"品质判定人","记录人"};
			List<String[]> excelList = new ArrayList<String[]>();
			for(int i=0; i < list.size() ; i++){
				AssemblyProductBack item = list.get(i);
				String[] itemStr = new String[ CN.length];
				itemStr[0] = DateFormatUtils.format(item.getDateT(), "yyyy-MM-dd");
				itemStr[1] = item.getFactoryS();
				itemStr[2] = item.getAreaS();
				itemStr[3] = item.getOrderNumbers();
				itemStr[4] = item.getProductTypeS();
				itemStr[5] = item.getLineS();
				itemStr[6] = item.getItemNameS();
				itemStr[7] = String.valueOf(item.getDefectQtyI());
				itemStr[8] = item.getDefectS();
				itemStr[9] = item.getDutyS();
				itemStr[10] = item.getDutyGroup1S();
				itemStr[11] = item.getDutyGroup2S();
				itemStr[12] = item.getDutyGroup3S();
				itemStr[13] = item.getCheckManS();
				itemStr[14] = item.getRecordManS();
				excelList.add(itemStr);
			}
			
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			
				ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
				String contentType =  "application/msexcel" ;
				ExcelUtilities.downloadExcel(request, response, filePath, contentType, "组装生产退次表"+fname);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
	}

	@Override
	public void fqcCheckExcelOutput(
			FormerProcessFqcCheck formerProcessFqcCheck,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
try {
			
			List<FormerProcessFqcCheck> list = formerProcessFqcCheckMapper.getFqcCheckList(formerProcessFqcCheck);
			String [] CN = {"发生日期",	"工厂",	"发生车间",	"发生班组",	"追溯号",	"产品型号",	"产品名称",	"抽检频次",	"不良频次",	"抽检数量",	"不良数量",	"不良现象",	"处置",	"责任单位1","责任单位2","责任单位3","记录人"};
			List<String[]> excelList = new ArrayList<String[]>();
			for(int i=0; i < list.size() ; i++){
				FormerProcessFqcCheck item = list.get(i);
				String[] itemStr = new String[ CN.length];
				itemStr[0] = DateFormatUtils.format(item.getDateT(), "yyyy-MM-dd");
				itemStr[1] = item.getFactoryS();
				itemStr[2] = item.getAreaS();
				itemStr[3] = getOccurGroup(item.getGroupS());
				itemStr[4] = item.getTrackingNumberS();
				itemStr[5] = item.getTypeS();
				itemStr[6] = item.getItemNameS();
				itemStr[7] = String.valueOf(item.getTotalQtyI());
				itemStr[8] = String.valueOf(item.getSimpleQtyI());
				itemStr[9] = item.getCheckResultS();
				itemStr[10] = String.valueOf(item.getDefectQtyI());
				itemStr[11] = item.getDefectS();
				itemStr[12] = item.getMethodS();
				itemStr[13] = item.getGroup1S();
				itemStr[14] = item.getGroup2S();
				itemStr[15] = item.getGroup3S();
				itemStr[16] = item.getRecordManS();
				excelList.add(itemStr);
			}
			
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			
				ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
				String contentType =  "application/msexcel" ;
				ExcelUtilities.downloadExcel(request, response, filePath, contentType, "前工序FQC检查表"+fname);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
	}
	

	private String getOccurGroup(String group){
		String groups ="";
		if(group!=null){
			groups = group.substring(0, group.indexOf("_"));
		}
		return groups;
	}

	@Override
	public void batchDefectExcelOutput(BatchDefectRecord batchDefectRecord,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {

			List<BatchDefectRecord> list = batchDefectRecordMapper.getBatchDefectList(batchDefectRecord);
			String [] CN = {"发生日期",	"工厂",	"发生车间",	"产品型号",	"产品名称",	"总数",	"不良数量",	"不良率",	"发现方",	"责任单位1",	"责任单位2",	"责任单位3",	"发生流程节点",	"批量大小","质量后果","质量风险分数","记录人"};
			List<String[]> excelList = new ArrayList<String[]>();
			for(int i=0; i < list.size() ; i++){
				BatchDefectRecord item = list.get(i);
				String[] itemStr = new String[ CN.length];
				itemStr[0] = DateFormatUtils.format(item.getRecordDateT(), "yyyy-MM-dd");
				itemStr[1] = item.getFactoryS();
				itemStr[2] = item.getAreaS();
				itemStr[3] = item.getTypeS();
				itemStr[4] = item.getProductNameS();
				itemStr[5] = String.valueOf(item.getTotalQtyI());
				itemStr[6] = String.valueOf(item.getDefectQtyI());
				itemStr[7] = item.getRateS();
				itemStr[8] = item.getFinderS();
				itemStr[9] = item.getGroup1S();
				itemStr[10] = item.getGroup2S();
				itemStr[11] = item.getGroup3S();
				itemStr[12] = item.getProcessNodeS();
				itemStr[13] = item.getBatchS();
				itemStr[14] = item.getResultS();
				itemStr[15] = String.valueOf(item.getRiskScoreI());
				itemStr[16] = item.getRecordManS();
				excelList.add(itemStr);
			}
			
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			
				ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
				String contentType =  "application/msexcel" ;
				ExcelUtilities.downloadExcel(request, response, filePath, contentType, "过程批量不良记录表"+fname);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
	}

	@Override
	public void stampingDaliyExcelOutput(
			StampingDailyReport stampingDailyReport,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {

			List<StampingDailyReport> list = stampingDailyReportMapper.getStampingDaliy(stampingDailyReport);
			String [] CN = {"发生日期",	"工厂",	"发生车间",	"班组",	"班长",	"检查员",	"产品名称",	"发现工序",	"责任工序",	"责任人",	"检查总数",	"不良数量",	"不良率",	"不良现象","不良原因","处理方式","记录人"};
			List<String[]> excelList = new ArrayList<String[]>();
			for(int i=0; i < list.size() ; i++){
				StampingDailyReport item = list.get(i);
				String[] itemStr = new String[ CN.length];
				itemStr[0] = DateFormatUtils.format(item.getDateT(), "yyyy-MM-dd");
				itemStr[1] = item.getFactoryS();
				itemStr[2] = item.getAreaS();
				itemStr[3] = item.getGroupS();
				itemStr[4] = item.getGroupLeaderS();
				itemStr[5] = item.getCheckManS();
				itemStr[6] = item.getProductNameS();
				itemStr[7] = item.getStepS();
				itemStr[8] = item.getDutyStepS();
				itemStr[9] = item.getDutyManS();
				itemStr[10] = String.valueOf(item.getCheckQtyI());
				itemStr[11] = String.valueOf(item.getDefectQtyI());
				itemStr[12] = item.getRateS();
				itemStr[13] = item.getDefectNameS();
				itemStr[14] = item.getDefectS();
				itemStr[15] = item.getMethodS();
				itemStr[16] = item.getRecordManS();
				excelList.add(itemStr);
			}
			
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			
				ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
				String contentType =  "application/msexcel" ;
				ExcelUtilities.downloadExcel(request, response, filePath, contentType, "冲压质量日报表"+fname);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
			
	}

	@Override
	public void processAuditExcelOutput(ProcessAuditRecord processAuditRecord,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {

			List<ProcessAuditRecord> list = processAuditRecordMapper.getProcessAudit(processAuditRecord);
			String [] CN = {"发生日期",	"工厂",	"发生车间",	"班组",	"班长",	"审核类型",	"审核依据",	"审核发现",	"审核员",	"责任人",	"跟进人",	"是否闭环",	"记录人"};
			List<String[]> excelList = new ArrayList<String[]>();
			for(int i=0; i < list.size() ; i++){
				ProcessAuditRecord item = list.get(i);
				String[] itemStr = new String[ CN.length];
				itemStr[0] = DateFormatUtils.format(item.getCheckDateT(), "yyyy-MM-dd");
				itemStr[1] = item.getFactoryS();
				itemStr[2] = item.getAreaS();
				itemStr[3] = item.getGroupS();
				itemStr[4] = item.getGroupLeaderS();
				itemStr[5] = item.getCheckTypeS();
				itemStr[6] = item.getAuditBasisS();
				itemStr[7] = item.getAuditFindS();
				itemStr[8] = item.getAuditManS();
				itemStr[9] = item.getDutyS();
				itemStr[10] = item.getFollowManS();
				itemStr[11] = item.getIsCloseS();
				itemStr[12] = item.getRecordManS();
				excelList.add(itemStr);
			}
			
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			
				ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
				String contentType =  "application/msexcel" ;
				ExcelUtilities.downloadExcel(request, response, filePath, contentType, "过程审核记录表"+fname);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}		
	}

	@Override
	public void qualityImpExcelOutput(
			QualityImprovementRfp qualityImprovementRfp,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {

			List<QualityImprovementRfp> list = QualityImprovementRfpMapper.getQualityImp(qualityImprovementRfp);
			String [] CN = {"发生日期",	"工厂",	"发生车间",	"班组",	"班长",	"提报人", "提报课题",	"改善课题评分",	"最总评分",	"记录人"};
			List<String[]> excelList = new ArrayList<String[]>();
			for(int i=0; i < list.size() ; i++){
				QualityImprovementRfp item = list.get(i);
				String[] itemStr = new String[ CN.length];
				itemStr[0] = DateFormatUtils.format(item.getRecordDateT(), "yyyy-MM-dd");
				itemStr[1] = item.getFactoryS();
				itemStr[2] = item.getAreaS();
				itemStr[3] = item.getDutyGroupS();
				itemStr[4] = item.getGroupLeaderS();
				itemStr[5] = item.getFinderS();
				itemStr[6] = item.getRfpNameS();
				itemStr[7] = String.valueOf(item.getRfpScoreI());
				itemStr[8] = String.valueOf(item.getTotalScoreI());
				itemStr[9] = item.getRecordManS();
				excelList.add(itemStr);
			}
			
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			
				ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
				String contentType =  "application/msexcel" ;
				ExcelUtilities.downloadExcel(request, response, filePath, contentType, "质量改善课题申报表"+fname);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}		
	}

	@Override
	public void boxDefectExcelOutput(BoxDefectRecord boxDefectRecord,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {

			List<BoxDefectRecord> list = boxDefectRecordMapper.getBoxDefect(boxDefectRecord);
			String [] CN = {"发生日期",	"工厂",	"不良来源",	"故障机型",	"故障机型类别",	"故障零件名称",	"产品编号或范围",	"故障现象",	"故障小类分析",	"发生流程节点",	"批量大小",	"质量后果",	"质量风险系数","责任单位1","责任单位2","责任单位3","记录人"};
			List<String[]> excelList = new ArrayList<String[]>();
			for(int i=0; i < list.size() ; i++){
				BoxDefectRecord item = list.get(i);
				String[] itemStr = new String[ CN.length];
				itemStr[0] = DateFormatUtils.format(item.getRecordDateT(), "yyyy-MM-dd");
				itemStr[1] = item.getFactoryS();
				itemStr[2] = item.getDefectSourceS();
				itemStr[3] = item.getDefectTypeS();
				itemStr[4] = item.getDefectCategoryS();
				itemStr[5] = item.getDefectSpareNameS();
				itemStr[6] = item.getProductNumberS();
				itemStr[7] = item.getDefectS();
				itemStr[8] = item.getDefectReasonS();
				itemStr[9] = item.getProcessNodeS();
				itemStr[10] = item.getLotQtyS();
				itemStr[11] = item.getQualityResultS();
				itemStr[12] = String.valueOf(item.getQualityScoreI());
				itemStr[13] = getOccurGroup(item.getGroup1S());
				itemStr[14] = item.getGroup2S();
				itemStr[15] = item.getGroup3S();
				itemStr[16] = item.getRecordManS();
				excelList.add(itemStr);
			}
			
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			
				ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
				String contentType =  "application/msexcel" ;
				ExcelUtilities.downloadExcel(request, response, filePath, contentType, "市场开箱不良记录表"+fname);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
	}

	@Override
	public void paintingDailyExcelOutput(
			PaintingDailyReport paintingDailyReport,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {

			List<PaintingDailyReport> list = paitingDailyReportMapper.getPaintingDaily(paintingDailyReport);
			String [] CN = {"发生日期",	"工厂",	"发生车间",	"发生班组",	"班长",	"检验员",	"工单",	"产品编号",	"产品名称",	"总数",	"一次检验数",	"合格数",	"不良数","不良现象","记录人"};
			List<String[]> excelList = new ArrayList<String[]>();
			for(int i=0; i < list.size() ; i++){
				PaintingDailyReport item = list.get(i);
				String[] itemStr = new String[ CN.length];
				itemStr[0] = DateFormatUtils.format(item.getDateT(), "yyyy-MM-dd");
				itemStr[1] = item.getFactoryS();
				itemStr[2] = item.getAreaS();
				itemStr[3] = item.getGroupS();
				itemStr[4] = item.getGroupLeaderS();
				itemStr[5] = item.getCheckManS();
				itemStr[6] = item.getOrderNumberS();
				itemStr[7] = item.getProductNumberS();
				itemStr[8] = item.getProductNameS();
				itemStr[9] = String.valueOf(item.getTotalQtyI());
				itemStr[10] = String.valueOf(item.getCheckQtyI());
				itemStr[11] = String.valueOf(item.getQualityQtyI());
				itemStr[12] = String.valueOf(item.getDefectQtyI());
				itemStr[13] = item.getDefectS();
				itemStr[14] = item.getRecordManS();
				excelList.add(itemStr);
			}
			
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			
				ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
				String contentType =  "application/msexcel" ;
				ExcelUtilities.downloadExcel(request, response, filePath, contentType, "喷涂质量日报表"+fname);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
	}

	@Override
	public void ipqcInspectsExcelOutput(IpqcInspects ipqcInspects,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {

			List<IpqcInspects> list = ipqcInspectsMapper.getIpqcInspects(ipqcInspects);
			String [] CN = {"发生日期",	"工厂",	"发生车间",	"发生班组",	"工单号",	"物料名称",	"抽检频次",	"不良频次",	"追溯数量",	"不良数量",	"责任工序",	"判定结果",	"不良现象","责任班组1","责任班组2","责任班组3","发生流程节点","批量大小","质量后果","质量风险分数","判定人"};
			List<String[]> excelList = new ArrayList<String[]>();
			for(int i=0; i < list.size() ; i++){
				IpqcInspects item = list.get(i);
				String[] itemStr = new String[ CN.length];
				itemStr[0] = item.getFindTimeS();
				itemStr[1] = item.getFactoryS();
				itemStr[2] = item.getWorkcenterS();
				itemStr[3] = item.getGroupNameS();
				itemStr[4] = item.getOrderNumberS();
				itemStr[5] = item.getFinderS();
				itemStr[6] = String.valueOf(item.getCheckQtyI()==null ? new BigDecimal(0):item.getCheckQtyI());
				itemStr[7] =  String.valueOf(item.getDefectNum()==null ? new BigDecimal(0):item.getDefectNum());
				itemStr[8] = String.valueOf(item.getFeedingQtyI()==null ? new BigDecimal(0):item.getFeedingQtyI());
				itemStr[9] = String.valueOf(item.getUnquantityQtyI()==null ? new BigDecimal(0):item.getUnquantityQtyI());
				itemStr[10] = item.getStepNameS();
				itemStr[11] = item.getInspectResultS();
				itemStr[12] = item.getDefectTypeS();
				itemStr[13] = item.getGroup1S();
				itemStr[14] = item.getGroup2S();
				itemStr[15] = item.getGroup3S();
				itemStr[16] = getNode(item.getProcessNode());
				itemStr[17] = getNode(item.getBatch());
				itemStr[18] = getNode(item.getResult());
				itemStr[19] = String.valueOf(item.getRiskScore()==null ?  new BigDecimal(0):item.getRiskScore());
				itemStr[20] = item.getInspectManS();
				excelList.add(itemStr);
			}
			
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			
				ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
				String contentType =  "application/msexcel" ;
				ExcelUtilities.downloadExcel(request, response, filePath, contentType, "IPQC巡检不良表"+fname);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
	}

	private String getNode(String str){
		String obj = null;
		if(str!=null && !"".equals(str)){
			obj = str.substring(0,1);
		}
		return obj;
	}

	@Override
	public void oqcCheckExcelOutput(OqcCheck oqcCheck,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {

			List<OqcCheck> list = oqcCheckmapper.getOqcCheck(oqcCheck);
			String [] CN = {"发生日期",	"工厂",	"产线",	"产品线",	"工单号",	"主机条码",	"检验单号",	"抽检内容",	"抽检结果",	"不良现象",	"责任班组1",	"责任班组2",	"责任班组3","抽检人"};
			List<String[]> excelList = new ArrayList<String[]>();
			for(int i=0; i < list.size() ; i++){
				OqcCheck item = list.get(i);
				String[] groups = new String[3];
				
				if(item.getUda5()!=null){
					String[] str = item.getUda5().split(",");
					for(int k =0; k<str.length; k++){
						groups[k] = str[k];
					}
				}
				String[] itemStr = new String[ CN.length];
				itemStr[0] = DateFormatUtils.format(item.getCreationTime(),"yyyy-MM-dd");
				itemStr[1] = item.getFactoryS();
				itemStr[2] = item.getPlineNames();
				itemStr[3] = item.getProductoinLine();
				itemStr[4] = item.getOrderNumber();
				itemStr[5] = item.getObjectName();
				itemStr[6] = item.getUda9();
				itemStr[7] = item.getOpName();
				itemStr[8] = String.valueOf(item.getTestPassed() == 0 ? "不合格" : "合格");
				itemStr[9] = item.getDefectComment();
				itemStr[10] = groups[0];
				itemStr[11] = groups[1] ;
				itemStr[12] = groups[2];
				itemStr[13] = item.getUda0();
				excelList.add(itemStr);
			}
			
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			
				ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
				String contentType =  "application/msexcel" ;
				ExcelUtilities.downloadExcel(request, response, filePath, contentType, "OQC抽检不良表"+fname);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
	}

	@Override
	public void assemblyRepairedExcelOutput(
			AssemblyProductBack assemblyProductBack,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {
            
			List<AssemblyProductBack> list = assembyProductBackMapper.getIpqcInspects(assemblyProductBack);
			String [] CN = {"发生日期",	"发生工厂",	"产线",	"责任班组",	"班组长",	"工单号",	"工序",	"主机条码",	"产品名称",	"国内外",	"不良现象",	"不良部件",	"不良原因","维修工","维修时间","维修方式","审核结论","审核人"};
			List<String[]> excelList = new ArrayList<String[]>();
			for(int i=0; i < list.size() ; i++){
				AssemblyProductBack item = list.get(i);
				String[] itemStr = new String[ CN.length];
				itemStr[0] = item.getCreationTimeZ();
				itemStr[1] = item.getFactoryS();
				itemStr[2] = item.getLineS();
				itemStr[3] = item.getGroupS();
				itemStr[4] = item.getGroupLeader();
				itemStr[5] = item.getOrderNumbers();
				itemStr[6] = item.getCheckStep();
				itemStr[7] = item.getItemNumberS();
				itemStr[8] = item.getProductTypeS();
				itemStr[9] = item.getAtrName();
				itemStr[10] = item.getDefectS();
				itemStr[11] = item.getItemNameS();
				itemStr[12] = item.getDefectSource();
				itemStr[13] = item.getRepariredMan();
				itemStr[14] = item.getDateZ();
				itemStr[15] = item.getRepairedMethod();
				itemStr[16] = item.getCheckResult();
				itemStr[17] = item.getRecordManS();
				excelList.add(itemStr);
			}
			
			String rootPath = request.getSession().getServletContext().getRealPath("/");		
			String fname = System.currentTimeMillis() + ".xls" ;
			System.out.println(rootPath+fname);
			String filePath = rootPath + "/" + fname;
			
				ExcelUtilities.toExcelFileByArray(CN, excelList, filePath);
				String contentType =  "application/msexcel" ;
				ExcelUtilities.downloadExcel(request, response, filePath, contentType, "组装维修日报表"+fname);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
	}

	@Override
	public List<EntityClass> assemblyRepairedbad(AssemblyProductBack assemblyProductBack) {
		return assembyProductBackMapper.getEntityClassbad(assemblyProductBack);
	}
	@Override
	public List<EntityClass> assemblyTypebad(AssemblyProductBack assemblyProductBack) {
		// TODO Auto-generated method stub
		return assembyProductBackMapper.getEntityTypebad(assemblyProductBack);
	}

	@Override
	public List<EntityClass> assemblyRepairedarrange(AssemblyProductBack assemblyProductBack) {
		return assembyProductBackMapper.getEntityClassarrange(assemblyProductBack);
	}

	@Override
	public List<EntityClass> assemblyRepairedarrangetwo(AssemblyProductBack assemblyProductBack) {
		return assembyProductBackMapper.getEntityClassarrangetwo(assemblyProductBack);
	}

	@Override
	public List<EntityClass> stampingdailyreportbad(StampingDailyReport stampingDailyReport) {
		return stampingDailyReportMapper.getStampingDaliybad(stampingDailyReport);
	}

	@Override
	public List<EntityClass> stampingdailyreportarrange(StampingDailyReport stampingDailyReport) {
		return stampingDailyReportMapper.getEntityClassarrange(stampingDailyReport);
	}

	@Override
	public List<EntityClass> stampingdailyreportarrangetwo(StampingDailyReport stampingDailyReport) {
		return stampingDailyReportMapper.getEntityClassarrangetwo(stampingDailyReport);
	}

	@Override
	public List<EntityClass> getPaintingDailybad(PaintingDailyReport paintingDailyReport) {
		return paitingDailyReportMapper.getPaintingDailybad(paintingDailyReport);
	}

	@Override
	public List<EntityClass> paintingDailyarrange(PaintingDailyReport paintingDailyReport) {
		return paitingDailyReportMapper.paintingDailyarrange(paintingDailyReport);
	}

	@Override
	public List<EntityClass> paintingDailyarrangetwo(PaintingDailyReport paintingDailyReport) {
		return paitingDailyReportMapper.paintingDailyarrangetwo(paintingDailyReport);
	}
}
