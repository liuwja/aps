package com.peg.dao.part;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.Location;
import com.peg.model.part.OnlineModel;

public interface OnlineMapper {
	//图
	List<OnlineModel> getEChartObj(@Param("strSql")String sql);
	//数据展示
	List<OnlineModel> gettablistPage(BaseSearch bs);
	//数据展示
	List<Location> getlistPage(BaseSearch bs);
	//返回string集合
	List<String> getListStr(@Param("strSql")String sql);
	//入库数，按供应商
	Integer getInTotal(@Param("entity")OnlineModel o);
	//绑定数，按供应商
	Integer getbingTotal(@Param("entity")OnlineModel o);
	//30个月的总生产更换
	Integer getRTotal(@Param("entity")OnlineModel o);
	//30个月的总生产退次
	Integer getTotal(@Param("entity")OnlineModel o);
	//30个月的总入库数
	Integer getStorageTotal(@Param("entity")OnlineModel o);
	//汇总—删除
	void dateDelete(@Param("strSql")String sql);
	//汇总—插入
	void dateTransfer(@Param("strSql")String sql);
	//汇总—插入——定时程序（关键件绑定） 
	void dataTkeypartsum();
	//汇总—插入——定时程序（mes退次） 
	void dataTreturnwaresum();
	
	//获取关键件绑定数
	List<OnlineModel> getPrimaryPartList(@Param("entity")OnlineModel o);
	
	//获取非关键件入库数
	List<OnlineModel> getUnprimaryKeyPartList(@Param("entity")OnlineModel o);
	
	//三十个月绑定数
	Integer getPrimaryTotal(@Param("entity")OnlineModel o);
	
	//获取不良批数
	List<OnlineModel> getBatchDefectRecordList(@Param("entity")OnlineModel o);
	
}
