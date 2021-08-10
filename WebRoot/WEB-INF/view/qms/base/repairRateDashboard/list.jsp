<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/repairRateDashboard/list.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>机型类别：</td>
					<td>
						<select name="productType" id="productType">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
								<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
							</c:forEach>
						</select>
					</td>
					<td>维修时间：</td>
					<td>
						<input type="text" name="month" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true" value="${vo.month}"/>
					</td>
					<td>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasPermission name="base:repairRateDashboard:ADD">
		    	<li><a class="add" href="base/repairRateDashboard/add.do" target="dialog" height="300" width="500" rel="addDown"><span>新增</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="base:repairRateDashboard:EDIT">
				<li><a class="edit" href="base/repairRateDashboard/edit.do?id={key}" target="dialog" height="300" width="500" rel="editUser"><span>修改</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="base:repairRateDashboard:DEL">
				<li><a class="delete" href="base/repairRateDashboard/delete.do?id={key}" target="ajaxTodo" title="确定删除该记录吗？"><span>删除</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="122">
		<thead>
			<tr>
				<th>选择</th>
				<th>机型类别</th>
				<th>维修截止时间</th>
				<th>现状</th>
				<th>基准</th>
				<th>目标</th>
				<th>录入人</th>
				<th>录入时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<td>
						<input type="radio" group="code" name="key" value="${o.id}">
					</td>
					<td>${o.productType}</td>
					<td>${o.month}</td>
					<td>${o.hundredRepairRate}</td>
					<td>${o.referenctRepairRate}</td>
					<td>${o.targetRepairRate}</td>
					<td>${o.createUser}</td>
					<td><fmt:formatDate value="${o.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>