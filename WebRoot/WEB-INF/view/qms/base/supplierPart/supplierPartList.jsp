<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<div class="pageHeader">
<form id="supplierPartForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/supplierRef/supplierPartList.do" method="post">
	<div class="searchBar ">
		<table class="searchContent ">
			<tr style="line-height: 28px">
			    <td>
			        物料编号：
			    </td>
			    <td>
			       <input type="search" name="partNumber" size="15" value="${param.partNumber }">
			    </td>
			     <td>
			        新供应商编号：
			    </td>
			    <td>
			       <input type="search" name="supplierNumberN" size="15" value="${param.supplierNumberN }">
			    </td>
			    <td>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				</td>
				<td>
					<div class="buttonActive"><div class="buttonContent"><button onclick="exportSupplierPartExcel()">导出</button></div></div>
				</td>
			</tr>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasPermission name="base:supplierPartList:ADD">
		    	<li><a class="add" href="base/supplierRef/addSupplierPart.do" target="dialog" height="400" width="800" rel="addDown" title="新增-供应商物料关系对照"><span>新增</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="base:supplierPartList:EDIT">
				<li><a class="edit" href="base/supplierRef/editSupplierPart.do?id={key}" target="dialog" height="400" width="800" rel="editUser" title="修改-供应商物料关系对照"><span>修改</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="base:supplierPartList:DEL">
				<li><a class="delete" href="base/supplierRef/deleteSupplierPart.do?id={key}" target="ajaxTodo" title="确定删除该记录吗？"><span>删除</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	<table class="table" style="width: 100%" layoutH="120">
		<thead>
			<tr>
				<th>选择</th>
				<th>物料编码</th>
				<th>物料名称</th>
				<th>供应商代号</th>
				<th>供应商简称</th>
				<th>新供应商编号</th>
				<th>供应商名称</th>
				<th>旧供应商编号</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
			<tr target="key" rel="${o.id}">
				<td>
					<input type="radio" group="code" name="key" value="${o.id}">
				</td>
				<td>${o.partNumber}</td>
				<td>${o.partName}</td>
				<td>${o.supplierCode}</td>
				<td>${o.supplierShortName}</td>
				<td>${o.supplierNumberN}</td>
				<td>${o.supplierNumber}</td>
				<td>${o.supplierName}</td>
			</tr>
			</c:forEach>			
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<script>
function exportSupplierPartExcel() {
	var myForm = document.createElement("form");
	myForm.action= "base/supplierRef/exportSupplierPartExcel.do";
	myForm.method="post";
	myForm.target="noexistForm";
	document.body.appendChild(myForm);
	myForm.submit();
}
</script>