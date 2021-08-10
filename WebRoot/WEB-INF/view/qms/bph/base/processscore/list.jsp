<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/processscore/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
			    <jsp:include page="../../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="0" name="area"/>
					<jsp:param value="0" name="category"/>
					<jsp:param value="0" name="fcategory"/>
					<jsp:param value="0" name="checkItem"/>
					<jsp:param value="0" name="fgroup"/>
					<jsp:param value="0" name="fagroup"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
				</jsp:include>	
			    <td>
					指标名称：
				</td>
				<td>
					<input type="text" name="indexContent" value="${param.indexContent}"/>
				</td>
				<td>
					编号：
				</td>
				<td>
					<input type="text" name="indexCode" value="${param.indexCode}"/>
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
<shiro:hasPermission name="base:processScore:ADD">
		    <li><a class="add" href="system/processscore/add.do"  mask="true" target="dialog" height="300" width="800"  title="新增-过程分数"><span>新增</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:processScore:EDIT">
			<li><a class="edit" href="system/processscore/edit.do?id={key}"  mask="true" target="dialog" height="300" width="800"   title="修改-过程分数"><span>修改</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:processScore:DEL">
			<li><a class="delete" href="system/processscore/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该过程分数吗？"><span>删除</span></a></li>
</shiro:hasPermission>		
		</ul>
	</div>

	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr>
			    <th></th>
			    <th width="10%">工厂</th>
				<th width="10%">指标名称</th>
				<th width="15%">指标描述</th>
				<th width="10%">编号</th>
				<th width="10%">得分类型</th>
				<th width="10%">分数</th>
				<th width="15%">创建人</th>
				<th width="15%">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<td>
						<input type="radio" group="code" name="key" value="${o.id}">
					</td>
					<%--需维护资料--%>
					
					<td>${o.factory}</td>
					<td>${o.indexContent}</td>
					<td>${o.content}</td>
					<td>${o.indexCode}</td>  
					<td id="scoretypetag" >
<!--				${o.scoreType eq 1 ? "加分" : "扣分"}	  -->
                    <c:if test="${o.scoreType==1}">加分 </c:if>
                    <c:if test="${o.scoreType==0}">扣分 </c:if>		
					</td>							
					<td>${o.score}</td> 
					<td>${o.createUser}</td>
					<td><fmt:formatDate value="${o.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
