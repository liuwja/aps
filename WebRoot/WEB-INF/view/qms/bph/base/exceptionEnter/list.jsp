<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function(){
    //jQuery("#formID").validationEngine();	
});
</script>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/exceptionEnter/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
			    <jsp:include page="../../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="area"/>
					<jsp:param value="0" name="category"/>
					<jsp:param value="0" name="fcategory"/>
					<jsp:param value="0" name="checkItem"/>
					<jsp:param value="0" name="fgroup"/>
					<jsp:param value="1" name="fagroup"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="0" name="isColspan"/>
				</jsp:include>
			</tr>
			<tr>
			<td>
					异常类型：
				</td>
				<td>
					<input type="text" name="exceptionType" valur="${param.exceptionType }" size="20">
				</td>	
				<td>
					期间：
				</td>
				<td>
					 <input type="text" id="startTime"  name="startTime" placeholder="请输入日期" onclick="laydate()" value="${param.startTime }" readonly="true"/>至
					 <input type="text" id="endTime"  name="endTime" placeholder="请输入日期" onclick="laydate()" value="${param.endTime }" readonly="true"/>
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
<shiro:hasPermission name="base:exceptionEnter:ADD">
		    <li><a class="add" href="system/exceptionEnter/add.do"  mask="true" target="dialog" height="300" width="800"  title="新增-异常信息"><span>新增</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:exceptionEnter:EDIT">
			<li><a class="edit" href="system/exceptionEnter/edit.do?id={key}"  mask="true" target="dialog" height="300" width="800"   title="修改-异常信息"><span>修改</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:exceptionEnter:DEL">
			<li><a class="delete" href="system/exceptionEnter/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该异常信息吗"><span>删除</span></a></li>
</shiro:hasPermission>		
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
			    <th ></th>
			    <th width="10%">发生时间</th>
			    <th width="10%">工厂</th>
				<th width="10%">车间</th>
				<th width="10%">班组</th>
				<th width="10%">异常类型</th>
				<th width="20%">异常内容</th>
				<th width="15%">创建时间</th>
				<th width="10%">创建人</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<td>
						<input type="radio" group="code" name="key" value="${o.id}">
					</td>
					<%--需维护资料--%>
					
					<td><fmt:formatDate value="${o.occurTime}" type="date" pattern="yyyy-MM-dd"/></td>
					<td>${o.factory}</td>
					<td>${o.area}</td> 
					<td>${o.groupName}</td>  
					<td>${o.exceptionType } </td> 
					<td>${o.exceptionName}</td>
					<td><fmt:formatDate value="${o.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${o.createUser}</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
