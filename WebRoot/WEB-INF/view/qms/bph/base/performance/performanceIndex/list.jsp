<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function() {
	loadDepartMent("navTab", "${performanceIndex.departmentNumber}");
});
//导出excle
function exportExcel(url){    

	var myForm = document.createElement("form");
	myForm.action= url;
	myForm.method="post"; 
	myForm.target="noexistForm"; 
	var myInput;
	
	var objs = $("#performanceIndex input",navTab.getCurrentPanel());
	var objs2 = $("#performanceIndex select",navTab.getCurrentPanel());
	
	for(var i = 0 ; i< objs.length+objs2.length ; i++){
		var $obj = null;
		if(i>=objs.length){
			$obj = $(objs2[i-objs.length]);	
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
			};
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
<form id="performanceIndex" onsubmit="return navTabSearch(this);" rel="pagerForm" action="ptm/performanceIndex/list.do" method="post">
<div class="searchBar">
	<table class="searchContent">
		<tr>
			<td>工厂：</td>
			<td>
				<select name="factoryNumber" id="factoryNumber" onchange="loadDepartMent()">
					<option value="">请选择</option>
					<c:forEach items="${factorys}" var="o">
						<option value="${o.factoryNumber }" <c:if test="${performanceIndex.factoryNumber eq o.factoryNumber }">selected="selected"</c:if>>${o.factory}</option>
					</c:forEach>
				</select>
			</td>
			<td>部门：</td>
			<td>
				<select id="department" name="departmentNumber">
				</select>
			</td>
			<td>绩效目标大类：</td>
			<td>
				<input type="text" name="performanceTargetClass" value="${performanceIndex.performanceTargetClass }" size="20">
			</td>
			<td>绩效目标衡量指标内容：</td>
			<td>
				<input type="text" name="indexContent" value="${performanceIndex.indexContent }" size="20">
			</td>
		</tr>
		<tr>
			<td>绩效类型：</td>
			<td>
				<input type="text" name="performanceType" value="${performanceIndex.performanceType }" size="20">
			</td>
			<td>指标类型：</td>
			<td>
				<input type="text" name="indexType" value="${performanceIndex.indexType }" size="20">
			</td>
			<td>年度：</td>
			<td>
				 <input type="text" id="checkYear"  name="year" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY'})" value="${performanceIndex.year}" readonly="true"/>
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
<shiro:hasPermission name="ptm:performanceIndexList:ADD">
	    <li><a class="add" href="ptm/performanceIndex/add.do"  mask="true" target="dialog" height="500" width="820"  title="绩效考核指标设定"><span>新增</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="ptm:performanceIndexList:EDIT">
		<li><a class="edit" href="ptm/performanceIndex/edit.do?id={key}"  mask="true" target="dialog" height="545" width="800"   title="修改-考核设定"><span>修改</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="ptm:performanceIndexList:DEL">
		<li><a class="delete" href="ptm/performanceIndex/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该信息吗？"><span>删除</span></a></li>
</shiro:hasPermission>	
<shiro:hasPermission name="ptm:performanceIndexList:ADD">
		  	<li><a class="add" href="###" onclick="exportExcel('ptm/performanceIndex/excelOutput.do');"   title="确定导出信息？"><span>导出EXCLE</span></a></li>
</shiro:hasPermission>	
	</ul>
</div>
<table class="list" width="100%" layoutH="120">
	<thead>
		<tr>
			<th></th>
		    <th width="">年度</th>
		    <th width="">工厂</th>
		    <th width="">部门</th>
			<th width="">绩效目标大类</th>
			<th width="">绩效目标衡量指标内容</th>
			<th width="">绩效类型</th>
			<th width="">指标类型</th>
			<th width="">权重</th>
			<th width="">单位</th>
			<th width="">计算公式</th>
			<th width="">小于基准</th>
			<th width="">基准与目标之间</th>
			<th width="">大于目标</th>
			<th width="">记录时间</th>
			<th width="">记录人</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="o">
			<tr target="key" rel="${o.id}">
				<td>
					<input type="radio" group="code" name="key" value="${o.id}">
				</td>
				<%--需维护资料--%>
				
				<td><fmt:formatDate value="${o.checkYear}" pattern="yyyy"/></td>
				<td>${o.factoryName}</td> 
				<td>${o.departmentName}</td> 
				<td>${o.performanceTargetClass}</td>  
				<td>${o.indexContent } </td> 
				<td>${o.performanceType}</td>  
				<td>${o.indexType}</td>
				<td>${o.weight}</td>
				<td>${o.company}</td>
				<td>${o.formula}</td>
				<td>${o.referenceValue}</td>
				<td>${o.middleValue}</td>
				<td>${o.targetValue}</td>
				<td><fmt:formatDate value="${o.createTime}" pattern="yyyy-MM-dd "/></td>
				<td>${o.createUser}</td>
			</tr>		
		</c:forEach>
	</tbody>
</table>
<c:import url="../../../../../_frag/pager/panelBar.jsp"></c:import>
</div>