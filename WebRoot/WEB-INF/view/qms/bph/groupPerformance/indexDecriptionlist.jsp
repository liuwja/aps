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
<script type="text/javascript">
function searchContent()
{
	var index = $("#indexName",navTab.getCurrentPanel()).val();
	if(index==null || index=='')
	{
		return;
	}else
	{
		
	}
}
</script>
<div class="pageContent" id="c_content" >
	<div class="c_top">
		<h3 class="t_h3">各考核指标计算逻辑说明 <!--   &nbsp;&nbsp; &nbsp;&nbsp;      <span style="font-weight:500;">指标名称：</span>	<input id="indexName" > <button type="button" onclick="searchContent()">查找 --></h3>
	</div>
	<div class="box">
		<table id="b_table" layoutH="60">
			 <thead>
			 	<tr class="th_tr">
			 		<th width="20%">考核指标（数据）</th>
			 		<th width="80%">说明 （计算方式）</th>
			 	</tr>
			 </thead>
			 <tbody class="tb">
			 <c:forEach items="${map }" var="index">
			   <tr>
			 		<td class="td_center">${index.key }</td>
			 		<td>
			 			${index.value }
			 		</td>
			 	</tr>
			 </c:forEach>
			 	<!-- <tr>
			 		<td class="td_center">冲压-客户质量(EA01)-组装退次率（自制件导致的组装维修率）(A1)</td>
			 		<td>
			 			 实际值 =【月度责任班组的油烟机产品退次数（不良数）】/【月度组装油烟机投产数】*100%*1000000<br>
			 			 得分公式 : 1. 目标=<实际<=基准，A1=【ABS(实际-基准)/ABS(目标-基准)*40+60】*该指标权重；<br>
                         2.实际=0，A1=150*该指标权重；<br>
                         3.0&lt;实际&lt;目标，A1=MIN(目标/实际*100,150)*该指标权重；<br>
                         4.否则  A1=0<br>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">冲压-客户质量(EA01)-喷涂退次率（A2）</td>
			 		<td>
			 			 实际值 =喷涂退次数/喷涂总数<br>
			 			 得分公式 : 1. 目标=<实际<=基准，A2=【ABS(实际-基准)/ABS(目标-基准)*40+60】*该指标权重；<br>
                         2.实际=0，A2=150*该指标权重；<br>
                         3.0&lt;实际&lt;目标，A2=MIN(目标/实际*100,150)*该指标权重；<br>
                         4.否则  A2=0<br>
			 		</td>
			 	</tr>
			 	
			 	<tr>
			 		<td class="td_center">冲压-客户质量(EA01)-客户端批不良-冲压责任（A3）</td>
			 		<td>
			 			 实际值 =月度《5M1E不合格项记录单》中包含料、对应班组&市场批量不良事故中包含对应班组，发生流程节点为A\B\C的质量风险分数(QRPN值)对应分数<br>
			 			 得分公式 = A3为负数，根据（发生流程节点*批量大小*质量后果）的结果值从扣分规则中获取对应的扣分值
			 		</td>
			 	</tr>
			 	
			 	<tr>
			 		<td class="td_center">冲压-过程质量(EA02)-冲压一次合格率（A4）</td>
			 		<td>
			 			 实际值 =【月度责任班组的合格品数量之和】/【月度责任班组的总数】*100%<br>
			 			1.  基准=<实际<=目标，B4=【(实际-基准)/(目标-基准)*40+60】*该指标权重；
                        2. 实际＞目标，B4=MIN【(实际-基准)/(目标-基准)*100,150】*该指标权重；
                      3. 否则  B4=0
			 		</td>
			 	</tr>
			 	
			 	<tr>
			 		<td class="td_center">冲压-过程质量(EA02)-IPQC批次不合格率（A5）</td>
			 		<td>
			 			 实际值 =【月度责任班组巡检不良批次数】/【月度责任班组巡检总批次数】*100%<br>
			 			 得分公式 = 1. 目标=<实际<=基准，A5=【ABS(实际-基准)/ABS(目标-基准)*40+60】*该指标权重；<br>
                         2.实际=0，A5=150*该指标权重；<br>
                         3.0&lt;实际&lt;目标，A5=MIN(目标/实际*100,150)*该指标权重；<br>
                         4.否则  A5=0<br>
			 		
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">冲压-过程质量(EA02)-冲压批不良（A6）</td>
			 		<td>
			 			 实际值 =《前工序IPQC巡检表》中包含对应班组,总数≥100，不良率≥20%且发生流程节点为D的质量风险分数(QRPN)之和<br>
			 			 得分公式 = 每单扣2.5分*实际值
			 		
			 		</td>
			 	</tr>
			 	
			 	<tr>
			 		<td class="td_center">组装-客户质量(EC01)-OQC（有责）（C1）</td>
			 		<td>
			 			 实际值 =OQC抽检责任单位判定为对应班组的不良台数<br>
			 			 得分公式 ： 不良台数=0时，C1=该考核指标总分；<br>
                                                 不良台数=1时，C1= -【该考核指标总分 * 50%】；<br>
                                                 不良台数>1时，C1= -【该考核指标总分 * 100%】；<br>
			 		
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">组装-客户质量(EC01)-开箱不良次数+有责）（C2）</td>
			 		<td>
			 			 实际值 = 不良来源为“开箱不良”的次数<br>
			 			 得分公式 ： 不良台数=0时，C2=该考核指标总分；<br>
						不良台数=1时，C2= -【该考核指标总分 * 50%】；<br>
						不良台数>1时，C2= -【该考核指标总分 * 101%】；<br>
			 		
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">组装-客户质量(EC01)-市场批不良次数（有责）（C3）</td>
			 		<td>
			 			 实际值 = 不良来源为“流行性不良”的次数<br>
			 			 得分公式 ： 次数>=1时，C3= -【 该考核项目（客户质量）总分】
			 		
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">组装-客户质量(EC02)-组装在线不良（有责）（C4）</td>
			 		<td>
			 			 实际值 = 【组装维修时，责任单位判定为“组装车间”且对应班组的产品数】/【对应班组总投产数】*100%<br>
			 			 得分公式 ：1. 目标=<实际<=基准，C4=【ABS(实际-基准)/ABS(目标-基准)*40+60】*该指标权重；<br>
						           2.实际=0，C4=150*该指标权重；<br>
						         3.0&lt;实际&lt;目标，C4=MIN(目标/实际*100,150)*该指标权重；<br>
						         4.否则  C4=0<br>
			 		
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">喷涂-客户质量(EB01)-组装上线退次不良率（B1）</td>
			 		<td>
			 			 实际值 = 【月度责任班组的退次数量】/【月度责任班组入库数量】*1000000<br>
			 			 得分公式 ：1. 目标=<实际<=基准，A5=【ABS(实际-基准)/ABS(目标-基准)*40+60】*该指标权重；<br>
                         2.实际=0，A5=150*该指标权重；<br>
                         3.0&lt;实际&lt;目标，A5=MIN(目标/实际*100,150)*该指标权重；<br>
                         4.否则  A5=0<br>
			 		
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">喷涂-客户质量(EB01)-客户端物料批不良-涂装责任（B2）</td>
			 		<td>
			 			 实际值 = 月度《5M1E不合格项记录单》中包含料、对应班组&市场批量不良事故中包含对应班组，发生流程节点为A\B\C的质量风险分数(QRPN值)对应分数<br>
			 			 得分公式 ：B2为负数，根据（发生流程节点*批量大小*质量后果）的结果值从扣分规则中获取对应的扣分值
			 		
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">喷涂-客户质量(EB02)-涂装IPQC不良率（B3）</td>
			 		<td>
			 			 实际值 = 【月度责任班组巡检不良批次数】/【月度责任班组巡检总批次数】*100%<br>
			 			 得分公式 ：1. 目标=<实际<=基准，A5=【ABS(实际-基准)/ABS(目标-基准)*40+60】*该指标权重；<br>
                         2.实际=0，A5=150*该指标权重；<br>
                         3.0&lt;实际&lt;目标，A5=MIN(目标/实际*100,150)*该指标权重；<br>
                         4.否则  A5=0<br>
			 		
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">喷涂-客户质量(EB02)-喷涂一次合格率（B4）</td>
			 		<td>
			 			 实际值 = 【月度下件合格数】/【月度挂件数】*100%;下件合格数和挂件数都不含返工工单的数量<br>
			 			 得分公式: 1. 基准=&lt;实际&lt;=目标，B4=【(实际-基准)/(目标-基准)*40+60】*该指标权重；<br>
                                  2.实际＞目标，B4=MIN【(实际-基准)/(目标-基准)*100,150】*该指标权重；<br>
                              3.否则  B4=0
			 		
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">喷涂-客户质量(EB02)-喷涂批不良（B6）</td>
			 		<td>
			 			 实际值 = 《前工序IPQC巡检表》中包含对应班组,总数≥100，不良率≥20%且发生流程节点为D的质量风险分数(QRPN)之和<br>
			 			 得分公式 :B6为负数，每单扣2.5分
			 		
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">IQC-客户质量(EF01)-组装物料批不良-供应商责任（包含停线）（F1）</td>
			 		<td>
			 			 实际值 = 月度《5M1E不合格项记录单》中包含料、责任单位为外协,且发生流程节点为A\B\C的质量风险分数(QRPN)之和<br>
			 			 得分公式 :1. 目标=<实际<=基准，F1=【ABS(实际-基准)/ABS(目标-基准)*40+60】*该指标权重；<br>
                         2.实际=0，F1=150*该指标权重；<br>
                         3.0&lt;实际&lt;目标，F1=MIN(目标/实际*100,150)*该指标权重；<br>
                         4.否则  F1=0<br>
			 		
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">IQC-客户质量(EF01)-OQC不良（有责）（F2）</td>
			 		<td>
			 			 实际值 = OQC抽检责任单位判定为对应班组的不良台数<br>
			 			 得分公式 :该项权重分*50%*OQC不良台数（OQC不良时判定责任单位为IQC）
			 		
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">OQC-客户质量(EG01)-市场批量不良次数+有责（G1）</td>
			 		<td>
			 			 实际值 = 月度责任班组不良来源为“流行性不良”的次数<br>
			 			 得分公式 :次数>=1时，G1= -【 该考核项目（客户质量）总分】
			 		
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">OQC-客户质量(EG01)-开箱不良次数+有责（G2）</td>
			 		<td>
			 			 实际值 = 月度责任班组不良来源为“开箱不良”的次数<br>
			 			 得分公式 :次数>=1时，G2= - 【5分/工厂组装班组数】/次，至该考核项目（客户质量）总分扣完为止
			 		
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">精加工-客户质量(ED01)-组装退次率（自制件导致的组装维修率）（D1）</td>
			 		<td>
			 			 实际值 = 【月度责任班组的退次数（不良数）】/【月度组装投产数】*100%*1000000<br>
			 			 得分公式 :1. 目标=<实际<=基准，D1=【ABS(实际-基准)/ABS(目标-基准)*40+60】*该指标权重；<br>
                         2.实际=0，D1=150*该指标权重；<br>
                         3.0&lt;实际&lt;目标，D1=MIN(目标/实际*100,150)*该指标权重；<br>
                         4.否则  D1=0<br>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">精加工-客户质量(ED01)-喷涂退次率（D2）</td>
			 		<td>
			 			 实际值 = 喷涂退次数/喷涂总数<br>
			 			 得分公式 :1. 目标=<实际<=基准，D2=【ABS(实际-基准)/ABS(目标-基准)*40+60】*该指标权重；<br>
                         2.实际=0，D2=150*该指标权重；<br>
                         3.0&lt;实际&lt;目标，D2=MIN(目标/实际*100,150)*该指标权重；<br>
                         4.否则  D2=0<br>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">精加工-客户质量(ED01)-喷涂退次率（D3）</td>
			 		<td>
			 			 实际值 = 月度《5M1E不合格项记录单》中包含料、对应班组&市场批量不良事故中包含对应班组，发生流程节点为A\B\C的质量风险分数(QRPN值)对应分数<br>
			 			 得分公式 :D3为负数，根据（发生流程节点*批量大小*质量后果）的结果值从扣分规则中获取对应的扣分值
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">精加工-过程质量(ED02)-精加工一次合格率（D4）</td>
			 		<td>
			 			 实际值 = 【月度责任班组的合格产品数量之和】/【月度责任班组的总数（指定型号的产品）（最后道工序）】*100%<br>
			 			 得分公式 :1. 目标=<实际<=基准，D4=【ABS(实际-基准)/ABS(目标-基准)*40+60】*该指标权重；<br>
                         2.实际=0，D4=150*该指标权重；<br>
                         3.0&lt;实际&lt;目标，D4=MIN(目标/实际*100,150)*该指标权重；<br>
                         4.否则  D4=0<br>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">精加工-过程质量(ED02)-IPQC批次不良率（D5）</td>
			 		<td>
			 			 实际值 = 【月度责任班组巡检不良批次数】/【月度责任班组巡检总批次数】*100%<br>
			 			 得分公式 :1. 目标=<实际<=基准，D5=【ABS(实际-基准)/ABS(目标-基准)*40+60】*该指标权重；<br>
                         2.实际=0，D5=150*该指标权重；<br>
                         3.0&lt;实际&lt;目标，D5=MIN(目标/实际*100,150)*该指标权重；<br>
                         4.否则  D5=0<br>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td class="td_center">精加工-过程质量(ED02)-精加工批不良（D6）</td>
			 		<td>
			 			 实际值 = 《前工序IPQC巡检表》中包含对应班组,总数≥100，不良率≥20%且发生流程节点为D的质量风险分数(QRPN)之和<br>
			 			 得分公式 :D6为负数，每单扣2.5分
			 		</td>
			 	</tr> -->
			 </tbody>
		</table>
	
	</div>
</div>