<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/basicdata/materiallist.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					物料编号： 
				</td>
				<td>
					<input type="text" name="username" value="${param.materialNo}"/>
				</td>				
				<td>
					物料名称：
				</td>
				<td>
					<input type="text" name="name" value="${param.materialName}"/> 
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
			<shiro:hasPermission name="ps:psinfo:ADD">
			    	<li><a class="add" href="system/basicdata/addMaterial.do"  target="dialog" height="400" width="800"  rel="addDown" title="新增-物料"><span>新增</span></a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="ps:psinfo:EDIT">
					<li><a class="edit" href="system/basicdata/editMaterial.do?id={key}"   target="dialog"  height="400" width="800"  rel="editUser" title="修改-物料"><span>修改</span></a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="ps:psinfo:DEL">
					<li><a class="deletematerial" href="system/basicdata/deletematerial.do?id={key}"  target="ajaxTodo"   title="确定删除该记录吗？"><span>删除</span></a></li>
						</shiro:hasPermission>
			</ul>
		</div>
	<table class="list" width="100%" layoutH="97">
		<thead>
			<tr>
				<th width= "5%"> 选 择</th>
				<th width="10%">物料编号  </th>
				<th width="20%">物料名称</th>
				<th width="10%">物料组</th>
				<th width="15%">采购组织</th>
				<th width="15%">库存仓库</th>
				<th width="15%">库存单位</th>
				<th width="15%">BOM消耗单位</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key" value="${o.id }">
					</td>
					<td>${o.materialNo}</td>
					<td>${o.materialName}</td>
					<td>${o.purchasingOgz}</td>
					<td>${o.warehouseStock}</td>
					<td>${o.stockUnit}</td>
					<td>${o.bomUnit}</td>
					<td>${o.materialGroup}</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
