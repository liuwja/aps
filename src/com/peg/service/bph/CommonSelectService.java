package com.peg.service.bph;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

/**
 * 公共查询接口
 * @author Administrator
 *
 */
public interface CommonSelectService {

	/**
	 * 组装生产退次表查询
	 * 
	 * @param bs
	 * @return
	 */
	List<AssemblyProductBack> getAssemblyAllByPage(BaseSearch bs);
	
	/**
	 * 前工序FQC查检表查询
	 * @param bs
	 * @return
	 */
	List<FormerProcessFqcCheck> getFormerProcessAllByPage(BaseSearch bs);
	
	/**
	 * 过程批量不良记录表查询
	 * @param bs
	 * @return
	 */
	List<BatchDefectRecord> getBatchDefectAllByPage(BaseSearch bs);
	
	/**
	 * 冲压质量日报表查询
	 * @param bs
	 * @return
	 */
	List<StampingDailyReport> getStampingDailyAllByPage(BaseSearch bs);
	
	/**
	 * 过程审核记录表查询
	 * @param bs
	 * @return
	 */
	List<ProcessAuditRecord> getProcessAuditAllByPage(BaseSearch bs);
	
	/**
	 * 质量改善课题申报表查询
	 * @param bs
	 * @return
	 */
	List<QualityImprovementRfp> getQualityAllByPage(BaseSearch bs);
	
	/**
	 * 市场开箱不良记录单查询
	 * @param bs
	 * @return
	 */
	List<BoxDefectRecord> getBoxDefectAllByPage(BaseSearch bs);
	
	/**
	 * 喷涂质量日报表查询
	 * @param bs
	 * @return
	 */
	List<PaintingDailyReport> getPaintingDailyAllByPage(BaseSearch bs);
	/**
	 * 喷涂质量日报表不良现象
	 * @param bs
	 * @return
	 */
	List<EntityClass> getPaintingDailybad(PaintingDailyReport paintingDailyReport);
	
	/**
	 * 喷涂质量日报表序列图基准值与目标值
	 * @param paintingDailyReport
	 * @return
	 */
	List<EntityClass> paintingDailyarrange(PaintingDailyReport paintingDailyReport);
	/**
	 * 喷涂质量日报表序列图实际值
	 * @param paintingDailyReport
	 * @return
	 */
	List<EntityClass> paintingDailyarrangetwo(PaintingDailyReport paintingDailyReport);
	
	/**
	 * IPQC巡检表
	 * @param bs
	 * @return
	 */
	List<IpqcInspects> getIpqcInspectsAllByPage(BaseSearch bs);
	
	/**
	 * OQC抽检
	 * @method: getOqcCheckAllByPage() -by fjt
	 * @TODO:  
	 * @param bs
	 * @return List<OqcCheck>
	 */
	List<OqcCheck> getOqcCheckAllByPage(BaseSearch bs);
	
	/**
	 * 组装维修数
	 * @method: getAssembleReparied() -by fjt
	 * @TODO:  
	 * @param assemblyReparied
	 * @return List<AssemblyProductBack>
	 */
	List<AssemblyProductBack> getAssembleRepariedByPage(BaseSearch bs);

	
	List<AssemblyProductBack> getAssemblyList(
			AssemblyProductBack assemblyProductBack);

	List<FormerProcessFqcCheck> getFqcCheckList(
			FormerProcessFqcCheck formerProcessFqcCheck);

	List<BatchDefectRecord> getBatchDefectList(
			BatchDefectRecord batchDefectRecord);

	List<StampingDailyReport> getStampingDaliy(
			StampingDailyReport stampingDailyReport);

	List<ProcessAuditRecord> getProcessAudit(
			ProcessAuditRecord processAuditRecord);

	List<BoxDefectRecord> getBoxDefect(BoxDefectRecord boxDefectRecord);

	List<QualityImprovementRfp> getQualityImp(
			QualityImprovementRfp qualityImprovementRfp);

	List<PaintingDailyReport> getPaintingDaily(
			PaintingDailyReport paintingDailyReport);

	List<IpqcInspects> getIpqcInspects(IpqcInspects ipqcInspects);

	List<OqcCheck> getOqcCheck(OqcCheck oqcCheck);

	List<AssemblyProductBack> getIpqcInspects(
			AssemblyProductBack assemblyProductBack);
	
