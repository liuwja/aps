<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function() {
	loadDepartMent("navTab", "${performanceIndex.departmentNumber}");
});
</script>
<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="ptm/totalPerformanceList/list.do" method="post">
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
	</ul>
</div>
<input type="hidden" id="monthPerformanceResultKey" name="key1" value="" />
<table class="table" width="" layoutH="100">
<tbody>
<tr style="background-color:#dedede">
	<td rowspan="2"></td>
    <td rowspan="2">年度</td>
    <td rowspan="2">工厂</td>
    <td rowspan="2">部门</td>
	<td rowspan="2">绩效目标大类</td>
	<td rowspan="2">绩效目标衡量指标内容</td>
	<td rowspan="2">绩效类型</td>
	<td rowspan="2">指标类型</td>
	<td rowspan="2">权重</td>
	<td rowspan="2">单位</td>
	<td rowspan="2">计算公式</td>
	
	<td rowspan="2">上年度实际值</td>
	<td rowspan="2">上半年基准值</td>
	<td rowspan="2">本年度基准值</td>
	<td rowspan="2">本年度目标值</td>
	<td rowspan="2">上半年目标值</td>
	<td rowspan="2">下半年目标值</td>
	
	<c:forEach var="monthIndex" begin="1" end="12" step="1">
		<td colspan="10" style="width:600px">
		${ monthIndex }月
		</td>
	</c:forEach>
	<tr  style="background-color:#dedede">
		<c:forEach var="monthIndex1" begin="1" end="12" step="1">
			<td>月份</td>
			<!-- <td>考核方法</td> -->
			<td>当月目标</td>
			<td>当月累计目标</td>
			<td>当月挑战目标</td>
			<td>当月实际值</td>
			<td>累计实际值</td>
			<!-- <td>实际挑战目标</td> -->
			<td>考核结果</td>
			<td>状态</td>
			<!-- <td>评级</td> -->
			<td>创建人</td>
			<td>创建时间</td>
		</c:forEach>
	</tr>
</tr>
	
		
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
	
	<c:if test="${ o.yearPerformance != null}">
		<td>${o.yearPerformance.lastYearRealityValue}</td>
		<td>${o.yearPerformance.firstYearReferenceValue}</td>
		<td>${o.yearPerformance.referenceValue}</td>
		<td>${o.yearPerformance.targetValue}</td>
		<td>${o.yearPerformance.firstYearTargetValue}</td>
		<td>${o.yearPerformance.secondYearTargetValue}</td>	
	</c:if>
	<c:if test="${ o.yearPerformance == null}">
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</c:if>
	
	
	<c:if test="${ fn:length(o.monthList) == 0 }">
		<c:forEach var="monthIndex2" begin="1" end="12" step="1">
			<!-- <td></td>
			<td></td>
			<td></td> -->
			<td></td>
			<td></td>
			<td></td>
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
					<td><fmt:formatDate value="${mon.myMonth}" pattern="yyyy-MM"/></td>
				    <%-- <td>${mon.checkMethod}</td> --%>
					<td>${mon.monTargetValue}</td>
				    <td>${mon.monTotalTargetValue}</td>
				    <td>${mon.monChallengeTargetValue}</td>
				    <td>${mon.monRealityTargetValue}</td>
				    <td>${mon.monRealityTotalTargetValue}</td>
				    <%-- <td>${mon.monRealityChallengeTargetValue}</td> --%>
				    <td>${mon.checkResult}</td>
				    <td>${mon.column1}</td>
				    <%-- <td>${mon.column2}</td> --%>
				    <td>${mon.createUser2}</td>
				    <td><fmt:formatDate value="${mon.createTime2}" pattern="yyyy-MM-dd "/></td>
				</c:if>
			</c:forEach>
			<c:if test="${ flag ne true}">
				<!-- <td></td>
				<td></td>
				<td></td> -->
				<td></td>
				<td></td>
				<td></td>
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
table tbody td{
	text-align:center;
	width:40px;
} 
tbody tr:nth-child(odd) {
  background-color:#F5F5F5;
}
tbody tr:nth-child(even) {
  background-color:#fff;
}	 
</style>