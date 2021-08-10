<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/basicdata/purchasinglist.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					采购组织编号： 
				</td>
				<td>
					<input type="text" name="purchasingNo" value="${param.purchasingNo}"/>
				</td>				
				<td>
					采购组织名称：
				</td>
				<td>
					<input type="text" name="purchasingName" value="${param.purchasingName}"/> 
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
			    	<li><a class="add" href="system/basicdata/addPurchasing.do"  target="dialog" height="200" width="400"  rel="addDown" title="新增-采购组织"><span>新增</span></a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="ps:psinfo:EDIT">
					<li><a class="edit" href="system/basicdata/editPurchasing.do?id={key}"   target="dialog"  height="200" width="400"  rel="editUser" title="修改-采购组织"><span>修改</span></a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="ps:psinfo:DEL">
					<li><a class="delete" href="system/basicdata/deletePurchasing.do?id={key}"  target="ajaxTodo"   title="确定删除该记录吗？"><span>删除</span></a></li>
				</shiro:hasPermission>
			</ul>
		</div>

	<table class="list" width="100%" layoutH="97">
		<thead>
			<tr>
			    <th width="1%">选择</th>
				<th width="15%">采购组织编号</th>
				<th width="20%">采购组织名称</th>
			</tr>
		</thead>
		<tbody>			
		
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key" value="${o.id }">
					</td>
					<td>${o.purchasingNo}</td>
					<td>${o.purchasingName}</td>
				</tr>		
			</c:forEach>
			
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
