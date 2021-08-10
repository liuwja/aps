<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<style>
#itemTbody a{
color:blue;
}
</style>

<script type="text/javascript">
jQuery(document).ready(function() {
	loadDepartMent("navTab", "${param.department}");
});

function initGroupCategory(){
	var url = "<c:url value='per/item/initGroupCategory.do' />";
	$.post(url,function(data){
		if(data.result==0){
			alert("初始化成功");
			navTab.reloadFlag("newItemNav");
		}
	});
}
function initGroup(){
	var url = "<c:url value='per/item/initGroup.do' />";
	$.post(url,function(data){
		if(data.result==0){
			alert("初始化成功");
			navTab.reloadFlag("newItemNav");
		}
	});
}
</script>
 


<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="per/item/list.do" method="post">
<div class="searchBar">
	<table class="searchContent">
		<tr>
			<td>工厂：</td>
			<td>
				<select name="factoryNumber" id="factoryNumber" onchange="loadDepartMent()">
					<option value="">请选择</option>
					<c:forEach items="${factorys}" var="o">
						<option value="${o.factoryNumber }" <c:if test="${param.factoryNumber eq o.factoryNumber }">selected="selected"</c:if>>${o.factory}</option>
					</c:forEach>
				</select>
			</td>
			<td>部门：</td>
			<td>
				<select id="department" name="department">
				</select>
			</td>
						
			<td>
					年度：
				</td>
				<td>
					 <input type="text" id="chekyear"  name="year" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY'})" value="${param.chekyear}" readonly="true"/>
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
<shiro:hasPermission name="per:item:ADD">
		    <li><a class="add" href="per/item/add.do?id={code_carrier}"  mask="true" target="dialog" height="600" width="800"  title="新增-绩效目标考核"><span>新增</span></a></li>
</shiro:hasPermission>

		</ul>
	</div>
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr>
			    <th></th>
			    <th>年度</th>
			    <th>工厂</th>
			    <th>部门</th>
				<th>绩效目标大类</th>
				<th>绩效目标衡量绩效内容</th>
				<th>绩效类型</th>
				<th>权重</th>
				<th>上年度实际值</th>
				<th>上半年目标值</th>
				<th>下半年目标值</th>
				<th>本年基准值</th>
				<th>本年目标值</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="itemTbody">
		<c:forEach items="${list}" var="o" varStatus="s">
		<c:set var="len" value="${fn:length(o.item) == 0 ? '1' : fn:length(o.item)}"></c:set> <!-- BUG #5879 -->
		<tr target="code_carrier" rel="${o.id}">
			<td align="center" rowspan="${len}">
				<input type="radio" group="code" name="id">
			</td>
				<td rowspan="${len}"><fmt:formatDate value="${o.chekyear}"  pattern="yyyy"/></td>		
				<td rowspan="${len}">${o.factoryName}</td>		
				<td rowspan="${len}">${o.departmentName}</td>
				<td rowspan="${len}">${o.targetclass}</td>
				<td rowspan="${len}">${o.indexcontent}</td>
				<td rowspan="${len}">${o.performancecontent}</td>
				<td rowspan="${len}">${o.weight}</td>
								
				<c:forEach items="${o.item }" var="vo" begin="0" end="0">
					   <td>${vo.upperactualvalue}</td>
					   <td>${vo.upperhalftargetvalue}</td>
					   <td>${vo.secondhalftargetvalue}</td>
					   <td>${vo.referencevalue}</td>
					   <td>${vo.yeartargetvalue}</td>
					   <td>${vo.record}</td>
					   <td><fmt:formatDate pattern="yyyy-MM-dd" type="date" value="${vo.recordtime}"/>  </td>
					   <td>
					     <shiro:hasPermission name="per:item:EDIT">
					         <a class="edit" href="per/item/edit.do?id=${o.id }&itemKey=${vo.itemKey}"  mask="true" target="dialog" height="400" width="800"   title="修改-考核项目"><span>修改</span></a>
					     </shiro:hasPermission>
					     <shiro:hasPermission name="per:item:DEL">
					         <a class="delete" href="per/item/delete.do?itemKey=${vo.itemKey}"   target="ajaxTodo"  title="删除考核项目"><span>删除</span></a>
					     </shiro:hasPermission>
					   </td>
					   
					  </c:forEach>
					<c:if test="${len == 0 }">
					  <td></td>
					  <td></td>
					  <td></td>
					  <td></td>
					  <td></td>
					  <td></td>
					  <td></td>
					  <td></td>
					</c:if>	 
			</tr>		
			
				     <c:forEach items="${o.item }" var="it" begin="1" >
					   <td>${it.upperactualvalue}</td>
					   <td>${it.upperhalftargetvalue}</td>
					   <td>${it.secondhalftargetvalue}</td>
					   <td>${it.referencevalue}</td>
					   <td>${it.yeartargetvalue}</td>
					   <td>${it.record}</td>
					   <td><fmt:formatDate value="${it.recordtime}" type="date" pattern="yyyy-MM-dd"/>   </td>
					   <td>
					     <shiro:hasPermission name="per:item:EDIT">
					          <a class="edit" href="per/item/edit.do?id=${o.id }&itemKey=${it.itemKey}"  mask="true" target="dialog" height="300" width="800"   title="修改-考核项目"><span>修改</span></a>
					     </shiro:hasPermission>
					     <shiro:hasPermission name="per:item:DEL">
					         <a class="delete" href="per/item/delete.do?itemKey=${it.itemKey}"   target="ajaxTodo"  title="删除考核项目"><span>删除</span></a>
					     </shiro:hasPermission>
					   </td>
					   </tr>
					</c:forEach>				
		</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
