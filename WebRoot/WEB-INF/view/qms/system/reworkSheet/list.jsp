<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function() {
	
});
function exportExcel(url){    	
	var myForm = document.createElement("form");
	myForm.action= url;
	myForm.method="post"; 
	myForm.target="noexistForm"; 
	var objs = $("#reworkSheetFrom input",navTab.getCurrentPanel());
	var objs_select = $("#reworkSheetFrom select",navTab.getCurrentPanel());	
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
	$(document.body,navTab.getCurrentPanel()).append(myForm);
	myForm.submit();
	
}
</script>
<div class="pageHeader">
<form id="reworkSheetFrom" onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/reworkSheet/list.do" method="post">
<div class="searchBar">
	<table class="searchContent">
		<tr>
			<td>工厂：</td>
			<td>
				<select name="factory" id="factory" onchange="">
					<option value="">请选择</option>
					<c:forEach items="${factorys}" var="o">
						<option value="${o.factory }" <c:if test="${reworkSheet.factory eq o.factory }">selected="selected"</c:if>>${o.factory}</option>
					</c:forEach>
				</select>
			</td>
			<td>返工/停线单号</td>
		    <td >
		         <input type="text" id="rework_number"  name="rework_number" size="20"  value="${reworkSheet.rework_number}"/>
		    </td>
			<td>开单日期：</td>
			<td>
				 <input type="text" id="creation_time"  name="creation_time" placeholder="请输入日期" onclick="laydate()" value="${reworkSheet.creation_time}" readonly="true"/>
			</td>
			<td>状态：</td>
			<td>
				<select name="status" id="status" onchange="">
					<option value=''>请选择</option>
		         	<option value="开启" <c:if test="${reworkSheet.status eq '开启' }">selected="selected"</c:if>>开启</option>
					<option value="关闭" <c:if test="${reworkSheet.status eq '关闭' }">selected="selected"</c:if>>关闭</option>
				</select>
			</td>
			<td>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
			</td>
			<td>
				<a class="button"  onclick="exportExcel('system/reworkSheet/exportExcel.do');"  title="确定导出信息？"><span>导出EXCEL</span></a>
			</td>  			
		</tr>
	</table>
</div>
</form>
</div>
<div class="pageContent">
<div class="panelBar">
	<ul class="toolBar">
<shiro:hasPermission name="base:reworkSheetData:ADD">
	    <li><a class="add" href="system/reworkSheet/add.do"  mask="true" target="dialog" height="350" width="500"  title="新增返工/停线单"><span>新增</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:reworkSheetData:EDIT">
		<li><a class="edit" href="system/reworkSheet/edit.do?id={key}"  mask="true" target="dialog" height="350" width="500"   title="修改返工/停线单"><span>修改</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:reworkSheetData:DEL">
		<li><a class="delete" href="system/reworkSheet/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该信息吗？"><span>删除</span></a></li>
</shiro:hasPermission>		
	</ul>
</div>
<table class="table" width="100%" layoutH="115">
	<thead>
		<tr>
			<th></th>
		    <th width="12%">序号</th>
		    <th width="12%">工厂</th>
		    <th width="12%">返工/停线单号</th>
			<th width="12%">工时</th>
			<th width="12%">耗材费用</th>
			<th width="12%">金额</th>
			<th width="12%">状态</th>
			<th width="12%">开单日期</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="o" varStatus="i">
			<tr target="key" rel="${o.id}">
				<td>
					<input type="radio" group="code" name="key" value="${o.id}">
				</td>
				<td width="3%">
        			${i.count}
        		</td>
				<td>${o.factory}</td> 
				<td>${o.rework_number}</td> 
				<td>${o.workhour}</td>  
				<td>${o.supplies_expense } </td> 
				<td>${o.money}</td>
				<td>${o.status}</td>
				<td>${o.creation_time}</td>
			</tr>		
		</c:forEach>
	</tbody>
</table>
<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>