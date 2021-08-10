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
				<th>选项</th>
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
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
