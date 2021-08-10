<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/Fault/FaultReason.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					故障代码： 
				</td>
				<td>
					<input type="text" name="code" value="${param.code}"/>
				</td>				
				<td>
					故障名称：
				</td>
				<td>
				<input type="text" name="name"  value="${param.name}"/>
					
				</td>
				
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">

	</div>
	<table class="list" width="100%" layoutH="97">
		<thead>
			<tr>
			    
				<th width="5%">序号</th>
				<th width="15%">故障代码</th>
				<th width="15%">故障名称</th>
				<th width="5%">是否有效</th>
				<th width="20%">创建时间</th>
				<th width="10%">创建用户</th>
				<th width="20%">修改时间</th>
				<th width="10%">修改用户</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				
					<%--需维护资料--%>
					<td>${o.code}</td>
					<td>${o.name}</td>
					<td>${o.valid}</td>
					
					<td><fmt:formatDate value="${o.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${o.createUser}</td>
					<td><fmt:formatDate value="${o.lastUpdateTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${o.lastUpdateUser}</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../_frag/pager/panelBar.jsp"></c:import>
</div>

