<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function(){
    //jQuery("#formID").validationEngine();	
});
   
 
</script>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/performanceYear/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
			    <td>
					工厂：
				</td>
				<td>
					<select id="factory" name="factory" >
					      <option value="">请选择</option>					     
					      <option value="电器工厂">电器一厂</option>	
					      <option value="燃气工厂">燃气工厂</option>
					      <option value="电器二厂">电器二厂</option>						     
					</select>
					 <script type="text/javascript">
                        $("#factory", navTab.getCurrentPanel()).val("${param.factory}"); 
                    </script>		
				</td>
				
				
				<td>
					年度：
				</td>
				<td>
					 <input type="text" id="queryYear"  name="queryYear" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY'})" value="${param.queryYear }" readonly="true"/>
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
<shiro:hasPermission name="base:performanceCheckYear:ADD">
		    <li><a class="add" href="system/performanceCheckYear/add.do"  mask="true" target="dialog" height="300" width="800"  title="新增-考核项目"><span>新增</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:performanceCheckYear:EDIT">
			<li><a class="edit" href="system/performanceCheckYear/edit.do?id={key}"  mask="true" target="dialog" height="300" width="800"   title="修改-考核项目"><span>修改</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:performanceCheckYear:DEL">
			<li><a class="delete" href="system/performanceCheckYear/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该班组吗？"><span>删除</span></a></li>
</shiro:hasPermission>		
		</ul>
	</div>
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr>
			    <th></th>
			    <th width="10%">年度</th>
			    <th width="10%">工厂</th>
				<th width="15%">考核指标</th>
				<th width="10%">年度基准</th>
				<th width="10%">年度目标</th>
				<th width="10%">上半年目标</th>
				<th width="10%">年度下降率</th>
				<th width="15%">创建时间</th>
				<th width="10%">创建人</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<td>
						<input type="radio" group="code" name="key" value="${o.id}">
					</td>
					<%--需维护资料--%>
					
					<td><fmt:formatDate value="${o.checkYear}" type="date" pattern="yyyy"/></td>
					<td>${o.factory}</td>
					<td>${o.checkIndexName}</td> 
					<td>${o.baseValueYear}</td>  
					<td>${o.targetValueYear } </td> 
					<td>${o.targetValueHalfyear}</td>
					<td>${o.depressRateYear}</td>
					<td><fmt:formatDate value="${o.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${o.createUser}</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
