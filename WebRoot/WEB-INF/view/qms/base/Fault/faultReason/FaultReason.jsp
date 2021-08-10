<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>

<script type="text/javascript">
function mesh(){ //合并故障名称
	var url = "base/fault/faultReason/faultReasonSelect.do?groupName=FR2";
	var opt = {width:980,height:500, mask:true};
	$.pdialog.open(url, "dlg_page12", "合并-请选择故障小类", opt);
}
function exportExcelFaultReason(url){    
	var myForm = document.createElement("form");
	myForm.action= url;
	myForm.method="post"; 
	myForm.target="noexistForm"; 
	var objs = $("#faultReasonForm input",navTab.getCurrentPanel());
	var objs_select = $("#faultReasonForm select",navTab.getCurrentPanel());	
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
		if($obj.attr("type")=="radio"){
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
<div class="pageHeader">
	<form id="faultReasonForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/fault/faultReason/faultReason.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
				<td>机型类别：</td>
				<td>
					<select name="productType">
						<option value="">请选择</option>
						<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
						</c:forEach>
					</select>
				</td>
				<td>故障代码：</td>
				<td><input type="text" name="code" value="${vo.code}"/></td>				
				<td>故障名称：</td>
				<td><input type="text" name="name"  value="${vo.name}"/></td>
				<td>合并故障名称：</td>
				<td><input type="text" name="meshFaultName"  value="${vo.meshFaultName}"/></td>
				<td style="padding-right:10px;">是否有效：</td>
				<td>
					<label style="display:inline-block;width:30px;">
                		<input type="radio" name="valid" value="是" <c:if test="${vo.valid=='是'}">checked="checked"</c:if>/>
                		是
                	</label>
                	<label style="display:inline-block;width:30px;">
                		<input type="radio" name="valid" value="否" <c:if test="${vo.valid=='否'}">checked="checked"</c:if>/>
                		否
                	</label>
				</td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				<a class="button" onclick="exportExcelFaultReason('base/fault/faultReason/exportExcelFaultReason.do');"  title="确定导出信息？"><span>导出</span></a>	
				</td>
				<td>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<shiro:hasPermission name="base:faultReason:ADD">
			    	<li><a class="add" href="base/fault/faultReason/addReason.do"  target="dialog" height="300" width="500"  rel="addDown" title="新增-故障小类"><span>新增</span></a></li>
				</shiro:hasPermission>
		<shiro:hasPermission name="base:faultReason:EDIT">
					<li><a class="edit" href="base/fault/faultReason/editReason.do?id={key}"   target="dialog"  height="300" width="500"  rel="editUser" title="修改-故障小类"><span>修改</span></a></li>
				</shiro:hasPermission>
		<shiro:hasPermission name="base:faultReason:DEL">
					<li><a class="delete" href="base/fault/faultReason/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该记录吗？"><span>删除</span></a></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="base:faultReason:mesh">
					<li><a class="edit" href="javascript:void(0);" onclick="mesh()" rel="meshKeys" title="合并与拆分" ><span>合并与拆分</span></a></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="base:faultReason:faultSynchronous">
					<li><a class="edit" href="base/fault/faultReason/synchronous.do"  target="ajaxTodo" ><span>同步故障名称</span></a></li>
		</shiro:hasPermission>
	</div>
	<table class="table" width="100%" layoutH="121">
		<thead>
			<tr>
			    
				<th>选择</th>
				<th>合并故障代码</th>
				<th>合并故障名称</th>
				<th>机型类别</th>
				<th>故障代码</th>
				<th>故障名称</th>
				<th>是否有效</th>
			
				<th>创建时间</th>
				<th>创建用户</th>
				<th>上次修改时间</th>
				<th>上次修改用户</th>
				<th>上次修改类型</th>
				
			</tr>
		</thead>
		<tbody id="chooseList">
			<c:forEach items="${list}" var="o">
				<c:set value="${fn:length(o.faultList)}" var="len"></c:set>
				<tr target="key" rel="${o.id}">
					<td rowspan="${len+1}">
						<input type="radio" group="code" name="key" value="${o.id }">
					</td>
					<td rowspan="${len+1}">${o.meshFaultCode}</td>
					<td rowspan="${len+1}">${o.meshFaultName}</td>
					<td>${o.productType}</td>
					<td>${o.code}</td>
					<td>${o.name}</td>
					<td>${o.valid}</td>
					<td><fmt:formatDate value="${o.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${o.createUser}</td>
					<td><fmt:formatDate value="${o.lastUpdateTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${o.lastUpdateUser}</td>
					<td>${o.lastUpdateType}</td>
				</tr>		
				<c:forEach items="${o.faultList}" var="chird">
					<tr target="key" rel="${chird.id}">
						<td>${chird.productType}</td>
						<td>${chird.code}</td>
						<td>${chird.name}</td>
						<td>${chird.valid}</td>
						
						<td><fmt:formatDate value="${chird.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${chird.createUser}</td>
						<td><fmt:formatDate value="${chird.lastUpdateTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${chird.lastUpdateUser}</td>
						<td>${chird.lastUpdateType}</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>