	List<CommonVo> getIpqcDefectType(CommonVo vo);
    
	List<CommonVo> getIpqcScore(CommonVo vo);
	/**
	 * 组装退次excel导出
	 * @param assemblyProductBack
	 * @param request
	 * @param response
	 */
	void assemblyExcelOutput(AssemblyProductBack assemblyProductBack,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	/**
	 * FQC退次表excel导出
	 * @param formerProcessFqcCheck
	 * @param request
	 * @param response
	 */
	void fqcCheckExcelOutput(FormerProcessFqcCheck formerProcessFqcCheck, 
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	/**
	 * 过程批量不良记录表excel导出
	 * @param batchDefectRecord
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	void batchDefectExcelOutput(BatchDefectRecord batchDefectRecord,
			HttpServletRequest request, HttpServletResponse response)throws Exception;
	
	/**
	 * 冲压质量日报表excel导出
	 * @param stampingDailyReport
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	void stampingDaliyExcelOutput(StampingDailyReport stampingDailyReport, 
			HttpServletRequest request, HttpServletResponse response)throws Exception;
	/**
	 * 过程审核记录表excel导出
	 * @param processAuditRecord
	 * @param request
	 * @param response
	 */
	void processAuditExcelOutput(ProcessAuditRecord processAuditRecord, 
			HttpServletRequest request, HttpServletResponse response)throws Exception;
	/**
	 * 质量课题改善报表excel导出
	 * @param qualityImprovementRfp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	void qualityImpExcelOutput(QualityImprovementRfp qualityImprovementRfp,
			HttpServletRequest request, HttpServletResponse response)throws Exception;
	/**
	 * 市场开箱不良excel导出
	 * @param boxDefectRecord
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	void boxDefectExcelOutput(BoxDefectRecord boxDefectRecord,
			HttpServletRequest request, HttpServletResponse response)throws Exception;
	/**
	 * 喷涂质量日报表导出excel
	 * @param paintingDailyReport
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	void paintingDailyExcelOutput(PaintingDailyReport paintingDailyReport,
			HttpServletRequest request, HttpServletResponse response)throws Exception;
	/**
	 * IPQC巡检表excel导出
	 * @param ipqcInspects
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	void ipqcInspectsExcelOutput(IpqcInspects ipqcInspects, 
			HttpServletRequest request, HttpServletResponse response)throws Exception;
	/**
	 * OQC抽检表excel导出
	 * @param oqcCheck
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	void oqcCheckExcelOutput(OqcCheck oqcCheck, 
			HttpServletRequest request, HttpServletResponse response)throws Exception;
	/**
	 * 组装维修日报表excel导出
	 * @param assemblyProductBack
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	void assemblyRepairedExcelOutput(AssemblyProductBack assemblyProductBack,
			HttpServletRequest request, HttpServletResponse response)throws Exception;
	/**
	 * 组装退次不良现象统计
	 * @param assemblyProductBack
	 * @return
	 */
	List<EntityClass> assemblyRepairedbad(AssemblyProductBack assemblyProductBack);
	/**
	 * 组装退次不良型号统计
	 * @param assemblyProductBack
	 * @return
	 */
	List<EntityClass> assemblyTypebad(AssemblyProductBack assemblyProductBack);
	
	/**
	 * 组装退次时间序列图 基准值和目标值
	 * @param assemblyProductBack
	 * @return
	 */
	List<EntityClass> assemblyRepairedarrange(AssemblyProductBack assemblyProductBack);
	
	/**
	 * 组装退次时间序列图 实际值
	 * @param assemblyProductBack
	 * @return
	 */
	List<EntityClass> assemblyRepairedarrangetwo(AssemblyProductBack assemblyProductBack);
	
	/**
	 * 冲压不良率
	 * @return
	 */
	List<EntityClass> stampingdailyreportbad(StampingDailyReport stampingDailyReport);
	
	/**
	 * 冲压一次不良率时间序列图 基准值和目标值
	 * @param assemblyProductBack
	 * @return
	 */
	List<EntityClass> stampingdailyreportarrange(StampingDailyReport stampingDailyReport);
	
	/**
	 *  冲压一次不良率时间序列图 实际值
	 * @param assemblyProductBack
	 * @return
	 */
	List<EntityClass> stampingdailyreportarrangetwo(StampingDailyReport stampingDailyReport);
}
