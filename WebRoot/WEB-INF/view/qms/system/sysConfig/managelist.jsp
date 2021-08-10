<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/sysconfig/managelist.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr><td>
					属性代码： 
				</td>
				<td>
					<input type="text" name="code" value="${param.code}"/>
				</td>	
				<td>
					属性名称：
				</td>
				<td>
					<input type="text" name="codeName" value="${param.codeName}"/> 
				</td>				
				<td>
					属性值：
				</td>
				<td>
					<input type="text" name="codeValue" value="${param.codeValue}"/> 
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
			    	<li><a class="add" href="system/sysconfig/addManage.do"  target="dialog" height="400" width="800"  rel="addDown" title="新增-属性"><span>新增</span></a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="ps:psinfo:EDIT">
					<li><a class="edit" href="system/sysconfig/editManage.do?id={key}"   target="dialog"  height="400" width="800"  rel="editUser" title="修改-属性"><span>修改</span></a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="ps:psinfo:DEL">
					<li><a class="delete" href="system/sysconfig/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该记录吗？"><span>删除</span></a></li>
				</shiro:hasPermission>
			</ul>
		</div>

	<table class="list" width="100%" layoutH="97">
		<thead>
			<tr>
				<th width="5%">选择</th>
				<th width="15%">属性代码</th>
				<th width="15%">属性名称</th>
				<th width="13%">属性值</th>
				<th width="13%">创建时间</th>
				<th width="15%">创建用户</th>
				<th width="13%">修改时间</th>
				<th width="14%">修改用户</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key" value="${o.id }">
					</td>
					<td>${o.code}</td>
					<td>${o.codeName}</td>
					<td>${o.codeValue}</td>
					<td>${o.createTime}</td>
					<td>${o.createUser}</td>
					<td>${o.updateTime}</td>
					<td>${o.updateUser}</td>
					
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

