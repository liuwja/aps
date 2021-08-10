package test.other;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.peg.model.system.SysOperateLog;
import com.peg.qms.controller.VoiceCustomerEchatrsController;

public class Json {

	
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> tooltip = new HashMap<String,Object>();
		Map<String,Object> axisPointer = new HashMap<String,Object>();
		
		tooltip.put("trigger","axis");
		tooltip.put("axisPointer","axis");
		axisPointer.put("type", "shadow");
		tooltip.put("axisPointer", axisPointer);
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String s  = mapper.writeValueAsString(tooltip);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(test1());
		
	}
	
	
	private static String test1(){
		VoiceCustomerEchatrsController v = new VoiceCustomerEchatrsController();
		List<String>  xValue = new ArrayList<String>();
		xValue.add("测试1");
		xValue.add("测试2");
		xValue.add("测试3");
		List<Long> yValue = new ArrayList<Long>();
		yValue.add(100L);
		yValue.add(200L);
		yValue.add(300L);
		String s = v.fJson("测试", xValue, yValue);
		return s;
	}
}
