<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/machineType/machineType.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr><td>
					机型类别： 
				</td>
				<td>
					<input type="text" name="machineType" value="${param.machineType}"/>
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
				<shiro:hasPermission name="base:machineType:ADD">
			    	<li><a class="add" href="base/machineType/addMachine.do"  target="dialog" height="400" width="800"  rel="addDown" title="新增-机型"><span>新增</span></a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="base:machineType:EDIT">
					<li><a class="edit" href="base/machineType/editMachine.do?id={key}"   target="dialog"  height="400" width="800"  rel="editUser" title="修改-机型"><span>修改</span></a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="base:machineType:DEL">
					<li><a class="delete" href="base/machineType/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该记录吗？"><span>删除</span></a></li>
				</shiro:hasPermission>
			</ul>
		</div>

	<table class="table" width="100%" layoutH="121">
		<thead>
			<tr>
				<th width="5%">选择</th>
				<th width="15%">机型类别</th>
				<th width="20%">创建时间</th>
				<th width="20%">创建用户</th>
				<th width="20%">修改时间</th>
				<th width="20%">修改用户</th>
				
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key" value="${o.id }">
					</td>
					<td>${o.machineType}</td>
					<td><fmt:formatDate value="${o.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${o.createUser}</td>
					<td><fmt:formatDate value="${o.updateTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${o.updateUser}</td>
					
					
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

