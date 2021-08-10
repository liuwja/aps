package com.peg.service.part;

import java.util.List;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.echarts.EChartObj;
import com.peg.model.bph.Onlinelookup;

public interface OnlineServiceI{
	//图
	EChartObj getEChartObj(Onlinelookup vo);
	//在线批次数据展示
	List<Onlinelookup> gettablist(BaseSearch bs);
	//来料入库明细
	List<Onlinelookup> gettabStoragelist(BaseSearch bs);
	//ERP在线不良数据展示
	List<Onlinelookup> getERPTabList(BaseSearch bs);
	//不良数展示（mes）
	List<Onlinelookup> getTabList(BaseSearch bs);
	//定时任务调度
	int insertDate();
}
