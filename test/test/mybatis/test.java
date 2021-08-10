package test.mybatis;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peg.dao.bph.GroupMapper;
import com.peg.dao.bph.IpqcInspectsMapper;
import com.peg.dao.bph.NMesDataSumMapper;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.bph.Group;
import com.peg.model.bph.MesDataSum;
import com.peg.service.RepairRateInputServiceI;
import com.peg.service.ShiftGroupServiceI;
import com.peg.service.bph.BphCommonServiceI;
import com.peg.service.bph.CheckItemServiceI;
import com.peg.service.bph.ComputeScoreServiceI;
import com.peg.service.bph.GroupCategorySerivceI;
import com.peg.service.bph.GroupServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class test {
	@Autowired
	private NMesDataSumMapper mesDataSumMapper;
	
	@Autowired
	private ComputeScoreServiceI compute;
	
	@Autowired
	private ShiftGroupServiceI shiftGroupService;
	
	@Autowired
	private ComputeScoreServiceI computeScore;
	
	@Autowired
	private CheckItemServiceI checkItemService;
		
	@Autowired
	private IpqcInspectsMapper ipqcMap;
	
	@Autowired
	private RepairRateInputServiceI repairRateInputService;
	
	@Autowired
	private GroupServiceI groupService;
	
	@Autowired
	private BphCommonServiceI bphCommonService;
	
	@Autowired
	private GroupMapper groupMapper;
	
	@Autowired
	private GroupCategorySerivceI groupCategoryMapper;
	
	@Test
	public void testGroup(){
		BaseSearch bs = new BaseSearch();
		PageParameter page = new PageParameter();
		bs.setPage(page);
		List<Group> list = groupService.getMonthAllByPage(bs);
		for(Group g : list){
			System.out.println(g.getGroupName()+g.getUigroupCategory().getCategory());
		}
	}
	
	


	@Test 
	public void testDay(){
		Date dNow = new Date();   //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance();  //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -2);  //设置为前两天
		dBefore = calendar.getTime();   //得到前一天的时间

		String startTime =DateFormatUtils.format( dBefore,"yyyy-MM-dd");
		String endTime = DateFormatUtils.format(Calendar.getInstance().getTime(), "yyyy-MM-dd");
		System.out.println(startTime+"  "+ endTime);
	}


	

	public ComputeScoreServiceI getComputeScore() {
		return computeScore;
	}

	public void setComputeScore(ComputeScoreServiceI computeScore) {
		this.computeScore = computeScore;
	}

	
	
	
	@Test
	public void insertMesData() throws Exception{
		MesDataSum mesDataSum = new MesDataSum();
		mesDataSum.setQueryMonth("2016-02");
		bphCommonService.insertMesDataSumMonth(mesDataSum);
	}
	
	@Test
	public void testGroupGetByMonth(){
		List<Group> list = groupMapper.getMonthAllByMonth("2015-12");
		for(Group group : list){
			System.out.println(group.getGroupName()+"---"+group.getFactory());
		}
	}
	
	@Test
	public void updateMesDataSumMonthAssembly(){
	int t =	mesDataSumMapper.updateMesDataSumMonthInspects("2016-01-26", "2016-02-25", "2016-02");
	System.out.println(t);
	}
	
	@Test
	public void deleteGc(){
		int t = groupCategoryMapper.delete(88L);
		System.out.println(t);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void insertMesDataSumDay(){
		MesDataSum mesDataSum = new MesDataSum();
		mesDataSum.setStartTime("2016-05-01");
		mesDataSum.setEndTime("2016-05-26");
		String startTime = "2016-08-01";
		String endTime = "2015-08-26";
//		mesDataSumMapper.deleteMesDataSumDay(startTime, endTime);
//		mesDataSumMapper.insertMesDataSumDay(startTime, endTime);
//		bphCommonService.insertMesDataSumDay(mesDataSum);
//		mesDataSumMapper.updateMesDataSumDayAssembly("2015-08-01", "2015-08-26");
//		mesDataSumMapper.updateMesDataSumDayAssRepaired("2015-08-01", "2015-08-26");
//		mesDataSumMapper.updateMesDataSumDayOqcDedectQty("2015-08-01", "2015-08-26");
//		mesDataSumMapper.updateMesDataSumMonthAssRepaired("2015-08-01", "2015-08-26", "2015-08");
		mesDataSumMapper.updateMesDataSumMonthOqcDedectQty("2015-08-01", "2015-08-26", "2015-08");
	}


}	

