<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	#c_content{font-family:微软雅黑;}
	#c_content .c_top{height:50px;}
	#c_content .c_top .t_h3{text-align:center;height:50px;line-height:50px;font-weight:600;font-size:20px;}
	#b_table{width:100%;border:solid #add9c0; border-width:1px 0px 0px 1px;}
	
	#b_table .th_tr{height:40px;line-height:40px;}
	
	#b_table .th_tr th{font-size: 14px;font-weight:bold;border:solid #add9c0; border-width:0px 1px 1px 0px;}
	#b_table .tb tr:hover{background:#f5f5f5;}
	#b_table .tb td{border:solid #add9c0; border-width:0px 1px 1px 0px;padding:10px;font-size:14px;line-height:24px;color:#1F1E1E;}
	#b_table .tb .td_center{text-align:center;}
	#b_table .tb td span{padding-left:2em;font-size:14px;line-height:24px;color:#1F1E1E;}
</style>
<div class="pageContent" id="c_content" >
	<div class="c_top">
		<h3 class="t_h3">各报表统计计算说明</h3>
	</div>
	<div class="box">
		<table id="b_table" layoutH="60">
			 <thead>
			 	<tr class="th_tr">
			 		<th width="20%">图表（数据）</th>
			 		<th width="80%">说明 （计算方式）</th>
			 	</tr>
			 </thead>
			 <tbody class="tb">
			 	<tr>
			 		<td class="td_center">单月百台维修率(Q)</td>
			 		<td>
			 			需求描述 :以维修截止时间为2015-02为准：
         				分子为2015.02月的维修数，设定为R；
						分母为以2015.02(含)往前推3个月（也即2014.11）为基准，再往前推12个月，也即【(2013.12~2014.11期间的发货数)/12 】，
						设定为S；Q=(R/S) *100%
						上述公式仅为单月百台维修率的公式，该文档中还涉及到各机型、各产线、各区域的单月百台维修，计算公式相同，仅需要区分产品的类型，如各产线的单月百台维修率，
						则需要先区分产线，再区分各产线在期间内的维修数及发货数以计算各产线的单月百台维修率。
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">单月累计百台维修率(X)</td>
			 		<td>
			 			需求描述
         				以维修截止时间为2015-02为准：
         				分子为以2015.02月(含)往前推12个月期间的维修数，也即2014.03~2015.02期间的维修数，设定为R；
						分母为以2015.02(含)往前推3个月（也即2014.11）为基准，再往前推12个月，也即2013.12~2014.11期间的发货数，设定为S；
						X=(R/S) *100%
						上述公式仅为单月累计百台维修率的公式，该文档中还涉及到各机型、各产线、各区域的单月累计百台维修率，计算公式相同，仅需要区分产品的类型，
						如各产线的单月累计百台维修率，则需要先区分产线，再区分各产线在期间内的维修数及发货数以计算各产线的单月累计百台维修率。
			 		</td>
			 	</tr>
			 	
			 	<tr>
			 		<td class="td_center">时间序列图</td>
			 		<td>
			 			1.X轴：以2015.02月（含）为基准往前推12个月，也即2014.3~2015.2 每个月份为一个统计单位（即X轴的一个项目）；<br/>
						•虚线：各统计单位下，所选择机型在基准月份（2015.02月）下所设定的月度累计百台维修率；<br/>
						•红实线：各统计单位下单月累计百台维修率，如：<br/>
						<span>1) 2014年4月的数值为  2013.5~2014.4期间的维修数/ 2013.2~2014.1期间的发货数；</span><br/>
						<span>2) 2014年5月的数值为  2013.6~2014.5期间的维修数/ 2013.3~2014.2期间的发货数；</span><br/>
						•蓝实线：各统计单位下单月百台维修率，如：<br/>
						<span>1) 2014年4月的数值为  2014.4月的维修数/ 【(2013.2~2014.1期间的发货数)/12 】；</span><br/>
						<span>2) 2014年5月的数值为  2014.5月的维修数/ 【(2013.3~2014.2期间的发货数)/12 】；</span><br/>
			 		</td>
			 	</tr>
			 	
			 	<tr>
			 		<td class="td_center">排列图</td>
			 		<td>
			 			1.统计期间：以2015.02月（含）为基准往前推12个月，也即2014.3~2015.2；<br/>
						2.X轴：每个型号为一个统计单位，按统计单位的维修数降序排列，上限20个，超过20以上的型号统称为其他，20个以内的则都显示；<br/>
  						柱状图数值：每个型号统计期间内的维修数；<br/>
 						 折线图数值：每个型号统计期间内单月累计百台维修率，基准时间以所选择月份为主，如基准时间为2015-02，<br/>
 						 则每个型号的数值为：当前型号2014.3~2015.2期间的维修数/当前型号2013.12~2014.11期间的发货数*100%。<br/>
			 		</td>
			 	</tr>
			 	
			 	<tr>
			 		<td class="td_center">P控图</td>
			 		<td>
			 			1.X轴：以2015.02月（含）为基准往前推12个月，也即2014.3~2015.2；每个月份为一个统计单位（匹配时间序列矩阵中的生产月份）； <br/>
  						2.各点数值：各统计单位下，满足查询条件且累计到基准月份的维修率即【（从生产月份当月到基准月份的维修数之和）/生产当月的生产数 *1000000】；<br/>
  						红色折线数值：为上控制线，上控制线取值详见时间序列矩阵-P控制线计算方法；<br/>
 						蓝色折线数值：为下控制线，下控制线取值详见时间序列矩阵-P控制线计算方法；<br/>
			 		
			 		</td>
			 	</tr>
			 </tbody>
		</table>
	
	</div>
</div>