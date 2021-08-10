package test.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.MarketRepairRecord;
import com.peg.model.TimeMatrx;
import com.peg.service.MarketRepairRecordServiceI;
import com.peg.service.TimeMatrxServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class TimeMatrxTest {

	@Autowired
	private TimeMatrxServiceI timeMatrxService;
	@Autowired
	private MarketRepairRecordServiceI marketRepairRecordService;
	
	@SuppressWarnings("unused")
	@Test
	public void mytest(){
		PageParameter page = new PageParameter();
		MarketRepairRecord repair = new MarketRepairRecord();
		List<String> partTypeList = new ArrayList<String>();
		repair.setPartTypeList(partTypeList);
		List<MarketRepairRecord> list = marketRepairRecordService.findAllRepair(page, repair);
		System.out.println("");
	}
	@Test
	public void testGetTimeMatrx(){
		TimeMatrx record = new TimeMatrx();
		record.setMachineType("灶具");
		Map<String, TimeMatrx> map = timeMatrxService.getAllByMachineType(record);
		for(Map.Entry<String,TimeMatrx>entry:map.entrySet()){
			String key = entry.getKey().toString();
			   String value = entry.getValue().getMachineType();
			   System.out.println("key=" + key + " value=" + value);
			
		}
		
	}
}
