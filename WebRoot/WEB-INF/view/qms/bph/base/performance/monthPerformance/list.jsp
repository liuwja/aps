<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function() {
	loadDepartMent("navTab", "${performanceIndex.departmentNumber}");
	setTimeout('$("input[name=departmentNumber]").val("${performanceIndex.departmentNumber}")',50);
});
</script>
<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="ptm/monthPerformanceSet/list.do" method="post">
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
<shiro:hasPermission name="ptm:monthPerformancelist:ADD">
	    <li><a class="add" href="ptm/monthPerformanceSet/add.do?id={key}"  mask="true" target="dialog" height="600" width="840"  title="月度指标设定"><span>新增</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="ptm:monthPerformancelist:EDIT">
		<li><a class="edit" href="ptm/monthPerformanceSet/edit.do?id={key}"  mask="true" target="dialog" height="500" width="1000"   title="月度指标修改"><span>修改</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="ptm:monthPerformancelist:DEL">
		<li><a class="delete" href="ptm/monthPerformanceSet/toDelete.do?id={key}" mask="true" target="dialog" height="500" width="1000" title="月度指标删除"><span>删除</span></a></li>
</shiro:hasPermission>		
	</ul>
</div>
<table class="list" width="100%" layoutH="90">
	<thead>
  		<tr>
			<th></th>
		    <th>年度</th>
		    <th>工厂</th>
		    <th>部门</th>
			<th>绩效目标大类</th>
			<th>绩效目标衡量指标内容</th>
			<th>绩效类型</th>
			<th>指标类型</th>
			<th>权重</th>
			<th>单位</th>
			<th>计算公式</th>
			
			<th>上年度实际值</th>
			<th>上半年基准值</th>
			<th>本年度基准值</th>
			<th>本年度目标值</th>
			<th>上半年目标值</th>
			<th>下半年目标值</th>
			
			<th>月份</th>
			<th>目标</th>
			<th>累计目标</th>
			<th>挑战目标</th>
			<th>创建时间</th>
			<th>创建人</th>
		</tr> 
