package com.peg.service.impl.bph;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.base.exception.QmsException;
import com.peg.dao.bph.BphCommonMapper;
import com.peg.dao.bph.IndexScroeMapper;
import com.peg.dao.bph.ItemScoreMapper;
import com.peg.dao.bph.MonthlyAssessmentMapper;
import com.peg.dao.bph.NMesDataSumMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.EntityClass;
import com.peg.model.bph.BphCommonVo;
import com.peg.model.bph.Contrast;
import com.peg.model.bph.CountPerformanceMonth;
import com.peg.model.bph.Group;
import com.peg.model.bph.Index;
import com.peg.model.bph.IndexScroe;
import com.peg.model.bph.Item;
import com.peg.model.bph.ItemScore;
import com.peg.model.bph.MesDataSum;
import com.peg.service.bph.BphCommonServiceI;
import com.peg.service.bph.CountPerformanceMonthServiceI;
import com.peg.service.bph.NComputeScoreServiceI;
import com.peg.service.sumscore.NComputeContext;

/**
 * 用于公共查询或操作
 * @author song
 *
 */
@Service("bphCommonService")
public class BhpCommonServiceImpl implements BphCommonServiceI{

	@Autowired
	private BphCommonMapper commonMapper;
	
	@Autowired
	private NMesDataSumMapper mesDataSumMapper;
	
	@Autowired
	private IndexScroeMapper indexScroeMapper;
	
	@Autowired 
	private ItemScoreMapper itemScoreMapper;
	
	@Autowired
	private NComputeScoreServiceI computeScoreService;
	
	@Autowired
	private MonthlyAssessmentMapper monthlyAssessmentMapper;
	
	@Autowired
	private CountPerformanceMonthServiceI countPerformanceMonthService;

	/**
	 * 运算锁
	 */
	static boolean BPH_LOCK = false;
	
	synchronized boolean isLock(){
		return BPH_LOCK;
	}
	
	synchronized void lock(){
		BPH_LOCK = true;
	}
	
	synchronized void unLock(){
		BPH_LOCK = false;
	}

