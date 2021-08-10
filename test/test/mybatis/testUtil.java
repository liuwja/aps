package test.mybatis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peg.dao.bph.MesDataSumMapper;
import com.peg.model.LaterSumtime;
import com.peg.model.bph.MesDataSum;
import com.peg.service.CommonServiceI;
import com.peg.service.LaterSumtimeServiceI;
import com.peg.service.ShiftGroupServiceI;
import com.peg.service.bph.CheckItemServiceI;
import com.peg.service.bph.ComputeScoreServiceI;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class testUtil {
	
	@Autowired
	private CommonServiceI commService;
	
	@Autowired
	private MesDataSumMapper mesDataSumMapper;
	
	@Autowired
	private ComputeScoreServiceI compute;
	
	@Autowired
	private ShiftGroupServiceI shiftGroupService;
	
	@Autowired
	private ComputeScoreServiceI computeScore;
	
	@Autowired
	private CheckItemServiceI checkItemService;
	
	@Autowired
	private LaterSumtimeServiceI laterSumtimeService;
		
	@Test
	public void testNull(){
		String name = "0.229123";
		double d = Double.parseDouble(name);
		System.out.println(d);
	}
	@Test
	public void test_1(){
		LaterSumtime laterTime = new LaterSumtime();
		laterTime.setSumMonth("2016-05");
		laterSumtimeService.insert(laterTime);
	}
	
	@Test
	public void testSimpleQty(){
		MesDataSum mesDataSum = new MesDataSum();
		mesDataSum.setQueryMonth("2015-02");
		int t = mesDataSumMapper.updateMesDataSumMonthFqcCheckSimQty(mesDataSum);
		System.out.println(t);
	}
	
	@Test
	public void tesSimpleQtyDay(){
		MesDataSum mesDataSum = new MesDataSum();
		mesDataSum.setQueryDay("2015-02-02");
		int t = mesDataSumMapper.updateMesDataSumDayFqcCheckSimQty(mesDataSum);
		System.out.println(t);
	}
	@Test
	public void testIndexOf(){
		String index = "B1_D1_C5_A11_A10";
		System.out.println(index.indexOf("A1")+"A1".substring(1,"A1".length()));
	}
	
    @Test
    public void testTime() throws ParseException{
    	String queryMonth = "2015-02";
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    	Calendar cal = Calendar.getInstance();       	
		cal.setTime(sdf.parse(queryMonth));
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 26);
		String startTime = DateFormatUtils.format(cal, "yyyy-MM-dd");
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 25); 
		String endTime = DateFormatUtils.format(cal, "yyyy-MM-dd");
        System.out.println(startTime);
        System.out.println(endTime);
    }
    
    @SuppressWarnings("unused")
	@Test
    public void testComtains(){
    	String str = "A1_A2_C1_D11";
    	String str1 = "A11_A12_C12_C13";
    	String str2 = "A1";
    	System.out.println(str1.contains(str2));
    }
	
	public CommonServiceI getCommService() {
		return commService;
	}

	public void setCommService(CommonServiceI commService) {
		this.commService = commService;
	}

	public ComputeScoreServiceI getComputeScore() {
		return computeScore;
	}

	public void setComputeScore(ComputeScoreServiceI computeScore) {
		this.computeScore = computeScore;
	}

	@Test
	public void testMonth(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String queryMonth = DateFormatUtils.format(cal.getTime(), "yyyy-MM");
		System.out.println(queryMonth);
	}
	
}
