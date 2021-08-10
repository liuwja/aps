<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function(){
    //jQuery("#formID").validationEngine();	
});
   
 
</script>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/countPerformanceMonth/list.do" method="post">
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
					考核指标：
				</td>
				<td>
					<input type="text" name="checkIndexName" value="${param.checkIndexName }" size="20">
				</td>
				<td>
					月度：
				</td>
				<td>
					 <input type="text" id="queryMonth"  name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${param.queryMonth}" readonly="true"/>
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
          <shiro:hasPermission name="base:countPerformanceMonth:ADD">
		    <li><a class="add" href="system/countPerformanceMonth/add.do"  mask="true" target="dialog" height="400" width="850"  title="新增-考核指标"><span>新增</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="base:countPerformanceMonth:EDIT">
						<li><a class="edit" href="system/countPerformanceMonth/edit.do?id={key}"  mask="true" target="dialog" height="300" width="850"   title="修改-考核指标"><span>修改</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="base:countPerformanceMonth:DEL">
						<li><a class="delete" href="system/countPerformanceMonth/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该考核指标？"><span>删除</span></a></li>
			</shiro:hasPermission>	
		</ul>
	</div>
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr>
			    <th>选择</th>
			    <th >月份</th>
			    <th >工厂</th>
				<th >考核指标</th>
				<th >当月目标</th>
				<th >当月实际</th>
				<th >累计实际</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<td>
						<input type="radio" group="code" name="key" value="${o.id}">
					</td>
					<%--需维护资料--%>
					
					<td><fmt:formatDate value="${o.checkMonth}" type="date" pattern="yyyy-MM"/></td>
					<td>${o.factory}</td>
					<td>${o.checkIndexName}</td> 
					<td>${o.targetValue}</td>  
					<td><c:if test="${o.checkIndexName eq '组装一次合格率'}">${1-o.actualValue }</c:if> <c:if test="${o.checkIndexName != '组装一次合格率'}">${o.actualValue }</c:if> </td> 
					<td><c:if test="${o.checkIndexName eq '组装一次合格率'}">${1-o.totalValue}</c:if> <c:if test="${o.checkIndexName != '组装一次合格率'}">${o.totalValue}</c:if></td>					
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
