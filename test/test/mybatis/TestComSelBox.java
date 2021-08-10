package test.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peg.dao.bph.AssemblyProductBackMapper;
import com.peg.dao.bph.CheckItemMapper;
import com.peg.dao.bph.CountPerformanceMonthMapper;
import com.peg.dao.bph.MesDataSumMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CommonSelectedBox;
import com.peg.model.bph.AssemblyProductBack;
import com.peg.model.bph.CheckItem;
import com.peg.model.bph.CountPerformanceMonth;
import com.peg.model.bph.MesDataSum;
import com.peg.model.bph.MonthlyAssessment;
import com.peg.service.CommonSelectedBoxService;
import com.peg.service.CommonServiceI;
import com.peg.service.ShiftGroupServiceI;
import com.peg.service.bph.ComputeScoreServiceI;
import com.peg.service.bph.CountPerformanceMonthServiceI;
import com.peg.service.bph.MonthlyAssessmentServiceI;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class TestComSelBox {
	
    @Autowired
    private CommonSelectedBoxService comSleBoxService;
    
    @Autowired
    private CheckItemMapper checkItem;
   
    @Autowired
	private CommonServiceI commService;
	
	@Autowired
	private MesDataSumMapper mesMapper;
	
	@Autowired
	private ComputeScoreServiceI computeScoreService;
	
	@Autowired
	private ShiftGroupServiceI shiftGroupService;
	
	@Autowired
	private ComputeScoreServiceI computeScore;
	
	@Autowired
	private MonthlyAssessmentServiceI monthlyAssementService;
	
	@Autowired
	private AssemblyProductBackMapper assemblyMappper;
	
	@Autowired
	private CountPerformanceMonthMapper countPerformanceMonthMapper;
	
	@Autowired
	private CountPerformanceMonthServiceI countPerformanceMonthService;
	
	@Autowired
	private AssemblyProductBackMapper assemblyProductBackMapper;
		
    @Test
    public void testQprn(){
    	Map<String,Object> map = new HashMap<String, Object>();
    	map = computeScoreService.QueryQPRNScore();
    	System.out.println(map);
    }
    
    @Test
    public void testCategory(){
    	CheckItem check = new CheckItem();
    	check.setFactory("燃气工厂");
    	List<CheckItem> list = checkItem.getCategory(check);
    	for(CheckItem item : list){
    		System.out.println(item.getShiftGroupCategory());
    	}
    }
    
    @Test
    public void testJson(){
    	Map<String,List<CheckItem>> map = new HashMap<String, List<CheckItem>>();
    	CheckItem check = new CheckItem();
    	check.setFactory("燃气工厂");
    	List<CheckItem> list = checkItem.getCategory(check);
    	map.put("cateList", list);
        JSONObject categoryList = JSONObject.fromObject(map);
        System.out.println(categoryList.toString());
    }

  
    @Test
    public void testGroupScore(){
    	String date = "2015-02";
    	String group = "冲压东01组";
    	List<CommonSelectedBox> list = comSleBoxService.getGroupScore(date, group,null);
    	for(CommonSelectedBox box : list){
    		System.out.println(box.getCol1()+box.getCol2()+box.getCol3());
    	}
    }
    
    @Test
    public void testList (){
    	List<String> alist = new ArrayList<String>();
    	List<String> blist = new ArrayList<String>();
    	alist.add("a");
    	alist.add("b");
    	alist.add("c");
    	blist.add("a");
        alist.removeAll(blist);
        for(String str : alist){
        	System.out.println(str);
        }
    	
    }
    
    @SuppressWarnings("unused")
	@Test
    public void testad(){
    	String date = "2015-02";
    	String group = "冲压东01组";
    	List<CommonSelectedBox> list  = comSleBoxService.getGroupScore(date, group,null);
    	List<CommonSelectedBox> alist = new ArrayList<CommonSelectedBox>();
		List<CommonSelectedBox> blist = new ArrayList<CommonSelectedBox>();
		blist = comSleBoxService.getFixGroupScore(date, group,null);
		for(int i=0; i<blist.size(); i++){
			for(CommonSelectedBox abox : list){
				if(blist.get(i).getCol4().equals(abox.getCol4())){
					blist.remove(i);
				}
			}		
		}
		for(int i=0; i<blist.size(); i++){
			System.out.println(blist.get(i).getCol1()+"=="+blist.get(i).getCol4());
		}
    }
    

    @Test
    public void testFindIndex(){
    	String fac ="电器工厂";
    	String mon = "2015-02";
    	String group = "冲压东01组";
    	String code = "A1";
    	List<MonthlyAssessment> list = monthlyAssementService.fingMonthIndex(mon, fac, group, code);
    	System.out.println(list.get(0).getCol2()+list.size());
    }
    
    @Test
    public void testDeleteIndex(){
    	String fac ="电器工厂";
    	String mon = "2015-02";
    	String group = "冲压东01组";
    	String code = "A1";
    	int t = monthlyAssementService.deleteMonthIndex(mon, fac, group, code);
    	System.out.println(t);
    }
    
    @Test
    public void testOQC(){
    	String startTime = "2015-02-01";
    	String endTime = "2015-02-28";
    	int t = mesMapper.insertOqcCheckSum(startTime, endTime);
    	System.out.println(t);
    }
    
    @Test
    public void testDeleteOQC(){
    	MesDataSum mesDataSum = new MesDataSum();
    	mesDataSum.setQueryMonth("2015-02");
    	int t = mesMapper.deleteOqcCheckSum(mesDataSum);
    	System.out.println(t);
    }
    
    @Test
    public void testDeleteOqcDay(){
    	MesDataSum mesDataSum = new MesDataSum();
    	mesDataSum.setQueryMonth("2015-02-02");
    	int t = mesMapper.deleteOqcCheckSumDay(mesDataSum);
    	System.out.println(t);
    }
    
    @Test
    public void teseOQCDay(){
    	String startTime = "2015-02-01";
    	String endTime = "2015-02-03";
    	int t = mesMapper.insertOqcCheckSumDay(startTime, endTime);
    	System.out.println(t);
    }
    
    @Test
    public void testUpOQC(){
    	MesDataSum mesDataSum = new MesDataSum();
    	mesDataSum.setQueryMonth("2015-02");
    	int t = mesMapper.updateMesDataSumMonthOqcDedectQty(mesDataSum);
    	System.out.println(t);
    }
    @Test
    public void testUpOQCDay(){
    	MesDataSum mesDataSum = new MesDataSum();
    	mesDataSum.setQueryDay("2015-02-02");
    	int t = mesMapper.updateMesDataSumDayOqcDedectQty(mesDataSum);
    	System.out.println(t);
    }
    
    @Test
    public void testUpDownMon(){
    	MesDataSum mesDataSum = new MesDataSum();
    	mesDataSum.setQueryMonth("2015-02");
    	int t = mesMapper.updateMesDataSumMonthDownQtyRiqcA(mesDataSum);
    	int h = mesMapper.updateMesDataSumMonthDownQtyRiqcB(mesDataSum);
    	int k = mesMapper.updateMesDataSumMonthDownQtyDiqc(mesDataSum);
    	System.out.println(t+"=="+h+"=="+k);
    }
    
    @Test
    public void testUpDownDay(){
    	MesDataSum mesDataSum = new MesDataSum();
    	mesDataSum.setQueryDay("2015-02-02");
    	int t = mesMapper.updateMesDataSumDayDownQtyRiqcA(mesDataSum);
    	int k = mesMapper.updateMesDataSumDayDownQtyRiqcB(mesDataSum);
    	int h = mesMapper.updateMesDataSumDayDownQtyDiqc(mesDataSum);
    	System.out.println(t+"--"+k+"=="+h);
    }
    
    @Test
    public void testAssUp(){
    	MesDataSum mesDataSum = new MesDataSum();
    	mesDataSum.setQueryMonth("2015-02");
    	mesDataSum.setStartTime("2015-02-01");
    	mesDataSum.setEndTime("20115-02-28");
    	int t = mesMapper.updateMesDataSumMonthAssembly(mesDataSum);
    	System.out.println(t);
    }
    
    @Test
    public void testUpdateIpqc(){
    	MesDataSum mesDataSum = new MesDataSum();
    	mesDataSum.setQueryMonth("2015-02");
    	mesDataSum.setStartTime("2015-02-01");
    	mesDataSum.setEndTime("2015-02-28");
    	int t = mesMapper.updateMesDataSumMonthInspects(mesDataSum);
    	System.out.println(t);
    }
    
    @Test
    public void testDownLine(){
    	MesDataSum mesDataSum = new MesDataSum();
    	mesDataSum.setQueryMonth("2015-02");
    	mesDataSum.setStartTime("2015-02-01");
    	mesDataSum.setEndTime("2015-02-28");
    	int t = mesMapper.updateMesDataSumMonthDownQtyRiqcA(mesDataSum);
    	int h = mesMapper.updateMesDataSumMonthDownQtyRiqcB(mesDataSum);
    	int k = mesMapper.updateMesDataSumMonthDownQtyDiqc(mesDataSum);
    	System.out.println(t+"=="+h+"=="+k);
    }
    
    @Test
    public void testDelete(){
    	MesDataSum mesDataSum = new MesDataSum();
    	mesDataSum.setQueryMonth("2015-02");
    	mesMapper.deleteAssemblyProductSum( mesDataSum);
    	mesMapper.deleteFormerProcessFqcCheckSum( mesDataSum);	    
    	mesMapper.deleteBatchDefectRecordSum( mesDataSum);	    
    	mesMapper.deleteProcessAuditRecodeSum( mesDataSum);	    
    	mesMapper.deleteQualityImprovementRfpSum( mesDataSum);	    
    	mesMapper.deleteStampingDailyReportSum( mesDataSum);	    
    	mesMapper.deletePaintingDailyReportSum( mesDataSum);	    
    	mesMapper.deleteBoxDefectRecordSum( mesDataSum);
    	mesMapper.deleteQmsdataMonth(mesDataSum);
    	mesMapper.deleteAssembleRepairedSum(mesDataSum); //删除组装投产数
    	mesMapper.deleteOqcCheckSum(mesDataSum);     //删除OQC巡检不良台数

    }
    
    @Test
    public void testInsertAssembly(){
    	String startTime = "2015-01-26";
    	String endTime = "2015-02-25";
    	int t = mesMapper.insertAssemblyProductSum(startTime, endTime);
    	System.out.println(t);
    }
    @Test
    public void testInsertFormer(){
    	String startTime = "2015-01-26";
    	String endTime = "2015-02-25";
    	int t = mesMapper.insertFormerProcessFqcCheckSum(startTime, endTime);
    	System.out.println(t);
    }
    @Test
    public void testInsertbatch(){
    	String startTime = "2015-01-26";
    	String endTime = "2015-02-25";
    	int t = mesMapper.insertBatchDefectRecordSum(startTime, endTime);
    	System.out.println(t);
    }
    @Test
    public void testInsertBox(){
    	String startTime = "2015-01-26";
    	String endTime = "2015-02-25";
    	int t = mesMapper.insertBoxDefectRecordSum(startTime, endTime);
    	System.out.println(t);
    }
    @Test
    public void testInsertQuality(){
    	String startTime = "2015-01-26";
    	String endTime = "2015-02-25";
    	int t = mesMapper.insertQualityImprovementRfpSum(startTime, endTime);
    	System.out.println(t);
    }
    @Test
    public void testInsertProcess(){
    	String startTime = "2015-01-26";
    	String endTime = "2015-02-25";
    	int t = mesMapper.insertProcessAuditRecodeSum(startTime, endTime);
    	System.out.println(t);
    }
    @Test
    public void testInsertPainting(){
    	String startTime = "2015-01-26";
    	String endTime = "2015-02-25";
    	int t = mesMapper.insertPaintingDailyReportSum(startTime, endTime);
    	System.out.println(t);
    }
    @Test
    public void testInsertQmsData(){
    	String startTime = "2015-01-26";
    	String endTime = "2015-02-25";
    	int t = mesMapper.insertQmsdataMonth(startTime, endTime);
    	System.out.println(t);
    }
    @Test
    public void testInsertRepaire(){
    	String startTime = "2015-01-26";
    	String endTime = "2015-02-25";
    	int t = mesMapper.insertAssembleRepairedSum(startTime, endTime);
    	System.out.println(t);
    }
    @Test
    public void testInsertOqcCheck(){
    	String startTime = "2015-01-26";
    	String endTime = "2015-02-25";
    	int t = mesMapper.insertOqcCheckSum(startTime, endTime);
    	System.out.println(t);
    }
    @SuppressWarnings("unused")
	@Test
    public void tesStmpDay(){
    	String startTime = "2015-01-26";
    	String endTime = "2015-02-25";
    	int t = mesMapper.insertStampingDailyReportSum(startTime, endTime);
    }
    
    @Test
    public void testUpdateFqc(){
    	MesDataSum mesDataSum = new MesDataSum();
    	mesDataSum.setQueryMonth("2015-02");
    	mesDataSum.setStartTime("2015-01-26");
    	mesDataSum.setEndTime("2015-02-25");
    	int t = mesMapper.updateMesDataSumMonthFqcCheckSimQty(mesDataSum);
    	System.out.println(t);
    	
    }
    
    @Test
    public void testAssRepaired(){
    	PageParameter page = new PageParameter();
    	BaseSearch bs = new BaseSearch();
    	bs.setPage(page);
    	bs.put("factoryS","电器工厂");
    	bs.put("group", "油烟机01组");
//    	bs.put("startTime", Calendar.getInstance().getTime());
//    	bs.put("endTime", Calendar.getInstance().setTime().getTime());
//    	List<AssemblyProductBack> list = assemblyMappper.getAssembleRepariedAllByPage(bs);
//    	System.out.println(list.size());
    }
    
    @Test
    public void testAssRe(){
    	AssemblyProductBack assemblyProductBack = new AssemblyProductBack();
    	assemblyProductBack.setFactoryS("电器工厂");
    	assemblyProductBack.setGroup("油烟机01组");
    	assemblyProductBack.setDutyGroup1S("2015-02-01");
    	assemblyProductBack.setDutyGroup2S("2015-02-28");
    
    }
    
    @Test
    public void tesupdateIPQC(){
    	MesDataSum mesDataSum = new MesDataSum();
    	mesDataSum.setStartTime("2015-08-26");
    	mesDataSum.setEndTime("2015-09-25");
    	mesDataSum.setQueryMonth("2015-09");
    	int t = mesMapper.updateMesDataSumMonthInspects(mesDataSum);
    	System.out.println(t);
    }
    
    @Test
    public void tesupdateIPQCDay(){
    	MesDataSum mesDataSum = new MesDataSum();
    	mesDataSum.setQueryDay("2015-08-26");
    	int t = mesMapper.updateMesDataSumDayInspects(mesDataSum);
    	System.out.println(t);
    }
    
    @Test
    public void testMonthExcelOut(){
    	MonthlyAssessment month = new MonthlyAssessment();
    	List<MonthlyAssessment> list  = monthlyAssementService.getMonthAssemByExcel(month);
    	for(MonthlyAssessment  mt : list){
    		System.out.println(mt.getCol2());
    	}
    	 
    }
    @Test
    public void testInsertQms(){
    	String startTime ="2015-08-26";
    	String endTime = "2015-09-25";
    	int t = mesMapper.insertQmsdataMonth(startTime, endTime);
    	System.out.println(t);
    }
    
	public CommonSelectedBoxService getComSleBoxService() {
		return comSleBoxService;
	}

	public void setComSleBoxService(CommonSelectedBoxService comSleBoxService) {
		this.comSleBoxService = comSleBoxService;
	}
	
	@Test
	public void testAccount(){
		 
		    CountPerformanceMonth ref = new CountPerformanceMonth();
		    ref.setQueryMonth("2015-09");
			ref.setStartTime("2015-01");
		    
			//1.更新开箱不良数
			int t =	countPerformanceMonthMapper.updateBoxdefectQtyYAJ(ref);
			System.out.println(t);
				
				countPerformanceMonthMapper.updateBoxdefectQtyXDG(ref);
				countPerformanceMonthMapper.updateBoxdefectQtyZJ(ref);
				countPerformanceMonthMapper.updateBoxdefectQtyRSQ(ref);
				countPerformanceMonthMapper.updateBoxdefectQtyWZK(ref);
				
				//2.跟新OQC不良数
				countPerformanceMonthMapper.updateOqcdefectQtyYYJ(ref);
				countPerformanceMonthMapper.updateOqcdefectQtyXDG(ref);
				countPerformanceMonthMapper.updateOqcdefectQtyZJ(ref);
				countPerformanceMonthMapper.updateOqcdefectQtyRSQ(ref);
				countPerformanceMonthMapper.updateOqcdefectQtyWZK(ref);
				//3.更新实绩值
				countPerformanceMonthMapper.updateAccountActualBox(ref);
				countPerformanceMonthMapper.updateAccountTotalBox(ref);
				countPerformanceMonthMapper.updateAccountActualOqc(ref);
				countPerformanceMonthMapper.updateAccountTotalOqc(ref);
				
				//跟新合格率
				countPerformanceMonthMapper.updatePaintingQty(ref);        //更新喷涂一次合格率（不良数，总数）
				countPerformanceMonthMapper.updateAssembleDefectQty(ref);  //更新组装一次合格率（不合格数）
				countPerformanceMonthMapper.updateAssembleTotalQty(ref);   //更新组装一次合格率（总数）
				
				//更新实绩合格率
				countPerformanceMonthMapper.updateAccountActualFitRate(ref);
				//跟新累计合格率
				countPerformanceMonthMapper.updateAccountTotalFitRate(ref);
			
		
		}
	
	@Test
	public void testCount(){
		 CountPerformanceMonth ref = new CountPerformanceMonth();
		 ref.setQueryMonth("2015-09");
		 countPerformanceMonthService.CaculateCountPerformaceMonth(ref);
	}
	
	@Test
	public void testAssembleExcel(){
		AssemblyProductBack assemblyProductBack = new AssemblyProductBack();
		assemblyProductBack.setFactoryS("电器工厂");
		assemblyProductBack.setAreaS("冲压车间");
		List<AssemblyProductBack> list = assemblyProductBackMapper.getAssemblyList(assemblyProductBack);
		for(AssemblyProductBack a : list){
			System.out.println(a.getFactoryS()+a.getAreaS()+a.getDateT());
		}
		
	}
	@SuppressWarnings("unused")
	@Test
	public void loadFAPG(){
		
		//工厂
		List<CommonSelectedBox> flist = comSleBoxService.getCommonFactory();  
//		request.getSession().setAttribute("lfactory", flist);
		
		//车间
		Map<String,List<CommonSelectedBox>> amap = new HashMap<String, List<CommonSelectedBox>>();
		List<CommonSelectedBox> alist = comSleBoxService.getAreaS();    
		Iterator<CommonSelectedBox> iter = alist.iterator();
		while(iter.hasNext()){
			CommonSelectedBox box = iter.next();
			String fac = box.getFactory();
			List<CommonSelectedBox> comList = new ArrayList<CommonSelectedBox>();
			if(!amap.containsKey(fac)){
				comList.add(box);
				amap.put(fac, comList);
			}
			amap.get(fac).add(box);
		}
		System.out.println(amap);
//		request.getSession().setAttribute("larea", amap);
		
	}
}
