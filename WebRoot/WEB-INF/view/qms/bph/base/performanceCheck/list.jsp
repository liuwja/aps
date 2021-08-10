<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function() {
	loadDepartMent("navTab", "${performanceCheck.department}");
});
</script>
<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/performanceCheck/list.do" method="post">
<div class="searchBar">
	<table class="searchContent">
		<tr>
			<td>工厂：</td>
			<td>
				<select name="factoryNumber" id="factoryNumber" onchange="loadDepartMent()">
					<option value="">请选择</option>
					<c:forEach items="${factorys}" var="o">
						<option value="${o.factoryNumber }" <c:if test="${performanceCheck.factoryNumber eq o.factoryNumber }">selected="selected"</c:if>>${o.factory}</option>
					</c:forEach>
				</select>
			</td>
			<td>部门：</td>
			<td>
				<select id="department" name="department">
				</select>
			</td>
			<td>绩效目标大类：</td>
			<td>
				<input type="text" name="targetclass" value="${performanceCheck.targetclass }" size="20">
			</td>
			<td>年度：</td>
			<td>
				 <input type="text" id="chekyear"  name="year" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY'})" value="${performanceCheck.year}" readonly="true"/>
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
<shiro:hasPermission name="base:performanceCheck:ADD">
	    <li><a class="add" href="system/performanceCheck/add.do"  mask="true" target="dialog" height="300" width="800"  title="新增-年度绩效考核设定"><span>新增</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:performanceCheck:EDIT">
		<li><a class="edit" href="system/performanceCheck/edit.do?id={key}"  mask="true" target="dialog" height="545" width="800"   title="修改-考核设定"><span>修改</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:performanceCheck:DEL">
		<li><a class="delete" href="system/performanceCheck/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该信息吗？"><span>删除</span></a></li>
</shiro:hasPermission>		
	</ul>
</div>
<table class="table" width="100%" layoutH="115">
	<thead>
		<tr>
			<th></th>
		    <th width="">年度</th>
		    <th width="">工厂</th>
		    <th width="">部门</th>
			<th width="">绩效目标大类</th>
			<th width="">绩效目标衡量指标内容</th>
			<!-- 原来是“绩效类型”，修改为“指标类型” -->
			<th width="">指标类型</th>
			<th width="">权重</th>
			<th width="">单位</th>
			<th width="">计算公式</th>
			<th width="">基准</th>
			<th width="">中间值</th>
			<th width="">目标值</th>
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
				
				<td><fmt:formatDate value="${o.chekyear}" pattern="yyyy"/></td>
				<td>${o.factoryName}</td> 
				<td>${o.department}</td> 
				<td>${o.targetclass}</td>  
				<td>${o.indexcontent } </td> 
				<td>${o.performancecontent}</td>
				<td>${o.weight}</td>
				<td>${o.company}</td>
				<td>${o.formula}</td>
				<td>${o.referencevalue}</td>
				<td>${o.median}</td>
				<td>${o.targetvalue}</td>
				<td><fmt:formatDate value="${o.recordtime}" pattern="yyyy-MM-dd "/></td>
				<td>${o.record}</td>
			</tr>		
		</c:forEach>
	</tbody>
</table>
<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>