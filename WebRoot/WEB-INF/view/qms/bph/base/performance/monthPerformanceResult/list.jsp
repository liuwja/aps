<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function() {
	loadDepartMent("navTab", "${performanceIndex.departmentNumber}");
	setTimeout('$("input[name=departmentNumber]").val("${performanceIndex.departmentNumber}")',50);
});
//导出excle
function exportExcel(url){    

	var myForm = document.createElement("form");
	myForm.action= url;
	myForm.method="post"; 
	myForm.target="noexistForm"; 
	var myInput;
	
	var objs = $("#monthPerformanceResult input",navTab.getCurrentPanel());
	var objs2 = $("#monthPerformanceResult select",navTab.getCurrentPanel());
	
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
<form id="monthPerformanceResult" onsubmit="return navTabSearch(this);" rel="pagerForm" action="ptm/monthPerformanceResult/list.do" method="post">
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
<shiro:hasPermission name="ptm:monthPerformanceResultlist:ADD">
	    <li><a id="addMonthResult" class="add" href="ptm/monthPerformanceResult/toAdd.do?id={key}"  mask="true" target="dialog" height="600" width="1300"  title="月度指标结果设定"><span>新增</span></a></li>
	    <!-- <li><a class="add" href="ptm/monthPerformanceResult/toAdd.do?id=key1"  mask="true" target="dialog" height="600" width="840"  title="月度指标结果设定" onclick="isExistId()"><span>新增</span></a></li> -->
</shiro:hasPermission>
<shiro:hasPermission name="ptm:monthPerformanceResultlist:EDIT">
		<li><a class="edit" href="ptm/monthPerformanceResult/edit.do?id={key}"  mask="true" target="dialog" height="500" width="1300"   title="月度指标结果修改"><span>修改</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="ptm:monthPerformanceResultlist:DEL">
		<li><a class="delete" href="ptm/monthPerformanceResult/toDelete.do?id={key}" mask="true" target="dialog" height="500" width="900" title="月度指标结果删除"><span>删除</span></a></li>
</shiro:hasPermission>	
<shiro:hasPermission name="ptm:monthPerformanceResultlist:ADD">
		  	<li><a class="add" href="ptm/monthPerformanceResult/indexImportInit.do"  target="navTab"  tabId="tabIdForIndexImport"  title="导入excel（所有指标数据）"><span>导入excel</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="ptm:monthPerformanceResultlist:ADD">
		  	<li><a class="add" href="###" onclick="exportExcel('ptm/monthPerformanceResult/excelOutput.do');"   title="确定导出信息？"><span>导出EXCLE</span></a></li>
</shiro:hasPermission>		
	</ul>
</div>
<input type="hidden" id="monthPerformanceResultKey" name="key1" value="" />
<table class="list" width="400%" layoutH="90">
<thead>
<tr class="fixColRow">
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	
	<c:forEach var="n" begin="1" end="12">
		<th></th>
		<th></th>
		<th></th>
		<th></th>
		<th></th>
		<th></th>
		<th></th>
	</c:forEach>
</tr>
<tr>
	<th rowspan="2" style="text-align:center;vertical-align:middle;"></th>
    <th rowspan="2" style="text-align:center;vertical-align:middle;">年度</th>
    <th rowspan="2" style="text-align:center;vertical-align:middle;">工厂</th>
    <th rowspan="2" style="text-align:center;vertical-align:middle;">部门</th>
	<th rowspan="2" style="text-align:center;vertical-align:middle;">绩效目标大类</th>
	<th rowspan="2" style="text-align:center;vertical-align:middle;">绩效目标衡量指标内容</th>
	<th rowspan="2" style="text-align:center;vertical-align:middle;">绩效类型</th>
	<th rowspan="2" style="text-align:center;vertical-align:middle;">指标类型</th>
	<th rowspan="2" style="text-align:center;vertical-align:middle;">权重</th>
	<th rowspan="2" style="text-align:center;vertical-align:middle;">单位</th>
	<th rowspan="2" style="text-align:center;vertical-align:middle;">计算公式</th>
	
	<th rowspan="2" style="text-align:center;vertical-align:middle;">上年度实际值</th>
	<th rowspan="2" style="text-align:center;vertical-align:middle;">上半年基准值</th>
	<th rowspan="2" style="text-align:center;vertical-align:middle;">本年度基准值</th>
	<th rowspan="2" style="text-align:center;vertical-align:middle;">本年度目标值</th>
	<th rowspan="2" style="text-align:center;vertical-align:middle;">上半年目标值</th>
	<th rowspan="2" style="text-align:center;vertical-align:middle;">下半年目标值</th>
	<c:forEach var="monthIndex" begin="1" end="12" step="1">
		<th colspan="7">${ monthIndex }月</th>
	</c:forEach>
</tr>
<tr>
	<c:forEach var="monthIndex1" begin="1" end="12" step="1">	
		<th>当月目标</th>
		<th>当月累计目标</th>
		<th>当月挑战目标</th>
		<th>当月实际值</th>
		<th>累计实际值</th>
		<th>考核结果</th>
		<th>状态</th>
	</c:forEach>
</tr>
<tr class="fixColRow">
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	
	<c:forEach var="n" begin="1" end="12">
		<th></th>
		<th></th>
		<th></th>
		<th></th>
		<th></th>
		<th></th>
		<th></th>
	</c:forEach>
</tr>
</thead>
<tbody>	
<c:forEach items="${list}" var="o">
<tr target="key" rel="${o.id}">
	<td>
		<input type="radio" group="code" name="key" value="${o.id}">
	</td>
	<td><fmt:formatDate value="${o.checkYear}" pattern="yyyy"/></td>
	<td>${o.factoryName}</td> 
	<td>${o.departmentName}</td> 
	<td>${o.performanceTargetClass}</td>  
	<td>${o.indexContent } </td> 
	<td>${o.performanceType } </td> 
	<td>${o.indexType}</td>
	<td>${o.weight}</td>
	<td>${o.company}</td>
	<td>${o.formula}</td>
	
	<c:set var="y" value="${ o.yearPerformance}"></c:set>
	<c:choose>
		<c:when test="${ !empty y}">
			<td>${o.yearPerformance.lastYearRealityValue}</td>
			<td>${o.yearPerformance.firstYearReferenceValue}</td>
			<td>${o.yearPerformance.referenceValue}</td>
			<td>${o.yearPerformance.targetValue}</td>
			<td>${o.yearPerformance.firstYearTargetValue}</td>
			<td>${o.yearPerformance.secondYearTargetValue}</td>	
		</c:when>
		<c:otherwise>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</c:otherwise>
	</c:choose>
	
	<c:if test="${ fn:length(o.monthList) == 0 }">
		<c:forEach var="monthIndex2" begin="1" end="12" step="1">
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</c:forEach>			
	</c:if>
	<c:if test="${ fn:length(o.monthList) != 0 }">
		<c:forEach var="monthIndex3" begin="1" end="12" step="1">
			<c:set var="flag" value="false"></c:set>
			<c:forEach items="${o.monthList }" var="mon" begin="0" end="11" step="1">
				<c:if test="${ mon.monthIndex == monthIndex3}">
					<c:set var="flag" value="true"></c:set>
					<td>${mon.monTargetValue}</td>
				    <td>${mon.monTotalTargetValue}</td>
				    <td>${mon.monChallengeTargetValue}</td>
				    <td>${mon.monRealityTargetValue}</td>
				    <td>${mon.monRealityTotalTargetValue}</td>
				    <td>${mon.checkResult}</td>
				    <td>${mon.column1}</td>
				</c:if>
			</c:forEach>
			<c:if test="${ flag ne true}">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</c:if>					
		</c:forEach>			
	</c:if>				
</tr>	
</c:forEach>
	</tbody>
</table>
<c:import url="../../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<style type="text/css">
/* table tbody td{
	text-align:center;
	width:40px;
}
tbody tr:nth-child(odd) {
  background-color:#F5F5F5;
}
tbody tr:nth-child(even) {
  background-color:#fff;
}	 */ 
</style>