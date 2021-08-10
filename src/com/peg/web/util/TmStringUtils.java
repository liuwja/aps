package com.peg.web.util;

public class TmStringUtils {
	
	/**
	 * 判断是否为空,ture:为空,false:非空 <BR>
	 * 方法名：isEmpty<BR>
	 * 创建人：fengqiguang<BR>
	 * 时间：2014年12月26日-下午9:27:14 <BR>
	 */
	public static boolean isEmpty(String str) {
		return null == str || str.length() == 0 || "".equals(str)
				|| str.matches("\\s*");
	}
	
	/**
	 * 判断是否为空 ture:非空,false:空<BR>
	 * 方法名：isNotEmpty<BR>
	 * 创建人：fengqiguang<BR>
	 * 时间：2014年12月26日-下午9:28:03 <BR>
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 通过“,”拆分字符串
	 * @param supplier
	 * @return
	 */
	public static String getSupStringByComma(String supplier) {
		String sup = "";
		if(supplier != null && !"".equals(supplier)){
			String[] str = supplier.split(",");
			for(int i= 0; i< str.length; i++){
					sup += "'"+str[i]+"',";
			}
			if(sup.endsWith(",")){
				sup = sup.substring(0, sup.length()-1);
			}
		}
		
		return sup;
	}
	/**
	 * 通过“;”拆分字符串
	 * @param supplier
	 * @return
	 */
	public static String getSupStringBySemicolon(String supplier) {
		String sup = "";
		if(supplier != null && !"".equals(supplier)){
			String[] str = supplier.split(";");
			for(int i= 0; i< str.length; i++){
					sup += "'"+str[i]+"',";
			}
			if(sup.endsWith(",")){
				sup = sup.substring(0, sup.length()-1);
			}
		}
		
		return sup;
	}
	/**
	 * 先按;,拆分
	 * 再按,拆分
	 * @param obj
	 * @return
	 */
	public static String getStringBysplit(String obj,Integer bs){
		String str="";
//		if(obj!=null && !obj.equals("")){
//			String[] arr = obj.split(";,");
//			for (int i = 0; i < arr.length; i++) {
//				String[] arr2=arr[i].split(",");
//				for (int j = 0; j < arr2.length; j++) {
//					if(j==1){
//						str+=arr2[j]+",";
//					}
//				}
//			}
//		}
		if(obj!=null && !obj.equals("")){
			String[] arr = null;
			if(bs==null || bs==16){
				arr = obj.split(";,");
			}else {
				arr = obj.split(";,");
			}	
			for (int i = 0; i < arr.length; i++) {
				String[] arr2=arr[i].split(",");
				for (int j = 0; j < arr2.length; j++) {
					if(j==1){
						str+=arr2[j]+",";
					}
				}
			}
		}
		return str;
	}
	
	public static String getStringByArray(String[] strs) {
		String sup = "";
		for(int i= 0; i< strs.length; i++){
			sup += "'"+strs[i]+"',";
		}
		if(sup.endsWith(",")){
			sup = sup.substring(0, sup.length()-1);
		}
		return sup;
	}
	
	public static String getVersionPartNumber(String partNumber,String version) {
		String sup = "";
		if(partNumber != null && !"".equals(partNumber)){
			String[] str = partNumber.split(",");
			if("否".equals(version)){
				for(int i= 0; i< str.length; i++){
					sup += "'"+str[i].substring(0, str[i].length()-1)+"',";
			     }
			}else{
				for(int i= 0; i< str.length; i++){
					sup += "'"+str[i]+"',";
			     }
			}
			if(sup.endsWith(",")){
				sup = sup.substring(0, sup.length()-1);
			}
		}
		
		return sup;
	}
}
