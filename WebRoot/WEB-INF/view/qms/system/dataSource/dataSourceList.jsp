<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<div class="pageHeader">
<form id="DataSourceForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/dataSource/list.do" method="post">
	<div class="searchBar ">
		<table class="searchContent ">
			<tr style="line-height: 28px">
			</tr>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasPermission name="system:dataSourceList:ADD">
		    	<li><a class="add" href="system/dataSource/addDataSource.do" target="dialog" height="400" width="800" rel="addDown" title="新增-数据来源说明"><span>新增</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="system:dataSourceList:EDIT">
				<li><a class="edit" href="system/dataSource/editDataSource.do?id={key}" target="dialog" height="400" width="800" rel="editUser" title="修改-数据来源说明"><span>修改</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="system:dataSourceList:DEL">
				<li><a class="delete" href="system/dataSource/delete.do?id={key}" target="ajaxTodo" title="确定删除该记录吗？"><span>删除</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	<table class="table" style="width: 100%">
		<thead>
			<tr style="text-align: center;">
				<th style="width: 2%; text-align: center;">选择</th>
				<th style="width: 10%; text-align: center;">第一级菜单</th>
				<th style="width: 10%; text-align: center;">第二级菜单</th>
				<th style="width: 10%; text-align: center;">第三级菜单</th>
				<th style="width: 10%; text-align: center;">图表类型</th>
				<th style="width: 59%; text-align: center;">说明</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
			<tr target="key" rel="${o.id}">
				<td style="text-align: center;">
					<input type="radio" group="code" name="key" value="${o.id}">
				</td>
				<td style="text-align: center;">${o.accordion}</td>
				<td style="text-align: center;">${o.folder}</td>
				<td style="text-align: center;">${o.menu}</td>
				<td style="text-align: center;">${o.chartType}</td>
				<td style="text-align: center;">${o.description}</td>
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
	myForm.submit();
}
</script>