	/**
	 * 插入MES数据总和表月份
	 */
	@SuppressWarnings("unused")
	@Override
	public int insertMesDataSumMonth(MesDataSum mesDataSum) throws Exception{
		 if(isLock())
         {
           throw new Exception("程序正在运行，请稍后再试！");
         } else{
        	 lock();
        	 try{
        		    String startTime = null;
        			String endTime = null;
        			String queryMonth = null;
        	       //删除所选月份数据
        		    mesDataSumMapper.deleteQmsdataMonth(mesDataSum);
//        		    mesDataSumMapper.deleteOqcCheckSum(mesDataSum);     //删除OQC巡检不良台数
        		    //插入所选月份数据
        		    queryMonth = mesDataSum.getQueryMonth();
                	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                	Calendar cal = Calendar.getInstance();       	
            		cal.setTime(sdf.parse(queryMonth));
            		cal.add(Calendar.MONTH, 0);
            		cal.set(Calendar.DAY_OF_MONTH, 1);
            	    startTime = DateFormatUtils.format(cal, "yyyy-MM-dd");
            		//cal.add(Calendar.MONTH, 0);
            	    //获取前一月的最后一天
            	 //   Calendar cale =Calendar.getInstance();
            	//    cale.add(Calendar.MONTH, 1); 
            		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
            	    endTime = DateFormatUtils.format(cal, "yyyy-MM-dd");
            		mesDataSum.setStartTime(startTime);
            		mesDataSum.setEndTime(endTime);
        			mesDataSumMapper.insertQmsdataMonth(startTime ,endTime);
//        			mesDataSumMapper.insertOqcCheckSum(startTime, endTime);          //新增OQC巡检不良数
        			//更新MES数据
        			mesDataSumMapper.deleteMesDataSumMonth(mesDataSum);
        			int t = mesDataSumMapper.insertMesDataSumMonth(mesDataSum);
    			    if(t>0){		    
    			    	 //生产退次数
            		   	mesDataSumMapper.updateMesDataSumMonthAssembly(startTime,endTime ,queryMonth);
            		   	//5M1E
            		   	mesDataSumMapper.updateMesDataSumMonthBatch(startTime,endTime ,queryMonth );
            		   	//5M1E(IQC)
            		   	mesDataSumMapper.updateMesDataSumMonthBatchForIqc(startTime,endTime ,queryMonth);
            		   	//市场开箱
            		   	mesDataSumMapper.updateMesDataSumMonthBox(startTime,endTime ,queryMonth );
            		   	//FQC
            		    mesDataSumMapper.updateMesDataSumMonthFormer(startTime,endTime ,queryMonth );
            		    //喷涂质量
            		    mesDataSumMapper.updateMesDataSumMonthPaint(startTime,endTime ,queryMonth );
            		    //过程审核
            		    mesDataSumMapper.updateMesDataSumMonthProcess(startTime,endTime ,queryMonth  );
            		    //质量改善
            		    mesDataSumMapper.updateMesDataSumMonthQuality(startTime,endTime ,queryMonth  );
            		    //冲压
            		    mesDataSumMapper.updateMesDataSumMonthStmp(startTime,endTime ,queryMonth  );
            		    //喷涂退次
            		    mesDataSumMapper.updateMesDataSumMonthPaintingProduct(startTime,endTime ,queryMonth);
            		    //精加工直通率
            		    mesDataSumMapper.updateMesDataSumMonthFinishing(startTime,endTime ,queryMonth);
            			//跟新组装维修数
            			mesDataSumMapper.updateMesDataSumMonthAssRepaired(startTime,endTime ,queryMonth);
            			//更新IPQC巡检
            			mesDataSumMapper.updateMesDataSumMonthInspects(startTime, endTime, queryMonth);
            			//更新OQC巡检不良台数
            			mesDataSumMapper.updateMesDataSumMonthOqcDedectQty(startTime, endTime, queryMonth);
            		    
            		    //从qms_data_month更新到MES总和表月度组装投产数
            			mesDataSumMapper.updateMesDataSumMonthQmsData(mesDataSum);    
            		    mesDataSumMapper.updateMesDataSumMonthQmsAssDStamp( mesDataSum); //电器冲压		    
            			mesDataSumMapper.updateMesDataSumMonthQmsAssRStampA(mesDataSum); //燃气冲压A		    
            			mesDataSumMapper.updateMesDataSumMonthQmsAssRStampB( mesDataSum); //燃气冲压B			    
            			mesDataSumMapper.updateMesDataSumMonthQmsAssRSpay(mesDataSum); //燃气喷涂
            			mesDataSumMapper.updateMesDataSumMonthQmsAssDComputer(mesDataSum);//电脑板车间

            			//更新组装停线次数
            			mesDataSumMapper.updateMesDataSumMonthDownQtyRiqcA(mesDataSum);//燃气IQC班组A
            			mesDataSumMapper.updateMesDataSumMonthDownQtyRiqcB(mesDataSum);//燃气IQC班组B
            			mesDataSumMapper.updateMesDataSumMonthDownQtyDiqc(mesDataSum);//电器IQC班组
            			
        			   //删除指标得分月表和项目得分月表
        			   int ac = mesDataSumMapper.deleteIndexScoreMonth(mesDataSum);
        			   int bj =  mesDataSumMapper.deleteItemScoreMonth(mesDataSum);
        			   //插入指标得分月表和项目得分月表
        				List<MesDataSum> list = mesDataSumMapper.getMesDataAll(mesDataSum);
        				Map<String,Group> groupMap = computeScoreService.QueryMonthAssessment(mesDataSum);
        				if(list != null && !list.isEmpty()){
        					for(MesDataSum mesData : list){
        						try {
        							NComputeContext contex = new NComputeContext(mesData, groupMap,computeScoreService);
        							contex.compute();
        						} catch (QmsException e) {
        							e.printStackTrace();
        						}		
        					}
        				}
    			    }
    				    //更新年度指标
    					CountPerformanceMonth ref = new CountPerformanceMonth();
    					ref.setStartDate(mesDataSum.getStartTime());
    					ref.setEndDate(mesDataSum.getEndTime());
    					ref.setQueryMonth(queryMonth);
    					countPerformanceMonthService.CaculateCountPerformaceMonth(ref);
    					
        	 }catch(Exception e){
        		 e.printStackTrace();
        	 }finally{
        		 unLock();
        	 }
         }
	  return 0;
	}



