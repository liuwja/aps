<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form id="faultTypeForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/fault/faultType/faultType.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					机型类别： 
				</td>
				<td>
					<select name="productType" >
						<option value="">请选择</option>
						<c:forEach items="${productTypes}" var="o">
						<option value="${o.machineType}" <c:if test="${vo.productType eq o.machineType }">selected="selected"</c:if>>${o.machineType}</option>
						</c:forEach>
					</select>
				</td>	
				<td>
					故障代码： 
				</td>
				<td>
					<input type="text" name="code" value="${vo.code}"/>
				</td>				
				<td>
					故障名称：
				</td>
				<td>
				<input type="text" name="name"  value="${vo.name}"/>
					
				</td>
				
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				<a class="button" onclick="exportExcelFaultType('base/fault/faultType/excelOutputFaultType.do');"  title="确定导出信息？"><span>导出</span></a>	
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<shiro:hasPermission name="base:faultType:ADD">
			    	<li><a class="add" href="base/fault/faultType/addType.do"  target="dialog" height="300" width="500"  rel="addDown" title="新增-故障大类"><span>新增</span></a></li>
				</shiro:hasPermission>
		<shiro:hasPermission name="base:faultType:EDIT">
					<li><a class="edit" href="base/fault/faultType/editType.do?id={key}"   target="dialog"  height="300" width="500"  rel="editUser" title="修改-故障大类"><span>修改</span></a></li>
				</shiro:hasPermission>
		<shiro:hasPermission name="base:faultType:DEL">
					<li><a class="delete" href="base/fault/faultType/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该记录吗？"><span>删除</span></a></li>
		</shiro:hasPermission>
	</div>
	<table class="table" width="100%" layoutH="121">
		<thead>
			<tr>
			    
				<th width="3%"  align="center" >选择</th>
				<th width="10%">机型类别</th>
				<th width="10%">故障代码</th>
				<th width="10%">故障名称</th>
				
				<th width="10%">创建时间</th>
				<th width="10%">创建用户</th>
				<th width="10%">修改时间</th>
				<th width="10%">修改用户</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key" value="${o.id }">
					</td>
					<td>${o.productType}</td>
					<td>${o.code}</td>
					<td>${o.name}</td>
					
					
					<td><fmt:formatDate value="${o.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${o.createUser}</td>
					<td><fmt:formatDate value="${o.lastUpdateTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${o.lastUpdateUser}</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<script>
function exportExcelFaultType(url){    
	var myForm = document.createElement("form");
	myForm.action= url;
	myForm.method="post"; 
	myForm.target="noexistForm"; 
	var objs = $("#faultTypeForm input",navTab.getCurrentPanel());
	var objs_select = $("#faultTypeForm select",navTab.getCurrentPanel());	
	var myInput;
	for(var i = 0 ; i< objs.length+objs_select.length ; i++){
		var $obj = null;
		if(i>=objs.length){
			$obj = $(objs_select[i-objs.length]);	
		}else{
			$obj = $(objs[i]);			
		}
		var v = $obj.val();
		if(v==null || v==""){
			v="";
		}
		if($obj.attr("type")=="checkbox"){
			if(!$obj.attr("checked")){
				v="";
			}
		}
		myInput = document.createElement("input");
		myInput.setAttribute("name", $obj.attr("name"));
		myInput.setAttribute("value", v);
		myForm.appendChild(myInput);
	}
	document.body.appendChild(myForm);
	myForm.submit();
}
</script>

