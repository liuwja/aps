<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function(){
	//loadPartTypeChart();  
});
function loadPerSelList(){
var startTime = $("#startTime", navTab.getCurrentPanel()).val();
var endTime = $("#endTime", navTab.getCurrentPanel()).val();

if(startTime ==''){
	alert('请选择开始月份');
	return false;
}
if(endTime ==''){
	alert('请选择结束月份');
	return false;
	}
	$('#perSl').submit();
}

</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="shiftGroupPerformanceChart/performanceSelectListDo.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
		    <tr>
		        <td>工厂</td>
		        <td>
		             <select id="factory" name="factory"  >
					   <option value="">请选择</option>
					   <option value="燃气工厂">燃气工厂</option>
					   <option value="电器工厂">电器工厂</option>
					</select>
					<script type="text/javascript">
						$("#factory", navTab.getCurrentPanel()).val("${param.factory}"); 
				    </script>
		        </td>
		    
			    <td>
					班组：
				</td>
				<td>
					<select id="shiftGroupTxt" name="shiftGroupTxt">
					   <option value="">请选择</option>
					   <c:forEach items="${vo}" var="vo">
					       <option value="${ vo.name}">${vo.name }</option>
					   </c:forEach>
					</select>
					<script type="text/javascript">
						$("#shiftGroupTxt", navTab.getCurrentPanel()).val("${param.shiftGroupTxt}"); 
				    </script>
				</td>	
				<td>
					期间：
				</td>
				<td>
					<input type="text" id="startTime" name="startTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})"M" value="${param.startTime }" readonly="readonly"/>&nbsp;至&nbsp;
					<input type="text" id="endTime" name="endTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})"M" value="${param.endTime }" readonly="readonly"/>
				</td>				
				
				<td><div class="buttonActive"><div class="buttonContent"><button id="perSl" type="button" onclick="loadPerSelList();">查找</button></div></div>
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	
	<table class="table" width="100%" layoutH="90">
		<thead>
			<tr>
			    <th width="15%">排名</th>
			    <th>班组</th>
				<th>分数</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o" varStatus="status">
				<tr >
					<td>
						${status.index+1}
					</td>
					
					
					<td>${o.shiftGroupTxt}</td>
					<td>${o.shiftGroupScore}</td>			
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
