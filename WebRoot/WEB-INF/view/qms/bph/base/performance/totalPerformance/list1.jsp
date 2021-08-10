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
			<td>
				统计类型： 
			</td>
			<td>
				<select name="statisticsType" id="statisticsType" onchange="">
					<!-- <option value="">请选择</option> -->
					<option value="所有" <c:if test="${performanceIndex.statisticsType eq '所有' }">selected="selected"</c:if>>所有</option>
					<option value="当月实际" <c:if test="${performanceIndex.statisticsType eq '当月实际' }">selected="selected"</c:if>>当月实际</option>
					<option value="当月累计实际" <c:if test="${performanceIndex.statisticsType eq '当月累计实际' }">selected="selected"</c:if>>当月累计实际</option>
				</select>
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
<%-- <div class="panelBar">
	<ul class="toolBar">
<shiro:hasPermission name="ptm:monthPerformanceResultlist:ADD">
	    <li><a class="add" href="ptm/monthPerformanceResult/toAdd.do?id={key}"  mask="true" target="dialog" height="600" width="840"  title="月度指标结果设定"><span>新增</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="ptm:monthPerformanceResultlist:EDIT">
		<li><a class="edit" href="ptm/monthPerformanceResult/edit.do?id={key}"  mask="true" target="dialog" height="500" width="1000"   title="月度指标结果修改"><span>修改</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="ptm:monthPerformanceResultlist:DEL">
		<li><a class="delete" href="ptm/monthPerformanceResult/toDelete.do?id={key}" mask="true" target="dialog" height="500" width="1000" title="月度指标结果删除"><span>删除</span></a></li>
</shiro:hasPermission>		
	</ul>
</div> --%>
<table class="table" width="100%" layoutH="90">
	<thead>
		<tr>
			<th></th>
		    <th width="">年度</th>
		    <th width="">工厂</th>
		    <th width="">部门</th>
			<th width="">绩效目标大类</th>
			<th width="">绩效目标衡量指标内容</th>
			<th width="">指标类型</th>
			<th width="">权重</th>
			<th width="">单位</th>
			<th width="">计算公式</th>
			
			<th width="">上年度实际值</th>
			<th width="">本年度基准值</th>
			<th width="">本年度目标值</th>
			<th width="">上半年目标值</th>
			<th width="">下半年目标值</th>
			
