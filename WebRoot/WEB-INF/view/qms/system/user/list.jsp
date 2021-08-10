<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/user/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户名： 
				</td>
				<td>
					<input type="text" name="userName" value="${vo.userName}"/>
				</td>				
				<td>
					姓名：
				</td>
				<td>
					<input type="text" name="description" value="${vo.description}"/> 
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
	    <li><a class="delete" href="system/user/mergeUser.do"  target="ajaxTodo"   title="确定要同步用户吗？"><span>同步MES用户</span></a></li>
	</ul>
</div>
	<%-- <div class="panelBar">
		<ul class="toolBar">
<shiro:hasPermission name="sys:userMgr:ADD">
		    <li><a class="add" href="system/user/add.do" mask="true"  target="dialog" height="450" width="650"  rel="registerDlg" title="新增-用户信息"><span>新增</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="sys:userMgr:EDIT">
			<li><a class="edit" href="system/user/edit.do?id={key}"  mask="true" target="dialog"  height="450" width="650"  rel="editUser" mask="true" title="修改-用户信息"><span>修改</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="sys:userMgr:DEL">
			<li><a class="delete" href="system/user/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该用户吗？"><span>删除</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="sys:userMgr:resetPwd">
			<li><a class="edit" href="system/user/toResetPwd.do?id={key}"  mask="true" target="dialog" height="300" width="500" rel="ressetPwd"  title="重置用户密码"><span>重置密码</span></a></li>
</shiro:hasPermission>		
		</ul>
	</div> --%>
	<table class="table" width="100%" layoutH="93">
		<thead>
			<tr>
			    <th width="5%"></th>
				<th width="15%">用户名</th>
				<th width="20%">姓名</th>
				<th width="20%">工厂</th>
				<th width="20%">车间</th>
				<th width="20%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.userKey}">
					<td>
						<input type="radio" group="code" name="key" value="${o.userKey }">
					</td>
					<%--需维护资料--%>
					<td>${o.userName}</td>
					<td>${o.description}</td>
					<td>${o.uda1}</td>
					<td>${o.uda2}</td>
					<td>
						<a class="edit" href="system/user/toUserGroup.do?userKey=${o.userKey}"   height="500" width="1000" mask="true" target="dialog" rel="usergroupPLG" title="设置用户组"><span>设置用户组</span></a>
					</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
