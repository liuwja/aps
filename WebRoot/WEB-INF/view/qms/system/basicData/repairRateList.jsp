<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/basicdata/repairRateList.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					机型类别： 
				</td>
				<td>
					<input type="text" name="machineType" value="${param.machineType}"/>
				</td>				
				<td>
					维修截止时间：
				</td>
				<td>
				<input type="text" name="yearMon" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true" value="${param.yearMon}"/>
					
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
				<shiro:hasPermission name="ps:psinfo:ADD">
			    	<li><a class="add" href="system/basicdata/addMachine.do"  target="dialog" height="300" width="500"  rel="addDown" title="新增-机型"><span>新增</span></a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="ps:psinfo:EDIT">
					<li><a class="edit" href="system/basicdata/editMachine.do?id={key}"   target="dialog"  height="300" width="500"  rel="editUser" title="修改-机型"><span>修改</span></a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="ps:psinfo:DEL">
					<li><a class="delete" href="system/basicdata/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该记录吗？"><span>删除</span></a></li>
				</shiro:hasPermission>
			</ul>
		</div>

	<table class="list" width="100%" layoutH="97">
		<thead>
			<tr>
				<th width="5%">选择</th>
				<th width="15%">机型类别</th>
				<th width="25%">维修截止时间</th>
				<th width="10%">累计百台维修率</th>
				<th width="20%">录入人</th>
				<th width="25%">录入时间</th>
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
					<td><fmt:formatDate value="${o.yearMon}" type="date" pattern="yyyy-MM"/></td>
					<td>${o.hundredRepairRate}</td>
					<td>${o.userName}</td>
					<td><fmt:formatDate value="${o.entryTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

