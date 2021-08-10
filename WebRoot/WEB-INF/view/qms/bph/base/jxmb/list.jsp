<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function() {
	loadDepartMent("navTab", "${vo.department}");
});

</script>
<style>
#indexTbody a{
 color:blue;
}
</style>
<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="per/index/list.do" method="post">
<div class="searchBar">
	<table class="searchContent">
		<tr>
		   <td>工厂：</td>
			<td>
				<select name="factoryNumber" id="factoryNumber" onchange="loadDepartMent()">
					<option value="">请选择</option>
					<c:forEach items="${factorys}" var="o">
						<option value="${o.factoryNumber }" <c:if test="${vo.factoryNumber eq o.factoryNumber }">selected="selected"</c:if>>${o.factory}</option>
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
					 <input type="text" id="chekyear"  name="year" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY'})" value="${vo.year}" readonly="true"/>
				</td>					
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr>
			    <th></th>
			    <th>年度</th>
			    <th>工厂</th>
			    <th>部门</th>
				<th>绩效大类</th>
				<th>衡量指标内容</th>
				<th>绩效类型</th>
				<th>权重</th>
				<th>上年实际值</th>
				<th>上半年目标</th>
				<th>下半年目标</th>
				<th>本年基准值</th>
				<th>本年目标值</th>
				<th>操作</th>
				<th>月份</th>
				<th>基准值</th>
				<th>目标值</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="indexTbody">
		 <c:forEach items="${list}" var="o" varStatus="s">
			<tr target="code_carrier" rel="${o.id}" >
			<c:set var = "len" value = "${o.indexNum == 0 ? '1' : o.indexNum}"></c:set>   <!-- BUG #5879 -->
				<td rowspan="${len}">
					<input type="radio" group="code" name="id">
				</td>
				<td  rowspan="${len}"><fmt:formatDate value="${o.chekyear}"  pattern="yyyy"/></td>
				<td  rowspan="${len}">${o.factoryName}</td>
				<td  rowspan="${len}">${o.departmentName}</td>
				<td  rowspan="${len}">${o.targetclass}</td>
				<td  rowspan="${len}">${o.indexcontent}</td>
				<td rowspan="${len}">${o.performancecontent}</td>
				<td rowspan="${len}">${o.weight}</td>
				
				     <c:forEach items="${o.item}" var="vo" begin="0" end="0">
				       <c:set var = "len" value = "${vo.indexNum ==0 ? '1' : vo.indexNum}"></c:set>
				       <td rowspan="${len}">${vo.upperactualvalue}</td>
					   <td rowspan="${len}">${vo.upperhalftargetvalue}</td>
					   <td rowspan="${len}">${vo.secondhalftargetvalue}</td>
					   <td rowspan="${len}">${vo.referencevalue}</td>
					   <td rowspan="${len}">${vo.yeartargetvalue}</td>
					   <td rowspan="${len}">
					       <shiro:hasPermission name="per:index:ADD"> 
					            <a  href="per/index/add.do?id=${o.id }&itemKey=${vo.itemKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="新增"><span>新增</span></a>
					       </shiro:hasPermission>  
					   </td>
		 			    <c:forEach items="${vo.uiindexs }" var="in" begin="0" end="0">
					      <td  > ${in.month }</td>
					      <td  >${in.basevalue }</td>
					      <td  >${in.targetvalue }</td>
					     				      
					         <td>
					          <shiro:hasPermission name="per:index:EDIT"> 
					            <a  href="per/index/edit.do?id=${o.id }&itemKey=${vo.itemKey}&indexKey=${in.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度考核"><span>修改</span></a>
					          </shiro:hasPermission>  
					          <shiro:hasPermission name="per:index:DEL"> 
					            <a  href="per/index/delete.do?indexKey=${in.indexKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
					          </shiro:hasPermission>
					         </td>
					    </c:forEach>
					    <c:if test="${vo.indexNum == 0}">
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    </c:if>
			 		   
					    <c:forEach items="${vo.uiindexs }" var="in2" begin="1">
					    <tr> 
					    <td  > ${in2.month }  </td>
					      <td  >${in2.basevalue }</td>
					     	<td  >${in2.targetvalue }</td>
					         <td>
					          <shiro:hasPermission name="per:index:EDIT"> 
					            <a class="edit" href="per/index/edit.do?id=${o.id }&itemKey=${vo.itemKey}&indexKey=${in2.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改考核指标"><span>修改</span></a>
					          </shiro:hasPermission>  
					          <shiro:hasPermission name="per:index:DEL"> 
					            <a class="delete" href="per/index/delete.do?indexKey=${in2.indexKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
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
					    <td></td>
					    </c:if>	 
			</tr>		
			
				     <c:forEach items="${o.item}" var="it" begin="1" >
				        <tr>
				           <c:set var = "len" value = "${it.indexNum == 0 ? '1' : it.indexNum}"></c:set>
				           <td rowspan="${len}">${it.upperhalftargetvalue}</td>
					       <td rowspan="${len}">${it.upperhalftargetvalue}</td>
					       <td rowspan="${len}">${it.secondhalftargetvalue}</td>
					       <td rowspan="${len}">${it.referencevalue}</td>
					       <td rowspan="${len}">${it.yeartargetvalue}</td>
					       <td rowspan="${len}">
						       <shiro:hasPermission name="per:index:ADD"> 
						            <a class="add" href="per/index/add.do?id=${o.id }&itemKey=${it.itemKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="新增"><span>新增</span></a>
						       </shiro:hasPermission>  
					      </td>
						<c:forEach items="${it.uiindexs }" var="ind" begin="0" end="0">
						<td  > ${ind.month } </td>				      
					      <td  >${ind.basevalue }</td>
					      <td  >${ind.targetvalue }</td>
					      
					         <td>
					          <shiro:hasPermission name="per:index:EDIT"> 
					            <a class="edit" href="per/index/edit.do?id=${o.id }&itemKey=${it.itemKey}&indexKey=${ind.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改考核指标"><span>修改</span></a>
					          </shiro:hasPermission>  
					          <shiro:hasPermission name="per:index:DEL"> 
					            <a class="delete" href="per/index/delete.do?indexKey=${ind.indexKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
					          </shiro:hasPermission>
					         </td>
					    </c:forEach>
					    <c:if test="${it.indexNum == 0}">
					  
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    </c:if>	
					    
					     <c:forEach items="${it.uiindexs }" var="ind2" begin="1">
					     <tr>
					     <td  > ${ind2.month } </td>
					      <td  >${ind2.basevalue }</td>
					      <td  >${ind2.targetvalue }</td>
					      
					         <td>
					          <shiro:hasPermission name="per:index:EDIT"> 
					            <a class="edit" href="per/index/edit.do?id=${o.id }&itemKey=${it.itemKey}&indexKey=${ind2.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改考核指标"><span>修改</span></a>
					          </shiro:hasPermission>  
					          <shiro:hasPermission name="per:index:DEL"> 
					            <a class="delete" href="per/index/delete.do?id=${o.id }&itemKey=${it.itemKey}&indexKey=${ind2.indexKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
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
					    <td></td>
					    </c:if>					    
					   </tr>
					</c:forEach>	
		</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