<!-- 			<th>月份</th>
			<th>考核方法</th>
			<th>目标</th>
			<th>累计目标</th>
			<th>挑战目标</th>
			<th>实际目标</th>
			<th>实际累计目标</th>
			<th>实际挑战目标</th>
			<th>考核结果</th>
			<th>状态</th>
			<th>评级</th>
			<th>创建时间</th>
			<th>创建人</th> -->
		
  			<c:choose>
				<c:when test="${performanceIndex.statisticsType eq '当月实际' }">
					<th>月份</th>
					<th>当月目标</th>
					<th>当月实际目标</th>
				</c:when>
				<c:otherwise>
					<th>月份</th>
					<th>考核方法</th>
					<th>目标</th>
					<th>累计目标</th>
					<th>挑战目标</th>
					<th>实际目标</th>
					<th>实际累计目标</th>
					<th>实际挑战目标</th>
					<th>考核结果</th>
					<th>状态</th>
					<th>评级</th>
					<th>创建时间</th>
					<th>创建人</th>
				</c:otherwise>
			</c:choose> 
						 
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="o">
		<c:set var="len" value="${fn:length(o.monthList) == 0 ? 1 : fn:length(o.monthList)+1}"></c:set>
			<tr target="key" rel="${o.id}">
				<td rowspan="${len}">
					<input type="radio" group="code" name="key" value="${o.id}">
				</td>
				<%--需维护资料--%>
				
				<td rowspan="${len}"><fmt:formatDate value="${o.checkYear}" pattern="yyyy"/></td>
				<td rowspan="${len}">${o.factoryName}</td> 
				<td rowspan="${len}">${o.departmentName}</td> 
				<td rowspan="${len}">${o.performanceTargetClass}</td>  
				<td rowspan="${len}">${o.indexContent } </td> 
				<td rowspan="${len}">${o.indexType}</td>
				<td rowspan="${len}">${o.weight}</td>
				<td rowspan="${len}">${o.company}</td>
				<td rowspan="${len}">${o.formula}</td>
				
				<td rowspan="${len}">${o.yearPerformance.lastYearRealityValue}</td>
				<td rowspan="${len}">${o.yearPerformance.referenceValue}</td>
				<td rowspan="${len}">${o.yearPerformance.targetValue}</td>
				<td rowspan="${len}">${o.yearPerformance.firstYearTargetValue}</td>
				<td rowspan="${len}">${o.yearPerformance.secondYearTargetValue}</td>
				
				<c:forEach items="${o.monthList }" var="mon">
					<c:choose>
						<c:when test="${o.indexType eq '望大型'}">
							<c:choose>
								<c:when test="${mon.monRealityTotalTargetValue < o.yearPerformance.referenceValue}">
									<tr style="background-color: red">
									<td><fmt:formatDate value="${mon.myMonth}" pattern="yyyy-MM"/></td>
									<td>${mon.checkMethod}</td>
									<td>${mon.monTargetValue}</td>
									<td>${mon.monTotalTargetValue}</td>
									<td>${mon.monChallengeTargetValue}</td>
									<td>${mon.monRealityTargetValue}</td>
									<td>${mon.monRealityTotalTargetValue}</td>
									<td>${mon.monRealityChallengeTargetValue}</td>
									<td>${mon.checkResult}</td>
									<td>${mon.column1}</td>
									<td>${mon.column2}</td>
									<td><fmt:formatDate value="${mon.createTime2}" pattern="yyyy-MM-dd "/></td>
									<td>${mon.createUser2}</td>
									</tr>									
								</c:when>
								<c:when test="${ mon.monRealityTotalTargetValue ge o.yearPerformance.referenceValue and mon.monRealityTargetValue lt mon.monTotalTargetValue }">
									<tr style="background-color: yellow">
									<td><fmt:formatDate value="${mon.myMonth}" pattern="yyyy-MM"/></td>
									<td>${mon.checkMethod}</td>
									<td>${mon.monTargetValue}</td>
									<td>${mon.monTotalTargetValue}</td>
									<td>${mon.monChallengeTargetValue}</td>
									<td>${mon.monRealityTargetValue}</td>
									<td>${mon.monRealityTotalTargetValue}</td>
									<td>${mon.monRealityChallengeTargetValue}</td>
									<td>${mon.checkResult}</td>
									<td>${mon.column1}</td>
									<td>${mon.column2}</td>
									<td><fmt:formatDate value="${mon.createTime2}" pattern="yyyy-MM-dd "/></td>
									<td>${mon.createUser2}</td>
									</tr>									
								</c:when>
								<c:when test="${ mon.monRealityTotalTargetValue ge mon.monTotalTargetValue }">
									<tr style="background-color: green">
									<td><fmt:formatDate value="${mon.myMonth}" pattern="yyyy-MM"/></td>
									<td>${mon.checkMethod}</td>
									<td>${mon.monTargetValue}</td>
									<td>${mon.monTotalTargetValue}</td>
									<td>${mon.monChallengeTargetValue}</td>
									<td>${mon.monRealityTargetValue}</td>
									<td>${mon.monRealityTotalTargetValue}</td>
									<td>${mon.monRealityChallengeTargetValue}</td>
									<td>${mon.checkResult}</td>
									<td>${mon.column1}</td>
									<td>${mon.column2}</td>
									<td><fmt:formatDate value="${mon.createTime2}" pattern="yyyy-MM-dd "/></td>
									<td>${mon.createUser2}</td>
									</tr>									
								</c:when>
							</c:choose>							
						</c:when>
						<c:when test="${o.indexType eq '望小型'}">
							<c:choose>
								<c:when test="${mon.monRealityTotalTargetValue gt o.yearPerformance.referenceValue}">
									<tr style="background-color: red">
									<td><fmt:formatDate value="${mon.myMonth}" pattern="yyyy-MM"/></td>
									<td>${mon.checkMethod}</td>
									<td>${mon.monTargetValue}</td>
									<td>${mon.monTotalTargetValue}</td>
									<td>${mon.monChallengeTargetValue}</td>
									<td>${mon.monRealityTargetValue}</td>
									<td>${mon.monRealityTotalTargetValue}</td>
									<td>${mon.monRealityChallengeTargetValue}</td>
									<td>${mon.checkResult}</td>
									<td>${mon.column1}</td>
									<td>${mon.column2}</td>
									<td><fmt:formatDate value="${mon.createTime2}" pattern="yyyy-MM-dd "/></td>
									<td>${mon.createUser2}</td>
									</tr>									
								</c:when>
								<c:when test="${ mon.monRealityTotalTargetValue lt o.yearPerformance.referenceValue and mon.monRealityTargetValue gt mon.monTotalTargetValue }">
									<tr style="background-color: yellow">
									<td><fmt:formatDate value="${mon.myMonth}" pattern="yyyy-MM"/></td>
									<td>${mon.checkMethod}</td>
									<td>${mon.monTargetValue}</td>
									<td>${mon.monTotalTargetValue}</td>
									<td>${mon.monChallengeTargetValue}</td>
									<td>${mon.monRealityTargetValue}</td>
									<td>${mon.monRealityTotalTargetValue}</td>
									<td>${mon.monRealityChallengeTargetValue}</td>
									<td>${mon.checkResult}</td>
									<td>${mon.column1}</td>
									<td>${mon.column2}</td>
									<td><fmt:formatDate value="${mon.createTime2}" pattern="yyyy-MM-dd "/></td>
									<td>${mon.createUser2}</td>
									</tr>									
								</c:when>
								<c:when test="${ mon.monRealityTotalTargetValue le mon.monTotalTargetValue }">
									<tr style="background-color: green">
									<td><fmt:formatDate value="${mon.myMonth}" pattern="yyyy-MM"/></td>
									<td>${mon.checkMethod}</td>
									<td>${mon.monTargetValue}</td>
									<td>${mon.monTotalTargetValue}</td>
									<td>${mon.monChallengeTargetValue}</td>
									<td>${mon.monRealityTargetValue}</td>
									<td>${mon.monRealityTotalTargetValue}</td>
									<td>${mon.monRealityChallengeTargetValue}</td>
									<td>${mon.checkResult}</td>
									<td>${mon.column1}</td>
									<td>${mon.column2}</td>
									<td><fmt:formatDate value="${mon.createTime2}" pattern="yyyy-MM-dd "/></td>
									<td>${mon.createUser2}</td>
									</tr>									
								</c:when>
							</c:choose>							
						</c:when>
						<c:when test="${o.indexType eq '望目型'}">
							<c:choose>
								<c:when test="${mon.monRealityTotalTargetValue eq mon.monTotalTargetValue}">
									<tr style="background-color: green">
									<td><fmt:formatDate value="${mon.myMonth}" pattern="yyyy-MM"/></td>
									<td>${mon.checkMethod}</td>
									<td>${mon.monTargetValue}</td>
									<td>${mon.monTotalTargetValue}</td>
									<td>${mon.monChallengeTargetValue}</td>
									<td>${mon.monRealityTargetValue}</td>
									<td>${mon.monRealityTotalTargetValue}</td>
									<td>${mon.monRealityChallengeTargetValue}</td>
									<td>${mon.checkResult}</td>
									<td>${mon.column1}</td>
									<td>${mon.column2}</td>
									<td><fmt:formatDate value="${mon.createTime2}" pattern="yyyy-MM-dd "/></td>
									<td>${mon.createUser2}</td>
									</tr>									
								</c:when>
								<c:when test="${ mon.monRealityTotalTargetValue ne mon.monTotalTargetValue }">
									<tr style="background-color: red">
									<td><fmt:formatDate value="${mon.myMonth}" pattern="yyyy-MM"/></td>
									<td>${mon.checkMethod}</td>
									<td>${mon.monTargetValue}</td>
									<td>${mon.monTotalTargetValue}</td>
									<td>${mon.monChallengeTargetValue}</td>
									<td>${mon.monRealityTargetValue}</td>
									<td>${mon.monRealityTotalTargetValue}</td>
									<td>${mon.monRealityChallengeTargetValue}</td>
									<td>${mon.checkResult}</td>
									<td>${mon.column1}</td>
									<td>${mon.column2}</td>
									<td><fmt:formatDate value="${mon.createTime2}" pattern="yyyy-MM-dd "/></td>
									<td>${mon.createUser2}</td>
									</tr>									
								</c:when>
							</c:choose>							
						</c:when>												

					</c:choose>					
				</c:forEach>
			</tr>	
		</c:forEach>
	</tbody>
</table>
<c:import url="../../../../../_frag/pager/panelBar.jsp"></c:import>
</div>