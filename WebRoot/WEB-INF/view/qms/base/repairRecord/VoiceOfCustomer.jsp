<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<!-- 分页相关 -->
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
$(function(){
	$("#resetBtn", navTab.getCurrentPanel()).click(function(){
	   $("form input", navTab.getCurrentPanel()).val("");
    });
})
</script>
<div class="pageHeader" style="position:static">
	<form id="voiceOfCustomerForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/voiceOfCustomer/voiceofcustomerList.do" method="post">
	<div class="searchBar" style="">
		<table class="searchContent">
			<tr>
				<td>诉求类型： </td>
				<td><input type="search" name="typeAppeal" value="${param.typeAppeal}" size="15"/></td>
				<td>服务中心： </td>
				<td><input type="search" name="serviceCenter" value="${param.serviceCenter}" size="15"/></td>
				<td>产品编码： </td>
				<td><input type="search" name="serialNumber" value="${param.serialNumber}" size="15"/></td>
				<td>产品名称： </td>
				<td><input type="search" name="nameProduct" value="${param.nameProduct}" size="15"/></td>
				<td>时间： </td>
				<td>
				<input size="6" type="text" id="startTime" name="startTime" placeholder="请输入日期" onclick="laydate()" value="${param.startTime}" readonly="readonly"/>
				至 <input size="6" type="text" id="endTime" name="endTime" placeholder="请输入日期" onclick="laydate()" value="${param.endTime}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>诉求编号： </td>
				<td><input type="search" name="numberAppeal" value="${param.numberAppeal}" size="15"/></td>
				<td>派工单号： </td>
				<td><input type="search" name="orderNumber" value="${param.orderNumber}" size="15"/></td>
				<td>故障大类： </td>
				<td><input type="search" name="bfaultClass" value="${param.bfaultClass}" size="15"/></td>
				<td>故障小类： </td>
				<td><input type="search" name="sfaultClass" value="${param.sfaultClass}" size="15"/></td>
				<td colspan="2">
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">查找</button>
						</div>
					</div>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onClick="exportExcelByCommon('base/voiceOfCustomer/exportExcel.do', '#voiceOfCustomerForm')">导出数据</button>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" style="width: 280%" layoutH="140">
		<thead>
			<tr>
			 	<th align="center" style="width: 2%" >序号</th>
			 	<th align="center" style="width: 4%" >时间</th>
				<th align="center" style="width: 3%">诉求类型</th>
				<th align="center" style="width: 3%">客户</th>
				<th align="center" style="width: 3%">省</th>
				<th align="center" style="width: 3%">市</th>
				<th style="width: 3%">区县</th>
				<th style="width: 4%">服务中心</th>
				<th style="width: 5%">产品名称</th>
				<th style="width: 4%">简化型号</th>
				<th style="width: 4%">产品类型</th>
				<th style="width: 5%">产品编号</th>
				<th style="width: 3%">VOC一级分类</th>
				<th style="width: 3%">VOC二级分类</th>
				<th style="width: 4%">诉求编号</th>
				<th style="width: 10%">诉求详细信息</th>
				<th style="width: 3%">派工单号</th>
				<th style="width: 5%">服务网点</th>
				<th style="width: 3%">服务技师</th>				
				<th style="width: 3%">服务项目</th>
				<th style="width: 3%">故障大类</th>
				<th style="width: 3%;min-width:10px;">故障小类</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o" varStatus="s">
				<tr>
					<td align="center">
				${o.rowId }
				</td>
					<td>${o.dateTime}</td>
					<td>${o.typeAppeal}</td>
					<td>${o.customer}</td>
					<td>${o.province}</td>
					<td>${o.city}</td>
					<td>${o.county}</td>
					<td>${o.serviceCenter}</td>
					<td>${o.nameProduct }</td>
					<td>${o.modelProduct }</td>
					<td>${o.typeProduct}</td>
					<td>${o.serialNumber}</td>
					<td>${o.type1}</td>
					<td>${o.type2}</td>
					<td>${o.numberAppeal}</td>
					<td>${o.detail}</td>
					<td>${o.orderNumber}</td>
					<td>${o.serviceLocation}</td>
					<td>${o.technician}</td>
					<td>${o.serviceProject}</td>
					<td>${o.bfaultClass}</td>
					<td>${o.sfaultClass}</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页相关 -->
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>