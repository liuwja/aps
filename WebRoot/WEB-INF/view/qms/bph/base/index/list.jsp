<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function(){
    //jQuery("#formID").validationEngine();	
});


</script>
<style>
#indexTbody a{
 color:blue;
}
</style>
<div class="pageHeader">
	<form  onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/index/list.do" method="post">
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

				<td><div class="buttonActive"><div class="buttonContent"><button type="submit" >查找</button></div></div>
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
<!--  
<shiro:hasPermission name="sys:item:ADD">
		    <li><a class="add" href="base/index/add.do?gcKey={code_carrier}"  mask="true" target="dialog" height="500" width="800"  title="新增-考核项目"><span>新增</span></a></li>
</shiro:hasPermission>
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
			    <th width="7%">工厂</th>
			    <th width="7%">车间</th>
				<th width="7%">班组类别</th>
				<th width="7%">考核项目</th>
				<th width="6%">项目代码</th>
				<th width="4%">操作</th>
				<th width="15%">考核指标</th>
				<th width="6%">指标代码</th>
				<th width="8%">指标得分公式</th>
				<th width="8%">创建人</th>
				<th width="8%">创建时间</th>
				<th width="6%">操作</th>
			</tr>
		</thead>
		<tbody id="indexTbody">
		 <c:forEach items="${list}" var="o" varStatus="s">
			<tr target="code_carrier" rel="${o.gcKey}" >
				<td rowspan="${o.indexNum}">
					<input type="radio" group="code" name="gcKey">
				</td>
				<td  rowspan="${o.indexNum}">${o.factory}</td>
				<td  rowspan="${o.indexNum}">${o.area}</td>
				<td  rowspan="${o.indexNum}">${o.category}</td>
				
				     <c:forEach items="${o.item}" var="vo" begin="0" end="0">
				       <td rowspan="${vo.indexNum}">${vo.itemName}</td>
					   <td rowspan="${vo.indexNum}">${vo.itemCode}</td>
					   <td rowspan="${vo.indexNum}">
					       <shiro:hasPermission name="base:index:ADD"> 
					            <a  href="base/index/add.do?gcKey=${o.gcKey }&itemKey=${vo.itemKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="新增"><span>新增</span></a>
					       </shiro:hasPermission>  
					   </td>
		 			    <c:forEach items="${vo.uiindexs }" var="in" begin="0" end="0">
					      <td  >${in.indexName }</td>
					      <td  >${in.indexCode }</td>
					      <td  >${fn:substring(in.indexDescription, 0, 10)}...</td>
					      <td  >${in.createUser }</td>
					      <td  > <fmt:formatDate value="${in.createTime }" type="date" pattern="yyyy-MM-dd"/> </td>
					         <td>
					          <shiro:hasPermission name="base:index:EDIT"> 
					            <a  href="base/index/edit.do?gcKey=${o.gcKey }&itemKey=${vo.itemKey}&indexKey=${in.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改考核指标"><span>修改</span></a>
					          </shiro:hasPermission>  
					          <shiro:hasPermission name="base:index:DEL"> 
					            <a  href="base/index/delete.do?indexKey=${in.indexKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
					          </shiro:hasPermission>
					         </td>
					    </c:forEach>
					    <c:if test="${vo.indexNum == 0}">
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    </c:if>
			 		   
					    <c:forEach items="${vo.uiindexs }" var="in2" begin="1">
					    <tr> 
					      <td  >${in2.indexName }</td>
					      <td  >${in2.indexCode }</td>
					      <td  >${fn:substring(in2.indexDescription, 0, 10)}...</td>
					      <td  >${in2.createUser }</td>
					      <td  ><fmt:formatDate value="${in2.createTime }" type="date" pattern="yyyy-MM-dd"/></td>
					         <td>
					          <shiro:hasPermission name="base:index:EDIT"> 
					            <a class="edit" href="base/index/edit.do?gcKey=${o.gcKey }&itemKey=${vo.itemKey}&indexKey=${in2.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改考核指标"><span>修改</span></a>
					          </shiro:hasPermission>  
					          <shiro:hasPermission name="base:index:DEL"> 
					            <a class="delete" href="base/index/delete.do?indexKey=${in2.indexKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
					          </shiro:hasPermission>
					         </td>
					    </tr> 
					    </c:forEach>
					    
					</c:forEach>	
					<c:if test="${o.indexNum == 0}">
					    <td></td>
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
			
				     <c:forEach items="${o.item}" var="it" begin="1" >
				        <tr> 
				           <td rowspan="${it.indexNum}">${it.itemName}</td>
					       <td rowspan="${it.indexNum}">${it.itemCode}</td>
					       <td rowspan="${it.indexNum}">
						       <shiro:hasPermission name="base:index:ADD"> 
						            <a class="add" href="base/index/add.do?gcKey=${o.gcKey }&itemKey=${it.itemKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="新增"><span>新增</span></a>
						       </shiro:hasPermission>  
					      </td>
						<c:forEach items="${it.uiindexs }" var="ind" begin="0" end="0">
					      <td  >${ind.indexName }</td>
					      <td  >${ind.indexCode }</td>
					      <td  >${fn:substring(ind.indexDescription, 0, 10)}...</td>
					      <td  >${ind.createUser }</td>
					      <td  ><fmt:formatDate value="${ind.createTime }" type="date" pattern="yyyy-MM-dd"/></td>
					         <td>
					          <shiro:hasPermission name="base:index:EDIT"> 
					            <a class="edit" href="base/index/edit.do?gcKey=${o.gcKey }&itemKey=${it.itemKey}&indexKey=${ind.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改考核指标"><span>修改</span></a>
					          </shiro:hasPermission>  
					          <shiro:hasPermission name="base:index:DEL"> 
					            <a class="delete" href="base/index/delete.do?indexKey=${ind.indexKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
					          </shiro:hasPermission>
					         </td>
					    </c:forEach>
					    <c:if test="${it.indexNum == 0}">
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    </c:if>	
					    
					     <c:forEach items="${it.uiindexs }" var="ind2" begin="1">
					     <tr>
					      <td  >${ind2.indexName }</td>
					      <td  >${ind2.indexCode }</td>
					      <td  >${fn:substring(ind2.indexDescription, 0, 10)}...</td>
					      <td  >${ind2.createUser }</td>
					      <td  ><fmt:formatDate value="${ind2.createTime }" type="date" pattern="yyyy-MM-dd"/></td>
					         <td>
					          <shiro:hasPermission name="base:index:EDIT"> 
					            <a class="edit" href="base/index/edit.do?gcKey=${o.gcKey }&itemKey=${it.itemKey}&indexKey=${ind2.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改考核指标"><span>修改</span></a>
					          </shiro:hasPermission>  
					          <shiro:hasPermission name="base:index:DEL"> 
					            <a class="delete" href="base/index/delete.do?gcKey=${o.gcKey }&itemKey=${it.itemKey}&indexKey=${ind2.indexKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
					          </shiro:hasPermission>
					         </td>
					      </tr>
					    </c:forEach>
					    <c:if test="${o.indexNum == 0}">
					    <td></td>
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
					</c:forEach>	
		</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
