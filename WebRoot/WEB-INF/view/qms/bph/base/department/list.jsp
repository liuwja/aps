<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="per/department/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
			    <td>工厂：</td>
				<td>
					<select name="factoryNumber" id="factoryNumber">
						<option value="">请选择</option>
						<c:forEach items="${factorys}" var="o">
							<option value="${o.factoryNumber }" <c:if test="${dt.factoryNumber eq o.factoryNumber }">selected="selected"</c:if>>${o.factory}</option>
						</c:forEach>
					</select>
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
			<shiro:hasPermission name="per:department:ADD">
		    	<li><a class="add" href="per/department/add.do" mask="true" target="dialog" height="450" width="650" title="新增"><span>新增</span></a></li>
			</shiro:hasPermission>
		
			<shiro:hasPermission name="per:department:EDIT">
				<li><a class="edit" href="per/department/edit.do?id={id}" height="450" width="650" mask="true" target="dialog" title="修改"><span>修改</span></a></li>
			</shiro:hasPermission>
			
			<shiro:hasPermission name="per:department:DEL">
				<li><a class="delete" href="per/department/delete.do?id={id}" target="ajaxTodo"   title="确定删除该信息吗？"><span>删除</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="93">
		<thead>
			<tr>
			    <th width="5%"></th>
				<th width="20%">工厂</th>
				<th width="20%">工厂编码</th>
				<th width="20%">部门名称</th>
				<th width="20%">部门编码</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o" varStatus="s">				
				<tr target="id" rel="${o.id}">
				<td>
					<input type="radio" group="code" name="id" value="${o.id}">
				</td>
					 <td >${o.factoryName}</td>
					 <td >${o.factoryNumber}</td>				
					 <td >${o.departmentName}</td>
					<td >${o.departmentNumber}</td>
					</tr>	
					</c:forEach>
					
			
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
