<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css" media="screen">
	${demo.css}
	
.my-uploadify-button {
    background:none;
    border: none;
    text-shadow: none;
    border-radius:0;
}

.uploadify:hover .my-uploadify-button {
    background:none;
    border: none;
}

.fileQueue {
    width: 400px;
    height: 150px;
    overflow: auto;
    border: 1px solid #E5E5E5;
    margin-bottom: 10px;
}
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
	$("#detailDiv").hide();
	setTimeout("loadDetailChart()",50);		
});
function loadDetailChart() {
	
	var factory = '${vo.factory}';
	var queryMonth = '${vo.queryMonth}';
//	alert(factory);
	var jsonVar = {factory:factory,queryMonth:queryMonth};
	if(factory == ""){
		 delete jsonVar["factory"]; 
	}
	if(queryMonth == ""){
		delete jsonVar["queryMonth"]; 
	}
	var url = "";
	var url = "<c:url value='groupPerformanceChart/getPerformanceSingleChar.do'/>";
	$.post(url, jsonVar, function(data) {

		if (data.result == 0) {
			groupShowChart("detailChart", data.chartsInfo,queryMonth);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}	
</script>

	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<div class="searchBar dropdownSearchBar" id="detailDiv">
		<table class="searchContent" >
			<tr style="height: 35px;line-height: 28px">
			    <th>工厂：</th>
               <td>
				  <select id="sgcfactory" name="factory" onchange="getSgcarea();" >
					   <option value="">请选择</option>
					   <option value="燃气工厂">燃气工厂</option>
					   <option value="电器一厂">电器一厂</option>
					    <option value="电器二厂">电器二厂</option>
				  </select>
					<script type="text/javascript">
						$("#sgcfactory", navTab.getCurrentPanel()).val("${param.factory}"); 
				    </script>			
    			</td>
				<td>车间</td>
				<td>
				     <select  id="sgcArea" name="area" >
					   <option value="">请选择</option>
					        <c:forEach items="${area}" var="ar">
		                       <option value="${ar.area}">${ar.area}</option>
		                    </c:forEach>   
					</select>
					<script type="text/javascript">
						$("#sgcArea", navTab.getCurrentPanel()).val("${param.area}"); 
				    </script>
				</td>
                <th>班组名称：</th>
                <td>
					<div id="shiftGroupList" class="dropdownlist"></div>
					
    			</td>
    					    			
				<th>期间：</th>
                <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${time.queryMonth }" readonly="readonly"/>
					<input type="hidden" id="region" value="（单月）">
    			</td> 			
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" id="sigbutton" onclick="loadPerformanceChart();">查找</button></div></div>
				</td>				
			</tr>

		</table>
	</div>
	</form>

<div class="pageContent">
		<div id="detailChart" class="singleChartDiv"></div>
</div>