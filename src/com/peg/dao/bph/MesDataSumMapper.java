package com.peg.dao.bph;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.MesDataSum;

/**插入各种总和表
 * @Class: MesDataSumMapper @TODO:
 */

public interface MesDataSumMapper {
	/**
	 * 查询所有数据
	 * @param bs
	 * @return
	 */
	List<MesDataSum> findAllByPage(BaseSearch bs);
    /**
     * 
     * @method: insertAssemblyProductSum() -by fjt
     * @TODO:  插入MES子表月份
     */
    int insertAssemblyProductSum(String startTime,String endTime);

    int insertFormerProcessFqcCheckSum(String startTime,String endTime);
    
    int insertBatchDefectRecordSum(String startTime,String endTime);
    
    int insertProcessAuditRecodeSum(String startTime,String endTime);
    
    int insertQualityImprovementRfpSum(String startTime,String endTime);
    
    int insertStampingDailyReportSum(String startTime,String endTime);
    
    int insertPaintingDailyReportSum(String startTime,String endTime);
    
    int insertBoxDefectRecordSum(String startTime,String endTime);
    
    int insertAssembleRepairedSum(String startTime,String endTime); //插入组装维修日报表
    
    int insertOqcCheckSum(String startTime,String endTime);//插入OQC巡检表
    
    int insertIpqcInspectSum(String startTime, String endTime);  // 插入IPQC巡检记录总和表
    /**
     * 根据IPQC巡检表（人，机，法，环）对应的组装班组更新过程审核中的盲点测试次数
     */
    int updateProcessAuditSum(String startTime, String endTime); 
    
    /**
     * 
     * @method: insertAssemblyProductSum() -by fjt
     * @TODO:  删除MES子表月份
     */
    int deleteAssemblyProductSum(MesDataSum mesData);

    int deleteFormerProcessFqcCheckSum(MesDataSum mesData);
    
    int deleteBatchDefectRecordSum(MesDataSum mesData);
    
    int deleteProcessAuditRecodeSum(MesDataSum mesData);
    
    int deleteQualityImprovementRfpSum(MesDataSum mesData);
    
    int deleteStampingDailyReportSum(MesDataSum mesData);
    
    int deletePaintingDailyReportSum(MesDataSum mesData);
    
    int deleteBoxDefectRecordSum(MesDataSum mesData);
    
    int deleteAssembleRepairedSum(MesDataSum mesData); 
    
    int deleteOqcCheckSum(MesDataSum mesData); //删除OQC巡检月
    
    int deleteIpqcInspectSum(MesDataSum mesData); //删除IPQC巡检月

    /**
     * 
     * @method: insertAssemblyProductSum() -by fjt
     * @TODO:  插入MES 子表天
     */
    int insertAssemblyProductSumDay(String startTime,String endTime);

    int insertFormerProcessSumDay(String startTime,String endTime);
    
    int insertBatchDefectSumDay(String startTime,String endTime);
    
    int insertProcessAuditSumDay(String startTime,String endTime);
    
    int insertQualityImprovementSumDay(String startTime,String endTime);
    
    int insertStampingDailyReportSumDay(String startTime,String endTime);
    
    int insertPaintingDailySumDay(String startTime,String endTime);
    
    int insertBoxDefectRecordSumDay(String startTime,String endTime);
    
    int insertAssembleRepairedSumDay(String startTime,String endTime); //插入组装维修日报表
    
    int insertOqcCheckSumDay(String startTime,String endTime); //插入OQC巡检表天
    
    int insertIpqcInspectSumDay(String startTime,String endTime); //插入IPQC巡检表天
    /**
     * 根据IPQC巡检表（人，机，法，环）对应的组装班组更新过程审核中的盲点测试次数
     */
    int updateProcessAuditSumDay(String startTime,String endTime);
    /**
     * 
     * @method: insertAssemblyProductSum() -by fjt
     * @TODO:  删除MES 子表天
     */
    int deleteAssemblyProductSumDay(MesDataSum mesData);

    int deleteFormerProcessSumDay(MesDataSum mesData);
    
    int deleteBatchDefectSumDay(MesDataSum mesData);
    
    int deleteProcessAuditSumDay(MesDataSum mesData);
    
    int deleteQualityImprovementSumDay(MesDataSum mesData);
    
    int deleteStampingDailyReportSumDay(MesDataSum mesData);
    
    int deletePaintingDailySumDay(MesDataSum mesData);
    
    int deleteBoxDefectRecordSumDay(MesDataSum mesData);
    
    int deleteAssembleRepairedSumDay(MesDataSum mesData);
    
    int deleteOqcCheckSumDay(MesDataSum mesData);//删除OQC巡检表天
    
    int deleteIpqcInspectSumDay(MesDataSum mesData); //删除IPQC巡检表天
    /**
     *
     * @method: insertQmsdataMonth() -by fjt
     * @TODO:   插入qms_data_month表月份
     * @param startTime
     */
   // int insertQmsDataByMonth(MesDataSum mesDataSum);
    
