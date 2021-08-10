<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/basicdata/relationlist.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					区域（CRM服务中心）： 
				</td>
				<td>
					<input type="text" name="crm" value="${param.crm}"/>
				</td>				
				<td>
					分公司仓库（MES）：
				</td>
				<td>
				<input type="text" name="mes" value="${param.mes}"/>
					
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
			    
				<th width="30%">分公司仓库</th>
				<th width="30%">区域</th>
				
				<th width="15%">录入人</th>
				<th width="25%">录入时间</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				
					<%--需维护资料--%>
					<td>${o.crm}</td>
					<td>${o.mes}</td>
					<td>${o.entryName}</td>
					<td><fmt:formatDate value="${o.entryTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					
					
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
