<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/repairRate/repairRateList.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					机型类别： 
				</td>
				<td>
			      	<select name="machineType" id="machineType" style="width: 210px;">
						<option value="">所有</option>
						<c:forEach items="${productTypeList}" var="o">
							<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
						</c:forEach>
 					<script type="text/javascript">
                        getObjByIdInCurrentTab("machineType").val("${param.machineType}");
                    </script>		      
				</td>				
				<td>
					维修截止时间：
				</td>
				<td>
				<input type="text" name="queryYearMonyh" placeholder="请输入日期"  onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true" value="${param.queryYearMonyh}"/>
				</td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<shiro:hasPermission name="base:repairRate:ADD">
			    	<li><a class="add" href="base/repairRate/addMachine.do"  target="dialog" height="300" width="500"  rel="addDown" title="新增-机型"><span>新增</span></a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="base:repairRate:EDIT">
					<li><a class="edit" href="base/repairRate/editMachine.do?id={key}"   target="dialog"  height="300" width="500"  rel="editUser" title="修改-机型"><span>修改</span></a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="base:repairRate:DEL">
					<li><a class="delete" href="base/repairRate/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该记录吗？"><span>删除</span></a></li>
				</shiro:hasPermission>
			</ul>
		</div>

	<table class="table" width="100%" layoutH="122">
		<thead>
			<tr>
				<th>选择</th>
				<th>机型类别</th>
				<th>维修截止时间</th>
				<th>累计百台维修率</th>
				<th>录入人</th>
				<th>录入时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key" value="${o.id }">
					</td>
					<td>${o.machineType}</td>
					<td>${o.yearMon}</td>
					<td>${o.hundredRepairRate}</td>
					<td>${o.userName}</td>
					<td><fmt:formatDate value="${o.entryTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