	@Override
	public List<BphCommonVo> getShiftGroupPerFormanceByGroup(BphCommonVo vo) {
		
		return commonMapper.getShiftGroupPerFormanceByGroup(vo);
	}

	@Override
	public List<BphCommonVo> getShiftGroupPerformanceList( BaseSearch bs) {
		
		return commonMapper.getShiftGroupPerformanceList(bs);
	}

	@Override
	public List<BphCommonVo> getIndexScoreAllByPage(BaseSearch bs) {
		
		return commonMapper.getIndexScoreAllByPage(bs);
	}

	@Override
	public List<IndexScroe> getIndexScoreMonthListByPage(BaseSearch bs) {
		
		return indexScroeMapper.getIndexScoreMonthListByPage(bs);
	}

	@Override
	public List<IndexScroe> getSumIndexScore(BaseSearch bs) {
		
		return indexScroeMapper.getSumIndexScore(bs);
	}

	@Override
	public List<IndexScroe> getIndexScoreByMonth(BaseSearch  bs) {
		
		return indexScroeMapper.getIndexScoreByMonth(bs);
	}


	@Override
	public int insertQmsdataMonth(String startTime, String endTime) {
		
		return mesDataSumMapper.insertQmsdataMonth(startTime, endTime);
	}

	@Override
	public List<ItemScore> sumItemScore(ItemScore record) {
		
		return itemScoreMapper.sumItemScore(record);
		
	}

	@Override
	public List<IndexScroe> getMonthIndexScoreListByPage(BaseSearch bs) {
		
		return indexScroeMapper.getMonthIndexScoreListByPage(bs);
	}
	


	@Override
	public List<ItemScore> sumItemScoreByGroup(ItemScore record) {
		
		return itemScoreMapper.sumItemScoreByGroup(record);
	}

	@Override
	public List<BphCommonVo> getShiftGroupPerformanceByMonth(BaseSearch bs) {
		
		return commonMapper.getShiftGroupPerformanceByMonth(bs);
	}

	@Override
	public List<BphCommonVo> sumItemScoreByYear(BphCommonVo vo) {
		
		return commonMapper.sumItemScoreByYear(vo);
	}

	@Override
	public List<BphCommonVo> sumItemScoreBySelectMonth(BphCommonVo vo) {
		
		return commonMapper.sumItemScoreBySelectMonth(vo);
	}

	@Override
	public List<BphCommonVo> selectGroupRank(BphCommonVo vo) {
		
		return commonMapper.selectGroupRank(vo);
	}

