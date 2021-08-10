<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<div class="pageHeader">
<form id="supplierRefForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/supplierRef/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent ">
			<tr style="line-height: 28px">
			   <td>
			           新供应商编号：
			   </td>
			   <td>
			     <input type="search" name="supplierNumberN" value="${param.supplierNumberN }">
			   </td>
			    <td>
			           新供应商名称：
			   </td>
			   <td>
			     <input type="search" name="supplierNameN" value="${param.supplierNameN }">
			   </td>
			   <td>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				</td>
				<td>
					<div class="buttonActive"><div class="buttonContent"><button onclick="exportSupplierRefExcel()">导出</button></div></div>
				</td>
			</tr>
		</table>
	</div>
</form>
</div>
<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasPermission name="part:supplierRefList:ADD">
		    	<li><a class="add" href="base/supplierRef/addSupplierRef.do" target="dialog" height="400" width="800" rel="addDown" title="新增-供应商对照关系"><span>新增</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="part:supplierRefList:EDIT">
				<li><a class="edit" href="base/supplierRef/editSupplierRef.do?id={key}" target="dialog" height="400" width="800" rel="editUser" title="修改-供应商对照关系"><span>修改</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="part:supplierRefList:DEL">
				<li><a class="delete" href="base/supplierRef/delete.do?id={key}" target="ajaxTodo" title="确定删除该记录吗？"><span>删除</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" style="width: 100%" layoutH="120">
		<thead>
			<tr>
				<th>选择</th>
				<th>旧供应商编号</th>
				<th>新供应商编号</th>
				<th>旧供应商名称</th>
				<th>新供应商名称</th>
<!-- 				<th>供应商简称</th> -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
			<tr target="key" rel="${o.id}">
				<td>
					<input type="radio" group="code" name="key" value="${o.id}">
				</td>
				<td>${o.supplierNumber}</td>
				<td>${o.supplierNumberN}</td>
				<td>${o.supplierName}</td>
				<td>${o.supplierNameN}</td>
<!-- 				<td>${o.supplierShortName}</td> -->
			</tr>
			</c:forEach>			
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<script>
function exportSupplierRefExcel() {
	var myForm = document.createElement("form");
	myForm.action= "base/supplierRef/exportExcel.do";
	myForm.method="post";
	myForm.target="noexistForm";
	document.body.appendChild(myForm);
	myForm.submit();
}
</script>