<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<div class="pageHeader">
<form id="mcPartForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="quality/MCPart/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr style="line-height: 28px">
				<td>MES物料编码：</td>
				<td><input type="search" name="mesPartNumber" value="${vo.mesPartNumber}"></td>
			   	<td>CRM物料编码：</td>
			   	<td><input type="search" name="crmPartNumber" value="${vo.crmPartNumber}"></td>
			   	<td>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				</td>
<!-- 				<td> -->
<!-- 					<div class="buttonActive"><div class="buttonContent"><button onclick="exportSupplierRefExcel()">导出</button></div></div> -->
<!-- 				</td> -->
			</tr>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasPermission name="part:mcPart:ADD">
		    	<li><a class="add" href="quality/MCPart/add.do" target="dialog" height="400" width="800" rel="addDown" title="新增-MES与CRM物料对照关系"><span>新增</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="part:mcPart:EDIT">
				<li><a class="edit" href="quality/MCPart/edit.do?id={key}" target="dialog" height="400" width="800" rel="editUser" title="修改-MES与CRM物料对照关系"><span>修改</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="part:mcPart:DEL">
				<li><a class="delete" href="quality/MCPart/delete.do?id={key}" target="ajaxTodo" title="确定删除该记录吗？"><span>删除</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	<table class="table" style="width: 100%" layoutH="120">
		<thead>
			<tr>
				<th>选择</th>
				<th>MES物料编号</th>
				<th>MES物料名称</th>
				<th>CRM物料编号</th>
				<th>CRM物料名称</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
			<tr target="key" rel="${o.id}">
				<td>
					<input type="radio" group="code" name="key" value="${o.id}">
				</td>
				<td>${o.mesPartNumber}</td>
				<td>${o.mesPartName}</td>
				<td>${o.crmPartNumber}</td>
				<td>${o.crmPartName}</td>
			</tr>
			</c:forEach>			
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>