	@Override
	public int insertQmsData(MesDataSum mesDataSum) {
		try{
			mesDataSumMapper.insertQmsDataAssembly(mesDataSum);
			mesDataSumMapper.insertQmsDataStamping(mesDataSum);
			mesDataSumMapper.insertQmsDataPainting(mesDataSum);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}


/**
 * 插入mes汇总表天
 */
	@Override
	public int insertMesDataSumDay(MesDataSum mesDataSum) throws Exception{
		if(isLock()){
			throw new Exception("程序正在运行，请稍后！");
		}
		try{
			String startTime = mesDataSum.getStartTime();
			String endTime = mesDataSum.getEndTime();
		 
	       //更新MES数据
		   mesDataSumMapper.deleteMesDataSumDay(startTime, endTime);
		   int t = mesDataSumMapper.insertMesDataSumDay(startTime, endTime);
		   if(t>0){		    
			   //生产退次数
			   	mesDataSumMapper.updateMesDataSumDayAssembly(startTime,endTime );
			   	//5M1E
			   	mesDataSumMapper.updateMesDataSumDayBatch(startTime,endTime );
			   	//5M1E(IQC)
			   	mesDataSumMapper.updateMesDataSumDayBatchForIqc(startTime,endTime );
			   	//市场开箱
			   	mesDataSumMapper.updateMesDataSumDayBox(startTime,endTime );
			   	//FQC
			    mesDataSumMapper.updateMesDataSumDayFormer(startTime,endTime  );
			    //喷涂质量
			    mesDataSumMapper.updateMesDataSumDayPaint(startTime,endTime );
			    //过程审核
			    mesDataSumMapper.updateMesDataSumDayProcess(startTime,endTime  );
			    //质量改善
			    mesDataSumMapper.updateMesDataSumDayQuality(startTime,endTime  );
			    //冲压
			    mesDataSumMapper.updateMesDataSumDayStmp(startTime,endTime  );
			    //喷涂退次
			    mesDataSumMapper.updateMesDataSumDayPaintingProduct(startTime,endTime );
			    //精加工直通率
			    mesDataSumMapper.updateMesDataSumDayFinishing(startTime,endTime);
				//跟新组装维修数
				mesDataSumMapper.updateMesDataSumDayAssRepaired(startTime,endTime );
				//更新IPQC巡检
				mesDataSumMapper.updateMesDataSumDayInspects(startTime, endTime);
				//更新OQC巡检不良台数
				mesDataSumMapper.updateMesDataSumDayOqcDedectQty(startTime, endTime);
			    
			    //从qms_data_month更新到MES总和表月度组装投产数
				mesDataSumMapper.updateMesDataSumDayQmsData(startTime, endTime);    
			    mesDataSumMapper.updateMesDataSumDayQmsAssDStamp( startTime, endTime); //电器冲压		    
				mesDataSumMapper.updateMesDataSumDayQmsAssRStampA(startTime, endTime); //燃气冲压A		    
				mesDataSumMapper.updateMesDataSumDayQmsAssRStampB( startTime, endTime); //燃气冲压B			    
				mesDataSumMapper.updateMesDataSumDayQmsAssRSpay(startTime, endTime); //燃气喷涂
				mesDataSumMapper.updateMesDataSumDayQmsAssDComputer(startTime, endTime);//电脑板车间
		   }

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			unLock();
		}
		
	  return 0;
	}	
	@Override
	public List<Item> getItemList(IndexScroe is) {
		return commonMapper.getItemName(is);
	}



	@Override
	public List<Index> getIndexList(List<String> str) {
		return commonMapper.getIndexName(str);
	}



	@Override
	public List<Group> getGroupList(IndexScroe is) {
		return commonMapper.getgroupName(is);
	}



	@Override
	public List<Contrast> getContrast(List<String> grouplist,
			List<String> itemlist, List<String> indexlist, String monthly) {
		return commonMapper.getgrouplist(grouplist, itemlist, indexlist, monthly);
	}



	@Override
	public List<Group> getGroupList2(List<String> list) {
		return commonMapper.getgroupName2(list);
	}



	@Override
	public List<EntityClass> getEntity(String indexkey, String[] strgroupname,String monthly) {
		return commonMapper.getentitylist(indexkey, strgroupname,monthly);
	}



	@Override
	public List<Item> getallitemlist(IndexScroe vo) {
		return commonMapper.getnullitemlist(vo);
	}



	@Override
	public List<Index> getallindexlist(List<String> item) {
		return commonMapper.getnullindexlist(item);
	}



	@Override
	public List<Index> getindexlistkey(List<String> indexkey) {
		return commonMapper.getindexlistkey(indexkey);
	}



	@Override
	public List<Item> getitemlistkey(List<String> itemkey) {
		return commonMapper.getitemlistkey(itemkey);
	}
	
	

	
}
