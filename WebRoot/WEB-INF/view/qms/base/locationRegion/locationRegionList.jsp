<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<script type="text/javascript">

function meshRegion(){
	var url = "base/locationRegion/regionSelect.do";
	var opt = {width:980,height:500, mask:true};
	$.pdialog.open(url, "dlg_regionPage", "合并服务中心", opt);
}
</script>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/locationRegion/locationRegionList.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					仓库编码： 
				</td>
				<td>
					<input type="text" name="locationCode" value="${param.locationCode}"/>
				</td>				
				<td>
					仓库名称：
				</td>
				<td>
				<input type="text" name="location" value="${param.location}"/>
					
				</td>
				<td>
					服务中心名称：
				</td>
				<td>
				<input type="text" name="region" value="${param.region}"/>
					
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
		<shiro:hasPermission name="base:locationRegionList:ADD">
			    	<li><a class="add" href="base/locationRegion/addLocationRegion.do"  target="dialog" height="300" width="500"  rel="addDown" title="新增-对应关系"><span>新增</span></a></li>
				</shiro:hasPermission>
		<shiro:hasPermission name="base:locationRegionList:EDIT">
					<li><a class="edit" href="base/locationRegion/editLocationRegion.do?id={key}"   target="dialog"  height="300" width="500"  rel="editUser" title="修改-对应关系"><span>修改</span></a></li>
				</shiro:hasPermission>
		<shiro:hasPermission name="base:locationRegionList:regionSelect">
					<li><a class="edit" href="javascript:void(0);" onclick="meshRegion()" rel="meshRegionKeys" title="合并与拆分" ><span>合并与拆分</span></a></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="base:locationRegionList:DEL">
					<li><a class="delete" href="base/locationRegion/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该记录吗？"><span>删除</span></a></li>
		</shiro:hasPermission>		
		<shiro:hasPermission name="base:locationRegionList:synchronous">
					<li><a class="edit" href="base/locationRegion/synchronous.do"  target="ajaxTodo"  ><span>同步mes仓库</span></a></li>
		</shiro:hasPermission>		
				

	</div>
	<table class="table" width="100%" layoutH="121">
		<thead>
			<tr>
			    <th width="5%">选择</th>
			     
				<th>仓库编码</th>
				<th>仓库名称</th>
				
				<th>服务中心名称</th>
				<th>合并服务中心名称</th>
				<th>所在省份</th>
				<th>创建用户</th>
				<th>创建时间</th>
				<th>最后修改用户</th>
				<th>最后修改时间</th>
				
			</tr>
		</thead>
		<tbody id="chooseRegionList">
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key" value="${o.id }">
					</td>
					
					<td>${o.locationCode}</td>
					<td>${o.location}</td>
					<td>${o.region}</td>
					<td>${o.mergeRegion}</td>
					<td>${o.province}</td>
					<td>${o.createUser}</td>
					<td><fmt:formatDate value="${o.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${o.lastUpdateUser}</td>
					<td><fmt:formatDate value="${o.lastUpdateTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					
					
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
