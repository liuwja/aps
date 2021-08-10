<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<style>
#monthAssesmontListTbody a{
color:blue;
}

</style>
<script type="text/javascript">
$("select").each(function(i,n){
    var options = "";
    $(n).find("option").each(function(j,m){
        if(options.indexOf($(m)[0].outerHTML) == -1)
        {
            options += $(m)[0].outerHTML;
        }
    });
    $(n).html(options);
});
   </script>
<div class="pageHeader">
<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="per/month/list.do" method="post">
<div class="searchBar">
	<table class="searchContent">
		<tr>
				<td>部门:
	<select name="department">
		<option value="">--请选择--</option>
		<c:forEach items="${list2}" var="e">
		<option value="${e.uigroupCategory.department }" <c:if test="${pergroup.department eq e.uigroupCategory.department}">selected="selected"</c:if>>${e.uigroupCategory.department }</option>
		</c:forEach>
	</select>
</td>	
			<td>
					年度：
				</td>
				<td>
					 <input type="text"  id="monthvalue" name="monthvalue" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY'})" value="${param.monthvalue}" readonly="true"/>
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
			    <th width="3%"></th>
			    <th width="6%">部门</th>
				<th width="5%">绩效大类</th>
				<th width="5%">衡量指标内容</th>
				<th width="5%">绩效类型</th>
				
				<th width="6%">本年基准值</th>
				<th width="6%">本年目标值</th>
				
				<th width="6%">月度基准值</th>
				<th width="6%">月份目标值</th>
				<th width="5%">操作</th>
				
				<th width="5%">上月累计目标值</th>
				<th width="5%">上月累计实际值</th>
				<th width="5%">当月实际值</th>
				<th width="5%">当月累计目标值</th>
				<th width="5%">当月累计实际值</th>
				<th width="5%">下月挑战目标</th>
				<th width="5%">月份</th>
				<th width="5%">考核类型</th>
				
				<th width="6%">操作</th>
			</tr>
		</thead>
		<tbody id="monthAssesmontListTbody">
	 <c:forEach items="${list}" var="o" varStatus="s"> 
			<tr id="${o.monthKey }_${o.uigroupCategory.id}" target="code_carrier" rel="${o.uigroupCategory.id}" >
				<td rowspan="${o.uigroupCategory.monthNum}" class="uigroup">
				<input type="radio" group="code" name="id" value="${o.monthKey }_${o.uigroupCategory.id}">
				
				</td>
				<td class="monthNum" rowspan="${o.uigroupCategory.monthNum}">${o.uigroupCategory.department}</td>
				<td  rowspan="${o.uigroupCategory.monthNum}">${o.uigroupCategory.targetclass}</td>
				<td  rowspan="${o.uigroupCategory.monthNum}">${o.uigroupCategory.indexcontent}</td>
				<td  rowspan="${o.uigroupCategory.monthNum}">${o.uigroupCategory.performancecontent}</td>
								
				<c:forEach items="${o.uigroupCategory.item}" var="vo" begin="0" end="0">
					<td rowspan="${vo.monthNum}">${vo.referencevalue}</td>
				    <td rowspan="${vo.monthNum}">${vo.yeartargetvalue}</td>
				    
				    <c:forEach items="${vo.uiindexs }" var="in" begin="0" end="0">
						<td  rowspan="${in.monthNum}">${in.basevalue }</td>
					    <td  rowspan="${in.monthNum}">${in.targetvalue }</td>
					    <td rowspan="${in.monthNum}">
					       <shiro:hasPermission name="per:month:ADD"> 
					            <a  href="per/month/add.do?id=${vo.id}&itemKey=${vo.itemKey}&indexKey=${in.indexKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="新增"><span>新增</span></a>
					       </shiro:hasPermission>  
					   </td>
			 								     
				            <td>${o.accumulatedmonth } </td>
				            <td>${o.lastmonthactual } </td>
				            <td>${o.monthreality } </td>
				            <td>${o.targetvaluemonth } </td>
				            <td>${o.accumumonth } </td>
				            <td>${o.record } </td>					   
				            <td>${o.monthvalue } </td>
				            <td>${o.lastupdateuser } </td>
				            <td>
					          	<shiro:hasPermission name="per:month:EDIT"> 
					            <a class="edit" href="per/month/edit.do?monthKey=${o.monthKey}"  rel="addCarrier"  target="dialog" width="900" height="500"  mask="true"  title="修改月度基准"><span>修改</span></a>
					          	</shiro:hasPermission>  
					          	<shiro:hasPermission name="per:month:DEL"> 
					            <a class="delete" href="per/month/delete.do?monthKey=${o.monthKey}" target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a>					             
					          	</shiro:hasPermission>
					        </td>
					    </c:forEach>		    					
					  </c:forEach>
					</c:forEach>	
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>	    
					       