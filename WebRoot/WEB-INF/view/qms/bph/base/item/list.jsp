<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<style>
#itemTbody a{
color:blue;
}
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
    //jQuery("#formID").validationEngine();	
});

function initGroupCategory(){
	var url = "<c:url value='base/item/initGroupCategory.do' />";
	$.post(url,function(data){
		if(data.result==0){
			alert("初始化成功");
			navTab.reloadFlag("newItemNav");
		}
	});
}
function initGroup(){
	var url = "<c:url value='base/item/initGroup.do' />";
	$.post(url,function(data){
		if(data.result==0){
			alert("初始化成功");
			navTab.reloadFlag("newItemNav");
		}
	});
}
</script>

<div class="pageHeader">
	<form  onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/item/list.do" method="post">
	<div class="searchBar">
	<!--1、dispalyType:doubleLine表示双行，在新增编辑页面用，inline表示单行，在列表用  -->
	<!--2、 1表示显示，0表示不显示 -->
	<!--3、 1表示value为编号，0表示value为 名称-->
	<!--4、 isRequired表示必填与否，true、false，用于新增、编辑的时候-->
		<table class="searchContent">
			<tr>
			    <jsp:include page="../../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="area"/>
					<jsp:param value="1" name="category"/>
					<jsp:param value="0" name="fcategory"/>
					<jsp:param value="0" name="checkItem"/>
					<jsp:param value="0" name="fgroup"/>
					<jsp:param value="0" name="fagroup"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
				</jsp:include>			   

				<td><div class="buttonActive"><div class="buttonContent"><button type="submit" >查找</button></div></div></td>
				<td>
                <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="initGroupCategory()" >初始化班组类别</button></div></div>
                <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="initGroup()" >初始化班组</button></div></div>
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
<shiro:hasPermission name="base:item:ADD">
		    <li><a class="add" href="base/item/add.do?gcKey={code_carrier}"  mask="true" target="dialog" height="500" width="800"  title="新增-考核项目"><span>新增</span></a></li>
</shiro:hasPermission>
<!--  
<shiro:hasPermission name="sys:item:EDIT">
			<li><a class="edit" href="base/item/edit.do?gcKey={code_carrier}"  mask="true" target="dialog" height="300" width="800"   title="修改-考核项目"><span>修改</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="sys:item:DEL">
			<li><a class="delete" href="base/item/delete.do?gcKey={code_carrier}"  target="ajaxTodo"   title="确定删除该班组吗？"><span>删除</span></a></li>
</shiro:hasPermission>	
-->	
		</ul>
	</div>
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr>
			    <th width="3%"></th>
			    <th width="10%">工厂</th>
			    <th width="10%">车间</th>
				<th width="10%">班组类别</th>
				<th width="15%">考核项目</th>
				<th width="10%">项目代码</th>
				<th width="15%">创建人</th>
				<th width="15%">创建时间</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody id="itemTbody">
		<c:forEach items="${list}" var="o" varStatus="s">
		<c:set var="len" value="${fn:length(o.item)}"></c:set>
			<tr target="code_carrier" rel="${o.gcKey}">
				<td align="center" rowspan="${len}">
					<input type="radio" group="code" name="gcKey">
				</td>
				<td rowspan="${len}">${o.factory}</td>
				<td rowspan="${len}">${o.area}</td>
				<td rowspan="${len}">${o.category}</td>
				     <c:forEach items="${o.item }" var="vo" begin="0" end="0">
					   <td>${vo.itemName}</td>
					   <td>${vo.itemCode}</td>
					   <td>${vo.createUser}</td>
					   <td><fmt:formatDate pattern="yyyy-MM-dd" type="date" value="${vo.createTime}"/>  </td>
					   <td>
					     <shiro:hasPermission name="base:item:EDIT">
					         <a class="edit" href="base/item/edit.do?gcKey=${o.gcKey }&itemKey=${vo.itemKey}"  mask="true" target="dialog" height="300" width="800"   title="修改-考核项目"><span>修改</span></a>
					     </shiro:hasPermission>
					     <shiro:hasPermission name="base:item:DEL">
					         <a class="delete" href="base/item/delete.do?itemKey=${vo.itemKey}"   target="ajaxTodo"  title="删除考核项目"><span>删除</span></a>
					     </shiro:hasPermission>
					   </td>
					  </c:forEach>
					<c:if test="${len == 0 }">
					  <td></td>
					  <td></td>
					  <td></td>
					  <td></td>
					  <td></td>
					</c:if>	 
			</tr>		
			
				     <c:forEach items="${o.item }" var="it" begin="1" >
				      <tr>
				        
					   <td>${it.itemName}</td>
					   <td>${it.itemCode}</td>
					   <td>${it.createUser}</td>
					   <td><fmt:formatDate value="${it.createTime}" type="date" pattern="yyyy-MM-dd"/>   </td>
					   <td>
					     <shiro:hasPermission name="base:item:EDIT">
					          <a class="edit" href="base/item/edit.do?gcKey=${o.gcKey }&itemKey=${it.itemKey}"  mask="true" target="dialog" height="300" width="800"   title="修改-考核项目"><span>修改</span></a>
					     </shiro:hasPermission>
					     <shiro:hasPermission name="base:item:DEL">
					         <a class="delete" href="base/item/delete.do?itemKey=${it.itemKey}"   target="ajaxTodo"  title="删除考核项目"><span>删除</span></a>
					     </shiro:hasPermission>
					   </td>
					   </tr>
					</c:forEach>				
		</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
