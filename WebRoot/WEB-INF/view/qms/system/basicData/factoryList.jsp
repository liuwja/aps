<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/basicdata/factorylist.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					工厂编号： 
				</td>
				<td>
					<input type="text" name="factoryNo" value="${param.factoryNo}"/>
				</td>				
				<td>
					工厂名称：
				</td>
				<td>
					<input type="text" name="factoryName" value="${param.factoryName}"/> 
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
			    	<li><a class="add" href="system/basicdata/addFactory.do"  target="dialog" height="200" width="400"  rel="addDown" title="新增-工厂"><span>新增</span></a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="ps:psinfo:EDIT">
					<li><a class="edit" href="system/basicdata/editFactory.do?id={key}"   target="dialog"  height="200" width="400"  rel="editUser" title="修改-工厂"><span>修改</span></a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="ps:psinfo:DEL">
					<li><a class="delete" href="system/basicdata/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该记录吗？"><span>删除</span></a></li>
				</shiro:hasPermission>
			</ul>
		</div>

	<table class="list" width="100%" layoutH="97">
		<thead>
			<tr>
				<th width="1%">选择</th>
				<th width="15%">工厂编号</th>
				<th width="20%">工厂名称</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key" value="${o.id }">
					</td>
					<td>${o.factoryNo}</td>
					<td>${o.factoryName}</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