    int insertQmsdataMonth(String startTime, String endTime);
    
    /**
    *
    * @method: insertQmsdataMonth() -by fjt
    * @TODO:   删除qms_data_month表月份
    * @param startTime
    */
   int deleteQmsdataMonth(MesDataSum mesData);
    
    /**
     * 
     * @method: insertMesDataSum() -by fjt
     * @TODO:  插入MES数据总和表月份
     * @return int
     */
    int insertMesDataSumMonth( MesDataSum mesDataSum);
    
    int deleteMesDataSumMonth(MesDataSum mesDataSum);
    /**
     * 
     * @method: insertMesDataSum() -by fjt
     * @TODO:  插入MES数据总和表日
     * @return int
     */
    int insertMesDataSumDay(MesDataSum mesDataSum);
    
    int deleteMesDataSumDay(MesDataSum mesDataSum);
    /**
     *
     * @method: updateMesDataSumMonth() -by fjt
     * @TODO:   更新Mes数据总和表月份
     * @param mesDataSum
     * @return int
     */
    int updateMesDataSumMonthAssembly(MesDataSum mesDataSum);
    
    int updateMesDataSumMonthFormer(MesDataSum mesDataSum);
    
    int updateMesDataSumMonthProcess(MesDataSum mesDataSum);
    
    int updateMesDataSumMonthQuality(MesDataSum mesDataSum);
    
    int updateMesDataSumMonthBatch(MesDataSum mesDataSum);
    
    int updateMesDataSumMonthStmp(MesDataSum mesDataSum);
    
    int updateMesDataSumMonthPaint(MesDataSum mesDataSum);
    
    int updateMesDataSumMonthBox(MesDataSum mesDataSum);
    
    int updateMesDataSumMonthQmsData(MesDataSum mesDataSum);
    
    //从qms_data_month更新到MES总和表月度组装投产数
    int updateMesDataSumMonthQmsAssDStamp(MesDataSum mesDataSum); //电器冲压
    
    int updateMesDataSumMonthQmsAssRStampA(MesDataSum mesDataSum); //燃气冲压A
    
    int updateMesDataSumMonthQmsAssRStampB(MesDataSum mesDataSum); //燃气冲压B
    
    int updateMesDataSumMonthQmsAssRSpay(MesDataSum mesDataSum); //燃气喷涂
    
    int updateMesDataSumMonthQmsAssDSpayFa(MesDataSum mesDataSum); //电器喷涂，精加工
    
   // 从at_inspects巡检表更新MES总和表
    int updateMesDataSumMonthInspects(MesDataSum mesDataSum);
  //更新组装维修数  
    int updateMesDataSumMonthAssRepaired(MesDataSum mesDataSum);
    //更新Fqc抽样批次数
    int updateMesDataSumMonthFqcCheckSimQty(MesDataSum mesDataSum);
    //更新OQC巡检不良台数
    int updateMesDataSumMonthOqcDedectQty(MesDataSum mesDataSum);
    
    //更新组装停线次数
    int updateMesDataSumMonthDownQtyRiqcB(MesDataSum mesDataSum);//燃气IQC班组B
    int updateMesDataSumMonthDownQtyRiqcA(MesDataSum mesDataSum);//燃气IQC班组A
    int updateMesDataSumMonthDownQtyDiqc(MesDataSum mesDataSum); //电器IQC班组
    
    /**
     * 
     * @method: updateMesDataSumMonth() -by fjt
     * @TODO:  更新Mes数据总和表单日
     * @param mesDataSum
     * @return int
     */
    int updateMesDataSumDayAssembly(MesDataSum mesDataSum);
    
    int updateMesDataSumDayFormer(MesDataSum mesDataSum);
    
    int updateMesDataSumDayProcess(MesDataSum mesDataSum );
    
    int updateMesDataSumDayQuality(MesDataSum mesDataSum);
    
    int updateMesDataSumDayBatch(MesDataSum mesDataSum);
    
    int updateMesDataSumDayStmp(MesDataSum mesDataSum);
    
    int updateMesDataSumDayPaint(MesDataSum mesDataSum);
    
    int updateMesDataSumDayBox(MesDataSum mesDataSum);
    
    int updateMesDataSumDayQmsData(MesDataSum mesDataSum);
    
    //从qms_data更新到MES总和表天组装投产数
    int updateMesDataSumDayQmsAssDStamp(MesDataSum mesDataSum); //电器冲压
    
    int updateMesDataSumDayQmsAssRStampA(MesDataSum mesDataSum); //燃气冲压A
    
    int updateMesDataSumDayQmsAssRStampB(MesDataSum mesDataSum); //燃气冲压B
    
    int updateMesDataSumDayQmsAssRSpay(MesDataSum mesDataSum); //燃气喷涂
    
    int updateMesDataSumDayQmsAssDSpayFa(MesDataSum mesDataSum); //电器喷涂，精加工
    
