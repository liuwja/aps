<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function(){

});
 
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/mesDataSum/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
			    <jsp:include page="../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="area"/>
					<jsp:param value="0" name="category"/>
					<jsp:param value="0" name="fcategory"/>
					<jsp:param value="0" name="checkItem"/>
					<jsp:param value="0" name="fgroup"/>
					<jsp:param value="1" name="fagroup"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="0" name="isColspan"/>
				</jsp:include>			  		
				<td>
					月份：
				</td>
				<td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="YYYY-MM" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${param.queryMonth }" readonly="readonly" />
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

		</ul>
	</div>
	<table class="table" width="200%" layoutH="115">
		<thead>
			<tr>
			    <th width="3%">选择</th>
			    <th width="3%">工厂</th>
				<th width="3%">车间</th>
				<th width="4%">班组名称</th>
				<th width="3%">月份</th>
				<th width="3%">组装退次数</th>
				<th width="3%">组装投产数</th>
				<th width="2%">FQC不良频次</th>
				<th width="2%">FQC抽检频次</th>
				<th width="2%">IPQC不良频次</th>
				<th width="3%">IPQC抽检频次</th>
				<th width="3%">IPQC发生流程节点为A/B/C的质量风险分数</th>
				<th width="3%">IPQC发生流程节点为D的次数</th>
				<th width="3%">冲压不良数</th>
				<th width="3%">冲压入库数</th>
				<th width="3%">喷涂下件合格数</th>
				<th width="3%">喷涂挂件数</th>
				<th width="3%">喷涂退次数</th>
				<th width="3%">OQC不良台数</th>
				<th width="3%">组装停线次数</th>
				<th width="3%">组装维修数</th>
				<th width="3%">市场开箱-开箱不良次数</th>
				<th width="3%">市场开箱-流行性不良次数</th>
				<th width="3%">精加工不良数   </th>
				<th width="3%">精加工抽检总数   </th>
				<th width="3%">5M1E发生流程节点为A/B/C的质量风险分数  </th>
				<th width="3%">5M1E发生流程节点为D的质量风险分数 </th>
				<th width="3%">公司外审不符合次数</th>
				<th width="3%">公司内审不符合次数   </th>
				<th width="3%">系统内审不符合次数   </th>
				<th width="3%">巡检5M1E次数  </th>
				<th width="3%">工艺纪律检查次数 </th>
				<th width="3%">盲点测试 次数 </th>
				<th width="3%">附加分数</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr >
					<td>
						<input type="radio" group="code" name="key" value="">
					</td>
					<%--需维护资料--%>
					<td>${o.factory}</td>
					<td>${o.areas}</td>
					<td>${o.shiftGroupName}</td>  
					<td><fmt:formatDate value="${o.sumDate }" type="date" pattern="yyyy-MM" /></td> 
					<td>${o.col1 eq null ? '-' : o.col1 }</td>
					<td>${o.col2 eq null ? '-' : o.col2 }</td>					
					<td>${o.col3 eq null ? '-' : o.col3 }</td>
					<td>${o.col4 eq null ? '-' : o.col4 }</td> 
					<td>${o.col5 eq null ? '-' : o.col5 }</td>
			  	    <td>${o.col6 eq null ? '-' : o.col6 }</td>
			  	    <td>${o.col7 eq null ? '-' : o.col5 }</td>
			  	    <td>${o.col8 eq null ? '-' : o.col6 }</td>
					<td>${o.col10 eq null ? '-' : o.col10 }</td>
			 		<td>${o.col11 eq null ? '-' : o.col11 }</td>
			 		<td>${o.col12 eq null ? '-' : o.col12 }</td>
			 		<td>${o.col13 eq null ? '-' : o.col13 }</td>
			 		<td>${o.col15 eq null ? '-' : o.col15 }</td>
			 		<td>${o.col16 eq null ? '-' : o.col16 }</td>
					<td>${o.col17 eq null ? '-' : o.col17 }</td>
					<td>${o.col18 eq null ? '-' : o.col18 }</td>
					<td>${o.col19 eq null ? '-' : o.col19 }</td>
					<td>${o.col20 eq null ? '-' : o.col20 }</td>
					<td>${o.col21 eq null ? '-' : o.col21 }</td>
					<td>${o.col22 eq null ? '-' : o.col22 }</td>
					<td>${o.col25 eq null ? '-' : o.col25 }</td>
					<td>${o.col26 eq null ? '-' : o.col26 }</td>
					<td>${o.col27 eq null ? '-' : o.col27 }</td>
					<td>${o.col28 eq null ? '-' : o.col28 }</td>
					<td>${o.col29 eq null ? '-' : o.col29}</td>
					<td>${o.col30 eq null ? '-' : o.col30  }</td>
					<td>${o.col31 eq null ? '-' : o.col31 }</td>
					<td>${o.col32 eq null ? '-' : o.col32 }</td>
					<td>${o.col9 eq null ? '-' : o.col9 }</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