<!--  		<tr>
			<th rowspan="2"></th>
		    <th rowspan="2">年度</th>
		    <th rowspan="2">工厂</th>
		    <th rowspan="2">部门</th>
			<th rowspan="2">绩效目<br>标大类</th>
			<th rowspan="2">绩效目标衡量指标内容</th>
			<th rowspan="2">绩效<br>类型</th>
			<th rowspan="2">指标<br>类型</th>
			<th rowspan="2">权重</th>
			<th rowspan="2">单位</th>
			<th rowspan="2">计算<br>公式</th>
			
			<th rowspan="2">上年度<br>实际值</th>
			<th rowspan="2">上半年<br>基准值</th>
			<th rowspan="2">本年度<br>基准值</th>
			<th rowspan="2">本年度<br>目标值</th>
			<th rowspan="2">上半年<br>目标值</th>
			<th rowspan="2">下半年<br>目标值</th>
			
			<th rowspan="2">月份</th>
			<th rowspan="2">目标</th>
			<th rowspan="2">累计<br>目标</th>
			<th rowspan="2">挑战<br>目标</th>
			<th rowspan="2">创建<br>时间</th>
			<th rowspan="2">创建人</th>
		</tr>  -->
	</thead>
	<tbody>
		<c:forEach items="${list}" var="o">
			<c:if test="${fn:length(o.monthList) == 0 }">
				<c:set var="len" value="1"></c:set>
				<tr target="key" rel="${o.id}">
					<td rowspan="len">
						<input type="radio" group="code" name="key" value="${o.id}">
					</td>
					<td rowspan="len"><fmt:formatDate value="${o.checkYear}" pattern="yyyy"/></td>
					<td rowspan="len">${o.factoryName}</td> 
					<td rowspan="len">${o.departmentName}</td> 
					<td rowspan="len">${o.performanceTargetClass}</td>  
					<td rowspan="len">${o.indexContent } </td> 
					<td rowspan="len">${o.performanceType } </td> 
					<td rowspan="len">${o.indexType}</td>
					<td rowspan="len">${o.weight}</td>
					<td rowspan="len">${o.company}</td>
					<td rowspan="len">${o.formula}</td>
					
					<td rowspan="len">${o.yearPerformance.lastYearRealityValue}</td>
					<td rowspan="len">${o.yearPerformance.firstYearReferenceValue}</td>
					<td rowspan="len">${o.yearPerformance.referenceValue}</td>
					<td rowspan="len">${o.yearPerformance.targetValue}</td>
					<td rowspan="len">${o.yearPerformance.firstYearTargetValue}</td>
					<td rowspan="len">${o.yearPerformance.secondYearTargetValue}</td>				
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<!-- <td></td> -->
					<td></td>
					<td></td>
				</tr>
			</c:if>
			<c:if test="${fn:length(o.monthList) == 1 }">
				<c:set var="len" value="1"></c:set>
				<tr target="key" rel="${o.id}">
					<td rowspan="1">
						<input type="radio" group="code" name="key" value="${o.id}">
					</td>
					<td rowspan="1"><fmt:formatDate value="${o.checkYear}" pattern="yyyy"/></td>
					<td rowspan="1">${o.factoryName}</td> 
					<td rowspan="1">${o.departmentName}</td> 
					<td rowspan="1">${o.performanceTargetClass}</td>  
					<td rowspan="1">${o.indexContent } </td>
					<td rowspan="1">${o.performanceType } </td>  
					<td rowspan="1">${o.indexType}</td>
					<td rowspan="1">${o.weight}</td>
					<td rowspan="1">${o.company}</td>
					<td rowspan="1">${o.formula}</td>
					
					<td rowspan="1">${o.yearPerformance.lastYearRealityValue}</td>
					<td rowspan="1">${o.yearPerformance.firstYearReferenceValue}</td>
					<td rowspan="1">${o.yearPerformance.referenceValue}</td>
					<td rowspan="1">${o.yearPerformance.targetValue}</td>
					<td rowspan="1">${o.yearPerformance.firstYearTargetValue}</td>
					<td rowspan="1">${o.yearPerformance.secondYearTargetValue}</td>				
					
					<c:forEach items="${o.monthList }" var="mon">
					    <td><fmt:formatDate value="${mon.myMonth}" pattern="yyyy-MM"/></td>
						<td>${mon.monTargetValue}</td>
					    <td>${mon.monTotalTargetValue}</td>
					    <td>${mon.monChallengeTargetValue}</td>
					    <%-- <td>${mon.checkMethod}</td> --%>
					    <td><fmt:formatDate value="${mon.createTime1}" pattern="yyyy-MM-dd "/></td>
					    <td>${mon.createUser1}</td>
					</c:forEach>					
				</tr>
			</c:if>	

			<c:if test="${fn:length(o.monthList) > 1 }">
				<c:set var="len" value="${fn:length(o.monthList) }"></c:set>
				<tr target="key" rel="${o.id}">
					<td rowspan="${ len }">
						<input type="radio" group="code" name="key" value="${o.id}">
					</td>
					<td rowspan="${ len }"><fmt:formatDate value="${o.checkYear}" pattern="yyyy"/></td>
					<td rowspan="${ len }">${o.factoryName}</td> 
					<td rowspan="${ len }">${o.departmentName}</td> 
					<td rowspan="${ len }">${o.performanceTargetClass}</td>  
					<td rowspan="${ len }">${o.indexContent } </td> 
					<td rowspan="${ len }">${o.performanceType } </td> 
					<td rowspan="${ len }">${o.indexType}</td>
					<td rowspan="${ len }">${o.weight}</td>
					<td rowspan="${ len }">${o.company}</td>
					<td rowspan="${ len }">${o.formula}</td>
					
					<td rowspan="${ len }">${o.yearPerformance.lastYearRealityValue}</td>
					<td rowspan="${ len }">${o.yearPerformance.firstYearReferenceValue}</td>
					<td rowspan="${ len }">${o.yearPerformance.referenceValue}</td>
					<td rowspan="${ len }">${o.yearPerformance.targetValue}</td>
					<td rowspan="${ len }">${o.yearPerformance.firstYearTargetValue}</td>
					<td rowspan="${ len }">${o.yearPerformance.secondYearTargetValue}</td>				
					
					<c:forEach items="${o.monthList }" var="mon" begin="0" end="0">
					    <td rowspan="1"><fmt:formatDate value="${mon.myMonth}" pattern="yyyy-MM"/></td>
						<td rowspan="1">${mon.monTargetValue}</td>
					    <td rowspan="1">${mon.monTotalTargetValue}</td>
					    <td rowspan="1">${mon.monChallengeTargetValue}</td>
					    <%-- <td rowspan="1">${mon.checkMethod}</td> --%>
					    <td rowspan="1"><fmt:formatDate value="${mon.createTime1}" pattern="yyyy-MM-dd "/></td>
					    <td rowspan="1">${mon.createUser1}</td>
					</c:forEach>					
				</tr>
				<c:forEach items="${o.monthList }" var="mon" begin="1">
					<tr>
				    <td rowspan="1"><fmt:formatDate value="${mon.myMonth}" pattern="yyyy-MM"/></td>
					<td rowspan="1">${mon.monTargetValue}</td>
				    <td rowspan="1">${mon.monTotalTargetValue}</td>
				    <td rowspan="1">${mon.monChallengeTargetValue}</td>
				    <%-- <td rowspan="1">${mon.checkMethod}</td> --%>
				    <td rowspan="1"><fmt:formatDate value="${mon.createTime1}" pattern="yyyy-MM-dd "/></td>
				    <td rowspan="1">${mon.createUser1}</td>
				    </tr>
				</c:forEach>
			</c:if>							
		</c:forEach>
	</tbody>
</table>
<c:import url="../../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<script>
/* $(function(){
	var arr = '${idList}';
	arr = arr.substring(1,arr.length-1);
	arr = arr.split(",");
	for(var i = 0;i<arr.length;i++){
		var model = arr[i].trim();
		initRow(model);
	}
	
});
function initRow(obj){
	for(var i = 1;i<16;i++){
		var a = obj+"_rowspantd"+i;
		beginRow(a);
	}
}
function beginRow(obj){
	$('.'+obj, navTab.getCurrentPanel()).each(function(index, element) {
        if(!$(this).hasClass('hide'))
        {    var next=$(this).parent('tr').next('tr').children('.'+obj);//下一个合并的对象
            $(this).attr('rowspan',1);
                while($(this).text()==next.text())
                {
                    $(this).attr('rowspan',parseInt($(this).attr('rowspan'))+1);
                    next.hide();
                    next.addClass('hide');
                    next=next.parent('tr').next('tr').children('.'+obj);//下一个合并的对象
                }
        }
    });	
} */
</script>
<style>
.changeline{
	height: 40px;
	word-wrap:break-word;
	white-space:normal;
}
</style>