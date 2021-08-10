<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<!-- 分页相关 -->
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css" media="screen">
	${demo.css}
	
.my-uploadify-button {
    background:none;
    border: none;
    text-shadow: none;
    border-radius:0;
}

.uploadify:hover .my-uploadify-button {
    background:none;
    border: none;
}

.fileQueue {
    width: 400px;
    height: 150px;
    overflow: auto;
    border: 1px solid #E5E5E5;
    margin-bottom: 10px;
}
</style>
<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="per/chartsDate/reachRateTable.do" method="post">
	<div class="searchBar dropdownSearchBar">
		<table class="searchContent">
			<tr>
				<td>年份：</td>
				<td>
					<input type="text" id="year"  name="smallYear" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY'})"" readonly="true" value="${vo.smallYear}" style="width: 100px"/>
						至
					<input type="text" id="year2"  name="bigYear" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY'})"" readonly="true" value="${vo.bigYear}" style="width: 100px"/>
				</td>					
                <td style="text-align: right;">统计类型：</td>
                <td>
                    <select id="type" name="type">
                		<option value="供应链" <c:if test="${vo.type=='供应链'}">selected="selected"</c:if> >供应链</option>
                		<option value="部门" <c:if test="${vo.type=='部门'}">selected="selected"</c:if> >部门</option>
                    </select>
                </td> 
                <td>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				</td>					
			 </tr>	
		</table>
	</div>
</form>
<div class="pageContent">
	<c:if test="">
	</c:if>
	<table class="list" width="100%" layoutH="95">
		<thead>
			<c:if test="${vo.type=='供应链'}">
				<tr>
					<th>月份</th>
					<th>当月实际达成率</th>
					<th>当月累计实际达成率</th>
				</tr>
			</c:if>
			<c:if test="${vo.type=='部门'}">
				<tr>
					<th>月份</th>
					<th>部门</th>
					<th>当月实际达成率</th>
					<th>当月累计实际达成率</th>
				</tr>
			</c:if>
		</thead>
		<tbody>
			<c:if test="${vo.type=='供应链'}">
				<c:forEach var="o"   items="${list}">
					<tr style="text-align: center;">
						<td>${o.monthTime}</td>
						<td>${o.actualReach}</td>
						<td>${o.totalReach}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${vo.type=='部门'}">
				<c:forEach var="o" items="${list}">
					<tr style="text-align: center;">
						<td>${o.monthTime}</td>
						<td>${o.depaName}</td>
						<td>${o.actualReach}</td>
						<td>${o.totalReach}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
