<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/sysOperateLog/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					操作人： 
				</td>
				<td>
					<input type="search" name="operator" value="${param.operator}"/>
				</td>				
				<td>
					操作时间： 
				</td>
				<td>
					 <input type="text"   name="operateTime" placeholder="请输入日期" onclick="laydate()" value="${param.operateTime }" readonly="true"/>
				</td>
		       
				
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	
	<table class="table" width="100%" layoutH="93">
		<thead>
			<tr>
				<th >操作人</th>
				<th width="15%">操作时间</th>
				<th >操作内容</th>
				<th >操作方式</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<td>${o.operator}</td>
					<td><fmt:formatDate value="${o.operateTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${o.content}</td>
					<td><c:if test="${o.opType eq '0'}">新增</c:if><c:if test="${o.opType eq '1'}">修改</c:if>
					    <c:if test="${o.opType eq '2'}">删除</c:if><c:if test="${o.opType eq '3'}">查看</c:if></td> 
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

