<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<form id="missDataForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/missData/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent dropdownSearchBar">
			<tr style="line-height: 28px">
				<th style="width: 60px">查询类型：</th>
				<td style="width: 51px">
					<select id="missStatisType" name="statisType">
						<option value="ship">发货</option>
						<option value="install" ${vo.statisType eq 'install' ? 'selected':''}>安装</option>
						<option value="repair" ${vo.statisType eq 'repair' ? 'selected':''}>维修</option>
					</select>
				</td>
				<th style="width: 60px">查询时间：</th>
				<td style="width: 120px">
					<input type="text" name="startTime" placeholder="请输入日期" onclick="laydate()" size="7" readonly="true" value="${vo.startTime}"/>
					至
					<input type="text" name="endTime" placeholder="请输入日期" onclick="laydate()" size="7" readonly="true" value="${vo.endTime}"/>
				</td>
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
					<div class="buttonActive"><div class="buttonContent"><button onclick="exportMissDataExcel()">导出</button></div></div>
				</td>
			</tr>
		</table>
	</div>
</form>
<div class="pageContent">
	<table class="table" style="width: 100%" layoutH="90">
		<c:if test="${vo.statisType eq 'ship'}"> <!-- 查询发货时 -->
			<thead>
				<tr>
					<th>主机条码</th>
					<th>机型类别</th>
					<th>物料编码</th>
					<th>物料系列</th>
					<th>物料型号</th>
					<th>物料名称</th>
					<th>服务中心</th>
					<th>产线编号</th>
					<th>发货时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="o">
					<tr>
						<td>${o.serialNumber}</td>
						<td>${o.productType}</td>
						<td>${o.partNumber}</td>
						<td>${o.partFamily}</td>
						<td>${o.partType}</td>
						<td>${o.partName}</td>
						<td>${o.region}</td>
						<td>${o.productLineNumber}</td>
						<td>${o.shipDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</c:if>
		<c:if test="${vo.statisType eq 'install'}"> <!-- 查询安装时 -->
			<thead>
				<tr>
					<th>主机条码</th>
					<th>服务工单</th>
					<th>物料名称</th>
					<th>服务中心</th>
					<th>安装时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="o">
					<tr>
						<td>${o.serialNumber}</td>
						<td>${o.orderNumber}</td>
						<td>${o.partName}</td>
						<td>${o.region}</td>
						<td>${o.installDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</c:if>
		<c:if test="${vo.statisType eq 'repair'}"> <!-- 查询维修时 -->
			<thead>
				<tr>
					<th>主机条码</th>
					<th>维修工单</th>
					<th>物料名称</th>
					<th>故障大类</th>
					<th>故障小类</th>
					<th>维修时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="o">
					<tr>
						<td>${o.serialNumber}</td>
						<td>${o.orderNumber}</td>
						<td>${o.partName}</td>
						<td>${o.faultTypeName}</td>
						<td>${o.faultReasonName}</td>
						<td>${o.repairDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</c:if>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<script>
	function exportMissDataExcel() {
		var myForm = document.createElement("form");
		myForm.action= "base/missData/exportExcel.do";
		myForm.method="post";
		myForm.target="noexistForm";
		var missStatisType = $("#missStatisType option:selected", navTab.getCurrentPanel()).val();
		var startTime = $("input[name = 'startTime']", navTab.getCurrentPanel()).val();
		var endTime = $("input[name = 'endTime']", navTab.getCurrentPanel()).val();
		
		var missStatisTypeInput = document.createElement("input");
		missStatisTypeInput.setAttribute("name", "statisType");
		missStatisTypeInput.setAttribute("value", missStatisType);
		myForm.appendChild(missStatisTypeInput);
		
		var missStartTime = document.createElement("input");
		missStartTime.setAttribute("name", "startTime");
		missStartTime.setAttribute("value", startTime);
		myForm.appendChild(missStartTime);
		
		var missEndTime = document.createElement("input");
		missEndTime.setAttribute("name", "endTime");
		missEndTime.setAttribute("value", endTime);
		myForm.appendChild(missEndTime);
		document.body.appendChild(myForm);
		myForm.submit();
	}
</script>