package com.peg.dao.bph;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.MesDataSum;

/**插入各种总和表
 * @Class: MesDataSumMapper @TODO:
 */

public interface NMesDataSumMapper {
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
    
    int insertOqcCheckSum(@Param("startDay")String startDay,@Param("endDay")String endDay);//插入OQC巡检表
    
    /**
     * 根据IPQC巡检表（人，机，法，环）对应的组装班组更新过程审核中的盲点测试次数
     */
    int updateProcessAuditSum(String startTime, String endTime); 
    
    /**
     * 
     * @method: insertAssemblyProductSum() -by fjt
     * @TODO:  删除MES子表月份
     */
  
   
    
    int deleteOqcCheckSum(MesDataSum mesData); //删除OQC巡检月
    
    int deleteIpqcInspectSum(MesDataSum mesData); //删除IPQC巡检月

    /**
     * 
     * @method: insertAssemblyProductSum() -by fjt
     * @TODO:  插入MES 子表天
     */
  
    
    int insertOqcCheckSumDay(String startTime,String endTime); //插入OQC巡检表天
    
    int insertIpqcInspectSumDay(String startTime,String endTime); //插入IPQC巡检表天
    /**
     * 根据IPQC巡检表（人，机，法，环）对应的组装班组更新过程审核中的盲点测试次数
     */
    int updateProcessAuditSumDay(String startTime,String endTime);
 
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
     * @method: updateMesDataSumMonth() -by fjt
     * @TODO:   更新Mes数据总和表月份
     * @param mesDataSum
     * @return int
     */
    //组装生产退次表
    int updateMesDataSumMonthAssembly(@Param("startDay")String startDay,@Param("endDay")String endDay,
    		@Param("queryMonth")String queryMonth);
    //FQC抽检表
    int updateMesDataSumMonthFormer(@Param("startDay")String startDay,@Param("endDay")String endDay,
    		@Param("queryMonth")String queryMonth);
    //过程审核表
    int updateMesDataSumMonthProcess(@Param("startDay")String startDay,@Param("endDay")String endDay,
    		@Param("queryMonth")String queryMonth);
    //质量改善附加分数
    int updateMesDataSumMonthQuality(@Param("startDay")String startDay,@Param("endDay")String endDay,
    		@Param("queryMonth")String queryMonth);
    //5M1E巡检
    int updateMesDataSumMonthBatch(@Param("startDay")String startDay,@Param("endDay")String endDay,
    		@Param("queryMonth")String queryMonth);
    //5M1E巡检（IQC）
    int updateMesDataSumMonthBatchForIqc(@Param("startDay")String startDay,@Param("endDay")String endDay,
    		@Param("queryMonth")String queryMonth);
    //冲压质量日报
    int updateMesDataSumMonthStmp(@Param("startDay")String startDay,@Param("endDay")String endDay,
    		@Param("queryMonth")String queryMonth);
    //喷涂质量日报
    int updateMesDataSumMonthPaint(@Param("startDay")String startDay,@Param("endDay")String endDay,
    		@Param("queryMonth")String queryMonth);
    //市场开箱
    int updateMesDataSumMonthBox(@Param("startDay")String startDay,@Param("endDay")String endDay,
    		@Param("queryMonth")String queryMonth);
    //更新组装维修数  
    int updateMesDataSumMonthAssRepaired(@Param("startDay")String startDay,@Param("endDay")String endDay,
    		@Param("queryMonth")String queryMonth);
    //更新喷涂退次数
    int updateMesDataSumMonthPaintingProduct(@Param("startDay")String startDay,@Param("endDay")String endDay,
    		@Param("queryMonth")String queryMonth);
  //更新精加工直通率
    int updateMesDataSumMonthFinishing(@Param("startDay")String startDay,@Param("endDay")String endDay,
    		@Param("queryMonth")String queryMonth);
    // 从at_inspects巡检表更新MES总和表
    int updateMesDataSumMonthInspects(@Param("startDay")String startDay,@Param("endDay")String endDay,
    		@Param("queryMonth")String queryMonth);
    //更新OQC巡检不良台数
    int updateMesDataSumMonthOqcDedectQty(@Param("startDay")String startDay,@Param("endDay")String endDay,
    		@Param("queryMonth")String queryMonth);
  
    
    //从qms_data_month更新到MES总和表月度组装投产数
    int updateMesDataSumMonthQmsData(MesDataSum mesDataSum);
    
    int updateMesDataSumMonthQmsAssDStamp(MesDataSum mesDataSum); //电器冲压
    
    int updateMesDataSumMonthQmsAssRStampA(MesDataSum mesDataSum); //燃气冲压A
    
    int updateMesDataSumMonthQmsAssRStampB(MesDataSum mesDataSum); //燃气冲压B
    
