<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<form id="missDataForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/missData/partList.do" method="post">
	<div class="searchBar">
		<table class="searchContent dropdownSearchBar">
			<tr style="line-height: 28px">
				<th style="width: 129px">物料型号：</th>
				<td style="width: 60px">
					<input type="text" id="missPartData_partNumber" name="partNumber" value="${vo.partNumber}" />
				</td>
				<th style="width: 60px">物料名称：</th>
				<td>
					<input type="text" id="missPartData_partName" name="partName" value="${vo.partName}" />
					<input type="hidden" id="missPartData_type" name="type" value="${vo.type}" />
				</td>
			</tr>
			<tr>
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
					<div class="buttonActive"><div class="buttonContent"><button onclick="exportMissPartDataExcel()">导出</button></div></div>
				</td>
			</tr>
		</table>
	</div>
</form>
<div class="pageContent">
	<table class="table" style="width: 100%" layoutH="120">
		<thead>
			<tr>
				<th>物料编码</th>
				<th>物料名称</th>
				<th>机型类别</th>
				<th>物料分类</th>
				<th>产品成熟度</th>
				<th>物料级别</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<td>${o.partNumber}</td>
					<td>${o.partName}</td>
					<td>${o.productType}</td>
					<td>${o.partType}</td>
					<td>${o.isNewS}</td>
					<td>${o.partLevelS}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<script>
	function exportMissPartDataExcel() {
		var myForm = document.createElement("form");
		myForm.action= "base/missData/exportPartExcel.do";
		myForm.method="post";
		myForm.target="noexistForm";
		var partNumber = $("#missPartData_partNumber", navTab.getCurrentPanel()).val();
		var partName = $("#missPartData_partName", navTab.getCurrentPanel()).val();
		
		var partNumberInput = document.createElement("input");
		partNumberInput.setAttribute("name", "partNumber");
		partNumberInput.setAttribute("value", partNumber);
		
		var partNameInput = document.createElement("input");
		partNameInput.setAttribute("name", "partName");
		partNameInput.setAttribute("value", partName);
		
		var typeInput = document.createElement("input");
		typeInput.setAttribute("name", "type");
		typeInput.setAttribute("value", "0");
		
		myForm.appendChild(partNumberInput);
		myForm.appendChild(partNameInput);
		myForm.appendChild(typeInput);
		document.body.appendChild(myForm);
		myForm.submit();
	}
</script>