package com.peg.beans.part;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.peg.model.part.TestInstance;

/**
 * 全质量进料部分sql合成
 * @author Administrator
 *
 */
public class TestInstanceBean {

	private String sql ;
	private StringBuffer select ;
	private StringBuffer order ;
	private StringBuffer groupBy ;
	private StringBuffer where ;
	private StringBuffer comselect ;
	private StringBuffer comorder ;
	private StringBuffer comgroupBy ;
	private StringBuffer comwhere ;
	private StringBuffer oldSelect;
	private StringBuffer oldWhere;
	
	public  String getSql(TestInstance test,Map<String,List<TestInstance>> productMap){
		select = new StringBuffer();
		order = new StringBuffer();
		groupBy = new StringBuffer(); 
		where = new StringBuffer();
		comselect = new StringBuffer();
		comorder = new StringBuffer();
		comgroupBy = new StringBuffer(); 
		comwhere = new StringBuffer();
		oldSelect = new StringBuffer();
		oldWhere = new StringBuffer();
		int type = test.getType();   //查询类型,0条件,1 供应商，2 零部件，3 不良现象，4 供应商趋势，5 零部件趋势，6 供应商对比，7 零部件对比
		String factory = test.getFactory();
		String productType = test.getProductType();
		String supplierCode = getSupString(test.getSupplierCode());
//		String supplier = getSupString(test.getSupplier());
		String partType = spiltStringBySemicolon(test.getPartType());
//		String partName =  getSupString(test.getPartName());
		String partNumber = getVersionPartNumber(test.getPartNumber(), test.getPartVersion(), test.getDistinction());
		String dateType = test.getDateType();
		String dateT = test.getDateT();
		String isNew = test.getIsNew();
//		String lotTime= test.getLotTime();
		String lotStartTime= test.getLotStartTime();
		String lotEndTime= test.getLotEndTime();
		String partClass = test.getPartClass();
		String partVersion = test.getPartVersion();
		String analysisType = test.getAnalysisType();  //0批次，1数
		String defectType = test.getDefectType();
		String sordType = test.getSordType();
		int columnNum = test.getColumnNum();
		select.append("select ");
		groupBy.append(" group by");
		order.append(" order by");
		where.append(" where");
		comselect.append("select ");
		comgroupBy.append(" group by");
		comorder.append(" order by");
		comwhere.append(" where");
		oldSelect.append("select ");
		oldWhere.append(" where 1=1 ");
		//供应商排列图
		if(type==1){
			select.append(" t.supplier_brief,t.new_supplier_number,");
			groupBy.append(" t.supplier_brief,t.new_supplier_number,");
			where.append(" 1=1");
			oldSelect.append(" df.supplier_brief, df.new_supplier_number,");
			oldWhere.append(" and tl.supplier_brief = df.supplier_brief ");
		}
		//零部件排列图
		if(type==2){
			if("否".equals(partVersion)){
				select.append(" t.part_name,substr(t.new_part_number,0,length(t.new_part_number)-1) new_part_number, ");
				groupBy.append(" t.part_name,substr(t.new_part_number,0,length(t.new_part_number)-1), ");
				where.append(" 1=1");
				oldSelect.append(" df.part_name,substr(df.new_part_number,0,length(df.new_part_number)-1) new_part_number, ");
				oldWhere.append(" and tl.part_name = df.part_name ");
			}else{
				select.append(" t.part_name,t.new_part_number, ");
				groupBy.append(" t.part_name,t.new_part_number, ");
				where.append(" 1=1");
				oldSelect.append(" df.part_name,df.new_part_number, ");
				oldWhere.append(" and tl.part_name = df.part_name ");
			}
		}
		//不良现象排列图
		if(type==3){
			select.append(" ");
			groupBy.append(" ");
			where.append(" 1=1");
		}
		//供应商趋势图
		if(type==4){
			select.append(" ");
			groupBy.append(" ");
			where.append(" 1=1");
			oldWhere.append(" and tl.date_t = df.date_t ");
		}
		//零部件趋势图
		if(type==5){
			select.append(" ");
			groupBy.append(" ");
			where.append(" 1=1");
			oldWhere.append(" and tl.date_t = df.date_t ");
		}
		//供应商对比图
		if(type==6){
			select.append(" t.supplier_brief,t.new_supplier_number,");
			groupBy.append(" t.supplier_brief,t.new_supplier_number,");
			where.append(" 1=1");
			comselect.append(" t.supplier_brief,t.new_supplier_number,");
			comgroupBy.append(" t.supplier_brief,t.new_supplier_number,");
			comwhere.append(" 1=1");
			oldSelect.append(" df.supplier_brief,df.new_supplier_number,");
			oldWhere.append(" and tl.supplier_brief = df.supplier_brief ");
		}
		//零部件对比图
		if(type==7){
			if("否".equals(partVersion)){
				select.append(" t.part_name,substr(t.new_part_number,0,length(t.new_part_number)-1) new_part_number,");
				groupBy.append(" t.part_name,substr(t.new_part_number,0,length(t.new_part_number)-1),");
				comselect.append(" t.part_name,substr(t.new_part_number,0,length(t.new_part_number)-1) new_part_number,");
				comgroupBy.append(" t.part_name,substr(t.new_part_number,0,length(t.new_part_number)-1) ,");
				oldSelect.append(" df.part_name,substr(df.new_part_number,0,length(df.new_part_number)-1) new_part_number,");
				oldWhere.append(" and tl.part_name = df.part_name ");
			}else{
				select.append(" t.part_name,t.new_part_number,");
				groupBy.append(" t.part_name,t.new_part_number,");
				comselect.append(" t.part_name,t.new_part_number,");
				comgroupBy.append(" t.part_name,t.new_part_number,");
				oldSelect.append(" df.part_name,df.new_part_number,");
				oldWhere.append(" and tl.part_name = df.part_name ");
			}
			where.append(" 1=1");
			comwhere.append(" 1=1");
		}
//		
//		if(StringUtils.isBlank(factory) && StringUtils.isBlank(productType) && StringUtils.isBlank(supplier) && StringUtils.isBlank(partType)
//				&& StringUtils.isBlank(partName)){
//			select.append(" t.supplier_name,");
//			groupBy.append(" t.supplier_name,");
//			where.append(" 1=1");
//		}
		if(StringUtils.isNotBlank(factory) && StringUtils.isBlank(productType) ){
			List<TestInstance> productList = productMap.get(factory);
			String productTypes = getProductType(productList);
				select.append(" ");
				groupBy.append(" ");
				where.append(" and t.product_type in ("+productTypes+")");
				comselect.append(" ");
				comgroupBy.append(" ");
				comwhere.append(" and t.product_type in ("+productTypes+")");
			
		}
		if(StringUtils.isNotBlank(productType)){
			select.append("  t.product_type,");
			groupBy.append(" t.product_type,");
			where.append(" and t.product_type='"+productType+"'");
			comselect.append(" t.product_type,");
			comgroupBy.append(" t.product_type,");
			comwhere.append(" and t.product_type='"+productType+"'");
		}
//		if(StringUtils.isNotBlank(supplier)){
//			if(type!=1 && type!=6 && type != 4){
//				select.append(" t.supplier_name,");
//				groupBy.append(" t.supplier_name,");
//				comselect.append(" t.supplier_name,");
//				comgroupBy.append(" t.supplier_name,");
//			}
//			System.out.println(supplier);
//			where.append(" and t.supplier_name in ("+supplier.replaceAll(";", ",")+")");
//			comwhere.append(" and t.supplier_name in ("+supplier.replaceAll(";", ",")+")");
//		}
		if(StringUtils.isNotBlank(supplierCode)){
			if(type!=1 && type!=6 && type != 4){
//				select.append(" t.supplier_number,t.supplier_name,t.new_supplier_number,");
//				groupBy.append(" t.supplier_number,t.supplier_name,t.new_supplier_number,");
//				comselect.append(" t.supplier_number,t.supplier_name,t.new_supplier_number,");
//				comgroupBy.append(" t.supplier_number,t.supplier_name,t.new_supplier_number,");
			}
			System.out.println(supplierCode);
			//换成新供应商编号
//			where.append(" and t.supplier_number in ("+supplierCode+")");
//			comwhere.append(" and t.supplier_number in ("+supplierCode+")");
			where.append(" and t.new_supplier_number in ("+supplierCode+")");
			comwhere.append(" and t.new_supplier_number in ("+supplierCode+")");
		}
		if(StringUtils.isNotBlank(partType)){
			select.append(" t.part_type,");
			groupBy.append(" t.part_type,");
			where.append(" and t.part_type in ("+partType.replaceAll(";", ",")+")");
			comselect.append(" t.part_type,");
			comgroupBy.append(" t.part_type,");
			comwhere.append(" and t.part_type in ("+partType.replaceAll(";", ",")+")");
		}
		if(StringUtils.isNotBlank(partNumber)){
			if(type!=2 && type !=7 && type !=5){
//				select.append(" t.part_number,t.part_name,t.new_part_number,");
//				groupBy.append(" t.part_number,t.part_name,t.new_part_number,");
//				comselect.append(" t.part_number,t.part_name,t.new_part_number,");
//				comgroupBy.append(" t.part_number,t.part_name,t.new_part_number,");
			}
			//1为带版本，0为不带版本
			if("否".equals(partVersion)){
				where.append(" and substr(t.new_part_number,0,length(t.new_part_number)-1) in ("+partNumber+")");
				comwhere.append(" and substr(t.new_part_number,0,length(t.new_part_number)-1) in ("+partNumber+")");
			}else{
				where.append(" and t.new_part_number in ("+partNumber+")");
				comwhere.append(" and t.new_part_number in ("+partNumber+")");
			}
		}
		if(type != 4 && type != 5 && type != 6 && type != 7){
			if(StringUtils.isNotBlank(dateType) && StringUtils.isNotBlank(dateT)){
				if("天".equals(dateType) ){
					select.append(" substr(t.date_t,0,10) as date_t,");
					groupBy.append(" substr(t.date_t,0,10),");
					where.append(" and substr(t.date_t,0,10) ='"+dateT+"'");
				}else if("周".equals(dateType) ){
					select.append(" to_char(to_date(substr(t.date_t,0,10),'yyyy-mm-dd'),'iw') as date_t,");
					groupBy.append(" to_char(to_date(substr(t.date_t,0,10),'yyyy-mm-dd'),'iw'),");
					where.append(" and  to_char(to_date(substr(t.date_t,0,10),'yyyy-mm-dd'),'yyyyiw') =  to_char(to_date('"+dateT+"','yyyy-mm-dd'),'yyyyiw')");
				}else if("月".equals(dateType)){
					select.append(" '"+dateT+"' as date_t,");
					groupBy.append(" '"+dateT+"',");
//					where.append(" and to_date(substr(t.date_t,0,10),'yyyy-mm-dd') between add_months(to_date('"+dateT+"','yyyy-mm')+25,-1) and add_months(to_date('"+dateT+"','yyyy-mm')+24,0)");
					where.append(" and substr(t.date_t,0,7) = '"+dateT+"'");
				}else if("年".equals(dateType)){
//					String dateN = dateT+"-12";
					select.append(" '"+dateT+"' as date_t,");
					groupBy.append(" '"+dateT+"',");
//					where.append(" and to_date(substr(t.date_t,0,10),'yyyy-mm-dd') between add_months(to_date('"+dateN+"','yyyy-mm')+25,-11) and add_months(to_date('"+dateN+"','yyyy-mm')+24,0)");
					where.append(" and substr(t.date_t,0,4) = '"+dateT+"' ");
				}
			}
		}
		
		if(type==4 || type==5){
			if(StringUtils.isNotBlank(dateType) && StringUtils.isNotBlank(dateT) ){
				if("天".equals(dateType) ){
					select.append(" substr(t.date_t,0,10) as date_t,");
					groupBy.append(" substr(t.date_t,0,10),");
					where.append(" and to_date(substr(t.date_t,0,10),'yyyy-mm-dd') between to_date('"+dateT+"','yyyy-mm-dd')-"+columnNum+" and to_date('"+dateT+"','yyyy-mm-dd') ");
					order.append(" substr(t.date_t,0,10),");
				}else if("周".equals(dateType) ){
					select.append(" to_char(to_date(substr(t.date_t,0,10),'yyyy-mm-dd'),'iw') as date_t,");
					groupBy.append(" to_char(to_date(substr(t.date_t,0,10),'yyyy-mm-dd'),'iw'),");
					where.append(" and to_char(to_date(substr(t.date_t,0,10),'yyyy-mm-dd'),'yyyyiw') between to_char(to_date('"+dateT+"','yyyy-mm-dd'),'yyyyiw')-'"+(columnNum-1)+"' and to_char(to_date('"+dateT+"','yyyy-mm-dd'),'yyyyiw')");
					order.append(" to_char(to_date(substr(t.date_t,0,10),'yyyy-mm-dd'),'iw'),");
				}else if("月".equals(dateType)){
//					select.append(" to_char(add_months(to_date(substr(t.date_t,0,7),'yyyy-mm'),decode(sign(substr(t.date_t,9,2)-25) ,1,1,0)),'yyyy-mm') as date_t,");
//					groupBy.append(" to_char(add_months(to_date(substr(t.date_t,0,7),'yyyy-mm'),decode(sign(substr(t.date_t,9,2)-25) ,1,1,0)),'yyyy-mm') ,");
//					where.append(" and to_date(substr(t.date_t,0,10),'yyyy-mm-dd') between add_months(to_date('"+dateT+"','yyyy-mm')+25,-"+columnNum+") and add_months(to_date('"+dateT+"','yyyy-mm')+24,0)");
//					order.append(" to_char(add_months(to_date(substr(t.date_t,0,7),'yyyy-mm'),decode(sign(substr(t.date_t,9,2)-25) ,1,1,0)),'yyyy-mm'),");
					select.append("  substr(t.date_t,0,7) as date_t,");
					groupBy.append(" substr(t.date_t,0,7)  ,");
					where.append(" and to_date(substr(t.date_t,0,7),'yyyy-mm') between add_months(to_date('"+dateT+"','yyyy-mm'),-"+(columnNum-1)+") and add_months(to_date('"+dateT+"','yyyy-mm'),0)");
					order.append(" substr(t.date_t,0,7),");
				}else if("年".equals(dateType)){
					int yearColumn ;
					if(columnNum>3){
						yearColumn = 3*12;
					}else{
						yearColumn = columnNum*12;
					}
					String dateN = dateT+"-12";
//					select.append(" substr(t.date_t,0,4)+decode(sign(replace(substr(t.date_t,6,5),'-')-1225) ,1,1,0) as date_t,");
//					groupBy.append(" substr(t.date_t,0,4)+decode(sign(replace(substr(t.date_t,6,5),'-')-1225) ,1,1,0 ) ,");
//					where.append(" and to_date(substr(t.date_t,0,10),'yyyy-mm-dd') between add_months(to_date('"+dateN+"','yyyy-mm')+25,-"+yearColumn+") and add_months(to_date('"+dateN+"','yyyy-mm')+24,0)");
//					order.append(" substr(t.date_t,0,4)+decode(sign(replace(substr(t.date_t,6,5),'-')-1225) ,1,1,0),");
					select.append(" substr(t.date_t,0,4) as date_t,");
					groupBy.append(" substr(t.date_t,0,4),");
					where.append(" and to_date(substr(t.date_t,0,10),'yyyy-mm-dd') between add_months(to_date('"+dateN+"','yyyy-mm'),-"+yearColumn+") and add_months(to_date('"+dateN+"','yyyy-mm'),0)");
					order.append(" substr(t.date_t,0,4),");
				}
			}
		}
		
		if(type==6 || type==7){
			if(StringUtils.isNotBlank(dateType) && StringUtils.isNotBlank(dateT)){
				if("天".equals(dateType) ){
					select.append(" substr(t.date_t,0,10) as date_t,");
					groupBy.append(" substr(t.date_t,0,10),");
					where.append(" and to_date(substr(t.date_t,0,10),'yyyy-mm-dd') =to_date('"+dateT+"','yyyy-mm-dd')");
					comselect.append(" substr(t.date_t,0,10) as date_t,");
					comgroupBy.append(" substr(t.date_t,0,10),");
					comwhere.append(" and to_date(substr(t.date_t,0,10),'yyyy-mm-dd') =to_date('"+dateT+"','yyyy-mm-dd')-1");
				}else if("周".equals(dateType) ){
					select.append(" '本周' as date_t,");
					groupBy.append(" '本周',");
					where.append(" and to_date(substr(t.date_t,0,10),'yyyy-mm-dd') between to_date('"+dateT+"','yyyy-mm-dd')-6 and to_date('"+dateT+"','yyyy-mm-dd')");
					comselect.append(" '上周' as date_t,");
					comgroupBy.append(" '上周',");
					comwhere.append(" and to_date(substr(t.date_t,0,10),'yyyy-mm-dd') between to_date('"+dateT+"','yyyy-mm-dd')-13 and to_date('"+dateT+"','yyyy-mm-dd')-7");
				}else if("月".equals(dateType)){
					select.append(" add_months(to_date('"+dateT+"','yyyy-mm'),0) as date_t,");
					groupBy.append(" add_months(to_date('"+dateT+"','yyyy-mm'),0),");
//					where.append(" and to_date(substr(t.date_t,0,10),'yyyy-mm-dd') between add_months(to_date('"+dateT+"','yyyy-mm')+25,-1) and add_months(to_date('"+dateT+"','yyyy-mm')+24,0)");
					where.append(" and substr(t.date_t,0,7) = '"+dateT+"' ");
					comselect.append(" add_months(to_date('"+dateT+"','yyyy-mm'),-1) as date_t,");
					comgroupBy.append(" add_months(to_date('"+dateT+"','yyyy-mm'),-1),");
//					comwhere.append(" and to_date(substr(t.date_t,0,10),'yyyy-mm-dd') between add_months(to_date('"+dateT+"','yyyy-mm')+25,-2) and add_months(to_date('"+dateT+"','yyyy-mm')+24,-1)");
					comwhere.append(" and to_date(substr(t.date_t,0,10),'yyyy-mm-dd') between add_months(to_date('"+dateT+"','yyyy-mm'),-1) and add_months(to_date('"+dateT+"','yyyy-mm')-1,0)");
				}else if("年".equals(dateType)){
//					String dateN = dateT+"-12";
					int dateC = Integer.parseInt(dateT)-1;
					select.append(" '"+dateT+"' as date_t,");
					groupBy.append(" '"+dateT+"',");
//					where.append(" and to_date(substr(t.date_t,0,10),'yyyy-mm-dd') between add_months(to_date('"+dateN+"','yyyy-mm')+25,-11) and add_months(to_date('"+dateN+"','yyyy-mm')+24,0)");
					where.append(" and substr(t.date_t,0,4) = '"+dateT+"' ");
					comselect.append(" '"+dateC+"' as date_t,");
					comgroupBy.append(" '"+dateC+"',");
//					comwhere.append(" and to_date(substr(t.date_t,0,10),'yyyy-mm-dd') between add_months(to_date('"+dateN+"','yyyy-mm')+25,-24) and add_months(to_date('"+dateN+"','yyyy-mm')+24,-12)");
					comwhere.append(" and substr(t.date_t,0,4) = '"+dateC+"'");
				}
			}
		}
		
		if(StringUtils.isNotBlank(isNew)){
			select.append(" t.is_new,");
			groupBy.append(" t.is_new,");
//			where.append(" and t.is_new ='"+isNew+"'");
			where.append(" and t.is_new like '%"+isNew+"%'");
			comselect.append("  t.is_new,");
			comgroupBy.append("  t.is_new,");
//			comwhere.append(" and t.is_new ='"+isNew+"'");
			comwhere.append(" and t.is_new like '%"+isNew+"%'");
		}
		if(StringUtils.isNotBlank(lotStartTime) && StringUtils.isNotBlank(lotEndTime)){
//			select.append(" substr(t.lot_name,11,3),");
//			groupBy.append(" substr(t.lot_name,11,3),");
			where.append(" and substr(t.lot_name,11,3) between '"+lotStartTime+"' and  '"+lotEndTime+"'");
//			comselect.append(" substr(t.lot_name,11,3),");
//			comgroupBy.append(" substr(t.lot_name,11,3),");
			comwhere.append(" and substr(t.lot_name,11,3) between '"+lotStartTime+"' and  '"+lotEndTime+"'");
		}
		if(StringUtils.isNotBlank(partClass)){
			select.append(" t.part_class,");
			groupBy.append(" t.part_class,");
			where.append(" and t.part_class ='"+partClass+"'");
			comselect.append(" t.part_class,");
			comgroupBy.append(" t.part_class,");
			comwhere.append(" and t.part_class ='"+partClass+"'");
		}
//		if(StringUtils.isNotBlank(partVersion)){
//			if("0".equals(partVersion)){
//				where.append(" and substr(t.part_number,0,length(t.part_number)-1) in ("+partNumber+")");
//				comwhere.append(" and substr(t.part_number,0,length(t.part_number)-1) in ("+partNumber+")");
//			}
////			select.append(" t.part_version,");
////			groupBy.append(" t.part_version,");
////			where.append(" and t.part_version ='"+partVersion+"'");
////			comselect.append(" t.part_version,");
////			comgroupBy.append(" t.part_version,");
////			comwhere.append(" and t.part_version ='"+partVersion+"'");
//		}
		if(StringUtils.isNotBlank(analysisType)){
			select.append(" ");
			groupBy.append(" ");
			where.append(" ");
			comselect.append(" ");
			comgroupBy.append(" ");
			comwhere.append(" ");
		}
		if(StringUtils.isNotBlank(defectType)){
			select.append(" ");
			groupBy.append(" ");
			comselect.append(" ");
			comgroupBy.append(" ");
			if("性能类不良批".contains(defectType)){
				where.append(" and t.result_s ='该批不合格' and t.property_type ='不合格' ");
				comwhere.append(" and t.result_s ='该批不合格' and t.property_type ='不合格' ");
			}
			if("尺寸类不良批".contains(defectType)){
				where.append(" and t.result_s ='该批不合格' and t.size_type ='不合格' ");
				comwhere.append(" and t.result_s ='该批不合格' and t.size_type ='不合格' ");
			}
			if("外观类不良批".contains(defectType)){
				where.append(" and t.result_s ='该批不合格' and t.aspect_type ='不合格' ");
				comwhere.append(" and t.result_s ='该批不合格' and t.aspect_type ='不合格' ");
			}
			if("其他类不良批".contains(defectType)){
				where.append(" and t.result_s ='该批不合格' and t.other_type ='不合格' ");
				comwhere.append(" and t.result_s ='该批不合格' and t.other_type ='不合格' ");
			}
			
		}
		if(type==1 || type==2 || type==4 || type==5){
			select.append(" count(t.result_s) total_lot,sum(decode(t.result_s,'该批不合格',1,0)) defect_lot, "
					+ " sum(decode(t.result_s,'该批不合格',decode(t.property_type ,'不合格', t.property_tnum ,'合格',decode(t.size_type,'不合格',t.size_tnum,'合格',"
					+ " decode(t.aspect_type,'不合格',t.aspect_tnum,'合格',decode(t.other_type,'不合格',t.other_tnum,'合格',0,0),0),0),0),0)) total_qty,"
					+ " sum(decode(t.result_s,'该批不合格',decode(t.property_type,'不合格',t.property_dnum,'合格',decode(t.size_type,'不合格',t.size_dnum,'合格',"
					+ " decode(t.aspect_type,'不合格',t.aspect_dnum,'合格',decode(t.other_type,'不合格',t.other_dnum,'合格',0,0),0),0),0),0)) defect_qty from t_test_instance_record t");
			if("0".equals(analysisType)){
				if("不良率".equals(sordType)){
					order.append(" defect_lot/total_lot desc");
				}else{
					order.append(" defect_lot desc");
				}
			}
			if("1".equals(analysisType)){
				if("不良率".equals(sordType)){
					order.append(" defect_qty/decode(total_qty,0,1,total_qty) desc");
				}else{
					order.append(" defect_qty desc");
				}
			}
		}
		if(type==3){
			select.append(" count(t.result_s) defect_lot,sum(decode(t.result_s,'该批不合格',decode(t.property_type,'不合格',1,0),0)) property_lot ,"
			+" sum(decode(t.result_s,'该批不合格',decode(t.property_type,'合格',decode(t.size_type,'不合格',1,0),0),0)) size_lot,"
			+ "  sum(decode(t.result_s,'该批不合格',decode(t.property_type,'合格', decode(t.size_type,'合格',decode(t.aspect_type,'不合格',1,0),0),0),0)) aspect_lot, "
			+ " sum(decode(t.result_s,'该批不合格',decode(t.property_type,'合格',decode(t.size_type,'合格',decode(t.aspect_type,'合格',decode(t.other_type,'不合格',1,0),0),0),0),0)) other_lot, "
			+ " sum( aspect_tnum) total_qty, "
			+ " sum(decode(t.result_s,'该批不合格',decode(t.property_type,'不合格',t.property_dnum,0),0)) property_qty,  "
			+ " sum(decode(t.result_s,'该批不合格',decode(t.property_type,'合格',decode(t.size_type,'不合格',t.size_dnum,0),0),0)) size_qty, "
			+ " sum(decode(t.result_s,'该批不合格',decode(t.property_type,'合格',decode(t.size_type,'合格',decode(t.aspect_type,'不合格',t.aspect_dnum,0),0),0),0)) aspect_qty, "
			+ " sum(decode(t.result_s,'该批不合格',decode(t.property_type,'合格',decode(t.size_type,'合格',decode(t.aspect_type,'合格', decode(t.other_type,'不合格',t.other_dnum,0),0),0),0),0)) other_qty "
			+ " from t_test_instance_record t ");
			order.append(" defect_lot desc");
		
		}
		if(type==6 || type==7 ){
			select.append(" count(t.result_s) total_lot,sum(decode(t.result_s,'该批不合格',1,0)) defect_lot, "
					+ " sum(decode(t.result_s,'该批不合格',decode(t.property_type,'不合格',t.property_tnum,'合格',decode(t.size_type,'不合格',t.size_tnum,'合格',"
					+ " decode(t.aspect_type,'不合格',t.aspect_tnum,'合格',decode(t.other_type,'不合格',t.other_tnum,'合格',0,0),0),0),0),0)) total_qty,"
					+ " sum(decode(t.result_s,'该批不合格',decode(t.property_type,'不合格',t.property_dnum,'合格',decode(t.size_type,'不合格',t.size_dnum,'合格',"
					+ " decode(t.aspect_type,'不合格',t.aspect_dnum,'合格',decode(t.other_type,'不合格',t.other_dnum,'合格',0,0),0),0),0),0)) defect_qty from t_test_instance_record t");
			comselect.append(" count(t.result_s) total_lot,sum(decode(t.result_s,'该批不合格',1,0)) defect_lot, "
					+ " sum(decode(t.result_s,'该批不合格',decode(t.property_type,'不合格',t.property_tnum,'合格',decode(t.size_type,'不合格',t.size_tnum,'合格',"
					+ " decode(t.aspect_type,'不合格',t.aspect_tnum,'合格',decode(t.other_type,'不合格',t.other_tnum,'合格',0,0),0),0),0),0)) total_qty,"
					+ " sum(decode(t.result_s,'该批不合格',decode(t.property_type,'不合格',t.property_dnum,'合格',decode(t.size_type,'不合格',t.size_dnum,'合格',"
					+ " decode(t.aspect_type,'不合格',t.aspect_dnum,'合格',decode(t.other_type,'不合格',t.other_dnum,'合格',0,0),0),0),0),0)) defect_qty from t_test_instance_record t");
			if("0".equals(analysisType)){
//				order.append(" t.date_t desc, defect_lot desc");
				if("不良率".equals(sordType)){
					order.append(" t.date_t desc, defect_lot/total_lot desc");
				}else{
					order.append(" t.date_t desc, defect_lot desc");
				}
			
			}
			if("1".equals(analysisType)){
//				order.append(" t.date_t desc, defect_qty desc");
				if("不良率".equals(sordType)){
					order.append(" t.date_t desc, defect_qty/decode(total_qty,0,1,total_qty) desc ");
				}else{
					order.append(" t.date_t desc, defect_qty desc");
				}
			}
		}
	
		String groupBys ="";
		String comgroupBys ="";
		if(groupBy.lastIndexOf(",")>0){
			groupBys = groupBy.substring(0, groupBy.lastIndexOf(","));
		}
		if(comgroupBy.lastIndexOf(",")>0){
			comgroupBys = comgroupBy.substring(0, comgroupBy.lastIndexOf(","));
		}
		if(type==1 || type==2 || type==3 || type==4 || type==5 ){
			sql = select.toString() + where.toString()+ groupBys +order.toString() ;
		}
		if(type==6 || type==7  ){
			sql = "select * from( " +select.toString() + where.toString()+ groupBys + " union all " +  comselect.toString() + comwhere.toString()+ comgroupBys +") t " +order.toString();
		}
		if(StringUtils.isNotBlank(defectType)){
			if(type != 3){
				String oldSql = sql;
				test.setDefectType(null);
				String newSql = getSql(test,productMap);
				sql = oldSelect+ " df.date_t ,tl.total_lot,df.defect_lot,tl.total_qty,df.defect_qty from (" + newSql +") tl,"
						+ "( "+ oldSql +") df " +oldWhere;
				test.setDefectType(defectType);		
			}
			
		}
		return sql;
	}
	/**
	 * 获取机型字符串
	 * @param productList
	 * @return
	 */
	private String getProductType(List<TestInstance> productList) {
		String product = "";
		for(TestInstance test : productList){
			product += "'"+test.getProductType()+"',";
		}
		if(product.endsWith(",")){
			product = product.substring(0, product.lastIndexOf(","));
		}
		return product;
	}

	public String getSupString(String supplier) {
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
	
	public String spiltStringBySemicolon(String supplier) {
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
	
	public String getVersionPartNumber(String partNumber,String version, Long distinction) {
		String sup = "";
		if(partNumber != null && !"".equals(partNumber)){
			String[] str = partNumber.split(",");
			if("否".equals(version) && distinction != null && 1 != distinction  ){
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
	
	

	public static void main(String args[]){
		TestInstance test = new TestInstance();
		test.setType(1);
		test.setAnalysisType("1");
		test.setColumnNum(5);
		test.setDateType("周");
		test.setDateT("2016-06-13");
		TestInstanceBean bean = new TestInstanceBean();
		String sql = bean.getSql(test,null);
		System.out.println(sql);
	}

	
}