    // 从at_inspects巡检表更新MES总和表天
    int updateMesDataSumDayInspects(MesDataSum mesDataSum);
    //更新组装维修数天
    int updateMesDataSumDayAssRepaired(MesDataSum mesDataSum);
    //更新Fqc抽样批次数 
    int updateMesDataSumDayFqcCheckSimQty(MesDataSum mesDataSum);
    //更新OQC巡检不良台数
    int updateMesDataSumDayOqcDedectQty(MesDataSum mesDataSum);
    
    //跟新组装停线次数
    int updateMesDataSumDayDownQtyRiqcA(MesDataSum mesDataSum);//燃气IQC班组A
    int updateMesDataSumDayDownQtyRiqcB(MesDataSum mesDataSum);//燃气IQC班组B
    int updateMesDataSumDayDownQtyDiqc(MesDataSum mesDataSum); //电器IQC班组
    
    /**
     *
     * @method: AssemblyExistList() -by fjt
     * @TODO:   查询MES数据子表天数据是否存在
     * @param mesDataSum
     * @return List<MesDataSum>
     */
    List<MesDataSum> AssemblyExistList(MesDataSum mesDataSum);  
    
    
    
    /**
    *
    * @method: AssemblyExistList() -by fjt
    * @TODO:   查询MES数据子表数据月是否存在
    * @param mesDataSum
    * @return List<MesDataSum>
    */
   List<MesDataSum> AssemblyMonthExistList(MesDataSum mesDataSum);   
    
    /**
     * 
     * @method: boxDefectSource() -by fjt
     * @TODO:  查询各班组市场开箱不良记录表不良来源次数月份
     * @param mesDataSum
     * @return List<MesDataSum>
     */
    List<MesDataSum> boxDefectSource(MesDataSum mesDataSum);
    
    /**
     *
     * @method: boxDefectSource() -by fjt
     * @TODO:   查询各班组市场开箱不良记录表不良来源次数天
     * @param mesDataSum
     * @return List<MesDataSum>
     */
    List<MesDataSum> boxDefectSourceByDay(MesDataSum mesDataSum);
    
    /**
     * 
     * @method: getMesDataAll() -by fjt
     * @TODO:  查询所有的MES数据
     * @return List<MesDataSum>
     */
    List<MesDataSum> getMesDataAll(MesDataSum mesDataSum);
    
    /**
     * 
     * @method: processAuditRecordByMonth() -by fjt
     * @TODO:  查询各班组过程审核记录表月度责任班组的审核次数
     * @param mesDataSum
     * @return List<MesDataSum>
     */
    List<MesDataSum> processAuditRecordByMonth(MesDataSum mesDataSum);
    
    /**
     * 
     * @method: processAuditRecordByMonth() -by fjt
     * @TODO: 查询各班组过程审核记录表责任班组的审核次数天 
     * @param mesDataSum
     * @return List<MesDataSum>
     */
    List<MesDataSum> processAuditRecordByDay(MesDataSum mesDataSum);
    
    /**
     * 
     * @method: batchDefectByMonth() -by fjt
     * @TODO:  查询各班组过程批量不良记录责任班组的月度质量风险分数之和
     * @param mesDataSum
     * @return List<MesDataSum>
     */
    List<MesDataSum> batchDefectByMonth(MesDataSum mesDataSum);
    
    /**
     * 
     * @method: batchDefectByMonth() -by fjt
     * @TODO:  查询各班组过程批量不良记录责任班组的质量风险分数之和天
     * @param mesDataSum
     * @return List<MesDataSum>
     */
    List<MesDataSum> batchDefectByDay(MesDataSum mesDataSum);
    
    /**
     * 
     * @method: getMesDataDayAll() -by fjt
     * @TODO:  得到所有MES数据天表
     * @param mesDataSum
     * @return List<MesDataSum>
     */
    List<MesDataSum> getMesDataDayAll(MesDataSum mesDataSum);
    
    /**
     * 删除指标得分月
     */
    int deleteIndexScoreMonth(MesDataSum mesDataSum);
    
    /**
     * 删除项目得分月
     */
    int deleteItemScoreMonth(MesDataSum mesDataSum);
    
    /**
     * 删除指标得分天
     */
    int deleteIndexScoreDay(MesDataSum mesDataSum);
    
    /**
     * 插入qms_data表
     */
    int insertQmsDataAssembly(MesDataSum mesDataSum); //组装投产数
    int insertQmsDataStamping(MesDataSum mesDataSum); //冲压入库数
    int insertQmsDataPainting(MesDataSum mesDataSum); //喷涂挂件数
    
    /**
     *查询各班组IPQC巡检表责任班组的月度质量风险分数之和月份
     */
    List<MesDataSum> getIpqcByMonth(MesDataSum mesDataSum);
    
    /**
     *查询各班组IPQC巡检表责任班组的月度质量风险分数之和天
     */
    List<MesDataSum> getIpqcByDay(MesDataSum mesDataSum);
    
}