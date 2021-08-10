package com.peg.service.part;

import java.util.List;

import com.peg.dao.interceptor.PageParameter;
import com.peg.echarts.EChartObj;
import com.peg.model.Location;
import com.peg.model.part.OnlineModel;

public interface OnlineServiceITwo{
	//图
	EChartObj getEChartObj(OnlineModel o);
	//在线批次数据展示
	List<OnlineModel> getlisttab(OnlineModel o,PageParameter page);
	//在线不良明细(mes)更换
	List<OnlineModel> getlisttabMes(OnlineModel o,PageParameter page);
	//在线不良明细(mes)退次
	List<OnlineModel> getlisttabMesR(OnlineModel o,PageParameter page);
	//在线不良明细ERP(erp组装生产退次)
	List<OnlineModel> getlisttabErp(OnlineModel o,PageParameter page);
	//来料入库
	List<OnlineModel> getlisttabMesStorage(OnlineModel o,PageParameter page);
	//更换数据月汇总
	void SumDataMonth(String queryMonth);
	
	//更换数据日汇总
	void SumDataDay(String startTime,String endTime);
	//区域
	List<Location> getLocal(Location mo,String strarr,PageParameter page);
	//汇总—插入——定时程序（关键件绑定）
	int insertdataTkeypartsum();
	//汇总—插入——定时程序（关键件绑定） 
	int insertTreturnwaresum();
}
