package test.mybatis;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peg.dao.bph.OnlinelookupMapper;
import com.peg.dao.part.TestInstanceMapper;
import com.peg.model.part.TestInstance;
import com.peg.service.bph.ComputeScoreServiceI;
import com.peg.service.part.TestInstanceServiceI;
import com.peg.web.util.DateEditor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class ComputeScoreTest
{
	@Autowired
	private ComputeScoreServiceI computeScoreService;
	
	@Autowired
	private TestInstanceMapper testInstanceMapper;
	
	@Autowired
	private OnlinelookupMapper onlinelookupMapper;
	
	@Autowired
	private TestInstanceServiceI testInstanceService;
	
	@Test
	public void test01(){
		TestInstance testInstance = new TestInstance();
		testInstance.setAnalysisType("1");
		testInstance.setDateType("年");
		testInstance.setDateT("2016");
		TestInstance t = testInstanceMapper.getTotalQty(testInstance);
		System.out.println(t.getTotalLot()+"--"+t.getDefectLot());
	}

	@Test
	public void test02() throws Exception{
	        Calendar cal = Calendar.getInstance();       	
			cal.setTime(new Date());
			cal.add(Calendar.DAY_OF_MONTH, -1);
			Date startTime = cal.getTime();
			Date endTime = cal.getTime();
			System.out.println(DateEditor.formatDate(startTime, "yyyy-MM-dd")+"-----"+DateEditor.formatDate(endTime, "yyyy-MM-dd"));
			testInstanceService.updateTestInstance(DateEditor.formatDate(startTime, "yyyy-MM-dd"), DateEditor.formatDate(endTime, "yyyy-MM-dd"));
	}
}
