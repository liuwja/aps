<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/productionreturn/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					班组类别：
				</td>
				<td>
					<input type="text" name="shiftgroupCategory" value="${param.shiftgroupCategory}"/>
				</td>
				<td>
					物料编号：
				</td>
				<td>
					<input type="text" name="materialTag" value="${param.materialTag}"/>
				</td>
				<td>
					物料名称：
				</td>
				<td>
					<input type="text" name="materialName" value="${param.materialName}"/>
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
<shiro:hasPermission name="base:productionReturn:ADD">
		    <li><a class="add" href="system/productionreturn/add.do"  mask="true" target="dialog" height="300" width="800"  title="新增-生产退次"><span>新增</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:productionReturn:EDIT">
			<li><a class="edit" href="system/productionreturn/edit.do?id={key}"  mask="true" target="dialog" height="300" width="800"   title="修改-生产退次"><span>修改</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:productionReturn:DEL">
			<li><a class="delete" href="system/productionreturn/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该班组吗？"><span>删除</span></a></li>
</shiro:hasPermission>		
		</ul>
	</div>
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr>
			    <th></th>
			    <th width="10%">工厂</th>
			    <th width="10%">车间</th>
				<th width="15%">班组类别</th>
				<th width="15%">物料编号</th>
				<th width="15%">物料名称</th>
				<th width="15%">创建人</th>
				<th width="15%">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<td>
						<input type="radio" group="code" name="key" value="${o.id}">
					</td>
					<%--需维护资料--%>
					
					<td>${o.factory}</td>
					<td>${o.area}</td> 
					<td>${o.shiftgroupCategory}</td>
					<td>${o.materialTag}</td> 
					<td>${o.materialName}</td> 
					<td>${o.createUser}</td>
					<td><fmt:formatDate value="${o.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
