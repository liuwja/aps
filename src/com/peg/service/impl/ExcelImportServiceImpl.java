package com.peg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peg.model.bph.Group;
import com.peg.model.bph.Index;
import com.peg.model.bph.MonthAssessment;
import com.peg.service.ExcelImportService;
import com.peg.service.bph.GroupServiceI;
import com.peg.service.bph.IndexServiceI;
import com.peg.service.bph.ItemServiceI;
import com.peg.service.bph.MonthAssessmentServiceI;
@Service("excelImportService")
public class ExcelImportServiceImpl implements ExcelImportService{
	@Autowired
	private ItemServiceI itemService;
	@Autowired
	private IndexServiceI indexService;
	@Autowired
	private GroupServiceI groupService;
	@Autowired
	private MonthAssessmentServiceI monthAssessmentService;
	
	private SimpleDateFormat format = new  SimpleDateFormat("yyyy-MM");
	

	@Override
	public String[] checkSettingMonthlyImportExcel(List<String[]> dataList) {
		String result = "" ;
		StringBuffer buffer = null;
		int idx = 0;
		int count = 0 ;
		buffer = new StringBuffer();
		for (String[] arryString:dataList) {
			idx++;
			//用户导入固定格式
			//月份	  工厂     车间	  班组类别     班组     项目代码	 考核项目    项目比例     指标代码    考核指标    指标比例    是否关键指标   基准值        目标值     

			String month = arryString[0].trim() ;
			if (month == null || month.trim().isEmpty())
			{
				count++;
				if(count>=2){
					break;
				}
				continue;
			}
			try{
				if(arryString[0].isEmpty()){
					throw new Exception("月份为空！");
				}
				if(!arryString[0].isEmpty()){
					try{
						format.parse(arryString[0].trim());
					}catch(Exception e){
						throw new Exception("月份格式错误！请按照‘yyyy-MM’格式录入");
					}
				}
				if(arryString[4].isEmpty()){
					throw new Exception("班组为空！");
				}
				if(!arryString[4].isEmpty()){
					Group group = groupService.getGroupByFag(arryString[1].trim(), arryString[2].trim(),arryString[3].trim(), arryString[4].trim());
					if(group == null){
						throw new Exception("请确定MES系统中'"+arryString[3].trim()+"'类别下该班组名称是否存在！");
					}
					List<MonthAssessment> list = monthAssessmentService.findeByGroupAndMonth(group.getGroupKey(),arryString[0]);
					if(!list.isEmpty()){
						throw new Exception("班组"+arryString[4]+arryString[0]+"已设定月度基准！");
					}
				}
				if(arryString[5].isEmpty()){
					throw new Exception("项目代码为空！");
				}
				if(!arryString[7].isEmpty()){
					Double.parseDouble(arryString[7].trim());
				}
				if(arryString[8].isEmpty()){
					throw new Exception("指标代码为空！");
				}
				if(!arryString[8].isEmpty()){
					Index index = null;
						 index = indexService.findByIndexCode(arryString[1].trim(),arryString[2].trim(),arryString[3].trim(),arryString[8].trim());
					if(index==null){
						throw new Exception("班组类别“"+arryString[3].trim()+"”不存在该指标代码:"+arryString[8].trim());
					}
				}
				if(!arryString[10].isEmpty()){
					Double.parseDouble(arryString[10].trim());
				}
				if(!arryString[12].isEmpty()){
					Double.parseDouble(arryString[12].trim());
				}
				if(!arryString[13].isEmpty()){
					Double.parseDouble(arryString[13].trim());
				}
						
			}catch(Exception e){
				result = "excel第"+idx+"行存在错误数据:"+e.getMessage();
				return new String[]{null,result};
			}

			for (int i = 0; i < arryString.length; i++) {
				if(i==14){
					continue;
				}
				if(arryString[i].equals("")){
					buffer.append(" ");
				}
				buffer.append(arryString[i]);
				if(i < arryString.length - 1){
					buffer.append("@");
				}
			}
			buffer.append("&");
			
		}
		//System.out.println(buffer.toString());
		
		return new String[]{buffer.toString().substring(0,buffer.length()-1),result};
	}

	@SuppressWarnings("unused")
	@Override
	public String uploadSettingMonthly(String dataString) {
		//月份	  工厂     车间    班组类别    班组         项目代码	 考核项目    项目比例     指标代码    考核指标    指标比例    是否关键指标   基准值        目标值     
		int result = 0;
		String[] arrStrings = dataString.split("&");
		try{
		for (int i = 0; i < arrStrings.length; i++) {
			String[] arrString = arrStrings[i].split("@");
			//System.out.println(arrStrings[i] + "  " +arrString.length);
			
			String month = arrString[0].trim();
			String factory = arrString[1].trim();
			String area = arrString[2].trim();
			String category = arrString[3].trim();
			String groupName = arrString[4].trim();
			String itemCode = arrString[5].trim();
			String itemName = arrString[6].trim();
			String itemScale = arrString[7].trim();
			String indexCode = arrString[8].trim();
			String indexName = arrString[9].trim();
			String indexScale = arrString[10].trim();
			String indexMainkey = arrString[11].trim();
			String baseValue = arrString[12].trim();
			String targetValue = arrString[13].trim();
			double toitemScale = 0;
			double toindexScale = 0;
			double tobaseValue = 0;
			double totargetValue = 0;
			if(!"".equals(itemScale)){
				toitemScale = Double.parseDouble(itemScale);
			}
			if(!"".equals(indexScale)){
				toindexScale = Double.parseDouble(indexScale);
			}
			if(!"".equals(baseValue)){
				tobaseValue = Double.parseDouble(baseValue);
			}
			if(!"".equals(targetValue)){
				totargetValue = Double.parseDouble(targetValue);
			}
			Group group = groupService.getGroupByFag(factory, area,category, groupName);
			Index index = indexService.findByIndexCode(factory,area,category,indexCode);
			
			
			MonthAssessment mon = new MonthAssessment();
			mon.setGroupKey(group.getGroupKey());
			mon.setIndexKey(index.getIndexKey());
			mon.setItemScale(toitemScale);
			mon.setIndexScale(toindexScale);
			mon.setIndexMainkey(indexMainkey);
			mon.setBaseValue(tobaseValue);
			mon.setTargetValue(totargetValue);
			mon.setCreateTime(new Date());
			mon.setMonthly(month);
			
			List<MonthAssessment> exitList = monthAssessmentService.findeByGroupAndMonthAndIndex(group.getGroupKey(), month, index.getIndexKey());
			if(exitList.size()>0)
			{
				for(MonthAssessment assement : exitList)
				{
					monthAssessmentService.deleteByPrimaryKey(assement.getMaKey());
				}
			}
			
		    monthAssessmentService.insert(mon);
			
		 }
		}catch(Exception e){
				result =-1;
				e.printStackTrace();
				new Exception("插入失败！"+e.getMessage());
			}
		return result+"";
	}

}
