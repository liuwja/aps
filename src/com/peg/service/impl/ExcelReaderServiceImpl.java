package com.peg.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.peg.service.ExcelReaderServiceI;
@Service("excelReaderService")
public class ExcelReaderServiceImpl implements ExcelReaderServiceI{

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public String[] checkMonthlyImportExcel(List<String[]> dataList) {
		String result = "" ;
		StringBuffer buffer = null;
		int idx = 0;
		int count = 0 ;
		buffer = new StringBuffer();
		for (String[] arryString:dataList) {
			idx++;
			//用户导入固定格式
			//发生班组	  发生工厂     发生车间	  发生日期         工单编号	       产品型号    产品线   零部件名称    不良数    不良现象   责任判定   责任班组1        责任班组2       责任班组3     品质判定人
			//油烟机01组    电器工厂    组装车间     2015-9-22    WO00318828    JX05        油烟机       导风板           3             划伤            内部         油烟机01组     冲压东01组                             E56727

			String group = arryString[0].trim() ;
			if (group!=null)
			{
				count++;
				if(count>=2){
					break;
				}
				continue;
			}
			try{
				if(arryString.length!=15){
					throw new Exception("所导入的excel的列数与给出的excel格式的列数不符！请核对");
				}
			}catch(Exception e){
				result = e.getMessage();
				return new String[]{null,result};
			}
			try{
				if(!arryString[3].isEmpty()){
					sdf.parse(arryString[3]);
				}
				if(!arryString[8].isEmpty()){
					Long.parseLong(arryString[8].trim());
				}
						
			}catch(Exception e){
				result = "第"+idx+"行存在错误数据"+e.getMessage();
				return new String[]{null,result};
			}

			for (int i = 0; i < arryString.length; i++) {
				if(i==15){
					continue;
				}
				if(arryString[i].equals("")){
					buffer.append(" ");
				}
				buffer.append(arryString[i]);
				//System.out.println(i+"  "+(arryString.length - 1));
				//System.out.println(i+" "+arryString.length);
				if(i < arryString.length - 1){
					buffer.append(",");
				}
			}
			buffer.append(";");
			
		}
		
		//System.out.println(buffer.toString());
		
		return new String[]{buffer.toString().substring(0,buffer.length()-1),result};
	}

	@SuppressWarnings("unused")
	@Override
	public String uploadMonthly(String data) {
         String[] arrStrings = data.split(";");
		
		for (int i = 0; i < arrStrings.length; i++) {
			String[] arrString = arrStrings[i].split(",");
			//System.out.println(arrStrings[i] + "  " +arrString.length);
			
			String group = arrString[0];
			String factory = arrString[1].trim();
			String area = arrString[2].trim();
			String date = arrString[3].trim();
			String orderNumber = arrString[4].trim();
			String productType = arrString[5].trim();
			String line = arrString[6].trim();
			String itemName = arrString[7].trim();
			String defectQty = arrString[8].trim();
			String defect = arrString[9].trim();
			String duty = arrString[10].trim();
			String group1 = arrString[11].trim();
			String group2 = arrString[12].trim();
			String group3 = arrString[13].trim();
			String checkMan = arrString[14].trim();
			Long defectNum = null;
			if(defectQty != ""){
				 defectNum = Long.parseLong(defectQty);
			}

//			
//			UIAssemblyProductBack assembleProduct = qmservice.createUIAssemblyProductBack();
//			assembleProduct.setFactory(factory);
//			assembleProduct.setArea(area);
//			assembleProduct.setDate(date);
//			assembleProduct.setDuty(duty);
//			assembleProduct.setCheckMan(checkMan);
//			assembleProduct.setDefect(defect);
//			assembleProduct.setDefectQty(defectNum);
//			assembleProduct.setGroup1(group1);
//			assembleProduct.setGroup2(group2);
//			assembleProduct.setGroup3(group3);
//			assembleProduct.setProductType(productType);
//			assembleProduct.setLine(line);
//			assembleProduct.setOrderNumber(orderNumber);
//			assembleProduct.setItemName(itemName);				
//			assembleProduct.setGroup(group);
//			assembleProduct.save(null, null);		
					
		
		}
		return null;
		
	}

}
