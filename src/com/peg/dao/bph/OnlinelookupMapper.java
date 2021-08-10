package com.peg.dao.bph;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.bph.Onlinelookup;

public interface OnlinelookupMapper {
	//查物料类别
	List<Onlinelookup> getpartPage(BaseSearch bs); 
	//查物料编号
	List<Onlinelookup> getparttwoPage(BaseSearch bs);
	//查供应商
	List<Onlinelookup> getAccountPage(BaseSearch bs);
	//查工厂
	List<String> getFactoryDao();
	//查机型类别
	List<String> getTypeDao(@Param("factory")String factory);
	//供应商排列图
	List<Onlinelookup> getAccountChar(@Param("entity")Onlinelookup bs);
	//供应商趋势图
	List<Onlinelookup> getAccountChar2(@Param("entity")Onlinelookup bs);
	//零部件排列图
	List<Onlinelookup> getSpareChar(@Param("entity")Onlinelookup bs);
	//零部件趋势图
	List<Onlinelookup> getSpareChar2(@Param("entity")Onlinelookup bs);
	//不良现象排列图
	List<Onlinelookup> getBadChar(@Param("entity")Onlinelookup bs);
	
	
	//供应商排列图（不良数/率，关键件）
	List<Onlinelookup> getAccountCharsl(@Param("entity")Onlinelookup bs);
	//供应商排列图（不良数/率，非关键件）
	List<Onlinelookup> getAccountCharsl2(@Param("entity")Onlinelookup bs);
	//零部件排列图（不良数/率，关键件）
	List<Onlinelookup> getSpareCharsl(@Param("entity")Onlinelookup bs);
	//零部件趋势图（不良数/率，非关键件）
	List<Onlinelookup> getSpareCharsl2(@Param("entity")Onlinelookup bs);
	//不良现象排列图（不良数/率）
	List<Onlinelookup> getBadcharsl(@Param("entity")Onlinelookup bs);
	//供应商不良数趋势图（关键件）
	List<Onlinelookup> getAccountRend(@Param("entity")Onlinelookup bs);
	//供应商不良数趋势图（非关键件）
	List<Onlinelookup> getAccountRend2(@Param("entity")Onlinelookup bs);
	//零部件不良数趋势图（关键件）
	List<Onlinelookup> getSpareRend(@Param("entity")Onlinelookup bs);
	//零部件不良数趋势图（非关键件）
	List<Onlinelookup> getSpareRend2(@Param("entity")Onlinelookup bs);
	//在线批次展示
	List<Onlinelookup> getOnlineshowPage(BaseSearch bs);
	//ERP在线不良数据展示
	List<Onlinelookup> getERPshowPage(BaseSearch bs);
	//在线不良数据展示（mes）
	List<Onlinelookup> getshowPage(BaseSearch bs);
	//来料入库明细
	List<Onlinelookup> getOnlineshowupPage(BaseSearch bs);
	//30个月的总生产退次
	Integer getTotal(@Param("entity")Onlinelookup vo);
	//30个月的总入库数
	Integer getStorageTotal(@Param("entity")Onlinelookup vo);
	//当月入库数
	//int getStorageSameTotal(@Param("entity")Onlinelookup vo);
	//定时任务调度
	void dateTransfer();
}