    int updateMesDataSumMonthQmsAssRSpay(MesDataSum mesDataSum); //燃气喷涂
    //更新组装投产数电脑版车间（消毒柜）
    int updateMesDataSumMonthQmsAssDComputer(MesDataSum mesDataSum);
    
    
    //更新组装停线次数
    int updateMesDataSumMonthDownQtyRiqcB(MesDataSum mesDataSum);//燃气IQC班组B
    int updateMesDataSumMonthDownQtyRiqcA(MesDataSum mesDataSum);//燃气IQC班组A
    int updateMesDataSumMonthDownQtyDiqc(MesDataSum mesDataSum); //电器IQC班组
    

 
  
    
    /**
     * 
     * @method: getMesDataAll() -by fjt
     * @TODO:  查询所有的MES数据
     * @return List<MesDataSum>
     */
    List<MesDataSum> getMesDataAll(MesDataSum mesDataSum);
    

    
    /**
     * 删除指标得分月
     */
    int deleteIndexScoreMonth(MesDataSum mesDataSum);
    
    /**
     * 删除项目得分月
     */
    int deleteItemScoreMonth(MesDataSum mesDataSum);
    

    
    /**
     * 插入qms_data表
     */
    int insertQmsDataAssembly(MesDataSum mesDataSum); //组装投产数
    int insertQmsDataStamping(MesDataSum mesDataSum); //冲压入库数
    int insertQmsDataPainting(MesDataSum mesDataSum); //喷涂挂件数
    
    
    /**
     * 
     * @method: insertMesDataSum() -by fjt
     * @TODO:  插入MES数据总和表日
     * @return int
     */
    int insertMesDataSumDay(@Param("startDay") String startDay,@Param("endDay")String endDay);
    
    /*
     * 删除MES数据总和表日天表(传入数据类型为（数组）)
     */
    int deleteMesDataSumDay(@Param("startDay") String startDay,@Param("endDay")String endDay);
 
    /**
    *
    * @method:
    * @TODO:   更新Mes数据总和表天
    */
    //组装生产退次表
    int updateMesDataSumDayAssembly(@Param("startDay")String startDay,@Param("endDay")String endDay);
    //FQC抽检表
    int updateMesDataSumDayFormer(@Param("startDay")String startDay,@Param("endDay")String endDay);
    //过程审核表
    int updateMesDataSumDayProcess(@Param("startDay")String startDay,@Param("endDay")String endDay);
    //质量改善附加分数
    int updateMesDataSumDayQuality(@Param("startDay")String startDay,@Param("endDay")String endDay);
    //5M1E巡检
    int updateMesDataSumDayBatch(@Param("startDay")String startDay,@Param("endDay")String endDay);
    //5M1E巡检（IQC）
    int updateMesDataSumDayBatchForIqc(@Param("startDay")String startDay,@Param("endDay")String endDay);
    //冲压质量日报
    int updateMesDataSumDayStmp(@Param("startDay")String startDay,@Param("endDay")String endDay);
    //喷涂质量日报
    int updateMesDataSumDayPaint(@Param("startDay")String startDay,@Param("endDay")String endDay);
    //市场开箱
    int updateMesDataSumDayBox(@Param("startDay")String startDay,@Param("endDay")String endDay);
    //更新组装维修数  
    int updateMesDataSumDayAssRepaired(@Param("startDay")String startDay,@Param("endDay")String endDay);
    //更新喷涂退次数
    int updateMesDataSumDayPaintingProduct(@Param("startDay")String startDay,@Param("endDay")String endDay);
    //更新精加工直通率
    int updateMesDataSumDayFinishing(@Param("startDay")String startDay,@Param("endDay")String endDay);
   // 从at_inspects巡检表更新MES总和表
    int updateMesDataSumDayInspects(@Param("startDay")String startDay,@Param("endDay")String endDay);
    
    //从qms_data_month更新到MES总和表月度组装投产数
    int updateMesDataSumDayQmsData(@Param("startDay")String startDay,@Param("endDay")String endDay);
    
    int updateMesDataSumDayQmsAssDStamp(@Param("startDay")String startDay,@Param("endDay")String endDay); //电器冲压
    
    int updateMesDataSumDayQmsAssRStampA(@Param("startDay")String startDay,@Param("endDay")String endDay); //燃气冲压A
    
    int updateMesDataSumDayQmsAssRStampB(@Param("startDay")String startDay,@Param("endDay")String endDay); //燃气冲压B
    
    int updateMesDataSumDayQmsAssRSpay(@Param("startDay")String startDay,@Param("endDay")String endDay); //燃气喷涂
    //更新组装投产数电脑版车间（消毒柜）
    int updateMesDataSumDayQmsAssDComputer(@Param("startDay")String startDay,@Param("endDay")String endDay);
    
    //更新OQC巡检不良台数
    int updateMesDataSumDayOqcDedectQty(@Param("startDay")String startDay,@Param("endDay")String endDay);
}