<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<form id="missDataForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/missData/supplierList.do" method="post">
	<div class="searchBar">
		<table class="searchContent dropdownSearchBar">
			<tr style="line-height: 28px">
				<th style="width: 80px">供应商名称：</th>
				<td style="width: 104px">
					<input type="text" name="supplierName" value="${vo.supplierName}" size="12" />
				</td>
				<th style="width: 80px">供应商编码：</th>
				<td style="width: 104px">
					<input type="text" name="supplierNumber" value="${vo.supplierNumber}" size="12" />
				</td>
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
					<div class="buttonActive"><div class="buttonContent"><button onclick="exportMissSupplierDataExcel()">导出</button></div></div>
				</td>
			</tr>
		</table>
	</div>
</form>
<div class="pageContent">
	<table class="table" style="width: 100%" layoutH="90">
		<thead>
			<tr>
				<th>供应商编码</th>
				<th>供应商名称</th>
				<th>产线</th>
				<th>工厂</th>
				<th>供应商简称</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<td>${o.supplierNumber}</td>
					<td>${o.supplierName}</td>
					<td>${o.productLineName}</td>
					<td>${o.factory}</td>
					<td>${o.shortSupplierName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<script>
	function exportMissSupplierDataExcel() {
		var myForm = document.createElement("form");
		myForm.action= "base/missData/exportSupplierExcel.do";
		myForm.method="post";
		myForm.target="noexistForm";
		var supplierName = $("input[name='supplierName']", navTab.getCurrentPanel()).val();
		var supplierNumber = $("input[name='supplierNumber']", navTab.getCurrentPanel()).val();
		
		var supplierNameInput = document.createElement("input");
		var supplierNumberInput = document.createElement("input");
		
		supplierNameInput.setAttribute("name", "supplierName");
		supplierNameInput.setAttribute("value", supplierName);
		myForm.appendChild(supplierNameInput);
		
		supplierNumberInput.setAttribute("name", "supplierNumber");
		supplierNumberInput.setAttribute("value", supplierNumber);
		myForm.appendChild(supplierNumberInput);
		document.body.appendChild(myForm);
		myForm.submit();
	}
</script>